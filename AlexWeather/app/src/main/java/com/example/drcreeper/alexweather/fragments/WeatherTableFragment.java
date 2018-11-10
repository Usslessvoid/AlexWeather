package com.example.drcreeper.alexweather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.drcreeper.alexweather.R;


public class WeatherTableFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*Utils.getWeatherServise().getWeatherList(Utils.APPID,Utils.LOCALE,Utils.UNITS,627907).enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                String lol = response.body().getCity().getName();
                Toast.makeText(getContext(),lol,Toast.LENGTH_LONG).show();
                List<WeatherData> l = response.body().getWeatherDataList(84);
            }

            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                Toast.makeText(getContext(),"ggwp"+t,Toast.LENGTH_LONG).show();
            }
        });
        */
               View view = inflater.inflate(R.layout.fragment_weather_table, container, false);
        return view;
    }

}
