/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class Hand extends GroupOfCards {
 
    /**
     * Creates an empty hand.
     */
    public Hand() {
        super(0); // hand size is dynamic
    }
 
    /**
     * Removes and returns the card at the given index.
     *
     * @param index the position of the card to remove
     * @return the removed card
     */
    public Card removeCard(int index) {
        return getCards().remove(index);
    }
 
    /**
     * @return true if the hand has no cards left
     */
    public boolean isEmpty() {
        return getCards().isEmpty();
    }
}
