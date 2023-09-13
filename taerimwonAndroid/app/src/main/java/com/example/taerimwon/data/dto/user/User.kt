package com.example.taerimwon.data.dto.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val uid: String = "",
    val userTel: String = "",
    val currentDate: String = "",
    @field:JvmField
    val authenticated: Boolean = false,
) : Parcelable