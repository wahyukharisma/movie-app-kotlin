package com.dayplug.movieticket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dayplug.movieticket.repository.DetailRepository
import com.dayplug.movieticket.service.model.Plays

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val play : LiveData<List<Plays>>
    private val _repository = DetailRepository(application)

    init {
        this.play = _repository.play
    }

    fun getPlays(title : String){
        _repository.getPlays(title)
    }

}