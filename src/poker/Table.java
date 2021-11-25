/**
 * CS F213 - Object Oriented Programming, BITS Pilani
 * <p><b>OOP Project - Simulate poker</b></p>
 *
 * <p><u>Description</u>:
 * You’ve been asked to simulate a game of <a href="https://en.wikipedia.org/w/index.php?title=Texas_hold_%27em&oldid=1046002735"> Texas hold ‘em poker </a>".
 * Your application should support upto 4 players playing on a table simultaneously.
 * Players would be dealt 2 cards face down, post which they should be allowed to
 * bet, check, raise, call, or fold as necessary. For learning the rules, go through the
 * wikipedia page linked above or look at some tutorials on YouTube. After each
 * round of betting, face up cards would be revealed (3 or 1 as the case may be)
 * and then the winner declared in the end.</p>
 */







/*
Description:
You’ve been asked to simulate a game of Texas hold ‘em poker. Your application should support upto 4 players playing on a table simultaneously. Players would be dealt 2 cards face down, post which they should be allowed to check, raise, call, or fold as necessary. For learning the rules, you go through the wikipedia page linked above or look at some tutorials on YouTube. After each round of betting, face up cards would be revealed (3 or 1 as the case may be) and then the winner declared in the end.

Class Design:
Here is the list of the basic classes, interfaces and methods one should implement -
>> Table (Dealer) : Handles the flow of the game, deals the cards, has the deck.
>> Player : Attributes like name, current chips, and should support actions like the ones listed in the description
>> Deck : Made up of Cards. Should support functionality like deal, and shuffle.
>> Card : Made of rank and suit. Should also keep track of whether it is face up or face down and should support functionality to flip it.

Sample input and outputs:
The first input would be the name of players and the initial chips that
different players have. During play, the private cards of all the players,
the community cards, and the total amount that has been bet should be on
display.
If the community cards have not been revealed yet, they should
printed as a “*”

On a player’s turn, he should be presented with a list of all options
available to him (call, raise, fold, check etc).
If he tries to bet more than the amount of chips available to him, an error
message should appear, and he should be asked to rechose.
Once all bets have been matched, community cards would be revealed and
this play would continue.
In the end, the winner would be displayed.

 */


package poker;

///////////////////////////////////////////////////////////////////////////
// Table class (Dealer): Handles the flow of the game, deals the cards, has the deck.
///////////////////////////////////////////////////////////////////////////


import java.util.*;

/**
 * <p>Handles the flow of the game, deals the cards, and has the deck.</p>
 * <pre><b>Attributes:</b>
 *      +deck:Deck
 *      +communityCards:Card[]
 * </pre>
 * <p>Player to the immediate left of the dealer puts half the minimum bet (small blind)
 * and the player to that person's left puts at least twice the small blind (big blind).</p>
 */
public class Table {
    
    public Deck deck;
    private int round;
    private List<Card> communityCards;
    public int potAmount;
    public int pot2Amount = -1;//after all in potAmount moved into pot2Amount and potAMount acts like sidepot
    public int pot3Amount = -1;
    public List<Player> players;
    private int hasBet;
    private int highestBet;
    static boolean isWinnerDeclared;
    
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    public List<Card> getCommunityCards() {
        return communityCards;
    }
    
    public void addCommunityCards(Card card) {
        communityCards.add(card);
    }
    
