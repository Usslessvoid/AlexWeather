package com.example.drcreeper.alexweather.utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.drcreeper.alexweather.models.LocationItem;

import java.util.List;

@Dao
public interface LocationDAO {
    @Query("SELECT * FROM locationitem")
    List<LocationItem> getAll();
    @Insert
    void putLocation(LocationItem item);
    @Delete
    void deleteLocation(LocationItem item);
}
