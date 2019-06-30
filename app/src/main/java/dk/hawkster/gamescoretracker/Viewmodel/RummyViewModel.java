package dk.hawkster.gamescoretracker.Viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Observer.RummyViewModelObservable;

public class RummyViewModel extends RummyViewModelObservable {

    private List<Player> players;
    private List<List<Double>> scoreBoard;
    private List<List<Double>> accumulatedScoreBoard;

    public RummyViewModel(List<String> playerNames) {
        players = new ArrayList<>();

        for (String s: playerNames) {
            this.players.add(new Player(s));
        }

        scoreBoard = new ArrayList<>();
        accumulatedScoreBoard = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Double> emptyPersonalScoreboard = new ArrayList<>();
            scoreBoard.add(emptyPersonalScoreboard);
            accumulatedScoreBoard.add(emptyPersonalScoreboard);
        }
    }

    public void updatePlayers(){
        for (int i = 0; i < players.size(); i++) {
            List<Double> currentGameScores = new ArrayList<>(scoreBoard.get(i));
            Player p = players.get(i);
            p.setCurrentGameScores(currentGameScores);
            Log.d("MMM", "updateScoreBoard: Individual scoreboard for player" + i + ": " + p.getCurrentGameScores());
        }
        updateAccumulatedGameScores();
        notifyObservers();

    }

    public void updateAccumulatedGameScores(){
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            Double totalCurrentPoints = 0.0;
            List<Double> currentGameScores = p.getCurrentGameScores();
            Log.d("-----", "calculatePoints: currentGameScore: " + currentGameScores);
            List<Double> accumulatedGameScores = new ArrayList<>();
            for (int j = 0; j < currentGameScores.size(); j++) {
                totalCurrentPoints += currentGameScores.get(j);
                accumulatedGameScores.add(totalCurrentPoints);
            }
            accumulatedScoreBoard.set(i, accumulatedGameScores);
            notifyObservers();
        }
    }


    public List<Double> getPlayersScores(int id){
        return players.get(id).getCurrentGameScores();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<List<Double>> getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(List<List<Double>> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public List<List<Double>> getAccumulatedScoreBoard() {
        return accumulatedScoreBoard;
    }

    public void setAccumulatedScoreBoard(List<List<Double>> accumulatedScoreBoard) {
        this.accumulatedScoreBoard = accumulatedScoreBoard;
    }
}
