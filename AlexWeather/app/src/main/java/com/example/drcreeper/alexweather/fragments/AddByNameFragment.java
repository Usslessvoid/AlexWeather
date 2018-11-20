package com.example.drcreeper.alexweather.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.MainActivity;
import com.example.drcreeper.alexweather.models.LocationItem;
import com.example.drcreeper.alexweather.models.generated.Cities;
import com.example.drcreeper.alexweather.models.generated.City;
import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;
import com.example.drcreeper.alexweather.utils.Utils;
import com.example.drcreeper.alexweather.utils.WriteLocationAsyncTask;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddByNameFragment extends Fragment {
    @BindView(R.id.find)
    AutoCompleteTextView findView;
    @BindView(R.id.progress)
    ProgressBar bar;
    Cities cities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_by_name, container, false);
        ButterKnife.bind(this,view);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    InputStreamReader stream = new InputStreamReader(getActivity().getAssets().open("citylist.json"));
                    Gson gson = new Gson();
                    cities = gson.fromJson(stream, Cities.class);

                } catch (IOException |ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                final List<String> list = cities.getNames();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findView.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,list));
                    }
                });
            }
        }).start();

        return view;
    }
    @OnClick(R.id.go)
    void onFindPress(){
        bar.setVisibility(View.VISIBLE);
        int townId = -1;
        String text = findView.getText().toString();
        for(City city: cities.getList()){
            if(text.equals(city.getName())){
                townId = city.getId();
                break;
            }
        }
        if(townId > 0){
            Utils.getWeatherServise().getData(Utils.APPID,Utils.LOCALE,Utils.UNITS,townId).enqueue(new Callback<WeatherAnswer>() {
                @Override
                public void onResponse(Call<WeatherAnswer> call, Response<WeatherAnswer> response) {
                    if(response.body() != null){
                        LocationItem item = new LocationItem(response.body());
                        WriteLocationAsyncTask task = new WriteLocationAsyncTask();
                        task.execute(item);
                        Intent toChoose = new Intent(getActivity(), MainActivity.class);
                        getActivity().finish();
                    }
                }

                @Override
                public void onFailure(Call<WeatherAnswer> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                    Toast.makeText(getContext(),R.string.no_internet,Toast.LENGTH_LONG).show();

                }
            });
        }else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getContext(),R.string.no_town,Toast.LENGTH_LONG).show();
        }
    }

}
