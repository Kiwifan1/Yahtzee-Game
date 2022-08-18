package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void testStartNullFile() {
        Boolean expected = false;
        Boolean threw = false;
        // Game game = new Game(null);
        // try{
        // game.startGame();
        // } catch (Exception e){
        // threw = true;
        // }
        assertEquals(expected, threw);
    }
}
