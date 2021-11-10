package poker;

public class Player {
    
    private String name;
    boolean hasButton;
    private boolean isActive;
    private Card[] cards;
    
    // getter and setter methods for all the private attributes
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public Card[] getCards() {
        return cards;
    }
    
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}
