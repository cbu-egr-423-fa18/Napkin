package com.egr423.android.napkin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class GPAActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        //Inputs
        EditText class1Credits = (EditText) findViewById(R.id.Class1Credits);
        EditText class2Credits = (EditText) findViewById(R.id.Class2Credits);
        EditText class3Credits = (EditText) findViewById(R.id.Class3Credits);
        EditText class4Credits = (EditText) findViewById(R.id.Class4Credits);
        EditText class5Credits = (EditText) findViewById(R.id.Class5Credits);
        EditText class6Credits = (EditText) findViewById(R.id.Class6Credits);
        EditText class7Credits = (EditText) findViewById(R.id.Class7Credits);

        Spinner class1Grade = (Spinner) findViewById(R.id.Class1Grade);
        Spinner class2Grade = (Spinner) findViewById(R.id.Class2Grade);
        Spinner class3Grade = (Spinner) findViewById(R.id.Class3Grade);
        Spinner class4Grade = (Spinner) findViewById(R.id.Class4Grade);
        Spinner class5Grade = (Spinner) findViewById(R.id.Class5Grade);
        Spinner class6Grade = (Spinner) findViewById(R.id.Class6Grade);
        Spinner class7Grade = (Spinner) findViewById(R.id.Class7Grade);


        //Listeners

        class1Credits.addTextChangedListener(new TextWatcher() {
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
        class2Credits.addTextChangedListener(new TextWatcher() {
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
        class3Credits.addTextChangedListener(new TextWatcher() {
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
        class4Credits.addTextChangedListener(new TextWatcher() {
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
        class5Credits.addTextChangedListener(new TextWatcher() {
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
        class6Credits.addTextChangedListener(new TextWatcher() {
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
        class7Credits.addTextChangedListener(new TextWatcher() {
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
        class1Grade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateOutputs();
            }
        });


        }

    private void updateOutputs() {
    }


}


