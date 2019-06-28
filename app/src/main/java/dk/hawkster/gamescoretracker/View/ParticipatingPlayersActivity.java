package dk.hawkster.gamescoretracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class ParticipatingPlayersActivity extends AppCompatActivity {

    List<TextView> nameFields;
    LinearLayout llPlayerContainer, llButtonsContainer;
    Intent startIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_players);

        llPlayerContainer = findViewById(R.id.players_container);
        llButtonsContainer = findViewById(R.id.buttons_container);

        startIntent = getIntent();

        nameFields = new ArrayList<>();

        addTextView();


    }

    public void addPlayerFragment(View view) {
        addTextView();
    }

    public void addTextView(){
        llPlayerContainer.removeView(llButtonsContainer);

        TextView textView = (TextView) View.inflate(this, R.layout.add_player_fragment, null);
        llPlayerContainer.addView(textView);
        nameFields.add(textView);

        llPlayerContainer.addView(llButtonsContainer);
    }

    public ArrayList<String> getPlayers(){
        ArrayList<String> players = new ArrayList<>();
        for (TextView tv: nameFields) {
            String name = tv.getText().toString();
            if(!name.equals("")){
                players.add(name);
            }
        }
        return players;
    }

    public void back(View view) {
        finish();
    }

    public void startGame(View view) {

        if(startIntent.hasExtra("GC")){
            String buttonPressed = startIntent.getStringExtra("GC");
            Intent nextClass = null;
            switch(buttonPressed){
                case "500":
                    nextClass = new Intent(this, RummyScoreboardActivity.class);
                    break;
                case "Whist":
                    break;
                case "3-mands whist":
                    break;
                case "Piratwhist":
                    break;
                case "Backgammon":
                    break;
            }
            nextClass.putExtra("PP", getPlayers());
            startActivity(nextClass);

        } else{
            Toast failedToast = Toast.makeText(this, "Mission failed", Toast.LENGTH_SHORT);
            failedToast.show();
        }


    }
}
