package com.dayplug.movieticket.service.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet (
    val deskripsi : String? = "",
    val sebelum : String? = "",
    val sesudah: String? = "",
    val status: String? = "",
    val waktu: String? = ""
) : Parcelable