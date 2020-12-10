package com.dayplug.movieticket.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dayplug.movieticket.view.auth.SignInActivity
import com.dayplug.movieticket.databinding.ActivityOnBoardingOneBinding
import com.dayplug.movieticket.utils.Preferences

class OnBoardingOneActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityOnBoardingOneBinding
    private lateinit var _preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingOneBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        _preferences = Preferences(this)

        if(_preferences.getValue("onboarding").equals("1")){
            finish()
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }

        _preferences.setValues("onboarding","1")

        with(_binding){
            btnNext.setOnClickListener {
                startActivity(Intent(applicationContext,OnBoardingTwoActivity::class.java))
            }

            btnSkip.setOnClickListener {
                finishAffinity()
                startActivity(Intent(applicationContext, SignInActivity::class.java))
            }
        }
    }
}