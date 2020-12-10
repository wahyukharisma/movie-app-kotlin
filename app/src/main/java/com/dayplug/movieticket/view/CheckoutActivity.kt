package com.dayplug.movieticket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.ActivityCheckoutBinding
import com.dayplug.movieticket.service.model.Checkout
import com.dayplug.movieticket.service.model.Film
import com.dayplug.movieticket.utils.Currency
import com.dayplug.movieticket.view.adapter.SeatCheckoutAdapter
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityCheckoutBinding
    private  var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCheckoutBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        dataList = intent.getSerializableExtra("checkout") as ArrayList<Checkout>
        val data = intent.getParcelableExtra<Film>("data")

        iv_back.setOnClickListener { finish() }
        btn_cancel.setOnClickListener { finish() }
        btn_pay.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, PaymentSuccessActivity::class.java))
        }

        with(_binding){
            tv_valueTotal.text = Currency.currency(getTotal().toDouble())
            tv_titleFilm.text = data!!.judul

            rvItems.adapter = SeatCheckoutAdapter(this@CheckoutActivity, dataList)
        }
    }

    private fun getTotal() : Int {
        var total: Int = 0
        for(temp in dataList){
            total += temp.harga!!.toInt()
        }

        return total
    }
}