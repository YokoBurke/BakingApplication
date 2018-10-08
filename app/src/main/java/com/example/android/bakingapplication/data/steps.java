package com.example.android.bakingapplication.data;

public class steps {
    int stepsId;
    String stepsShortDescription;
    String stepsDescription;
    String stepsVideoUrl;

    public steps (int myId, String myShortDescription, String myDescription, String myVideoUrl){
        stepsId = myId;
        stepsShortDescription = myShortDescription;
        stepsDescription = myDescription;
        stepsVideoUrl = myVideoUrl;
    }

}
