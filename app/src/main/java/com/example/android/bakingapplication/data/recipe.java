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

    public int getRecipeId() {
        return recipeId;
    }

    public String getRecipeName(){
        return recipeName;
    }

    public ArrayList<ingredients> getRecipeIngredeints(){
        return recipeIngredeints;
    }

    public ArrayList<steps> getRecipeSteps(){
        return recipeSteps;
    }

    public int getRecipeServings(){
        return recipeServings;
    }

}
