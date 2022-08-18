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

import java.util.ArrayList;
import java.util.List;

/** Class to calculate and store data for the upper section of the scorecard */
public class UpperSectionScoring {
    private Hand hand;
    private List<Integer> upperSection;

    public UpperSectionScoring(Hand hand) {
        upperSection = new ArrayList<>();
        for (int i = 0; i < hand.getDice().get(0).getNumSides(); i++) {
            upperSection.add(0);
        }
        updateSection(hand);
    }

    /**
     * Updates the data in this scorecard section with a new hand.
     *
     * @param hand the hand to update the scorecard with.
     */
    public void updateSection(Hand hand) {
        this.hand = hand;

        List<Die> dice = hand.getDice();
        for (Die die : dice) {
            upperSection.set(die.getSideUp() - 1, upperSection.get(die.getSideUp() - 1) + die.getSideUp());
        }
    }

    /**
     * Retrieves a list of integers where the value at each index corresponds to the
     * quantity of die with a
     * face up value of i+1. For example, if the hand has two 4's the value at index
     * 3 would equal 2.
     *
     * @return the list of quantities of die as described above.
     */
    public final List<Integer> getSectionData() {
        return upperSection;
    }
}
