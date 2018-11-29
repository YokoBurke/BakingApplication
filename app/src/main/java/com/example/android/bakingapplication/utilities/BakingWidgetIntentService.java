package com.example.android.bakingapplication.utilities;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

public class BakingWidgetIntentService extends IntentService {

    public static final String ACTION_UPDATE_INGREDIENTS = "com.example.android.bakingapplication.utilities.action.update_ingredients";

    public BakingWidgetIntentService(){
        super("BakingWidgetIntentService");
    }

    public static void setActionUpdateIngredients(Context context) {
        Intent intent = new Intent(context, BakingWidgetIntentService.class);
        intent.setAction(ACTION_UPDATE_INGREDIENTS);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_INGREDIENTS.equals(action)) {
                handleActionUpdateIngredients();
            }
        }

    }

    private void handleActionUpdateIngredients() {
    }

}
