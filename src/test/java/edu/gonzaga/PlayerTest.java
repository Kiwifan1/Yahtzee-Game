package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerTest {
    @Test
    void nextTurnTest() {
        Player player = new Player("name", new GameConfiguration());
        int oldTurn = player.getTurn();
        player.newTurn();
        assertNotEquals(oldTurn, player.getTurn());
    }
}
