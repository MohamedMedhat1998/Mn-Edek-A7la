package com.andalus.abomed7at55.mn_edek_a7la;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.iv_logo_splash_screen)
    ImageView ivLogoSplashScreen;

    private static final int DELAY = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.logo).apply(RequestOptions.circleCropTransform()).into(ivLogoSplashScreen);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this,CategoriesActivity.class));
                finish();
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable,DELAY);
    }
}
