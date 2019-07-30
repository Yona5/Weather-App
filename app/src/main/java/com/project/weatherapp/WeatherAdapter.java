package com.project.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.project.weatherapp.model.Weather;

import java.util.ArrayList;
import java.util.List;


public class WeatherAdapter extends ArrayAdapter<Weather> {
    private Context context;
    private List<Weather> weatherList;

    public WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherList){
        super(context, 0, weatherList);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @Nullable ViewGroup parent){
        View listItem = view;
        if (listItem == null)
            listItem = LayoutInflater.from(this.context).inflate(R.layout.layout, parent, false);





        return listItem;
    }
}
