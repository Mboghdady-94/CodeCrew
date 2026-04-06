/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 *Manages turn order and applies action card effects during a UNO game
 * @author MahmoudElboghdady
 */
public class TurnManager {

    private int direction;             // 1 = clockwise, -1 = counter-clockwise
    private ArrayList<Player> players; // reference to the registered players
    private DrawPile drawPile;         // used to draw penalty cards
    private DiscardPile discardPile;   // used to reshuffle when the draw pile is empty
    private GameView view;             // used to display turn effects

    // creates a new TurnManager with references to the shared game components
    public TurnManager(ArrayList<Player> players, DrawPile drawPile,
                       DiscardPile discardPile, GameView view) {
        this.direction = 1;
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.view = view;
    }

    // calculates the next player index based on the current direction
    // handles wrap-around correctly in both clockwise and counter-clockwise directions
    public int nextIndex(int currentIndex) {
        int size = players.size();
        return ((currentIndex + direction) % size + size) % size;
    }

    // applies the effect of the opening card before the first turn begins
    // only a Reverse card changes anything at this stage
    public void applyOpeningCardEffect(Card card) {
        if (card instanceof ActionCard) {
            if (((ActionCard) card).getType() == CardType.Reverse) {
                direction *= -1;
            }
        }
    }

    // applies the effect of an action card after it is played
    // returns the updated current index — some effects skip the next player
    public int applyCardEffect(Card card, int currentIndex) {
        if (card instanceof ActionCard) {
            ActionCard ac = (ActionCard) card;

            if (ac.getType() == CardType.Skip) {
                view.showSkipEffect();
                currentIndex = nextIndex(currentIndex);

            } else if (ac.getType() == CardType.Reverse) {
                direction *= -1;
                view.showReverseEffect();
                // with 2 players, Reverse acts the same as Skip
                if (players.size() == 2) {
                    currentIndex = nextIndex(currentIndex);
                }

            } else if (ac.getType() == CardType.DrawTwo) {
                currentIndex = forceDrawCards(currentIndex, 2);
            }

        } else if (card instanceof WildCard) {
            if (((WildCard) card).isDrawFour()) {
                currentIndex = forceDrawCards(currentIndex, 4);
            }
        }

        return currentIndex;
    }

    // forces the next player to draw a given number of cards and lose their turn
    // used by both DrawTwo and WildDrawFour to avoid duplicated logic
    private int forceDrawCards(int currentIndex, int numCards) {
        int nextIdx = nextIndex(currentIndex);
        UNOPlayer next = (UNOPlayer) players.get(nextIdx);
        view.showForceDrawEffect(next.getName(), numCards);
        for (int i = 0; i < numCards; i++) {
            Card drawn = safeDrawCard();
            if (drawn != null) {
                next.drawCard(drawn);
            }
        }
        return nextIdx;
    }

    // draws a card safely — reshuffles the discard pile into the draw pile if needed
    // returns null if both piles are empty
    public Card safeDrawCard() {
        if (drawPile.isEmpty()) {
            view.showReshuffle();
            ArrayList<Card> recycled = discardPile.refillDraw();
            if (recycled.isEmpty()) {
                return null;
            }
            drawPile.refillDraw(recycled);
        }
        return drawPile.draw();
    }
}