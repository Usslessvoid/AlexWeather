package com.example.drcreeper.alexweather.fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.WeatherActivity;
import com.example.drcreeper.alexweather.models.WeatherData;
import com.example.drcreeper.alexweather.utils.PicturePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class WeekListRecyclerViewAdapter extends RecyclerView.Adapter<WeekListRecyclerViewAdapter.ViewHolder> {
    public static final String DAY_MASK = "d, HH:mm";
    private List<WeatherData> mValues;
    private int showPosition = 0;
    private Context context;
    private WeatherActivity activity;
    private String[] weekDays;
    private String now = "";

    public WeekListRecyclerViewAdapter(List<WeatherData> items, WeatherActivity activity) {
        mValues = items;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        weekDays = parent.getContext().getResources().getStringArray(R.array.week_days);
        now = parent.getContext().getString(R.string.now);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        WeatherData item = mValues.get(position);
        StringBuilder name = new StringBuilder();
        if(item.getTime() == 0){
            name.append(now);
            //holder.mView.setBackgroundResource(R.color.weatherColor);
        }else {
            Date date = new Date(item.getTime());
            SimpleDateFormat format;
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); //Integer.parseInt(format.format(date));
            name.append(weekDays[dayOfWeek - 1]);
            name.append(" ");
            format = new SimpleDateFormat(DAY_MASK);
            name.append(format.format(date));
        }
        holder.day.setText(name.toString());
        holder.temperature.setText(Double.toString(item.getTemperature()));
        holder.image.setImageResource(PicturePicker.getPicture(item.getPicture()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.showWeather(mValues.get(position));
                notifyItemChanged(showPosition);
                showPosition = position;
                notifyItemChanged(showPosition);
            }
        });
        if(position == showPosition){
            holder.mView.setBackgroundResource(R.color.weatherColor);
        }else {
            holder.mView.setBackgroundResource(R.color.white);
        }
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
