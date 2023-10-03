package com.example.taerimwon.ui.order

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
class OrderViewModel @Inject constructor(
): ViewModel(){
    private val _engraveTypePosition = MutableLiveData<Int>()
    val engraveTypePosition: LiveData<Int>
        get() = _engraveTypePosition


    fun engraveTypePosition(engraveTypePosition : Int) {
        _engraveTypePosition.value = engraveTypePosition
    }
}