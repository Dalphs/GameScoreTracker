package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class SuitsFragment extends Fragment {

    List<RadioButton> radioButtons;
    RadioGroup radioGroup;
    SuitsFragmentListener listener;
    boolean suitChosen;

    public interface SuitsFragmentListener{
        void suitsUpdated();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_suit, container, false);

        radioButtons = new ArrayList<>();
        radioGroup = v.findViewById(R.id.suits_radio_group);

        radioButtons.add((RadioButton) v.findViewById(R.id.radiobutton_clubs));
        radioButtons.add((RadioButton) v.findViewById(R.id.radiobutton_spades));
        radioButtons.add((RadioButton) v.findViewById(R.id.radiobutton_hearts));
        radioButtons.add((RadioButton) v.findViewById(R.id.radiobutton_diamonds));

        int checker = radioGroup.getCheckedRadioButtonId();
        suitChosen = checker >= 0;

        for (RadioButton rb: radioButtons) {
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.suitsUpdated();
                    suitChosen = true;
                }
            });
        }
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SuitsFragmentListener){
            listener = (SuitsFragmentListener) context;
        }else{
            throw new RuntimeException(context.toString() + " must implement SuitsFragmentListener");
        }
    }

    public void reset(){
        for (RadioButton rb: radioButtons) {
            rb.setChecked(false);
        }
        suitChosen = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public RadioButton getCheckedButton(){
        return getView().findViewById(radioGroup.getCheckedRadioButtonId());
    }

    public boolean isSuitChosen() {
        return suitChosen;
    }

    public void setSuitChosen(boolean suitChosen) {
        this.suitChosen = suitChosen;
    }
}
