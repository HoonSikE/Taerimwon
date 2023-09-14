package com.example.taerimwon.data.repository

import androidx.fragment.app.FragmentActivity
import com.example.taerimwon.data.dto.user.User
import com.example.taerimwon.data.dto.user.black_list.BlackList
import com.example.taerimwon.utils.constant.UiState

interface AuthRepository {
    fun getBlackList(result: (UiState<List<BlackList>>) -> Unit)
    fun phoneAuth(tel: String, activity: FragmentActivity, result: (String) -> Unit)
    fun ckeckPhoneAuth(user: User, verificationId: String, code: String, result: (UiState<String>) -> Unit)
}