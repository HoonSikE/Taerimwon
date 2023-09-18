package com.example.taerimwon.ui.result

import android.text.Editable
import android.text.TextWatcher
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.databinding.FragmentUrnContainerBinding
import com.example.taerimwon.di.ApplicationClass


@AndroidEntryPoint
class UrnContainerFragment : BaseFragment<FragmentUrnContainerBinding>(R.layout.fragment_urn_container) {
    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
        binding.editTextName1.setText(ApplicationClass.prefs.name1)
        binding.editTextDate1.setText(ApplicationClass.prefs.date1)
        binding.editTextDate2.setText(ApplicationClass.prefs.date2)
    }
    private fun setOnClickListeners() {
    }
    private fun observer() {
        // 고인 정보
        binding.editTextName1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.name1 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        binding.editTextDate1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.date1 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
//        binding.spinnerDate1Type.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // 이전 텍스트 변경 이벤트
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // 텍스트 변경 이벤트
//                ApplicationClass.prefs.name1 = s?.toString() ?: ""
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                // 텍스트 변경 후 이벤트
//            }
//        })
        binding.editTextDate2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.date2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
//        binding.spinnerDate2Type.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // 이전 텍스트 변경 이벤트
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // 텍스트 변경 이벤트
//                ApplicationClass.prefs.name1 = s?.toString() ?: ""
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                // 텍스트 변경 후 이벤트
//            }
//        })
    }
}