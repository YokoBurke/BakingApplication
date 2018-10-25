package com.example.android.bakingapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ingredients implements Parcelable {

    //@SerializedName("quantity")
    String quantity;

    String measure;
    String ingredient;

    public ingredients (String myQuantity, String myMeasure, String myIngredient){

        quantity = myQuantity;
        measure = myMeasure;
        ingredient = myIngredient;
    }

    public String getQuanity() {
        return quantity;
    }

    public String getMeasure(){
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    private ingredients (Parcel inParcel){
        quantity = inParcel.readString();
        measure = inParcel.readString();
        ingredient = inParcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(quantity);
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
