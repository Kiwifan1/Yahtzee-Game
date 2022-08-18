/**
 * This program plays a game of Yahtzee.
 * CPSC 224, Spring 2022
 * HW4
 * No sources to cite.
 *
 * @author Tyler CH
 * @version v1.0 4/7/2022
 */
package edu.gonzaga.dialogs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import edu.gonzaga.GameConfiguration;
import edu.gonzaga.views.ParameterView;

/**
 * Dialog for users to choose game settings before game.
 */
public class ConfigurationDialog extends JDialog implements PropertyChangeListener {

    ParameterView content;

    public ConfigurationDialog(JFrame frame, GameConfiguration config) {
        super(frame, "Game Settings", true);
        content = new ParameterView(config);
        content.addPropertyChangeListener(this::propertyChange);
        setContentPane(content);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(250, 175);
        setResizable(false);
    }

    public ConfigurationDialog(JFrame frame) {
        this(frame, new GameConfiguration());
    }

    public GameConfiguration getPayload() {
        return content.getConfig();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("close")) {
            setVisible(false);
        }
    }
}
