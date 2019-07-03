package dk.hawkster.gamescoretracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Model.WhistRound;
import dk.hawkster.gamescoretracker.Viewmodel.RummyViewModel;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WhistRoundTest {

    @Test
    public void whistRoundCalculator7with7tricks() {
        WhistRound whistRound = new WhistRound(1, 2, 7,
                null, new int[]{7}, new int[]{1, 2});
        int[] expected = new int[]{1, 1, -1, -1};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7with10tricks() {
        WhistRound whistRound = new WhistRound(1, 2, 7,
                null, new int[]{10}, new int[]{1, 2});
        int[] expected = new int[]{2, 2, -2, -2};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7with9tricks() {
        WhistRound whistRound = new WhistRound(1, 2, 7,
                null, new int[]{9}, new int[]{1, 2});
        int[] expected = new int[]{2, 2, -2, -2};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7with6tricks() {
        WhistRound whistRound = new WhistRound(1, 2, 7,
                null, new int[]{6}, new int[]{1, 2});
        int[] expected = new int[]{-1, -1, 1, 1};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator9with8tricks() {
        WhistRound whistRound = new WhistRound(1, 2, 9,
                null, new int[]{8}, new int[]{1, 2});
        int[] expected = new int[]{-3, -3, 3, 3};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator9with6tricks() {
        WhistRound whistRound = new WhistRound(1, 2, 9,
                null, new int[]{6}, new int[]{1, 2});
        int[] expected = new int[]{-9, -9, 9, 9};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7with10tricksOnly1Winner() {
        WhistRound whistRound = new WhistRound(1, 2, 7,
                null, new int[]{10}, new int[]{1});
        int[] expected = new int[]{6, -2, -2, -2};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7with10tricksAndClubs() {
        WhistRound whistRound = new WhistRound(1, 4, 7,
                null, new int[]{10}, new int[]{1, 2});
        int[] expected = new int[]{4, 4, -4, -4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7halves10tricks() {
        WhistRound whistRound = new WhistRound(3, 2, 7,
                null, new int[]{10}, new int[]{1, 2});
        int[] expected = new int[]{4, 4, -4, -4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7halves10tricksAndClubs() {
        WhistRound whistRound = new WhistRound(3, 4, 7,
                null, new int[]{10}, new int[]{1, 2});
        int[] expected = new int[]{8, 8, -8, -8};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator7Whip10tricksWhipped3TimesAndClubs() {
        WhistRound whistRound = new WhistRound(4, 4, 7,
                3, new int[]{10}, new int[]{1, 2});
        int[] expected = new int[]{32, 32, -32, -32};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator9Whip10tricksWhipped3TimesAndClubs() {
        WhistRound whistRound = new WhistRound(4, 4, 9,
                3, new int[]{10}, new int[]{1, 2});
        int[] expected = new int[]{48, 48, -48, -48};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }


    @Test
    public void whistRoundCalculator10Whip13tricksWhipped3TimesAndClubs() {
        WhistRound whistRound = new WhistRound(4, 4, 10,
                3, new int[]{13}, new int[]{1, 2});
        int[] expected = new int[]{256, 256, -256, -256};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }


    @Test
    public void whistRoundCalculatorSunWon() {
        WhistRound whistRound = new WhistRound(5, null, null,
                null, new int[]{1}, new int[]{1});
        int[] expected = new int[]{6, -2, -2, -2};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorSunLost() {
        WhistRound whistRound = new WhistRound(5, null, null,
                null, new int[]{2}, new int[]{1});
        int[] expected = new int[]{-6, 2, 2, 2};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorSunOnTableWon() {
        WhistRound whistRound = new WhistRound(6, null, null,
                null, new int[]{0}, new int[]{1});
        int[] expected = new int[]{12, -4, -4, -4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorSunOnTableLost() {
        WhistRound whistRound = new WhistRound(6, null, null,
                null, new int[]{2}, new int[]{1});
        int[] expected = new int[]{-12, 4, 4, 4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorCleanSunWon() {
        WhistRound whistRound = new WhistRound(7, null, null,
                null, new int[]{0}, new int[]{1});
        int[] expected = new int[]{12, -4, -4, -4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorCleanSunLost() {
        WhistRound whistRound = new WhistRound(7, null, null,
                null, new int[]{1}, new int[]{1});
        int[] expected = new int[]{-12, 4, 4, 4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorCleanSunOnTableWon() {
        WhistRound whistRound = new WhistRound(8, null, null,
                null, new int[]{0}, new int[]{1});
        int[] expected = new int[]{18, -6, -6, -6};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorCleanSunOnTableLost() {
        WhistRound whistRound = new WhistRound(8, null, null,
                null, new int[]{1}, new int[]{1});
        int[] expected = new int[]{-18, 6, 6, 6};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorMultipleSunsWon() {
        WhistRound whistRound = new WhistRound(5, null, null,
                null, new int[]{1, 1}, new int[]{1, 2});
        int[] expected = new int[]{4, 4, -4, -4};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorMultipleSunsWonAndLost() {
        WhistRound whistRound = new WhistRound(5, null, null,
                null, new int[]{1, 2}, new int[]{1, 2});
        int[] expected = new int[]{8, -8, 0, 0};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculator3SunsWon() {
        WhistRound whistRound = new WhistRound(5, null, null,
                null, new int[]{1, 1, 1}, new int[]{1, 2, 3});
        int[] expected = new int[]{2, 2, 2, -6};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }

    @Test
    public void whistRoundCalculatorMultipleSunsLost() {
        WhistRound whistRound = new WhistRound(5, null, null,
                null, new int[]{2, 2, 2}, new int[]{1, 2, 3});
        int[] expected = new int[]{-2, -2, -2, 6};
        whistRound.calculateScores();
        assertArrayEquals(expected, whistRound.getScores());
    }
}
