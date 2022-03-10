/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 2/17/22
 * Programming Assigment: Yahtzee HW1
 * Description: To build and implement the game of yahtzee in Java
 * Notes: 
 *  2/17/22 - initialized
 * 
 **/

package edu.gonzaga;

import java.util.ArrayList;
import java.util.Scanner; //for user input
import java.io.BufferedReader; //for file reading
import java.io.BufferedWriter; //for file writing
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;


public class GameControl {
    //private int numSides;
    private int numDice;
    //private int numRolls;
    
    /**
     * @Author Joshua Venable
     * @Date created: 1/16/22;
     * Date last modified: 2/17/22
     * @Description plays the game of Yahtzee
     * @pre unplayed game
     * @post played game and user has decided to not play anymore
     **/
    public void playGame() {
        try {
            File file = new File("yahtzeeConfig.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<Integer> fileInfo = readFile(reader);
            String ans;
            Player player1;
            Scanner scanner = new Scanner(System.in); 

            fileInfo = changeDiceConfiguration(scanner, fileInfo, file);
            //gets the number of sides, dice and rolls
            //this.numSides = fileInfo.get(0);
            this.numDice = fileInfo.get(1);
            //this.numRolls = fileInfo.get(2);

            player1 = new Player(fileInfo);
            ans = "y";

            System.out.println("Welcome to Yahtzee! Lets Play!");
            //continues as long as player wants to continue playing
            while (ans.equals("y")) {
                System.out.print("Press 'S' if you would like to see the Score Card: ");
                ans = scanner.nextLine();
                // if player wants to look at the scoreCard
                if(ans.equals("S"))
                {
                    printOutScoreCard(player1);
                }
                System.out.println();
                player1.playGame();
                System.out.print("\nEnter 'y' to play again: ");
                ans = scanner.nextLine();

                //empty line handling
                if(ans.equals(""))
                {
                    break;
                }
                else
                {
                    ans = ans.toLowerCase().substring(0, 1);
                }
                if(player1.getScoreCard().getUnplayed() == null)
                {
                    System.out.println("You are all out of possible sections to put scores in!");
                    break;
                }
            }

            System.out.println("Thank you for playing! Here's your final Score:");

            printOutScoreCard(player1);
            reader.close();
            scanner.close();
        } 
        catch (Exception e) 
        {
            System.out.println("File not found (or I/O exception)! Something is wrong! Path file is somehow messed up.");
            System.out.print(e.getStackTrace());
        }

    }

    /**
     * @Author Joshua Venable
     * @Date created: 1/27/22;
     * Date last modified: 1/27/22
     * @Description reads and grabs the data from file for seeing how many 
     * @param reader a BufferedReader object used primarily in java for file Input
     * @return an arrayList of the information garnered from file
     * @pre unread file at file path previously grabbed
     * @post read file with data stored in arraylist for access
     **/
    public ArrayList<Integer> readFile(BufferedReader reader)
    {
        try 
        {
            ArrayList<Integer> fileData = new ArrayList<>();
            String line = reader.readLine();
            line = line.trim();

            //while haven't reached end of  file
            while(line != null && !line.equals(""))
            {
                fileData.add(Integer.valueOf(line));
                line = reader.readLine();
            }
            return fileData;
        } 

        catch (IOException e) 
        {
            System.out.println("Error in reading the file! Fix this issue!");
            System.out.println(e.getStackTrace());
            return new ArrayList<>();
        }
    }    

    /**
     * @Author Joshua Venable
     * @Date created: 2/1/22; 
     * Date last modified: 2/2/22
     * @Description writes to the yahtzee Configuration file, to change aspects of the game to user wishes
     * @param writer the BufferedWriter object used for parsing and writing to file
     * @param userRespone the user's response as a String for the writer to put in the file
     * @pre previously written data in file
     * @post overwritten data in file
     **/
    public void writeFile(File file, String userResponse)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String[] answers = userResponse.split(",");
            //going through each user answer to questions
            for(int i = 0; i < answers.length; i ++)
            {
                writer.write(answers[i]);
                writer.newLine();
            }
            writer.flush();
            writer.close();
        } 
        catch (IOException e)
        {
            System.out.println("Error in writing the file! Fix this issue!");
            System.out.println(e.getStackTrace());
        }
        
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/17/22; 
     * Date last modified: 2/17/22
     * @Description Asks the user if they want to change the dice configuration, and if so, changes it
     * @param scanner the scanner to read user input
     * @param fileInfo the fileInfo that may or may not be changed
     * @param file the file name to read from
     * @return an ArrayList<Integer> of the new fileInfo information
     * @pre preivously read fileInfo
     * @post newly read and written fileInfo
     **/
    private ArrayList<Integer> changeDiceConfiguration(Scanner scanner, ArrayList<Integer> fileInfo, File file)
    {
        try {
            String ans;
            String temp = "";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // checks whether user wants to use different die information
            System.out.println("You are playing with " + fileInfo.get(1) + " " + fileInfo.get(0) + "-sided dice");
            System.out.println("You get " + fileInfo.get(2) + " rolls per hand.\n");
            System.out.print("Enter 'y' if you would like to change the configuration: ");
            ans = scanner.nextLine();
            //if user wants to change configuration
            if(ans.equals(""))
            {
                fileInfo = readFile(reader);
                return fileInfo;
            }
            else if(ans.toLowerCase().substring(0, 1).equals("y"))
            {
                ans = "";
                
                //finding the number of sides
                System.out.print("\nEnter the number of sides on each dice: ");
                temp = scanner.nextLine();
                if(temp.equals("") || !Character.isDigit(temp.charAt(0)))
                {
                    ans += "6,";
                }
                else
                {
                    ans += temp.substring(0, 1) + ",";
                }

                //finding the number of dice
                System.out.print("\nEnter the number of dice in play: ");
                temp = scanner.nextLine();
                if(temp.equals("") || !Character.isDigit(temp.charAt(0)))
                {
                    ans += "5,";
                }
                else
                {
                    ans += temp + ",";
                }

                //finding the number of rolls
                System.out.print("\nEnter the number of rolls per hand: ");
                temp = scanner.nextLine();
                if(temp.equals("") || !Character.isDigit(temp.charAt(0)))
                {
                    ans += "3";
                }
                else
                {
                    ans += temp;
                }
                
                writeFile(file, ans);
                    
                //resets reader to go through file again
                fileInfo = readFile(reader);
            }
            return fileInfo;            
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error in changing dice configuration!");
            e.printStackTrace();
            return fileInfo;
        }
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/17/22;
     * Date last modified: 2/17/22
     * @Description prints out the player's score card
     * @param player the player's score card to be printed out
     * @pre unprinted score card
     * @post printed score card
     **/
    private void printOutScoreCard(Player player)
    {
        ScoreCard scoreCard = player.getScoreCard();
        System.out.println("\nScore Card:");
        for(ScoreCardLine scoreLine : scoreCard)
        {
            // if its an individual score
            if(scoreLine.toString().length() <= 1)
            {
                System.out.println("Section: " + scoreLine.toString() + "\t\t| Score: " + scoreLine.getScore());
            }

            // if its a lower section score
            else
            {
                printLowerSection(scoreLine);
            }
        }
        //prints out total score
        System.out.println("Total Score: " + scoreCard.getTotalScore());
    }

    /**
     * @Author Joshua Venable
     * @Date created: 2/17/22; 
     * Date last modified: 2/17/22
     * @Description prints out the lower sections, including 'of a kind'
     * @param scoreLine the scoreLine to read out the scores
     * @pre unprinted out scores when user wants to
     * @post printed out scores when user had asked to
     **/
    private void printLowerSection(ScoreCardLine scoreLine)
    {
        //for the 'of a kind'
        for(int i = 3; i < this.numDice; i++)
        {
            //if there is a score that constitutes an 'ofkind' >= 3
            if(scoreLine.toString().equals("OFKIND" + i))
            {
                System.out.println("Section: " + i + " of a Kind\t" + "| Score: " + scoreLine.getScore());
            }
        }

        //for every section name in the enums
        for(SectionNames lowerSectionName : SectionNames.values())
        {

            //if the scoreCardLine name is the same as the sectionName then look for which to print out
            if(scoreLine.toString().equals(lowerSectionName.name()))
            {
                switch (lowerSectionName.name())
                {
                    case "FULLHOUSE":
                        System.out.println("Section: Full house\t| Score: " + scoreLine.getScore());
                        break;
                    case "SMSTRAIGHT":
                        System.out.println("Section: Small Straight\t| Score: " + scoreLine.getScore());
                        break;
                    case "LGSTRAIGHT":
                        System.out.println("Section: Large Straight\t| Score: " + scoreLine.getScore());
                        break;
                    case "YAHTZEE":
                        System.out.println("Section: Yahtzee\t| Score: " + scoreLine.getScore());
                        break;
                    case "CHANCE":
                        System.out.println("Section: Chance\t\t| Score: " + scoreLine.getScore());
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
