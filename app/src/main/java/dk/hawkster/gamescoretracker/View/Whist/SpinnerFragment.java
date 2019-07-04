package dk.hawkster.gamescoretracker.View.Whist;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import dk.hawkster.gamescoretracker.R;

/* When calling this class:
        spinnerFragment = new SpinnerFragment();

        Bundle args = new Bundle();
        args.putInt("NOT", 10);
        args.putString("TVC", "WOOW");
 */

public class SpinnerFragment extends Fragment {

    private Spinner spinner;
    private Context context;
    ArrayList<String> adapter;
    SpinnerFragmentListener listener;
    boolean whipSpinner;
    boolean numberElementChosen;

    public interface SpinnerFragmentListener{
        void elementChosen();
        void defaultElementChosen();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_spinner, container, false);

        context = v.getContext();
        adapter = new ArrayList<>();

        spinner = v.findViewById(R.id.spinner_spinner_fragment);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                numberElementChosen = position != 0;
                if(whipSpinner){
                    if(numberElementChosen)
                        listener.elementChosen();
                    else
                        listener.defaultElementChosen();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Bundle args = getArguments();

        if (args != null) {
            whipSpinner = args.getBoolean("isWhipSpinner");
            String type = args.getString("type");
            if (type.equals("counterSpinner")) {
                String title = args.getString("title");
                int amount = args.getInt("amount");
                boolean ascending = args.getBoolean("ascending");
                boolean includeZero = args.getBoolean("includeZero");


                adapter.add(title);
                if (ascending) {
                    for (int i = includeZero ? 0 : 1; i <= amount; i++) {
                        adapter.add(String.valueOf(i));
                    }
                } else {
                    for (int i = amount; i >= (includeZero ? 0 : 1); i--) {
                        adapter.add(String.valueOf(i));
                    }
                }
            }else if (type.equals("stringSpinner")){
                adapter = args.getStringArrayList("elementsList");
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, adapter);
            spinner.setAdapter(arrayAdapter);
        }else {
            throw new RuntimeException("SpinnerFragment must have arguments");
        }

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof SpinnerFragmentListener){
            listener = (SpinnerFragmentListener) context;
        } else{
            throw new RuntimeException(context.toString() + " must implement SaveButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public String getItemSelected(){
        return spinner.getSelectedItem().toString();
    }

    public boolean isNumberElementChosen() {
        return numberElementChosen;
    }
}
