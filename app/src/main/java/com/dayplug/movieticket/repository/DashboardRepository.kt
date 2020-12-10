package com.dayplug.movieticket.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dayplug.movieticket.service.model.Film
import com.dayplug.movieticket.service.network.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class DashboardRepository(val application: Application) {
    var nowPlaying = MutableLiveData<List<Film>>()
    var comingSoon = MutableLiveData<List<Film>>()

    var films = ArrayList<Film>()

    fun getNowPlaying(){
        Firebase.filmDatabase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                films.clear()
                for(data in snapshot.children){
                    val film = data.getValue(Film::class.java)
                    films.add(film!!)
                }

                nowPlaying.value = films
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getComingSoon(){
        Firebase.filmDatabase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                films.clear()
                for(data in snapshot.children){
                    val film = data.getValue(Film::class.java)
                    films.add(film!!)
                }

                comingSoon.value = films
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }
}