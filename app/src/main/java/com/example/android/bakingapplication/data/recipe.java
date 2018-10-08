package com.example.android.bakingapplication.data;

import java.util.ArrayList;

public class recipe {

    int recipeId;
    String recipeName;
    ArrayList<ingredients> recipeIngredeints;
    ArrayList<steps> recipeSteps;
    int recipeServings;

    public recipe(int myID, String myName, ArrayList<ingredients> myIngredeints, ArrayList<steps> mySteps, int myServings) {
        recipeId = myID;
        recipeName = myName;
        recipeIngredeints = myIngredeints;
        recipeSteps = mySteps;
        recipeServings = myServings;

    }

}
