package com.dayplug.movieticket.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.ActivitySelectChairBinding
import com.dayplug.movieticket.service.model.Checkout
import com.dayplug.movieticket.service.model.Film

class SelectChairActivity : AppCompatActivity() {
    private lateinit var _binding: ActivitySelectChairBinding

    private var dataList = ArrayList<Checkout>()

    var total: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySelectChairBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Film>("data")

        with(_binding){
            tvTitleFilm.text = data!!.judul
            ivBack.setOnClickListener { finish() }

            ivChairA1.setOnClickListener { setStatus(ivChairA1,"A1") }
            ivChairA2.setOnClickListener { setStatus(ivChairA2,"A2") }
            ivChairA3.setOnClickListener { setStatus(ivChairA3,"A3") }
            ivChairA4.setOnClickListener { setStatus(ivChairA4,"A4") }

            ivChairB1.setOnClickListener { setStatus(ivChairB1,"B1") }
            ivChairB2.setOnClickListener { setStatus(ivChairB2,"B2") }
            ivChairB3.setOnClickListener { setStatus(ivChairB3,"B3") }
            ivChairB4.setOnClickListener { setStatus(ivChairB4,"B4") }

            ivChairC1.setOnClickListener { setStatus(ivChairC1,"C1") }
            ivChairC2.setOnClickListener { setStatus(ivChairC2,"C2") }
            ivChairC3.setOnClickListener { setStatus(ivChairC3,"C3") }
            ivChairC4.setOnClickListener { setStatus(ivChairC4,"C4") }

            ivChairD1.setOnClickListener { setStatus(ivChairD1,"D1") }
            ivChairD2.setOnClickListener { setStatus(ivChairD2,"D2") }
            ivChairD3.setOnClickListener { setStatus(ivChairD3,"D3") }
            ivChairD4.setOnClickListener { setStatus(ivChairD4,"D4") }

            btnNext.setOnClickListener {
                startActivity(Intent(this@SelectChairActivity, CheckoutActivity::class.java)
                        .putExtra("checkout", dataList)
                        .putExtra("data", data))
            }
        }
    }

    private fun removeChair(code: String) : Int{
        for((index,data) in this.dataList.withIndex()){
            if(data.kursi == code){
                return index
            }
        }
        return -1
    }

    private fun setStatus(view : ImageView, chair: String){
        if(view.drawable.constantState == resources.getDrawable(R.drawable.shape_rectangle_chair_selected, theme).constantState){
            view.setImageResource(R.drawable.shape_rectangle_chair_empty)
            this.total -= 1
            this.dataList.removeAt(removeChair(chair))
        }else{
            view.setImageResource(R.drawable.shape_rectangle_chair_selected)
            this.total += 1
            val data = Checkout(chair,"70000")
            this.dataList.add(data)
        }

        buyTicket(total)

    }

    private fun buyTicket(total: Int){
        if(total == 0){
            _binding.btnNext.setText(R.string.button_buy)
            _binding.btnNext.visibility = View.INVISIBLE
        }
        else{
            _binding.btnNext.text = getString(R.string.button_buy,total)
            _binding.btnNext.visibility = View.VISIBLE
        }
    }
}