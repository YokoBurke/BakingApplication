package com.example.android.bakingapplication.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MiriamsList {

    List<recipe> miriamsRecipe;

    public MiriamsList(){
        miriamsRecipe = new ArrayList<recipe>();
    }

    public static List<recipe> parseJSON(String response) {

        // note https://stackoverflow.com/questions/9598707/gson-throwing-expected-begin-object-but-was-begin-array
        /* Gson gson = new GsonBuilder().create();
        Type miriamType = new TypeToken<Collection<recipe>>(){}.getType();
        Collection<recipe> myrecipes = gson.fromJson(response, miriamType);
        return myrecipes; */

        Gson gson = new GsonBuilder().create();
        List<recipe> list = gson.fromJson(response, new TypeToken<List<recipe>>(){}.getType());

        return list;
    }

    //Note 1:http://www.javacreed.com/simple-gson-example/
    //Note 2: https://guides.codepath.com/android/leveraging-the-gson-library#mapping-java-date-objects
}
