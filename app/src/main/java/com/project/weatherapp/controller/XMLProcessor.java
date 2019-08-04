package com.project.weatherapp.controller;

import com.project.weatherapp.model.Weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//Yonas Tilahun Temesgen
//ID: S1719046

public class XMLProcessor {
    XmlPullParser xmlPullParser;

    public XMLProcessor(XmlPullParser xmlPullParser) {
        this.xmlPullParser = xmlPullParser;
    }

    public ArrayList<Weather> process() throws XmlPullParserException, IOException {
        ArrayList<HashMap<String, String>> weatherList = new ArrayList<>();
        HashMap<String, String> weatherHash = new HashMap<>();
        String tag, text = "no data", location = "", avatar = "";
        int event = xmlPullParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {
            tag = xmlPullParser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    if (tag.equalsIgnoreCase("item")) {
                        weatherHash = new HashMap<>();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = xmlPullParser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    switch (tag) {
                        case "title":
                            weatherHash.put("title", text);
                            if (location.isEmpty()) {
                                location = text;
                            }
                            break;
                        case "url" : avatar = text;
                            break;
                        case "description": weatherHash.put("description", text);
                            break;
                        case "dc:date": weatherHash.put("date", text);
                            break;
                        case "item":
                            if (weatherHash != null) {
                                weatherHash.put("avatar",avatar);
                                weatherHash.put("location", location);
                                weatherList.add(weatherHash);
                            }
                    }
            }
            event = xmlPullParser.next();
        }
        return new ModelMaker(weatherList).mold();
    }
}
