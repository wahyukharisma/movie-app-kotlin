package com.dayplug.movieticket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.RowItemCheckoutBinding
import com.dayplug.movieticket.service.model.Checkout
import com.dayplug.movieticket.utils.Currency

class SeatCheckoutAdapter(private val context: Context, private val items: List<Checkout>) : RecyclerView.Adapter<SeatCheckoutAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            SeatCheckoutAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_item_checkout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.tvPrice.text = Currency.currency(items[position].harga!!.toDouble())
            binding.tvSeat.text = items[position].kursi
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = RowItemCheckoutBinding.bind(view)
    }
}