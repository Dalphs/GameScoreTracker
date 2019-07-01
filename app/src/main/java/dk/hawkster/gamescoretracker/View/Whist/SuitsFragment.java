package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

        for (RadioButton rb: radioButtons) {
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.suitsUpdated();
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

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public RadioButton getCheckedButton(){
        return getView().findViewById(radioGroup.getCheckedRadioButtonId());
    }
}
