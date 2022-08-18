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

import edu.gonzaga.GameConfiguration;
import edu.gonzaga.components.LabeledComponent;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ParameterView extends JPanel {
    private final Integer[] sideChoices = { 6, 8, 12 };
    private final Integer[] handSizeChoices = { 5, 6, 7 };
    private final Integer[] numRollsChoices = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    private JLabel title;
    private JComboBox<Integer> dieSides;
    private JComboBox<Integer> handSize;
    private JComboBox<Integer> numRolls;
    private JButton confirmButton;
    private JButton cancelButton;

    private LabeledComponent dieSidesComponent;
    private LabeledComponent handSizeComponent;
    private LabeledComponent numRollsComponent;

    private GameConfiguration config;

    private JDialog parent;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Options for selecting the game config.
     */
    public ParameterView() {
        this(new GameConfiguration());
    }

    public ParameterView(GameConfiguration config) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.config = config;
        this.parent = parent;

        title = new JLabel("Choose your game settings:");
        dieSides = new JComboBox<>(sideChoices);
        dieSides.setPreferredSize(new Dimension(100, 20));
        dieSides.setSelectedItem(config.getNumDieSides());
        handSize = new JComboBox<>(handSizeChoices);
        handSize.setPreferredSize(new Dimension(100, 20));
        handSize.setSelectedItem(config.getHandSize());
        numRolls = new JComboBox<>(numRollsChoices);
        numRolls.setPreferredSize(new Dimension(100, 20));
        numRolls.setSelectedIndex(2);

        dieSidesComponent = new LabeledComponent("Die Sides:", dieSides);
        handSizeComponent = new LabeledComponent("Hand Sides:", handSize);
        numRollsComponent = new LabeledComponent("Rolls per Turn:", numRolls);

        this.add(dieSidesComponent);
        this.add(handSizeComponent);
        this.add(numRollsComponent);
        this.add(new BottomButtons());
        this.add(Box.createRigidArea(new Dimension(5, 10)));

        confirmButton.addActionListener(e -> { // OK Button
            int sides = (Integer) dieSides.getSelectedItem();
            int size = (Integer) handSize.getSelectedItem();
            int rolls = (Integer) numRolls.getSelectedItem();

            this.config = new GameConfiguration(sides, size, rolls);
            pcs.firePropertyChange("close", 0, 1);
        });

        // Cancel Button
        cancelButton.addActionListener(e -> {
            this.config = null;
            pcs.firePropertyChange("close", 0, 1);
        });
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
     * Used to get the payload out of dialog.
     * 
     * @return the configuration choosen from this panel.
     */
    public GameConfiguration getConfig() {
        return config;
    }

    /**
     * OK and Cancel buttons.
     */
    private class BottomButtons extends JPanel {
        public BottomButtons() {
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

            confirmButton = new JButton("OK");
            cancelButton = new JButton("Cancel");

            this.add(confirmButton);
            this.add(cancelButton);
        }
    }
}
