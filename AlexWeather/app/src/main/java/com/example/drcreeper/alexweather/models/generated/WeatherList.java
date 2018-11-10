
package com.example.drcreeper.alexweather.models.generated;

import com.example.drcreeper.alexweather.models.WeatherData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherList {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private double message;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = null;
    @SerializedName("city")
    @Expose
    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    public java.util.List<WeatherData> getWeatherDataList(int location){
        java.util.List<WeatherData> data = new ArrayList<>();
        for(List item:list){
            WeatherData weather = new WeatherData();
            weather.setLocationId(location);
            weather.setTime(1000l * item.getDt());
            weather.setState(item.getWeather().get(0).getDescription());
            weather.setPicture(item.getWeather().get(0).getIcon());
            weather.setTemperature(item.getMain().getTemp());
            weather.setMinTemperature(item.getMain().getTempMin());
            weather.setMaxTemperature(item.getMain().getTempMax());
            weather.setPressure(item.getMain().getPressure());
            weather.setHumidity(item.getMain().getHumidity());
            weather.setWindSpeed(item.getWind().getSpeed());
            weather.setWindAngle(item.getWind().getDeg());
            data.add(weather);
        }
        return  data;
    }
}
