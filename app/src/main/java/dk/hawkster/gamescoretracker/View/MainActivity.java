package dk.hawkster.gamescoretracker.View;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import dk.hawkster.gamescoretracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newGameClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), ChooseGameActivity.class);
        startActivity(intent);
    }
}
