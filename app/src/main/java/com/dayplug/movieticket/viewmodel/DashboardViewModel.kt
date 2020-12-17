package com.dayplug.movieticket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dayplug.movieticket.repository.DashboardRepository
import com.dayplug.movieticket.service.model.Film

class DashboardViewModel(application: Application) : AndroidViewModel(application) {
    private val _repository = DashboardRepository(application)
    val nowPlaying : LiveData<List<Film>>
    val comingSoon : LiveData<List<Film>>

    init {
        this.nowPlaying = _repository.nowPlaying
        this.comingSoon = _repository.comingSoon
    }

    fun getNowPlaying(){
        _repository.getNowPlaying()
    }

    fun getComingSoon(){
        _repository.getComingSoon()
    }
}