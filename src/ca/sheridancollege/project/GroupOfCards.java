/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 * @author mahmoudelboghdadi
 * @contributer ajayprashad
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
    /**
     * Adds a card to the group.
     *
     * @param c the card to add
     */
    
    public void addCard(Card c){
        cards.add(c);
    }
    /**
     * Shuffles the cards randomly.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }
    /**
     * Checks if the group has no cards.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }
    
    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

}//end class
