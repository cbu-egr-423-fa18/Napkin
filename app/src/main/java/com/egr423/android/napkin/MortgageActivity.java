package com.egr423.android.napkin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MortgageActivity extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences("com.egr423.android.napkin", MODE_PRIVATE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage);

        // Inputs
        EditText mortgageAmount = (EditText) findViewById(R.id.mortgageAmountInput);
        EditText interestRate = (EditText) findViewById(R.id.interestRateInput);
        EditText mortgagePeriod = (EditText) findViewById(R.id.mortgagePeriodInput);

        mortgageAmount.setText(prefs.getString("mortageAmount", "0"), TextView.BufferType.EDITABLE);
        interestRate.setText(prefs.getString("interestRateMortage", "0"), TextView.BufferType.EDITABLE);
        mortgagePeriod.setText(prefs.getString("mortagePeriod", "0"), TextView.BufferType.EDITABLE);
        updateOutputs();


        // Each input needs to have an addTextChangedListener to dynamically change the outputs
        mortgageAmount.addTextChangedListener(new TextWatcher() {
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

        interestRate.addTextChangedListener(new TextWatcher() {
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

        mortgagePeriod.addTextChangedListener(new TextWatcher() {
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
            EditText mortgageAmountInput = (EditText) findViewById(R.id.mortgageAmountInput);
            EditText interestRateInput = (EditText) findViewById(R.id.interestRateInput);
            EditText mortgagePeriodInput = (EditText) findViewById(R.id.mortgagePeriodInput);

            prefs.edit().putString("mortageAmount",mortgageAmountInput.getText().toString()).apply();
            prefs.edit().putString("interestRateMortage",interestRateInput.getText().toString()).apply();
            prefs.edit().putString("mortagePeriod",mortgagePeriodInput.getText().toString()).apply();

            // Input values
            double mortgageAmount = Double.parseDouble(mortgageAmountInput.getText().toString());
            double interestRate = Double.parseDouble(interestRateInput.getText().toString()) / 100;
            double mortgagePeriod = Double.parseDouble(mortgagePeriodInput.getText().toString());

            // Outputs
            TextView totalCostOfMortgage = (TextView) findViewById(R.id.totalCostOfMortgageOutput);
            TextView monthlyPayment = (TextView) findViewById(R.id.monthlyPaymentOutput);

            // Convert the interestRate to a monthly rate
            interestRate /= 12.0;

            // Convert the mortgagePeriod to months
            mortgagePeriod *= 12.0;

            double expectedMonthlyPayment = mortgageAmount *
                    (interestRate * Math.pow((1 + interestRate), mortgagePeriod)) /
                    ((Math.pow((1 + interestRate), mortgagePeriod)) - 1);
            double expectedCostOfMortgage = ((interestRate * mortgageAmount) /
                    (1 - (Math.pow(1 + interestRate, -mortgagePeriod)))) * mortgagePeriod;

            // Update the outputs with dollar signs and only 2 decimals
            totalCostOfMortgage.setText(String.format("$%s", String.format("%.2f", expectedCostOfMortgage)));
            monthlyPayment.setText(String.format("$%s", String.format("%.2f", expectedMonthlyPayment)));
        } catch (NumberFormatException e) {
            return;
        }
    }
}
