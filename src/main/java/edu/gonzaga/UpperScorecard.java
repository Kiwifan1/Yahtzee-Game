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

import java.util.List;

public class UpperScorecard extends Scorecard {

    private final String[] titles = {
            "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "Sevens", "Eights", "Nines", "Tens", "Elevens", "Twelves"
    };

    public UpperScorecard(GameConfiguration config) {
        super(config);

        // Adding a new scorecard line to the list of lines, as well as adding a
        // propertyChangeListener
        for (int i = 0; i < config.getNumDieSides(); i++) {
            getLines().add(new ScorecardLine(titles[i]));
            getLines().get((getLines().size() - 1)).addPropertyChangeListener(this::propertyChange);
        }
    }

    /**
     * If the line is not scored, set the value of the line to the value of the new
     * score at the index of
     * the line.
     * 
     * @param hand The hand that is being scored
     */
    @Override
    public void scoreNewHand(Hand hand) {
        UpperSectionScoring scoring = new UpperSectionScoring(hand);
        List<Integer> data = scoring.getSectionData();

        // Iterates through the data list. If the line is not scored, then
        // the value of the line is set to the value of the new score at the index of
        // the line.
        for (int i = 0; i < data.size(); i++) {
            if (!getLines().get(i).isScored()) {
                getLines().get(i).setValue(data.get(i));
            }
        }
    }

    public Integer checkBonus() {

        Integer newTotal = 0;
        newTotal = getTotalLine().getValue();
        if (newTotal >= 60) {
            newTotal += 35;
        }
        return newTotal;

    }
}
