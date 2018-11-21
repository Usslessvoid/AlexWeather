package com.example.drcreeper.alexweather.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.WeatherActivity;
import com.example.drcreeper.alexweather.models.LocationItem;
import com.example.drcreeper.alexweather.utils.PicturePicker;

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
            holder.state = (ImageView) convertView.findViewById(R.id.image);
            holder.lastUpdate = (TextView) convertView.findViewById(R.id.last_update);
            convertView.setTag(holder);
        }else {
            holder = (LocationHolder)convertView.getTag();
        }
        final LocationItem item = getItem(position);
        holder.location.setText(item.getName());
        holder.temperature.setText(Integer.toString((int) Math.round(item.getTemperature())));
        holder.state.setImageResource(PicturePicker.getPicture(item.getState()));
        holder.lastUpdate.setText(item.getDate());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WeatherActivity.class);
                intent.putExtra("id",item.getTownId());
                intent.putExtra("name",item.getName());
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
