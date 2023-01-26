package com.example.currencyconverter.model;

import android.content.Context;

import java.util.ArrayList;


public class CalculateExchangeRate {

    private double rate;

    private ArrayList<Currency> data;

    private SaveData saveData;

    public CalculateExchangeRate(Context context){

        saveData = new SaveData(context);
        data = saveData.getData();

    }
    private void calculate(){

        double dValue  = data.get(saveData.getPositionLeft()).getValue();
        rate = data.get(saveData.getPositionRight()).getValue();

        double nominalLValue = Double.parseDouble((data.get(saveData.getPositionLeft()).getNominal()));
        double nominalRValue = Double.parseDouble((data.get(saveData.getPositionRight()).getNominal()));

        rate = rate / nominalRValue; //get one unit for right value

        dValue = dValue / nominalLValue; //get one unit for left value

        if(rate != 0)
            rate = dValue / rate;


    }

    public double getRate() {

        calculate();

        return rate;

    }


}
