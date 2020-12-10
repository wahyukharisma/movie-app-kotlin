package com.dayplug.movieticket.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dayplug.movieticket.service.model.Plays
import com.dayplug.movieticket.service.network.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class DetailRepository(val application: Application) {
    val play = MutableLiveData<List<Plays>>()
    val data = ArrayList<Plays>()

    fun getPlays(title : String){
        Firebase.filmDatabase
                .child(title)
                .child("play")
                .addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        data.clear()

                        for(temp in snapshot.children){
                            var plays = temp.getValue(Plays::class.java)
                            data.add(plays!!)
                        }

                        play.value = data
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
                    }

                })
    }
}