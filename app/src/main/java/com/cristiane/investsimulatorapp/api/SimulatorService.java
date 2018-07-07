package com.cristiane.investsimulatorapp.api;

import com.cristiane.investsimulatorapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.cristiane.investsimulatorapp.util.UrlConstants.URL_SIMULATE;

/**
 * Created by cristiane on 07/07/2018.
 */

public interface SimulatorService {

    @GET(URL_SIMULATE)
    Call<Result> simulateInvestment(@Query("investedAmount") Double investedAmount, @Query("index") String index, @Query("rate") Double rate, @Query("isTaxFree") boolean isTaxFree, @Query("maturityDate") String maturityDate);

}
