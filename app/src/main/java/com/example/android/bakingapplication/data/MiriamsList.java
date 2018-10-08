package com.example.android.bakingapplication.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class MiriamsList {

    List<recipe> miriamsRecipe;

    public MiriamsList(){
        miriamsRecipe = new ArrayList<recipe>();
    }

    public static MiriamsList parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        MiriamsList miriamsList = gson.fromJson(response, MiriamsList.class);
        return miriamsList;
    }

    //Note 1:http://www.javacreed.com/simple-gson-example/
    //Note 2: https://guides.codepath.com/android/leveraging-the-gson-library#mapping-java-date-objects
}
