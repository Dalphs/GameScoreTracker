package dk.hawkster.gamescoretracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dk.hawkster.gamescoretracker.R;

public class ChooseGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
    }

    public void addPlayers(View view) {
        Intent intent = new Intent(getApplicationContext(), ParticipatingPlayersActivity.class);
        startActivity(intent);
    }
}
