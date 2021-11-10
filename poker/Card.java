package poker;

public class Card {
    private boolean faceUp;
    private String suit;  // The suit of a card can be anyone of {"club", "diamond", "heart", "spade"}
    private String rank;  // The rank of a card can be anyone of {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"}
    
    // getter and setter methods for all the private attributes
    Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false;
    }
    
    public boolean isFaceUp() {
        return faceUp;
    }
    
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
    
    public void setSuit(String suit) {
        this.suit = suit;
    }
    
    public String getRank() {
        return rank;
    }
    
    // DEBUG
    @Override
    public String toString() {
        return "Card{" +
                "faceUp=" + faceUp +
                ", suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}
