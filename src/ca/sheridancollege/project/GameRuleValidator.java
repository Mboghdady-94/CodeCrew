/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author mahmoudelboghdadi
 */
public class GameRuleValidator {
 
    private Card topCard;
 
    /**
     * Creates a GameRuleValidator with an initial top card.
     *
     * @param topCard the starting top card on the discard pile
     */
    public GameRuleValidator(Card topCard) {
        this.topCard = topCard;
    }
 
    /**
     * Updates the reference to the current top card.
     *
     * @param topCard the new top card after a card is played
     */
    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }
 
    /**
     * Returns the current active color from the top card.
     * For WildCards this returns the chosen color; for others it returns their own color.
     *
     * @return the active color string
     */
    public String getActiveColor() {
        if (topCard instanceof WildCard) {
            return ((WildCard) topCard).getChosenColor();
        } else if (topCard instanceof NumberCard) {
            return ((NumberCard) topCard).getColor();
        } else if (topCard instanceof ActionCard) {
            return ((ActionCard) topCard).getColor();
        }
        return "";
    }
 
    /**
     * Checks whether a card can legally be played on top of the current top card.
     * A card is playable if it matches the active color, the number/symbol, or is a Wild.
     *
     * @param card the card the player wants to play
     * @return true if the card is playable
     */
    public boolean isCardPlayable(Card card) {
        // Wild cards can always be played
        if (card instanceof WildCard) {
            return true;
        }
 
        String activeColor = getActiveColor();
 
        if (card instanceof NumberCard) {
            NumberCard nc = (NumberCard) card;
            // Match by color
            if (nc.getColor().equals(activeColor)) {
                return true;
            }
            // Match by number against top card if top is also a number card
            if (topCard instanceof NumberCard) {
                return nc.getNumber() == ((NumberCard) topCard).getNumber();
            }
            return false;
        }
 
        if (card instanceof ActionCard) {
            ActionCard ac = (ActionCard) card;
            // Match by color
            if (ac.getColor().equals(activeColor)) {
                return true;
            }
            // Match by symbol (type) against top card if top is also an action card
            if (topCard instanceof ActionCard) {
                return ac.getType() == ((ActionCard) topCard).getType();
            }
            return false;
        }
 
        return false;
    }
 
    /**
     * Returns the CardType of the current top card.
     * Used by UNOGame to apply effects after a card is played.
     *
     * @return the CardType of the top card
     */
    public String getCardType() {
        if (topCard instanceof WildCard) {
            return ((WildCard) topCard).isDrawFour() ? "WildDrawFour" : "Wild";
        } else if (topCard instanceof ActionCard) {
            return ((ActionCard) topCard).getType().name();
        } else if (topCard instanceof NumberCard) {
            return "Number";
        }
        return "Unknown";
    }
 
    /**
     * @return true if the current top card is a Wild card of any kind
     */
    public boolean isWildCard() {
        return topCard instanceof WildCard;
    }
}

