package poker;

import java.util.*;
import java.lang.IllegalArgumentException;

public class Player {

    private String name;
    public boolean isCurrentPlayer;
    private boolean isActive;
    private ArrayList<Card> cards;
    private int chips;
    private boolean equalBet;
    private int currentBet;

    Player(String name, int chips) {
        this.name = name;
        this.isCurrentPlayer = false;
        this.isActive = true;
        cards = new ArrayList<Card>();
        this.chips = chips;
        this.equalBet = false;
        this.currentBet = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(Card card) {
        this.cards.add(card);
    }

    public int getChips() {
        return chips;
    }

    public boolean isEqualBet() {
        return equalBet;
    }

    public void setEqualBet(boolean equalBet) {
        this.equalBet = equalBet;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public void bet(int chips) throws IllegalArgumentException{
        if (chips > this.chips) {
            throw new IllegalArgumentException();
        } else {
            this.chips -= chips;
            this.currentBet = chips;
            this.equalBet = true;
            System.out.println("Updated number of chips with you " + this.getName() + " : " + this.chips);
        }
    }

    public void call(int chips) throws IllegalArgumentException{
        if (chips >= this.chips) {
            throw new IllegalArgumentException();
        } else {
            this.chips -= chips;
            this.currentBet = chips;
            this.equalBet = true;
            System.out.println("Updated number of chips with you " + this.getName() + " : " + this.chips);
        }
    }

    // ANISH: Follwing is used in Table class for raise() method
    public void raise(int chips){
        // // FIXME: See if method is safetyChecked in Table Class itself
        // while(chips < this.chips){
        //     System.out.println("You must enter a higher amount!");
        //     System.out.println("What's the final amount you want to bet? ");
        //
        //     Scanner sc = new Scanner(System.in);
        //     chips = sc.nextInt();
        //     raise(chips);
        // }
        //

        if (chips >= this.chips) {
            throw new IllegalArgumentException();
        } else {
            this.chips -= chips;
            this.currentBet = chips;
            this.equalBet = true;
            System.out.println("Updated number of chips:" + this.chips);
        }
    }

    public void fold() {
        System.out.printf("You have folded. Thus, you quit the game, %s\n\n", this.getName());
        this.isActive = false;
    }
}
