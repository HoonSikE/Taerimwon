package com.example.taerimwon.ui.home

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taerimwon.data.dto.user.User
import com.example.taerimwon.data.dto.user.black_list.BlackList
import com.example.taerimwon.data.repository.AuthRepository
import com.example.taerimwon.utils.constant.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
): ViewModel(){
    private val _getBlackList = MutableLiveData<UiState<List<BlackList>>>()
    val getBlackList: LiveData<UiState<List<BlackList>>>
        get() = _getBlackList

    private val _phoneAuth = MutableLiveData<String>()
    val phoneAuth: LiveData<String>
        get() = _phoneAuth

    private val _ckeckPhoneAuth = MutableLiveData<UiState<String>>()
    val ckeckPhoneAuth: LiveData<UiState<String>>
        get() = _ckeckPhoneAuth

    fun getBlackList(){
        repository.getBlackList(
        ){
            _getBlackList.value = it
        }
    }

    fun phoneAuth(tel: String, activity: FragmentActivity){
        repository.phoneAuth(
            tel = tel,
            activity = activity
        ){
            _phoneAuth.value = it
        }
    }

    fun ckeckPhoneAuth(user: User, verificationId: String, code: String){
        repository.ckeckPhoneAuth(
            user = user,
            verificationId = verificationId,
            code = code
        ){
            _ckeckPhoneAuth.value = it
        }
    }
}