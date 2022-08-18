/* (C)2021 */
/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author
 * @version v1.0 4/7/2022
 */
package edu.gonzaga;

/*
* Class for a Die used in Yahtzee.
*/

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/** Class to store the state of a single die. */
public class Die implements Comparable<Die> {
    private Integer sideUp; // Current die 'value' in range 1..numSides
    private Integer numSides; // Sides on the die (should be 1...INF integer)
    private static final Integer DEFAULT_NUM_SIDES = 6;
    private static final Integer DEFAULT_SIDE_UP = 1;

    private boolean locked;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public Die() {
        locked = false;
        this.numSides = DEFAULT_NUM_SIDES;
        this.sideUp = DEFAULT_SIDE_UP;
    }

    public Die(Integer numSides) {
        locked = false;
        this.numSides = numSides;
        this.sideUp = DEFAULT_SIDE_UP;
    }

    public Die(Integer numSides, Integer startingSide) {
        locked = false;
        this.numSides = numSides;
        this.sideUp = startingSide;
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
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description locks the die so that it can no longer be rolled
     * @pre unfired propertyChange event
     * @post fired propertyChange event for view
     **/
    public void lock() {
        boolean prev = locked;
        locked = true;
        pcs.firePropertyChange("lock", prev, true);
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description unlocks the die so that it can be rolled again
     * @pre unfired propertyChange event
     * @post fired propertyChange event for view
     **/
    public void unlock() {
        boolean prev = locked;
        locked = false;
        pcs.firePropertyChange("lock", prev, false);
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description checks if this object (die) is locked and returns status
     * @return whether this die object is locked
     **/
    public boolean isLocked() {
        return locked;
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description rolls die object if it is not locked
     * @pre previously set die face value
     * @post newly set die face values
     **/
    public void roll() {
        if (!locked) {
            Random rand = new Random();
            setNewValue(rand.nextInt(this.numSides) + 1);
        }
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description sets this objects sideUp value to a passed value
     * @param newValue the new value to be this die object's side up
     * @pre previously set side up face value
     * @post newly set side up face value
     **/
    private void setNewValue(int newValue) {
        int oldValue = sideUp;
        sideUp = newValue;
        pcs.firePropertyChange("sideUp", oldValue, newValue);
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description returns current die value (the side that's up).
     * @return the current sideUp as an Integer
     **/
    public Integer getSideUp() {
        return this.sideUp;
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description returns the number of sides this die object has
     * @return the current number of sides this object has
     **/
    public Integer getNumSides() {
        return this.numSides;
    }

    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description Provides the ability to convert the Die object into a string.
     *              representation, both with {@link java.lang.Integer#toString()}
     *              and with {@link java.io.PrintStream#println(x)}
     * @return String representation of Die
     **/
    @Override
    public String toString() {
        return this.sideUp.toString();
    }

    @Override
    /**
     * @Author Tyler C
     * @Date created: 4/20/22;
     *       Date last modified: 4/20/22
     * @Description makes two dice comparable using <, ==, >, etc. based on sideUp
     *              values. Based upon {@link Comparable#compareTo(Object)}
     * @param otherDie Another Die object being compared to
     * @return An Integer (-1 : less than, 0 : equal to, 1 : greater than)
     **/
    public int compareTo(Die otherDie) {
        return this.sideUp.compareTo(otherDie.sideUp);
    }
}
