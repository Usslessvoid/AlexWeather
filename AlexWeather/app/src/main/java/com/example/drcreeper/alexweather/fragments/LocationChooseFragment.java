package com.example.drcreeper.alexweather.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.AddLocationActivity;
import com.example.drcreeper.alexweather.models.LocationItem;
import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;
import com.example.drcreeper.alexweather.utils.ReadLocationsAsyncTask;
import com.example.drcreeper.alexweather.utils.Utils;
import com.example.drcreeper.alexweather.utils.WriteLocationAsyncTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

// api key: 5753974d4f4e8e428ba77f4eb8816501

public class LocationChooseFragment extends Fragment {

    @BindView(R.id.location_list)
    ListView locationsList;
    @BindView(R.id.no_any_elements)
    View v;

    List<LocationItem> list;
    WeatherAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        list = new ArrayList<>();
        ReadLocationsAsyncTask readTask = new ReadLocationsAsyncTask();
        readTask.setCallback(new com.example.drcreeper.alexweather.utils.Callback() {
            @Override
            public Object run(Object o) {
                list.addAll((List<LocationItem>)o);
                locationsList.setAdapter(adapter);
                return null;
            }
        });
        readTask.execute();

        Utils.getWeatherServise().getData(Utils.APPID, Utils.LOCALE,Utils.UNITS,625144).enqueue(new Callback<WeatherAnswer>() {
            @Override
            public void onResponse(retrofit2.Call<WeatherAnswer> call, Response<WeatherAnswer> response) {
                if(response!= null){
                    LocationItem locationItem = new LocationItem(response.body());
                    list.add(locationItem);
                    WriteLocationAsyncTask task = new WriteLocationAsyncTask();
                    task.execute(locationItem);
                    locationsList.setAdapter(adapter);

                }else {
                    onFailure(call,null);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<WeatherAnswer> call, Throwable t) {
                Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
/**/
        adapter = new WeatherAdapter(getContext(),R.layout.location_item,list);
        locationsList.setEmptyView(v);
        locationsList.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_button:
                refresh();
                break;
            case R.id.add_button:
                add();
                break;
        }
        return true;
    }

    public void refresh(){

    }

    public void add(){
        Intent addLocation = new Intent(getContext(), AddLocationActivity.class);
        getActivity().startActivity(addLocation);
    }
    @OnClick(R.id.create_location_button)
    public void onAddPress(){
        add();
    }
}
