package com.example.drcreeper.alexweather.utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils extends Application {

    public static final String BASE_URL = "http://api.openweathermap.org";
    public static final String APPID = "5753974d4f4e8e428ba77f4eb8816501";
    public static final String UNITS = "metric";
    public static final String LOCALE = "ru";
    private static GetWeather weatherServise;
    private static LocationDAO locationDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
       weatherServise = retrofit.create(GetWeather.class);
       WeatherDatabase db = Room.databaseBuilder(getApplicationContext(),WeatherDatabase.class,"weather_database").build();
       locationDAO = db.getLocationDAO();
    }
    public static LocationDAO getLocationDAO(){
        return locationDAO;
    }
    public static GetWeather getWeatherServise(){
        return  weatherServise;
    }
}
