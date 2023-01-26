package com.example.currencyconverter.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Currency {

    private String id;
    private String charCode;
    private String name;
    private String nominal;
    private double value;

    public String getId() { return id; }
    public String getCharCode() { return charCode; }
    public String getNominal() { return nominal; }
    public String getName(){
        return name;
    }
    public double getValue(){ return value; }

    public void setId(String id) { this.id = id; }
    public void setCharCode(String charCode) { this.charCode = charCode; }
    public void setNominal(String nominal) { this.nominal = nominal; }
    public void setName(String name){
        this.name = name;
    }
    public void setValue(String value){
        NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
        double dValue = 0;
        try {
            dValue = (double) nf.parse(value);//because there is a comma in string and the compiler gives an error
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.value = dValue;
    }


}
