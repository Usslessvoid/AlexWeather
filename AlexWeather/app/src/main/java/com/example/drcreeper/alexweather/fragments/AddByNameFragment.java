package com.example.drcreeper.alexweather.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    Thread readTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_by_name, container, false);
        ButterKnife.bind(this,view);
        readTask = new Thread(new Runnable() {
            @Override
            public void run() {
/*
                try {
                    InputStreamReader stream = new InputStreamReader(getActivity().getAssets().open("citylist.json"));
                    Gson gson = new Gson();
                    cities = gson.fromJson(stream, Cities.class);

                } catch (IOException |ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
 }*/
                File listFile = null;
                File input = null;
                File cacheDir = getContext().getCacheDir();
                try {
                    input = new File(cacheDir, Utils.DUMP_FILE_NAME);
                    while (!input.exists()) {
                        TimeUnit.SECONDS.sleep(2);
                        input = new File(cacheDir, Utils.DUMP_FILE_NAME);
                    }
                    listFile = new File(cacheDir, Utils.LIST_FILE_NAE);
                    while (!listFile.exists()) {
                        listFile = new File(cacheDir, Utils.LIST_FILE_NAE);
                        TimeUnit.SECONDS.sleep(2);
                    }
                    ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(listFile));
                    final List<String> list = (List<String>)inputStream.readObject();
                    if(getActivity()!=null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                findView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, list));
                            }
                        });
                    }
                    inputStream = new ObjectInputStream(new FileInputStream(input));
                    cities = (Cities)inputStream.readObject();
                }catch (InterruptedException|IOException|ClassNotFoundException e){
                    Log.e("lol",e.toString());
                }



            }
        });
        readTask.start();
        return view;
    }


    @OnClick(R.id.go)
    void onFindPress(){
        int townId = -1;
        if(cities == null){
            Toast.makeText(getContext(),R.string.wait,Toast.LENGTH_LONG).show();
            return;
        }
        bar.setVisibility(View.VISIBLE);
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
