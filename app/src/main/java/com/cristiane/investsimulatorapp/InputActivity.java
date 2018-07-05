package com.cristiane.investsimulatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cristiane on 04/07/2018.
 */

public class InputActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "InputActivity";
    private EditText etValueInput;
    private EditText etDateInput;
    private EditText etCdiPercentageInput;
    private Button btSimulate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        initComponents();
    }

    private void initComponents() {
        etValueInput = findViewById(R.id.et_value_input);
        etDateInput = findViewById(R.id.et_date_input);
        etCdiPercentageInput = findViewById(R.id.et_cdi_percentage_input);
        btSimulate = findViewById(R.id.bt_simulate);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_simulate:
                openResultScreen();
                break;
        }
    }

    private void openResultScreen() {
        //Intent intent = new Intent(this, ResultActivity.class);
        //startActivity(intent);
    }
}
