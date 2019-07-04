package dk.hawkster.gamescoretracker.View;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import dk.hawkster.gamescoretracker.R;
import dk.hawkster.gamescoretracker.View.Rummy.RummyScoreboardActivity;
import dk.hawkster.gamescoretracker.View.Whist.WhistScoreBoardActivity;

public class ChooseGameActivity extends AppCompatActivity {

    private String buttonPressed;
    private ArrayList<String> listPlayers;
    private ChoosePlayerAdapter choosePlayerAdapter;
    private Button bAddPlayer;
    private TextView tvNewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);

        listPlayers = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.default_players)));
    }

    public void addPlayers(View view) {
        Button button = (Button) view;
        buttonPressed = button.getText().toString();

        View v = getLayoutInflater().inflate(R.layout.dialog_choose_players, null);
        bAddPlayer = v.findViewById(R.id.add_player_button);
        tvNewPlayer = v.findViewById(R.id.add_player_edit_text);
        bAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tvNewPlayer.getText().toString().equals("") ){
                    String newName = tvNewPlayer.getText().toString().trim();
                    newName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
                    listPlayers.add(newName);
                    choosePlayerAdapter.updateData(listPlayers);
                }
            }
        });

        RecyclerView recyclerView = v.findViewById(R.id.choose_players_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        choosePlayerAdapter = new ChoosePlayerAdapter(listPlayers);
        recyclerView.setAdapter(choosePlayerAdapter);

        new AlertDialog.Builder(this)
                .setTitle(R.string.alert_players_title)
                .setView(v)
                .setPositiveButton(R.string.button_start, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startNextActivity(choosePlayerAdapter.getPlayersChecked());
                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void startNextActivity(ArrayList<Integer> chosenPlayers) {
        ArrayList<String> playerNames = new ArrayList<>();
        for (Integer i : chosenPlayers) {
            playerNames.add(listPlayers.get(i));
        }

        Intent nextClass = null;
        switch (buttonPressed) {
            case "500":
                if (playerNames.size() > 1 && playerNames.size() < 6) {
                    nextClass = new Intent(this, RummyScoreboardActivity.class);
                } else if (playerNames.size() > 5) {
                    Toast tooMany = Toast.makeText(this, "Max. 4 spillere", Toast.LENGTH_SHORT);
                    tooMany.show();
                } else {
                    Toast tooFew = Toast.makeText(this, "Min. 4 spillere", Toast.LENGTH_SHORT);
                    tooFew.show();
                }
                break;
            case "Whist":
                if (playerNames.size() == 4) {
                    nextClass = new Intent(this, WhistScoreBoardActivity.class);
                } else {
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
        if (nextClass != null) {
            nextClass.putStringArrayListExtra("Players", playerNames);
            startActivity(nextClass);
        }
    }
}
