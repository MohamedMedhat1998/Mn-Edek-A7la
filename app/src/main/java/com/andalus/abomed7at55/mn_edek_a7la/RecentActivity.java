package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.andalus.abomed7at55.mn_edek_a7la.Adapters.RecipesAdapter;
import com.andalus.abomed7at55.mn_edek_a7la.Data.AppDatabase;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnRecipeClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.JsonParser;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.Measurements;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecentActivity extends AppCompatActivity implements OnRecipeClickListener {

    private FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

    @BindView(R.id.rv_recipes_recent_activity)
    RecyclerView rvRecipesRecentActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        ButterKnife.bind(this);

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();

        mFirebaseRemoteConfig.setConfigSettings(configSettings);

        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        mFirebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RecentActivity.this, "Fetch Succeeded",
                                    Toast.LENGTH_SHORT).show();
                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Toast.makeText(RecentActivity.this, "Fetch Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //TODO do something when the data is returned
                        doWhenReceived();
                    }
                });
    }

    private void doWhenReceived(){
        String s = mFirebaseRemoteConfig.getString("test_key");
        Log.d("REMOTE CONFIG VALUE",s);

        rvRecipesRecentActivity.setLayoutManager(new StaggeredGridLayoutManager(Measurements.numberOfGridLayoutColumns(this), LinearLayoutManager.VERTICAL));
        try {
            List<Recipe> data = JsonParser.parseRecipes(s);
            Recipe[] recipes = data.toArray(new Recipe[data.size()]);
            new CacheRecipes(this).execute(recipes);
            RecipesAdapter adapter = new RecipesAdapter(data,this);
            rvRecipesRecentActivity.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRecipeClicked(Recipe recipe) {
        Intent detailsIntent = new Intent(this,DetailsActivity.class);
        detailsIntent.putExtra(Recipe.COLUMN_ID,recipe.getId());
        detailsIntent.putExtra(Recipe.COLUMN_TITLE,recipe.getTitle());
        detailsIntent.putExtra(Recipe.COLUMN_INGREDIENTS,recipe.getIngredients());
        detailsIntent.putExtra(Recipe.COLUMN_STEPS,recipe.getSteps());
        detailsIntent.putExtra(Recipe.COLUMN_CATEGORY,recipe.getCategory());
        detailsIntent.putExtra(Recipe.COLUMN_PHOTO_LINK,recipe.getPhotoLink());
        detailsIntent.putExtra(Recipe.COLUMN_VIDEO_LINK,recipe.getVideoLink());
        startActivity(detailsIntent);
    }

    private static class CacheRecipes extends AsyncTask<Recipe,Void,Void>{

        private Context mContext;

        CacheRecipes(Context context){
            mContext = context;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            for (Recipe recipe : recipes) {
                if(AppDatabase.getInstance(mContext).getRecipeDao().getRecipeById(recipe.getId()) == null){
                    AppDatabase.getInstance(mContext).getRecipeDao().insertRecipe(recipe);
                }
            }
            return null;
        }
    }
}
