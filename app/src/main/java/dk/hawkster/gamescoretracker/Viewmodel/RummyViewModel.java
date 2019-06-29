package dk.hawkster.gamescoretracker.Viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Model.Rummy;
import dk.hawkster.gamescoretracker.Observer.ArrayListObservable;

public class RummyViewModel extends ArrayListObservable {

    List<Player> players;
    Rummy rummy;

    public RummyViewModel(List<String> playerNames) {
        players = new ArrayList<>();

        for (String s: playerNames) {
            this.players.add(new Player(s));
        }

        rummy = new Rummy(players);
    }

    public void updateScoreBoard(List<List<Double>> scoreBoard){
        for (int i = 0; i < players.size(); i++) {
            List<Double> currentGameScores = new ArrayList<>(scoreBoard.get(i));
            Player p = players.get(i);
            p.setCurrentGameScores(currentGameScores);
        }
        rummy.calculatePoints();
        Log.d("--------------------", "updateScoreBoard: " + (players.get(1).getAccumulatedGameScores()));

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
}
