package dk.hawkster.gamescoretracker.Viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Model.Rummy;
import dk.hawkster.gamescoretracker.Observer.RummyObserver;
import dk.hawkster.gamescoretracker.Observer.RummyViewModelObservable;

public class RummyViewModel extends RummyViewModelObservable {

    private List<Player> players;
    private Rummy rummy;
    private List<List<Double>> scoreBoard;

    public RummyViewModel(List<String> playerNames) {
        players = new ArrayList<>();

        for (String s: playerNames) {
            this.players.add(new Player(s));
        }

        rummy = new Rummy(players);
        RummyGameObserver rummyGameObserver = new RummyGameObserver();
        rummy.addObserver(rummyGameObserver);
        scoreBoard = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            ArrayList<Double> emptyPersonalScoreboard = new ArrayList<>();
            scoreBoard.add(emptyPersonalScoreboard);
        }
    }

    public void updateScoreBoard(){
        for (int i = 0; i < players.size(); i++) {
            List<Double> currentGameScores = new ArrayList<>(scoreBoard.get(i));
            Player p = players.get(i);
            p.setCurrentGameScores(currentGameScores);
        }
        rummy.calculatePoints();
        notifyObservers();
        Log.d("--------------------", "updateScoreBoard: " + (players.get(1).getAccumulatedGameScores()));

    }

    public class RummyGameObserver implements RummyObserver {

        @Override
        public void update() {

            players = rummy.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                scoreBoard.set(i, players.get(i).getAccumulatedGameScores());
            }

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

    public Rummy getRummy() {
        return rummy;
    }

    public void setRummy(Rummy rummy) {
        this.rummy = rummy;
    }

    public List<List<Double>> getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(List<List<Double>> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }
}
