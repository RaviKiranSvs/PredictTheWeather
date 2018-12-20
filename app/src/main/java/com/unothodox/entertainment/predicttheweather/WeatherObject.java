package com.unothodox.entertainment.predicttheweather;

public class WeatherObject {
    private String Date;
    private float T_C;

    public WeatherObject(String date, float t_C) {
        Date = date;
        T_C = t_C;
    }

    public String getDate() {
        return Date;
    }

    public float getT_C() {
        return T_C;
    }
}
