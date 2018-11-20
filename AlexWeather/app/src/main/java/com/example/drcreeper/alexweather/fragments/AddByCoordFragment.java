package com.example.drcreeper.alexweather.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.drcreeper.alexweather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddByCoordFragment extends Fragment {
    @BindView(R.id.lat)
    EditText latText;
    @BindView(R.id.lon)
    EditText lonText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_by_coord, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.ok)
    public void onOkClick(){
        Intent result = new Intent();
        try{
            result.putExtra("lat",Double.parseDouble(latText.getText().toString()));
            result.putExtra("lon",Double.parseDouble(lonText.getText().toString()));
            getActivity().setResult(Activity.RESULT_OK,result);
        }catch (NumberFormatException e){
            Toast.makeText(getContext(),"NOOOOOOOO, parse err",Toast.LENGTH_LONG).show();
            getActivity().setResult(Activity.RESULT_CANCELED,result);
        }
        getActivity().finish();
    }
}
