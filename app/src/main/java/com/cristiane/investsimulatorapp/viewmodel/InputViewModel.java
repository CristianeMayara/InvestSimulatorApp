package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.cristiane.investsimulatorapp.api.RetrofitInitializer;
import com.cristiane.investsimulatorapp.api.SimulatorService;
import com.cristiane.investsimulatorapp.model.Result;

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

    public void updateInputData(double investedAmount, String index, double rate, boolean isTaxFree, String maturityDate) {
        this.investedAmount = investedAmount;
        this.index = index;
        this.rate = rate;
        this.isTaxFree = isTaxFree;
        this.maturityDate = maturityDate;
    }

    private boolean isInvestedAmountValid(){
        return !(investedAmount <= 0.0);
    }

    private boolean isMaturityDateValid(){
        return !TextUtils.isEmpty(maturityDate);
    }

    private boolean isRateValid(){
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

    public boolean loadResult(Double investedAmount, String index, Double rate, boolean isTaxFree, String maturityDate) {
        boolean hasError = false;

        updateInputData(investedAmount, index, rate, isTaxFree, maturityDate);

        if (!isInvestedAmountValid()){
//            registerScreen.showNameError();
            hasError = true;
        }

        if (!isMaturityDateValid()){
//            registerScreen.showEmailError();
            hasError = true;
        }

        if (!isRateValid()){
//            registerScreen.showPhoneError();
            hasError = true;
        }

        if (hasError){
            return false;
        }

        fetchResult(investedAmount, index, rate, isTaxFree, maturityDate);
        return true;
    }

    private void fetchResult(Double investedAmount, String index, Double rate, boolean isTaxFree, String maturityDate) {
        Call<Result> call = RetrofitInitializer.createService(SimulatorService.class).simulateInvestment(investedAmount, index, rate, isTaxFree, maturityDate);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    result.postValue(response.body());
                } else {
                    result.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                result.postValue(null);
            }
        });
    }
}
