package poker;

public class Card implements Comparable<Card>{
    private boolean faceUp;
    private String suit;  // The suit of a card can be anyone of {"club", "diamond", "heart", "spade"}
    private String rank;  // The rank of a card can be anyone of {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"}
    private int parsedRank;

    // getter and setter methods for all the private attributes
    Card(String suit, String rank){
        this.suit = suit;
        this.rank = rank;
        this.parsedRank = parseRank(rank);
        this.faceUp = false;
    }

    public int parseRank(String rank){
        int r;
        if(rank.equals("A"))
            r = 14;
        else if(rank.equals("K"))
            r = 13;
        else if(rank.equals("Q"))
            r = 12;
        else if(rank.equals("J"))
            r = 11;
        else
            r = Integer.parseInt(rank);
        return r;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    // Print a card by overriding the toString() method in java.lang
    @Override
    public String toString() {
        return "Card{" +
                "faceUp=" + faceUp +
                ", suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    @Override
    public int compareTo(Card card) {
        if(this.parsedRank == card.parsedRank)
            return 0;
        else if(this.parsedRank > card.parsedRank)
            return 1;
        else
            return -1;
    }
}
