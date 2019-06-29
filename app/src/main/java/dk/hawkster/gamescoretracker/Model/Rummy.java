package dk.hawkster.gamescoretracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Rummy {

    private List<Player> players;
    private int numberOfGames;

    public Rummy(List<Player> players) {
        numberOfGames = 0;
        this.players = players;

    }

    public void calculatePoints(){
        for (Player p: players) {
            Double totalCurrentPoints = 0.0;
            List<Double> currentGameScores = p.getCurrentGameScores();
            Log.d("-----", "calculatePoints: currentGameScore: " + currentGameScores);
            List<Double> accumulatedGameScores = new ArrayList<>();
            for (int i = 0; i < currentGameScores.size(); i++) {
                totalCurrentPoints += currentGameScores.get(i);
                accumulatedGameScores.add(totalCurrentPoints);
            }
            p.setAccumulatedGameScores(accumulatedGameScores);
        }
    }

    public void updateGame(List<Player> updatedPlayers){
        for (int i = 0; i < updatedPlayers.size(); i++) {
            players.set(i, updatedPlayers.get(i));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }
}
