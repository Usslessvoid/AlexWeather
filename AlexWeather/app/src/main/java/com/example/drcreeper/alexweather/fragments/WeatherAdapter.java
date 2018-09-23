package com.example.drcreeper.alexweather.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.models.LocationItem;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<LocationItem> {
    public WeatherAdapter(@NonNull Context context, int resource, @NonNull List<LocationItem> objects) {
        super(context, resource, objects);
    }

    public WeatherAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LocationHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.location_item, parent,false);
            holder = new LocationHolder();
            holder.location = (TextView) convertView.findViewById(R.id.location_name);
            holder.temperature = (TextView)convertView.findViewById(R.id.temperature);
            holder.state = (TextView)convertView.findViewById(R.id.state);
            holder.lastUpdate = (TextView) convertView.findViewById(R.id.last_update);
            convertView.setTag(holder);
        }else {
            holder = (LocationHolder)convertView.getTag();
        }
        LocationItem item = getItem(position);
        holder.location.setText(item.getName());
        holder.temperature.setText(Integer.toString(item.getTemperature()));
        holder.state.setText(item.getState());
        holder.lastUpdate.setText(item.getDate());
        return convertView;
    }
}