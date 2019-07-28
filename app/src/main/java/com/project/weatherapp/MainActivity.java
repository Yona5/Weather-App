package com.project.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.project.weatherapp.controller.XMLParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        XMLParser xmlParser = new XMLParser(this);
        try {
            xmlParser.parse();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
