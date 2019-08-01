package com.project.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.weatherapp.controller.XMLParser;
import com.project.weatherapp.model.Weather;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements XMLParser.AsyncResponse{

    private ListView listView;
    private WeatherAdapter weatherAdapter;
    private ArrayList<Weather> weatherArrayList1 = new ArrayList<>();
    private ArrayList<Weather> weatherArrayList2 = new ArrayList<>();
    private ArrayList<Weather> weatherArrayList3 = new ArrayList<>();
    private String urlStrg = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    String [] paths = {"2648579", "2643743", "5128581", "287286", "934154", "1185241", "344979"};
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }

        for(int i = 0; i < paths.length; i++){
            XMLParser xmlParser = new XMLParser(this, urlStrg+paths[i]);
            xmlParser.execute();
        }
    }

    @Override
    public void finalResponse(ArrayList<Weather> weatherList) {
        try{
            for(int i = 0; i < weatherList.size(); i++){
                if(i==0) weatherArrayList1.add(weatherList.get(i));
                if(i==1) weatherArrayList2.add(weatherList.get(i));
                if(i==2) weatherArrayList3.add(weatherList.get(i));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        counter++;
        if(counter == paths.length) createView(weatherArrayList1);
    }

    public void createView(ArrayList<Weather> weathers){
        try{
            this.listView = findViewById(R.id.weather_list);
            this.weatherAdapter = new WeatherAdapter(this, weathers);
            this.listView.setAdapter(weatherAdapter);
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
