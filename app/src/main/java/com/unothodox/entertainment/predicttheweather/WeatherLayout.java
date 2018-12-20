package com.unothodox.entertainment.predicttheweather;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherLayout extends ArrayAdapter<WeatherObject> {

    public WeatherLayout(Context context, ArrayList<WeatherObject> e) {
        super(context, R.layout.layout_weather, e);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inf = LayoutInflater.from(getContext());
            convertView = inf.inflate(R.layout.layout_weather, parent, false);
        }
        WeatherObject wObj = getItem(position);
        TextView tv_date = convertView.findViewById(R.id.tv_date);
        TextView tv_temp = convertView.findViewById(R.id.tv_temp);

        assert wObj != null;
        tv_date.setText(wObj.getDate());
        tv_temp.setText(String.valueOf(wObj.getT_C()));

        return convertView;
    }
}
