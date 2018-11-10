package com.example.drcreeper.alexweather.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.activities.WeatherActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddLocationFragment extends Fragment {
    @BindView(R.id.radios)
    RadioGroup choose;
    @BindView(R.id.next)
    Button next;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frragment_add_location,container,true);
        ButterKnife.bind(this,view);
        next.setEnabled(false);
        choose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                next.setEnabled(true);
            }
        });
        return view;
    }


    @OnClick(R.id.next)
    public void onNextPress(){
        switch (choose.getCheckedRadioButtonId()){
            case R.id.radio_position:

                break;
            case R.id.radio_name:

                break;
            case R.id.radio_coord:
                Intent intent = new Intent(getContext(),WeatherActivity.class);
                getActivity().startActivity(intent);
                break;
                default:
                    Toast.makeText(getContext(),"Noo!!!",Toast.LENGTH_LONG).show();

        }
    }
}
