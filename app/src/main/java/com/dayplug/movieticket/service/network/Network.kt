package com.dayplug.movieticket.service.network

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

private val mDatabase = FirebaseDatabase.getInstance()

object Firebase{
    val userDatabase : DatabaseReference = mDatabase.getReference("User")
    val filmDatabase : DatabaseReference = mDatabase.getReference("Film")
    val mStorage : StorageReference = FirebaseStorage.getInstance().getReference("Image")
}