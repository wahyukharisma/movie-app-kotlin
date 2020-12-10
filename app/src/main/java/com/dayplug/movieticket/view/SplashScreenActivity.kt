package com.dayplug.movieticket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.dayplug.movieticket.R
import com.dayplug.movieticket.view.onboarding.OnBoardingOneActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val r = Runnable {
            startActivity(Intent(this,OnBoardingOneActivity::class.java))
            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed(r,2000)
    }
}