package dk.hawkster.gamescoretracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WhistGame {

    private List<Player> players;
    private List<WhistRound> rounds;
    private WhistGameListener listener;

    public interface WhistGameListener{
        void scoresUpdated(int[] scores);
    }

    public WhistGame(List<Player> players) {
        this.players = players;
        rounds = new ArrayList<>();

    }

    public void addListener(WhistGameListener listener){
        this.listener = listener;
    }

    public void addNewRound (Integer gameMode, Integer suit, Integer requiredTricks,
                            Integer whips, int[] tricks, int[] players){
       WhistRound whistRound = new WhistRound(gameMode, suit, requiredTricks, whips, tricks, players);
        Log.d("----", "onActivityResult: " + gameMode + " " + requiredTricks + " " + suit + " " + whips);
       whistRound.calculateScores();
        Log.d("-----", "addNewRound: " + Arrays.toString(whistRound.getScores()));
       int[] scores = whistRound.getScores();

       assignPoints(scores);
       rounds.add(whistRound);
        Log.d("-----", "addNewRound: " + Arrays.toString(scores));

       listener.scoresUpdated(scores);
    }

    public void assignPoints(int[] scores){
        int counter = 0;
        for (Player p: players) {
            p.getCurrentGameScores().add((double) scores[counter]);
            counter++;
        }
    }

}
