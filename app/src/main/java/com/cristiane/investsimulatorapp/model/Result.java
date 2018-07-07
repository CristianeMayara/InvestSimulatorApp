package com.cristiane.investsimulatorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cristiane on 07/07/2018.
 */

public class Result implements Parcelable {
    private double grossAmount;
    private double taxesAmount;
    private double netAmount;
    private double grossAmountProfit;
    private double netAmountProfit;
    private double annualGrossRateProfit;
    private double monthlyGrossRateProfit;
    private double dailyGrossRateProfit;
    private double taxesRate;
    private double rateProfit;
    private double annualNetRateProfit;

    public Result() {
    }

    public Result(double grossAmount, double taxesAmount, double netAmount, double grossAmountProfit, double netAmountProfit, double annualGrossRateProfit, double monthlyGrossRateProfit, double dailyGrossRateProfit, double taxesRate, double rateProfit, double annualNetRateProfit) {
        this.grossAmount = grossAmount;
        this.taxesAmount = taxesAmount;
        this.netAmount = netAmount;
        this.grossAmountProfit = grossAmountProfit;
        this.netAmountProfit = netAmountProfit;
        this.annualGrossRateProfit = annualGrossRateProfit;
        this.monthlyGrossRateProfit = monthlyGrossRateProfit;
        this.dailyGrossRateProfit = dailyGrossRateProfit;
        this.taxesRate = taxesRate;
        this.rateProfit = rateProfit;
        this.annualNetRateProfit = annualNetRateProfit;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getTaxesAmount() {
        return taxesAmount;
    }

    public void setTaxesAmount(double taxesAmount) {
        this.taxesAmount = taxesAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getGrossAmountProfit() {
        return grossAmountProfit;
    }

    public void setGrossAmountProfit(double grossAmountProfit) {
        this.grossAmountProfit = grossAmountProfit;
    }

    public double getNetAmountProfit() {
        return netAmountProfit;
    }

    public void setNetAmountProfit(double netAmountProfit) {
        this.netAmountProfit = netAmountProfit;
    }

    public double getAnnualGrossRateProfit() {
        return annualGrossRateProfit;
    }

    public void setAnnualGrossRateProfit(double annualGrossRateProfit) {
        this.annualGrossRateProfit = annualGrossRateProfit;
    }

    public double getMonthlyGrossRateProfit() {
        return monthlyGrossRateProfit;
    }

    public void setMonthlyGrossRateProfit(double monthlyGrossRateProfit) {
        this.monthlyGrossRateProfit = monthlyGrossRateProfit;
    }

    public double getDailyGrossRateProfit() {
        return dailyGrossRateProfit;
    }

    public void setDailyGrossRateProfit(double dailyGrossRateProfit) {
        this.dailyGrossRateProfit = dailyGrossRateProfit;
    }

    public double getTaxesRate() {
        return taxesRate;
    }

    public void setTaxesRate(double taxesRate) {
        this.taxesRate = taxesRate;
    }

    public double getRateProfit() {
        return rateProfit;
    }

    public void setRateProfit(double rateProfit) {
        this.rateProfit = rateProfit;
    }

    public double getAnnualNetRateProfit() {
        return annualNetRateProfit;
    }

    public void setAnnualNetRateProfit(double annualNetRateProfit) {
        this.annualNetRateProfit = annualNetRateProfit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.grossAmount);
        parcel.writeDouble(this.taxesAmount);
        parcel.writeDouble(this.netAmount);
        parcel.writeDouble(this.grossAmountProfit);
        parcel.writeDouble(this.netAmountProfit);
        parcel.writeDouble(this.annualGrossRateProfit);
        parcel.writeDouble(this.monthlyGrossRateProfit);
        parcel.writeDouble(this.dailyGrossRateProfit);
        parcel.writeDouble(this.taxesRate);
        parcel.writeDouble(this.rateProfit);
        parcel.writeDouble(this.annualNetRateProfit);
    }

    protected Result(Parcel in) {
        this.grossAmount = in.readDouble();
        this.taxesAmount = in.readDouble();
        this.netAmount = in.readDouble();
        this.grossAmountProfit = in.readDouble();
        this.netAmountProfit = in.readDouble();
        this.annualGrossRateProfit = in.readDouble();
        this.monthlyGrossRateProfit = in.readDouble();
        this.dailyGrossRateProfit = in.readDouble();
        this.taxesRate = in.readDouble();
        this.rateProfit = in.readDouble();
        this.annualNetRateProfit = in.readDouble();
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
