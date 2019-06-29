package dk.hawkster.gamescoretracker.View.Rummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import dk.hawkster.gamescoretracker.Observer.RummyPlayerFragmentObserver;
import dk.hawkster.gamescoretracker.R;
import dk.hawkster.gamescoretracker.Viewmodel.RummyViewModel;

public class RummyScoreboardActivity extends AppCompatActivity {

    LinearLayout llPlayerContainer;
    Bundle extras;
    List<RummyPlayerFragment> playersView;
    RummyViewModel rummyViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rummy_scoreboard);


        llPlayerContainer = findViewById(R.id.linear_layout_rummy_players);
        extras = getIntent().getExtras();
        List<String> players = new ArrayList<>();

        playersView = new ArrayList<>();
        Object extraPP = extras.get("PP");
        if(extraPP instanceof ArrayList<?>){
            ArrayList<?> al = (ArrayList<?>) extraPP;
            if(al.size() > 0){
                for (int i = 0; i < al.size(); i++) {
                    if(al.get(i) instanceof String){
                        players.add((String) al.get(i));
                    }
                }
            }
        }

        rummyViewModel = new RummyViewModel(players);

        int idCounter = 0;
        for (String s : players) {
            RummyPlayerFragment rummyPlayerFragment = new RummyPlayerFragment(this, s, idCounter);
            playersView.add(rummyPlayerFragment);
            LinearLayout linearLayout = rummyPlayerFragment.getLlContainer();
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
            llPlayerContainer.addView(linearLayout);
            idCounter++;
            Log.d("-----------------------", "RummyScoreboardActivity onCreate: " + s);
        }
        llPlayerContainer.setWeightSum(players.size());
        PlayerFragmentObserver playerFragmentObserver = new PlayerFragmentObserver();

        for (RummyPlayerFragment r: playersView) {
            r.addObserver(playerFragmentObserver);
            Log.d("-----", "onCreate: observing on" + r.getName());
        }

    }

    public class PlayerFragmentObserver implements RummyPlayerFragmentObserver {

        @Override
        public void update(int id, int etPosition) {
            List<List<Double>> scoreBoard = new ArrayList<>();
            for (RummyPlayerFragment r: playersView) {
                scoreBoard.add(r.getNumbersInFields());
            }
            Log.d("-------", "update: PlayerFragmentObserver notified");
            Log.d("------", "update: Scoreboard: " + scoreBoard.get(1));
            rummyViewModel.updateScoreBoard(scoreBoard);
            if (etPosition >= 0){
                playersView.get(id).getNumberFields().get(etPosition).setText(Double.toString(rummyViewModel.getPlayers().get(id).getCurrentGameScores().get(etPosition)));
            }
        }
    }
}
