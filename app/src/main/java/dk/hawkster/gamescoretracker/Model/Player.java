package dk.hawkster.gamescoretracker.Model;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private Map<String, Double> allTimeScores;
    private double currentGameScore;

    public Player() {
        setupAllTimeScores();
        currentGameScore = 0;

    }

    private void setupAllTimeScores() {
        Map<String, Double> allTimeScores = new HashMap<>();
        allTimeScores.put("Rummy", 0.0);
        allTimeScores.put("Whist", 0.0);
        allTimeScores.put("3 Person Whist", 0.0);
        allTimeScores.put("Pirate Whist", 0.0);
        allTimeScores.put("Backgammon", 0.0);

        this.allTimeScores = allTimeScores;
        System.out.println(allTimeScores);
    }

    public Map<String, Double> getAllTimeScores() {
        return allTimeScores;
    }

    public void setAllTimeScores(Map<String, Double> allTimeScores) {
        this.allTimeScores = allTimeScores;
    }

    public double getCurrentGameScore() {
        return currentGameScore;
    }

    public void setCurrentGameScore(double currentGameScore) {
        this.currentGameScore = currentGameScore;
    }
}
