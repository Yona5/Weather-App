package com.project.weatherapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.project.weatherapp.model.Weather;

import java.util.ArrayList;

public class DetailsActivity extends Activity {

    private ArrayList<Weather> weathers = new ArrayList<>();
    private ArrayList<String> details = new ArrayList<>();
    private ListView listView;
    private DetailsAdapter detailsAdapter;
    private int position;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.details);

        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            this.weathers = (ArrayList<Weather>) extra.get("details");
            Object obj = extra.get("position");
            this.position = Integer.parseInt(obj.toString());
        }
        populate(this.position);
    }

    public void populate(int position){
        try{
            details.add(weathers.get(position).getPressure());
            details.add(weathers.get(position).getHumidity());
            details.add(weathers.get(position).getUV_risk());
            details.add(weathers.get(position).getVisibility());
            details.add(weathers.get(position).getWind_direction());
            details.add(weathers.get(position).getWind_speed());

            this.listView = findViewById(R.id.details_list);
            this.detailsAdapter = new DetailsAdapter(this, this.details);
            this.listView.setAdapter(detailsAdapter);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
