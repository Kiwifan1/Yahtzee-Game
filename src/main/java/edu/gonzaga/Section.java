/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 1/14/22
 * Programming Assigment: Yahtzee HW3
 * Description: To build and implement the game of yahtzee in Java
 * Notes: 
 *  2/11/22 - implementing lower section instead of having everything in hand
 * 
 **/


package edu.gonzaga;

import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;

public class Section {
    private HashMap<Integer, Integer> hashDice = new HashMap<>();
    private HashMap<String, Integer> scores = new HashMap<>();
    private int numDice;
    private int totalScore;

    public Section(int numDice)
    {
        this.numDice = numDice;
        this.totalScore = 0;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/11/22;
     * Date last modified: 2/11/22
     * @Description simple getter method for giving away lowerScores hashmap
     * @return the scores HashMap
     * @pre unknown lower section scores
     * @post known/returned lower section scores
     **/
    public HashMap<String, Integer> getScores()
    {
        return scores;
    }

    public void setTotalScore(int score)
    {
        this.totalScore = score;
    }

    public void setHashDice(HashMap<Integer, Integer> newHashDice)
    {   
        this.hashDice = newHashDice;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/31/22;
     * Date last modified: 1/31/22
     * @Description calclulates the score of potential full houses
     * @pre uncalculated score for full house
     * @post calculated score for full house
     **/
    public void calculateFullHouse()
    {
        boolean hasFullHouse = false;
        int minDie = Integer.MIN_VALUE;
        int secondMinDie = Integer.MIN_VALUE;

        for(int value : hashDice.values())
        {
            if(value >= 3 && secondMinDie < 3)
            {
                secondMinDie = value;
            }

            else if(value >= 2)
            {
                minDie = value;
            }
        }
        hasFullHouse = secondMinDie >= 3 && minDie >= 2;
        //checking for full house
        if(hasFullHouse)
        {
            scores.put(SectionNames.FULLHOUSE.name(), 25);
        }
        else
        {
            scores.put(SectionNames.FULLHOUSE.name(), 0);
        }

    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22; 
     * Date last modified: 1/31/22
     * @Description calculates all of the 'of a kind' elements and adds them to the hashmap of scores
     * @pre uncalculated scores from the dice held in hand
     * @post calculated scores from dice held in hand
     **/
    public void calculateOfKind()
    {
        int numDiceValue;
        int maxKind = 0;

        //this is for finding how many of a kind there are
        for(int check : hashDice.keySet())
        {
            numDiceValue = hashDice.get(check);
            if(numDiceValue > maxKind)
            {
                maxKind = numDiceValue;
            }
        }

        //if there is an of a kind
        if(maxKind >= 3)
        {
            //if there is a yahtzee
            if(maxKind == numDice)
            {
                scores.put(SectionNames.YAHTZEE.name(), 50);
            }
            else
            {
                scores.put(SectionNames.YAHTZEE.name(), 0);
            }
            while(maxKind >= 3)
            {
                scores.put("OFKIND" + maxKind, totalScore);
                maxKind --;
            }
            
        }

        //if no straight and no yahtzee
        else
        {
            scores.put("OFKIND" + maxKind, 0);
            scores.put(SectionNames.YAHTZEE.name(), 0);
        }
        //inputs the chance lower part
        scores.put(SectionNames.CHANCE.name(), totalScore);
    }


    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22; 
     * Date last modified: 1/31/22
     * @Description calculates all of the straight scores from the hand of dice
     * @pre uncalculated straight scores from hand
     * @post calculates straight scores from hand
     **/
    public void calculateStraight()
    {
        String diceRoll = "";
        int lengthStraight = 1;

        //adds all the numbers as a string of numbers together
        for(int num : hashDice.keySet())
        {
            diceRoll += Integer.toString(num);
        }

        //goes through to track longest straight
        for(int i = 0; i < diceRoll.length() - 1; i++)
        {
            if(diceRoll.charAt(i) + 1 == diceRoll.charAt(i + 1))
            {
                lengthStraight ++;
            }
        }
        //if there are as many dice in a row as there are dice
        if(lengthStraight == numDice)
        {
            scores.put(SectionNames.LGSTRAIGHT.name(), 40);
            scores.put(SectionNames.SMSTRAIGHT.name(), 30);
        }
        //else if there is all but one dice in a row
        else if(lengthStraight == numDice - 1)
        {
            scores.put(SectionNames.LGSTRAIGHT.name(), 0);
            scores.put(SectionNames.SMSTRAIGHT.name(), 30);
        }
        else
        {
            scores.put(SectionNames.LGSTRAIGHT.name(), 0);
            scores.put(SectionNames.SMSTRAIGHT.name(), 0);
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/11/22; 
     * Date last modified: 2/11/22
     * @Description Calculates the upper section of the game scorecard
     * @pre uncalculated and unadded upper section
     * @post calculated and added upper section
     **/
    public void calculateUpperSection()
    {
        //for loop going through all the dice, this is for calculating upper section
        for(int dieValue : hashDice.keySet())
        {
            scores.put(String.valueOf(dieValue), dieValue * hashDice.get(dieValue));
        }        
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22;
     * Date last modified: 2/11/22
     * @Description converts all of the dice in the hand to the hashmap for easy comparison later
     * @param dice the dice to be converted to a hashmap
     * @pre empty hashmap
     * @post hashmap filled with dice values
     **/
    public void diceToHash(ArrayList<Die> dice)
    {
        //sorts the player's hand in descending order for access of hashMap
        Collections.sort(dice);
        Collections.reverse(dice);

        //for loop going through hand of dice
        for(int i = 0; i < dice.size(); i++)
        {
            int side = dice.get(i).getSideUp();

            //if hashmap doesn't contain dice side
            if(!this.hashDice.containsKey(side))
            {
                this.hashDice.put(side, 1);
            }
            //else increment number of keys
            else
            {
                this.hashDice.replace(side, this.hashDice.get(side) + 1);
            }
        }
    }    

    /**
     * @Author Joshua Venable
     * @Date created: 1/31/22; 
     * Date last modified: 1/31/22
     * @Description simply clears out the score hashmap
     * @pre uncleared hashmap
     * @post cleared hashmap
     **/
    public void flushScores()
    {
        this.scores.clear();
        this.hashDice.clear();
    }
}
