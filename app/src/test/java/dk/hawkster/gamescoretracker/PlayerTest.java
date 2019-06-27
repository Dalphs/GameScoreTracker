package dk.hawkster.gamescoretracker;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import dk.hawkster.gamescoretracker.Model.Player;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlayerTest {
    @Test
    public void hashMapSetup() {
        Map<String, Double> expected = new HashMap<>();
        expected.put("Rummy", 0.0);
        expected.put("Whist", 0.0);
        expected.put("3 Person Whist", 0.0);
        expected.put("Pirate Whist", 0.0);
        expected.put("Backgammon", 0.0);
        assertEquals(expected, new Player().getAllTimeScores());
    }
}