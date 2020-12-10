package com.dayplug.movieticket.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dayplug.movieticket.view.auth.SignInActivity
import com.dayplug.movieticket.databinding.ActivityOnBoardingThreeBinding

class OnBoardingThreeActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityOnBoardingThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingThreeBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        with(_binding){
            btnSkip.setOnClickListener {
                finishAffinity()
                startActivity(Intent(applicationContext,SignInActivity::class.java))
            }
        }
    }
}