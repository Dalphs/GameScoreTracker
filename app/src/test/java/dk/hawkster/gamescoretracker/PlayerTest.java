package dk.hawkster.gamescoretracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Model.Rummy;
import dk.hawkster.gamescoretracker.Viewmodel.RummyViewModel;

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
        assertEquals(expected, new Player("Søren").getAllTimeScores());
    }

    @Test
    public void accumulatedCalculatorTest(){
        RummyViewModel rummy = new RummyViewModel(Arrays.asList("Simon", "Sune"));
        List<Double> simon = new ArrayList<>(Arrays.asList(20.0, 40.0, 60.0));
        List<Double> sune = new ArrayList<>(Arrays.asList(10.0, 60.0, 80.0));
        List<List<Double>> scoreBoard = new ArrayList<>(Arrays.asList(simon, sune));
        System.out.println("Players form viewmodel" + rummy.getPlayers());
        System.out.println("Scroeboard" + scoreBoard);
        rummy.updateScoreBoard(scoreBoard);
        System.out.println("Simon accumulated: " + rummy.getPlayers().get(0).getAccumulatedGameScores());
        System.out.println("Sune accumulated: " + rummy.getPlayers().get(1).getAccumulatedGameScores());
        System.out.println("Simon not accumulated: " + rummy.getPlayers().get(0).getCurrentGameScores());
        System.out.println("Sune not accumulated: " + rummy.getPlayers().get(1).getCurrentGameScores());

    }
}