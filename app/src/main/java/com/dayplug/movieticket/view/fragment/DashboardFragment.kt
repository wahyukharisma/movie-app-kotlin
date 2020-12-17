package com.dayplug.movieticket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dayplug.movieticket.databinding.FragmentDashboardBinding
import com.dayplug.movieticket.utils.Currency
import com.dayplug.movieticket.utils.Preferences
import com.dayplug.movieticket.view.adapter.ComingSoonAdapter
import com.dayplug.movieticket.view.adapter.NowPlayingAdapter
import com.dayplug.movieticket.view.adapter.SpacesItemDecoration
import com.dayplug.movieticket.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var  preferences: Preferences
    private lateinit var _viewModel : DashboardViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)

        with(binding){
            tvName.text = preferences.getValue("nama")

            val wallet = preferences.getValue("saldo")
            val photo = preferences.getValue("url")

            if(!photo.equals("")){
                Glide.with(this@DashboardFragment)
                    .load(photo)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivPhotoEmpty)
            }

            if(!wallet.equals("")){
                tvWallet.text = Currency.currency(wallet!!.toDouble())
            }

            rvNowPlaying.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


            rvComingSoon.layoutManager = GridLayoutManager(context,3)
            rvComingSoon.addItemDecoration(SpacesItemDecoration(24))

            _viewModel.getNowPlaying()
            _viewModel.getComingSoon()

            _viewModel.nowPlaying.observe(viewLifecycleOwner,  {
                rvNowPlaying.adapter = NowPlayingAdapter(context!!,it)
            })

            _viewModel.comingSoon.observe(viewLifecycleOwner, {
                rvComingSoon.adapter = ComingSoonAdapter(context!!, it)
            })

        }
    }

}