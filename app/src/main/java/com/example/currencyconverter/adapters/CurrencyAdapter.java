package com.example.currencyconverter.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.currencyconverter.R;
import com.example.currencyconverter.model.SaveData;
import com.example.currencyconverter.model.Currency;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder>{

    private static final int LEFT = 0;

    private static final int RIGHT = 1;

    private final ArrayList<Currency> data;

    private final Activity context;

    private SaveData saveData;

    public CurrencyAdapter(Activity c){

        context = c;

        saveData = new SaveData(c.getApplicationContext());

        data = openSavedData();

    }

    private ArrayList<Currency> openSavedData() {

        return saveData.getData();

    }

    @Override
    public int getItemCount() {

        return data.size();

    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.valute_card, parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.valute.setText(data.get(holder.getAdapterPosition()).getName());
        holder.valuteChar.setText(data.get(holder.getAdapterPosition()).getCharCode());

        if(saveData.getOnClicked() == LEFT){


            if(saveData.getPositionLeft() == position){

                holder.checkView.setVisibility(View.VISIBLE);

            } else {

                holder.checkView.setVisibility(View.INVISIBLE);

            }

        }
        if (saveData.getOnClicked() == RIGHT){

            if(saveData.getPositionRight() == position){

                holder.checkView.setVisibility(View.VISIBLE);

            } else {

                holder.checkView.setVisibility(View.INVISIBLE);

            }
        }

        holder.view.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                saveData = new SaveData(context);

                if(saveData.getOnClicked() == LEFT){

                    saveData.setPositionLeft(holder.getAdapterPosition());

                }

                if(saveData.getOnClicked() == RIGHT){

                    saveData.setPositionRight(holder.getAdapterPosition());

                }

                context.onBackPressed();

            }

        });

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView valute ;
        TextView valuteChar;
        LinearLayout view;
        ImageView checkView;

        public ViewHolder(View itemView) {

            super(itemView);

            valute = (TextView)itemView.findViewById(R.id.valute);
            valuteChar = (TextView)itemView.findViewById(R.id.valute_char);
            view = (LinearLayout)itemView.findViewById(R.id.view);
            checkView = (ImageView) itemView.findViewById(R.id.check_view);

        }
    }
}

