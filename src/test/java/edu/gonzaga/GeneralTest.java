package edu.gonzaga;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.io.File;

public class GeneralTest {

    // ------------------------------------Yahtzee---------------------------------------

    @Test
    void checkAbleToInitializeYahtzee() {
        Yahtzee yahtzee = new Yahtzee();
        Assertions.assertTrue(yahtzee instanceof Yahtzee);
    }

    // ------------------------------------GameControl------------------------------------

    @Test
    void checkCanCreateGameControl()
    {
        GameControl control = new GameControl();
        Assertions.assertTrue(control instanceof GameControl);
    }
    // @Test
    // void checkReadFile() {
    //     File file = new File("YahtzeeConfig.txt");
    //     Assertions.assertTrue(file.canRead());
    // }

    // @Test
    // void checkWriteFile() {
    //     File file = new File("YahtzeeConfig.txt");
    //     Assertions.assertTrue(file.canWrite());
    // }

    // ------------------------------------Player-----------------------------------------
    @Test
    void checkSettingPossibleScores() {
        HashMap<String, Integer> scores = new HashMap<>();
        ArrayList<Integer> diceInfo = new ArrayList<>();
        diceInfo.add(6); // 6 sided dice
        diceInfo.add(5); // 5 dice
        diceInfo.add(3); // 3 rerolls
        scores.put("1", 1);
        scores.put("2", 4);
        scores.put("3", 6);
        scores.put("4", 4);
        scores.put("OFKIND3", 6);
        scores.put("CHANCE", 12);
        Player player = new Player(diceInfo);
        player.setPossibleScores(scores);
        ScoreCardLine line = player.getScoreCardLine("OFKIND3");
        int expected = 6;
        int actual = line.getPossibleScore();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void checkNumberSections() {
        ArrayList<Integer> diceInfo = new ArrayList<>();
        diceInfo.add(6); // 6 sided dice
        diceInfo.add(8); // 5 dice
        diceInfo.add(3); // 3 rerolls
        Player player = new Player(diceInfo);
        int expected = 18;
        int actual = player.getNumSections();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void checkAddIndividualScoreCardLine() {
        ArrayList<Integer> diceInfo = new ArrayList<>();
        diceInfo.add(6); // 6 sided dice
        diceInfo.add(5); // 5 dice
        diceInfo.add(3); // 3 rerolls
        Player player = new Player(diceInfo);
        player.addScore("OFKIND3", 18);
        int expected = 18;
        int actual = player.getScoreCardLine("OFKIND3").getScore();
        Assertions.assertEquals(expected, actual);
    }

    // ------------------------------------Hand-------------------------------------------
    @Test
    void checkDiceScore() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(6); // 6 sided dice
        array.add(5); // 5 dice
        array.add(3); // 3 rerolls

        Hand hand = new Hand(array);
        int expected = 5;
        int actual = hand.getTotal();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkDiceRoll() {
        // tests if dice can be rolled
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(6); // 6 sided dice
        array.add(5); // 5 dice
        array.add(3); // 3 rerolls

        Hand hand = new Hand(array);
        hand.rollDice();
        hand.rollDice();
        hand.rollDice();

        ArrayList<Die> methodValue = hand.returnDice();
        ArrayList<Die> expectedValue = new ArrayList<Die>();
        expectedValue.add(new Die());
        expectedValue.add(new Die());
        expectedValue.add(new Die());
        expectedValue.add(new Die());
        expectedValue.add(new Die());
        expectedValue.add(new Die());

        Assertions.assertNotEquals(expectedValue, methodValue);
    }

    @Test
    void checkStraight() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Die> dice = new ArrayList<Die>();
        dice.add(new Die(8, 3));
        dice.add(new Die(8, 1));
        dice.add(new Die(8, 2));
        dice.add(new Die(8, 4));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 6));
        array.add(8); // 8 sided dice
        array.add(6); // 6 dice
        array.add(3); // 3 rerolls

        Hand hand = new Hand(array);
        hand.setDice(dice);

        HashMap<String, Integer> returnValue = hand.calculateScores();

        int LgStraightAnswer = returnValue.get("LGSTRAIGHT");
        int smStraightAns = returnValue.get("SMSTRAIGHT");
        boolean allAnswers = (smStraightAns == 30 && LgStraightAnswer == 40);

