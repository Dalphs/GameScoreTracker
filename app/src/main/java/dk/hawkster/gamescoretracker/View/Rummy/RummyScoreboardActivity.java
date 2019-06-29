package dk.hawkster.gamescoretracker.View.Rummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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


        if(players != null) {
            for (String s : players) {
                RummyPlayerFragment rummyPlayerFragment = new RummyPlayerFragment(this, s);
                playersView.add(rummyPlayerFragment);
                LinearLayout linearLayout = rummyPlayerFragment.getLlContainer();
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                llPlayerContainer.addView(linearLayout);
                Log.d("-----------------------", "onCreate: " + s);
            }
        }
        llPlayerContainer.setWeightSum(players.size());

    }
}