    Table() {
        round = 0;
        potAmount = 0;
        communityCards = new ArrayList<Card>();
        deck = new Deck();
        players = new ArrayList<Player>();
        isWinnerDeclared = false;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public Card distributeCard() {
        return deck.distributeCard();
    }
    
    public void shuffle() {
        deck.shuffle(deck.cards);
    }
    
    public int getHighestBet() {
        return highestBet;
    }
    
    public void setHighestBet(int highestBet) {
        this.highestBet = highestBet;
    }
    
    public int getHasBet() {
        return hasBet;
    }
    
    public void setHasBet(int hasBet) {
        this.hasBet = hasBet;
    }
    
    public void end() {
        final int MAX_CARDS_IN_PLAYER_DECK = 7;
        
        List<Integer> rank = new ArrayList<>();
        List<String> highCard = new ArrayList<>();
        List<String> highCard2 = new ArrayList<>();
        for (Player p : players) {
            if (p.isActive()) {
                int r = 11;  // temp value
                String h1, h2 = "2";  // temp value
                // Create new array with player cards + community
    
                List<Card> allCards = new ArrayList<Card>(p.getCards());
                
                // Sort cards
                // compareTo() method in Card, sorts in descending rank order
                // i.e. A > K > Q > J > 10 > 9 > 8 > 7  > 6 > 5 > 4 > 3 > 2
                Collections.sort(allCards);
                
                // check 4 of a kind r = 3
                List<String> cardRank = new ArrayList<>();
                int[] cardFreq = new int[7];
                int ind = 0;
                
                // Iterate through all cards of player
                // If the player's card is not there in cardRank, add it, and increase its corresponding frequency in cardFreq
                // Else if the player's card is there in cardRank, increase it's corresponding frequency in cardFreq
                for (Card c : p.getCards()) {
                    if (!cardRank.contains(c.getRank())) {
                        cardRank.add(c.getRank());
                        cardFreq[ind++] = 1;
                    } else {
                        cardFreq[cardRank.indexOf(c.getRank())] += 1;
                    }
                }
                
                int m1 = cardFreq[0];
                h1 = cardRank.get(0);
                int m2 = 0;
                for (int k = 1; k < MAX_CARDS_IN_PLAYER_DECK; k++) {
                    if (cardFreq[k] > m1) {
                        h2 = h1;  // store it in the temp variable
                        m2 = m1;  // store it in the temp variable
                        m1 = cardFreq[k];
                        h1 = cardRank.get(k);
                    } else if (cardFreq[k] > m2) {
                        m2 = cardFreq[k];
                        h2 = cardRank.get(k);
                    }
                }
                
                // m1 and h1 correspond to the max frequency card now
                // m2 and h2 correspond to the second highest frequency card now
                if (m1 == 4) {
                    r = 3;
                } else if (m1 == 3 && m2 == 2) {
                    r = 4;
                } else if (m1 == 3) {
                    r = 7;
                } else if (m1 == 2 && m2 == 2) {
                    r = 8;
                } else if (m1 == 2) {
                    r = 9;
                }
                
                // check 5 of suit
                // Iterate through all cards of player
                // If the player's card is not there in cardSuit, add it, and increase its corresponding frequency in cardSFreq
                // Else if the player's card is there in cardSuit, increase it's corresponding frequency in cardSFreq
                ArrayList<String> cardSuit = new ArrayList<>();
                String sh1, sh2 = null;
                int[] cardSFreq = new int[7];
                int indx = 0;
                for (Card c : allCards) {
                    if (!cardSuit.contains(c.getSuit())) {
                        cardSuit.add(c.getSuit());
                        cardSFreq[indx++] = 1;
                    } else {
                        cardSFreq[cardSuit.indexOf(c.getSuit())] += 1;
                    }
                }
                int n1 = cardSFreq[0];
                sh1 = cardSuit.get(0);
                
                for (int k = 1; k < MAX_CARDS_IN_PLAYER_DECK; k++) {
                    if (cardSFreq[k] > n1) {
                        n1 = cardSFreq[k];
                        sh1 = cardSuit.get(k);
                    }
                }
                // n1 and h1 correspond to the max frequency card now, associated with the suit.
                
                // FIXME: Check if there is an error in the nested if statements
                if (n1 > 4) {
                    if (r > 5) {
                        r = 5;
                    }
                    List<Card> matchCards = new ArrayList<>();
                    for (Card c : allCards) {
                        if (c.getSuit().equals(sh1)) {
                            matchCards.add(c);
                        }
                        int strt = matchCards.get(0).parseRank(matchCards.get(0).getRank());
                        h1 = matchCards.get(0).getRank();
                        int count = 1;
                        for (int l = 1; l < matchCards.size(); l++) {
                            if (matchCards.get(l).parseRank(matchCards.get(l).getRank()) + count == strt) {
                                count++;
                                
                                if (count == 5) {
                                    if (strt == 14) {
                                        r = 1;
                                    } else {
                                        r = 2;
                                    }
                                }
                            } else {
                                strt = matchCards.get(l).parseRank(matchCards.get(l).getRank());
                                h1 = matchCards.get(l).getRank();
                                count = 1;
                            }
                        }
                    }
                }
                rank.add(r);
                highCard.add(h1);
                highCard2.add(h2);
            } else {
                rank.add(11);
                highCard.add("2");
                highCard2.add("2");
            }
    
        }
        
        int minRank = rank.get(0);
        int minh1 = players.get(0).getCards().get(0).parseRank(highCard.get(0));
        int minh2 = players.get(0).getCards().get(0).parseRank(highCard2.get(0));
        int minIndex = 0;
        int minIndex2 = -1, minIndex3 = -1, minIndex4 = -1;
        
        for (int a = 1; a < players.size(); a++) {
            if (rank.get(a) < minRank) {
                
                minRank = rank.get(a);
                minh1 = players.get(0).getCards().get(0).parseRank(highCard.get(a));
                minh2 = players.get(0).getCards().get(0).parseRank(highCard2.get(a));
                minIndex = a;
    
                minIndex2 = -1;
                minIndex3 = -1;
                minIndex4 = -1;
                
            } else if (rank.get(a) == minRank) {
                if (minRank == 1) {
    
                    if (minIndex2 == -1) {
                        minIndex2 = a;
                    } else if (minIndex3 == -1) {
                        minIndex3 = a;
                    } else if (minIndex4 == -1) {
                        minIndex4 = a;
                    }
    
                } else {
                    if (minh1 < players.get(0).getCards().get(0).parseRank(highCard.get(a))) {
                        
                        minRank = rank.get(a);
                        minh1 = players.get(0).getCards().get(0).parseRank(highCard.get(a));
                        minh2 = players.get(0).getCards().get(0).parseRank(highCard2.get(a));
                        minIndex = a;
                        
                    } else if (minh1 == players.get(0).getCards().get(0).parseRank(highCard.get(a))) {
                        
                        if (minh2 < players.get(0).getCards().get(0).parseRank(highCard2.get(a))) {
                            minRank = rank.get(a);
                            minh1 = players.get(0).getCards().get(0).parseRank(highCard.get(a));
                            minh2 = players.get(0).getCards().get(0).parseRank(highCard2.get(a));
                            minIndex = a;
                            minIndex2 = -1;
                            minIndex3 = -1;
                            minIndex4 = -1;
                        } else if (minh2 == players.get(0).getCards().get(0).parseRank(highCard2.get(a))) {
    
    
                            if (minIndex2 == -1) {
                                minIndex2 = a;
                            } else if (minIndex3 == -1) {
                                minIndex3 = a;
        
                            } else if (minIndex4 == -1) {
                                minIndex4 = a;
                            }
                        }
                    }
                }
            }
    
        }
        players.get(minIndex).setWinner(true);
        if (minIndex2 != -1) {
            players.get(minIndex2).setWinner(true);
        } else if (minIndex3 != -1) {
            players.get(minIndex3).setWinner(true);
    
        } else if (minIndex4 != -1) {
            players.get(minIndex4).setWinner(true);
        }
        // Before closing end(), a call is given to declareWinner()'
        declareWinner();
    }
    
    
    ArrayList<Player> winners = new ArrayList<>();
    int[] share = new int[4];
    
    public void declareWinner() {
        int pot;
    
        for (int i = 3; i > 0; i--) {
            if (i == 3) {
                pot = pot2Amount;
            } else if (i == 2) {
                pot = pot3Amount;
            } else if (i == 1) {
                pot = potAmount;
            }
        
            int w = 0;
            for (Player player : players) {
                if (player.getAllIn() < i && player.isWinner()) {
                    w++;
                }
            }
            int j = 0;
            for (Player player : players) {
                if (player.getAllIn() < i && player.isWinner()) {
                    share[j] = potAmount / w;
                    isWinnerDeclared = true;
                }
                j++;
            }
        }
    
        int j = 0;
        for (Player player : players) {
            if(player.isWinner()) {
                System.out.printf("Congratulations, %s! You get the amount %d\n", player.getName(), share[j]);
            }
            j++;
        }
    
    }
    // Print all the winners
    
}






    
    
