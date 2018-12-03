package com.egr423.android.napkin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.view.WindowManager;

public class AnnualPayActivity extends AppCompatActivity {
    SharedPreferences prefs;

    // Input Variables
    public double hourlyPay;
    public double annualPay;
    public double workHoursPerWeek;
    public double workWeeksPerYear;

    // Output Variables
    public double expectedHourlyPay;
    public double expectedAnnualPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences("com.egr423.android.napkin", MODE_PRIVATE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_annual_pay);

        // Inputs
        EditText hourlyPay = (EditText) findViewById(R.id.hourlyPayInput);
        EditText annualPay = (EditText) findViewById(R.id.annualPayInput);
        EditText workHoursPerWeek = (EditText) findViewById(R.id.workHoursPerWeek);
        EditText workWeeksPerYear = (EditText) findViewById(R.id.workWeeksPerYear);

        hourlyPay.setText(prefs.getString("hourlyPay", "0"), TextView.BufferType.EDITABLE);
        annualPay.setText(prefs.getString("annualPay", "0"), TextView.BufferType.EDITABLE);
        workHoursPerWeek.setText(prefs.getString("workHoursPerWeek", "0"), TextView.BufferType.EDITABLE);
        workWeeksPerYear.setText(prefs.getString("workWeeksPerYear", "0"), TextView.BufferType.EDITABLE);
        updateOutputs();
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

            prefs.edit().putString("hourlyPay",hourlyPayInput.getText().toString()).apply();
            prefs.edit().putString("annualPay",annualPayInput.getText().toString()).apply();
            prefs.edit().putString("workHoursPerWeek",workHoursPerWeekInput.getText().toString()).apply();
            prefs.edit().putString("workWeeksPerYear",workWeeksPerYearInput.getText().toString()).apply();

            // Input values
            hourlyPay = Double.parseDouble(hourlyPayInput.getText().toString());
            annualPay = Double.parseDouble(annualPayInput.getText().toString());
            workHoursPerWeek = Double.parseDouble(workHoursPerWeekInput.getText().toString());
            workWeeksPerYear = Double.parseDouble(workWeeksPerYearInput.getText().toString());

            // Outputs
            TextView hourlyPayOutput = (TextView) findViewById(R.id.totalCostOfMortgageOutput);
            TextView annualPayOutput = (TextView) findViewById(R.id.annualPayOutputDisplay);

            expectedHourlyPay = annualPay / (workHoursPerWeek * workWeeksPerYear);
            expectedAnnualPay = hourlyPay * workHoursPerWeek * workWeeksPerYear;

            // Update the outputs with dollar signs and only 2 decimals
            hourlyPayOutput.setText(String.format("$%s", String.format("%.2f", expectedHourlyPay)));
            annualPayOutput.setText(String.format("$%s", String.format("%.2f", expectedAnnualPay)));
        } catch (NumberFormatException e) {
            return;
        }
    }
}
