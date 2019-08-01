package com.project.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.project.weatherapp.model.Weather;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private ArrayList<Weather> weathers = new ArrayList<>();
    private ListView listView;
    private DetailsAdapter detailsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details);
        Bundle extra = getIntent().getExtras();
        if(extra != null) this.weathers = (ArrayList<Weather>) extra.get("details");
        populate();
    }

    public void populate(){
        try{
            this.listView = findViewById(R.id.details_list);
            System.out.println( weathers.get(0).getWeather());
            this.detailsAdapter = new DetailsAdapter(this, this.weathers);
            this.listView.isOpaque();
            this.listView.setAdapter(detailsAdapter);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
