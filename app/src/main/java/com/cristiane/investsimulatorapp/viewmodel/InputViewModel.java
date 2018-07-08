package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

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
    private double investedAmount = 32323.0;
    private String index = "CDI";
    private double rate = 123;
    private boolean isTaxFree = false;
    private String maturityDate = "2023-03-03";

    private MutableLiveData<Result> result = new MutableLiveData<>();

    public void updateInputData(double investedAmount, String index, double rate, boolean isTaxFree, String maturityDate) {
        this.investedAmount = investedAmount;
        this.index = index;
        this.rate = rate;
        this.isTaxFree = isTaxFree;
        this.maturityDate = maturityDate;
    }

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

    public void loadResult(Double investedAmount, String index, Double rate, boolean isTaxFree, String maturityDate) {
        //Investment i = new Investment(32323.0,9.5512, 1981, 1409,  "2023-03-03", 120, false);
        //setResult(new Result(i, 60528.20, 4230.78, 56297.42, 28205.20, 23974.42, 87.26, 0.76, 0.01, 15.0, 9.5512, 74.17));

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
