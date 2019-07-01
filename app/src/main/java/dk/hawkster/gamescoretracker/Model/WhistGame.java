package dk.hawkster.gamescoretracker.Model;

import java.util.List;

public class WhistGame {

    private List<Player> players;
    private List<WhistRound> rounds;

    public WhistGame(List<Player> players) {
        this.players = players;
    }

    public void addNewRound (Integer gameMode, Integer requiredTricks, Integer suit,
                            Integer whips, int[] players, int[] tricks){
       WhistRound whistRound = new WhistRound(gameMode, suit,requiredTricks, whips, tricks, players);
       rounds.add(whistRound);
    }
}
