package com.egr423.android.napkin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class InterestActivity extends AppCompatActivity {

    private ArrayList<EditText> inputs;
    private double term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_interest);

        //User Input Fields
            inputs = new ArrayList<EditText>();
            inputs.add((EditText) findViewById(R.id.principalInput));
            inputs.add((EditText) findViewById(R.id.interestRateInput));
            inputs.add((EditText) findViewById(R.id.paymentPeriodInput));

        Spinner termSelector = (Spinner) findViewById(R.id.termInputSelector);

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

        termSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    //annually
                    case 0:
                        break;
                    //monthly
                    case 1:
                        break;
                    default:
                        break;
                }

                updateOutputs();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void updateOutputs(){
        //setup
        double monthlyPayment = 0;

        try {
            //calculation of monthly payment
            //take input values
            double principal = Double.parseDouble(((EditText) findViewById(R.id.principalInput)).getText().toString());
            double rate = Double.parseDouble(((EditText) findViewById(R.id.interestRateInput)).getText().toString()) / 100 / 12;
            int periods = Integer.parseInt(((EditText) findViewById(R.id.paymentPeriodInput)).getText().toString());

            double numerator = Math.pow(1 + rate, periods);
            double denominator = Math.pow(1 + rate, periods) - 1;
            monthlyPayment = principal * rate * (Math.pow(1 + rate, periods)/(Math.pow(1+rate,periods)-1));

            //outputs
            TextView monthlyPaymentOutput = findViewById(R.id.paymentOutput);
            TextView totalPaymentOutput = findViewById(R.id.totalPaymentOutput);

            //output to screen
            monthlyPaymentOutput.setText(String.format("$%s", String.format("%.2f", monthlyPayment)));
            totalPaymentOutput.setText(String.format("$%s", String.format("%.2f", monthlyPayment * periods)));
        } catch(NumberFormatException E){

        }

    }
}
