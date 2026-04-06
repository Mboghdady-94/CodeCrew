/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * This class represents a Wild UNO card (Wild or Wild Draw Four). The card type is
 * immutable once created, but the chosen color is set when the player plays the
 * card.
 *
 * @author MarwahAlAnssari
 */
public class WildCard extends UNOCards {

    private final boolean isDrawFour;//true if it's a Wild Draw Four, false if regular Wild, immutable
    // The color of the Wild card is chosen by the player after playing this card
    private String chosenColor;

    // Creates a new WildCard
    // isDrawFour is true for Wild Draw Four, false for a regular Wild
    public WildCard(boolean isDrawFour) {
        this.isDrawFour = isDrawFour;
        this.chosenColor = "";// no color chosen yet until the card is played
    }

    // returns true if card is a Wild Draw Four, false if it is a regular Wild
    public boolean isDrawFour() {
        return isDrawFour;
    }

    // returns the color chosen by the player after playing this card
    @Override
    public String getColor() {
        return chosenColor;
    }

    // This method sets the color of card after the wild card is played
    public void setChosenColor(String color) {
        this.chosenColor = color;
    }

    // overrides toString() in the Card class
    // returns "Wild Draw Four" or "Wild" depending on the card type 
    @Override
    public String toString() {
        if (isDrawFour) {
            return "Wild Draw Four";
        }
        return "Wild";
    }

}
