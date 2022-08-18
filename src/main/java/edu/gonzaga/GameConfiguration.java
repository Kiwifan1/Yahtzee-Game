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

/**
 * Represents the game configuration values.
 */
public class GameConfiguration {
    private int numDieSides;
    private int handSize;
    private int numTurnsPerHand;

    public GameConfiguration() {
        this(6, 5, 3);
    }

    public GameConfiguration(int numDieSides, int handSize, int numTurnsPerHand) {
        this.numDieSides = numDieSides;
        this.handSize = handSize;
        this.numTurnsPerHand = numTurnsPerHand;
    }

    /**
     * The number of sides in the configuration.
     * 
     * @return number of die sides.
     */
    public int getNumDieSides() {
        return numDieSides;
    }

    /**
     * The hand side in the configuration
     * 
     * @return size of hand.
     */
    public int getHandSize() {
        return handSize;
    }

    /**
     * The number of turns per hand in the configuration.
     * 
     * @return number of turns per hand.
     */
    public int getNumTurnsPerHand() {
        return numTurnsPerHand;
    }

    /**
     * Returns a string representation of the configuration.
     * 
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "GameConfiguration{" +
                "numDieSides=" + numDieSides +
                ", handSize=" + handSize +
                ", numTurnsPerHand=" + numTurnsPerHand +
                '}';
    }
}
