package dk.hawkster.gamescoretracker.View.Rummy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class RummyScoreboardActivity extends AppCompatActivity {

    LinearLayout llPlayerContainer;
    Bundle extras;
    List<RummyPlayerFragment> playersView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rummy_scoreboard);

        llPlayerContainer = findViewById(R.id.linear_layout_rummy_players);
        extras = getIntent().getExtras();

        playersView = new ArrayList<>();
        ArrayList<String> players = (ArrayList) extras.get("PP");

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
