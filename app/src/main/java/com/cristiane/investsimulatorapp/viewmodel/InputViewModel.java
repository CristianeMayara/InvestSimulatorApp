package com.cristiane.investsimulatorapp.viewmodel;

import android.arch.lifecycle.ViewModel;

/**
 * Created by cristiane on 05/07/2018.
 */

public class InputViewModel extends ViewModel {
    private double investedAmount;
    private String index;
    private double rate;
    private boolean isTaxFree;
    private String maturityDate;

    public void updateData(double investedAmount, String index, double rate, boolean isTaxFree, String maturityDate) {
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
}
