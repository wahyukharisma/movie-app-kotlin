package com.dayplug.movieticket.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plays(
    var nama: String ? = "",
    val url: String ? = ""
) : Parcelable