package com.project.weatherapp.view;

import android.app.TimePickerDialog;
import android.content.Intent;

import android.os.Handler;
import android.support.annotation.NonNull;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;

import com.project.weatherapp.R;
import com.project.weatherapp.controller.WeatherAdapter;
import com.project.weatherapp.controller.XMLParser;
import com.project.weatherapp.model.Weather;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//Yonas Tilahun Temesgen
//ID: S1719046

public class MainActivity extends AppCompatActivity implements XMLParser.AsyncResponse, View.OnClickListener {

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
    String[] paths = {"2648579", "2643743", "5128581", "287286", "934154", "1185241", "344979"};
    private int counter = 0;
    final long[] time = new long[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (counter == 0) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            setContentView(R.layout.activity_main);

            try {
                this.getSupportActionBar().hide();
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }

        this.bundle = savedInstanceState;

        if (counter == 0) {
            XMLParser xmlParser = new XMLParser(this, urlStrg + paths[0]);
            xmlParser.execute();
        } else {
            for (int i = 1; i < paths.length; i++) {
                XMLParser xmlParser = new XMLParser(this, urlStrg + paths[i]);
                xmlParser.execute();
            }
        }
    }


    @Override
    public void finalResponse(ArrayList<Weather> weatherList) {
        try {
            for (int i = 0; i < weatherList.size(); i++) {
                if (i == 0) weatherArrayList1.add(weatherList.get(i));
                if (i == 1) weatherArrayList2.add(weatherList.get(i));
                if (i == 2) weatherArrayList3.add(weatherList.get(i));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        counter++;
        if (counter == 1) {
            Date date = new Date();
            String dateStr = new Date().toString().substring(0, 10);
            createListView(weatherArrayList1, dateStr);
            nextNavView();
            onCreate(bundle);
        } else if (counter == paths.length) {
            Date date = new Date();
            String dateStrg = new Date().toString().substring(0, 10);
            createListView(weatherArrayList1, dateStrg);
            nextNavView();
            counter = 0;
        }
    }

    public void createListView(final ArrayList<Weather> weathers, String date) {
        try {
            this.listView = findViewById(R.id.weather_list);
            this.weatherAdapter = new WeatherAdapter(this, weathers, date);
            this.listView.setAdapter(weatherAdapter);
            this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    goToDetailsView(position, weathers);
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void goToDetailsView(int position, ArrayList<Weather> weathers) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("details", weathers);
        intent.putExtra("position", position);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    public void nextNavView() {

        navigationView = findViewById(R.id.bottomNavigationView);
        final Menu menu = navigationView.getMenu();
        menu.findItem(R.id.today).setTitle("Today");
        menu.findItem(R.id.tomorrow).setTitle("Tomorrow");
        menu.findItem(R.id.the_day_after).setTitle("The day After");
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.today:
                        Date date1 = new Date();
                        String dateStrg1 = new Date().toString().substring(0, 10);
                        createListView(weatherArrayList1, dateStrg1);
                        break;
                    case R.id.tomorrow:
                        Date date2 = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date2);
                        calendar.add(Calendar.DATE, 1);
                        String dateStrg2 = calendar.getTime().toString().substring(0, 10);
                        createListView(weatherArrayList2, dateStrg2);

                        break;
                    case R.id.the_day_after:
                        Date date3 = new Date();
                        Calendar calendar1 = Calendar.getInstance();
                        calendar1.setTime(date3);
                        calendar1.add(Calendar.DATE, 2);
                        String dateStrg3 = calendar1.getTime().toString().substring(0, 10);
                        createListView(weatherArrayList3, dateStrg3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.menu) {

            drawerLayout = findViewById(R.id.activity_main);
            drawerLayout.openDrawer(Gravity.START);
            navView = findViewById(R.id.nv);

            navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    final Calendar calendar = Calendar.getInstance();
                    final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);

                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.interval:
                            timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    double min = (double) minute / 60;
                                    double hour = (double) hourOfDay;
                                    double t = (hour + min) * 3600000;
                                    time[0] = Math.round(t);
                                    refreshApp(time[0]);
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

    public void refreshApp(long time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("we are done ");
                finish();
                startActivity(getIntent());
            }
        }, time);
    }
}
