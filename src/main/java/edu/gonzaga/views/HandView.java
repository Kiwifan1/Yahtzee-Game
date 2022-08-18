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

import edu.gonzaga.Hand;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HandView extends JPanel implements PropertyChangeListener {

    private JButton rollButton;

    private ArrayList<DieView> dieViews;

    private Hand hand;
    private int rollCount;

    /**
     * Class for the display of the hand.
     * 
     * @param hand           the hand to display.
     * @param showRollButton option to hide the roll button.
     */
    public HandView(Hand hand, boolean showRollButton) {
        rollButton = new JButton("Roll");
        dieViews = new ArrayList<>();
        this.hand = hand;
        this.hand.addPropertyChangeListener(this::propertyChange);
        rollCount = 1;

        for (int i = 0; i < hand.getHandSize(); i++) {
            DieView view = new DieView(hand.getDice().get(i));
            add(view);
            dieViews.add(view);
        }

        if (showRollButton) {
            add(rollButton);
        }

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hand.rollAll();
                rollCount++;
                if (rollCount >= hand.maxRollsPerTurn()) {
                    rollButton.setEnabled(false);
                }
            }
        });
        hand.rollAll();
    }

    /**
     * Get a reference to the roll button that rolls the die in the hand.
     * 
     * @return the roll button.
     */
    public JButton getRollButton() {
        return rollButton;
    }

    /**
     * PropertyChangeListener callback.
     * 
     * @param evt the event triggered.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("newturn")) {
            rollCount = 1;
        }
    }
}