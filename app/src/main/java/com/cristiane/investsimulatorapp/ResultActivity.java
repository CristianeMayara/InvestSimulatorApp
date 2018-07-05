package com.cristiane.investsimulatorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by cristiane on 05/07/2018.
 */

public class ResultActivity extends AppCompatActivity {

    public static final String TAG = "ResultActivity";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initComponents();
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

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt_simulate_again:
                    openInputScreen();
                    break;
            }
        }
    };

    private void openInputScreen() {
        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }
}
