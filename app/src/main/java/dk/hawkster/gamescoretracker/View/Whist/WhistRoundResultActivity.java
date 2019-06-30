package dk.hawkster.gamescoretracker.View.Whist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.Model.Player;
import dk.hawkster.gamescoretracker.R;

public class WhistRoundResultActivity extends AppCompatActivity {

    List<String> players;

    RadioGroup rgGameModePoints, rgGameMode, rgSun;
    CheckBox cbOnTheTable, cbPlayer1, cbPlayer2, cbPlayer3, cbPlayer4;
    LinearLayout llSuits, llPlayerPlaying, llWhip;
    RadioButton rbWhip;
    Spinner sDropDownMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whist_round_result);

        List<String> players = new ArrayList<>();
        Object extraPP = getIntent().getExtras().get("PP");
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
        this.players = players;

        rgGameModePoints = findViewById(R.id.game_mode_points_radiogroup);
        rgGameMode = findViewById(R.id.game_mode_radiogroup);
        rgSun = findViewById(R.id.sun_types_radiogroup);

        cbOnTheTable = findViewById(R.id.checkbox_on_the_table);

        llSuits = findViewById(R.id.suits_container);
        llPlayerPlaying = findViewById(R.id.players_playing_container);
        llWhip = findViewById(R.id.whip_container);

        rbWhip = findViewById(R.id.radiobutton_whip);

        sDropDownMenu = findViewById(R.id.spinner_whip);
        setupSpinner();

        setupPlayers();

    }

    public void setupSpinner(){
        List<String> numberOfWhips = new ArrayList<>();
        numberOfWhips.add("1");
        numberOfWhips.add("2");
        numberOfWhips.add("3");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, numberOfWhips);
        sDropDownMenu.setAdapter(arrayAdapter);

    }

    public void setupPlayers(){
        cbPlayer1 = findViewById(R.id.checkbox_player1);
        cbPlayer2 = findViewById(R.id.checkbox_player2);
        cbPlayer3 = findViewById(R.id.checkbox_player3);
        cbPlayer4 = findViewById(R.id.checkbox_player4);

        cbPlayer1.setText(players.get(0));
        cbPlayer2.setText(players.get(1));
        cbPlayer3.setText(players.get(2));
        cbPlayer4.setText(players.get(3));
    }

    public void normalGameModeChosen(View view) {
        rgSun.clearCheck();
        cbOnTheTable.setChecked(false);
        int gameModePoints = rgGameModePoints.getCheckedRadioButtonId();
        int gameMode = rgGameMode.getCheckedRadioButtonId();
        llPlayerPlaying.setVisibility(LinearLayout.INVISIBLE);

        if(gameModePoints >= 0 && gameMode >= 0 ){
          llSuits.setVisibility(LinearLayout.VISIBLE);
            int whipButton = rbWhip.getId();
            if(gameMode == whipButton){
                llWhip.setVisibility(LinearLayout.VISIBLE);
            }
        }
    }

    public void sunGameModeChosen(View view) {
        rgGameMode.clearCheck();
        rgGameModePoints.clearCheck();
        llSuits.setVisibility(LinearLayout.VISIBLE);

        int sunMode = rgSun.getCheckedRadioButtonId();

        if (sunMode >= 0){
            llPlayerPlaying.setVisibility(LinearLayout.VISIBLE);
        }
    }

    public void suitChosen(View view) {
        llPlayerPlaying.setVisibility(LinearLayout.VISIBLE);

    }
}
