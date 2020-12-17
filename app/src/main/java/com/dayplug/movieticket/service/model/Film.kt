package com.dayplug.movieticket.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (
    var desc: String ? = "",
    var director: String ? = "",
    var genre: String ? = "",
    var judul: String ? = "",
    var poster: String ? = "",
    var teaser: String ? = "",
    var url: String ? = "",
    var rating: String ? = "",
    var status: String ? = "",
) : Parcelable
