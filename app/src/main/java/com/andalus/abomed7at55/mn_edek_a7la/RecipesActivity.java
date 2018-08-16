package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Context;
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
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.Measurements;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {

    @BindView(R.id.rv_recipes)
    RecyclerView rvRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recipes);

        ButterKnife.bind(this);

        setUpRecyclerView();

        Toast.makeText(getBaseContext(),getIntent().getExtras().getString(FoodCategory.TAG_KEY),Toast.LENGTH_SHORT).show();
    }

    private void setUpRecyclerView(){
        String tag = getIntent().getExtras().getString(FoodCategory.TAG_KEY);
        AsyncList asyncList = new AsyncList();
        rvRecipes.setHasFixedSize(true);
        rvRecipes.setLayoutManager(new StaggeredGridLayoutManager(Measurements.numberOfGridLayoutColumns(this),LinearLayoutManager.VERTICAL));
        asyncList.execute(tag);
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
            mAdapter = new RecipesAdapter(recipes);
            rvRecipes.setAdapter(mAdapter);
        }
    }
}
