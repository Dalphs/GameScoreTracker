package dk.hawkster.gamescoretracker.Observer;

import java.util.ArrayList;

public class ArrayListObservable {

    private ArrayList<ArrayListObserver> observers = new ArrayList<>();

    public void notifyObservers(ArrayList<String[]> players){
        for (ArrayListObserver o: observers) {
            o.update(players);
        }
    }

    public void addObserver(ArrayListObserver observer){
        observers.add(observer);
    }
}
