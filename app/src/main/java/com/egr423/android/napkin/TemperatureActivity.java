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
import android.widget.Switch;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        EditText inputAmount = (EditText) findViewById(R.id.tempAmount);
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
    private void updateOutputs() {
        try {
            EditText inputAmount = (EditText) findViewById(R.id.tempAmount);
            TextView outputAmount = (TextView) findViewById(R.id.ConvertedAmount);
            Spinner inputType = (Spinner) findViewById(R.id.ConvertFromSelector);
            Spinner outputType = (Spinner) findViewById(R.id.ConvertToSelector);
            String amountString = inputAmount.getText().toString();
            String inputString = inputType.getSelectedItem().toString();
            String outputString = outputType.getSelectedItem().toString();

            double amount = Double.parseDouble(inputAmount.getText().toString());
            double result = 0;

            switch(inputString){
                case "Kelvin":
                    switch (outputString){
                        case "Celsius":
                            result = amount - 273.15;
                            break;
                        case "Fahrenheit":
                            result = (amount - 273.15) * 9.0/5.0 + 32;
                            break;
                        case "Kelvin":{
                            result = amount;
                            break;
                        }
                    }
                    break;
                case "Fahrenheit":
                    switch (outputString){
                        case "Celsius":
                            result = (amount - 32) * 5.0/9.0;
                            break;
                        case "Kelvin":
                            result = (amount + 459.67) * 5.0/9.0;
                            break;
                        case "Fahrenheit":{
                            result = amount;
                            break;
                        }
                    }
                    break;
                case "Celsius":
                    switch (outputString){
                        case "Fahrenheit":
                            result = (amount * 9.0/5.0) + 32;
                            break;
                        case "Kelvin":
                            result = amount + 273.15;
                            break;
                        case "Celsius":{
                            result = amount;
                            break;
                        }
                    }
                    break;
            }
            outputAmount.setText(Double.toString(result));

        } catch (Exception E) {

        }
    }
}
