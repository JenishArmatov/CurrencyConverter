package com.example.currencyconverter.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.currencyconverter.R;
import com.example.currencyconverter.loader.Downloader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "Main Activity";

    private Unbinder unbinder;
    @BindView(R.id.progressInd)
    public ProgressBar progress;

    @BindView(R.id.refresh_button)
    public ImageButton refreshButton;

    @BindView(R.id.check_connection_TV)
    public TextView checkConnect_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this, this);

        new Downloader(this);

    }




}