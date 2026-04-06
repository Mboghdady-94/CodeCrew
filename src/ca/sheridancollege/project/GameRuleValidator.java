/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 * Validates card plays and tracks the active color during a UNO game
 *
 * @author VaqasAmer
 */
public class GameRuleValidator {

    private Card topCard;
    private String activeColor;

    //Creates a GameRuleValidator with an initial top card.
    public GameRuleValidator(Card topCard) {
        this.topCard = topCard;
        this.activeColor = getCardColor(topCard);
    }
    // returns the currently active color

    public String getActiveColor() {
        return activeColor;
    }

    /**
     * Updates the reference to the current top card.
     *
     * @param topCard the new top card after a card is played
     */
    public void setTopCard(Card topCard) {
        this.topCard = topCard;
        activeColor = getCardColor(topCard);
    }

    /**
     * Checks whether a card can be played on top of the current top card. A
     * card is playable if it matches the active color, the number/symbol, or is
     * a Wild.
     */
    public boolean isCardPlayable(Card card) {
        // Wild cards can always be played
        if (card instanceof WildCard) {
            return true;
        }

        // Match by color
        if (card instanceof UNOCards) {
            UNOCards unoCard = (UNOCards) card;
            if (unoCard.getColor().equalsIgnoreCase(activeColor)) {
                return true;
            }
        }
        // Match by number against top card if top is also a number card
        if (card instanceof NumberCard && topCard instanceof NumberCard) {
            return ((NumberCard) card).getNumber()
                    == ((NumberCard) topCard).getNumber();
        }

        // Match by type against top card if top is also an action card
        if (card instanceof ActionCard && topCard instanceof ActionCard) {
            return ((ActionCard) card).getType()
                    == ((ActionCard) topCard).getType();
        }

        return false;
    }

    /**
     * extracts the color from any UNOCard using the shared getColor() Wild
     * cards return the player's chosen color, which starts empty
     */
    private String getCardColor(Card card) {
        if (card instanceof UNOCards) {
            return ((UNOCards) card).getColor();
        }
        return "";
    }

    /**
     * Returns the CardType of the current top card. Used by UNOGame to apply
     * effects after a card is played.
     */
    public CardType getCardType() {
        if (topCard instanceof WildCard) {
            return ((WildCard) topCard).isDrawFour()
                    ? CardType.WildDrawFour
                    : CardType.Wild;
        } else if (topCard instanceof ActionCard) {
            return ((ActionCard) topCard).getType();
        } else if (topCard instanceof NumberCard) {
            return CardType.Number;
        }
        return null;
    }

}
