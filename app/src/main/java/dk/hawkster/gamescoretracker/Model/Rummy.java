package dk.hawkster.gamescoretracker.Model;

import java.util.List;

public class Rummy {

    private List<Player> players;
    private int numberOfGames;

    public Rummy() {
        numberOfGames = 0;
    }

    public void assignPoints(){
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
