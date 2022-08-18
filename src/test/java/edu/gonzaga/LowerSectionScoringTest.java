package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowerSectionScoringTest {
    @Test
    void testYahtzeeScoring() {
        int expectedValue = 50;
        int dieSides = 6;
        Hand hand = new Hand(5, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        dice.add(new Die(dieSides, 6));
        hand.setHand(dice);
        LowerSectionScoring score = new LowerSectionScoring(hand);
        assertEquals(expectedValue, score.getSectionData().get("y"));
    }
}
