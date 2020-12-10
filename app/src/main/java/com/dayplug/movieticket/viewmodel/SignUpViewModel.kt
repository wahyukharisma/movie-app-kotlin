package com.dayplug.movieticket.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dayplug.movieticket.repository.SignUpRepository
import com.dayplug.movieticket.service.model.User

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val _repository = SignUpRepository(application)

    val user: LiveData<User>

    init {
        this.user = _repository.user
    }

    fun pushUser(username : String, password: String, name: String, email: String){
        val data = User()
        data.username = username
        data.email = email
        data.nama = name
        data.password = password
        _repository.pushUser(data)
    }
}