    /*
    public void win(){
        List<Integer> rank = new ArrayList<>();
        List<String> highCard = new ArrayList<>();

        List<String> highCard2 = new ArrayList<>();

        for(Player p : players) {
            if (p.isActive()) {
                int r;  // temp value
                String h1, h2;  // temp value
                // Create new array with player cards + community cards
                List<Card> allCards = new ArrayList<>();
                allCards.addAll(p.getCards());
                // allCards.add(p.getCards().get(0));
                // allCards.add(p.getCards().get(1));
                allCards.addAll(communityCards);
                // allCards.add(communityCards.get(0));
                // allCards.add(communityCards.get(1));
                // allCards.add(communityCards.get(2));
                // allCards.add(communityCards.get(3));
                // allCards.add(communityCards.get(4));


                // Sort cards
                // compareTo() method in Card
                Collections.sort(allCards);

                //check 4 of a kind r=3
        ArrayList<String> cardRank = new ArrayList<>();
        int cardFreq[] = new int[7];
        int ind=0;
        for (Card c:allCards) {
            if (cardRank.indexOf(c.getRank()) == -1) {
                cardRank.add(c.getRank());
                cardFreq[ind++] = 1;
            } else {
                cardFreq[cardRank.indexOf(c.getRank())] += 1;
            }
        }
        int m1=cardFreq[0];
        h1= cardRank.get(0);
        int m2=0;
        for(int k=1;k<7;k++){
            if(cardFreq[k]>m1){
                h2=h1;
                m2=m1;
                m1=cardFreq[k];
                h1=cardRank.get(k);
            }
            else if(cardFreq[k]>m2){

                m2=cardFreq[k];
                h2=cardRank.get(k);
            }

        }
        if(m1==4){
            r=3;
        }
        else if(m1==3&&m2==2){
            r=4;
        }
        else if(m1==3){
            r=7;
        }
        else if(m1==2&&m2==2){
            r=8;
        }
        else if(m1==2){
            r=9;
        }

        //check 5 of suit
        //check royal flush 1 AKQJ10
        //check straight flush 2 any 5 consecutive
        //else flush 5 but check 3 and 4 present also
                // check straigt (5 consecutive regardless of suite)

                //highest value
            }
            else{
                rank[i++]=11;
            }
        }
    }
    */


class Poker {

