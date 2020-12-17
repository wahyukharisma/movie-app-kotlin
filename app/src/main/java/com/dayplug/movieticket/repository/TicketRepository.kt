package com.dayplug.movieticket.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dayplug.movieticket.service.model.Order
import com.dayplug.movieticket.service.network.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TicketRepository(val application: Application){
    val listTicket = MutableLiveData<List<Order>>()
    var film = ArrayList<Order>()

    fun getListTicket(username: String){
        Firebase.orderDatabase.child(username).child("film").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                film.clear()

                for(data in snapshot.children){
                    val order = data.getValue(Order::class.java)
                    film.add(order!!)
                }

                listTicket.value = film
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}