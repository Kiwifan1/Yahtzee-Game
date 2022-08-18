package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpperScorecardTest {
    @Test
    void testInitialization() {
        int expected = 0;

        UpperScorecard scorecard = new UpperScorecard(new GameConfiguration());
        for (ScorecardLine line : scorecard.getLines()) {
            assertEquals(expected, line.getValue());
        }
    }

    @Test
    void testScoreHand() {
        boolean expected = true;
        int sum = 0;

        Hand hand = new Hand(new GameConfiguration());
        hand.rollAll();
        UpperScorecard scorecard = new UpperScorecard(new GameConfiguration());
        scorecard.scoreNewHand(hand);
        for (ScorecardLine line : scorecard.getLines()) {
            sum += line.getValue();
        }
        assertEquals(expected, sum > 0);
    }
    @Test
    void checkBonusTest(){
        int expectedValue = 100;
        Hand hand = new Hand(5, 4, 3);
        ScorecardLine test = new ScorecardLine("test");
        UpperScorecard card = new UpperScorecard(new GameConfiguration());
        card.setTotalLine(65);

        UpperSectionScoring score = new UpperSectionScoring(hand);
        test.setValue(card.checkBonus());
        assertEquals(expectedValue, test.getValue());
    }
}
