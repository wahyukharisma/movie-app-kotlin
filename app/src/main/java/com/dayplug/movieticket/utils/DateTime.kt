package com.dayplug.movieticket.utils
import java.text.SimpleDateFormat
import java.util.*

object DateTime {
    fun getNow() : String{
        val current = Calendar.getInstance().time
        return current.toString()
    }

}