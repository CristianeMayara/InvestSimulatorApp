package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.ViewModel;

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
}
