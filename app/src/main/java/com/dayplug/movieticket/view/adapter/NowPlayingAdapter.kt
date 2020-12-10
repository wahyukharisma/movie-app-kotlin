package com.dayplug.movieticket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.RowItemNowPlayingBinding
import com.dayplug.movieticket.service.model.Film

class NowPlayingAdapter(private val context: Context, private val items: List<Film>)
    : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_now_playing, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvTitle.text = items[position].judul
            binding.tvRate.text = items[position].rating
            binding.tvTag.text = items[position].genre

            Glide.with(context)
                .load(items[position].poster)
                .into(binding.ivPoster)
        }
    }

    override fun getItemCount() : Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = RowItemNowPlayingBinding.bind(view)
    }

}