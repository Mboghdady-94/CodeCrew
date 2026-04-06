/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Coordinates the UNO game from start to finish.
 * @author mahmoudelboghdadi
 */
public class UNOGame extends Game {

    private DrawPile drawPile;           // the pile players draw cards from
    private DiscardPile discardPile;     // the pile played cards go onto
    private GameRuleValidator validator; // validates card plays and tracks active color
    private GameView view;               // handles all console output
    private TurnManager turnManager;     // manages turn order and action card effects
    private PlayerRegistration registration; // handles player registration
    private Scanner scanner;             // reads keyboard input from players
    private String winner;               // stores the winner's name when the game ends

    // creates a new UNOGame and initialises all shared components
    public UNOGame() {
        super("UNO");
        this.drawPile = new DrawPile();
        this.discardPile = new DiscardPile();
        this.scanner = new Scanner(System.in);
        this.winner = null;
        this.view = new GameView();
        this.registration = new PlayerRegistration(view);
    }

    // builds and shuffles the deck, deals 7 cards to each player,
    // and flips the first non-wild card onto the discard pile
    public void startGame() {
        drawPile.buildDeck();
        dealCards();

        // keep drawing until the first card is not a Wild card
        Card firstCard = drawPile.draw();
        while (firstCard instanceof WildCard) {
            drawPile.getCards().add(0, firstCard);
            firstCard = drawPile.draw();
        }

        discardPile.addCard(firstCard);
        view.showStartingCard(firstCard);

        validator = new GameRuleValidator(firstCard);

        // TurnManager is created here once the player list is finalised
        turnManager = new TurnManager(getPlayers(), drawPile, discardPile, view);
        turnManager.applyOpeningCardEffect(firstCard);
        
        view.showGameStart(getPlayers().size());
    }

    // deals 7 cards to every registered player
    private void dealCards() {
        for (Player p : getPlayers()) {
            UNOPlayer up = (UNOPlayer) p;
            for (int i = 0; i < 7; i++) {
                up.drawCard(drawPile.draw());
            }
        }
    }

    // main game loop — registers players, starts the game, then cycles turns
    // until a player empties their hand
    @Override
    public void play() {
        view.showWelcome();

        int numPlayers = collectPlayerCount();
        setPlayers(registration.register(numPlayers, scanner));
        startGame();

        int currentIndex = 0;

        while (true) {
            UNOPlayer current = (UNOPlayer) getPlayers().get(currentIndex);

            view.showTurnHeader(current.getName());
            view.showTopCard(discardPile.getTopCard(), validator.getActiveColor());
            view.showHand(current.getHand().getCards());

            ArrayList<Integer> playableIndexes = findPlayableCards(current);
            Card playedCard;

            if (playableIndexes.isEmpty()) {
                playedCard = handleNoPlayableCard(current);
            } else {
                playedCard = handleCardSelection(current, playableIndexes);
            }

            if (playedCard != null) {
                // if a Wild card was played, ask the player to choose a color
                if (playedCard instanceof WildCard) {
                    String chosen = current.chooseColor(scanner, view);
                    ((WildCard) playedCard).setChosenColor(chosen);
                    view.showChosenColor(current.getName(), chosen);
                }

                discardPile.addCard(playedCard);
                validator.setTopCard(playedCard);
                view.showPlayedCard(current.getName(), playedCard);

                // check if the player has emptied their hand
                if (current.getHand().isEmpty()) {
                    winner = current.getName();
                    declareWinner();
                    break;
                }

                currentIndex = turnManager.applyCardEffect(playedCard, currentIndex);
            }

            currentIndex = turnManager.nextIndex(currentIndex);
        }

        scanner.close();
    }

    // asks how many players will play and validates the input is between 2 and 4
    private int collectPlayerCount() {
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 4) {
            view.promptPlayerCount();
            try {
                numPlayers = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                view.showInvalidNumberError();
            }
        }
        return numPlayers;
    }

    // returns a list of indexes of all cards in the player's hand that are currently playable
    private ArrayList<Integer> findPlayableCards(UNOPlayer current) {
        ArrayList<Integer> playableIndexes = new ArrayList<>();
        ArrayList<Card> hand = current.getHand().getCards();
        for (int i = 0; i < hand.size(); i++) {
            if (validator.isCardPlayable(hand.get(i))) {
                playableIndexes.add(i);
            }
        }
        return playableIndexes;
    }

    // handles the case where a player has no playable card
    // draws one card — plays it automatically if playable, otherwise passes the turn
    private Card handleNoPlayableCard(UNOPlayer current) {
        view.showNoPlayableCard();
        Card drawn = turnManager.safeDrawCard();
        if (drawn == null) {
            view.showNoDraw();
            return null;
        }
        current.drawCard(drawn);
        view.showDrawnCard(drawn);
        if (validator.isCardPlayable(drawn)) {
            view.showDrawnCardPlayable();
            return current.playCard(current.getHand().getCards().size() - 1);
        }
        view.showDrawnCardNotPlayable();
        return null;
    }

    // prompts the player to choose a card from their playable options
    // keeps prompting until a valid index is entered
    private Card handleCardSelection(UNOPlayer current, ArrayList<Integer> playableIndexes) {
        int choice = -1;
        while (true) {
            view.promptCardChoice(playableIndexes);
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (playableIndexes.contains(choice)) {
                    break;
                }
                view.showNotPlayableError(playableIndexes);
            } catch (NumberFormatException e) {
                view.showInvalidCardError();
            }
        }
        return current.playCard(choice);
    }

    // announces the winner of the game
    @Override
    public void declareWinner() {
        view.showWinner(winner);
    }
}
