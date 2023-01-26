package com.example.currencyconverter.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;



public class SaveData {

    private final String STORAGE = "com.example.STORAGE";

    private SharedPreferences preferences;

    public SaveData(Context context) {

        preferences = context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);

    }

    public void setOnClicked(Integer integer) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("OnCLick", integer);
        editor.apply();

    }

    public Integer getOnClicked(){
        return preferences.getInt("OnCLick", 0);
    }

    public void setPositionLeft(int position){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("PositionLeft", position);
        editor.apply();

    }

    public Integer getPositionLeft(){
        return preferences.getInt("PositionLeft", 0);
    }

    public void setPositionRight(int position){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("PositionRight", position);
        editor.apply();

    }

    public Integer getPositionRight(){
        return preferences.getInt("PositionRight", 0);
    }

    public void setData(ArrayList<Currency> currencies) {

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(currencies);
        editor.putString("Valute", json);
        editor.apply();

    }

    public ArrayList<Currency> getData(){

        Gson gson = new Gson();
        String json = preferences.getString("Valute", null);
        Type type = new TypeToken<ArrayList<Currency>>() {
        }.getType();
        return gson.fromJson(json, type);

    }

}
