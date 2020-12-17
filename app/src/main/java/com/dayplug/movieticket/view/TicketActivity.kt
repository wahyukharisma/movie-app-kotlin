package com.dayplug.movieticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dayplug.movieticket.databinding.ActivityTicketBinding
import com.dayplug.movieticket.service.model.Film
import com.dayplug.movieticket.service.model.Order
import com.dayplug.movieticket.utils.Preferences

class TicketActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTicketBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTicketBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Order>("data")

        with(_binding){
            ivBack.setOnClickListener { finish() }

            Glide.with(this@TicketActivity)
                .load(data!!.poster)
                .into(ivPoster)

            tvRate.text = data.rating
            tvTag.text = data.genre
            tvTitle.text = data.nama
        }
    }
}