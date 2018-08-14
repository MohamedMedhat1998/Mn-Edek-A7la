package com.andalus.abomed7at55.mn_edek_a7la;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.Toast;

import com.andalus.abomed7at55.mn_edek_a7la.Adapters.RecipesAdapter;
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
        rvRecipes.setLayoutManager(new GridLayoutManager(this, Measurements.numberOfGridLayoutColumns(this), LinearLayoutManager.VERTICAL,false));
        RecipesAdapter adapter;
        List<Recipe> list = null;
        String tag = getIntent().getExtras().getString(FoodCategory.TAG_KEY);
        switch (tag){
            //TODO initialize the list in each case
            case FoodCategory.MEAT_TAG:
                break;
            case FoodCategory.FISH_TAG:
                break;
            case FoodCategory.SWEET_TAG:
                break;
            case FoodCategory.DRINK_TAG:
                break;
            case FoodCategory.STARCHES_TAG:
                break;
            case FoodCategory.APPETIZER_TAG:
                break;
            case FoodCategory.IDEA_TAG:
                break;
            case FoodCategory.DIET_TAG:
                break;
            case FoodCategory.BAKERY_TAG:
                break;
            case FoodCategory.MILK_TAG:
                break;
            //TODO if you added a category, add its list here
        }
        adapter = new RecipesAdapter(list);
        rvRecipes.setAdapter(adapter);
    }
}
