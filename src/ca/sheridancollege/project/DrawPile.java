/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The draw pile in UNO
 * @author mahmoudelboghdadi
 * @contributer ajayprashad
 */
public class DrawPile extends GroupOfCards {

    // creates a new empty DrawPile
    public DrawPile() {
        super(UNOCards.DECK_SIZE);
    }
 
    /**
     * Builds the full 108-card UNO deck and shuffles it.
     * 1 zero card per color (4 total)
     * 2 of each 1-9 per color (72 total)
     * 2 Skip, 2 Reverse, 2 Draw Two per color (24 total)
     * 4 Wild, 4 Wild Draw Four (8 total)
     */
    public void buildDeck() {
        getCards().clear();
 
                for (String color : UNOCards.VALID_COLORS) {
            // One zero per color
             getCards().add(new NumberCard(0, color));
 
            // Two of each 1-9
            for (int num = 1; num <= 9; num++) {
                for (int i = 0; i < 2; i++) {
                    getCards().add(new NumberCard(num, color));
                }
            }
 
            // Two Skip, two Reverse, two Draw Two per color
          for (int i = 0; i < 2; i++) {
                getCards().add(new ActionCard(CardType.Skip, color));
                getCards().add(new ActionCard(CardType.Reverse, color));
                getCards().add(new ActionCard(CardType.DrawTwo, color));
            }
        }
        // Four Wild, four Wild Draw Four
        for (int i = 0; i < 4; i++) {
            getCards().add(new WildCard(false));// regular Wild
            getCards().add(new WildCard(true));  // Wild Draw Four
        }
 
        shuffle();
    }
 
    /**
     *  draws and removes the top card from the draw pile
     * returns null if the draw pile is empty
     */
    public Card draw() {
        if (getCards().isEmpty()) {
            return null;
        }
        return getCards().remove(0);
    }
 
    /**
     * refills the draw pile using recycled cards from the discard pile (except the top card).
     *  shuffles the recycled cards before use.
     */
    public void refillDraw(ArrayList<Card> recycledCards) {
        getCards().addAll(recycledCards);
        shuffle();
    }

   public boolean isEmpty() {
        return getCards().isEmpty();
    }

}
