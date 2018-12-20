package com.unothodox.entertainment.predicttheweather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class WeatherAPI {

    private static final String TAG = "WeatherAPI";
    private static final String APIkey = "989b73eafa5247ae9580f164cbd0c2ba";
    private static final String City = "Vijayawada";

    ArrayList<WeatherObject> getWeather()    {
        HttpURLConnection connection = null;    //establish a connection
        BufferedReader reader = null;           //this thing reads

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast" +
                    "?q=" + City +
                    "&appid=" + APIkey);                                    //this is the URL from which we will get the json

            connection = (HttpURLConnection) url.openConnection();          //We give connection, which url to connect to
            connection.connect();                                           //We start the connection
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));     //putting it into reader

            StringBuilder buffer = new StringBuilder();                     //new String Builder
            String line;
            while((line = reader.readLine()) != null)   {
                buffer.append(line).append("\n");
                Log.d(TAG, "getWeather: line appended - " + line);
            }

            JSONArray WeatherList = new JSONObject(buffer.toString()).getJSONArray("list");
            ArrayList<WeatherObject> wObj = new ArrayList<>();
            for (int i=0; i<WeatherList.length(); i++) {
                JSONObject jObj = WeatherList.getJSONObject(i);
                String date = jObj.getString("dt_txt");
                float t_k = Float.parseFloat(jObj.getJSONObject("main").getString("temp"));
                float t_c = (float)((int)((t_k - 273.15)*100))/100;
                wObj.add(new WeatherObject(date, t_c));
            }
            return wObj;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
                Log.d(TAG, "getWeather: connection disconnected.");
            }
            try {
                if(reader!=null) {
                    reader.close();
                    Log.d(TAG, "getWeather: reader closed.");
                }
            }catch (IOException e)  {
                e.printStackTrace();
            }
        }
        Log.d(TAG, "getWeather: null returned.");
        return null;
    }
}
