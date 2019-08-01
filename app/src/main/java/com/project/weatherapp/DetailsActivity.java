package com.project.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.project.weatherapp.model.Weather;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ArrayList<Weather> weathers = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_layout);
        Bundle extra = getIntent().getExtras();
        if(extra != null) weathers = (ArrayList<Weather>) extra.get("details");

    }
}
