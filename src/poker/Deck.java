package poker;

import java.util.*;

public class Deck{
   List<Card> cards;

   // TODO: implement draw()

   Deck(){
       // Initialize the deck with all the cards
       // Step-1: Initialize the ranks
       String[] ranks = new String[]{"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};

       // Step-2: Initialize the suits
       String[] suits = new String[]{"club", "diamond", "heart", "spade"};

       // Step-3: Initialize the cards
       cards = new ArrayList<>();
       for(int i = 0; i < suits.length; i++){
           for(int j = 0; j < ranks.length; j++){
               cards.add(new Card(suits[i], ranks[j]));
           }
       }

       // All 52 cards have been initialized, now shuffle the deck.
       shuffle(cards);
   }

   void shuffle(List<Card> cards){
       Collections.shuffle(cards);
   }

    /**
     * Distributes the topmost card to the player
     * @return Card card: Topmost card
     */
   Card distributeCard(){
       Card c= cards.get(cards.size() - 1);
       cards.remove(cards.size() - 1);
       return c;
   }

    /**
     * <p>
     * burnCard() function implements the functionality of removing the topmost card after each round.
     * This ensures that there is fairness in the game by observing that the privacy of the topmost card is not compromised.
     * </p>
     */
   void burnCard(){
       cards.remove(cards.size() - 1);  // Burn the topmost card, to maintain fairness.
   }

}
