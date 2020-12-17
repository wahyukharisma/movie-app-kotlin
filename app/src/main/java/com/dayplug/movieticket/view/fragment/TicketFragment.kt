package com.dayplug.movieticket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.FragmentTicketBinding
import com.dayplug.movieticket.utils.Preferences
import com.dayplug.movieticket.view.adapter.TicketAdapter
import com.dayplug.movieticket.viewmodel.TicketViewModel

class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!
    private lateinit var _viewModel : TicketViewModel
    private lateinit var  preferences: Preferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewModel = ViewModelProvider(this).get(TicketViewModel::class.java)
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)

        with(binding){
            _viewModel.getListTicket(preferences.getValue("nama").toString())
            _viewModel.listTicket.observe(viewLifecycleOwner, Observer {
                rvTicketMovie.adapter = TicketAdapter(context!!,it)
                tvMovieTicket.text = getString(R.string.count_movies, it.size)
            })
        }

    }


}