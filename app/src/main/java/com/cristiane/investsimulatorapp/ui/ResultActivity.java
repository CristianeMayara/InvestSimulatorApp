package com.cristiane.investsimulatorapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cristiane.investsimulatorapp.R;
import com.cristiane.investsimulatorapp.model.Result;
import com.cristiane.investsimulatorapp.viewmodel.ResultViewModel;

/**
 * Created by cristiane on 05/07/2018.
 */

public class ResultActivity extends AppCompatActivity {

    public static final String TAG = "ResultActivity";
    public static final String ARG_RESULT = "ARG_RESULT";

    private TextView tvValue;
    private TextView tvTotalIncome;

    private TextView tvAppliedValue;
    private TextView tvGrossValue;
    private TextView tvIncomeValue;
    private TextView tvIncomeTax;
    private TextView tvNetValue;

    private TextView tvRedemptionDate;
    private TextView tvConsecutiveDays;
    private TextView tvMonthlyIncome;
    private TextView tvCdiPercentage;
    private TextView tvAnnualProfitability;
    private TextView tvPeriodProfitability;

    private Button btSimulateAgain;

    private ResultViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initComponents();
        initViewModel();
        setArguments();
        updateValues();
    }

    private void initComponents() {
        tvValue = findViewById(R.id.tv_value);
        tvTotalIncome = findViewById(R.id.tv_total_income);

        tvAppliedValue = findViewById(R.id.tv_applied_value_result);
        tvGrossValue = findViewById(R.id.tv_gross_value_result);
        tvIncomeValue = findViewById(R.id.tv_income_value_result);
        tvIncomeTax = findViewById(R.id.tv_income_tax_result);
        tvNetValue = findViewById(R.id.tv_net_value_result);

        tvRedemptionDate = findViewById(R.id.tv_redemption_date_result);
        tvConsecutiveDays = findViewById(R.id.tv_consecutive_days_result);
        tvMonthlyIncome = findViewById(R.id.tv_monthly_income_result);
        tvCdiPercentage = findViewById(R.id.tv_cdi_percentage_result);
        tvAnnualProfitability = findViewById(R.id.tv_annual_profitability_result);
        tvPeriodProfitability = findViewById(R.id.tv_period_profitability_result);

        btSimulateAgain = findViewById(R.id.bt_simulate_again);
        btSimulateAgain.setOnClickListener(onClickListener);
    }

    private void initViewModel() {
        model = ViewModelProviders.of(this).get(ResultViewModel.class);
    }

    private void setArguments() {
        if (getIntent() != null) {
            model.setResult((Result) getIntent().getExtras().getParcelable(ARG_RESULT));
        }
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_simulate_again:
                    finish();
                    break;
            }
        }
    };

    private void updateValues() {
        //model.loadResult(32323.0, "CDI", 123.0, false, "2023-03-03");

        tvValue.setText(getString(R.string.real_value, model.getResult().getGrossAmount()));
        tvTotalIncome.setText(getString(R.string.total_income, model.getResult().getGrossAmountProfit()));

        tvAppliedValue.setText(getString(R.string.real_value, model.getResult().getInvestmentParameter().getInvestedAmount()));
        tvGrossValue.setText(getString(R.string.real_value, model.getResult().getGrossAmount()));
        tvIncomeValue.setText(getString(R.string.real_value, model.getResult().getGrossAmountProfit()));
        tvIncomeTax.setText(getString(R.string.income_tax_value, model.getResult().getTaxesAmount(), model.getResult().getTaxesRate()));
        tvNetValue.setText(getString(R.string.real_value, model.getResult().getNetAmount()));

        tvRedemptionDate.setText(model.getResult().getInvestmentParameter().getMaturityDate());
        tvConsecutiveDays.setText(String.valueOf(model.getResult().getInvestmentParameter().getMaturityBusinessDays()));
        tvMonthlyIncome.setText(getString(R.string.percentage_value, model.getResult().getAnnualGrossRateProfit()));
        tvCdiPercentage.setText(getString(R.string.percentage_value, model.getResult().getInvestmentParameter().getRate()));
        tvAnnualProfitability.setText(getString(R.string.percentage_value, model.getResult().getInvestmentParameter().getYearlyInterestRate()));
        tvPeriodProfitability.setText(getString(R.string.percentage_value, model.getResult().getRateProfit()));
    }
}
