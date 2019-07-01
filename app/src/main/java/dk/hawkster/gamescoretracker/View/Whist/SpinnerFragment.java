package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import dk.hawkster.gamescoretracker.R;

/* When calling this class:
        spinnerFragment = new SpinnerFragment();

        Bundle args = new Bundle();
        args.putInt("NOT", 10);
        args.putString("TVC", "WOOW");
 */

public class SpinnerFragment extends Fragment {

    private TextView textView;
    private Spinner spinner;
    private Context context;
    int number;
    String text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_spinner, container, false);

        context = v.getContext();


        textView = v.findViewById(R.id.text_view_spinner);
        spinner = v.findViewById(R.id.spinner_spinner_fragment);

        textView.setText(text);

        ArrayList<String> adapter = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            adapter.add(Integer.toString(i));
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, adapter);
        spinner.setAdapter(arrayAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        number = bundle.getInt("SV");
        text = bundle.getString("ST");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public String getItemSelected(){
        return spinner.getSelectedItem().toString();
    }

    public void setEditText(String s){
        textView.setText(s);
    }
}
