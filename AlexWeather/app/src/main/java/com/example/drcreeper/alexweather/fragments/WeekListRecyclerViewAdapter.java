package com.example.drcreeper.alexweather.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.models.WeatherData;
import com.example.drcreeper.alexweather.utils.PicturePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekListRecyclerViewAdapter extends RecyclerView.Adapter<WeekListRecyclerViewAdapter.ViewHolder> {
    public static final String WEEK_DAY_MASK = "u";
    public static final String DAY_MASK = "d, HH:mm";
    private List<WeatherData> mValues = new ArrayList<>();
    private String[] weekDays;

    public WeekListRecyclerViewAdapter(List<WeatherData> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        weekDays = parent.getContext().getResources().getStringArray(R.array.week_days);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        WeatherData item = mValues.get(position);
        Date date = new Date(item.getTime());
        SimpleDateFormat format = new SimpleDateFormat(WEEK_DAY_MASK);
        int dayOfWeek = Integer.parseInt(format.format(date));
        StringBuilder name = new StringBuilder(weekDays[dayOfWeek - 1]);
        name.append(" ");
        format = new SimpleDateFormat(DAY_MASK);
        name.append(format.format(date));
        holder.day.setText(name.toString());
        holder.temperature.setText(Double.toString(item.getTemperature()));
        holder.image.setImageResource(PicturePicker.getPicture(item.getPicture()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView day;
        public TextView temperature;
        public ImageView image;
        public View mView;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            day = (TextView) view.findViewById(R.id.week_day);
            temperature = (TextView) view.findViewById(R.id.week_temp);
            image = (ImageView) view.findViewById(R.id.week_image);
        }
    }
}
