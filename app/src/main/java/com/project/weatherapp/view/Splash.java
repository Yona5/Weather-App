package com.project.weatherapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.project.weatherapp.R;

//Yonas Tilahun Temesgen
//ID: S1719046

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {
            this.getSupportActionBar().hide();
        }catch (NullPointerException ex){

        }
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                Splash.this.startActivity(intent);
                Splash.this.finish();
                ProgressBar splashProgress = findViewById(R.id.splashProgress);
                splashProgress.setVisibility(View.GONE);
            }
        }, 500);

    }
}
