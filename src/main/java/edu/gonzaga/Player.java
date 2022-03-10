/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 1/14/22
 * Programming Assigment: Yahtzee HW1
 * Description: To build and implement the game of yahtzee in Java
 * Notes: 
 *  1/15/22 - changed constructor
 *  2/14/22 - need to fix how things are printed, going well
 * 
 **/

package edu.gonzaga;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; //for holding the hand of dice
import java.util.Scanner; //for getting user input
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Player {
    private Hand hand; //hand object that can do all the score totaling
    private ScoreCard scoreCard;
    private ArrayList<Integer> diceData; //index 0 is sides per die, 1 is number of dice, 2 is number of rerolls 
    
    //constructors
    public Player(ArrayList<Integer> diceInfo)
    {
        this.diceData = diceInfo;
        this.hand = new Hand(diceInfo);
        this.scoreCard = new ScoreCard(diceData.get(0), this.hand.getNumDice() - 3);
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22;
     * Date last modified: 1/27/22
     * @Description the dice are rolled and player is asked if they want to switch anything as per yahtzee
     * @pre preset dice from player's hand
     * @post dice rolled randomly from 1-3 times
     **/
    public void playGame()
    {
        int numRolled = 1;
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> scores;
        String noMoreRoll = "";

        this.hand.rollDice();
        System.out.print("Your roll was: ");
        this.printDice();

        //creates the end condition if player wants to stop rolling dice
        for(int i = 0; i < diceData.get(1); i++)
        {
            noMoreRoll += "y";
        }
        //while player wants to keep rolling
        while(numRolled < diceData.get(2))
        {
            System.out.print("\nEnter dice to keep (y or n) ");
            userInput = scanner.nextLine();
            
            //if player does not want to roll anything can quit prematurely

            if(userInput.contains(noMoreRoll))
            {
                break;
            }

            this.hand.rollDice(userInput);
            System.out.print("\nYour roll was: ");
            this.printDice();

            System.out.println();

            numRolled ++;
        }

        scores = hand.calculateScores();

        getUserChoice(scores);
        this.scoreCard.gameEnd();
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/14/22;
     * Date last modified: 2/17/22
     * @Description sets the possible scores into the ScoreCard instance
     * @param scores the scores to be put in the scoreCard
     * @pre only initialized scoreCardLines in the scoreCard
     * @post scoreCardLines with possible scores in scoreCard
     **/
    public void setPossibleScores(HashMap<String, Integer> scores)
    {
        //for every line in the scoreCard
        for(ScoreCardLine newLine : this.scoreCard)
        {
            if(scores.keySet().contains(newLine.toString()))
            {
                newLine.setPossibleScore(scores.get(newLine.toString()));
            }
            //if scores does not have newLine, then the score will be 0
            else
            {
                newLine.setPossibleScore(0);
            }
        }
    }
    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22;
     * Date last modified: 2/13/22
     * @Description prints out the possible scores a player can have
     * @param scores an hashmap of all the scores from the game round
     * @pre unshown all possibilities of points for yahtzee
     * @post shown all possibilities of points for yahtzee
     **/
    private void printScorePossibilities(HashMap<String, Integer> scores)
    {
        System.out.println();
        printUpperSection(scores);
        printLowerSection(scores);
    }

    private void printUpperSection(HashMap<String, Integer> scores)
    {
        //printing for upper section
        String dieString;
        ScoreCardLine line;
        //for every dice
        for(int die = 1; die <= diceData.get(0); die++)
        {
            //if dice line hasn't already been picked
            line = scoreCard.getLine(String.valueOf(die));
            if(line != null && !line.hasBeenPicked())
            {
                dieString = String.valueOf(die);
                //if dice line has score
                if(scores.containsKey(dieString))
                {
                    System.out.println("Possible score: " + scores.get(dieString) + " on the " + dieString + " line");
                }
                //if dice line has no score
                else
                {
                    System.out.println("Possible score: 0 on the " + dieString + " line");
                }
            }
        }
    }

    private void printLowerSection(HashMap<String, Integer> scores)
    {
        ScoreCardLine line;
        boolean alreadyPicked = false;
        String lowerSection;
        //printing lower section
        //for loop going from 3 of a kind to 1 less than a yahtzee
        for(int i = 3; i < this.hand.getNumDice(); i++)
        {
            line = scoreCard.getLine("OFKIND" + i);

            //if dice line hasn't already been picked to hold a score
            if(line != null && !line.hasBeenPicked())
            {
                //if there is a score that constitutes an 'ofkind' >= 3
                if(scores.get("OFKIND" + i) != null)
                {
                    System.out.println("Possible score: " + scores.get("OFKIND" + i) + " on the " + i + " of a Kind line");
                }
                else
                {
                    System.out.println("Possible score: 0 on the " + i + " of a Kind line");
                }
            }
        }

        // for looking at the unique lower section scores, go through each enum
        for(SectionNames lowerSectionName : SectionNames.values())
        {
            line = scoreCard.getLine(lowerSectionName.name());
            alreadyPicked = line.hasBeenPicked();

            //if the line hasn't already been picked to hold a score
            if(line != null && !alreadyPicked)
            {
                lowerSection = lowerSectionName.name();
                switch (lowerSectionName.name()) {
                    case "FULLHOUSE":
                        System.out.println("Possible score: " + scores.get(lowerSection) + " on the Full House line");
                        break;
                    case "SMSTRAIGHT":
                        System.out.println("Possible score: " + scores.get(lowerSection) + " on the Small Straight line");
                        break;
                    case "LGSTRAIGHT":
                        System.out.println("Possible score: " + scores.get(lowerSection) + " on the Large Straight line");
                        break;
                    case "YAHTZEE":
                        System.out.println("Possible score: " + scores.get(lowerSection) + " on the Yahtzee line");
                        break;
                    case "CHANCE":
                        System.out.println("Possible score: " + scores.get(lowerSection) + " on the Chance line");
                        break;
                    default:
                        break;
                }
            }
        }          
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/13/22; 
     * Date last modified: 2/13/22
     * @Description counts up the total amount of sections
     * @return the number of sections as an integer
     * @pre unknown amount of sections
     * @post known amount of sections
     **/
    public int getNumSections()
    {
        int numberSections = 0;
        numberSections += this.hand.getNumDice(); //upper section
        numberSections += (this.hand.getNumDice() - 3); // of kind lower section
        numberSections += 5; // lower section uniques
        return numberSections;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/14/22; 
     * Date last modified: 1/24/22
     * @Description prints out the dice in the player's hand
     * @pre unprinted player's hand of dice
     * @post printed player's hand of dice
     **/
    private void printDice()
    {
        ArrayList<Die> list = hand.returnDice();
        //for loop that goes through all the dice in a hand
        for(int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }
    }
    
    private boolean checkUserInputValidity(String userAnswer)
    {
        String userAnswerFirstLetter;
        boolean isValid = false;
        String[] validAnswers = {"FULLHOUSE", "SMSTRAIGHT", "LGSTRAIGHT", "YAHTZEE", "CHANCE"};
        //if the user has input a character thats a number
        if(userAnswer.length() == 1 && Character.isDigit(userAnswer.charAt(0)))
        {
            userAnswerFirstLetter = userAnswer.substring(0, 1);
            //if user has input a number thats within bounds of sides of die
            if(Integer.parseInt(userAnswerFirstLetter) <= diceData.get(0))
            {
                isValid = true;
            }
        }
        //if user chose ofkind
        else if(userAnswer.contains("OFKIND"))
        {
            // if user length is larger than 'ofkind'
            if(userAnswer.length() > 6)
            {
                int numOfKind = Character.getNumericValue(userAnswer.charAt(userAnswer.length() - 1));
                //if numOfkind is in-bounds for the game instance
                if(numOfKind > 1 && numOfKind <= diceData.get(1) - 1)
                {
                    isValid = true;
                }
                else
                {
                    isValid = false;
                }
            }
            else
            {
                isValid = false;
            }
        }
        //if user chose one of the unique ones
        else
        {
            for(String knownWord : validAnswers)
            {
                if(knownWord.equals(userAnswer))
                {
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/13/22;
     * Date last modified: 2/21/22
     * @Description gets the user choice from input and makes sure its in correct format
     * @param scores the scores as a hashmap to be printed out and have a user choose from
     * @pre unknown what user wants to add to score
     * @post known what user wantds to add to score
     **/
    public void getUserChoice(HashMap<String, Integer> scores)
    {
        Scanner input = new Scanner(System.in);
        ScoreCardLine line;
        String userAnswer;
        boolean isValid = false;
        setPossibleScores(scores);
        printScorePossibilities(scores);

        System.out.println();
        System.out.println("Please Choose a line to put your score in (<number>; FULLHOUSE; OFKIND<number>; SMSTRAIGHT; LGSTRAIGHT; YAHTZEE; CHANCE): ");
        userAnswer = input.nextLine().toUpperCase();
        isValid = checkUserInputValidity(userAnswer);
        if(!isValid)
        {
            System.out.println("Invalid Input! Choosing first possible option...");
            ScoreCardLine firstUnplayed = scoreCard.getUnplayed();
            if(firstUnplayed != null)
            {
                addScore(firstUnplayed.toString());
            }
        }
        else
        {
            line = getScoreCardLine(userAnswer.toUpperCase());
        
            //while user tries to pick a place that already has been picked
            while(line.hasBeenPicked())
            {
                System.out.println("That's already been picked!  Please Choose a line to put your score in ([number]; FULLHOUSE; OFKIND[number]; SMSTRAIGHT; LGSTRAIGHT; YAHTZEE; CHANCE): ");
                userAnswer = input.nextLine().toUpperCase();
                isValid = checkUserInputValidity(userAnswer);
                //checking validity
                if(!isValid)
                {
                    ScoreCardLine firstUnplayed = scoreCard.getUnplayed();
                    if(firstUnplayed != null)
                    {
                        addScore(firstUnplayed.toString());
                    }
                }
                else
                {
                    line = getScoreCardLine(userAnswer);
                }
            }
        
            addScore(userAnswer);
        }
    }
    
    /**
     * @Author Joshua Venable
     * @Date created: 2/13/22; 
     * Date last modified: 2/17/22
     * @Description adds the given score to the player's scorecard, so the totals can be tallied up
     * @param userChoice the user's choice on where to put his points, held as a string
     * @pre given user choice on what score to add points to 
     * @post the scoreCard updated to reflect the score w/ points
     **/
    public void addScore(String userChoice)
    {

        for(ScoreCardLine i : scoreCard)
        {
            if(i.toString().equals(userChoice))
            {
                i.setFinalScore();
                break;
            }
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/17/22; 
     * Date last modified: 2/17/22
     * @Description adds the given score to the player's scorecard, so the totals can be tallied up
     * @param userChoice the user's choice on where to put his points, held as a string
     * @param score the new score to put in the scoreCardLine
     * @pre given user choice on what score to add points to 
     * @post the scoreCard updated to reflect the score w/ points
     **/
    public void addScore(String userChoice, int score)
    {
        for(ScoreCardLine i : scoreCard)
        {
            if(i.toString().equals(userChoice))
            {
                i.setFinalScore(score);
                break;
            }
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/14/22; 
     * Date last modified: 2/14/22
     * @Description getter method for getting a scoreCardLine object from the player's scoreCard
     * @param id the id string to be checked for
     * @return the scoreCardLine object if found
     * @pre unknown if scoreCardLine in object
     * @post returned scoerCardLine object if in player class
     **/
    public ScoreCardLine getScoreCardLine(String id)
    {
        for(ScoreCardLine i : this.scoreCard)
        {
            if(i.toString().equals(id))
            {
                return i;
            }
        }
        return null;
    }

    public ScoreCard getScoreCard() { return this.scoreCard; }
}
