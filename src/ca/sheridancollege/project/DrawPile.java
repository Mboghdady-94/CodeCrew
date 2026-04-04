/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *
 * @author mahmoudelboghdadi
 * @contributer ajayprashad
 */
public class DrawPile extends GroupOfCards {
 
    private static final String[] COLORS = {"Red", "Green", "Blue", "Yellow"};
    private static final int DECK_SIZE = 108;

    /**
     * Creates an empty DrawPile.
     */
    public DrawPile() {
        super(DECK_SIZE);
    }
 
    /**
     * Builds the full 108-card UNO deck and shuffles it.
     * - 1 zero card per color (4 total)
     * - 2 of each 1-9 per color (72 total)
     * - 2 Skip, 2 Reverse, 2 Draw Two per color (24 total)
     * - 4 Wild, 4 Wild Draw Four (8 total)
     */
    public void buildDeck() {
        ArrayList<Card> cards = getCards();
        cards.clear();
 
        for (String color : COLORS) {
            // One zero per color
            addCard(new NumberCard(0, color));
 
            // Two of each 1-9
            for (int num = 1; num <= 9; num++) {
                for (int i = 0; i < 2; i++) {
                    addCard(new NumberCard(num, color));
                }
            }
 
            // Two Skip, two Reverse, two Draw Two per color
          for (int i = 0; i < 2; i++) {
                addCard(new ActionCard(CardType.Skip, color));
                addCard(new ActionCard(CardType.Reverse, color));
                addCard(new ActionCard(CardType.DrawTwo, color));
            }
        }
        // Four Wild, four Wild Draw Four
        for (int i = 0; i < 4; i++) {
            addCard(new WildCard(false));
            addCard(new WildCard(true));
        }
 
        shuffle();
    }
 
    /**
     * Draws the top card from the draw pile.
     * The top card is the last element in the list.

     * @return the top card, or null if the pile is empty
     */
    public Card draw() {
        if (isEmpty()) {
            return null;
        }
        ArrayList<Card> cards = getCards();
        return cards.remove(cards.size() - 1);
    }
 
    /**
     * Refills the draw pile from the discard pile cards (except the top card).
     * The returned list should remain as the new discard pile (just the top card).
     *
     * @param discardedCards the cards from the discard pile to recycle
     */
    public void refillDraw(ArrayList<Card> discardedCards) {
        for (Card card : discardedCards) {
            addCard(card);
        }
        shuffle();
    }
 
    /**
     * @return true if there are no cards left in the draw pile
     */
}
