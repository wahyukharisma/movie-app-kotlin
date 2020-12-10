package com.dayplug.movieticket.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dayplug.movieticket.service.model.User
import com.dayplug.movieticket.service.network.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SignUpRepository(val application: Application) {
     var user = MutableLiveData<User>()

    fun pushUser(data: User){
        Firebase.userDatabase.child(data.username!!).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val temp = snapshot.getValue(User::class.java)

                if(temp == null){
                    Firebase.userDatabase.child(data.username!!).setValue(data)
                    user.value = data
                }else{
                    Toast.makeText(application,"Username not available", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application,error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}