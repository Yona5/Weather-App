package com.project.weatherapp.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;
import com.project.weatherapp.model.Weather;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLParser extends AsyncTask<Void, Void, XmlPullParser> {

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


    @Override
    protected XmlPullParser doInBackground(Void... voids) {
        String urlStrg = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1545752";

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
            xmlProcessor.process();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        this.delegate.finalResponse(weather);
    }
}


