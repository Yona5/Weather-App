package com.project.weatherapp.model;

import java.util.Date;

public class Weather {
    //location (country)
    String location;
    // day of the week and date, temperature, wind speed, likelihood of rain
    String max_temp;
    String min_temp;
    String wind_speed;
    String weather;
    String avatar;
    String day;


    String date;
    // wind direction, visibility, pressure, humidity, UV risk, pollution
    String wind_direction;
    String visibility;
    String humidity;
    String pressure;
    String UV_risk;

    public Weather() {
    }

    public Weather(String location, String max_temp, String min_temp, String wind_speed,
                   String weather, String avatar, String date, String wind_direction,
                   String visibility, String humidity, String pressure, String UV_risk, String day){
        this.location = location;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.wind_speed = wind_speed;
        this.weather = weather;
        this.avatar = avatar;
        this.date = date;
        this.wind_direction = wind_direction;
        this.visibility = visibility;
        this.humidity = humidity;
        this.pressure = pressure;
        this.UV_risk = UV_risk;
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getUV_risk() {
        return UV_risk;
    }

    public void setUV_risk(String UV_risk) {
        this.UV_risk = UV_risk;
    }
}
