/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class NumberCard extends Card {
 
    private int number;
    private String color;
 
    /**
     * Creates a new NumberCard with the given number and color.
     *
     * @param number the card number (0-9)
     * @param color  the card color (Red, Green, Blue, Yellow)
     */
    public NumberCard(int number, String color) {
        this.number = number;
        this.color = color;
    }
 
    /**
     * @return the card number
     */
    public int getNumber() {
        return number;
    }
 
    /**
     * @return the card color
     */
    public String getColor() {
        return color;
    }
 
    @Override
    public String toString() {
        return color + " " + number;
    }
}
