package com.example.android.bakingapplication.data;

import java.util.ArrayList;

public class recipe {

    int id;
    String name;
    ArrayList<ingredients> ingredients;
    ArrayList<steps> steps;
    int servings;

    public recipe(int myID, String myName, ArrayList<ingredients> myIngredeints, ArrayList<steps> mySteps, int myServings) {
        id = myID;
        name = myName;
        ingredients = myIngredeints;
        steps = mySteps;
        servings = myServings;

    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public ArrayList<ingredients> getIngredients(){
        return ingredients;
    }

    public ArrayList<steps> getSteps(){
        return steps;
    }

    public int getServings(){
        return servings;
    }

}
