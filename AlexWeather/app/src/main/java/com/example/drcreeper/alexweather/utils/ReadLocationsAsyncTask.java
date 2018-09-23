package com.example.drcreeper.alexweather.utils;

import android.os.AsyncTask;

import com.example.drcreeper.alexweather.models.LocationItem;

import java.util.List;

public class ReadLocationsAsyncTask extends AsyncTask<Void,Void,List<LocationItem>> {
private Callback callback;

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected List<LocationItem> doInBackground(Void... voids) {
        return Utils.getLocationDAO().getAll();
    }

    @Override
    protected void onPostExecute(List<LocationItem> locationItems) {
        callback.run(locationItems);
    }
}
