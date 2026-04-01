/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class ActionCard extends Card {
 
    private String symbol;
    private CardType type;
    private String color;
 
    /**
     * Creates a new ActionCard.
     *
     * @param symbol the display symbol (e.g., "Skip", "Reverse", "Draw Two")
     * @param type   the CardType enum value
     * @param color  the card color
     */
    public ActionCard(String symbol, CardType type, String color) {
        this.symbol = symbol;
        this.type = type;
        this.color = color;
    }
 
    /**
     * @return the symbol label for this card
     */
    public String getSymbol() {
        return symbol;
    }
 
    /**
     * @return the CardType of this action card
     */
    public CardType getType() {
        return type;
    }
 
    /**
     * @return the color of this card
     */
    public String getColor() {
        return color;
    }
 
    @Override
    public String toString() {
        return color + " " + symbol;
    }
}