        Assertions.assertTrue(allAnswers);
    }

    @Test
    void checkOfKind() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Die> dice = new ArrayList<Die>();
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 2));
        dice.add(new Die(8, 1));
        array.add(8); // 8 sided dice
        array.add(6); // 6 dice
        array.add(3); // 3 rerolls
        Hand hand = new Hand(array);
        hand.setDice(dice);

        int ofThreeValue = hand.calculateScores().get("OFKIND3");
        int ofFourValue = hand.calculateScores().get("OFKIND4");
        boolean allCorrect = (ofThreeValue == 23 && ofFourValue == 23);
        Assertions.assertTrue(allCorrect);

    }

    @Test
    void checkFullHouse() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Die> dice = new ArrayList<Die>();
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 3));
        dice.add(new Die(8, 3));
        dice.add(new Die(8, 1));
        array.add(8); // 8 sided dice
        array.add(6); // 6 dice
        array.add(3); // 3 rerolls
        Hand hand = new Hand(array);
        hand.setDice(dice);

        int returnValue = hand.calculateScores().get("FULLHOUSE");
        int expectedValue = 25;
        Assertions.assertEquals(expectedValue, returnValue);
    }

    @Test
    void checkChance() {
        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Die> dice = new ArrayList<Die>();
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 5));
        dice.add(new Die(8, 3));
        dice.add(new Die(8, 3));
        dice.add(new Die(8, 1));
        array.add(8); // 8 sided dice
        array.add(6); // 6 dice
        array.add(3); // 3 rerolls
        Hand hand = new Hand(array);
        hand.setDice(dice);

        int returnValue = hand.calculateScores().get("CHANCE");
        int expectedValue = 22;
        Assertions.assertEquals(expectedValue, returnValue);
    }

    // ------------------------------------Section---------------------------------------------

    @Test
    void checkCalculateFullHouse() {
        Section section = new Section(5);
        HashMap<Integer, Integer> hashedDice = new HashMap<>();
        hashedDice.put(4, 3);
        hashedDice.put(6, 2);
        section.setHashDice(hashedDice);
        section.calculateFullHouse();
        int expected = 25;
        int actual = section.getScores().get("FULLHOUSE");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkCalculateOfKind() {
        Section section = new Section(5);
        HashMap<Integer, Integer> hashedDice = new HashMap<>();
        hashedDice.put(4, 3);
        hashedDice.put(6, 2);
        section.setHashDice(hashedDice);
        section.setTotalScore(24);
        section.calculateOfKind();
        int expected = 24;
        int actual = section.getScores().get("OFKIND3");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkCalculateStraight() {
        Section section = new Section(5);
        HashMap<Integer, Integer> hashedDice = new HashMap<>();
        hashedDice.put(1, 1);
        hashedDice.put(2, 1);
        hashedDice.put(3, 1);
        hashedDice.put(4, 1);
        hashedDice.put(6, 1);
        section.setHashDice(hashedDice);
        section.calculateStraight();
        int smallStraight = section.getScores().get("SMSTRAIGHT");
        int largeStraight = section.getScores().get("LGSTRAIGHT");
        boolean bothCorrect = (smallStraight == 30 && largeStraight == 0);
        Assertions.assertTrue(bothCorrect);
    }

    @Test
    void checkCalculateUpperSection() {
        Section section = new Section(5);
        HashMap<Integer, Integer> hashedDice = new HashMap<>();
        hashedDice.put(4, 2);
        hashedDice.put(1, 1);
        hashedDice.put(6, 2);
        section.setHashDice(hashedDice);
        section.calculateUpperSection();
        int expected = 12;
        int actual = section.getScores().get("6");
        Assertions.assertEquals(expected, actual);
    }

    // ------------------------------------ScoreCard-------------------------------------------

    @Test
    void checkInitialization() {
        ScoreCard scoreCard = new ScoreCard(5, 2);
        int expected = 12;
        int actual = scoreCard.size();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkGetLine() {
        ScoreCardLine line = new ScoreCardLine("3", 3);
        ScoreCard scoreCard = new ScoreCard(5, 2);
        for (ScoreCardLine i : scoreCard) {
            i.setPossibleScore(3);
        }
        ScoreCardLine potentialLine = scoreCard.get(2);
        boolean bothMatch = (line.toString().equals(potentialLine.toString())
                && line.getPossibleScore() == potentialLine.getPossibleScore());
        Assertions.assertTrue(bothMatch);
    }

    @Test
    void testTotalScore() {
        ScoreCard scoreCard = new ScoreCard(5, 3);
        int count = 1;
        for (ScoreCardLine i : scoreCard) {
            i.setFinalScore(count);
            count++;
        }
        int expected = 91;
        int actual = scoreCard.getTotalScore();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testGameEnd() {
        ScoreCard scoreCard = new ScoreCard(5, 2);
        for (ScoreCardLine i : scoreCard) {
            i.setPossibleScore(3);
        }
        scoreCard.gameEnd();
        int expected = 0;
        int actual = scoreCard.getLine("1").getPossibleScore();
        Assertions.assertEquals(expected, actual);
    }

    // ------------------------------------ScoreCardLine---------------------------------------

    @Test
    void checkCanAddScore() {
        ScoreCardLine line = new ScoreCardLine("1", 5);
        line.setFinalScore();
        boolean scorePicked = (line.getScore() == 5 && line.hasBeenPicked());
        Assertions.assertTrue(scorePicked);
    }

    @Test
    void checkSetNewPossibleScore() {
        ScoreCardLine line = new ScoreCardLine("2", 6);
        line.setPossibleScore(3);
        int expected = 3;
        int actual = line.getPossibleScore();
        Assertions.assertEquals(expected, actual);
    }
}
