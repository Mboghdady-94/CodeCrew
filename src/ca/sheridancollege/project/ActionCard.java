/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * This class represents an action UNO card (Skip, Reverse, Draw Two) with a fixed color
 * and type.
 * @author MarwahAlAnssari
 */
public class ActionCard extends Card {

    private final CardType type;//card type using the enum e.g. CardType.Skip, immutable
    private final String color;// card color (Red, Blue, Green, Yellow), immutable

    // Creates a new ActionCard with a fixed type and color
    public ActionCard(CardType type, String color) {
        this.type = type;
        this.color = color;
    }

    // returns the CardType of this action card
    public CardType getType() {
        return type;
    }

    // returns the color of this card
    public String getColor() {
        return color;
    }
    // overrides toString() in the Card class
    // returns a String representation of the UNO card e.g. "Red Skip"

    @Override
    public String toString() {
        return color + " " + type;
    }
}
