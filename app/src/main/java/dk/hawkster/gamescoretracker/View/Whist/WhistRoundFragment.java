package dk.hawkster.gamescoretracker.View.Whist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class WhistRoundFragment extends Fragment {

    List<TextView> players;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_whist_round, container, false);

        players = new ArrayList<>();

        players.add((TextView) v.findViewById(R.id.fragment_whist_round_player1));
        players.add((TextView) v.findViewById(R.id.fragment_whist_round_player2));
        players.add((TextView) v.findViewById(R.id.fragment_whist_round_player3));
        players.add((TextView) v.findViewById(R.id.fragment_whist_round_player4));

        if(getArguments() != null){
            setScores(getArguments().getIntArray("startValues"));
        }

        return v;
    }

    public void setScores(int[] scores){
        for (int i = 0; i < scores.length; i++) {
            players.get(i).setText(String.valueOf(scores[i]));
        }
    }

    public void addToScores(int[] additionalScores){
        for (int i = 0; i < additionalScores.length; i++) {
            TextView textView = players.get(i);
            int currentScore = Integer.parseInt(textView.getText().toString());
            int updatedScore = currentScore + additionalScores[i];

            textView.setText(String.valueOf(updatedScore));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }
}
