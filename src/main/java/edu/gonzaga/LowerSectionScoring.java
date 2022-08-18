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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Class to calculate and store data for the lower section of the scorecard */
public class LowerSectionScoring {
    private Hand hand;
    private Map<String, Integer> sectionData;
    private List<Integer> counts;

    public LowerSectionScoring(Hand hand) {
        sectionData = new HashMap<>();
        counts = new ArrayList<>();

        // Creating a list of 0's with a length equal to the number of sides on the die.
        for (int i = 0; i < hand.getDice().get(0).getNumSides(); i++) {
            counts.add(0);
        }
        updateSection(hand);
    }

    /**
     * This function updates the section of the scorecard that is being used
     * 
     * @param hand the hand of dice
     */
    public void updateSection(Hand hand) {
        this.hand = hand;
        countDie();
        calculateOfAKind();
        calculateStraight();
        calculateChance();
    }

    /**
     * Returns a map where each String key corresponds to the data on the line of
     * the scorecard with
     * the same name. For example, the value at key "3k" would be the score on the
     * three-of-a-kind
     * line in the scorecard.
     *
     * @return A map with a String-Integer key-value pair as described above.
     */
    public final Map<String, Integer> getSectionData() {
        return sectionData;
    }

    /**
     * Populates the counts list with the count of each number of die in the hand.
     * For example,
     * if a hand was all 1's the list would contain 5 in the 0 index and 0 in the
     * rest of the indices.
     */
    private void countDie() {
        List<Die> dice = hand.getDice();
        for (Die die : dice) {
            counts.set(die.getSideUp() - 1, counts.get(die.getSideUp() - 1) + 1);
        }
    }

    /**
     * If the dice contain 3 of a kind, then if they also contain 2 of a kind,
     * then add 25 to the score for full house. Otherwise, add the sum of the dice
     * to the score for 3 of a kind.
     * If the dice contain 4 of a kind, then add the sum of the dice to the score
     * for 3 of a kind and
     * 4 of a kind. If the dice contains 5 of a kind, then add the sum of the dice
     * to the score for
     * 3 of a kind, 4 of a kind, and yahtzee
     */
    private void calculateOfAKind() {
        if (counts.contains(3)) {
            if (counts.contains(2)) {
                sectionData.put("fh", 25);
            }
            int sum = calculateSum();
            sectionData.put("3k", sum);
        }
        if (counts.contains(4)) {
            int sum = calculateSum();
            sectionData.put("3k", sum);
            sectionData.put("4k", sum);
        }
        if (counts.contains(5)) {
            int sum = calculateSum();
            sectionData.put("3k", sum);
            sectionData.put("4k", sum);
            sectionData.put("y", 50);
        }
    }

    /**
     * If the number of consecutive values in the array is 4, then the value of the
     * key "ss" (small straight)
     * in the HashMap is 30. If the number of consecutive non-zero values in the
     * array is 5, then the
     * value of the key "ss" (small straight) in the HashMap is 30 and the value of
     * the key "ls" (large straight) in the HashMap is 40
     */
    private void calculateStraight() {
        int numConsec = 1;
        for (int i = 0; i < counts.size() - 1; i++) {
            if (counts.get(i) != 0 && counts.get(i + 1) != 0) {
                numConsec++;
            } else if ((counts.get(i) == 0 || counts.get(i + 1) == 0) && numConsec < 4) {
                numConsec = 1;
            }
            if (numConsec == 4) {
                sectionData.put("ss", 30);
            } else if (numConsec == 5) {
                sectionData.put("ss", 30);
                sectionData.put("ls", 40);
            }
        }

    }

    /**
     * It takes a list of integers, and returns the sum of the product of each
     * integer and its index (for chance line)
     */
    private void calculateChance() {
        int sum = 0;
        for (int i = 1; i <= counts.size(); i++) {
            sum += i * counts.get(i - 1);
        }
        sectionData.put("c", sum);
    }

    /**
     * This function calculates the sum of the dice in the hand
     * 
     * @return The sum of the dice.
     */
    private int calculateSum() {
        int sum = 0;
        for (Die die : hand.getDice()) {
            sum += die.getSideUp();
        }
        return sum;
    }
}
