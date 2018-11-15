package com.egr423.android.napkin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CumulativeGPAActivity extends AppCompatActivity {

    private ArrayList<EditText> inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_cumulative_gpa);

        //User Input Fields
        inputs = new ArrayList<EditText>();
        inputs.add((EditText) findViewById(R.id.currentGPAInput));
        inputs.add((EditText) findViewById(R.id.totalCreditsInput));
        inputs.add((EditText) findViewById(R.id.classGradeInput));
        inputs.add((EditText) findViewById(R.id.classCreditsInput));

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

    }

    private void updateOutputs(){

        try {

            //Taking user inputs
            double currentGPA = Double.parseDouble(((EditText) findViewById(R.id.currentGPAInput)).getText().toString());
            double totalCredits = Double.parseDouble(((EditText) findViewById(R.id.totalCreditsInput)).getText().toString());
            double classGrade = Double.parseDouble(((EditText) findViewById(R.id.classGradeInput)).getText().toString());
            double classCredits = Double.parseDouble(((EditText) findViewById(R.id.classCreditsInput)).getText().toString());

            //Calculating GPA
            double cumulativeGPAOutput = (currentGPA * totalCredits) + (classGrade * classCredits) / (totalCredits + classCredits);

            //Output
            TextView cumulativeGPA = findViewById(R.id.cumulativeGPAOutput);

            //output to screen
            cumulativeGPA.setText(String.format("%.1f", cumulativeGPAOutput));

        } catch(NumberFormatException E){

        }

    }
}
