package com.example.taerimwon.ui.result

import android.content.ContentValues
import android.net.Uri
import android.provider.Telephony
import android.telephony.SmsManager
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController


@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
    }
    private fun setOnClickListeners() {
        binding.buttonResultToOrderFragment.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonMMS.setOnClickListener{
            // MMS 보내기
        }
    }
    private fun observer() {
    }
}