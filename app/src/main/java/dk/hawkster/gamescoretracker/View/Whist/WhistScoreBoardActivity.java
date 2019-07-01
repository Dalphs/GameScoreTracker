package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;
import dk.hawkster.gamescoretracker.Viewmodel.WhistViewModel;

public class WhistScoreBoardActivity extends AppCompatActivity {

    LinearLayout llContainer;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whist_score_board);

        llContainer = findViewById(R.id.linear_layout_whist_players);

        extras = getIntent().getExtras();

        List<String> players = new ArrayList<>();
        Object extraPP = extras.get("PP");
        if (extraPP instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) extraPP;
            if (al.size() > 0) {
                for (int i = 0; i < al.size(); i++) {
                    if (al.get(i) instanceof String) {
                        players.add((String) al.get(i));
                    }
                }
            }
        }

        WhistViewModel whistViewModel = new WhistViewModel(players);

        Intent intent = new Intent(this, WhistRoundResultActivity.class);
        ArrayList<String> playerNames = new ArrayList<>(players);
        intent.putExtra("PP", playerNames);
        startActivityForResult(intent, 1);
    }

    public void newRound(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Integer gameMode = null;
        Integer suit = null;
        Integer whip = null;
        Integer tricksRequired = null;
        int[] players, tricks;

        if (data != null) {
            gameMode = data.getIntExtra("GameMode", -1);
            players = data.getIntArrayExtra("Players");
            tricks = data.getIntArrayExtra("Tricks");

            if (gameMode < 5){
                tricksRequired = data.getIntExtra("TricksRequired", -1);
                if(gameMode != 2){
                    suit = data.getIntExtra("Suit", -1);
                }
                if (gameMode == 4){
                    whip = data.getIntExtra("Whips", -1);
                }
            }
        }


    }
}
