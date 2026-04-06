/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * This class represents player's hand of cards in UNO
 *
 * @author YuvrajSinghSahi
 */
public class Hand {

    private ArrayList<Card> cards;   // the cards currently in the player's hand

    // creates a new empty Hand
    public Hand() {
        this.cards = new ArrayList<>();
    }
    // adds a card to this hand

    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * removes and returns the card at the given index from this hand
     * index is the position of the card to remove returns null if the index is
     * out of range
     */
    public Card removeCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.remove(index);
    }
    // returns the list of cards currently in this hand

    public ArrayList<Card> getCards() {
        return cards;
    }

    // returns the number of cards currently in this hand
    public int getSize() {
        return cards.size();
    }

    //return true if the hand has no cards left
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
