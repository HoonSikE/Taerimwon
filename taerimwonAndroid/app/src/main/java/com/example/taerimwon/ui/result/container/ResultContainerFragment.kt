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
import com.example.taerimwon.databinding.FragmentResultBinding
import com.example.taerimwon.databinding.FragmentResultContainerBinding
import com.example.taerimwon.di.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultContainerFragment : BaseFragment<FragmentResultContainerBinding>(R.layout.fragment_result_container) {
    override fun init() {
        initData()
    }
    private fun initData() {
        var textResultTextLeader = " - 소속: " + ApplicationClass.prefs.leaderDepartment +
                                    "\n - 발주자명: " + ApplicationClass.prefs.leaderName +
                                    "\n - 전화번호: " + ApplicationClass.prefs.leaderTel

        binding.textResultTextLeader.text = textResultTextLeader

        if(ApplicationClass.prefs.clientName == "" && ApplicationClass.prefs.clientTel == "") {
            binding.View1.visibility = View.GONE
            binding.layoutResultTextClient.visibility = View.GONE
        }else{
            var textResultTextClient = " - 이름: " + ApplicationClass.prefs.clientName +
                    "\n - 전화번호: " + ApplicationClass.prefs.clientTel
            binding.textResultTextClient.text = textResultTextClient
        }

        var textResultTextLocation = ""

        val selectedLocation = ApplicationClass.prefs.selectedLocation.toString()
        if(selectedLocation.equals("화장장")){
            textResultTextLocation += " - 화장장소: " + ApplicationClass.prefs.cremationArea2 +
                    "\n - 화장시간: " + ApplicationClass.prefs.cremationTime
        } else if(selectedLocation.equals("장례식장")){
            textResultTextLocation += " - 장례식장 명: " + ApplicationClass.prefs.funeralName +
                    "\n - 호실: " + ApplicationClass.prefs.funeralNumber +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.funeralTime
        } else if(selectedLocation.equals("장지")){
            textResultTextLocation += " - 장지명: " + ApplicationClass.prefs.burialName +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.burialTime
        }
        binding.textResultTextLocation.text = textResultTextLocation
    }
}