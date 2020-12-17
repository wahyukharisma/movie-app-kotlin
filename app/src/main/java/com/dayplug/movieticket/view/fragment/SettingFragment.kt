package com.dayplug.movieticket.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dayplug.movieticket.R
import com.dayplug.movieticket.databinding.FragmentSettingBinding
import com.dayplug.movieticket.utils.Preferences

class SettingFragment : Fragment() {

    private lateinit var _binding : FragmentSettingBinding
    private val binding get() = _binding
    private lateinit var _preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _preferences = Preferences(context!!)

        with(binding){
            tvName.text = _preferences.getValue("nama")
            tvEmail.text = _preferences.getValue("email")

            Glide.with(this@SettingFragment)
                .load(_preferences.getValue("url"))
                .apply(RequestOptions.circleCropTransform())
                .into(ivPhotoEmpty)
        }
    }
}