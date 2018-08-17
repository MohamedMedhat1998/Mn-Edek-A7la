package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;
import com.andalus.abomed7at55.mn_edek_a7la.Utils.ImageUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_ingredients_details)
    TextView tvIngredientsDetails;
    @BindView(R.id.tv_steps_details)
    TextView tvStepsDetails;
    @BindView(R.id.iv_details_activity_image)
    ImageView ivDetailsActivityImage;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        receiveAndPopulate();
    }

    private void receiveAndPopulate(){
        Intent dataIntent = getIntent();
        Bundle receivedBundle = dataIntent.getExtras();
        collapsingToolbarLayout.setTitle("  " + receivedBundle.getString(Recipe.COLUMN_TITLE));
        Glide.with(this)
                .applyDefaultRequestOptions(new RequestOptions().placeholder(ImageUtils.getPlaceHolderId(receivedBundle.getString(Recipe.COLUMN_CATEGORY))))
                .load(receivedBundle.getString(Recipe.COLUMN_PHOTO_LINK))
                .into(ivDetailsActivityImage);
        tvIngredientsDetails.setText(receivedBundle.getString(Recipe.COLUMN_INGREDIENTS));
        tvStepsDetails.setText(receivedBundle.getString(Recipe.COLUMN_STEPS));
    }
}
