package com.example.drcreeper.alexweather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.WeatherActivity;
import com.example.drcreeper.alexweather.models.WeatherData;
import com.example.drcreeper.alexweather.models.generated.WeatherList;
import com.example.drcreeper.alexweather.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>

 * interface.
 */
public class WeekListFragment extends Fragment {

    /* TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters       627907
    private int mColumnCount = 1;
                String lol = response.body().getCity().getName();
                Toast.makeText(getContext(),lol,Toast.LENGTH_LONG).show();*/
    private RecyclerView recyclerView;
    private List<WeatherData> list;
    private WeatherData currentWeather;
    private int townId;


    public void setCurrentWeather(WeatherData currentWeather) {
        this.currentWeather = currentWeather;
        if(list == null){
            list = new ArrayList<>();
            list.add(0,currentWeather);
        }else {
            list.add(0,currentWeather);
            recyclerView.getAdapter().notifyDataSetChanged();
            updateWeather(townId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        townId = getActivity().getIntent().getIntExtra("id",625144);
        recyclerView = (RecyclerView)view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new WeekListRecyclerViewAdapter(new ArrayList<WeatherData>(), (WeatherActivity) getActivity()));
        Utils.getWeatherServise().getWeatherList(Utils.APPID,Utils.LOCALE,Utils.UNITS,townId).enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                if(response.body()!=null) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.addAll(response.body().getWeatherDataList(townId));//84
                    recyclerView.setAdapter(new WeekListRecyclerViewAdapter(list, (WeatherActivity) getActivity()));
                    if(currentWeather != null){
                        updateWeather(townId);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                Toast.makeText(getContext(), "ggwp" + t, Toast.LENGTH_LONG).show();
            }
        });
        /* Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new WeekListRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        */
        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void updateWeather(int id){
        Utils.clearLocation(id);
        Utils.writeAllWeather(list);
    }
}
