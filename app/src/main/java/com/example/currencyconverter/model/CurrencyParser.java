package com.example.currencyconverter.model;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class CurrencyParser {

    private ArrayList<Currency> currencys;

    public CurrencyParser(){
        currencys = new ArrayList<>();
    }

    public ArrayList<Currency> getValutes(){
        return currencys;
    }

    public boolean parse(XmlPullParser xpp){

        String textValue = "";

        boolean status = true;
        boolean inEntry = false;

        Currency currentCurrency = null;

        try {

            int eventType = xpp.getEventType();

            while(eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();

                switch (eventType) {

                    case XmlPullParser.START_TAG:

                        if("Valute".equalsIgnoreCase(tagName)){

                            inEntry = true;
                            currentCurrency = new Currency();

                        }
                        break;

                    case XmlPullParser.TEXT:

                        textValue = xpp.getText();

                        break;

                    case XmlPullParser.END_TAG:

                        if(inEntry) {

                            if("Valute".equalsIgnoreCase(tagName)){

                                currencys.add(currentCurrency);
                                inEntry = false;

                            } else if("id".equalsIgnoreCase(tagName)){

                                currentCurrency.setId(textValue);

                            } else if("charCode".equalsIgnoreCase(tagName)){

                                currentCurrency.setCharCode(textValue);

                            } else if("name".equalsIgnoreCase(tagName)){

                                currentCurrency.setName(textValue);

                            } else if("nominal".equalsIgnoreCase(tagName)){

                                currentCurrency.setNominal(textValue);

                            } else if("Value".equalsIgnoreCase(tagName)){

                                currentCurrency.setValue(textValue);

                            }
                        }

                        break;

                    default:
                }

                eventType = xpp.next();

            }

        }

        catch (Exception e){

            status = false;

            e.printStackTrace();

        }

        return  status;

    }

}
