package dk.hawkster.gamescoretracker.Viewmodel;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.Model.WhistRound;

public class WhistViewModel {

    List<Player> players;
    List<String> playerNames;

    public WhistViewModel(List<String> players) {

        players = new ArrayList<>();
        playerNames = players;
        for (String player: players) {
            this.players.add(new Player(player));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addNewWhistRound(Integer gameMode, Integer requiredTrricks, Integer suit,
                                 Integer whips, int[] players, int[] tricks){

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

}
