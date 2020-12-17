package com.dayplug.movieticket.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.RowItemMovieTicketBinding
import com.dayplug.movieticket.service.model.Film
import com.dayplug.movieticket.service.model.Order
import com.dayplug.movieticket.view.TicketActivity

class TicketAdapter(private val context: Context,private val items: List<Order>)
    : RecyclerView.Adapter<TicketAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    = ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_movie_ticket, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvTitle.text = items[position].nama
            binding.tvRate.text = items[position].rating
            binding.tvTag.text = items[position].genre
            binding.tvDesc.text = items[position].desc

            Glide.with(context)
                    .load(items[position].poster)
                    .into(binding.ivPoster)

            binding.ivPoster.setOnClickListener {
                context.startActivity(Intent(context, TicketActivity::class.java)
                    .putExtra("data", items[position]))
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = RowItemMovieTicketBinding.bind(view)
    }

}