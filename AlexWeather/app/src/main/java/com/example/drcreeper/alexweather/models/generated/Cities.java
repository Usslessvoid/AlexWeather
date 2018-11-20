package com.example.drcreeper.alexweather.models.generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class Cities {
    @Expose
    @SerializedName("list")
    private List<City> list = new LinkedList<>();

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }
    public List<String> getNames(){
        List<String> result = new LinkedList<>();
        for(City city:list){
            result.add(city.getName());
        }
        return result;
    }
}
