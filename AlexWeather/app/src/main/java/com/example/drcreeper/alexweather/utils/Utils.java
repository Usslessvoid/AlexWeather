package com.example.drcreeper.alexweather.utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.drcreeper.alexweather.models.WeatherData;

import java.util.List;
import java.util.Locale;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils extends Application {
    public static final String JSON_FILE_NAME = "citylist.json";
    public static final String DUMP_FILE_NAME = "cties.dat";
    public static final String LIST_FILE_NAE = "list.dat";
    public static final long MIN_UPDATE_INTERVAL = 60000;
    public static final int GET_COORD = 228;
    public static final String BASE_URL = "http://api.openweathermap.org";
    public static final String APPID = "5753974d4f4e8e428ba77f4eb8816501";
    public static final String UNITS = "metric";
    public static final String LOCALE = "ru";
    private static GetWeather weatherServise;
    private static LocationDAO locationDAO;
    private static WeatherDAO weatherDAO;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
       weatherServise = retrofit.create(GetWeather.class);
       WeatherDatabase db = Room.databaseBuilder(getApplicationContext(),WeatherDatabase.class,"weather_database").build();
       locationDAO = db.getLocationDAO();
       weatherDAO = db.geWeatherDAO();
    }
    public static LocationDAO getLocationDAO(){
        return locationDAO;
    }

    public static WeatherDAO getWeatherDAO() {
        return weatherDAO;
    }

    public static GetWeather getWeatherServise(){
        return  weatherServise;
    }
    //How better avoid it?
    public static void writeWeather(final WeatherData data){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                weatherDAO.putWeather(data);
            }
        };
        new Thread(task).start();
    }
    public static void writeAllWeather(final List<WeatherData> data){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                weatherDAO.putAll(data);
            }
        };
        new Thread(task).start();
    }
    public static void clearOld(final long time){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                weatherDAO.clear(time);
            }
        };
        new Thread(task).start();
    }
    public static void clearLocation(final int id){
        Runnable task = new Runnable() {
            @Override
            public void run() {
                weatherDAO.clearLocation(id);
            }
        };
        new Thread(task).start();
    }
    public static String normalizeNumber(double num){
        String pattern;// TODO: 20.11.2018
        if(num % 1 == 0){
            pattern = "%2.0f";
        }else {
            pattern = "%2.1f";
        }
        return String.format(Locale.ENGLISH,pattern,num);
    }
}
