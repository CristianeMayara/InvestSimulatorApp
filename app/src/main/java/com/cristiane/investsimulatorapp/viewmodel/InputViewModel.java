package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.cristiane.investsimulatorapp.api.RetrofitInitializer;
import com.cristiane.investsimulatorapp.api.SimulatorService;
import com.cristiane.investsimulatorapp.model.Result;
import com.cristiane.investsimulatorapp.ui.InputActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cristiane on 05/07/2018.
 */

public class InputViewModel extends ViewModel {
    private double investedAmount;
    private String index;
    private double rate;
    private boolean isTaxFree;
    private String maturityDate;

    private MutableLiveData<Result> result = new MutableLiveData<>();

    private void updateInputData(String investedAmount, String index, String rate, boolean isTaxFree, String maturityDate) {
        this.investedAmount = stringToDouble(investedAmount);
        this.index = index;
        this.rate = stringToDouble(rate);
        this.isTaxFree = isTaxFree;
        this.maturityDate = maturityDate;
    }

    private Double stringToDouble(String value) {
        if (TextUtils.isEmpty(value)) return 0.0;

        try {
            return Double.parseDouble(value);
        } catch(NumberFormatException e) {
            return 0.0;
        }
    }

    private boolean isInvestedAmountValid() {
        return !(investedAmount <= 0.0);
    }

    private boolean isMaturityDateValid() {
        return !TextUtils.isEmpty(maturityDate);
    }

    private boolean isRateValid() {
        return !(rate <= 0.0);
    }

    // getters and setters

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean isTaxFree() {
        return isTaxFree;
    }

    public void setTaxFree(boolean taxFree) {
        isTaxFree = taxFree;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LiveData<Result> getResult() {
        return result;
    }

    public void loadResult(Context ctx, String investedAmount, String index, String rate, boolean isTaxFree, String maturityDate) {
        boolean hasError = false;

        updateInputData(investedAmount, index, rate, isTaxFree, maturityDate);

        if (!isInvestedAmountValid()){
            ((InputActivity) ctx).showValueInputError();
            hasError = true;
        }

        if (!isMaturityDateValid()){
            ((InputActivity) ctx).showDateError();
            hasError = true;
        }

        if (!isRateValid()){
            ((InputActivity) ctx).showCdiPercentageInputError();
            hasError = true;
        }

        if (hasError) return;

        fetchResult(ctx);
    }

    private void fetchResult(final Context ctx) {
        Call<Result> call = RetrofitInitializer.createService(SimulatorService.class).simulateInvestment(investedAmount, index, rate, isTaxFree, maturityDate);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if (response.isSuccessful()) {
                    result.postValue(response.body());
                } else {
                    result.postValue(null);
                    ((InputActivity) ctx).showSimulationFailed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, Throwable t) {
                result.postValue(null);
            }
        });
    }
}
