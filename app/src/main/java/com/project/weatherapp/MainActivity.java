package com.project.weatherapp;

import android.app.TimePickerDialog;
import android.content.Intent;

import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.project.weatherapp.controller.XMLParser;
import com.project.weatherapp.model.Weather;
import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements XMLParser.AsyncResponse, View.OnClickListener{

    private ListView listView;
    private WeatherAdapter weatherAdapter;
    private ArrayList<Weather> weatherArrayList1 = new ArrayList<>();
    private ArrayList<Weather> weatherArrayList2 = new ArrayList<>();
    private ArrayList<Weather> weatherArrayList3 = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private Bundle bundle;
    private BottomNavigationView navigationView;
    private TimePickerDialog timePickerDialog;

    private String urlStrg = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    String [] paths = {"2648579", "2643743", "5128581", "287286", "934154", "1185241", "344979"};
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(counter == 0){
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
        }

        this.bundle = savedInstanceState;

        if(counter == 0){
            XMLParser xmlParser = new XMLParser(this, urlStrg+paths[0]);
            xmlParser.execute();
        }else{
            for(int i = 1; i < paths.length; i++){
                XMLParser xmlParser = new XMLParser(this, urlStrg+paths[i]);
                xmlParser.execute();
            }
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
        if(counter == 1) {
            createListView(weatherArrayList1);
            nextNavView();
            onCreate(bundle);
        }else if(counter == paths.length){
            createListView(weatherArrayList1);
            nextNavView();
        }
    }

    public void createListView(final ArrayList<Weather> weathers){
        try{
            this.listView = findViewById(R.id.weather_list);
            this.weatherAdapter = new WeatherAdapter(this, weathers);
            this.listView.setAdapter(weatherAdapter);
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    goToDetailsView(position, weathers);
                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void goToDetailsView(int position, ArrayList<Weather> weathers){
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("details", weathers);
        intent.putExtra("position", position);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    public void nextNavView(){
        navigationView = findViewById(R.id.bottomNavigationView);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.today).setTitle(weatherArrayList1.get(0).getDay());
        menu.findItem(R.id.tomorrow).setTitle(weatherArrayList2.get(0).getDay());
        menu.findItem(R.id.the_day_after).setTitle(weatherArrayList3.get(0).getDay());
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.today:
                        createListView(weatherArrayList1);
                        break;
                    case R.id.tomorrow:
                        createListView(weatherArrayList2);
                        break;
                    case R.id.the_day_after:
                        createListView(weatherArrayList3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.menu){

            drawerLayout = findViewById(R.id.activity_main);
            drawerLayout.openDrawer(Gravity.START);
            navView = findViewById(R.id.nv);

            navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    final Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);

                    int id = menuItem.getItemId();
                    switch(id)
                    {
                        case R.id.interval:
                            timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                }
                            }, hour, minute, true);
                            timePickerDialog.show();
                            break;
                        default:
                            return true;
                    }
                    return true;
                }
            });
        }
    }
}
