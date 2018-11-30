package com.egr423.android.napkin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class SalesActivity extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences("com.egr423.android.napkin", MODE_PRIVATE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        // Inputs
        EditText initialPrice = (EditText) findViewById(R.id.initialPrice);
        EditText salePercentage = (EditText) findViewById(R.id.salePercentage);
        EditText salesTax = (EditText) findViewById(R.id.salesTax);
        EditText itemQuantity = (EditText) findViewById(R.id.itemQuantity);

        initialPrice.setText(prefs.getString("initialPrice", "0"), TextView.BufferType.EDITABLE);
        salePercentage.setText(prefs.getString("salesPercentage", "0"), TextView.BufferType.EDITABLE);
        salesTax.setText(prefs.getString("salesTax", "0"), TextView.BufferType.EDITABLE);
        itemQuantity.setText(prefs.getString("itemQuantity", "0"), TextView.BufferType.EDITABLE);

        // Each input needs to have an addTextChangedListener to dynamically change the outputs
        initialPrice.addTextChangedListener(new TextWatcher() {
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

        salePercentage.addTextChangedListener(new TextWatcher() {
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

        salesTax.addTextChangedListener(new TextWatcher() {
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

        itemQuantity.addTextChangedListener(new TextWatcher() {
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
            EditText initialPriceInput = (EditText) findViewById(R.id.initialPrice);
            EditText salePercentageInput = (EditText) findViewById(R.id.salePercentage);
            EditText salesTaxInput = (EditText) findViewById(R.id.salesTax);
            EditText itemQuantityInput = (EditText) findViewById(R.id.itemQuantity);

            prefs.edit().putString("initialPrice",initialPriceInput.getText().toString()).apply();
            prefs.edit().putString("salesPercentage",salePercentageInput.getText().toString()).apply();
            prefs.edit().putString("salesTax",salesTaxInput.getText().toString()).apply();
            prefs.edit().putString("itemQuantity",itemQuantityInput.getText().toString()).apply();

            // Input values
            double initialPrice = Double.parseDouble(initialPriceInput.getText().toString());
            double salePercentage = Double.parseDouble(salePercentageInput.getText().toString()) / 100;
            double salesTax = Double.parseDouble(salesTaxInput.getText().toString()) / 100;
            double itemQuantity = Double.parseDouble(itemQuantityInput.getText().toString());

            // Outputs
            TextView costPerItem = (TextView) findViewById(R.id.ConvertedAmount);
            TextView totalCost = (TextView) findViewById(R.id.totalCost);

            // Get the input values and then calculate what the output values will be
            double expectedCostPerItem = (initialPrice - (initialPrice * salePercentage)) * (1 + salesTax);
            double expectedTotalCost = expectedCostPerItem * itemQuantity;

            // Update the outputs with dollar signs and only 2 decimals
            costPerItem.setText(String.format("$%s", String.format("%.2f", expectedCostPerItem)));
            totalCost.setText(String.format("$%s", String.format("%.2f", expectedTotalCost)));
        } catch (NumberFormatException e) {
            return;
        }
    }
}
