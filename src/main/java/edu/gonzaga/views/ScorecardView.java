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

import edu.gonzaga.Scorecard;
import edu.gonzaga.ScorecardLine;
import edu.gonzaga.UpperScorecard;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ScorecardView extends JPanel {

    private ArrayList<Scorecard> scorecards;

    private JLabel titleLabel;
    private ArrayList<ScorecardLineView> lineViews;

    /**
     * GUI for a scorecard.
     */
    public ScorecardView(ArrayList<Scorecard> scorecards, boolean scoringMode, String title) {
        this.scorecards = scorecards;
        this.lineViews = new ArrayList<>();
        this.titleLabel = new JLabel(title);

        setLayout(new GridLayout(13, 2));

        titleLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));

        add(titleLabel);
        for (Scorecard scorecard : scorecards) {
            for (ScorecardLine line : scorecard.getLines()) {
                if (!line.isScored() && !line.getIdentifier().equals("b")) {
                    lineViews.add(new ScorecardLineView(null, scoringMode, line));
                    add(lineViews.get(lineViews.size() - 1));
                }
            }
            if (!scoringMode) {
                add(new ScorecardLineView(null, false, scorecard.getTotalLine()));
            }
        }
    }

    public ScorecardView(Scorecard scorecard, boolean scoringMode, String title) {
        this.scorecards = new ArrayList<>();
        scorecards.add(scorecard);
        this.lineViews = new ArrayList<>();
        this.titleLabel = new JLabel(title);

        setLayout(new GridLayout(13, 2));

        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));

        add(titleLabel);
        for (ScorecardLine line : scorecard.getLines()) {
            lineViews.add(new ScorecardLineView(null, scoringMode, line));
            add(lineViews.get(lineViews.size() - 1));
        }

        if (!scoringMode) {
            add(new ScorecardLineView(null, false, scorecard.getTotalLine()));
        }
    }

    public ScorecardView(Scorecard scorecard, String title) {
        this(scorecard, false, title);
    }

}
