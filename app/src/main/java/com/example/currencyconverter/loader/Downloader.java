package com.example.currencyconverter.loader;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.example.currencyconverter.dialogs.ErrorInternetDialog;
import com.example.currencyconverter.model.SaveData;
import com.example.currencyconverter.model.CurrencyParser;
import com.example.currencyconverter.ui.activities.CalculateActivity;
import com.example.currencyconverter.ui.activities.MainActivity;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;


public class Downloader {

    public static String TAG = "AsyncDownloader";
    public static String URL = "https://www.cbr.ru/scripts/XML_daily.asp";

    private final MainActivity activity;


    public Downloader(Activity activity){

        this.activity = (MainActivity) activity;
        init();

    }
    private void init(){

        if(isOnline(activity)){

            load();

        }else {

            initCheckInternetConnection();

        }

    }
    private void load(){

        activity.progress.setVisibility(View.VISIBLE);
        activity.refreshButton.setVisibility(View.GONE);
        activity.checkConnect_TV.setVisibility(View.GONE);

        AsyncDownloader asyncDownloader = new AsyncDownloader();
        asyncDownloader.execute(URL);
    }

    private void initCheckInternetConnection() {

        FragmentManager manager = activity.getSupportFragmentManager();

        ErrorInternetDialog dialog = new ErrorInternetDialog();

        dialog.show(manager, "");

        activity.progress.setVisibility(View.GONE);
        activity.refreshButton.setVisibility(View.VISIBLE);
        activity.checkConnect_TV.setVisibility(View.VISIBLE);

        activity.refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                init();

            }
        });
    }

    private boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {

            return true;

        }

        return false;
    }

    private void save(CurrencyParser currencyParser) {

        SaveData saveData = new SaveData(activity);
        saveData.setData(currencyParser.getValutes());

        Intent intent = new Intent(activity, CalculateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        activity.startActivity(intent);
    }


    private class AsyncDownloader extends AsyncTask<String, String, Integer> {

        @Override
        protected Integer doInBackground(String... arg0) {

            XmlPullParser receivedData = tryDownloadingXmlData(arg0[0]);

            CurrencyParser currencyParser = new CurrencyParser();

            if(currencyParser.parse(receivedData)){

                save(currencyParser);

            }

            return 1;
        }

        private XmlPullParser tryDownloadingXmlData(String url) {

            try {

                Log.i(TAG, "Now downloading...");

                URL xmlUrl = new URL(url);

                XmlPullParser receivedData = XmlPullParserFactory.newInstance().newPullParser();

                receivedData.setInput(xmlUrl.openStream(), null);

                return receivedData;

            } catch (XmlPullParserException e) {

                Log.e(TAG, "XmlPullParserExecption", e);

            } catch (IOException e) {

                Log.e(TAG, "XmlPullParserExecption", e);

            }

            return null;
            
        }

    }


}