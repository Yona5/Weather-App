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


public class WeatherAdapter extends ArrayAdapter<Weather> {
    private Context context;
    private List<Weather> weatherList;

    public WeatherAdapter(@NonNull Context context, ArrayList<Weather> weatherList){
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
                listItem = LayoutInflater.from(this.context).inflate(R.layout.layout, parent, false);
            }
            Weather current_weather = weatherList.get(position);
            TextView weather_pre = listItem.findViewById(R.id.weather);
            TextView location = listItem.findViewById(R.id.locationName);
            TextView temperature = listItem.findViewById(R.id.temperature);
            TextView max_temp = listItem.findViewById(R.id.max_temp);
            TextView min_temp = listItem.findViewById(R.id.min_temp);

            weather_pre.setText(current_weather.getWeather());
            location.setText(current_weather.getLocation());
            max_temp.setText(current_weather.getMax_temp() + "°" + " C");
            min_temp.setText(current_weather.getMin_temp() + "°" + " C");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listItem;
    }
}
