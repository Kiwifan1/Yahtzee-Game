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

import java.util.ArrayList;

public class ScoreCard extends ArrayList<ScoreCardLine>{

    public ScoreCard(int numSides, int numOfKind)
    {
        for(int i = 1; i <= numSides; i ++)
        {
            this.add(new ScoreCardLine("" + i));
        }
        for(int i = 3; i < numOfKind + 3; i ++)
        {
            this.add(new ScoreCardLine("OFKIND" + i));
        }
        this.add(new ScoreCardLine(SectionNames.FULLHOUSE.name()));
        this.add(new ScoreCardLine(SectionNames.SMSTRAIGHT.name()));
        this.add(new ScoreCardLine(SectionNames.LGSTRAIGHT.name()));
        this.add(new ScoreCardLine(SectionNames.YAHTZEE.name()));
        this.add(new ScoreCardLine(SectionNames.CHANCE.name()));
    }

    @Override
    public String toString()
    {
        String line = "";
        for(ScoreCardLine i : this)
        {
            line += i.toString() + ", ";
        }
        return line;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/13/22; 
     * Date last modified: 2/13/22
     * @Description returns the scoreCardLine instance that matches the line id given
     * @param line the string of the id being searched for
     * @return the scoreCardLine if found, null otherwise
     * @pre uknown scoreCardLine in scoreCard
     * @post returned scoreCardLine in scoreCard
     **/
    public ScoreCardLine getLine(String line)
    {
        for(ScoreCardLine i : this)
        {
            if(i.toString().equals(line))
            {
                return i;
            }
        }
        return null;
    }


    /**
     * @Author Joshua Venable
     * @Date created: 2/17/22; 
     * Date last modified: 2/17/22
     * @Description resets the scores of the scoreCardLines for a new game
     * @pre previously scored scores 
     * @post fresh set of scoreCardLines with boolean values still set
     **/
    public void gameEnd()
    {
        for(ScoreCardLine i : this)
        {
            i.setPossibleScore(0);
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/13/22
     * Date last modified: 2/21/22
     * @Description calculates the user's total score, with adding a bonus score if upper section gets >= 63
     * @return the user's total score
     * @pre unknown total score
     * @post known total score
     **/
    public int getTotalScore()
    {
        int total = 0;
        int upperTotal = 0;
        
        //calculating all scores
        for(ScoreCardLine i : this)
        {
            if(i.toString().length() <= 1)
            {
                upperTotal += i.getScore();
            }
            total += i.getScore();
        }

        //adding upper section bonus
        if(upperTotal >= 63)
        {
            total += 35;
        }
        return total;
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/23/22; 
     * Date last modified: 2/23/22
     * @Description Returns the first instance of an unplayed scoreCardLine
     * @return the first unplayed scoreCardLine
     * @pre unknown first unplayed scoreCardLine
     * @post known first unplayed scoreCardLine
     **/
    public ScoreCardLine getUnplayed()
    {
        for(ScoreCardLine i : this)
        {
            if(!i.hasBeenPicked())
            {
                return i;
            }
        }
        return null;
    }
}
