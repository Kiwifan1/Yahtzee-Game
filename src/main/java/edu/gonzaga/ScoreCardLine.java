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

public class ScorecardLine {

    private String identifier;
    private String title;

    private boolean scored;
    private int value;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public ScorecardLine(String title) {
        this(title, "");
    }

    public ScorecardLine(String title, String identifier) {
        this.identifier = identifier;
        this.value = 0;
        this.scored = false;
        this.title = title;
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
     * @return returns the string identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return true if this scoreline has a scored value.
     */
    public boolean isScored() {
        return scored;
    }

    /**
     * If the line has already been scored, throw an exception. Otherwise, set the
     * scored variable to true and fire a property change event
     * 
     * @exception IllegalStateException()
     */
    public void score() {
        if (scored) {
            throw new IllegalStateException("Line already scored.");
        }
        scored = true;
        pcs.firePropertyChange("scored", -1, value);
    }

    /**
     * @return the score value of this scoreline.
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the displayed title of this scoreline
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param value sets a new score value for this scoreline.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * If the new {@link ScorecardLine} value is different than the old value, then
     * fire a property change event.
     * 
     * @param value The name of the property.
     */
    public void setValueWithEvent(int value) {
        int oldValue = this.value;
        this.setValue(value);
        pcs.firePropertyChange("value", oldValue, value);
    }
}
