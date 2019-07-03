package dk.hawkster.gamescoretracker.Viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Model.WhistGame;
import dk.hawkster.gamescoretracker.Model.WhistRound;
import dk.hawkster.gamescoretracker.Observer.PushObserver;

public class WhistViewModel implements WhistGame.WhistGameListener {

    List<Player> players;
    List<String> playerNames;
    WhistGame whistGame;
    WhistViewModelListener listener;

    public interface WhistViewModelListener{
        void newScoresCalculated(int[] scores);
    }

    public WhistViewModel(List<String> players) {

        this.players = new ArrayList<>();
        playerNames = players;
        for (String player: players) {
            this.players.add(new Player(player));
        }
        Log.d("--------", "WhistViewModel: " + players);
        whistGame = new WhistGame(this.players);
        whistGame.addListener(this);
    }

    public void addListener(WhistViewModelListener listener){
        this.listener = listener;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addNewWhistRound(Integer gameMode, Integer suit, Integer requiredTricks,
                                 Integer whips, int[] tricks, int[] players){
        whistGame.addNewRound(gameMode, suit, requiredTricks, whips, tricks, players);
    }


    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public void setPlayerNames(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    @Override
    public void scoresUpdated(int[] scores) {
        listener.newScoresCalculated(scores);
    }
}
