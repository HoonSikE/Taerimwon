package com.example.taerimwon.data.dto.user.black_list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BlackList (
    val tel: String = "",
) : Parcelable