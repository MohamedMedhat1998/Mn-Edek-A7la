package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class RecipesActivity extends AppCompatActivity {

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

    private void setUpRecyclerView() {
        rvRecipes.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

}
