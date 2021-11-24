package poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private String name;
    boolean hasButton;
    private boolean isActive;
    private List<Card> cards;
    
    Player(){
        this.name = "Anonymous";
        this.hasButton = false;
        this.isActive = false;
        cards = new ArrayList<Card>();
    }
    
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
    
    public List<Card> getCards() {
        return cards;
    }
    
    public void setCards(Card card) {
        this.cards.add(card);
    }
}
