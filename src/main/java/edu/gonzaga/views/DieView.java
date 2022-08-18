/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author Tyler CH
 * @version v1.0 4/7/2022
 */
package edu.gonzaga.views;

import edu.gonzaga.DiceImages;
import edu.gonzaga.Die;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DieView extends JButton implements PropertyChangeListener {
    private DiceImages images;

    Die die;

    private boolean locked = false;

    public DieView(Die die) {
        super("");
        images = new DiceImages("media");
        this.die = die;
        setIcon(images.getDieImage(1));
        setPreferredSize(new Dimension(40, 40));
        die.addPropertyChangeListener(this::propertyChange);

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Updates GUI when lock state changed from model.
                toggleLock();
            }
        });

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
    }

    /**
     * Locks/Unlocks the die in this view
     */
    private void toggleLock() {
        locked = !locked;
        updateLockedState();
    }

    /**
     * Refreshed the UI to whatever state the model is in.
     */
    private void updateLockedState() {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Border redline = BorderFactory.createLineBorder(Color.red);
        if (locked) {
            setBorder(redline);
            die.lock();
        } else {
            setBorder(blackline);
            die.unlock();
        }
    }

    /**
     * Sets the locked state of the UI. Does not update model.
     * 
     * @param locked the state to set.
     */
    private void setLockedState(boolean locked) {
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Border redline = BorderFactory.createLineBorder(Color.red);
        if (locked) {
            setBorder(redline);
        } else {
            setBorder(blackline);
        }
    }

    /**
     * PropertyChangeListener callback.
     * 
     * @param evt the event triggered.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) { // Called when die model is rolled.
        String propertyName = evt.getPropertyName();
        if (propertyName.equals("sideUp")) {
            setIcon(images.getDieImage((int) evt.getNewValue()));
        } else if (propertyName.equals("lock")) {
            setLockedState((Boolean) evt.getNewValue());
        }
    }
}
