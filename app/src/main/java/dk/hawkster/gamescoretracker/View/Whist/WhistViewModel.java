package dk.hawkster.gamescoretracker.View.Whist;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;

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
