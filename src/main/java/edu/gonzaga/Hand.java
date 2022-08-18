/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author Tyler CH
 * @version v1.0 4/7/2022
 */
package edu.gonzaga;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/** Class to store the player's hand. List of dice. */
public class Hand {
    public int handSize;
    private List<Die> dice;

    private int maxRollsPerTurn;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Hand(int handSize, int dieSides, int maxRollsPerTurn) {
        this.handSize = handSize;
        this.maxRollsPerTurn = maxRollsPerTurn;
        dice = new ArrayList<>(this.handSize);
        for (int i = 0; i < this.handSize; i++) {
            dice.add(new Die(dieSides));
        }
    }

    public Hand(GameConfiguration config) {
        this(config.getHandSize(), config.getNumDieSides(), config.getNumTurnsPerHand());
    }

    /**
     * Registers a PropertyChangeListener to this class.
     * 
     * @param listener the listener to register.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Removes a PropertyChangeListener to this class.
     * 
     * @param listener the listener to remove.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /**
     * Retrieves the max number of allowed rolls per turn
     * 
     * @return the max number of rolls per turn.
     */
    public int maxRollsPerTurn() {
        return maxRollsPerTurn;
    }

    /**
     * Retrieves the size of the hand.
     *
     * @return The number of die in this hand.
     */
    public int getHandSize() {
        return handSize;
    }

    /**
     * Overrides the dice in a hand. Usually used for testing.
     *
     * @param dice the list of dice to override.
     */
    public void setHand(List<Die> dice) {
        this.dice = dice;
    }

    /**
     * Randomly rolls all the dice in the hand.
     */
    public void rollAll() {
        for (Die die : dice) {
            die.roll();
        }
    }

    /**
     * Unlocks all the die and updates ui.
     */
    public void unlockAllDie() {
        for (Die die : dice) {
            die.unlock();
        }
    }

    /**
     * This function unlocks all the dice, rolls them, and then fires a property
     * change event "newturn".
     */
    public void newTurn() {
        unlockAllDie();
        rollAll();
        pcs.firePropertyChange("newturn", 0, 1);
    }

    /**
     * This function returns a list of dice
     * 
     * @return The dice list.
     */
    public final List<Die> getDice() {
        return dice;
    }
}
