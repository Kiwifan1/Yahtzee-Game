package edu.gonzaga;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScorecardLineTest implements PropertyChangeListener {

    private boolean triggered;

    @Test
    void testScore() {
        boolean expected = true;
        ScorecardLine line = new ScorecardLine("Aces");
        line.score();
        assertEquals(expected, line.isScored());
    }

    @Test
    void testPropertyChangeListener() {
        boolean expected = true;
        triggered = false;
        ScorecardLine line = new ScorecardLine("Aces");
        line.addPropertyChangeListener(this::propertyChange);
        line.score();

        assertEquals(expected, triggered);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("scored")) {
            triggered = true;
        }
    }

    @Test
    void testExceptionThrow() {
        ScorecardLine line = new ScorecardLine("Aces");
        line.score();
        assertThrows(IllegalStateException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                line.score();
            }
        });
    }
}
