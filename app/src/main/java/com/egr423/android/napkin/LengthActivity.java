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

public class LengthActivity extends AppCompatActivity {
    private double inchToMeter = 0.0254;
    private double footToMeter = 0.3048;
    private double mileToMeter = 1609.344;
    private double mmToMeter = 0.001;
    private double cmToMeter = 0.01;
    private double kmToMeter = 1000;
    private double yardToMeter = 0.9144;

    private double factor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        EditText inputAmount = (EditText) findViewById(R.id.inputAmount);
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
            case "Millimeter":
                factor = mmToMeter;
                break;
            case "Centimeter":
                factor = cmToMeter;
                break;
            case "Meter":
                factor = 1;
                break;
            case "Inch":
                factor = inchToMeter;
                break;
            case "Foot":
                factor = footToMeter;
                break;
            case "Yard":
                factor = yardToMeter;
                break;
            case "Mile":
                factor = mileToMeter;
                break;
        }

    }
    private void updateOutputs() {
        try {
            EditText inputAmount = (EditText) findViewById(R.id.inputAmount);
            TextView outputAmount = (TextView) findViewById(R.id.ConvertedLength);
            Spinner inputType = (Spinner) findViewById(R.id.ConvertFromSelector);
            Spinner outputType = (Spinner) findViewById(R.id.ConvertToSelector);
            String amountString = inputAmount.getText().toString();
            String inputString = inputType.getSelectedItem().toString();
            String outputString = outputType.getSelectedItem().toString();

            double amount = Double.parseDouble(inputAmount.getText().toString());
            convert(inputString);
            double gramAmount = amount * factor;
            convert(outputString);
            double convertAmount = gramAmount / factor;
            outputAmount.setText(Double.toString(convertAmount));


        } catch (Exception E) {

        }
    }
}
