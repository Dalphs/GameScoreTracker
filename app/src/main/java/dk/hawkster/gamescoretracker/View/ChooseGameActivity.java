package dk.hawkster.gamescoretracker.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import dk.hawkster.gamescoretracker.R;
import dk.hawkster.gamescoretracker.View.Rummy.RummyScoreboardActivity;
import dk.hawkster.gamescoretracker.View.Whist.WhistScoreBoardActivity;

public class ChooseGameActivity extends AppCompatActivity {

    private String buttonPressed;
    private String[] listPlayers;
    private ArrayList<Integer> chosenPlayers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
    }

    public void addPlayers(View view) {
        Button button = (Button) view;
        buttonPressed = button.getText().toString();
        listPlayers = getResources().getStringArray(R.array.default_players);
        boolean[] checkedPlayers = new boolean[listPlayers.length];
        final ArrayList<Integer> mChosenPlayers = new ArrayList<>();

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle(R.string.alert_players_title);
        mBuilder.setMultiChoiceItems(listPlayers, checkedPlayers, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked){
                    mChosenPlayers.add(which);
                }else{
                    mChosenPlayers.remove(Integer.valueOf(which));
                }

            }
        });

        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton(R.string.button_start, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chosenPlayers = mChosenPlayers;
                startNextActivity(chosenPlayers);
            }
        });
        mBuilder.setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = mBuilder.create();
        dialog.setContentView(R.layout.fragment_save_button);
        dialog.show();
    }

    private void startNextActivity(ArrayList<Integer> chosenPlayers) {
        ArrayList<String> playerNames = new ArrayList<>();
        for (Integer i: chosenPlayers) {
            playerNames.add(listPlayers[i]);
        }

        Intent nextClass = null;
        switch(buttonPressed){
            case "500":
                if(playerNames.size() > 1 && playerNames.size() < 6) {
                    nextClass = new Intent(this, RummyScoreboardActivity.class);
                }else if(playerNames.size() > 5) {
                    Toast tooMany = Toast.makeText(this, "Max. 4 spillere", Toast.LENGTH_SHORT);
                    tooMany.show();
                }else{
                    Toast tooFew = Toast.makeText(this, "Min. 4 spillere", Toast.LENGTH_SHORT);
                    tooFew.show();
                }
                break;
            case "Whist":
                if(playerNames.size() == 4) {
                    nextClass = new Intent(this, WhistScoreBoardActivity.class);
                }else {
                    Toast tooMany = Toast.makeText(this, "Vælg 4 spillere", Toast.LENGTH_SHORT);
                    tooMany.show();
                }
                break;
            case "3-mands whist":
                break;
            case "Piratwhist":
                break;
            case "Backgammon":
                break;
        }
        if(nextClass != null) {
            nextClass.putStringArrayListExtra("Players", playerNames);
            startActivity(nextClass);
        }
    }
}
