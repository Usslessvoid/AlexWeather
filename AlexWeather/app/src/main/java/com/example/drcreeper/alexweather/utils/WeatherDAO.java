package com.example.drcreeper.alexweather.utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.drcreeper.alexweather.models.WeatherData;

import java.util.List;

@Dao
public interface WeatherDAO {
    @Query("SELECT * FROM weatherdata")
    List<WeatherData> getAll();
    @Insert
    void putWeather(WeatherData data);
    @Insert
    void putAll(List<WeatherData> list);
    @Query("DELETE FROM weatherdata WHERE time<:nowTime")
    void clear(long nowTime);
    @Query("DELETE FROM weatherdata WHERE locationId = :id")
    void clearLocation(int id);
}
