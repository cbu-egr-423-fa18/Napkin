package com.egr423.android.napkin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CumulativeGPAActivity extends AppCompatActivity {
    SharedPreferences prefs;
    private ArrayList<EditText> inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_cumulative_gpa);
        prefs = getSharedPreferences("com.egr423.android.napkin", MODE_PRIVATE);

        //User Input Fields
        inputs = new ArrayList<EditText>();
        inputs.add((EditText) findViewById(R.id.currentGPAInput));
        inputs.add((EditText) findViewById(R.id.totalCreditsInput));
        inputs.add((EditText) findViewById(R.id.classCreditsInput));
        Spinner gradeSelector = findViewById(R.id.classGradeSelector);
        inputs.get(0).setText(prefs.getString("currentGPAC", "0"));
        inputs.get(1).setText(prefs.getString("totalCreditsC", "0"));
        inputs.get(2).setText(prefs.getString("classCreditsC", "0"));
        gradeSelector.setSelection(prefs.getInt("gradeSelectorC", 0), false);
        updateOutputs();

        //Set listeners
        for(EditText e: inputs){
            e.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    updateOutputs();
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        gradeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateOutputs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void updateOutputs(){

        try {

            //Taking user inputs
            double currentGPA = Double.parseDouble(((EditText) findViewById(R.id.currentGPAInput)).getText().toString());
            double totalCredits = Double.parseDouble(((EditText) findViewById(R.id.totalCreditsInput)).getText().toString());
            double classCredits = Double.parseDouble(((EditText) findViewById(R.id.classCreditsInput)).getText().toString());
            Spinner gradeSelector = findViewById(R.id.classGradeSelector);

            prefs.edit().putString("currentGPAC",((EditText) findViewById(R.id.currentGPAInput)).getText().toString()).apply();
            prefs.edit().putString("totalCreditsC",((EditText) findViewById(R.id.totalCreditsInput)).getText().toString()).apply();
            prefs.edit().putString("classCreditsC",((EditText) findViewById(R.id.classCreditsInput)).getText().toString()).apply();
            prefs.edit().putInt("gradeSelectorC", gradeSelector.getSelectedItemPosition()).apply();

            //Calculating GPA
            double classGradeValue = gradeValue(gradeSelector.getSelectedItem().toString());
            double cumulativeGPAOutput = ((currentGPA * totalCredits) + (classGradeValue * classCredits)) / (totalCredits + classCredits);
            Log.d("GPA calculation", Double.toString(currentGPA * totalCredits) + " + " + (classGradeValue * classCredits) + " / " + (totalCredits + classCredits));
            Log.d("classGradeValue", Double.toString(classGradeValue));
            Log.d("gpaValue", Double.toString(cumulativeGPAOutput));
            //Output
            TextView cumulativeGPA = findViewById(R.id.cumulativeGPAOutput);

            //output to screen
           // cumulativeGPA.setText(String.format("%.2f", cumulativeGPAOutput));
            cumulativeGPA.setText(String.valueOf(cumulativeGPAOutput));

        } catch(NumberFormatException E){

        }

    }

    double gradeValue(String gradeText){
        Log.d("grade text", gradeText);
        double gradeval = 0;
        switch (gradeText){
            case "A":
                gradeval = 4.0;
                break;
            case "A-":
                gradeval = 3.7;
                break;
            case "B+":
                gradeval = 3.3;
                break;
            case "B":
                gradeval = 3.0;
                break;
            case "B-":
                gradeval = 2.7;
                break;
            case "C+":
                gradeval = 2.3;
                break;
            case "C":
                gradeval = 2.0;
                break;
            case "C-":
                gradeval = 1.7;
                break;
            case "D+":
                gradeval = 1.3;
                break;
            case "D":
                gradeval = 1.0;
                break;
            case "D-":
                gradeval = 0.7;
                break;
            case "F":
                gradeval = 0.0;
                break;
        }
        return gradeval;
    }
}
