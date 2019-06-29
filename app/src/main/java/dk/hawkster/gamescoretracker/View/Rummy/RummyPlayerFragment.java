package dk.hawkster.gamescoretracker.View.Rummy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.hawkster.gamescoretracker.R;

public class RummyPlayerFragment {

    private Context context;
    private LinearLayout llContainer;
    private List<EditText> numberFields;
    private String name;

    public RummyPlayerFragment(Context context, String name) {
        this.context = context;

        numberFields = new ArrayList<>();
        this.name = name;

        llContainer = (LinearLayout) View.inflate(context, R.layout.fragment_linear_layout, null);
        llContainer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        setup();
    }

    public void setup(){
        TextView nameField = (TextView) View.inflate(context, R.layout.fragment_name_field, null);
        nameField.setText(name);
        llContainer.addView(nameField);
        for (int i = 0; i < 2; i++) {
            EditText editText = createEditText();
            addToView(editText);
        }

    }

    public EditText createEditText(){
        EditText editText = (EditText) View.inflate(context, R.layout.fragment_number_field, null);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et = (EditText) v;
                if (et.equals(numberFields.get(numberFields.size() - 1))) {
                    EditText newEditText = createEditText();
                    addToView(newEditText);
                }
            }
        });
        return editText;
    }

    public void addToView(View v){
        numberFields.add((EditText) v);
        llContainer.addView(v);
    }

    public LinearLayout getLlContainer() {
        return llContainer;
    }

    public void setLlContainer(LinearLayout llContainer) {
        this.llContainer = llContainer;
    }

    public List<EditText> getNumberFields() {
        return numberFields;
    }

    public void setNumberFields(List<EditText> numberFields) {
        this.numberFields = numberFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
