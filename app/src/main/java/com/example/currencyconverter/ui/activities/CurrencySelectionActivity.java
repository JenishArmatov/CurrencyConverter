package com.example.currencyconverter.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.currencyconverter.R;
import com.example.currencyconverter.adapters.CurrencyAdapter;
import com.example.currencyconverter.model.SaveData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CurrencySelectionActivity extends AppCompatActivity {

    private static int LEFT = 0;

    private static int RIGHT = 1;

    private Unbinder unbinder;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private SaveData saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_currency_selection);

        unbinder = ButterKnife.bind(this, this);

        saveData = new SaveData(getApplicationContext());

        init();


    }
    private void init(){

        CurrencyAdapter valuteAdapter = new CurrencyAdapter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(llm);

        if(saveData.getOnClicked() == LEFT){

            recyclerView.scrollToPosition(saveData.getPositionLeft() - 1);

        }
        if(saveData.getOnClicked() == RIGHT){

            recyclerView.scrollToPosition(saveData.getPositionRight() - 1);

        }
        recyclerView.setAdapter(valuteAdapter);
    }

}