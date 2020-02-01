package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Window;

import com.andalus.abomed7at55.mn_edek_a7la.Data.AppDatabase;
import com.andalus.abomed7at55.mn_edek_a7la.Data.FavoriteDatabase;
import com.andalus.abomed7at55.mn_edek_a7la.Interfaces.OnRecipeClickListener;
import com.andalus.abomed7at55.mn_edek_a7la.model.Category;
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe;

import java.util.List;


public class RecipesActivity extends AppCompatActivity implements OnRecipeClickListener{

    RecyclerView rvRecipes;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recipes);


        mContext = this;

        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        rvRecipes.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        Intent detailsIntent = new Intent(this,DetailsActivity.class);
        startActivity(detailsIntent);
    }

}