    public static void main(String[] args) {
        Table dealer = new Table();
    
        int MAX_PLAYERS = 4;
    
        Scanner sc = new Scanner(System.in);
    
        System.out.printf("Are the number of players = %d? (Y/N): ", MAX_PLAYERS);
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("N") || confirmation.equalsIgnoreCase("No")) {
            System.out.println("Enter the number of players: ");
            int numPlayers = sc.nextInt();
            String flush = sc.nextLine();
            MAX_PLAYERS = numPlayers;
        }
    
        for (int i = 0; i < MAX_PLAYERS; i++) {
            System.out.printf("Enter Player %d Name: ", i + 1);
            String name = sc.nextLine();
        
            System.out.printf("How many chips, %s? ", name);
            int chips = sc.nextInt();
        
            // Create a new player in each iteration
            Player player = new Player(name, chips);
            // Distribute each player two topmost cards
            player.setCards(dealer.distributeCard());
            player.setCards(dealer.distributeCard());
            player.getCards().get(0).setFaceUp(true);
            player.getCards().get(1).setFaceUp(true);
        
            // Finally add each player to player List
            dealer.players.add(player);
        
            // Flush sc
            String flush = sc.nextLine();
        }
    
        // The boolean variable flag decides if the same players want to play the game again.
        // It is set to false, by default, which means a single game with same players.
        boolean flag = false;
    
        
        do {
            // Round 0 : Pre-Flop
            {
                System.out.printf("\n\tROUND - %d\n", dealer.getRound());
                int i = 0;
                boolean isBet = false;
        
                // DEBUG
                System.out.println("Pot amount = " + dealer.potAmount);
        
                // Reveal two community cards
                // Burn the topmost card
                dealer.deck.burnCard();
        
                //System.out.println("Revealing two community cards.\n" +
                  //      "Press Enter to continue ");
                String userSleep = sc.nextLine();
        
                Card communityCard;
                // Add two cards to the community card deck of all active players
                // 1st Card
                for(int ii=0;ii<5;ii++)
                dealer.addCommunityCards(dealer.distributeCard());
                //communityCard = dealer.getCommunityCards().get(0);
                //communityCard.setFaceUp(true);
                //System.out.println(communityCard.toString());
                // 2nd Card
                
                //communityCard = dealer.getCommunityCards().get(1);  // currentRound-1 always yields the index of latest added card in each round after preflop
                //communityCard.setFaceUp(true);
                //System.out.println(communityCard.toString());
                System.out.println();
        
                boolean allBetEqual = false;
                while (!allBetEqual)  //i != dealer.players.size() - 1
                {
                    if (dealer.players.get(i).isActive()&&dealer.players.get(i).getAllIn()==0) {
                        dealer.players.get(i).isCurrentPlayer = true;
                        boolean safetyCheck = false;
                        while (!safetyCheck) {
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            System.out.printf("\n\tROUND - %d\n", dealer.getRound());
                            System.out.println("Community Cards:"+dealer.getCommunityCards().get(0)+" "+dealer.getCommunityCards().get(1)+" "+dealer.getCommunityCards().get(2)+" "+dealer.getCommunityCards().get(3)+" "+dealer.getCommunityCards().get(4));
                            System.out.println("Player Cards:"+dealer.getPlayers().get(i).getCards().get(0)+" "+dealer.getPlayers().get(i).getCards().get(1));
                            System.out.printf("What do you want to do, %s?\n", dealer.players.get(i).getName());
                    
                            if (!isBet) {
                                System.out.println(
                                        "\t3 - Fold\n" +
                                        "\t4 - Bet\n");
                            } else {
                                System.out.println(
                                        "\t1 - Call\n" +
                                        "\t2 - Raise\n" +
                                        "\t3 - Fold\n");
                            }
                    
                            int choice = sc.nextInt();
                            String flush0 = sc.nextLine();
                    
                            safetyCheck = true;
                    
                            switch (choice) {
                                case 1:
                                    if (!isBet) {
                                        System.out.println("No bet to call upon");
                                
                                        break;
                                    }
                                    try {
                                        dealer.players.get(i).call(dealer.getHighestBet());
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("All in");
                                        int count=0;
                                        int check=0;
                                        if(dealer.pot2Amount==-1)
                                            check=1;
                                        for(Player p2: dealer.players) {
                                            if(p2.getAllIn()<3-check){
                                                count++;
                                        }
                                        }
                                        if(count>2) {
                                            if (dealer.pot2Amount == -1) {
                                                dealer.pot2Amount = dealer.potAmount;
                                                dealer.potAmount = 0;
                                                dealer.players.get(i).setAllIn(2);
                                            } else {
                                                dealer.pot3Amount = dealer.potAmount;
                                                dealer.potAmount = 0;
                                                dealer.players.get(i).setAllIn(1);
                                            }
                                        }
                                        else{
                                        //reveal remaining community card and decide winner
                                        }
                                    }
                                    break;
                                case 2:
                                    if (!isBet) {
                                        System.out.println("No bet to raise");
                                
                                        break;
                                    }
                                    System.out.println("What's the final amount you want to bet? ");
                                    int raisedAmount = sc.nextInt();
                                    // Flush sc
                                    String flush1 = sc.nextLine();
                                    if (raisedAmount > dealer.getHighestBet()) {
                                        try {
                                            dealer.players.get(i).raise(raisedAmount);
                                            for (int j = 0; j < dealer.players.size(); j++) {
                                                if (j != i)
                                                    dealer.players.get(j).setEqualBet(false);
                                            }
                                            dealer.setHighestBet(raisedAmount);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Bet higher than current amount of chips. Try again");
                                    
                                            safetyCheck = false;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Bet higher than current amount of chips. Try again");
                                        safetyCheck = false;
                                        break;
                                    }
                                case 3:
                                    System.out.printf("You quit from the game, %s\n", dealer.players.get(i).getName());
                                    dealer.players.get(i).fold();
                                    break;
                                case 4:
                                    if (!isBet) {
                                        System.out.println("Amount to bet? ");
                                        int chips = sc.nextInt();
                                        // Flush sc
                                        String flush2 = sc.nextLine();
                                        try {
                                            dealer.players.get(i).bet(chips);
                                            isBet = true;
                                            dealer.setHighestBet(chips);
                                    
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Bet higher than current amount of chips. Try again");
                                            safetyCheck = false;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Bet already done this round.");
                                        break;
                                    }
                                default:
                                    System.out.println("Not a valid Input. Please try again");
                                    --i;
                                    safetyCheck = false;
                                    break;
                            }
                        }
                
                        dealer.players.get(i).isCurrentPlayer = false;
                        i = ++i % MAX_PLAYERS;
                        
                    } else {
                        i = ++i % MAX_PLAYERS;
                    }
            
                    allBetEqual = true;
                    for (int j = 0; j < dealer.players.size(); j++) {
                        if (dealer.players.get(j).isActive()) {
                            allBetEqual = allBetEqual && dealer.players.get(j).isEqualBet();
                            //System.out.println(allBetEqual);
                        }
                    }
    
                    // After completion of each round, check if there is only one player
                    int finalRoundPlayers = 0, finalRoundPlayerIndex = 0;
                    for (int itr = 0; itr < dealer.players.size(); itr++) {
                        if (dealer.players.get(itr).isActive()) {
                            finalRoundPlayers++;
                            finalRoundPlayerIndex = itr;
                        }
                    }
    
                    // If single player remaining, set the player to be the winner
                    if (finalRoundPlayers == 1) {
                        dealer.players.get(finalRoundPlayerIndex).setWinner(true);
                        allBetEqual = true;
                    }
                    dealer.declareWinner();
                    
                }
                
                // Reset equal bet for all
                for (int j = 0; j < dealer.players.size(); j++) {
                    dealer.players.get(j).setEqualBet(false);
                }
        
                // Now move on to the next round
                dealer.setRound(dealer.getRound()+1);
            }
            // After completion of first round, add amount to pot
            dealer.potAmount += dealer.getHighestBet();
            // DEBUG
            System.out.println("Pot amount = " + dealer.potAmount);
    
            dealer.getCommunityCards().get(0).setFaceUp(true);
            dealer.getCommunityCards().get(1).setFaceUp(true);
    
            // Rounds : 1 (Flop), 2 (Turn), 3 (River)
            while (dealer.getRound() != 3 && !dealer.isWinnerDeclared) {
    
                // Print the current round
                System.out.printf("\n\tROUND - %d\n", dealer.getRound());
    
                for (int j = 0; j < dealer.players.size(); j++) {
                    dealer.players.get(j).setEqualBet(false);
                }
    
                // int currentRound = 0;  // No need to set as after preflop, we already increment the round number, likewise
                int i = 0;
                boolean isBet = false;
                boolean allBetEqual = false;
    
    
                // Do the following in each round
                // Burn the topmost card
                dealer.deck.burnCard();
    
                // Add one card to the community card deck of all active players
                
                
                Card communityCard = dealer.getCommunityCards().get(dealer.getRound() +1);  // currentRound+1 always yields the index of latest added card in each round after preflop
                dealer.getCommunityCards().get(dealer.getRound() +1).setFaceUp(true);
                
    
                System.out.println(communityCard.toString());
    
                for (int itr = 0; itr < dealer.players.size(); itr++) {
                    if (!dealer.players.get(itr).isActive()) {
                        continue;
                    }
                    dealer.players.get(itr).setCards(communityCard);
                }
                
                int i2=0;
                while (!allBetEqual) {
                    if (dealer.players.get(i).isActive()) {
                        i2++;//count of num active players who have played
                        dealer.players.get(i).isCurrentPlayer = true;
                        boolean safetyCheck = false;
                        while (!safetyCheck) {
                            System.out.printf("\n\tROUND - %d\n", dealer.getRound());
                            System.out.println("Community Cards:"+dealer.getCommunityCards().get(0)+" "+dealer.getCommunityCards().get(1)+" "+dealer.getCommunityCards().get(2)+" "+dealer.getCommunityCards().get(3)+" "+dealer.getCommunityCards().get(4));
                            System.out.println("Player Cards:"+dealer.getPlayers().get(i).getCards().get(0)+" "+dealer.getPlayers().get(i).getCards().get(1));
                            System.out.printf("What do you want to do, %s?\n", dealer.players.get(i).getName());
    
                            if (!isBet) {
                                System.out.printf("What do you want to do, %s?\n" +
                                                "\t4 - Bet\n" +
                                                "\t5 - Check\n",
                                        dealer.players.get(i).getName());
                            } else {
                                System.out.printf("What do you want to do, %s?\n" +
                                                "\t1 - Call\n" +
                                                "\t2 - Raise\n" +
                                                "\t3 - Fold\n",
                                        dealer.players.get(i).getName());
                            }


                            int choice = sc.nextInt();
                            String flush3 = sc.nextLine();

                            safetyCheck = true;

                            switch (choice) {
                                case 1:
                                    if (!isBet) {
                                        System.out.println("No bet to call upon");

                                        break;
                                    }
                                    try {
                                        dealer.players.get(i).call(dealer.getHighestBet());
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Bet higher than current amount of chips. Try again");
                                        safetyCheck = false;
                                    }
                                    break;
                                case 2:
                                    if (!isBet) {
                                        System.out.println("No bet to raise");

                                        break;
                                    }
                                    System.out.println("What's the final amount you want to bet? ");
                                    int raisedAmount = sc.nextInt();
                                    // // Flush sc
                                    String flush4 = sc.nextLine();
                                    if (raisedAmount > dealer.getHighestBet()) {
                                        try {
                                            dealer.players.get(i).raise(raisedAmount);
                                            for (int j = 0; j < dealer.players.size(); j++) {
                                                if (j != i)
                                                    dealer.players.get(j).setEqualBet(false);
                                            }
                                            dealer.setHighestBet(raisedAmount);
                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Bet higher than current amount of chips. Try again");

                                            safetyCheck = false;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Bet higher than current amount of chips. Try again");
                                        safetyCheck = false;
                                        break;
                                    }
                                case 3:
                                    i2--;
                                    System.out.printf("You quit from the game, %s\n", dealer.players.get(i).getName());
                                    dealer.players.get(i).fold();
                                    break;
                                case 4:
                                    //
                                    // System.out.println("Amount to bet? ");
                                    // int chips = sc.nextInt();
                                    // try {
                                    //     dealer.players.get(i).bet(chips);
                                    //     isBet = true;
                                    //     dealer.setHighestBet(chips);
                                    // } catch (IllegalArgumentException e) {
                                    //     System.out.println("Bet higher than current amount of chips. Try again");
                                    //
                                    //     safetyCheck = false;
                                    // }
                                    // break;
                                    //
                                    //
                                    if (!isBet) {

                                        System.out.println("Amount to bet? ");
                                        int chips = sc.nextInt();
                                        // Flush sc
                                        String flush5 = sc.nextLine();
                                        try {
                                            dealer.players.get(i).bet(chips);
                                            isBet = true;
                                            dealer.setHighestBet(chips);

                                        } catch (IllegalArgumentException e) {
                                            System.out.println("Bet higher than current amount of chips. Try again");
                                            safetyCheck = false;
                                        }
                                        break;
                                    } else {
                                        System.out.println("Bet already done this round.");
                                        break;
                                    }
                                case 5:
                                    if (isBet) {
                                        System.out.println("A member has bet before, so you cannot check");
                                        safetyCheck = false;
                                        break;
                                    }
                                    System.out.printf("%s, you checked.\n",
                                            dealer.players.get(i).getName());
                                    break;
                                default:
                                    System.out.println("Not a valid Input. Please try again");
                                    --i;
                                    safetyCheck = false;
                                    break;
                            }
                        }

                        dealer.players.get(i).isCurrentPlayer = false;
                        i = ++i % MAX_PLAYERS;
                    } else {
                        i = ++i % MAX_PLAYERS;
                    }

                    allBetEqual = true;
                    boolean allCheck=false;
                    int chk=0;
                    for (int j = 0; j < dealer.players.size(); j++) {
                        if (dealer.players.get(j).isActive()) {
                            allBetEqual = allBetEqual && dealer.players.get(j).isEqualBet();
                            allCheck=allCheck||dealer.players.get(j).isEqualBet();
                            chk++;//get number of current active players
                        }
                    }
                    if(!allCheck && i2==chk){
                        allBetEqual=true;
                    }
                    // After completion of each round, check if there is only one player
                    int finalRoundPlayers = 0, finalRoundPlayerIndex = 0;
                    for (int itr = 0; itr < dealer.players.size(); itr++) {
                        if (dealer.players.get(itr).isActive()) {
                            finalRoundPlayers++;
                            finalRoundPlayerIndex = itr;
                        }
                    }

                    // If single player remaining, set the player to be the winner
                    if (finalRoundPlayers == 1) {
                        dealer.players.get(finalRoundPlayerIndex).setWinner(true);
                        allBetEqual = true;
                    }
                    dealer.declareWinner();
                }
    
                dealer.setRound(dealer.getRound() + 1);
    
    
                // After completion of each round, add amount to pot
                dealer.potAmount += dealer.getHighestBet();
                // DEBUG
                // System.out.println("Pot amount = " + dealer.potAmount);
    
            }

            // TODO: Change cards of all players to faceUp
            for (int i = 0; i < dealer.players.size(); i++) {
                if (dealer.players.get(i).isActive()) {
                    for (int j = 0; j < dealer.players.get(i).getCards().size(); j++) {
                        dealer.players.get(i).getCards().get(j).setFaceUp(true);
                    }
                }
            }

            // TODO: Declaring the winner
            // At end of each round, we checked if only one player is remaining,
            // if yes, then we already set the player as winner, so call declareWinner()
            // else, use implemented logic at end of game to declareWinner()


            // If more than one players, then call the winner function using dealer
            dealer.end();
            
            System.out.println("Would you like to play one more game (Yes/No)");
            System.out.println("Previous winners will get the chips added");
            String op = sc.nextLine();
            
            if (op.equalsIgnoreCase("Yes") || op.equalsIgnoreCase("Y")) {
                flag = true;
                // Set up a new deck with the dealer (shuffle implicitly invoked)
                dealer.deck = new Deck();
        
                for (int i = 0; i < MAX_PLAYERS; i++) {
                    
                    String name = dealer.players.get(0).getName();
                    int chips = dealer.players.get(0).getChips();
                    // If the player is a winner, add the chips to the chips the player previously has
                    if(dealer.winners.contains(dealer.players.get(0))) {
                        chips = dealer.players.get(0).getChips() + dealer.share[i];
                    }
                    System.out.printf("%s, you have %d chips with you\n", name, chips);
                    System.out.println("Do you want to get more chips? (Y/N)");
                    String confirm = sc.nextLine();
                    if(confirm.equalsIgnoreCase("Y")){
                        System.out.println("How many more chips?");
                        int increaseChips = sc.nextInt();
                        String flush6 = sc.nextLine();
                        chips += increaseChips;
                    }
                    else {
                        System.out.println("Ok, you keep " + chips + " chips with you");
                    }
                    
                    Player player = new Player(name, chips);
    
                    // Distribute each player two topmost cards
                    player.setCards(dealer.distributeCard());
                    player.setCards(dealer.distributeCard());
                    player.getCards().get(0).setFaceUp(true);
                    player.getCards().get(1).setFaceUp(true);
            
                    // Finally add each player to player List, and remove the previous players
                    dealer.players.add(player);
                    dealer.players.remove(dealer.players.get(0));
                }
            }
            else {
                flag = false;
            }
        } while (flag);
        
    }  // End of psvm (main)
    
}  // End of poker Class
