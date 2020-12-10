package com.dayplug.movieticket.view.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dayplug.movieticket.view.auth.SignInActivity
import com.dayplug.movieticket.databinding.ActivityOnBoardingTwoBinding

class OnBoardingTwoActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityOnBoardingTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnBoardingTwoBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        with(_binding){
            with(_binding){
                btnNext.setOnClickListener {
                    startActivity(Intent(applicationContext,OnBoardingThreeActivity::class.java))
                }

                btnSkip.setOnClickListener {
                    finishAffinity()
                    startActivity(Intent(applicationContext,SignInActivity::class.java))
                }
            }
        }
    }
}