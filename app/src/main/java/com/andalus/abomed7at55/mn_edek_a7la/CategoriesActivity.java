package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.andalus.abomed7at55.mn_edek_a7la.Adapters.CategoriesAdapter;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnCategoryClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.Objects.FoodCategory;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.Measurements;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnCategoryClickListener {

    private static final int SPAN_COUNT = 2;

    @BindView(R.id.rv_categories)
    RecyclerView rvCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadCategories();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.categories, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

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
        Intent recipesActivityIntent = new Intent(CategoriesActivity.this,RecipesActivity.class);
        recipesActivityIntent.putExtra(FoodCategory.TAG_KEY,tag);
        startActivity(recipesActivityIntent);
    }
}
