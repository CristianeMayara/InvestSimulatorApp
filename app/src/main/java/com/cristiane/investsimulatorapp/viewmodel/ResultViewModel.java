package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.cristiane.investsimulatorapp.model.Investment;
import com.cristiane.investsimulatorapp.model.Result;

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
        Investment i = new Investment(32323.0,9.5512, 1981, 1409,  "2023-03-03", 123, false);
        result = new Result(i, 60528.20, 4230.78, 56297.42, 28205.20, 23974.42, 87.26, 0.76, 0.01, 15.0, 9.5512, 74.17);
    }
}
