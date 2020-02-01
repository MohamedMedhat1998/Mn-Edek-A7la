package com.andalus.abomed7at55.mn_edek_a7la.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_splash_screen.*

private const val DELAY = 1500

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)

        Glide.with(this).load(R.drawable.logo).apply(RequestOptions.circleCropTransform()).into(ivLogo)
        val runnable = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val handler = Handler()
        handler.postDelayed(runnable, DELAY.toLong())
    }

}