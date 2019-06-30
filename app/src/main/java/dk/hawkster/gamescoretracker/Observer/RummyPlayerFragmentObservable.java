package dk.hawkster.gamescoretracker.Observer;

import android.util.Log;

import java.util.ArrayList;

public class RummyPlayerFragmentObservable {

    private ArrayList<RummyPlayerFragmentObserver> observers = new ArrayList<>();

    public void notifyObservers(int id, int etPosition, boolean hasFocus, double input){
        for (RummyPlayerFragmentObserver o: observers) {
            o.update(id, etPosition, hasFocus, input);
        }
    }

    public void addObserver(RummyPlayerFragmentObserver observer){
        observers.add(observer);
    }
}
