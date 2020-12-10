package com.dayplug.movieticket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dayplug.movieticket.databinding.ActivityPaymentSuccessBinding

class PaymentSuccessActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityPaymentSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPaymentSuccessBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        with(_binding){
            btnHome.setOnClickListener {
                finish()
                startActivity(Intent(this@PaymentSuccessActivity,HomeActivity::class.java))
            }
        }
    }
}