package com.example.drcreeper.alexweather.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.AddByCoord;
import com.example.drcreeper.alexweather.activities.AddByName;
import com.example.drcreeper.alexweather.models.LocationItem;
import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;
import com.example.drcreeper.alexweather.utils.Utils;
import com.example.drcreeper.alexweather.utils.WriteLocationAsyncTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLocationFragment extends Fragment {
    @BindView(R.id.main)
    LinearLayout layout;
    @BindView(R.id.radios)
    RadioGroup choose;
    @BindView(R.id.next)
    Button next;
    LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frragment_add_location, container, true);
        ButterKnife.bind(this, view);
        next.setEnabled(false);
        choose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                next.setEnabled(true);
            }
        });
        return view;
    }

    @SuppressLint("MissingPermission")
    @OnClick(R.id.next)
    public void onNextPress() {
        switch (choose.getCheckedRadioButtonId()) {
            case R.id.radio_position:
                locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                Location location;
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) { //&&(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    addByCoord(location.getLatitude(),location.getLongitude());
                }
                break;
            case R.id.radio_name:
                Intent toName = new Intent(getContext(), AddByName.class);
                startActivity(toName);
                break;
            case R.id.radio_coord:
                Intent intent = new Intent(getContext(), AddByCoord.class);
                startActivityForResult(intent, Utils.GET_COORD);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if((requestCode == Utils.GET_COORD)&&(resultCode == Activity.RESULT_OK)){
            addByCoord(data.getDoubleExtra("lat",0),data.getDoubleExtra("lon",0));
        }

    }
    private void addByCoord(double lat, double lon){
        Utils.getWeatherServise().getDataByCoord(Utils.APPID,Utils.LOCALE,Utils.UNITS,lat,lon).enqueue(new Callback<WeatherAnswer>() {
            @Override
            public void onResponse(Call<WeatherAnswer> call, Response<WeatherAnswer> response) {
                if(response.body()!=null){
                    LocationItem item = new LocationItem(response.body());
                    WriteLocationAsyncTask task = new WriteLocationAsyncTask();
                    task.execute(item);
                    getActivity().finish();
                }else {
                    onFailure(call, new NullPointerException());
                }
            }

            @Override
            public void onFailure(Call<WeatherAnswer> call, Throwable t) {
                Toast.makeText(getContext(),R.string.no_internet,Toast.LENGTH_LONG).show();
            }
        });
    }
}
