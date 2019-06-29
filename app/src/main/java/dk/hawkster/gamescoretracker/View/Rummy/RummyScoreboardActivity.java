package dk.hawkster.gamescoretracker.View.Rummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import dk.hawkster.gamescoretracker.Observer.RummyPlayerFragmentObserver;
import dk.hawkster.gamescoretracker.Observer.RummyViewModelObserver;
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
        ViewModelRummyObserver viewModelRummyObserver = new ViewModelRummyObserver();
        rummyViewModel.addObserver(viewModelRummyObserver);

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

    public class ViewModelRummyObserver implements RummyViewModelObserver{

        @Override
        public void update() {
            List<List<Double>> scoreBoard= rummyViewModel.getScoreBoard();
            for (int i = 0; i < scoreBoard.size(); i++) {
                double[] doubles = new double[scoreBoard.get(i).size()];
                for (int j = 0; j < doubles.length; j++) {
                    doubles[i] = scoreBoard.get(i).get(j);
                }
                playersView.get(i).setNumberFields(doubles);
            }

        }
    }

    public class PlayerFragmentObserver implements RummyPlayerFragmentObserver {

        @Override
        public void update(int id, int etPosition, boolean hasFocus) {
            Log.d("MMM", "update: etPosition: " + etPosition);
                RummyPlayerFragment player = playersView.get(id);
                EditText scoreContainer = player.getNumberFields().get(etPosition);

                if (hasFocus) {
                    List<Double> originalInputs = rummyViewModel.getPlayersScores(id);
                    if (originalInputs.size() > 0 && originalInputs.size() > etPosition) {
                        Double oldInput = originalInputs.get(etPosition);
                        scoreContainer.setText(Double.toString(oldInput));
                        Log.d("MMM", "update: setText: " + oldInput);
                    }

                } else {

                    if (!scoreContainer.getText().toString().equals("")) {
                        List<Double> personalScoreboard = rummyViewModel.getScoreBoard().get(id);
                        Double doubleInScoreContainer = Double.parseDouble(scoreContainer.getText().toString());
                        if (personalScoreboard.size() == etPosition) {
                            personalScoreboard.add(doubleInScoreContainer);
                        } else {
                            personalScoreboard.set(etPosition, doubleInScoreContainer);
                        }
                        rummyViewModel.getScoreBoard().set(id, personalScoreboard);
                        Log.d("MMM", "update: personalScoreboard: " + personalScoreboard);
                    }
                }

            }

    }
}
