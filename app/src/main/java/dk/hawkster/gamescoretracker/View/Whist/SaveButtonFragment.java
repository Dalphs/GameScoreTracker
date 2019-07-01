package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.hawkster.gamescoretracker.R;

public class SaveButtonFragment extends Fragment {

    private Button button;
    private SaveButtonListener saveButtonListener;

    public interface SaveButtonListener{
        void saveButtonClicked();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_save_button, container, false);

        button = v.findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveButtonListener.saveButtonClicked();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SaveButtonListener){
            saveButtonListener = (SaveButtonListener) context;
        } else{
            throw new RuntimeException(context.toString() + " must implement SaveButtonListener");
        }
    }
}
