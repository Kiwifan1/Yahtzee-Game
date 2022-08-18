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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/** Class to store the upper and lower sections of the scorecard. */
public abstract class Scorecard implements PropertyChangeListener {
    private ArrayList<ScorecardLine> lines;

    boolean isFull;

    private GameConfiguration configuration;

    private ScorecardLine totalLine;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    protected Scorecard() {
        lines = new ArrayList<>();
        isFull = false;
        totalLine = new ScorecardLine("TOTAL");
    }

    protected Scorecard(GameConfiguration configuration) {
        this();
        this.configuration = configuration;
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
     * When a property is changed in a scoreline, fire a property change event,
     * reset un-scored lines
     * after calculating score values, and set isFull to true if there are no empty
     * lines
     * 
     * @param evt The event that was fired.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) { // When a property is changed in a scoreline
        if (evt.getPropertyName().equals("scored")) {
            pcs.firePropertyChange("scored", evt.getOldValue(), evt.getNewValue());
            boolean emptyLine = false;
            for (ScorecardLine line : lines) { // Reset un-scored lines after calculating score values.
                if (!line.isScored()) {
                    line.setValue(0);
                }

                calcNewTotal();

                if (!line.isScored()) {
                    emptyLine = true;
                }
            }
            isFull = !emptyLine;
        }
    }

    /**
     * <<<<<<< HEAD
     * If all the lines are scored, then the scorecard is full
     * 
     * @return The method isFull() is returning a boolean value.
     *         =======
     *         Test if all lines in scorecard are full.
     * 
     * @return True if full, false if not full.
     *         >>>>>>> de9b885f21a5a199f4ad46faa00b02a09beb0b65
     */
    public boolean isFull() {
        for (ScorecardLine line : getLines()) {
            if (!line.isScored()) {
                return false;
            }
        }
        return true;
    }

    /**
     * <<<<<<< HEAD
     * This function returns an ArrayList of ScorecardLine objects
     * 
     * @return The lines arraylist.
     *         =======
     *         Get the data of all lines in this scorecard.
     * 
     * @return The list of lines in this scorecard.
     *         >>>>>>> de9b885f21a5a199f4ad46faa00b02a09beb0b65
     */
    public ArrayList<ScorecardLine> getLines() {
        return lines;
    }

    /**
     * This function is called when a new hand is dealt. It should update the score
     * of the hand.
     * 
     * @param hand The hand to score.
     */
    public abstract void scoreNewHand(Hand hand);

    /**
     * "If the total has changed, fire a property change event."
     * 
     * The first line of the function gets the old total. The third line sets the
     * new total by summing the values and fires a property change event.
     * The fourth line fires a property change event for the total
     */
    public void calcNewTotal() {
        int old = totalLine.getValue();
        int sum = 0;
        for (ScorecardLine line : getLines()) {
            sum += line.getValue();
        }

        totalLine.setValueWithEvent(sum);
        pcs.firePropertyChange("total", old, sum);

    }

    /**
     * <<<<<<< HEAD
     * This function returns the totalLine
     * 
     * @return The totalLine object.
     *         =======
     *         Gets the total line model for this scorecard.
     * 
     * @return the total line model.
     *         >>>>>>> de9b885f21a5a199f4ad46faa00b02a09beb0b65
     */
    public ScorecardLine getTotalLine() {
        return totalLine;
    }

    public ScorecardLine setTotalLine(Integer number) {
        totalLine.setValue(number);
        return totalLine;
    }

}
