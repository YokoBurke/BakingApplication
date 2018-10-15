package com.example.android.bakingapplication;

import android.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.bakingapplication.data.ingredients;
import com.example.android.bakingapplication.data.steps;
import com.example.android.bakingapplication.utilities.IngredientsAdapter;
import com.example.android.bakingapplication.utilities.NetworkUtils;
import com.example.android.bakingapplication.utilities.StepsAdapter;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity{


    final static String CLASS_NAME = DetailActivity.class.getSimpleName();

    private ArrayList<ingredients> myIngredients;
    private ArrayList<steps> mySteps;

    private RecyclerView stepsRecyclerView;
    private RecyclerView ingredientsRecyclerView;

    private IngredientsAdapter mIngredientsAdapter;
    private StepsAdapter mStepsAdapter;

    private RecyclerView.LayoutManager stepsLayoutManager;
    private RecyclerView.LayoutManager ingredientsLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsRecyclerView = (RecyclerView) findViewById(R.id.recycle_ingredients);
        ingredientsRecyclerView.setHasFixedSize(true);

        stepsRecyclerView = (RecyclerView) findViewById(R.id.recycle_steps);
        stepsRecyclerView.setHasFixedSize(true);



        stepsLayoutManager = new LinearLayoutManager(this);
        ingredientsLayoutManager = new LinearLayoutManager(this);

        mIngredientsAdapter = new IngredientsAdapter(this, myIngredients);
        mStepsAdapter = new StepsAdapter(this, mySteps, new StepsAdapter.ListItemClickListner() {
            @Override
            public void onListItemClick(int clickedItemIndex) {

            }
        });


        ingredientsRecyclerView.setAdapter(mIngredientsAdapter);
        stepsRecyclerView.setAdapter(mStepsAdapter);

        Log.i(CLASS_NAME, "Adapter Set");


    }
}
