package com.example.android.bakingapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

public class steps implements Parcelable {
    int id;
    String shortDescription;
    String description;
    String videoURL;

    public steps (int myId, String myShortDescription, String myDescription, String myVideoUrl){
        id = myId;
        shortDescription = myShortDescription;
        description = myDescription;
        videoURL = myVideoUrl;
    }

    public int getId(){
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription(){
        return description;
    }

    public String getVideoURL(){return videoURL;}

    public steps (Parcel inParcel){
        id = inParcel.readInt();
        shortDescription = inParcel.readString();
        description = inParcel.readString();
        videoURL = inParcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(shortDescription);
        parcel.writeString(description);
        parcel.writeString(videoURL);
    }

    public static final Parcelable.Creator<steps> CREATOR
            = new Parcelable.Creator<steps>(){
        public steps createFromParcel(Parcel in){
            return new steps(in);
        }

        public steps[] newArray(int size) {
            return new steps[size];        }
    };


}
