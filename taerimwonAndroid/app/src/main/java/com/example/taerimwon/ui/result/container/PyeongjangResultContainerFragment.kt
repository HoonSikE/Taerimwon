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
import com.example.taerimwon.databinding.FragmentPyeongjangResultContainerBinding
import com.example.taerimwon.databinding.FragmentResultBinding
import com.example.taerimwon.databinding.FragmentResultContainerBinding
import com.example.taerimwon.di.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PyeongjangResultContainerFragment : BaseFragment<FragmentPyeongjangResultContainerBinding>(R.layout.fragment_pyeongjang_result_container) {
    override fun init() {
        initData()
    }
    private fun initData() {
        /** 평장 */
        var textResultTextPyeongjang = ""

        val selectedPyeongjangType = ApplicationClass.prefs.selectedPyeongjangType.toString()
        textResultTextPyeongjang += " - 평장 종류: " + selectedPyeongjangType
        if(selectedPyeongjangType == "선택안함") {
            binding.View1.visibility = View.GONE
            binding.layoutResultTextPyeongjang.visibility = View.GONE
        }else {
            textResultTextPyeongjang += "\n - 평장 명칭: " + ApplicationClass.prefs.selectedPyeongjangName

            /** 평장 추가 */
            val selectedPyeongjangType2 = ApplicationClass.prefs.selectedPyeongjangType2.toString()

            if(selectedPyeongjangType2 == "선택안함") {
            }else {
                textResultTextPyeongjang += "\n\n평장 추가 정보"
                textResultTextPyeongjang += "\n - 평장 종류: " + selectedPyeongjangType2
                textResultTextPyeongjang += "\n - 평장 명칭: " + ApplicationClass.prefs.selectedPyeongjangName2
            }
        }
        binding.textResultTextPyeongjang.text = textResultTextPyeongjang

    }
}