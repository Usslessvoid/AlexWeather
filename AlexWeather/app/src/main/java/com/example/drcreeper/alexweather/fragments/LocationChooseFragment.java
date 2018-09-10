package com.example.drcreeper.alexweather.fragments;

import android.app.ListActivity;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.drcreeper.alexweather.AddLocationActivity;
import com.example.drcreeper.alexweather.R;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        locationsList.setEmptyView(v);


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
