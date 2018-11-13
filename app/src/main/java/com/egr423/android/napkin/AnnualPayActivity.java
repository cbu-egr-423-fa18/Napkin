package com.egr423.android.napkin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class AnnualPayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_annual_pay);
        updateOutputs();

        // Inputs
        EditText hourlyPay = (EditText) findViewById(R.id.hourlyPayInput);
        EditText annualPay = (EditText) findViewById(R.id.annualPayInput);
        EditText workHoursPerWeek = (EditText) findViewById(R.id.workHoursPerWeek);
        EditText workWeeksPerYear = (EditText) findViewById(R.id.workWeeksPerYear);

        workWeeksPerYear.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "52")});

        // Each input needs to have an addTextChangedListener to dynamically change the outputs
        hourlyPay.addTextChangedListener(new TextWatcher() {
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

        annualPay.addTextChangedListener(new TextWatcher() {
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

        workHoursPerWeek.addTextChangedListener(new TextWatcher() {
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

        workWeeksPerYear.addTextChangedListener(new TextWatcher() {
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
            EditText hourlyPayInput = (EditText) findViewById(R.id.hourlyPayInput);
            EditText annualPayInput = (EditText) findViewById(R.id.annualPayInput);
            EditText workHoursPerWeekInput = (EditText) findViewById(R.id.workHoursPerWeek);
            EditText workWeeksPerYearInput = (EditText) findViewById(R.id.workWeeksPerYear);

            // Input values
            double hourlyPay = Double.parseDouble(hourlyPayInput.getText().toString());
            double annualPay = Double.parseDouble(annualPayInput.getText().toString());
            double workHoursPerWeek = Double.parseDouble(workHoursPerWeekInput.getText().toString());
            double workWeeksPerYear = Double.parseDouble(workWeeksPerYearInput.getText().toString());

            // Outputs
            TextView hourlyPayOutput = (TextView) findViewById(R.id.hourlyPayOutputDisplay);
            TextView annualPayOutput = (TextView) findViewById(R.id.annualPayOutputDisplay);

            double expectedHourlyPay = annualPay / (workHoursPerWeek * workWeeksPerYear);
            double expectedAnnualPay = hourlyPay * workHoursPerWeek * workWeeksPerYear;

            // Update the outputs with dollar signs and only 2 decimals
            hourlyPayOutput.setText(String.format("$%s", String.format("%.2f", expectedHourlyPay)));
            annualPayOutput.setText(String.format("$%s", String.format("%.2f", expectedAnnualPay)));
        } catch (NumberFormatException e) {
            return;
        }
    }
}
