package com.project.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.weatherapp.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter extends ArrayAdapter<Weather> {

    private Context context;
    private List<Weather> weatherList;

    public DetailsAdapter(@NonNull Context context, ArrayList<Weather> weatherList){
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

            Weather current_weather = weatherList.get(position);

            TextView key = listItem.findViewById(R.id.key);

            key.setText(current_weather.getHumidity());

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listItem;
    }
}
