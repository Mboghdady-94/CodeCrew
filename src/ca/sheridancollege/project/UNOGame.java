/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mahmoudelboghdadi
 */
public class UNOGame extends Game {

    private int direction;          // 1 = clockwise, -1 = counter-clockwise
    private UNOPlayer currentTurn;
    private DrawPile drawPile;
    private DiscardPile discardPile;
    private GameRuleValidator validator;
    private Scanner scanner;
    private String winner;

    public UNOGame() {
        super("UNO");
        this.direction = 1;
        this.drawPile = new DrawPile();
        this.discardPile = new DiscardPile();
        this.scanner = new Scanner(System.in);
        this.winner = null;
    }

    /**
     * Registers 2 to 4 players with name and password validation.
     *
     * @param players the array of UNOPlayers to register
     */
    public void registerPlayers(UNOPlayer[] players) {
        UNOPasswordValidator passwordValidator = new UNOPasswordValidator();
        ArrayList<Player> registered = new ArrayList<>();

        for (int i = 0; i < players.length; i++) {
            System.out.println("Registering player " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = scanner.nextLine().trim();

            String password = "";
            while (true) {
                System.out.print("Enter password (more than 7 chars, must include a special character): ");
                password = scanner.nextLine().trim();
                if (passwordValidator.validate(password)) {
                    break;
                }
                System.out.println("Invalid password. Try again.");
            }

            registered.add(new UNOPlayer(name, password));
            System.out.println(name + " registered successfully.");
            System.out.println("Registered players so far:");
            for (Player p : registered) {
                System.out.println("  - " + p.getName());
            }
            System.out.println();
        }

        setPlayers(registered);
    }

    /**
     * Starts the game: builds deck, deals 7 cards to each player,
     * flips the first card onto the discard pile.
     */
    public void startGame() {
        drawPile.buildDeck();

        // Deal 7 cards to each player
        for (Player p : getPlayers()) {
            UNOPlayer up = (UNOPlayer) p;
            for (int i = 0; i < 7; i++) {
                up.drawCard(drawPile.draw());
            }
        }

        // Flip the first card onto the discard pile (re-draw if it's a wild)
        Card firstCard = drawPile.draw();
        while (firstCard instanceof WildCard) {
            drawPile.getCards().add(0, firstCard); // put it back at the bottom
            firstCard = drawPile.draw();
        }
        discardPile.addCard(firstCard);
        System.out.println("Starting card: " + firstCard);

        validator = new GameRuleValidator(firstCard);

        // If first card is an action card, apply its effect before turn 1
        applyCardEffect(firstCard, null);
    }

    /**
     * Main game loop. Runs until a player empties their hand.
     */
    @Override
    public void play() {
        System.out.println("\n=== Welcome to UNO! ===\n");

        // Collect player count
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.print("How many players? (2-4): ");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }

        registerPlayers(new UNOPlayer[numPlayers]);
        startGame();

        int currentIndex = 0;

        while (true) {
            UNOPlayer current = (UNOPlayer) getPlayers().get(currentIndex);
            currentTurn = current;

            System.out.println("\n--- " + current.getName() + "'s turn ---");
            System.out.println("Top card: " + discardPile.getTopCard());
            System.out.println("Active color: " + validator.getActiveColor());
            System.out.println("Your hand:");

            ArrayList<Card> hand = current.getHand().getCards();
            for (int i = 0; i < hand.size(); i++) {
                System.out.println("  [" + i + "] " + hand.get(i));
            }

            // Find playable cards
            ArrayList<Integer> playableIndexes = new ArrayList<>();
            for (int i = 0; i < hand.size(); i++) {
                if (validator.isCardPlayable(hand.get(i))) {
                    playableIndexes.add(i);
                }
            }

            Card playedCard = null;

            if (playableIndexes.isEmpty()) {
                // No playable card: draw one
                System.out.println("No playable card. Drawing a card...");
                Card drawn = safeDrawCard();
                if (drawn != null) {
                    current.drawCard(drawn);
                    System.out.println("Drew: " + drawn);
                    if (validator.isCardPlayable(drawn)) {
                        System.out.println("The drawn card is playable! Playing it automatically.");
                        playedCard = current.playCard(current.getHand().getCards().size() - 1);
                    } else {
                        System.out.println("Card is not playable. Turn passes.");
                    }
                } else {
                    System.out.println("No cards available to draw. Turn passes.");
                }
            } else {
                // Let the player choose a card
                int choice = -1;
                while (true) {
                    System.out.print("Enter the number of the card to play (playable cards: " + playableIndexes + "): ");
                    try {
                        choice = Integer.parseInt(scanner.nextLine().trim());
                        if (playableIndexes.contains(choice)) {
                            break;
                        }
                        System.out.println("That card is not playable. Choose from: " + playableIndexes);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }
                playedCard = current.playCard(choice);
            }

            // If a card was played, process it
            if (playedCard != null) {
                // Handle wild card color selection
                if (playedCard instanceof WildCard) {
                    String chosen = current.chooseColor(scanner);
                    ((WildCard) playedCard).setChosenColor(chosen);
                    System.out.println(current.getName() + " chose: " + chosen);
                }

                discardPile.addCard(playedCard);
                validator.setTopCard(playedCard);
                System.out.println(current.getName() + " played: " + playedCard);

                // Check win condition
                if (current.getHand().isEmpty()) {
                    winner = current.getName();
                    declareWinner();
                    break;
                }

                // Apply action card effects; they may shift the next index
                currentIndex = applyCardEffect(playedCard, currentIndex);
            }

            // Advance to next player
            currentIndex = nextIndex(currentIndex);
        }

        scanner.close();
    }

    /**
     * Draws a card safely, reshuffling the discard pile into the draw pile if needed.
     *
     * @return a Card, or null if neither pile has cards
     */
    private Card safeDrawCard() {
        if (drawPile.isEmpty()) {
            System.out.println("Draw pile is empty. Reshuffling discard pile...");
            ArrayList<Card> recycled = discardPile.refillDraw();
            if (recycled.isEmpty()) {
                return null;
            }
            drawPile.refillDraw(recycled);
        }
        return drawPile.draw();
    }

    /**
     * Applies the effect of an action card and returns the (possibly adjusted) current index.
     *
     * @param card         the card that was just played
     * @param currentIndex the current player index (null if called for the starting card)
     * @return the updated current index after applying effects
     */
    private int applyCardEffect(Card card, Integer currentIndex) {
        if (currentIndex == null) {
            // Called for the opening card; direction may be reversed but turns haven't started
            if (card instanceof ActionCard) {
                ActionCard ac = (ActionCard) card;
                if (ac.getType() == CardType.Reverse) {
                    direction *= -1;
                }
            }
            return 0;
        }

        if (card instanceof ActionCard) {
            ActionCard ac = (ActionCard) card;

            if (ac.getType() == CardType.Skip) {
                System.out.println("Skip! Next player loses their turn.");
                currentIndex = nextIndex(currentIndex); // skip one extra

            } else if (ac.getType() == CardType.Reverse) {
                direction *= -1;
                System.out.println("Reverse! Direction changed.");
                // With 2 players, Reverse acts like Skip
                if (getPlayers().size() == 2) {
                    currentIndex = nextIndex(currentIndex);
                }

            } else if (ac.getType() == CardType.DrawTwo) {
                int nextIdx = nextIndex(currentIndex);
                UNOPlayer next = (UNOPlayer) getPlayers().get(nextIdx);
                System.out.println(next.getName() + " must draw 2 cards and loses their turn!");
                for (int i = 0; i < 2; i++) {
                    Card drawn = safeDrawCard();
                    if (drawn != null) next.drawCard(drawn);
                }
                currentIndex = nextIdx; // the penalized player becomes current so advance skips them
            }

        } else if (card instanceof WildCard) {
            WildCard wc = (WildCard) card;
            if (wc.isDrawFour()) {
                int nextIdx = nextIndex(currentIndex);
                UNOPlayer next = (UNOPlayer) getPlayers().get(nextIdx);
                System.out.println(next.getName() + " must draw 4 cards and loses their turn!");
                for (int i = 0; i < 4; i++) {
                    Card drawn = safeDrawCard();
                    if (drawn != null) next.drawCard(drawn);
                }
                currentIndex = nextIdx; // same as DrawTwo logic
            }
        }

        return currentIndex;
    }

    /**
     * Calculates the next player index based on current direction.
     *
     * @param currentIndex the current player's index
     * @return the next player's index
     */
    private int nextIndex(int currentIndex) {
        int size = getPlayers().size();
        return ((currentIndex + direction) % size + size) % size;
    }

    /**
     * Announces the winner.
     */
    @Override
    public void declareWinner() {
        System.out.println("  " + winner + " wins the game!");
    }
}
