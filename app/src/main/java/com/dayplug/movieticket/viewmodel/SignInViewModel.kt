package com.dayplug.movieticket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dayplug.movieticket.repository.SignInRepository
import com.dayplug.movieticket.service.model.User

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val _repository = SignInRepository(application)

    val user : LiveData<User>

    init {
        this.user = _repository.user
    }

    fun pushLogin(username : String, password : String){
        _repository.pushLogin(username, password)
    }
}