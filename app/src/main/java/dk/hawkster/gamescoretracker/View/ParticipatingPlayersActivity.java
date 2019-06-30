package dk.hawkster.gamescoretracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;
import dk.hawkster.gamescoretracker.View.Rummy.RummyScoreboardActivity;
import dk.hawkster.gamescoretracker.View.Whist.WhistRoundResultActivity;
import dk.hawkster.gamescoretracker.View.Whist.WhistScoreBoardActivity;

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
        textView.requestFocus();
        llPlayerContainer.addView(textView);
        nameFields.add(textView);

        llPlayerContainer.addView(llButtonsContainer);
    }

    public ArrayList<String> getPlayers(){
        ArrayList<String> players = new ArrayList<>();
        for (TextView tv: nameFields) {
            String input = tv.getText().toString();
            if(!input.equals("")){
                String name = input.substring(0, 1).toUpperCase() + input.substring(1);
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
                    nextClass = new Intent(this, WhistScoreBoardActivity.class);
                    break;
                case "3-mands whist":
                    break;
                case "Piratwhist":
                    break;
                case "Backgammon":
                    break;
            }
            ArrayList<String> players = getPlayers();
            if(buttonPressed.equals("Whist") && players.size() < 4){
                Toast tooFew = Toast.makeText(this, "Min. 4 spillere", Toast.LENGTH_SHORT);
                tooFew.show();
            }else if (buttonPressed.equals("Whist") && players.size() > 4){
                Toast tooMany = Toast.makeText(this, "Max. 4 spillere", Toast.LENGTH_SHORT);
                tooMany.show();
            }else {
                nextClass.putExtra("PP", getPlayers());
                startActivity(nextClass);
            }

        } else{
            Toast failedToast = Toast.makeText(this, "Mission failed", Toast.LENGTH_SHORT);
            failedToast.show();
        }


    }
}
