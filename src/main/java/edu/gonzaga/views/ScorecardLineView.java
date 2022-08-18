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

import edu.gonzaga.ScorecardLine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScorecardLineView extends JPanel implements PropertyChangeListener {
    private ImageIcon icon;
    private JLabel iconView;
    private JLabel label;
    private JTextField value;
    private JButton button;

    private ScorecardLine line;

    private final boolean SHOW_SCORE_BUTTON = false;

    /**
     * GUI for scoreline.
     */
    public ScorecardLineView(ImageIcon icon, boolean showScoreButton, ScorecardLine line) {
        this.icon = icon;
        label = new JLabel(line.getTitle());
        value = new JTextField();
        button = new JButton("Score");
        this.line = line;
        line.addPropertyChangeListener(this::propertyChange);

        button.setPreferredSize(new Dimension(100, 30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                line.score();
            }
        });

        value.setPreferredSize(new Dimension(50, 30));
        value.setEditable(false);
        value.setText("" + line.getValue());

        add(label);
        add(value);

        if (showScoreButton) {
            add(button);
        }

        if (icon != null) {
            iconView = new JLabel(icon);
            add(iconView);
        }

        setPreferredSize(new Dimension(200, 40));
    }

    /**
     * Refresh the UI with the model.
     */
    public void updateValue() {
        value.setText("" + line.getValue());
    }

    /**
     * PropertyChangeListener callback.
     * 
     * @param evt the event triggered.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("scored") || evt.getPropertyName().equals("value")) {
            updateValue();
        }
    }
}
