package com.example.drcreeper.alexweather.utils;

import android.os.AsyncTask;

import com.example.drcreeper.alexweather.models.LocationItem;

public class WriteLocationAsyncTask extends AsyncTask<LocationItem,Void,Void> {
    @Override
    protected Void doInBackground(LocationItem... locationItems) {
        Utils.getLocationDAO().putLocation(locationItems[0]);
        return null;
    }
}
