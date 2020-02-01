package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class DetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView tvIngredientsDetails;
    TextView tvStepsDetails;
    ImageView ivDetailsActivityImage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Button btnOpenInYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        setSupportActionBar(toolbar);

    }


}
