package com.cristiane.investsimulatorapp.ui;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cristiane.investsimulatorapp.R;
import com.cristiane.investsimulatorapp.model.Result;
import com.cristiane.investsimulatorapp.viewmodel.InputViewModel;

/**
 * Created by cristiane on 04/07/2018.
 */

@SuppressWarnings("deprecation")
public class InputActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    public static final String TAG = InputActivity.class.getSimpleName();

    private Context context;
    private EditText etValueInput;
    private EditText etDateInput;
    private EditText etCdiPercentageInput;
    private Button btSimulate;

    private InputViewModel model;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    @NonNull
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
    }

    private void initComponents() {
        context = this;
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
//                    showSimulationSuccessfully();
                    openResultScreen(result);
                }
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_simulate:
                    model.loadResult(context, etValueInput.getText().toString(), "CDI", etCdiPercentageInput.getText().toString(), false, etDateInput.getText().toString());
                    break;
            }
        }
    };

    public void showValueInputError() {
        etValueInput.setError(getString(R.string.error_invalid_value));
        etValueInput.requestFocus();
    }

    public void showDateError() {
        etDateInput.setError(getString(R.string.error_invalid_date));
        etDateInput.requestFocus();
    }

    public void showCdiPercentageInputError() {
        etCdiPercentageInput.setError(getString(R.string.error_invalid_rate));
        etCdiPercentageInput.requestFocus();
    }

    public void showSimulationFailed() {
        Toast.makeText(this, getString(R.string.error_simulation_failed), Toast.LENGTH_SHORT).show();
    }

    public void showSimulationSuccessfully() {
        Toast.makeText(this, getString(R.string.simulation_successfully), Toast.LENGTH_SHORT).show();
    }

    private void openResultScreen(Result result) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.ARG_RESULT, result);
        startActivity(intent);
    }
}
