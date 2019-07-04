package dk.hawkster.gamescoretracker.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.hawkster.gamescoretracker.R;

public class ChoosePlayerAdapter extends RecyclerView.Adapter<ChoosePlayerAdapter.ViewHolderChoosePlayer> {

    private ArrayList<String> players;
    private ArrayList<Integer> playersChecked;


    public class ViewHolderChoosePlayer extends RecyclerView.ViewHolder{
        public TextView tvPlayer;
        public CheckBox cbPlayer;

        public ViewHolderChoosePlayer(@NonNull View itemView) {
            super(itemView);

            tvPlayer = itemView.findViewById(R.id.players_dialog_text_view);
            cbPlayer = itemView.findViewById(R.id.players_dialog_check_box);

        }
    }

    public ChoosePlayerAdapter(ArrayList<String> players) {
        this.players = players;
        playersChecked = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolderChoosePlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_choose_players, parent, false);
        return new ViewHolderChoosePlayer(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChoosePlayer holder, final int position) {
        holder.tvPlayer.setText(players.get(position));
        holder.cbPlayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    playersChecked.add(position);
                }else{
                    playersChecked.remove(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void updateData(ArrayList<String> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public ArrayList<Integer> getPlayersChecked() {
        return playersChecked;
    }
}
