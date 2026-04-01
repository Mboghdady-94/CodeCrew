/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 *
 * @author mahmoudelboghdadi
 */
public class UNOPlayer extends Player {
 
    private Hand hand;
    private String password;
 
    /**
     * Creates a UNOPlayer with the given name and password.
     *
     * @param name     the player's name
     * @param password the player's validated password
     */
    public UNOPlayer(String name, String password) {
        super(name);
        this.hand = new Hand();
        this.password = password;
    }
 
    /**
     * @return the player's hand
     */
    public Hand getHand() {
        return hand;
    }
 
    /**
     * Adds a card to the player's hand.
     *
     * @param card the card to add
     */
    public void drawCard(Card card) {
        hand.addCard(card);
    }
 
    /**
     * Plays a card from the player's hand at the given index.
     *
     * @param index the index of the card to play
     * @return the card that was played
     */
    public Card playCard(int index) {
        return hand.removeCard(index);
    }
 
    /**
     * Prompts the player to choose a color when playing a Wild card.
     *
     * @param scanner the Scanner for keyboard input
     * @return the chosen color string
     */
    public String chooseColor(Scanner scanner) {
        String[] validColors = {"Red", "Green", "Blue", "Yellow"};
        System.out.println(getName() + ", choose a color (Red, Green, Blue, Yellow):");
        while (true) {
            String input = scanner.nextLine().trim();
            for (String color : validColors) {
                if (color.equalsIgnoreCase(input)) {
                    return color;
                }
            }
            System.out.println("Invalid color. Please enter Red, Green, Blue, or Yellow:");
        }
    }
 
    /**
     * Not used directly in UNO; turn logic is handled by UNOGame.
     */
    @Override
    public void play() {
        // Turn logic is delegated to UNOGame
    }
}
