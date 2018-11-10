package com.example.drcreeper.alexweather.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class WeatherData {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int locationId;
    private long time;
    private String state;
    private String picture;
    private double temperature;
    private double minTemperature;
    private double maxTemperature;
    private double pressure;
    private double humidity;
    private double windAngle;
    private double windSpeed;

    public WeatherData() {
        locationId = 0;
        state = "none";
        picture = "none";
        temperature = 0;
        minTemperature = 0;
        maxTemperature = 0;
        pressure = 0;
        humidity = 0;
        windAngle = 0;
        windSpeed = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindAngle() {
        return windAngle;
    }

    public void setWindAngle(double windAngle) {
        this.windAngle = windAngle;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
}
