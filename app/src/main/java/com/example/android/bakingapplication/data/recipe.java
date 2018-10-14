package com.example.android.bakingapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class recipe implements Parcelable {


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

     public recipe(Parcel inParcel) {
         id = inParcel.readInt();
         name = inParcel.readString();
         ingredients = inParcel.readParcelable(com.example.android.bakingapplication.data.ingredients.class.getClassLoader());
         steps = inParcel.readParcelable(com.example.android.bakingapplication.data.steps.class.getClassLoader());
         servings = inParcel.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    public static final Parcelable.Creator<recipe> CREATOR = new Parcelable.Creator<recipe>() {
        public recipe createFromParcel(Parcel in){
            return new recipe(in);
        }

        public recipe[] newArray(int size) {
            return new recipe[size];
        }

    };
}
