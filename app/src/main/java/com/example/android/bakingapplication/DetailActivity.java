package com.example.android.bakingapplication;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakingapplication.data.ingredients;
import com.example.android.bakingapplication.data.steps;
import com.example.android.bakingapplication.utilities.IngredientsAdapter;
import com.example.android.bakingapplication.utilities.StepsAdapter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private ArrayList<ingredients> myIngredients;
    private ArrayList<steps> mySteps;

    private RecyclerView stepsRecyclerView;
    private RecyclerView ingredientsRecyclerView;

    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
