package com.unothodox.entertainment.predicttheweather;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PreviousActivity extends AppCompatActivity {

    ListView lv_list;
    WeatherAPI weather;
    ArrayList<WeatherObject> weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous);

        lv_list = findViewById(R.id.lv_list);
        weather = new WeatherAPI();

        getWeatherData();
    }

    void getWeatherData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                weatherList = weather.getWeather();
                handler.sendEmptyMessage(0);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ListAdapter adapter = new WeatherLayout(PreviousActivity.this, weatherList);
            lv_list.setAdapter(adapter);

        }
    };
}
