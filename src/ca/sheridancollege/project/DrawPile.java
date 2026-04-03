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
public class DrawPile extends GroupOfCards {
 
    private static final String[] COLORS = {"Red", "Green", "Blue", "Yellow"};
 
    /**
     * Creates an empty DrawPile.
     */
    public DrawPile() {
        super(108);
    }
 
    /**
     * Builds the full 108-card UNO deck and shuffles it.
     * - 1 zero card per color (4 total)
     * - 2 of each 1-9 per color (72 total)
     * - 2 Skip, 2 Reverse, 2 Draw Two per color (24 total)
     * - 4 Wild, 4 Wild Draw Four (8 total)
     */
    public void buildDeck() {
        getCards().clear();
 
        for (String color : COLORS) {
            // One zero per color
            getCards().add(new NumberCard(0, color));
 
            // Two of each 1-9
            for (int num = 1; num <= 9; num++) {
                getCards().add(new NumberCard(num, color));
                getCards().add(new NumberCard(num, color));
            }
 
            // Two Skip, two Reverse, two Draw Two per color
            getCards().add(new ActionCard(CardType.Skip, color));
            getCards().add(new ActionCard(CardType.Skip, color));
            getCards().add(new ActionCard(CardType.Reverse, color));
            getCards().add(new ActionCard(CardType.Reverse, color));
            getCards().add(new ActionCard(CardType.DrawTwo, color));
            getCards().add(new ActionCard(CardType.DrawTwo, color));
        }
 
        // Four Wild, four Wild Draw Four
        for (int i = 0; i < 4; i++) {
            getCards().add(new WildCard(false));
            getCards().add(new WildCard(true));
        }
 
        shuffle();
    }
 
    /**
     * Draws the top card from the draw pile.
     *
     * @return the top card, or null if the pile is empty
     */
    public Card draw() {
        if (isEmpty()) {
            return null;
        }
        return getCards().remove(getCards().size() - 1);
    }
 
    /**
     * Refills the draw pile from the discard pile cards (except the top card).
     * The returned list should remain as the new discard pile (just the top card).
     *
     * @param discardedCards the cards from the discard pile to recycle
     */
    public void refillDraw(ArrayList<Card> discardedCards) {
        getCards().addAll(discardedCards);
        shuffle();
    }
 
    /**
     * @return true if there are no cards left in the draw pile
     */
    public boolean isEmpty() {
        return getCards().isEmpty();
    }
}
