package dk.hawkster.gamescoretracker.Observer;

import java.util.ArrayList;

public class RummyViewModelObservable {

    private ArrayList<RummyViewModelObserver> observers = new ArrayList<>();

    public void notifyObservers(){
        for (RummyViewModelObserver o: observers) {
            o.update();
        }
    }

    public void addObserver(RummyViewModelObserver observer){
        observers.add(observer);
    }
}
