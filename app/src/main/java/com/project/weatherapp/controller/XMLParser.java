package com.project.weatherapp.controller;


import android.os.AsyncTask;
import android.util.Log;
import com.project.weatherapp.model.Weather;
import org.xmlpull.v1.XmlPullParser;

import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

//Yonas Tilahun Temesgen
//ID: S1719046

public class XMLParser extends AsyncTask<Void, Void, XmlPullParser> {

    public interface AsyncResponse{
        void finalResponse(ArrayList<Weather> weatherList);
    }

    public AsyncResponse delegate;
    private String urlStrng;

    public XMLParser(AsyncResponse delegate, String urlStrg){
        this.delegate = delegate;
        this.urlStrng = urlStrg;
    }

    @Override
    protected XmlPullParser doInBackground(Void... voids) {
        String urlStrg = this.urlStrng;
        try{
            URL url = new URL(urlStrg);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            httpURLConnection.disconnect();

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();

            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlPullParser.setInput(inputStream, null);

            return xmlPullParser;

        }catch(Exception ex){
            Log.e("app", "exception", ex);
        }
        return null;
    }
    @Override
    protected void onPostExecute(XmlPullParser xmlPullParser){
        XMLProcessor xmlProcessor = new XMLProcessor(xmlPullParser);
        try {
            ArrayList<Weather> weathers = xmlProcessor.process();
            this.delegate.finalResponse(weathers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


