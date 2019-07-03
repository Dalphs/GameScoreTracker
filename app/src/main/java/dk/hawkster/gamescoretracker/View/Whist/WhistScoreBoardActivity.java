package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dk.hawkster.gamescoretracker.R;
import dk.hawkster.gamescoretracker.Viewmodel.WhistViewModel;

public class WhistScoreBoardActivity extends AppCompatActivity implements WhistViewModel.WhistViewModelListener {

    LinearLayout llContainer, llRounds;
    Bundle extras;
    List<TextView> tvPlayers;
    List<String> players;
    WhistViewModel whistViewModel;
    List<WhistRoundFragment> roundFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whist_score_board);

        llContainer = findViewById(R.id.linear_layout_whist_players);
        llRounds = findViewById(R.id.rounds_container);

        roundFragments = new ArrayList<>();
        tvPlayers = new ArrayList<>();
        tvPlayers.add((TextView) findViewById(R.id.text_view_player1));
        tvPlayers.add((TextView) findViewById(R.id.text_view_player2));
        tvPlayers.add((TextView) findViewById(R.id.text_view_player3));
        tvPlayers.add((TextView) findViewById(R.id.text_view_player4));

        extras = getIntent().getExtras();

        players = new ArrayList<>();
        Object extraPP = extras.get("PP");
        if (extraPP instanceof ArrayList<?>) {
            ArrayList<?> al = (ArrayList<?>) extraPP;
            if (al.size() > 0) {
                for (int i = 0; i < al.size(); i++) {
                    if (al.get(i) instanceof String) {
                        players.add((String) al.get(i));
                        tvPlayers.get(i).setText((String) al.get(i));
                    }
                }
            }
        }

        whistViewModel = new WhistViewModel(players);
        whistViewModel.addListener(this);

    }

    public void newRound(View view) {

        WhistRoundFragment whistRoundFragment = new WhistRoundFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.rounds_container, whistRoundFragment).commit();
        roundFragments.add(whistRoundFragment);

        Intent intent = new Intent(this, WhistRoundResultActivity.class);
        ArrayList<String> playerNames = new ArrayList<>(players);
        intent.putExtra("PP", playerNames);
        startActivityForResult(intent, 1);
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
            whistViewModel.addNewWhistRound(gameMode, suit, tricksRequired, whip, tricks, players);
        }


    }

    @Override
    public void newScoresCalculated(int[] scores) {
        WhistRoundFragment whistRoundFragment = roundFragments.get(roundFragments.size() - 1);
        whistRoundFragment.setScores(scores);

    }
}
