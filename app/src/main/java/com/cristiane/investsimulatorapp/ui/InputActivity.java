package com.cristiane.investsimulatorapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cristiane.investsimulatorapp.R;
import com.cristiane.investsimulatorapp.viewmodel.InputViewModel;

/**
 * Created by cristiane on 04/07/2018.
 */

public class InputActivity extends AppCompatActivity {

    public static final String TAG = "InputActivity";
    private EditText etValueInput;
    private EditText etDateInput;
    private EditText etCdiPercentageInput;
    private Button btSimulate;

    private InputViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        initComponents();

        model = ViewModelProviders.of(this).get(InputViewModel.class);
    }

    private void initComponents() {
        etValueInput = findViewById(R.id.et_value_input);
        etDateInput = findViewById(R.id.et_date_input);
        etCdiPercentageInput = findViewById(R.id.et_cdi_percentage_input);
        btSimulate = findViewById(R.id.bt_simulate);
        btSimulate.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_simulate:
                    openResultScreen();
                    break;
            }
        }
    };

    private void openResultScreen() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}
