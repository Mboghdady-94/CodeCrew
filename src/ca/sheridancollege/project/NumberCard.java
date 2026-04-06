/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * This class represents  a numbered UNO card with a fixed color and number
 * @author MarwahAlAnssari
 */
public class NumberCard extends UNOCards{

    private final int number; // card number (0-9), immutable
    private final String color; // card color (Red, Green, Blue, Yellow), immutable

    // creates a new NumberCard with a fixed number and color
    public NumberCard(int number, String color) {
        this.number = number;
        this.color = color;
    }

    // returns the card number
    public int getNumber() {
        return number;
    }

    // returns the card color
    @Override
    public String getColor() {
        return color;
    }

    // overides the toString() in the Card class
    // and return a String representation of uno card e.g. Red 2
    @Override
    public String toString() {
        return color + " " + number;
    }
}
