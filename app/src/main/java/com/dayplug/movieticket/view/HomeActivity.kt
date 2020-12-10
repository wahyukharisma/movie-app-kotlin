package com.dayplug.movieticket.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.ActivityHomeBinding
import com.dayplug.movieticket.view.fragment.DashboardFragment
import com.dayplug.movieticket.view.fragment.TicketFragment
import com.dayplug.movieticket.view.fragment.UserFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityHomeBinding

    private val fragmentHome = DashboardFragment()
    private val fragmentTicket = TicketFragment()
    private val fragmentUser = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        _setFragment(fragmentHome)

        with(_binding){
            ivMenuDashboard.setOnClickListener {
                _setFragment(fragmentHome)
                _changeIcon(ivMenuDashboard, R.drawable.ic_home_active)
                _changeIcon(ivMenuTicket, R.drawable.ic_ticket_inactive)
                _changeIcon(ivMenuUser, R.drawable.ic_user_inactive)
            }

            ivMenuTicket.setOnClickListener {
                _setFragment(fragmentTicket)
                _changeIcon(ivMenuDashboard, R.drawable.ic_home_inactive)
                _changeIcon(ivMenuTicket, R.drawable.ic_ticket_active)
                _changeIcon(ivMenuUser, R.drawable.ic_user_inactive)
            }

            ivMenuUser.setOnClickListener {
                _setFragment(fragmentUser)
                _changeIcon(ivMenuDashboard, R.drawable.ic_home_inactive)
                _changeIcon(ivMenuTicket, R.drawable.ic_ticket_inactive)
                _changeIcon(ivMenuUser, R.drawable.ic_user_active)
            }
        }
    }

    private fun _setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_content, fragment)
        fragmentTransaction.commit()
    }

    private fun _changeIcon(imageView : ImageView, int : Int){
        imageView.setImageResource(int)
    }
}