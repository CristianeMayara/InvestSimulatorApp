package com.cristiane.investsimulatorapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cristiane on 06/07/2018.
 */

public class Investment implements Parcelable {

    private double investedAmount;
    private double yearlyInterestRate;
    private int maturityTotalDays;
    private int maturityBusinessDays;
    private String maturityDate;
    private double rate;
    private boolean isTaxFree;

    public Investment() {
    }

    public Investment(double investedAmount, double yearlyInterestRate, int maturityTotalDays, int maturityBusinessDays, String maturityDate, double rate, boolean isTaxFree) {
        this.investedAmount = investedAmount;
        this.yearlyInterestRate = yearlyInterestRate;
        this.maturityTotalDays = maturityTotalDays;
        this.maturityBusinessDays = maturityBusinessDays;
        this.maturityDate = maturityDate;
        this.rate = rate;
        this.isTaxFree = isTaxFree;
    }

    public double getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(double investedAmount) {
        this.investedAmount = investedAmount;
    }

    public double getYearlyInterestRate() {
        return yearlyInterestRate;
    }

    public void setYearlyInterestRate(double yearlyInterestRate) {
        this.yearlyInterestRate = yearlyInterestRate;
    }

    public int getMaturityTotalDays() {
        return maturityTotalDays;
    }

    public void setMaturityTotalDays(int maturityTotalDays) {
        this.maturityTotalDays = maturityTotalDays;
    }

    public int getMaturityBusinessDays() {
        return maturityBusinessDays;
    }

    public void setMaturityBusinessDays(int maturityBusinessDays) {
        this.maturityBusinessDays = maturityBusinessDays;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.investedAmount);
        parcel.writeDouble(this.yearlyInterestRate);
        parcel.writeInt(this.maturityTotalDays);
        parcel.writeInt(this.maturityBusinessDays);
        parcel.writeString(this.maturityDate);
        parcel.writeDouble(this.rate);
        parcel.writeByte(this.isTaxFree ? (byte) 1 : (byte) 0);
    }

    protected Investment(Parcel in) {
        this.investedAmount = in.readDouble();
        this.yearlyInterestRate = in.readDouble();
        this.maturityTotalDays = in.readInt();
        this.maturityBusinessDays = in.readInt();
        this.maturityDate = in.readString();
        this.rate = in.readDouble();
        this.isTaxFree = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Investment> CREATOR = new Parcelable.Creator<Investment>() {
        @Override
        public Investment createFromParcel(Parcel source) {
            return new Investment(source);
        }

        @Override
        public Investment[] newArray(int size) {
            return new Investment[size];
        }
    };
}
