package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.andalus.abomed7at55.mn_edek_a7la.Adapters.CategoriesAdapter;
import com.andalus.abomed7at55.mn_edek_a7la.Adapters.RecipesAdapter;
import com.andalus.abomed7at55.mn_edek_a7la.Data.AppDatabase;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnCategoryClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnRecipeClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FavoriteRecipe;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.Measurements;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCategoryClickListener, OnRecipeClickListener {

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @BindView(R.id.rv_search_result)
    RecyclerView rvSearchResult;

    @BindView(R.id.et_search_cat_act)
    EditText etSearchCatAct;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mContext = this;

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadCategories();
        setUpDatabase();
        setUpSearchProcess();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_favorite) {
            startRecipesActivityWithTag(FavoriteRecipe.FAVORITE_RECIPE_TAG);
        } else if (id == R.id.nav_recent) {
            startActivity(new Intent(this,RecentActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadCategories(){
        List<FoodCategory> foodCategories = new ArrayList<>();
        foodCategories.add(new FoodCategory(getString(R.string.meat),R.drawable.chicken_hot_icon,FoodCategory.MEAT_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.fish),R.drawable.fish_icon,FoodCategory.FISH_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.sweet),R.drawable.cake_icon,FoodCategory.SWEET_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.drink),R.drawable.juice_icon,FoodCategory.DRINK_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.starches),R.drawable.rice_icon,FoodCategory.STARCHES_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.appetizer),R.drawable.snack_icon,FoodCategory.APPETIZER_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.idea),R.drawable.ligh_bulb_icon,FoodCategory.IDEA_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.diet),R.drawable.diet_icon,FoodCategory.DIET_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.bakery),R.drawable.bread_icon,FoodCategory.BAKERY_TAG));
        foodCategories.add(new FoodCategory(getString(R.string.milk),R.drawable.milk_bottle_icon,FoodCategory.MILK_TAG));
        //TODO to add more categories, add a new FoodCategory object to the above list
        CategoriesAdapter adapter = new CategoriesAdapter(foodCategories,this);

        rvCategories.setAdapter(adapter);
        rvCategories.setLayoutManager(new GridLayoutManager(this, Measurements.numberOfGridLayoutColumns(this), LinearLayoutManager.VERTICAL,false));
    }

    @Override
    public void onCategoryClicked(String tag) {
        startRecipesActivityWithTag(tag);
    }

    private void setUpDatabase(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        AppDatabase.setCurrentVersion(sharedPreferences.getInt(AppDatabase.VERSION_KEY,1));
        try {
            AppDatabase.copyDatabase(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUpSearchProcess(){

        rvSearchResult.setLayoutManager(new StaggeredGridLayoutManager(Measurements.numberOfGridLayoutColumns(this),LinearLayoutManager.VERTICAL));

        etSearchCatAct.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    showView(rvCategories);
                    hideView(rvSearchResult);
                }else{
                    hideView(rvCategories);
                    showView(rvSearchResult);
                    AsyncList asyncList = new AsyncList();
                    asyncList.execute(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void hideView(View view){
        if(view.getVisibility() == View.VISIBLE){
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void showView(View view){
        if(view.getVisibility() == View.INVISIBLE){
            view.setVisibility(View.VISIBLE);
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

    private class AsyncList extends AsyncTask<String,Object,List<Recipe>>{

        private RecipesAdapter mAdapter;

        @Override
        protected List<Recipe> doInBackground(String... strings) {
            return AppDatabase.getInstance(getBaseContext()).getRecipeDao().getRecipesBySearchKeyword("%"+strings[0]+"%");
        }

        @Override
        protected void onPostExecute(List<Recipe> recipes) {
            super.onPostExecute(recipes);
            mAdapter = new RecipesAdapter(recipes, (OnRecipeClickListener) mContext);
            rvSearchResult.setAdapter(mAdapter);
        }
    }

    private void startRecipesActivityWithTag(String tag){
        Intent recipesActivityIntent = new Intent(this,RecipesActivity.class);
        recipesActivityIntent.putExtra(FoodCategory.TAG_KEY,tag);
        startActivity(recipesActivityIntent);
    }

}
