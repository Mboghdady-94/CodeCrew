/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Handles all console output for the UNO game Used for displaying information
 * to the players
 *
 * @author MahmoudElboghdady
 */
public class GameView {

    // displays the welcome banner at the start of the game
    public void showWelcome() {
        System.out.println("\n=== Welcome to UNO! ===\n");
    }

    // prompts the user to enter how many players will play
    public void promptPlayerCount() {
        System.out.print("How many players? (2-4): ");
    }

    // displays an error when the player count input is not a valid number
    public void showInvalidNumberError() {
        System.out.println("Please enter a number.");
    }

    // prompts a specific player number to enter their name
    public void promptRegisterPlayer(int playerNumber) {
        System.out.println("Registering player " + playerNumber + ":");
        System.out.print("Enter name: ");
    }

    // prompts the player to enter a password
    public void promptPassword() {
        System.out.print("Enter password (more than 7 chars, must include a special character): ");
    }

    // displays an error when the entered password does not meet the rules
    public void showInvalidPasswordError() {
        System.out.println("Invalid password. Try again.");
    }

    // confirms a player has been successfully registered
    public void showRegistrationSuccess(String name) {
        System.out.println(name + " registered successfully.");
    }

    // displays all currently registered players
    public void showRegisteredPlayers(ArrayList<Player> players) {
        System.out.println("Registered players so far:");
        for (Player p : players) {
            System.out.println("  - " + p.getName());
        }
        System.out.println();
    }
    // displays the start of the game
    public void showGameStart(int numPlayers) {
        System.out.println("\n=== Game Starting ===");
        System.out.println("Players: " + numPlayers);
        System.out.println("Each player has been dealt 7 cards.\n");
    }

    // displays the starting card flipped onto the discard pile
    public void showStartingCard(Card card) {
        System.out.println("Starting card: " + card);
    }

    // displays the current player's turn header
    public void showTurnHeader(String playerName) {
        System.out.println("\n--- " + playerName + "'s turn ---");
    }

    // displays the current top card and active color
    public void showTopCard(Card topCard, String activeColor) {
        System.out.println("Top card: " + topCard);
        System.out.println("Active color: " + activeColor);
    }

    // displays the player's current hand of cards with index numbers
    public void showHand(ArrayList<Card> hand) {
        System.out.println("Your hand:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("  [" + i + "] " + hand.get(i));
        }
    }

    // prompts the player to enter the index of the card they want to play
    public void promptCardChoice(ArrayList<Integer> playableIndexes) {
        System.out.print("Enter the number of the card to play (playable cards: "
                + playableIndexes + "): ");
    }

    // displays an error when the chosen card index is not in the playable list
    public void showNotPlayableError(ArrayList<Integer> playableIndexes) {
        System.out.println("That card is not playable. Choose from: " + playableIndexes);
    }

    // displays an error when card input is not a valid number
    public void showInvalidCardError() {
        System.out.println("Please enter a valid number.");
    }

    // announces that the player has no playable card and must draw
    public void showNoPlayableCard() {
        System.out.println("No playable card. Drawing a card...");
    }

    // displays the card that was just drawn
    public void showDrawnCard(Card card) {
        System.out.println("Drew: " + card);
    }

    // announces that the drawn card is playable and will be played automatically
    public void showDrawnCardPlayable() {
        System.out.println("The drawn card is playable! Playing it automatically.");
    }

    // announces that the drawn card is not playable and the turn passes
    public void showDrawnCardNotPlayable() {
        System.out.println("Card is not playable. Turn Skiped.");
    }

    // announces that no cards are available to draw and the turn passes
    public void showNoDraw() {
        System.out.println("No cards available to draw. Turn passes.");
    }

    // prompts the player to choose a color after playing a Wild card
    public void promptColorChoice() {
        System.out.print("Choose a color (Red, Blue, Green, Yellow): ");
    }

    // displays an error when the chosen color is not one of the four valid colors
    public void showInvalidColorError() {
        System.out.println("Invalid color. Please try again.");
    }

    // announces the color a player chose after playing a Wild card
    public void showChosenColor(String playerName, String color) {
        System.out.println(playerName + " chose: " + color);
    }

    // announces the card that was just played
    public void showPlayedCard(String playerName, Card card) {
        System.out.println(playerName + " played: " + card);
    }

    // announces that the draw pile is empty and the discard pile is being reshuffled
    public void showReshuffle() {
        System.out.println("Draw pile is empty. Reshuffling discard pile...");
    }

    // announces a Skip card effect
    public void showSkipEffect() {
        System.out.println("Skip! Next player loses their turn.");
    }

    // announces a Reverse card effect
    public void showReverseEffect() {
        System.out.println("Reverse! Direction changed.");
    }

    // announces a Draw Two or Wild Draw Four effect
    public void showForceDrawEffect(String playerName, int numCards) {
        System.out.println(playerName + " must draw " + numCards + " cards and loses their turn!");
    }

    // announces the winner of the game
    public void showWinner(String playerName) {
        System.out.println("\n*** " + playerName + " wins the game! ***\n");
    }
}
