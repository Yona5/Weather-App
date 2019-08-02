package com.project.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.project.weatherapp.model.Weather;
import com.squareup.picasso.Picasso;

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
            ImageView avatar = listItem.findViewById(R.id.icon);
            ImageView menu = listItem.findViewById(R.id.menu);
            View layout = listItem.findViewById(R.id.constraintLayout);

            weather_pre.setText(current_weather.getWeather());
            location.setText(current_weather.getLocation());
            max_temp.setText(current_weather.getMax_temp() + "°" + " C");
            min_temp.setText(current_weather.getMin_temp() + "°" + " C");
            temperature.setText(current_weather.getMax_temp());

//            Picasso.get().load(current_weather.getAvatar()).into(avatar);

            String weather = current_weather.getWeather().toLowerCase();
            System.out.println(weather);
            if(weather.contains("sunny")){
                avatar.setImageResource(R.drawable.weather_sunny);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_hot));
            }
            if(weather.contains("partly cloud")){
                avatar.setImageResource(R.drawable.weather_partly_cloudy);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_cloudy));
            }
            if(weather.contains("sunny intervals")){
                avatar.setImageResource(R.drawable.weather_partly_cloudy);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_clear));
            }
            if(weather.contains("light rain")){
                avatar.setImageResource(R.drawable.weather_rainy);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_storm));
            }
            if(weather.contains("heavy rain")){
                avatar.setImageResource(R.drawable.weather_pouring);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_storm));
            }
            if(weather.contains("sleet shower")){
                avatar.setImageResource(R.drawable.weather_snowy_rainy);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_sleet));
            }
            if(weather.contains("hail shower")){
                avatar.setImageResource(R.drawable.weather_hail);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_clear_night));
            }
            if(weather.contains("heavy snow")){
                avatar.setImageResource(R.drawable.weather_snowy_rainy);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_sleet));
            }
            if(weather.contains("thunder")){
                avatar.setImageResource(R.drawable.weather_lightning);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_thunder));
            }
            if(weather.contains("clear sky")){
                avatar.setImageResource(R.drawable.weather_sunny);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_clear));
            }
            if(weather.contains("light cloud")){
                avatar.setImageResource(R.drawable.weather_cloudy);
                layout.setBackground(getContext().getDrawable(R.drawable.bg_color_cloudy));
            }

            if(position == 0){
                menu.setVisibility(View.VISIBLE);
            }else{menu.setVisibility(View.GONE);}

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return listItem;
    }
}
