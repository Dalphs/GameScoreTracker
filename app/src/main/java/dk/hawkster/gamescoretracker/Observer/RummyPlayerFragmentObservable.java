package dk.hawkster.gamescoretracker.Observer;

import android.util.Log;

import java.util.ArrayList;

public class RummyPlayerFragmentObservable {

    private ArrayList<RummyPlayerFragmentObserver> observers = new ArrayList<>();

    public void notifyObservers(int id, int etPosition){
        for (RummyPlayerFragmentObserver o: observers) {
            o.update(id, etPosition);
            Log.d("-----", "notifyObservers: updating");
        }
    }

    public void addObserver(RummyPlayerFragmentObserver observer){
        observers.add(observer);
    }
}
