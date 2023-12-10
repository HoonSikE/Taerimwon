package com.example.taerimwon.ui.result.container

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentAddResultContainerBinding
import com.example.taerimwon.di.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddResultContainerFragment : BaseFragment<FragmentAddResultContainerBinding>(R.layout.fragment_add_result_container) {
    override fun init() {
        initData()
    }
    private fun initData() {
        /** 평장 */
        var textResultTextAdd = ""
        
        val selectedAddName1 = ApplicationClass.prefs.selectedAddName1
        val selectedAddName2 = ApplicationClass.prefs.selectedAddName2
        val selectedAddName3 = ApplicationClass.prefs.selectedAddName3
        val selectedAddName4 = ApplicationClass.prefs.selectedAddName4
        val selectedAddName5 = ApplicationClass.prefs.selectedAddName5

        if(selectedAddName1 != ""){
            textResultTextAdd += " - 추가 주문 1: " + selectedAddName1
        }
        if(selectedAddName2 != ""){
            textResultTextAdd += "\n - 추가 주문 2: " + selectedAddName2
        }
        if(selectedAddName3 != ""){
            textResultTextAdd += "\n - 추가 주문 3: " + selectedAddName3
        }
        if(selectedAddName4 != ""){
            textResultTextAdd += "\n - 추가 주문 4: " + selectedAddName4
        }
        if(selectedAddName5 != ""){
            textResultTextAdd += "\n - 추가 주문 5: " + selectedAddName5
        }
        binding.textResultTextAdd.text = textResultTextAdd
    }
}