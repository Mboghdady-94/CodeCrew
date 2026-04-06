/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/** 
 * The discard pile in UNO
 * @author VaqasAmer
 */
public class DiscardPile extends GroupOfCards {
    
     // creates a new empty DiscardPile with a capacity matching the UNO deck size
    public DiscardPile() {
        super(UNOCards.DECK_SIZE);
    }
    
     // adds a played card to the top of the discard pile
    public void addCard(Card card) {
        getCards().add(card);
    }
    
    /**
     * returns the top card of the discard pile without removing it
     * returns null if the discard pile is empty
     */
    public Card getTopCard() {
        if (getCards().isEmpty()) {
            return null;
        }
        return getCards().get(getCards().size() - 1);
    }

    /**
     * removes and returns all cards except the top card for recycling into the draw pile
     * the top card stays so the game state remains valid
     */
        public ArrayList<Card> refillDraw() {
        ArrayList<Card> recycled = new ArrayList<>();
        // Keeps only the top card in the discard pile
        while (getCards().size() > 1) {
            recycled.add(getCards().remove(0));
        }
        return recycled;
    }
}
