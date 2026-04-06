/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * Abstract base class for all UNO-specific cards
 * Shared the card colors with all UNO cards and holds
 * the standard UNO deck size and valid color constants.
 * @author MarwahAlAnssari
 */
public abstract class UNOCards extends Card {
 
    // the total number of cards in a standard UNO deck
    public static final int DECK_SIZE = 108;
 
    // the four valid colors in a standard UNO deck
    public static final String[] VALID_COLORS = {"Red", "Blue", "Green", "Yellow"};
 
    // returns the color of this card
    // all UNO card types must implement this
    public abstract String getColor();
 
    // overrides toString() from Card 
    @Override
    public abstract String toString();
}