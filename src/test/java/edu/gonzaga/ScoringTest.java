package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ScoringTest {
    @Test
    void testThreeOfAKind() {
        int expectedValue = 120;
        int dieSides = 30;
        int handSize = 10;

        Hand hand = new Hand(handSize, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 9));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 29));
        hand.setHand(dice);
        // Scorecard card = new Scorecard(hand, true);
        // Map<String, Integer> data = card.getLowerSectionScoring().getSectionData();
        // assertEquals(expectedValue, data.get("3k"));
    }

    @Test
    void testFourOfAKind() {
        int expectedValue = 90;
        int dieSides = 30;
        int handSize = 10;

        Hand hand = new Hand(handSize, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 9));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 29));
        hand.setHand(dice);
        // Scorecard card = new Scorecard(hand, true);
        // Map<String, Integer> data = card.getLowerSectionScoring().getSectionData();
        // assertEquals(expectedValue, data.get("3k"));
        // assertEquals(expectedValue, data.get("4k"));
    }

    @Test
    void testYahtzee() {
        int expectedValue = 91;
        int dieSides = 30;
        int handSize = 10;

        Hand hand = new Hand(handSize, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 29));
        hand.setHand(dice);
        // Scorecard card = new Scorecard(hand, true);
        // Map<String, Integer> data = card.getLowerSectionScoring().getSectionData();
        // assertEquals(expectedValue, data.get("3k"));
        // assertEquals(expectedValue, data.get("4k"));
        // assertEquals(expectedValue, data.get("y"));
    }

    @Test
    void testFullHouse() {
        int expectedValue = 25;
        int dieSides = 30;
        int handSize = 10;

        Hand hand = new Hand(handSize, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 2));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 5));
        dice.add(new Die(dieSides, 7));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 21));
        dice.add(new Die(dieSides, 29));
        hand.setHand(dice);
        // Scorecard card = new Scorecard(hand, true);
        // Map<String, Integer> data = card.getLowerSectionScoring().getSectionData();
        // assertEquals(expectedValue, data.get("fh"));
    }

    @Test
    void testSmallStraight() {
        int expectedValue = 30;
        int dieSides = 30;
        int handSize = 10;

        Hand hand = new Hand(handSize, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 7));
        dice.add(new Die(dieSides, 8));
        dice.add(new Die(dieSides, 9));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 12));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 29));
        hand.setHand(dice);
        // Scorecard card = new Scorecard(hand, true);
        // Map<String, Integer> data = card.getLowerSectionScoring().getSectionData();
        // assertEquals(expectedValue, data.get("ss"));
    }

    @Test
    void testLargeStraight() {
        int expectedValueSmall = 30;
        int expectedValueLarge = 40;
        int dieSides = 30;
        int handSize = 10;

        Hand hand = new Hand(handSize, dieSides, 3);
        List<Die> dice = new ArrayList<>();
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 1));
        dice.add(new Die(dieSides, 7));
        dice.add(new Die(dieSides, 8));
        dice.add(new Die(dieSides, 9));
        dice.add(new Die(dieSides, 10));
        dice.add(new Die(dieSides, 11));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 20));
        dice.add(new Die(dieSides, 29));
        hand.setHand(dice);
        // Scorecard card = new Scorecard(hand, true);
        // Map<String, Integer> data = card.getLowerSectionScoring().getSectionData();
        // assertEquals(expectedValueSmall, data.get("ss"));
        // assertEquals(expectedValueLarge, data.get("ls"));
    }
}
