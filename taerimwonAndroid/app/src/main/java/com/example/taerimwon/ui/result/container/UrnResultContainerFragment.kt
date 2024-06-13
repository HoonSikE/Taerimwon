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
import com.example.taerimwon.databinding.FragmentUrnResultContainerBinding
import com.example.taerimwon.di.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrnResultContainerFragment : BaseFragment<FragmentUrnResultContainerBinding>(R.layout.fragment_urn_result_container) {
    override fun init() {
        initData()
    }
    private fun initData() {
        var textResultTextUrn = ""
        val selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        textResultTextUrn += " - 함 명칭: " + ApplicationClass.prefs.selectedUrnName
        if(selectedUrnType == "선택안함"){
            binding.View1.visibility = View.GONE
            binding.layoutResultTextUrn.visibility = View.GONE
        }else {
            if(ApplicationClass.prefs.name1.toString() != "") {
                textResultTextUrn += "\n - 각인 종류: " + ApplicationClass.prefs.engraveType + "[" + ApplicationClass.prefs.engraveType2 + "]"
                textResultTextUrn += "\n - 고인명: " + ApplicationClass.prefs.name1
            }

            if(ApplicationClass.prefs.name1.toString() != "" && !ApplicationClass.prefs.selectedUrnName.toString().contains("목함")){
                textResultTextUrn += "\n - 생년월일: " + ApplicationClass.prefs.date1.toString().replace("-", ".") + " (${ApplicationClass.prefs.date1Type})" +
                        "\n - 사망월일: " + ApplicationClass.prefs.date2.toString().replace("-", ".") + " (${ApplicationClass.prefs.date2Type})"
            }

            // 직분, 세례명, 법명
            if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
                textResultTextUrn += "\n - 직분: " + ApplicationClass.prefs.name2
            }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
                textResultTextUrn += "\n - 법명: " + ApplicationClass.prefs.name2
            }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
                textResultTextUrn += "\n - 세례명: " + ApplicationClass.prefs.name2
            }

            // 추가
            val selectedUrnType2 = ApplicationClass.prefs.selectedUrnType2.toString()
            if(selectedUrnType2 != "선택안함"){
                textResultTextUrn += "\n\n - [추가] 함 명칭: " + ApplicationClass.prefs.selectedUrnName2


                if(ApplicationClass.prefs.boneName1.toString() != "") {
                    textResultTextUrn += "\n - 각인 종류: " + ApplicationClass.prefs.boneEngraveType + "[" + ApplicationClass.prefs.boneEngraveType2 + "]" +
                            "\n - 성별: " + ApplicationClass.prefs.boneSex +
                            "\n - 고인명: " + ApplicationClass.prefs.boneName1
                }

                if(ApplicationClass.prefs.boneName1.toString() != "" && !ApplicationClass.prefs.selectedUrnName2.toString().contains("목함")){
                    textResultTextUrn += "\n - 생년월일: " + ApplicationClass.prefs.boneDate1.toString().replace("-", ".") + " (${ApplicationClass.prefs.boneDate1Type})" +
                            "\n - 사망월일: " + ApplicationClass.prefs.boneDate2.toString().replace("-", ".") + " (${ApplicationClass.prefs.boneDate2Type})"
                }

                // 직분, 세례명, 법명
                if((ApplicationClass.prefs.boneEngraveType == "기독교" || ApplicationClass.prefs.boneEngraveType == "순복음") && (ApplicationClass.prefs.boneEngraveType2 == "기본")) {
                    textResultTextUrn += "\n - 직분: " + ApplicationClass.prefs.boneName2
                }else if(ApplicationClass.prefs.boneEngraveType == "불교" && ApplicationClass.prefs.boneEngraveType2 == "법명") {
                    textResultTextUrn += "\n - 법명: " + ApplicationClass.prefs.boneName2
                }else if(ApplicationClass.prefs.boneEngraveType == "천주교" && ApplicationClass.prefs.boneEngraveType2 == "기본") {
                    textResultTextUrn += "\n - 세례명: " + ApplicationClass.prefs.boneName2
                }
            }
            // 합골
            if(selectedUrnType.contains("합골")){
                textResultTextUrn += "\n\n - [합골] 각인 종류: " + ApplicationClass.prefs.boneEngraveType + "[" + ApplicationClass.prefs.boneEngraveType2 + "]" +
                        "\n - 성별: " + ApplicationClass.prefs.boneSex +
                        "\n - 고인명: " + ApplicationClass.prefs.boneName1 +
                        "\n - 생년월일: " + ApplicationClass.prefs.boneDate1.toString().replace("-", ".") + " (${ApplicationClass.prefs.boneDate1Type})" +
                        "\n - 사망월일: " + ApplicationClass.prefs.boneDate2.toString().replace("-", ".") + " (${ApplicationClass.prefs.boneDate2Type})"

                // 직분, 세례명, 법명
                if((ApplicationClass.prefs.boneEngraveType == "기독교" || ApplicationClass.prefs.boneEngraveType == "순복음") && (ApplicationClass.prefs.boneEngraveType2 == "기본")) {
                    textResultTextUrn += "\n - 직분: " + ApplicationClass.prefs.boneName2
                }else if(ApplicationClass.prefs.boneEngraveType == "불교" && ApplicationClass.prefs.boneEngraveType2 == "법명") {
                    textResultTextUrn += "\n - 법명: " + ApplicationClass.prefs.boneName2
                }else if(ApplicationClass.prefs.boneEngraveType == "천주교" && ApplicationClass.prefs.boneEngraveType2 == "기본") {
                    textResultTextUrn += "\n - 세례명: " + ApplicationClass.prefs.boneName2
                }
            }
        }
        binding.textResultTextUrn.text = textResultTextUrn
    }
}