package com.dayplug.movieticket.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dayplug.movieticket.databinding.ActivityDetailBinding
import com.dayplug.movieticket.service.model.Film
import com.dayplug.movieticket.view.adapter.PlaysAdapter
import com.dayplug.movieticket.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityDetailBinding
    private lateinit var _viewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Film>("data")
        _viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        with(_binding){
            tvTitleFilm.text = data!!.judul
            tvRating.text = data.rating
            tvTag.text = data.genre
            tv_contentStoryBoard.text = data.desc

            Glide.with(this@DetailActivity)
                    .load(data.poster)
                    .into(ivPoster)

            Glide.with(this@DetailActivity)
                    .load(data.teaser)
                    .into(ivHeader)


            ivBack.setOnClickListener { finish() }

            ivHeader.setOnClickListener { watchYoutube(data.url!!) }

            btnSelectChair.setOnClickListener {
                startActivity(Intent(this@DetailActivity, SelectChairActivity::class.java)
                        .putExtra("data", data))
            }

            rvWhoPlayed.layoutManager = LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)

            _viewModel.getPlays(data.judul.toString())

            _viewModel.play.observe(this@DetailActivity, Observer {
                rvWhoPlayed.adapter = PlaysAdapter(this@DetailActivity, it)
            })
        }
    }

    private fun watchYoutube(url: String){
        val apiIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        try{
            startActivity(apiIntent)
        }catch (ex: ActivityNotFoundException){}
    }
}