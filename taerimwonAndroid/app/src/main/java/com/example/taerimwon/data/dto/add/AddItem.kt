package com.example.taerimwon.data.dto.add

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddItem (
    val addItem: String = "",
    val flagImage: Int = 0,
) : Parcelable