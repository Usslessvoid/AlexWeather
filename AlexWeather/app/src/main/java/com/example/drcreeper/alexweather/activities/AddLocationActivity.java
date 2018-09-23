package com.example.drcreeper.alexweather.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.drcreeper.alexweather.R;

public class AddLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        getSupportActionBar().setTitle(R.string.add_location_activity_tittle);
    }
}
