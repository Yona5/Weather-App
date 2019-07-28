package com.project.weatherapp.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.project.weatherapp.model.Weather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

public class XMLParser extends AsyncTask<Void, Void, Weather> {

    public interface asyncResponse{
        void finalResponse(Weather weather);
    }

    public XMLParser.asyncResponse delegate;
    public XMLParser(XMLParser.asyncResponse delegate){
        this.delegate = delegate;
    }

    AssetManager assetManager;
    public XMLParser(Context context) {
        assetManager = context.getAssets();
    }

    public void parse() throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
        InputStream inputStream = assetManager.open("glasgow.xml");
        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xmlPullParser.setInput(inputStream, null);

        String tag, text = "no data";
        int event = xmlPullParser.getEventType();

        while (event != XmlPullParser.END_DOCUMENT) {
            tag = xmlPullParser.getName();
            switch (event) {
                case XmlPullParser.START_TAG:
                    if (tag.equals("title")) {

                    }
                    break;
                case XmlPullParser.TEXT:
                    text = xmlPullParser.getText();
//                    System.out.println("============");
//                    System.out.println(text);
                    break;
                case XmlPullParser.END_TAG:
                    switch (tag){
                        case "title":
                            System.out.println("============");
                            System.out.println(text);
                            break;
                    }
            }
            event = xmlPullParser.next();
        }
    }

    @Override
    protected Weather doInBackground(Void... voids) {
        return null;
    }
    @Override
    protected void onPostExecute(Weather weather){
        this.delegate.finalResponse(weather);
    }
}


