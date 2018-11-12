package com.egr423.android.napkin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class TipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        // Inputs
        EditText waysSplit = (EditText) findViewById(R.id.waysSplit);
        EditText tipPercentage = (EditText) findViewById(R.id.tipPercentage);
        EditText totalBill = (EditText) findViewById(R.id.totalBill);
        EditText taxRate = (EditText) findViewById(R.id.taxRate);

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

        taxRate.addTextChangedListener(new TextWatcher() {
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
        // Inputs
        EditText waysSplitInput = (EditText) findViewById(R.id.waysSplit);
        EditText tipPercentageInput = (EditText) findViewById(R.id.tipPercentage);
        EditText totalBillInput = (EditText) findViewById(R.id.totalBill);
        EditText taxRateInput = (EditText) findViewById(R.id.taxRate);

        // Input values
        double waysSplit = Double.parseDouble(waysSplitInput.getText().toString());
        double tipPercentage = Double.parseDouble(tipPercentageInput.getText().toString()) / 100;
        double totalBill = Double.parseDouble(totalBillInput.getText().toString());
        double taxRate = Double.parseDouble(taxRateInput.getText().toString()) / 100;

        // Outputs
        TextView costPerPerson = (TextView) findViewById(R.id.costPerPerson);
        TextView totalCostOfBill = (TextView) findViewById(R.id.totalCostOfBill);

        double expectedTotalCostOfBill = totalBill * (1 + tipPercentage) * (1 + taxRate);
        double expectedCostPerPerson = expectedTotalCostOfBill / waysSplit;

        // Update the outputs with dollar signs and only 2 decimals
        costPerPerson.setText(String.format("$%s", String.format("%.2f", expectedCostPerPerson)));
        totalCostOfBill.setText(String.format("$%s", String.format("%.2f", expectedTotalCostOfBill)));
    }
}
