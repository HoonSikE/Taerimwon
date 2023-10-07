package com.example.taerimwon.ui.result

import android.os.Bundle
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
                                    "\n - 발주자 전화번호: " + ApplicationClass.prefs.leaderTel

        binding.textResultTextLeader.text = textResultTextLeader

        var textResultTextClient = "상주명: " + ApplicationClass.prefs.clientName +
                                    "\n상주 전화번호: " + ApplicationClass.prefs.clientTel
        binding.textResultTextClient.text = textResultTextClient

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

        var textResultTextUrn = ""

        val selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        textResultTextUrn += "유골함 종류: " + selectedUrnType
        if(selectedUrnType != "선택안함"){
            textResultTextUrn += "\n\n각인 종류: " + ApplicationClass.prefs.engraveType + "[" + ApplicationClass.prefs.engraveType2 + "]"

            textResultTextUrn += "\n\n고인 정보" +
                    "\n - 고인명: " + ApplicationClass.prefs.name1 +
                    "\n - 생년월일: " + ApplicationClass.prefs.date1.toString().replace("-", ".") + " (${ApplicationClass.prefs.date1Type})" +
                    "\n - 사망월일: " + ApplicationClass.prefs.date2.toString().replace("-", ".") + " (${ApplicationClass.prefs.date2Type})"

            // 직분, 세례명, 법명
            if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
                textResultTextUrn += "\n - 직분: " + ApplicationClass.prefs.name2
            }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
                textResultTextUrn += "\n - 법명: " + ApplicationClass.prefs.name2
            }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
                textResultTextUrn += "\n - 세례명: " + ApplicationClass.prefs.name2
            }

            // 합골
            if(selectedUrnType.contains("합골")){
                textResultTextUrn += "\n\n합골 추가 정보" +
                        "\n - 각인 종류: " + ApplicationClass.prefs.boneEngraveType + "[" + ApplicationClass.prefs.boneEngraveType2 + "]" +
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

        var textResultTextTablet = ""

        val selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        textResultTextTablet += "위패 종류: " + selectedTabletType
        if(selectedTabletType != "선택안함") {
            textResultTextTablet += "\n\n위패 상세 종류: " + ApplicationClass.prefs.tabletType

            if(!selectedTabletType.contains("사진")){
                textResultTextTablet += "\n - 위패 내용: " + ApplicationClass.prefs.name3
                if(!selectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.tabletType.toString().contains("기독교"))
                        textResultTextTablet += "\n직분: " + ApplicationClass.prefs.tabletName2
                    else if(ApplicationClass.prefs.tabletType.toString().contains("천주교"))
                        textResultTextTablet += "\n세례명: " + ApplicationClass.prefs.tabletName2
                }
            }
        }
        val boneSelectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
        if(boneSelectedTabletType != "선택안함") {
            textResultTextTablet += "\n\n합골 위패 종류: " + ApplicationClass.prefs.boneSelectedTabletType
            textResultTextTablet += "\n\n위패 상세 종류: " + ApplicationClass.prefs.boneTabletType

            if(!boneSelectedTabletType.contains("사진")){
                textResultTextTablet += "\n - 위패 내용: " + ApplicationClass.prefs.boneName3
                if(!boneSelectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.boneTabletType.toString().contains("기독교"))
                        textResultTextTablet += "\n직분: " + ApplicationClass.prefs.boneTabletName2
                    else if(ApplicationClass.prefs.boneTabletType.toString().contains("천주교"))
                        textResultTextTablet += "\n세례명: " + ApplicationClass.prefs.boneTabletName2
                }
            }
        }
        binding.textResultTextTablet.text = textResultTextTablet

        var textResultTextNote = ApplicationClass.prefs.note
        if(ApplicationClass.prefs.note == "")
            textResultTextNote = "없음"
        binding.textResultTextNote.text = textResultTextNote
    }
}