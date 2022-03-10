/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 1/24/22
 * Programming Assigment: Yahtzee HW1
 * Description: To build and implement the game of yahtzee in Java
 * Notes: 
 *  1/24/22 met with Dr. Crandall, moved dice/hand to a new class
 * 
 * NOTE: For the Full House and Straight calculations, I changed them from hardcoded numbers to dynamically changing ones.
 *        Full House will be any set of numbers where there are more than 2 instances, and 3 instances.
 *        A Small Straight is calculated as the number of dice - 1, while a Large Straight is the number of dice
 **/


package edu.gonzaga;

import java.util.ArrayList; //for holding dice
import java.util.HashMap; //for holding hashMap of dice
public class Hand {

    private Integer numberDice = 5;
    private ArrayList<Die> dice = new ArrayList<>();
    private HashMap<Integer, Integer> hashDice = new HashMap<>();
    private Section section;

    public Hand()
    {
        populateHands(numberDice);
        section = new Section(numberDice);
    }
    public Hand(int numDice)
    {
        numberDice = numDice;
        populateHands(numberDice);
        section = new Section(numberDice);
    }
    public Hand(ArrayList<Integer> diceInfo)
    {
        numberDice = diceInfo.get(1);
        populateHands(diceInfo.get(1), diceInfo.get(0));
        section = new Section(numberDice);
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/24/22;
     * Date last modified: 1/24/22
     * @Description simple getter method that returns the hand of dice
     * @return the hand of dice as an ArrayList<Die>
     * @pre unknown/ungotten dice hand
     * @post retrieved dice hand
     **/
    public ArrayList<Die> returnDice()
    {
        return this.dice;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/27/22; 
     * Date last modified: 1/27/22
     * @Description simple getter method for seing total number of dice
     * @return the number of dice as an integer
     * @pre unknown number of dice in hand
     * @post known number of dice in hand
     **/
    public int getNumDice()
    {
        return this.numberDice;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/27/22; 
     * Date last modified: 1/27/22
     * @Description simple getter method for getting total score of dice
     * @return total score of dice as an integer
     * @pre unknown score of dice in hand
     * @post known score of dice in hand
     **/
    public int getTotal()
    {
        return diceScore();
    }


    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22;
     * Date last modified: 1/24/22
     * @Description populated the player's starting hand with a number of sets of dice
     * @param numDice the number of dice that the player will start with
     * @pre empty array list of dice
     * @post filled arraylist of preset hands of dice
     **/
    private void populateHands(int numDice)
    {
        for(int i = 0; i < numDice; i ++)
        {
            Die newDie = new Die();
            this.dice.add(newDie);
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22;
     * Date last modified: 1/27/22
     * @Description populated the player's starting hand with a number of sets of dice
     * @param numDice the number of dice that the player will start with
     * @pre empty array list of dice
     * @post filled arraylist of preset hands of dice
     **/
    private void populateHands(int numDice, int numSides)
    {
        for(int i = 0; i < numDice; i ++)
        {
            Die newDie = new Die(numSides);
            this.dice.add(newDie);
        }
    }


    /**
     * @Author Joshua Venable
     * @Date created: 1/24/22; 
     * Date last modified: 1/24/22
     * @Description rolls all of the dice in the hand
     * @pre the dice in their previous position
     * @post dice rolled into their new random position
     **/
    public void rollDice()
    {
        rollDice("\0");
    }    

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22; 
     * Date last modified: 1/24/22
     * @Description rolls all of the dice in the hand
     * @param rollDice the dice that a player wants rolled or not rolled
     * @pre the dice in their previous position
     * @post dice chosen to roll in their new random position
     **/
    public void rollDice(String rollDice)
    {
        //if more than the wanted amount of dice
        if(rollDice.length() > numberDice)
        {
            rollDice = rollDice.substring(0, numberDice + 1);
        }
        //for loop going through the dice within the hand
        for(int die = 0; die < rollDice.length(); die++)
        {
            //if they don't say 'y' it will roll, prefer this to making sure they say 'n' since it defaults to rolling
            if(!rollDice.substring(die, die + 1).equalsIgnoreCase("y"))
            {
                this.dice.get(die).roll();
            }
        }
        //error handling if they don't have all letters put in
        if(rollDice.length() < numberDice)
        {
            if(!rollDice.equals("\0"))
            {
                System.out.println("\nYou didn't put all dice in! Rolling unchosen dice!");
            }
            
            for(int die = rollDice.length(); die < numberDice; die++)
            {
                this.dice.get(die).roll();
            }
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22;
     * Date last modified: 1/31/22
     * @Description calculates all the upper scores of Yahtzee, and most of the lower
     * @return a hashmap of all scores 
     * @pre unknown/uncalculated total scores
     * @post known/calculated total score
     **/
    public HashMap<String, Integer> calculateScores()
    {
        int diceTotal;
        HashMap<String, Integer> scores;
        
        //flush out hashmaps so you can read scores for multiple runs
        this.section.flushScores();

        diceTotal = diceScore();

        section.setTotalScore(diceTotal);
        section.diceToHash(dice);
        
        //calculates lower section
        section.calculateFullHouse();
        section.calculateOfKind();
        section.calculateStraight();

        //calculates upper section
        section.calculateUpperSection();


        scores = section.getScores();

        hashDice.clear();

        return scores;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/15/22
     * Date last modified: 1/24/22
     * @Description Calculates the total score for the dice values of the hand
     * @return the total score of all the dice as an integer
     * @pre unknown/uncalculated value total for dice
     * @post known/calculated value total for dice
     **/
    private int diceScore()
    {
        int score = 0;
        for(Die i : this.dice)
        {
            score += i.getSideUp();
        }
        return score;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/2/22; 
     * Date last modified: 2/2/22
     * @Description simply sets the dice in the hand to the passed dice
     * @param newDice the new dice to be set to this hand's
     * @pre old hand's dice set as this.dice
     * @post new dice set as this.dice
     **/
    public void setDice(ArrayList<Die> newDice)
    {
        this.dice = newDice;
    }
}
