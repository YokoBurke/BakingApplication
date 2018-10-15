package com.example.android.bakingapplication.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapplication.R;
import com.example.android.bakingapplication.data.ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {

    private static final String LOG_TAG = IngredientsAdapter.class.getSimpleName();
    private List<ingredients> myIngredients;
    private Context myContext;

    public IngredientsAdapter(Context myContextData, List<ingredients> myIngredientsData){
        myContext = myContextData;
        myIngredients = myIngredientsData;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView quantityTextView;
        public TextView measureTextView;
        public TextView ingredientTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            quantityTextView = (TextView) itemView.findViewById(R.id.text_quantity);
            measureTextView = (TextView) itemView.findViewById(R.id.text_measure);
            ingredientTextView = (TextView) itemView.findViewById(R.id.text_ingredient);
        }
    }


    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int myLayoutId = R.layout.ingredient_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        Log.i(LOG_TAG, "Adapter called.  OnCreateViewHolder");
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.MyViewHolder holder, int position) {
        int myQuantity = myIngredients.get(position).getQuanity();
        String myMeasure = myIngredients.get(position).getMeasure();
        String myIngredient = myIngredients.get(position).getIngredient();

        holder.quantityTextView.setText(Integer.toString(myQuantity));
        holder.measureTextView.setText(myMeasure);
        holder.ingredientTextView.setText(myIngredient);


    }

    @Override
    public int getItemCount() {
        if (myIngredients == null){
            return 0;
        }

        return myIngredients.size();
    }
}
