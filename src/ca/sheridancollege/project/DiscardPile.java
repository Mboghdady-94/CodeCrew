/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author mahmoudelboghdadi
 */
public class DiscardPile extends GroupOfCards {
 
    /**
     * Creates an empty DiscardPile.
     */
    public DiscardPile() {
        super(0);
    }
 
    /**
     * Returns the top card of the discard pile without removing it.
     *
     * @return the top card, or null if the pile is empty
     */
    public Card getTopCard() {
        if (getCards().isEmpty()) {
            return null;
        }
        return getCards().get(getCards().size() - 1);
    }
 
    /**
     * Adds a card on top of the discard pile.
     *
     * @param c the card to place on top
     */
    public void addCard(Card c) {
        getCards().add(c);
    }
 
    /**
     * Removes and returns all cards except the top card.
     * Used to refill the draw pile when it runs out.
     *
     * @return list of cards to recycle into the draw pile
     */
    public ArrayList<Card> refillDraw() {
        ArrayList<Card> recycled = new ArrayList<>();
        // Keep only the top card in the discard pile
        while (getCards().size() > 1) {
            recycled.add(getCards().remove(0));
        }
        return recycled;
    }
}
