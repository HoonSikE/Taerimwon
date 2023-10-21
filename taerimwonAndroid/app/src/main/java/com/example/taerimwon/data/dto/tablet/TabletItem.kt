package com.example.taerimwon.data.dto.tablet

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TabletItem (
    val tabletItem: String = "",
    val flagImage: Int = 0,
) : Parcelable