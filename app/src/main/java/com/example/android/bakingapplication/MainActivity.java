package com.example.android.bakingapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.bakingapplication.utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int SEARCH_LOADER = 125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_recipe_card);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        getSupportLoaderManager().initLoader(SEARCH_LOADER, null, this);
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {

        return new AsyncTaskLoader<String>(this) {
            String mRecipeJson;

            @Override
            public void onStartLoading() {
                if (mRecipeJson != null) {
                    deliverResult(mRecipeJson);
                } else {
                    forceLoad();
                }
            }

            @Nullable
            @Override
            public String loadInBackground() {
                URL recipeUrl = NetworkUtils.checkURL();
                String myString = "";
                try {
                    myString = NetworkUtils.getResponseFromHttpUrl(recipeUrl);


                } catch (IOException e) {
                    Log.e("Main Activity", "Problem making the HTTP request.", e);
                }

                return myString;
            }

            @Override
            public void deliverResult(String myRecipeJson) {
                mRecipeJson = myRecipeJson;
                super.deliverResult(myRecipeJson);
            }

        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}


