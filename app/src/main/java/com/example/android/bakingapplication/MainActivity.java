package com.example.android.bakingapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.bakingapplication.data.MiriamsList;
import com.example.android.bakingapplication.data.recipe;
import com.example.android.bakingapplication.utilities.NetworkUtils;
import com.example.android.bakingapplication.utilities.RecipeAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    final static String CLASS_NAME = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final int SEARCH_LOADER = 125;

    private List<recipe> myRecipeList;
    private RecipeAdapter mAdapter;

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_recipe_card);
        recyclerView.setHasFixedSize(true);

        isTablet = getResources().getBoolean(R.bool.is_tablet);

        if (isTablet) {
            mLayoutManager = new GridLayoutManager(this, 3);
        } else {
            mLayoutManager = new LinearLayoutManager(this);
        }


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
                Log.i(CLASS_NAME, recipeUrl.toString());
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

        myRecipeList = MiriamsList.parseJSON(data);
        mAdapter = new RecipeAdapter(this, (List<recipe>) myRecipeList, new RecipeAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

}


