package dk.hawkster.gamescoretracker.Observer;

import java.util.ArrayList;

public class PushObservable {

    private ArrayList<PushObserver> observers = new ArrayList<>();

    public void notifyObservers(){
        for (PushObserver o: observers) {
            o.update();
        }
    }

    public void addObserver(PushObserver observer){
        observers.add(observer);
    }
}
