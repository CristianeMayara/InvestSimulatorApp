package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;

import com.cristiane.investsimulatorapp.api.RetrofitInitializer;
import com.cristiane.investsimulatorapp.api.SimulatorService;
import com.cristiane.investsimulatorapp.model.Result;
import com.cristiane.investsimulatorapp.ui.InputActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public MutableLiveData<Result> result = new MutableLiveData<>();

    public void updateInputData(String investedAmount, String index, String rate, boolean isTaxFree, String maturityDate) {
        this.investedAmount = stringToDouble(investedAmount);
        this.index = index;
        this.rate = stringToDouble(rate);
        this.isTaxFree = isTaxFree;
        this.maturityDate = maturityDate;
    }

    private Double stringToDouble(String value) {
        if (value.length() == 0) return 0.0;

        try {
            return Double.parseDouble(value);
        } catch(NumberFormatException e) {
            return 0.0;
        }
    }

    private String setDateFormat(String input) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(input);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean validateDateFormat(String date) {
        Pattern pattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");

        Matcher matcher = pattern.matcher(date);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    private boolean isInvestedAmountValid() {
        return !(investedAmount <= 0.0);
    }

    private boolean isMaturityDateValid() {
        if (maturityDate.length() == 0) return false;
        return validateDateFormat(maturityDate);
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

        setMaturityDate(setDateFormat(getMaturityDate()));

        fetchResult(ctx);
    }

    public void fetchResult(final Context ctx) {
        Call<Result> call = RetrofitInitializer.createService(SimulatorService.class).simulateInvestment(investedAmount, index, rate, isTaxFree, maturityDate);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if (response.isSuccessful()) {
                    result.postValue(response.body());
                    ((InputActivity) ctx).showSimulationSuccessfully();
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
