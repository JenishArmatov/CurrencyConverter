package com.example.currencyconverter.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.currencyconverter.R;
import com.example.currencyconverter.model.CalculateExchangeRate;
import com.example.currencyconverter.model.SaveData;
import com.example.currencyconverter.model.Currency;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CalculateActivity extends AppCompatActivity{

    public static String TAG = "CalculateActivity";
    private static int LEFT = 0;
    private static int RIGHT = 1;

    private Unbinder unbinder;

    @BindView(R.id.value_left_TV)
    EditText valueLeftTV;

    @BindView(R.id.value_TV)
    TextView valueRightTV;

    @BindView(R.id.valute_left_TV)
    TextView valuteLeftTV;

    @BindView(R.id.valute_right_TV)
    TextView valuteRightTV;

    @BindView(R.id.change_valute_left_TV)
    TextView changeValuteLeftTV;

    @BindView(R.id.change_valute_right_TV)
    TextView changeValuteRightTV;

    private ArrayList<Currency> data;

    private SaveData saveData;

    private CalculateExchangeRate calculateExchangeRate;


    private String leftValue = "1";

    private String rightValue;

    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calculate);

        unbinder = ButterKnife.bind(this, this);

        calculateExchangeRate = new CalculateExchangeRate(this);

        setTextForRightTV("1");
    }

    private void initView() {

        valueLeftTV.setText(leftValue);
        valueRightTV.setText(rightValue);

        valuteLeftTV.setText(saveData.getData().get(saveData.getPositionLeft()).getCharCode());
        valuteRightTV.setText(saveData.getData().get(saveData.getPositionRight()).getCharCode());

        changeValuteLeftTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData.setOnClicked(LEFT);

                startActivity(new Intent(CalculateActivity.this, CurrencySelectionActivity.class));
            }
        });
        changeValuteRightTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData.setOnClicked(RIGHT);
                startActivity(new Intent(CalculateActivity.this, CurrencySelectionActivity.class));
            }
        });
        valueLeftTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                setTextForRightTV(s);

            }
        });

    }
    private void setTextForRightTV(String s){
        double exchangeRate = calculateExchangeRate.getRate();
        if(!s.equalsIgnoreCase("")){
            rightValue = decimalFormat.format(exchangeRate * Double.parseDouble(s));
        }else {
            rightValue = "0";
        }
        valueRightTV.setText(rightValue);
    }

    private void openSavedData() {
        saveData = new SaveData(this);
        data = saveData.getData();
    }
    @Override
    protected void onResume() {
        openSavedData();
        initView();
        super.onResume();
    }




}
