package com.dayplug.movieticket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dayplug.movieticket.repository.TicketRepository
import com.dayplug.movieticket.service.model.Order

class TicketViewModel(application: Application): AndroidViewModel(application)  {
    val listTicket: LiveData<List<Order>>
    private val _repository = TicketRepository(application)

    init {
        this.listTicket = _repository.listTicket
    }

    fun getListTicket(username: String){
        _repository.getListTicket(username)
    }
}