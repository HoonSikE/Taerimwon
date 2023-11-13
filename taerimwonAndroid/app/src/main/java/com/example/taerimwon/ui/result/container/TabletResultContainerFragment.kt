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
import com.example.taerimwon.databinding.FragmentTabletResultContainerBinding
import com.example.taerimwon.di.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabletResultContainerFragment : BaseFragment<FragmentTabletResultContainerBinding>(R.layout.fragment_tablet_result_container) {
    override fun init() {
        initData()
    }
    private fun initData() {
        /** 위패 */
        var textResultTextTablet = ""

        val selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        if(selectedTabletType == "선택안함") {
            binding.View1.visibility = View.GONE
            binding.layoutResultTextTablet.visibility = View.GONE
        }else {
            textResultTextTablet += " - 위패 상세 종류: " + ApplicationClass.prefs.tabletType +
                                    "\n - 위패 명칭: " + ApplicationClass.prefs.selectedTabletName

            if(!selectedTabletType.contains("사진")){
                textResultTextTablet += "\n - 위패 내용: " + ApplicationClass.prefs.name3
                if(!selectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.tabletType.toString().contains("기독교"))
                        textResultTextTablet += "\n - 직분: " + ApplicationClass.prefs.tabletName2
                    else if(ApplicationClass.prefs.tabletType.toString().contains("천주교"))
                        textResultTextTablet += "\n - 세례명: " + ApplicationClass.prefs.tabletName2
                }
            }
        }
        val boneSelectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
        if(boneSelectedTabletType != "선택안함") {
            textResultTextTablet += "\n\n - [추가] 위패 상세 종류: " + ApplicationClass.prefs.boneTabletType +
                                    "\n - 위패 명칭: " + ApplicationClass.prefs.selectedTabletName2

            if(!boneSelectedTabletType.contains("사진")){
                textResultTextTablet += "\n - 위패 내용: " + ApplicationClass.prefs.boneName3
                if(!boneSelectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.boneTabletType.toString().contains("기독교"))
                        textResultTextTablet += "\n - 직분: " + ApplicationClass.prefs.boneTabletName2
                    else if(ApplicationClass.prefs.boneTabletType.toString().contains("천주교"))
                        textResultTextTablet += "\n - 세례명: " + ApplicationClass.prefs.boneTabletName2
                }
            }
        }

        if(selectedTabletType.contains("합골")) {
            textResultTextTablet += "\n\n - [합골] 위패 상세 종류: " + ApplicationClass.prefs.boneTabletType

            if(!boneSelectedTabletType.contains("사진")){
                textResultTextTablet += "\n - 위패 내용: " + ApplicationClass.prefs.boneName3
                if(!boneSelectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.boneTabletType.toString().contains("기독교"))
                        textResultTextTablet += "\n - 직분: " + ApplicationClass.prefs.boneTabletName2
                    else if(ApplicationClass.prefs.boneTabletType.toString().contains("천주교"))
                        textResultTextTablet += "\n - 세례명: " + ApplicationClass.prefs.boneTabletName2
                }
            }
        }
        binding.textResultTextTablet.text = textResultTextTablet
    }
}