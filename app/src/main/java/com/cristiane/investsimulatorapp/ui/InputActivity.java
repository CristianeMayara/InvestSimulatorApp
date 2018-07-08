package com.cristiane.investsimulatorapp.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cristiane.investsimulatorapp.R;
import com.cristiane.investsimulatorapp.model.Result;
import com.cristiane.investsimulatorapp.viewmodel.InputViewModel;

/**
 * Created by cristiane on 04/07/2018.
 */

public class InputActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final String TAG = "InputActivity";
    private EditText etValueInput;
    private EditText etDateInput;
    private EditText etCdiPercentageInput;
    private Button btSimulate;

    private InputViewModel model;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        initComponents();
        initViewModel();
        updateValues();
    }

    private void initComponents() {
        etValueInput = findViewById(R.id.et_value_input);
        etDateInput = findViewById(R.id.et_date_input);
        etCdiPercentageInput = findViewById(R.id.et_cdi_percentage_input);
        btSimulate = findViewById(R.id.bt_simulate);
        btSimulate.setOnClickListener(onClickListener);
    }

    private void initViewModel() {
        model = ViewModelProviders.of(this).get(InputViewModel.class);
        attachObserver(model);
    }

    private void attachObserver(InputViewModel viewModel) {
        viewModel.getResult().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result result) {
                if (result != null) {
                    //hideProgress();
                    openResultScreen(result);
                } else {
                    //showProgress();
                }
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_simulate:
                    model.loadResult(32323.0, "CDI", 123.0, false, "2023-03-03");
                    break;
            }
        }
    };

    private void updateValues() {
        etValueInput.setText(getString(R.string.real_value, model.getInvestedAmount()));
        etDateInput.setText(model.getMaturityDate());
        etCdiPercentageInput.setText(getString(R.string.percentage_value, model.getRate()));
    }

    private void openResultScreen(Result result) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.ARG_RESULT, result);
        startActivity(intent);
    }
}
