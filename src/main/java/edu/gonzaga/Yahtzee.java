/**
 * Name: Joshua Venable
 * Class: CPSC 224, Spring 2022
 * Date: 1/14/22
 * Programming Assigment: Yahtzee HW1
 * Description: To build and implement the game of yahtzee in Java
 * Notes: 
 * 1/14/22 - Issue need to fix, if more than one set of doubles, smaller number taken
 * 1/27/22 working on HW2 now on new branch
 *  NOTE: For the Full House and Straight calculations, I changed them from hardcoded numbers to dynamically changing ones.
 *        Full House will be any set of numbers where there are more than 2 instances, and 3 instances.
 *        A Small Straight is calculated as the number of dice - 1, while a Large Straight is the number of dice
 * 2/17/22 - moved all methods to a new class to clean up main
 **/
package edu.gonzaga;
/*
*  This is the main class for the Yahtzee project.
*  It really should just instantiate another class and run
*   a method of that other class.
*/

/** Main program class for launching Yahtzee program. */
public class Yahtzee {
    public static void main(String[] args) {
        GameControl game = new GameControl();
        game.playGame();
    }

}
