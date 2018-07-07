package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.cristiane.investsimulatorapp.model.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by cristiane on 07/07/2018.
 */

public class ResultViewModel extends ViewModel {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void loadResult() {
        String jsonInString = "{"+
                    "investedAmount= \"32323.0\"," +
                    "yearlyInterestRate= 9.5512," +
                    "maturityTotalDays= 1981," +
                    "maturityBusinessDays= 1409," +
                    "maturityDate= \"2023-03-03T00:00:00\"," +
                    "rate= 123.0," +
                    "isTaxFree= false }," +
                "grossAmount= 60528.20," +
                "taxesAmount= 4230.78," +
                "netAmount= 56297.42," +
                "grossAmountProfit= 28205.20," +
                "netAmountProfit= 23974.42," +
                "annualGrossRateProfit= 87.26," +
                "monthlyGrossRateProfit= 0.76," +
                "dailyGrossRateProfit= 0.000445330025305748," +
                "taxesRate= 15.0," +
                "rateProfit= 9.5512," +
                "annualNetRateProfit= 74.17" +
        "}";

        Gson gson;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        result = gson.fromJson(jsonInString, Result.class);

        //JSONObject jsonObject = null;
    }
}
