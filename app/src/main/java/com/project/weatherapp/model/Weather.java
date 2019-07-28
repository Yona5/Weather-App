package com.project.weatherapp.model;

import java.util.Date;

public class Weather {
    //location (country)
    String location;
    // day of the week and date, temperature, wind speed, likelihood of rain
    double max_temp;
    double min_temp;
    double wind_speed;
    String weather;
    Date date;
    // wind direction, visibility, pressure, humidity, UV risk, pollution
    String wind_direction;
    String visibility;
    double pressure;
    double UV_risk;
    String pollution;

    public Weather() {
    }

    public Weather(String location, double max_temp, double min_temp, double wind_speed,
                   String weather, Date date, String wind_direction, String visibility,
                   double pressure, double UV_risk, String pollution) {
        this.location = location;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.wind_speed = wind_speed;
        this.weather = weather;
        this.date = date;
        this.wind_direction = wind_direction;
        this.visibility = visibility;
        this.pressure = pressure;
        this.UV_risk = UV_risk;
        this.pollution = pollution;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(double max_temp) {
        this.max_temp = max_temp;
    }

    public double getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(double min_temp) {
        this.min_temp = min_temp;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getUV_risk() {
        return UV_risk;
    }

    public void setUV_risk(double UV_risk) {
        this.UV_risk = UV_risk;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }
}
