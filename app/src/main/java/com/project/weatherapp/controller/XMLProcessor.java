package com.project.weatherapp.controller;

import com.project.weatherapp.model.Weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class XMLProcessor {
    XmlPullParser xmlPullParser;

    public XMLProcessor(XmlPullParser xmlPullParser) {
        this.xmlPullParser = xmlPullParser;
    }

    public Weather process() throws XmlPullParserException, IOException {
        ArrayList<HashMap<String, String>> weatherList;
        HashMap<String, String> weatherHash;
        String tag, text = "no data";
        int event = xmlPullParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {
            tag = xmlPullParser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    if (tag.equalsIgnoreCase("channel")) {
                        weatherHash = new HashMap<>();
                    }
                    break;
                case XmlPullParser.TEXT:
                    text = xmlPullParser.getText();
                    break;
                case XmlPullParser.END_TAG:
                    switch (tag){
                        case "channel":
                            case "title":
                                System.out.println("============");
                                System.out.println(text);
//                        break;
                        return null;
                    }
            }
            event = xmlPullParser.next();
        }
        ModelMaker modelMaker = new ModelMaker();
        return  null;
    }
}
