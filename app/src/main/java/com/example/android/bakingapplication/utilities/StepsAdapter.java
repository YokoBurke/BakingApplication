package com.example.android.bakingapplication.utilities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapplication.R;
import com.example.android.bakingapplication.data.steps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.MyStepsViewHolder> {

    private static String LOG_TAG = StepsAdapter.class.getSimpleName();
    private List<steps> myStepsData;
    private Context myContext;
    final private ListItemClickListner mOnClickListener;

    public interface ListItemClickListner{
        void onListItemClick(int clickedItemIndex);
    }

    public StepsAdapter(Context context, List<steps> StepsData, ListItemClickListner listener){
        myStepsData = StepsData;
        myContext = context;
        mOnClickListener = listener;

    }

    class MyStepsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.short_desc_steps) TextView shortDescTextView;

        public MyStepsViewHolder(View itemView) {
            super(itemView);
            // ButterKnife.bind(this, itemView);
            ButterKnife.bind((Activity) myContext);

        }
    }

    @NonNull
    @Override
    public StepsAdapter.MyStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int myLayoutId = R.layout.ingredient_item;
        View itemView = LayoutInflater.from(parent.getContext()).inflate(myLayoutId, parent, false);
        MyStepsViewHolder myStepsViewHolder = new MyStepsViewHolder(itemView);
        return myStepsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.MyStepsViewHolder holder, int position) {

        String mySteps = myStepsData.get(position).getShortDescription();
        holder.shortDescTextView.setText(mySteps);


    }

    @Override
    public int getItemCount() {
        if (myStepsData == null){
            return 0;
        }
        return myStepsData.size();
    }
}
