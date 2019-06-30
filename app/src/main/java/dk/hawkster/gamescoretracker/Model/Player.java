package dk.hawkster.gamescoretracker.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private String name;
    private Map<String, Double> allTimeScores;
    private List<Double> currentGameScores;

    public Player(String name) {
        setupAllTimeScores();
        currentGameScores = new ArrayList<>();
        this.name = name;

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

    public List<Double> getCurrentGameScores() {
        return currentGameScores;
    }

    public void setCurrentGameScores(List<Double> currentGameScores) {
        this.currentGameScores = currentGameScores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
