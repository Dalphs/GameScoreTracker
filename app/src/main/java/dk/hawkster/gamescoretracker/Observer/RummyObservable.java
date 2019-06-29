package dk.hawkster.gamescoretracker.Observer;

import java.util.ArrayList;

public class RummyObservable {

    private ArrayList<RummyObserver> observers = new ArrayList<>();

    public void notifyObservers(){
        for (RummyObserver o: observers) {
            o.update();
        }
    }

    public void addObserver(RummyObserver observer){
        observers.add(observer);
    }
}
