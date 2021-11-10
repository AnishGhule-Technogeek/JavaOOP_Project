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

import java.lang.reflect.Array;

/**
 * <p>Handles the flow of the game, deals the cards, and has the deck.</p>
 * <pre><b>Attributes:</b>
 *      +deck:Deck
 *      +communityCards:Card[]
 * </pre>
 * <p>Player to the immediate left of the dealer puts half the minimum bet (small blind)
 * and the player to that person's left</p>
 */
public class Table {
    
    private int round;
    public Deck deck;
    public Card[] communityCards;
    public float potAmount;
    public Player[] players;
    
    public int getRound() {
        return round;
    }
    
    public void setRound(int round) {
        this.round = round;
    }
    
    Table(){
        round = 0;
        potAmount = 0f;
        communityCards = new Card[52];
        deck = new Deck();
    }
    
}


class Poker{
    public static void main(String[] args) {
        Table dealer = new Table();
        
    }
}
