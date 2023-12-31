package com.example.taerimwon.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("data") val data: T,
    @SerializedName("count") val count: Int,
    @SerializedName("msg") val msg: String
)
