/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * This class represents a UNO player with a hand of cards and a registered
 * password. It manages the player's own cards and color selection
 *
 * @author YuvrajSinghSahi
 */
public class UNOPlayer extends Player {

    private final Hand hand;       // the cards this player is currently holding
    private final String password; // the player's registered password, immutable

    // creates a new UNOPlayer with the given name and password
    public UNOPlayer(String name, String password) {
        super(name);
        this.password = password;
        this.hand = new Hand();
    }

    // returns this player's hand
    public Hand getHand() {
        return hand;
    }

    // adds a drawn card to player's hand
    public void drawCard(Card card) {
        hand.addCard(card);
    }

    //plays a card from the player's hand at the given index.
    public Card playCard(int index) {
        return hand.removeCard(index);
    }

    /**
     * Prompts the player to choose a color when playing a Wild card. keeps
     * asking until a valid color is entered display is Assigned to GameView
     */
    public String chooseColor(Scanner scanner, GameView view) {
        while (true) {
            view.promptColorChoice();
            String input = scanner.nextLine().trim();
            for (String color : UNOCards.VALID_COLORS) {
                if (color.equalsIgnoreCase(input)) {
                    return color;
                }
            }
            view.showInvalidColorError();
        }
    }

    /**
     * turn logic is handled by UNOGame, not the player so its empty
     */
    @Override
    public void play() {
    }
    
}
