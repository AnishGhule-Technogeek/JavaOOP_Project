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
https://discord.com/channels/882173463404634182/893389412275216384

https://www.pokernews.com/poker-rules/texas-holdem.htm
https://lucid.app/lucidchart/a56a43b2-48b2-412d-b472-900453bb3cb1/edit?invitationId=inv_a8312b81-96e0-445f-a5a7-bc516b578971

To join the video meeting, click this link: https://meet.google.com/jwr-frrt-csn
Otherwise, to join by phone, dial +1 413-338-4613 and enter this PIN: 783 411 482#
 */


/*
 * Rules:
 * In a game of Texas hold'em, each player is dealt two cards face down
 (the 'hole cards')
 * Throughout several betting rounds, five more cards are (eventually)
 dealt face up in the middle of the table
 * These face-up cards are called the 'community cards.' Each player is
 free to use the community cards in combination with their hole cards to
 build a five-card poker hand.
 * While we will see each betting round and different phase that forms a full
  hand of a Texas hold'em game, you should know that the five community cards
  are dealt in three stages:
    The Flop: the first three community cards.
    The Turn: the fourth community card.
    The River:The fifth and final community card.
 * Your mission is to construct your five-card poker hands using the best
 available five cards out of the seven total cards (the two hole cards and
 the five community cards).
 * You can do that by using both your hole cards in combination with three
 community cards, one hole card in combination with four community cards,
 or no hole cards.
 * If the cards on the table lead to a better combination, you can also play
 all five community cards and forget about yours.
 * In a game of Texas hold'em you can do whatever works to make the best
 five-card hand.
 * If the betting causes all but one player to fold, the lone remaining
 player wins the pot without having to show any cards.
 * For that reason, players don't always have to hold the best hand to win
 the pot. It's always possible a player can 'bluff' and get others to fold
  better hands
 * If two or more players make it all of the way to the showdown after the
 last community card is dealt and all betting is complete, the only way to
 win the pot is to have the highest-ranking five-card poker hand.
 * Before every new hand begins, two players at the table are obligated to
  post small and big blinds. (The blinds are forced bets that begin the
  wagering.)
 * As the number of players keeps decreasing and the stacks of the remaining
 players keep getting bigger, it is a necessity that the blinds keep
 increasing throughout a tournament.
 *
 *
 * Feature:
 * The Button
 * The play moves clockwise around the table, starting with action to the
 left of the dealer button. The button determines which player at the
 table is the acting dealer.
 * RULE: In Texas hold'em, the player on button, or last active player closest to the button receives the last action on all post-flop streets of play.
 *
 * Rounds ('Streets' as the game's lingo):
 * Preflop -
 * Flop    -
 * Turn    -
 * River   -
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



import javax.management.InvalidAttributeValueException;
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
    public List<Player> players;
    private int hasBet;
    private int highestBet;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void addCommunityCards(Card card){
        communityCards.add(card);
    }

    Table(){
        round = 0;
        potAmount = 0;
        communityCards = new ArrayList<Card>();
        deck = new Deck();
        players = new ArrayList<Player>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Card distributeCard(){
        return deck.distributeCard();
    }

    public void shuffle(){
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
}


class Poker{
    public static void main(String[] args) {
        Table dealer = new Table();

        int MAX_PLAYERS = 4;

        Scanner sc = new Scanner(System.in);
    
        System.out.printf("Are the number of players = %d? (Y/N): ", MAX_PLAYERS);
        String confirmation = sc.nextLine();
        if(confirmation.equalsIgnoreCase("N")){
            System.out.println("Enter the number of players: ");
            int numPlayers = sc.nextInt();
            String flush = sc.nextLine();
            MAX_PLAYERS = numPlayers;
        }
        
        for(int i = 0; i < MAX_PLAYERS; i++){
            System.out.printf("Enter Player %d Name: ", i + 1);
            String name = sc.nextLine();

            System.out.printf("How many chips, %s? ", name);
            int chips = sc.nextInt();

            // Create a new player in each iteration
            Player player = new Player(name, chips);
            // Distribute each player two topmost cards
            player.setCards(dealer.distributeCard());
            player.setCards(dealer.distributeCard());

            // Finally add each player to player List
            dealer.players.add(player);

            // Flush sc
            String flush = sc.nextLine();
        }

        int currentRound = 0;
        // Round 0 : Pre-Flop
        {
            System.out.printf("\n\tROUND - %d\n", currentRound);
            int i = 0;
            boolean isBet = false;
    
            // DEBUG
            System.out.println("Pot amount = " + dealer.potAmount);
            
            // Reveal two community cards
            // Burn the topmost card
            dealer.deck.burnCard();

            System.out.println("Revealing two community cards.\n" +
                    "Press Enter to continue ");
            String userSleep = sc.nextLine();

            Card communityCard;
            // Add two cards to the community card deck of all active players
            // 1st Card
            dealer.addCommunityCards(dealer.distributeCard());
            communityCard = dealer.getCommunityCards().get(0);
            communityCard.setFaceUp(true);
            System.out.println(communityCard.toString());
            // 2nd Card
            dealer.addCommunityCards(dealer.distributeCard());
            communityCard = dealer.getCommunityCards().get(1);  // currentRound-1 always yields the index of latest added card in each round after preflop
            communityCard.setFaceUp(true);
            System.out.println(communityCard.toString());
            System.out.println();

            boolean allBetEqual = false;
            while(!allBetEqual)  //i != dealer.players.size() - 1
            {
                if (dealer.players.get(i).isActive()) {
                    dealer.players.get(i).isCurrentPlayer = true;
                    boolean safetyCheck = false;
                    while (!safetyCheck) {
                        System.out.printf("\n\tROUND - %d\n", currentRound);
                        System.out.printf("What do you want to do, %s?\n", dealer.players.get(i).getName());

                        if(!isBet) {
                            System.out.println(
                                "\t3 - Fold\n" +
                                "\t4 - Bet\n");
                        } else{
                            System.out.println(
                                    "\t1 - Call\n" +
                                    "\t2 - Raise\n" +
                                    "\t3 - Fold\n");
                        }

                        int choice = sc.nextInt();

                        safetyCheck = true;

                        switch (choice) {
                            case 1:
                                if(!isBet){
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
                                if(!isBet){
                                    System.out.println("No bet to raise");

                                    break;
                                }
                                System.out.println("What's the final amount you want to bet? ");
                                int raisedAmount = sc.nextInt();
                                // Flush sc
                                String flush = sc.nextLine();
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
                                }
                                else{
                                    System.out.println("Bet higher than current amount of chips. Try again");
                                    safetyCheck = false;
                                    break;
                                }
                            case 3:
                                System.out.printf("You quit from the game, %s\n", dealer.players.get(i).getName());
                                dealer.players.get(i).fold();
                                break;
                            case 4:
                                if(!isBet) {
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
                                }
                                else{
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
                for(int j = 0; j < dealer.players.size(); j++) {
                    if (dealer.players.get(j).isActive()) {
                        allBetEqual = allBetEqual && dealer.players.get(j).isEqualBet();
                        System.out.println(allBetEqual);
                    }
                }
            }

            for(int j = 0; j < dealer.players.size(); j++){
                dealer.players.get(j).setEqualBet(false);
            }

            // Now move on to the next round
            dealer.setRound(++currentRound);
        }
        // After completion of first round, add amount to pot
        dealer.potAmount += dealer.getHighestBet();
        // DEBUG
        System.out.println("Pot amount = " + dealer.potAmount);


        // Rounds : 1 (Flop), 2 (Turn), 3 (River)
        while(dealer.getRound() != 3){
            // Print the current round
            System.out.printf("\n\tROUND - %d\n", currentRound);

            for(int j = 0; j < dealer.players.size(); j++){
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
            dealer.addCommunityCards(dealer.distributeCard());
            Card communityCard = dealer.getCommunityCards().get(currentRound - 1);  // currentRound-1 always yields the index of latest added card in each round after preflop
            communityCard.setFaceUp(true);

            System.out.println(communityCard.toString());

            for(int itr = 0; itr < dealer.players.size(); itr++){
                if(!dealer.players.get(itr).isActive()){
                    continue;
                }
                dealer.players.get(itr).setCards(communityCard);
            }

            while(!allBetEqual) {
                if (dealer.players.get(i).isActive()) {
                    dealer.players.get(i).isCurrentPlayer = true;
                    boolean safetyCheck = false;
                    while (!safetyCheck) {
                        System.out.printf("\n\tROUND - %d\n", currentRound);
                        if(!isBet) {
                            System.out.printf("What do you want to do, %s?\n" +
                                            "\t4 - Bet\n" +
                                            "\t5 - Check\n",
                                    dealer.players.get(i).getName());
                        }
                        else{
                            System.out.printf("What do you want to do, %s?\n" +
                                            "\t1 - Call\n" +
                                            "\t2 - Raise\n" +
                                            "\t3 - Fold\n",
                                    dealer.players.get(i).getName());
                        }


                        int choice = sc.nextInt();
                    
                        safetyCheck = true;
                        
                        switch (choice) {
                            case 1:
                                if(!isBet){
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
                                if(!isBet){
                                    System.out.println("No bet to raise");

                                    break;
                                }
                                System.out.println("What's the final amount you want to bet? ");
                                int raisedAmount = sc.nextInt();
                                // // Flush sc
                                // String flush = sc.nextLine();
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
                                }
                                else{
                                    System.out.println("Bet higher than current amount of chips. Try again");
                                    safetyCheck = false;
                                    break;
                                }
                            case 3:
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
                                if(!isBet) {
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
                                }
                                else{
                                    System.out.println("Bet already done this round.");
                                    break;
                                }
                            case 5:
                                if(isBet){
                                    System.out.println("A member has bet before, so you cannot check");
                                    safetyCheck = false;
                                    break;
                                }
                                System.out.printf("%s, you checked.\n" +
                                        "Thus, passed to %s\n",
                                        dealer.players.get(i),
                                        dealer.players.get(i + 1));
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
                }
                else {
                    i = ++i % MAX_PLAYERS;
                }

                allBetEqual = true;
                for(int j = 0; j < dealer.players.size(); j++){
                    if(dealer.players.get(i).isActive()) {
                        allBetEqual = allBetEqual && dealer.players.get(j).isEqualBet();
                    }
                }
            }

            dealer.setRound(dealer.getRound()+1);


            // After completion of each round, add amount to pot
            dealer.potAmount += dealer.getHighestBet();
            // DEBUG
            System.out.println("Pot amount = " + dealer.potAmount);
        }

        // TODO: Change cards of all players to faceUp

        // TODO: Declaring the winner
    }
}
