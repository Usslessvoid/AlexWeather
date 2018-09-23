package com.example.drcreeper.alexweather.utils;

import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetWeather {
    @GET("/data/2.5/weather")
    Call<WeatherAnswer> getData(@Query("appid") String appId, @Query("lang") String locale, @Query("units") String units, @Query("id") int id);
}