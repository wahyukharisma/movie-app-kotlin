package com.dayplug.movieticket.utils

import java.text.NumberFormat
import java.util.*

object Currency{
    fun currency(price: Double) : String {
        val localId = Locale("in","ID")
        val format = NumberFormat.getCurrencyInstance(localId)
        return format.format(price)
    }
}
