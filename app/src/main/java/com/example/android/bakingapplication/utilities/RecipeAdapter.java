package com.example.android.bakingapplication.utilities;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapplication.DetailActivity;
import com.example.android.bakingapplication.R;
import com.example.android.bakingapplication.data.recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private static final String LOG_TAG = RecipeAdapter.class.getSimpleName();

    private List<recipe> myRecipe;
    private Context myContext;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RecipeAdapter (Context mContext, List<recipe> mRecipe, ListItemClickListener mListener){
        myRecipe = mRecipe;
        myContext = mContext;
        mOnClickListener = mListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView recipeNameTextView;
        public TextView servingsTextView;

        public MyViewHolder(View itemView){
            super(itemView);
            recipeNameTextView = (TextView) itemView.findViewById(R.id.recipe_name);
            servingsTextView = (TextView) itemView.findViewById(R.id.recipe_serving_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
            // add intent here

            Intent intent = new Intent(myContext, DetailActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, myRecipe.get(clickedPosition));

            Log.i(LOG_TAG, "Name is " + myRecipe.get(clickedPosition).getName());
            myContext.startActivity(intent);


        }
    }

    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        int myLayoutId = R.layout.main_card_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyViewHolder recipeViewHolder = new MyViewHolder(itemView);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder holder, int position) {

        String myRecipeName = myRecipe.get(position).getName();
        String myServingNumber = Integer.toString(myRecipe.get(position).getServings());

        holder.recipeNameTextView.setText(myRecipeName);
        holder.servingsTextView.setText(myServingNumber);
    }

    @Override
    public int getItemCount() {
        if (myRecipe == null) {
            return 0;
        }
        return myRecipe.size();
    }

}
