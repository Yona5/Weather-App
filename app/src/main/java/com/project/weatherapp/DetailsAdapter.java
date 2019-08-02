package com.project.weatherapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> weatherList;

    public DetailsAdapter(@NonNull Context context, ArrayList<String> weatherList){
        super(context, 0, weatherList);
        this.context = context;
        this.weatherList = weatherList;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @Nullable ViewGroup parent){
        View listItem = view;
        try{
            if (listItem == null){
                listItem = LayoutInflater.from(this.context).inflate(R.layout.details_layout, parent, false);
            }

            String current_weather = weatherList.get(position);
            String [] keyList = {"Pressure", "Humidity", "UV Risk", "Visibility", "Wind Direction", "Wind Speed"};

            TextView key = listItem.findViewById(R.id.key);
            TextView value = listItem.findViewById(R.id.value);
            ImageView imageView = listItem.findViewById(R.id.detail_icon);


            if(position <= keyList.length){
                key.setText(keyList[position]);
                switch (keyList[position]){
                    case "Pressure": imageView.setImageResource(R.drawable.pressure);
                        break;
                    case "Humidity": imageView.setImageResource(R.drawable.wet);
                        break;
                    case "UV Risk": imageView.setImageResource(R.drawable.restrict);
                        break;
                    case "Visibility": imageView.setImageResource(R.drawable.eye);
                        break;
                    case "Wind Direction": imageView.setImageResource(R.drawable.wind_gauge);
                        break;
                    case "Wind Speed": imageView.setImageResource(R.drawable.wind_speed);
                        break;
                }
            }
            value.setText(current_weather);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listItem;
    }
}
