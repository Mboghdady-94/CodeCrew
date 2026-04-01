/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class WildCard extends Card {
 
    private boolean isDrawFour;
    // The active color is set when the player plays this card
    private String chosenColor;
 
    /**
     * Creates a WildCard.
     *
     * @param isDrawFour true if this is a Wild Draw Four, false for a plain Wild
     */
    public WildCard(boolean isDrawFour) {
        this.isDrawFour = isDrawFour;
        this.chosenColor = "";
    }
 
    /**
     * @return true if this card is a Wild Draw Four
     */
    public boolean isDrawFour() {
        return isDrawFour;
    }
 
    /**
     * @return the color chosen by the player after playing this card
     */
    public String getChosenColor() {
        return chosenColor;
    }
 
    /**
     * Sets the active color after this wild card is played.
     *
     * @param color the color chosen by the player
     */
    public void setChosenColor(String color) {
        this.chosenColor = color;
    }
 
    @Override
    public String toString() {
        if (isDrawFour) {
            return "Wild Draw Four";
        }
        return "Wild";
    }
}

