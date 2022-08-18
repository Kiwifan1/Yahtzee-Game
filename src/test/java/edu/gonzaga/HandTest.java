package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {
    @Test
    void testRoll() {
        Boolean expectedValue = true;
        int sum = 0;
        Hand hand = new Hand(new GameConfiguration());
        hand.rollAll();
        for (Die die : hand.getDice()) {
            sum += die.getSideUp();
        }
        assertEquals(expectedValue, sum > 6);
    }

    @Test
    void testUnlockAll() {
        Boolean expectedValue = false;
        Hand hand = new Hand(new GameConfiguration());
        for (Die die : hand.getDice()) {
            die.lock();
        }
        hand.unlockAllDie();
        for (Die die : hand.getDice()) {
            assertEquals(expectedValue, die.isLocked());
        }
    }

}
