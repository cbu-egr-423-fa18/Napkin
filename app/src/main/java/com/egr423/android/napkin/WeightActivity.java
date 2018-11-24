package com.egr423.android.napkin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class WeightActivity extends AppCompatActivity {
    private double kgToGram = 1000;
    private double ounceToGram = 28.349523125;
    private double poundToGram = 453.59237;

    private double factor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        EditText inputAmount = (EditText) findViewById(R.id.initialWeight);
        Spinner inputType = (Spinner) findViewById(R.id.ConvertFromSelector);
        Spinner outputType = (Spinner) findViewById(R.id.ConvertToSelector);
        inputAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateOutputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }});
        View.OnTouchListener spinListen = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                updateOutputs();
                return false;
            }
        };
        inputType.setOnTouchListener(spinListen);
        outputType.setOnTouchListener(spinListen);
    }

    private void convert(String type){
        switch (type){
            case "Pound":
                factor = poundToGram;
                break;
            case "Ounce":
                factor = ounceToGram;
                break;
            case "Kilogram":
                factor = kgToGram;
                break;
            case "Gram":
                factor = 1;
                break;
        }

    }

    private void updateOutputs() {
        try {
            EditText inputAmount = (EditText) findViewById(R.id.initialWeight);
            TextView outputAmount = (TextView) findViewById(R.id.ConvertedAmount);
            Spinner inputType = (Spinner) findViewById(R.id.ConvertFromSelector);
            Spinner outputType = (Spinner) findViewById(R.id.ConvertToSelector);
            String amountString = inputAmount.getText().toString();
            String inputString = inputType.getSelectedItem().toString();
            String outputString = outputType.getSelectedItem().toString();

            double amount = Double.parseDouble(amountString);
            convert(inputString);
            double gramAmount = amount * factor;
            convert(outputString);
            double convertAmount = gramAmount / factor;
            outputAmount.setText(Double.toString(convertAmount));


        } catch (Exception E) {

        }
    }
}
