package dk.hawkster.gamescoretracker.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class ParticipatingPlayersActivity extends AppCompatActivity {

    List<TextView> nameFields;
    LinearLayout llPlayerContainer, llButtonsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participating_players);

        llPlayerContainer = findViewById(R.id.players_container);
        llButtonsContainer = findViewById(R.id.buttons_container);

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

    public void back(View view) {
        finish();
    }

    public void startGame(View view) {
    }
}
