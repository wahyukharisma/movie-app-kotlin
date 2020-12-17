package com.dayplug.movieticket.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Order (
    val cinema: String? = "",
    val nama: String? = "",
    val waktu: String? = "",
    var desc: String ? = "",
    var genre: String ? = "",
    var poster: String ? = "",
    var rating: String ? = ""
): Parcelable