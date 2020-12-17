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

    var filmNow = ArrayList<Film>()
    var filmSoon = ArrayList<Film>()

    fun getFilm(){
        Firebase.filmDatabase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                filmNow.clear()
                filmSoon.clear()

                for(data in snapshot.children){
                    val film = data.getValue(Film::class.java)

                    if(film!!.status == "1"){
                        filmNow.add(film)
                    }else{
                        filmSoon.add(film)
                    }
                }

                if(filmNow.size > 0)
                    nowPlaying.value = filmNow

                if(filmSoon.size > 0)
                    comingSoon.value = filmSoon
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }

        })
    }

}