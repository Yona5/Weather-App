package com.project.weatherapp.controller;

import android.util.Log;

import com.project.weatherapp.model.Weather;

import java.util.ArrayList;
import java.util.HashMap;

public class ModelMaker {
    ArrayList<HashMap<String, String>> weatherList;
    ArrayList<Weather> modelList = new ArrayList<>();

    public  ModelMaker(ArrayList<HashMap<String, String>> weatherList){
        this.weatherList = weatherList;
    }

    public ArrayList<Weather> mold(){
        Weather weather;
        for(int i = 0; i < weatherList.size(); i++){
            try{
                weather = new Weather();
                HashMap<String, String> weatherHash = weatherList.get(i);

                String[] description  = weatherHash.get("description").split(",");
                String degree = ""+(char)176;
                String max_temp = description[0].split(":")[1].split(degree)[0];
                String min_temp = description[1].split(":")[1].split(degree)[0];
                String wind_direction = description[2].split(":")[1];
                String wind_speed = description[3].split(":")[1].split("mph")[0];
                String visibility = description[4].split(":")[1];
                String pressure = description[5].split(":")[1].split("mb")[0];
                String humidity = description[6].split(":")[1].split("%")[0];
                String uv_risk = description[7].split(":")[1];

                String location = weatherHash.get("location").split("for")[1];
                String avatar = weatherHash.get("avatar");
                String day = weatherHash.get("title").split(",")[0].split(":")[0];
                String weather_pre = weatherHash.get("title").split(",")[0].split(":")[1];
                String date =  weatherHash.get("date").split("T")[0];

                weather.setLocation(location.trim());
                weather.setAvatar(avatar.trim());
                weather.setMax_temp(max_temp.trim());
                weather.setMin_temp(min_temp.trim());
                weather.setWind_direction(wind_direction.trim());
                weather.setWind_speed(wind_speed.trim());
                weather.setVisibility(visibility.trim());
                weather.setPressure(pressure.trim());
                weather.setHumidity(humidity.trim());
                weather.setUV_risk(uv_risk.trim());
                weather.setDay(day);
                weather.setWeather(weather_pre);
                weather.setDate(date);

                this.modelList.add(weather);

            }catch (Exception ex){
                Log.e("app", "exception", ex);
            }
        }
        return this.modelList;
    }
}
