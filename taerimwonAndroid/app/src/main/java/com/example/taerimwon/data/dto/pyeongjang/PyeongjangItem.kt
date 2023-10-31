package com.example.taerimwon.data.dto.pyeongjang

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PyeongjangItem (
    val pyeongjangItem: String = "",
    val flagImage: Int = 0,
) : Parcelable