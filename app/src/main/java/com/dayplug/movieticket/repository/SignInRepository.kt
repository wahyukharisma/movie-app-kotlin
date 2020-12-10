package com.dayplug.movieticket.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.dayplug.movieticket.service.model.User
import com.dayplug.movieticket.service.network.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class SignInRepository(val application: Application) {

    var user = MutableLiveData<User>()

    fun pushLogin(username : String, password : String){
        Firebase.userDatabase.child(username).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               val data = snapshot.getValue(User::class.java)

                if(data == null){
                    Toast.makeText(application,"User tidak ditemukan",Toast.LENGTH_LONG).show()
                }else{
                    user.value = data
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(application,error.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}