package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck{
   Card[] cards;
   int noOfCards;
   
   //check if java is pass by ref so if we can directly overwrite array values in shuffle
   Deck(){
       /*
       // Psuedocode:
       int ranks[]=[1,2,3,4,5,6,7,8,9,10,11,12,13];
       String[] suits=['Clubs','Spades','Hearts','Diamonds']
       int i=0;
       for el in ranks:
            for e in suits:
                cards[i]=new Card(e,el);
                i+=1;
       this.shuffle();
       
        */
       
       
       // Initialize the deck with all the cards
       // Step-1: Initialize the ranks
       String[] ranks = new String[]{"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};

       // Step-2: Initialize the suits
       String[] suits = new String[]{"club", "diamond", "heart", "spade"};
       
       // Step-3: Initialize the cards
       cards = new Card[52];
       int num = 0;
       for(int i = 0; i < suits.length; i++){
           for(int j = 0; j < ranks.length; j++){
               cards[num] = new Card(suits[i], ranks[j]);
               num++;
           }
       }
       
       // DEBUG
       for(Card card: cards)
           System.out.println(card);
       
       // All 52 cards have been initialized, now shuffle the deck.
       
       shuffle(cards);
       // //DEBUG
       // for(Card card: cards)
       //     System.out.println(card);
   }
   
   void shuffle(Card[] cards){
       // FIXME: Erase the comments
       // /*
       //  * Create an array of integers (indices) from 0 to 51 in random order, random[52]
       //  * Declare a temporary array of integers, temp[52]
       //  * Iterate through cards array and assign the temp[random_index_from_random] value of card of cards
       //  * Return temp
       //  */
       // int[] random = new int[52];
       // Card[] temp = new Card[52];
       List<Card> cardsToShuffle = new ArrayList<>(Arrays.asList(cards));
       Collections.shuffle(cardsToShuffle);
    
       // DEBUG
       for(int i = 0; i < cardsToShuffle.size(); i++)
           System.out.println(cardsToShuffle.get(i));
   }
   
   void burnCard(){
       for(int i = cards.length - 1; i >=0; i--)
           if(cards[i] != null)
               cards[i] = null;  // Burn the topmost card, to maintain fairness.
       // TODO: If implementation is as a LinkedList, we can iterate while hasPrevious(), without !null redundancy.
   }
   
}
