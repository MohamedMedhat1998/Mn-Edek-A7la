package com.andalus.abomed7at55.mn_edek_a7la.ui.splash

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers.DatabaseReceiver
import com.andalus.abomed7at55.mn_edek_a7la.services.FileManagerService
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.ACTION_DATABASE_SERVICE_COMPLETE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_splash_screen.*

//private const val DELAY = 1500

class SplashScreenActivity : AppCompatActivity() {


    private val dbReceiver = DatabaseReceiver {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)

        registerReceiver(dbReceiver, IntentFilter(ACTION_DATABASE_SERVICE_COMPLETE))


        Glide.with(this).load(R.drawable.logo).apply(RequestOptions.circleCropTransform()).into(ivLogo)
        /*val runnable = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        val handler = Handler()
        handler.postDelayed(runnable, DELAY.toLong())*/
        startService(Intent(this, FileManagerService::class.java))

    }

    override fun onDestroy() {
        unregisterReceiver(dbReceiver)
        super.onDestroy()
    }


}