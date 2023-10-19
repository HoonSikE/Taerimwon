package com.example.taerimwon.data.dto.urn

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrnItem (
    val urnItem: String = "",
    val flagImage: Int = 0,
) : Parcelable