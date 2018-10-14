package com.example.android.bakingapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ingredients implements Parcelable {

    int quanity;
    String measure;
    String ingredient;

    public ingredients (int myQuantity, String myMeasure, String myIngredient){
        quanity = myQuantity;
        measure = myMeasure;
        ingredient = myIngredient;
    }

    public int getQuanity() {
        return quanity;
    }

    public String getMeasure(){
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    private ingredients (Parcel inParcel){
        quanity = inParcel.readInt();
        measure = inParcel.readString();
        ingredient = inParcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeInt(quanity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public static final Parcelable.Creator<ingredients> CREATOR
            = new Parcelable.Creator<ingredients>(){
        public ingredients createFromParcel(Parcel in){
            return new ingredients(in);
        }

        public ingredients[] newArray(int size){
            return new ingredients[size];
        }
    };




}
