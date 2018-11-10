package com.example.drcreeper.alexweather.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;

import java.text.SimpleDateFormat;
import java.util.Date;
@Entity
public class LocationItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double temperature;
    private String state;
    private long lastUpdate;
    private int townId;

    public LocationItem() {
        name = "Unknown";
        temperature = 0;
        state = "Unknown";
        lastUpdate = 0;
    }

    public LocationItem(WeatherAnswer source){
        townId =source.getId();
        name = source.getName();
        temperature = source.getMain().getTemp();
        state = source.getWeather().get(0).getDescription();
        lastUpdate = new Date().getTime();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }
    public String getDate(){
        Date lastUpdate = new Date(this.lastUpdate);
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.YYYY HH:mm");
        return date.format(lastUpdate);
    }
}
