package com.example.drcreeper.alexweather.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.drcreeper.alexweather.models.LocationItem;
import com.example.drcreeper.alexweather.models.WeatherData;

@Database(entities = {LocationItem.class, WeatherData.class},version = 1,exportSchema = false)//
public abstract class WeatherDatabase extends RoomDatabase {
    public abstract LocationDAO getLocationDAO();
    public abstract WeatherDAO geWeatherDAO();
}
