package dk.hawkster.gamescoretracker.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dk.hawkster.gamescoretracker.R;

public class RummyScoreboardActivity extends AppCompatActivity {

    LinearLayout llPlayerContainer;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rummy_scoreboard);

        llPlayerContainer = findViewById(R.id.linear_layout_rummy_players);
        Bundle extras = getIntent().getExtras();
        ArrayList<String> players = (ArrayList) extras.get("PP");

    }
}
