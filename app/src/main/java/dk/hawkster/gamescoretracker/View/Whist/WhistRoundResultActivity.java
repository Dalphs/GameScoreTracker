package dk.hawkster.gamescoretracker.View.Whist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class WhistRoundResultActivity extends AppCompatActivity implements SuitsFragment.SuitsFragmentListener,
        SaveButtonFragment.SaveButtonListener, SpinnerFragment.SpinnerFragmentListener {

    List<String> players;

    RadioGroup rgGameModePoints, rgGameMode, rgSun;
    CheckBox cbOnTheTable;
    LinearLayout llPlayerPlaying, llViewContainer, llTrickContainer;
    RadioButton rbWhip, rbCleanSun, rbSun, rbWithout;
    List<CheckBox> cbPlayers;
    List<SpinnerFragment> tricksCounters;
    SuitsFragment suitsFragment;
    SpinnerFragment whipSpinner;
    SaveButtonFragment saveButtonFragment;

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

        llPlayerPlaying = findViewById(R.id.players_playing_container);
        llViewContainer = findViewById(R.id.view_container);
        llTrickContainer = findViewById(R.id.trick_spinner_holder);

        suitsFragment = new SuitsFragment();
        whipSpinner = new SpinnerFragment();
        saveButtonFragment = new SaveButtonFragment();

        rbWhip = findViewById(R.id.radiobutton_whip);
        rbCleanSun = findViewById(R.id.radiobutton_clean_sun);
        rbSun = findViewById(R.id.radiobutton_sun);
        rbWithout = findViewById(R.id.radiobutton_without);

        cbPlayers = new ArrayList<>();
        tricksCounters = new ArrayList<>();


        setupPlayers();

    }

    public void setupPlayers(){
        cbPlayers.add((CheckBox) findViewById(R.id.checkbox_player1));
        cbPlayers.add((CheckBox) findViewById(R.id.checkbox_player2));
        cbPlayers.add((CheckBox) findViewById(R.id.checkbox_player3));
        cbPlayers.add((CheckBox) findViewById(R.id.checkbox_player4));

        for (int i = 0; i < cbPlayers.size(); i++) {
            cbPlayers.get(i).setText(players.get(i));
        }
    }

    public void normalGameModeChosen(View view) {
        rgSun.clearCheck();
        cbOnTheTable.setChecked(false);
        int gameModePoints = rgGameModePoints.getCheckedRadioButtonId();
        int gameMode = rgGameMode.getCheckedRadioButtonId();

        if(gameModePoints >= 0 && gameMode >= 0 ){
            if(gameMode != rbWithout.getId()) {
                if(!suitsFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().add(R.id.suits_holder, suitsFragment).commit();
                }
            }else{
                llPlayerPlaying.setVisibility(LinearLayout.VISIBLE);
                if(suitsFragment.isAdded()) {
                    getSupportFragmentManager().beginTransaction().remove(suitsFragment).commit();
                    suitsFragment.reset();
                }
            }
            if(suitsFragment.isSuitChosen() || gameMode == rbWithout.getId()){
                llPlayerPlaying.setVisibility(LinearLayout.VISIBLE);
            }

            int whipButton = rbWhip.getId();
            if(gameMode == whipButton){
                if(!whipSpinner.isAdded()) {
                    Bundle args = new Bundle();
                    args.putString("type", "counterSpinner");
                    args.putString("title", "Antal vip");
                    args.putInt("amount", 3);
                    args.putBoolean("ascending", true);
                    args.putBoolean("includeZero", false);
                    args.putBoolean("isWhipSpinner", true);


                    whipSpinner.setArguments(args);
                    getSupportFragmentManager().beginTransaction().
                            add(R.id.whip_spinner_holder, whipSpinner).commit();
                }
            }else{
                getSupportFragmentManager().beginTransaction().remove(whipSpinner).commit();
            }
        }else{
            getSupportFragmentManager().beginTransaction().remove(saveButtonFragment).commit();
            unchceckPlayers();
            llPlayerPlaying.setVisibility(View.INVISIBLE);
        }
        if (gameMode != rbWithout.getId() && !suitsFragment.isSuitChosen()){
            getSupportFragmentManager().beginTransaction().remove(saveButtonFragment).commit();
            unchceckPlayers();
            llPlayerPlaying.setVisibility(View.INVISIBLE);
        }
    }

    public void sunGameModeChosen(View view) {
        rgGameMode.clearCheck();
        rgGameModePoints.clearCheck();
        getSupportFragmentManager().beginTransaction().remove(suitsFragment).remove(whipSpinner).commit();
        if(suitsFragment.isSuitChosen()) {
            suitsFragment.reset();
        }
        getSupportFragmentManager().beginTransaction().remove(saveButtonFragment).commit();
        unchceckPlayers();

        if (tricksCounters.size() > 0){
            getSupportFragmentManager().beginTransaction().remove(tricksCounters.get(0)).commit();
        }

        int sunMode = rgSun.getCheckedRadioButtonId();

        if (sunMode >= 0){
            llPlayerPlaying.setVisibility(LinearLayout.VISIBLE);
        }
    }

    public void playerSelected(View view) {
        int checker = rgSun.getCheckedRadioButtonId();
        int numberOfChecked = 0;

        List<CheckBox> boxesChecked = new ArrayList<>();

        for (CheckBox c: cbPlayers) {
            if (c.isChecked()) {
                numberOfChecked++;
                boxesChecked.add(c);
            }
        }
        if(checker >= 0 && numberOfChecked >= 0){
            llTrickContainer.removeAllViews();
            tricksCounters.clear();
            for (CheckBox cb: boxesChecked) {
                Bundle args = new Bundle();
                args.putString("type", "counterSpinner");
                args.putString("title", cb.getText().toString() + "s stik");
                args.putInt("amount", 13);
                args.putBoolean("ascending", true);
                args.putBoolean("includeZero", true);

                SpinnerFragment spinnerFragment = new SpinnerFragment();
                spinnerFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().
                        add(R.id.trick_spinner_holder, spinnerFragment).commit();
                tricksCounters.add(spinnerFragment);
            }
        } else if(tricksCounters.size() <   1){
            Bundle args = new Bundle();
            args.putString("type", "counterSpinner");
            args.putString("title", "Antal stik");
            args.putInt("amount", 13);
            args.putBoolean("ascending", false);
            args.putBoolean("includeZero", true);

            SpinnerFragment spinnerFragment = new SpinnerFragment();
            spinnerFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().
                    add(R.id.trick_spinner_holder, spinnerFragment).commit();
            tricksCounters.add(spinnerFragment);
        } else if(numberOfChecked == 3){
            CheckBox checkBox = (CheckBox) view;
            checkBox.setChecked(false);
            Toast toast = Toast.makeText(this, "Max 2 makkere", Toast.LENGTH_SHORT);
            toast.show();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.save_button_holder, saveButtonFragment).commit();

    }

    public void unchceckPlayers(){
        for (CheckBox cb: cbPlayers) {
            cb.setChecked(false);
        }
        llTrickContainer.removeAllViews();
        tricksCounters.clear();
    }

    @Override
    public void suitsUpdated() {
        llPlayerPlaying.setVisibility(LinearLayout.VISIBLE);
    }

    @Override
    public void saveButtonClicked() {
        Intent returnIntent = new Intent();
        int gameMode = getGameMode();
        returnIntent.putExtra("GameMode", gameMode);
        if (gameMode < 5){
            int tricksRequired;
            RadioButton checkedModeButton = findViewById(rgGameModePoints.getCheckedRadioButtonId());
            tricksRequired = Integer.parseInt(checkedModeButton.getText().toString());
            returnIntent.putExtra("TricksRequired", tricksRequired);
            if(gameMode != 2){
                int suitChosen = getSuit();
                returnIntent.putExtra("Suit", suitChosen);
            }
            if(gameMode == 4){
                int numberOfWhips = Integer.parseInt(whipSpinner.getItemSelected());
                returnIntent.putExtra("Whips", numberOfWhips);
            }
        }
        int[] tricks = getNumberOfTricks();
        returnIntent.putExtra("Tricks", tricks);

        int[] playerIndexes = getPlayerIndexes();
        returnIntent.putExtra("Players", playerIndexes);


        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }

    public int getGameMode(){
        int gameMode = 0;
        int sunGameMode = rgSun.getCheckedRadioButtonId();

        if(sunGameMode < 0){
            RadioButton mode = findViewById(rgGameMode.getCheckedRadioButtonId());
            switch(mode.getText().toString()){
                case "Med":
                    gameMode = 1;
                    break;
                case "Uden":
                    gameMode = 2;
                    break;
                case "Halve":
                    gameMode = 3;
                    break;
                case "Vip":
                    gameMode = 4;
                    break;
            }
        }else{
            if (sunGameMode == rbSun.getId()){
                gameMode = 5;
                if(cbOnTheTable.isChecked()){
                    gameMode = 6;
                }

            }else if(sunGameMode == rbCleanSun.getId()){
                gameMode = 7;
                if(cbOnTheTable.isChecked()){
                    gameMode = 8;
                }
            }
        }

        return gameMode;
    }

    public int getSuit(){
        RadioButton checkedSuitButton = suitsFragment.getCheckedButton();
        int suitChosen = 0;

        switch (checkedSuitButton.getText().toString()){
            case "Hjerter":
                suitChosen = 1;
                break;
            case "Spar":
                suitChosen = 2;
                break;
            case "Ruder":
                suitChosen = 3;
                break;
            case "Klør":
                suitChosen = 4;
                break;
        }
        return suitChosen;

    }

    public int[] getNumberOfTricks(){
        int[] tricks = new int[tricksCounters.size()];

        for (int i = 0; i < tricksCounters.size(); i++) {
            tricks[i] = Integer.parseInt(tricksCounters.get(i).getItemSelected());
        }

        return tricks;
    }

    public int[] getPlayerIndexes(){
        List<Integer> indexes = new ArrayList<>();
        int counter = 1;

        for (CheckBox cb: cbPlayers) {
            if (cb.isChecked()){
                indexes.add(counter);
            }
            counter++;
        }
        int[] players = new int[indexes.size()];
        for (int i = 0; i < players.length; i++) {
            players[i] = indexes.get(i);
        }

        return players;
    }

    @Override
    public void elementChosen() {
        Log.d("ttt", "elementChosen: ");
    }

    @Override
    public void defaultElementChosen() {
        Log.d("ttt", "defaultElementChosen: ");
    }
}
