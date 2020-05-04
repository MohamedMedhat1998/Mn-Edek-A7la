package com.andalus.abomed7at55.mn_edek_a7la.ui.splash

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers.DatabaseReceiver
import com.andalus.abomed7at55.mn_edek_a7la.services.FileManagerService
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.CheckList
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.ACTION_DATABASE_SERVICE_COMPLETE
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_splash_screen.*

//private const val DELAY = 1500

class SplashScreenActivity : AppCompatActivity() {

    private val animationItem = "animation"
    private val dbReceiverItem = "dbReceiver"

    private val checkList = CheckList {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private val dbReceiver = DatabaseReceiver {
        checkList.check(dbReceiverItem)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_splash_screen)

        checkList.register(animationItem)
        checkList.register(dbReceiverItem)

        registerReceiver(dbReceiver, IntentFilter(ACTION_DATABASE_SERVICE_COMPLETE))

        Glide.with(this).load(R.drawable.logo).apply(RequestOptions.circleCropTransform()).into(ivLogo)
        startService(Intent(this, FileManagerService::class.java))

        loadAnimation()
    }

    private fun loadAnimation() {
        var shouldAnimateAppName = true
        val alphaAnimator = ValueAnimator.ofFloat(0f, 1f)
        alphaAnimator.addUpdateListener {
            ivLogo.alpha = it.animatedValue as Float
        }
        alphaAnimator.duration = 1000
        alphaAnimator.start()
        val moveUpAnimator = ValueAnimator.ofFloat(100f, 0f)
        moveUpAnimator.addUpdateListener {
            ivLogo.translationY = it.animatedValue as Float
            if (it.animatedValue as Float in 45f..55f && shouldAnimateAppName) {
                tvAppNameAnimation()
                shouldAnimateAppName = false
            }
        }
        Handler().postDelayed({
            if (!checkList.get(animationItem)) {
                checkList.check(animationItem)
            }
        }, 2000)
        moveUpAnimator.duration = 500
        moveUpAnimator.start()
    }

    private fun tvAppNameAnimation() {
        val alphaAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener {
                tvAppName.alpha = it.animatedValue as Float
            }
            duration = 550
            addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {

                }

                override fun onAnimationEnd(animation: Animator?) {
                    checkList.check(animationItem)
                }

                override fun onAnimationCancel(animation: Animator?) {

                }

                override fun onAnimationStart(animation: Animator?) {

                }

            })
        }
        alphaAnimator.start()
        val moveUpAnimator = ValueAnimator.ofFloat(50f, 0f)
        moveUpAnimator.addUpdateListener {
            tvAppName.translationY = it.animatedValue as Float
        }
        moveUpAnimator.duration = 550
        moveUpAnimator.start()
    }

    override fun onDestroy() {
        unregisterReceiver(dbReceiver)
        super.onDestroy()
    }
}