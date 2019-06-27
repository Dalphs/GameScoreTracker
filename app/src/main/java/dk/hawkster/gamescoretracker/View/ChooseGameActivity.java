package dk.hawkster.gamescoretracker.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import dk.hawkster.gamescoretracker.R;

public class ChooseGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
    }

    public void addPlayers(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ParticipatingPlayersActivity.class);
        startActivity(intent);
    }
}
