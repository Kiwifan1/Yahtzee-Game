/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 2/13/22
 * Programming Assigment: Yahtzee HW3
 * Description: To build and implement the game of yahtzee in Java
 * Notes: 
 *  
 * 
 **/

package edu.gonzaga;

public class ScoreCardLine {
    private String id;
    private boolean beenPicked;
    private int possibleScore;
    private int score;

    public ScoreCardLine()
    {
        this.possibleScore = 0;
        this.score = 0;
        this.beenPicked = false;
    }

    public ScoreCardLine(String id)
    {
        this.id = id;
        this.possibleScore = 0;
        this.score = 0;
        this.beenPicked = false;
    }

    public ScoreCardLine(String id, int possibleScore)
    {
        this.id = id;
        this.possibleScore = possibleScore;
        this.score = 0;
        this.beenPicked = false;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/11/22; 
     * Date last modified: 2/11/22
     * @Description simple setting method for possible scores
     * @param possibleScore an integer noting the possible score a player could receive
     * @pre unset possible score
     * @post set possible score
     **/
    public void setPossibleScore(int possibleScore)
    {
        this.possibleScore = possibleScore;
    }


    /**
     * @Author Joshua Venable
     * @Date created: 2/11/22; 
     * Date last modified: 2/21/22
     * @Description sets the score if chosen to the possible score
     * @pre unset final score
     * @post set final score to the possible score and notified that its been picked
     **/
    public void setFinalScore()
    {
        this.score = possibleScore;
        this.beenPicked = true;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/11/22; 
     * Date last modified: 2/13/22
     * @Description sets the score to a new Score
     * @param newScore the new score thats going to be the final score
     * @pre unset final score
     * @post set final score to the new score and notified that its been picked
     **/
    public void setFinalScore(int newScore)
    {
        this.score = newScore;
        this.beenPicked = true;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/13/22; 
     * Date last modified: 2/13/22
     * @Description simple getter method for seeing if user has picked this scoreLine before
     * @return boolean on whether scoreLine instance has been picked
     * @pre unknown whether scoreLine instance has been picked
     * @post known whether scoreLine instance has been picked
     **/
    public boolean hasBeenPicked()
    {
        return this.beenPicked;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
    
    public int getScore()
    {
        return this.score;
    }

    public int getPossibleScore()
    {
        return this.possibleScore;
    }

    @Override
    public String toString()
    {
        return this.id;
    }

}
