package edu.gonzaga;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowerScorecardTest {
    private final String[] titles = {
            "3 of a kind", "4 of a kind", "Full House", "Sm. Straight", "Lg. Straight", "YAHTZEE",
            "Chance", "Bonus"
    };

    private final String[] ids = {
            "3k", "4k", "fh", "ss", "ls", "y",
            "c", "b"
    };

    @Test
    void testNameInit() {
        LowerScorecard scorecard = new LowerScorecard(new GameConfiguration());
        ArrayList<ScorecardLine> lines = scorecard.getLines();
        for (int i = 0; i < titles.length; i++) {
            assertEquals(titles[i], lines.get(i).getTitle());
        }
    }

    @Test
    void testIdInit() {
        LowerScorecard scorecard = new LowerScorecard(new GameConfiguration());
        ArrayList<ScorecardLine> lines = scorecard.getLines();
        for (int i = 0; i < ids.length; i++) {
            assertEquals(ids[i], lines.get(i).getIdentifier());
        }
    }

    @Test
    void testValueInit() {
        int expected = 0;

        LowerScorecard scorecard = new LowerScorecard(new GameConfiguration());
        ArrayList<ScorecardLine> lines = scorecard.getLines();
        for (int i = 0; i < lines.size(); i++) {
            assertEquals(expected, lines.get(i).getValue());
        }
    }

    @Test
    void testScoreNewHand() {
        Hand hand = new Hand(new GameConfiguration());

    }

    @Test
    void testThreeOfAKind() {
        int expectedValue = 120;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        assertEquals(expectedValue, card.getLine("3k").getValue());
    }

    @Test
    void testFourOfAKind() {
        int expectedValue = 90;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        assertEquals(expectedValue, card.getLine("3k").getValue());
        assertEquals(expectedValue, card.getLine("4k").getValue());
    }

    @Test
    void testYahtzee() {
        int expectedValue = 91;
        int expectedYValue = 50;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        assertEquals(expectedValue, card.getLine("3k").getValue());
        assertEquals(expectedValue, card.getLine("4k").getValue());
        assertEquals(expectedYValue, card.getLine("y").getValue());
    }

    @Test
    void testFullHouse() {
        int expectedValue = 25;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        assertEquals(expectedValue, card.getLine("fh").getValue());
    }

    @Test
    void testSmallStraight() {
        int expectedValue = 30;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        assertEquals(expectedValue, card.getLine("ss").getValue());
    }

    @Test
    void testLargeStraight() {
        int expectedValueSmall = 30;
        int expectedValueLarge = 40;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        assertEquals(expectedValueSmall, card.getLine("ss").getValue());
        assertEquals(expectedValueLarge, card.getLine("ls").getValue());
    }

    @Test
    void testBonus() {
        int expectedValue = 200;
        int dieSides = 30;
        int handSize = 10;

        GameConfiguration config = new GameConfiguration(handSize, dieSides, 3);

        Hand hand = new Hand(config);
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
        LowerScorecard card = new LowerScorecard(config);
        card.scoreNewHand(hand);
        card.getLine("y").score();
        card.scoreNewHand(hand);
        card.scoreNewHand(hand);
        assertEquals(card.getLine("b").getValue(), expectedValue);
    }
}
