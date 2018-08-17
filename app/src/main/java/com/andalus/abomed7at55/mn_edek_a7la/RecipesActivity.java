package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Window;
import android.widget.Toast;

import com.andalus.abomed7at55.mn_edek_a7la.Adapters.RecipesAdapter;
import com.andalus.abomed7at55.mn_edek_a7la.Data.AppDatabase;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnRecipeClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.Measurements;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity implements OnRecipeClickListener{

    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recipes);

        ButterKnife.bind(this);

        mContext = this;

        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        String tag = getIntent().getExtras().getString(FoodCategory.TAG_KEY);
        AsyncList asyncList = new AsyncList();
        rvRecipes.setHasFixedSize(true);
        rvRecipes.setLayoutManager(new StaggeredGridLayoutManager(Measurements.numberOfGridLayoutColumns(this),LinearLayoutManager.VERTICAL));
        asyncList.execute(tag);
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

    private class AsyncList extends AsyncTask<String,Object,List<Recipe>>{

        private RecipesAdapter mAdapter;

        @Override
        protected List<Recipe> doInBackground(String... strings) {
            return AppDatabase.getInstance(getBaseContext()).getRecipeDao().getRecipesByCategory("%"+strings[0]+"%");
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            mAdapter = new RecipesAdapter(recipes, (OnRecipeClickListener) mContext);
            rvRecipes.setAdapter(mAdapter);
        }
    }
}
