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

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.AddLocationActivity;
import com.example.drcreeper.alexweather.models.ChacheService;
import com.example.drcreeper.alexweather.models.LocationItem;
import com.example.drcreeper.alexweather.utils.Callback;
import com.example.drcreeper.alexweather.utils.ReadLocationsAsyncTask;
import com.example.drcreeper.alexweather.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// api key: 5753974d4f4e8e428ba77f4eb8816501

public class LocationChooseFragment extends Fragment {

    @BindView(R.id.location_list)
    ListView locationsList;
    @BindView(R.id.no_any_elements)
    View v;

    List<LocationItem> list;
    WeatherAdapter adapter;
    ReadLocationsAsyncTask readTask;
    boolean needUpdate = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        list = new ArrayList<>();
        readTask = new ReadLocationsAsyncTask();
        readTask.setCallback(new com.example.drcreeper.alexweather.utils.Callback() {
            @Override
            public Object run(Object o) {
                list.addAll((List<LocationItem>)o);
                adapter.notifyDataSetChanged();
                return null;
            }
        });
        adapter = new WeatherAdapter(getContext(),R.layout.location_item,list);
        locationsList.setEmptyView(v);
        locationsList.setAdapter(adapter);
        needUpdate = false;
        readTask.execute();
        File file = new File(getContext().getCacheDir(), Utils.DUMP_FILE_NAME);
        if(!file.exists()){
            getContext().startService(new Intent(getContext(), ChacheService.class));
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(needUpdate){
            update();
        }
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
    public void update(){
        readTask = new ReadLocationsAsyncTask();
        readTask.setCallback(new Callback() {
            @Override
            public Object run(Object o) {
                list.clear();
                list.addAll((List<LocationItem>)o);
                adapter.notifyDataSetChanged();
                return null;
            }
        });
        readTask.execute();
    }
    public void add(){
        needUpdate = true;
        Intent addLocation = new Intent(getContext(), AddLocationActivity.class);
        getActivity().startActivity(addLocation);
    }
    @OnClick(R.id.create_location_button)
    public void onAddPress(){
        add();
    }
}
