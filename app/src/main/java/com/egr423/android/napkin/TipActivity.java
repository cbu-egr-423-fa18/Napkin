package com.egr423.android.napkin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class TipActivity  extends AppCompatActivity  {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences("com.egr423.android.napkin", MODE_PRIVATE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        // Inputs
        EditText waysSplit = (EditText) findViewById(R.id.waysSplit);
        EditText tipPercentage = (EditText) findViewById(R.id.tipPercentage);
        EditText totalBill = (EditText) findViewById(R.id.totalBill);

        waysSplit.setText(prefs.getString("waysSplit", "0"), TextView.BufferType.EDITABLE);
        tipPercentage.setText(prefs.getString("tipPercentage", "0"), TextView.BufferType.EDITABLE);
        totalBill.setText(prefs.getString("totalBill", "0"), TextView.BufferType.EDITABLE);

        updateOutputs();

        // Each input needs to have an addTextChangedListener to dynamically change the outputs
        waysSplit.addTextChangedListener(new TextWatcher() {
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

        tipPercentage.addTextChangedListener(new TextWatcher() {
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

        totalBill.addTextChangedListener(new TextWatcher() {
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

    /**
     * Updates the output fields to the appropriate number whenever an input field is changed.
     */
    private void updateOutputs() {
        try {
            // Inputs
            EditText waysSplitInput = (EditText) findViewById(R.id.waysSplit);
            EditText tipPercentageInput = (EditText) findViewById(R.id.tipPercentage);
            EditText totalBillInput = (EditText) findViewById(R.id.totalBill);

            prefs.edit().putString("waysSplit",waysSplitInput.getText().toString()).apply();
            prefs.edit().putString("tipPercentage",tipPercentageInput.getText().toString()).apply();
            prefs.edit().putString("totalBill",totalBillInput.getText().toString()).apply();

            // Input values
            double waysSplit = Double.parseDouble(waysSplitInput.getText().toString());
            double tipPercentage = Double.parseDouble(tipPercentageInput.getText().toString()) / 100;
            double totalBill = Double.parseDouble(totalBillInput.getText().toString());

            // Outputs
            TextView costPerPerson = (TextView) findViewById(R.id.costPerPerson);
            TextView totalCostOfBill = (TextView) findViewById(R.id.totalCostOfBill);

            double expectedTotalCostOfBill = totalBill * (1 + tipPercentage);
            double expectedCostPerPerson = expectedTotalCostOfBill / waysSplit;

            // Update the outputs with dollar signs and only 2 decimals
            costPerPerson.setText(String.format("$%s", String.format("%.2f", expectedCostPerPerson)));
            totalCostOfBill.setText(String.format("$%s", String.format("%.2f", expectedTotalCostOfBill)));
        } catch (NumberFormatException e){
            return;
        }

    }
}
