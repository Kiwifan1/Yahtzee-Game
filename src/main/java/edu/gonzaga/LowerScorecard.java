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
import java.util.Map;

public class LowerScorecard extends Scorecard implements PropertyChangeListener {
    private final String[] titles = {
            "3 of a kind", "4 of a kind", "Full House", "Sm. Straight", "Lg. Straight", "YAHTZEE",
            "Chance", "Bonus"
    };

    private final String[] ids = {
            "3k", "4k", "fh", "ss", "ls", "y",
            "c", "b"
    };

    public LowerScorecard(GameConfiguration configuration) {
        super(configuration);

        // Looping through the array of titles and adding them to the scorecard.
        for (int i = 0; i < titles.length; i++) {
            getLines().add(new ScorecardLine(titles[i], ids[i]));
            getLines().get((getLines().size() - 1)).addPropertyChangeListener(this::propertyChange);
        }

        getLine("b").score();
    }

    /**
     * Updates the data in this scorecard section with a new hand.
     *
     * @param hand the hand to update the scorecard with.
     */
    @Override
    public void scoreNewHand(Hand hand) {
        LowerSectionScoring scoring = new LowerSectionScoring(hand);
        Map<String, Integer> data = scoring.getSectionData();

        // Looping through the array of lines and setting their value to 0
        for (ScorecardLine line : getLines()) {
            if (!line.isScored() && !line.getIdentifier().equals("b")) {
                line.setValue(data.getOrDefault(line.getIdentifier(), 0));
            }
        }

        // Checking if the line with the id of "y" is scored and if the data has a value
        // of "y" that is greater than 0.
        if (getLine("y").isScored() && data.getOrDefault("y", 0) > 0) {
            ScorecardLine line = getLine("b");
            line.setValueWithEvent(line.getValue() + 100);
        }
    }

    /**
     * Use to get a line in this scorecard.
     * 
     * @param id the id of the line to get.
     * @return the line with the corresponding id.
     */
    public ScorecardLine getLine(String id) {
        for (ScorecardLine line : getLines()) {
            if (line.getIdentifier().equals(id)) {
                return line;
            }
        }
        return null;
    }
}
