package com.dayplug.movieticket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.RowItemPlaysBinding
import com.dayplug.movieticket.service.model.Plays

class PlaysAdapter(private val context: Context, private val items: List<Plays>)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_plays, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvName.text = items[position].nama

            Glide.with(context)
                    .load(items[position].url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.ivPhoto)
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = RowItemPlaysBinding.bind(view)
    }

}