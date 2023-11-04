package com.example.taerimwon.ui.result.framelayout

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.databinding.FragmentResultBoneTabletBinding
import com.example.taerimwon.databinding.FragmentResultTabletBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultBoneTabletFragment : BaseFragment<FragmentResultBoneTabletBinding>(R.layout.fragment_result_bone_tablet) {
    private lateinit var selectedTabletType: String
    private lateinit var selectedTabletName: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        selectedTabletName = ApplicationClass.prefs.selectedTabletName.toString()

        if(selectedTabletType != "선택안함") {
            if(selectedTabletType.contains("사진")){
                binding.layoutResultContent.visibility = View.GONE
            }else if(ApplicationClass.prefs.tabletType.toString().contains("문구")){
                binding.layoutResultContent.visibility = View.GONE
            }else if(selectedTabletType.contains("합골")){
//                setTabletData()
                setTablet2Data()
            }
        }else {
            binding.layoutResultContent.visibility = View.GONE
        }
    }
    private fun setTable2Mark() {
        val layoutBoneTabletMark0 = binding.layoutBoneTabletMark0
        val layoutBoneTabletResult00 = binding.layoutBoneTabletResult00
        val layoutBoneTabletResult01 = binding.layoutBoneTabletResult01

        // 정보1
        var name3 = ApplicationClass.prefs.name3
        var tabletReligion = ApplicationClass.prefs.tabletReligion
        var tabletType = ApplicationClass.prefs.tabletType
        var layoutBoneTabletMark1 = binding.layoutBoneTabletMark1
        var layoutBoneTabletResult10 = binding.layoutBoneTabletResult10
        var layoutBoneTabletResult12 = binding.layoutBoneTabletResult12

        // 정보2
        var boneName3 = ApplicationClass.prefs.boneName3
        var boneTabletReligion = ApplicationClass.prefs.boneTabletReligion
        var boneTabletType = ApplicationClass.prefs.boneTabletType
        var layoutBoneTabletMark2 = binding.layoutBoneTabletMark2
        var layoutBoneTablet2Result10 = binding.layoutBoneTablet2Result10
        var layoutBoneTablet2Result12 = binding.layoutBoneTablet2Result12

        if(ApplicationClass.prefs.boneTabletSex == "남성"){
            println("ApplicationClass.prefs.boneTabletSex" + ApplicationClass.prefs.boneTabletSex)
//            // 정보1
            name3 = ApplicationClass.prefs.boneName3
            tabletReligion = ApplicationClass.prefs.boneTabletReligion
            tabletType = ApplicationClass.prefs.boneTabletType
//
//            // 정보2
            boneName3 = ApplicationClass.prefs.name3
            boneTabletReligion = ApplicationClass.prefs.tabletReligion
            boneTabletType = ApplicationClass.prefs.tabletType
        }
        else if(ApplicationClass.prefs.boneTabletSex == "여성"){
        }

        // 추가 정보 / 기존 정보
        if(tabletReligion == boneTabletReligion && (tabletType != "문구" && boneTabletType != "문구") && !(name3 == "" || boneName3 == "")) {
            var imageName = "img_mark1"

            if(tabletReligion == "일반") {
                if(tabletType.toString().contains("본관") && boneTabletType.toString().contains("본관")){
                }else if(!tabletType.toString().contains("본관") && !boneTabletType.toString().contains("본관")){
                    layoutBoneTabletResult00.visibility = View.VISIBLE
                }else {
                    if(!tabletType.toString().contains("본관")){
                        layoutBoneTabletResult10.visibility = View.VISIBLE
                    }
                    if(!boneTabletType.toString().contains("본관")){
                        layoutBoneTablet2Result10.visibility = View.VISIBLE
                    }
                }
            }else if(tabletReligion == "기독교"){
                layoutBoneTabletResult01.visibility = View.VISIBLE
                imageName = "img_mark2"
            }else if(tabletReligion == "불교"){
                layoutBoneTabletResult01.visibility = View.VISIBLE
                imageName = "img_mark3"
            }else if(tabletReligion == "천주교") {
                layoutBoneTabletResult01.visibility = View.VISIBLE
                imageName = "img_mark4"
                if(selectedTabletName!!.contains("검정")) {
                    imageName = "img_mark4_2"
                }
            }
            val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
            layoutBoneTabletResult01.setImageResource(imageResource)
        }
        else {
            if(name3 != "" && tabletType != "문구"){
                // 이미지 이름을 문자열로 정의합니다.
                var imageName = "img_mark1"
                layoutBoneTabletResult12.visibility = View.VISIBLE

                if(tabletType == "일반(본관)") {
                    layoutBoneTabletResult12.visibility = View.GONE
                }else if(tabletType == "일반") {
//            imageName = "img_mark1"
                    layoutBoneTabletResult10.visibility = View.VISIBLE
                    layoutBoneTabletResult12.visibility = View.GONE
                }else if(tabletReligion == "기독교")
                    imageName = "img_mark2"
                else if(tabletReligion == "불교")
                    imageName = "img_mark3"
                else if(tabletReligion == "천주교") {
                    imageName = "img_mark4"
                    if(selectedTabletName!!.contains("검정")) {
                        imageName = "img_mark4_2"
                    }
                }

                // 직분, 세례명, 법명
                val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
                layoutBoneTabletResult12.setImageResource(imageResource)
            }

            if(boneName3 != "" && boneTabletType != "문구"){
                // 이미지 이름을 문자열로 정의합니다.
                var imageName = "img_mark1"
                layoutBoneTablet2Result12.visibility = View.VISIBLE

                if(boneTabletType == "일반(본관)") {
                    layoutBoneTablet2Result12.visibility = View.GONE
                }else if(boneTabletType == "일반") {
//            imageName = "img_mark1"
                    layoutBoneTablet2Result10.visibility = View.VISIBLE
                    layoutBoneTablet2Result12.visibility = View.GONE
                }else if(boneTabletReligion == "기독교")
                    imageName = "img_mark2"
                else if(boneTabletReligion == "불교")
                    imageName = "img_mark3"
                else if(boneTabletReligion == "천주교"){
                    imageName = "img_mark4"
                    if(selectedTabletName!!.contains("검정")) {
                        imageName = "img_mark4_2"
                    }
                }

                // 직분, 세례명, 법명
                val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
                layoutBoneTablet2Result12.setImageResource(imageResource)
            }
        }
    }
    private fun setTablet2Data() {
        setTable2Mark()
        val pixel_size_13 = resources.getDimensionPixelSize(R.dimen.pixel_size_13)
        val pixel_size_15 = resources.getDimensionPixelSize(R.dimen.pixel_size_15)
        val pixel_size_17_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_17_5)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)

        // 이름
        var tabletName2 = ApplicationClass.prefs.tabletName2.toString()
        var name3 = ApplicationClass.prefs.name3.toString()
        var tmp = StringBuilder()
        var tmp2 = StringBuilder()
        var tabletReligion = ApplicationClass.prefs.tabletReligion
        var tabletType = ApplicationClass.prefs.tabletType.toString()

        // 합골
        var boneTabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
        var boneName3 = ApplicationClass.prefs.boneName3.toString()
        var boneTmp = StringBuilder()
        var boneTmp2 = StringBuilder()
        var boneTabletReligion = ApplicationClass.prefs.boneTabletReligion
        var boneTabletType = ApplicationClass.prefs.boneTabletType.toString()


        // 추가 정보 / 기존 정보
        if(ApplicationClass.prefs.boneTabletSex == "남성"){
            // 이름
            tabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
            name3 = ApplicationClass.prefs.boneName3.toString()
//            tabletReligion = ApplicationClass.prefs.boneTabletReligion
            tabletType = ApplicationClass.prefs.boneTabletType.toString()

            // 합골
            boneTabletName2 = ApplicationClass.prefs.tabletName2.toString()
            boneName3 = ApplicationClass.prefs.name3.toString()
//            boneTabletReligion = ApplicationClass.prefs.tabletReligion
            boneTabletType = ApplicationClass.prefs.tabletType.toString()
        }

        if(selectedTabletName!!.contains("검정")){
            binding.layoutBoneTabletResult00.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutBoneTabletResult10.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult1.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult22.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult23.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult24.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult25.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult26.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult27.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult28.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult29.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult3.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutBoneTablet2Result10.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result1.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result22.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result23.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result24.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result25.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result26.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result27.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result28.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result29.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result3.setTextColor(Color.parseColor("#FFD700"))
        }

        if(!tabletType.contains("본관") && tabletType != "문구") {
            val layoutBoneTabletResult1 = binding.layoutBoneTabletResult1
            val layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
            val layoutBoneTabletResult21 = binding.layoutBoneTabletResult21
            val layoutBoneTabletResult22 = binding.layoutBoneTabletResult22
            val layoutBoneTabletResult23 = binding.layoutBoneTabletResult23
            val layoutBoneTabletResult24 = binding.layoutBoneTabletResult24
            val layoutBoneTabletResult25 = binding.layoutBoneTabletResult25
            val layoutBoneTabletResult26 = binding.layoutBoneTabletResult26
            val layoutBoneTabletResult27 = binding.layoutBoneTabletResult27
            val layoutBoneTabletResult28 = binding.layoutBoneTabletResult28
            val layoutBoneTabletResult29 = binding.layoutBoneTabletResult29
            val layoutBoneTabletResult3 = binding.layoutBoneTabletResult3
            layoutBoneTabletResult2.visibility = View.VISIBLE

            when (tabletType) {
                "일반", "불교" -> {
                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 340
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (name3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[1].toString()

                                tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
//                            layoutBoneTabletResult2.setLineSpacing(260f, 1f)
                        }
                        3 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[2].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
//                                .append(name3[2])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 3.2f)
                        }
                        4 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[3].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
//                                .append(name3[2]).append("\n").append(name3[3])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 2.2f)
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "기독교" -> {
                    layoutBoneTabletResult1.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 310
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (name3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[1].toString()

                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
//                            layoutBoneTabletResult2.setLineSpacing(260f, 1f)
                        }
                        3 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[2].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
//                                .append(name3[2])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 3.2f)
                        }
                        4 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[3].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
//                                .append(name3[2]).append("\n").append(name3[3])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 2.2f)
                        }
                    }

                    when (tabletName2.length) {
                        2 ->{
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])
                        }
                        3 -> {
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])
                        }
                        4 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])
                            layoutBoneTabletResult1.letterSpacing = -0.2f
                        }
                    }
                    tmp2.append(tabletName2)
                    layoutBoneTabletResult1.text = tmp2.toString()
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "천주교" -> {
                    layoutBoneTabletResult3.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 310
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (name3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[1].toString()

                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
//                            layoutBoneTabletResult2.setLineSpacing(260f, 1f)
                        }
                        3 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[2].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
//                                .append(name3[2])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 3.2f)
                        }
                        4 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[3].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
//                                .append(name3[2]).append("\n").append(name3[3])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 2.2f)
                        }
                    }

                    when (tabletName2.length) {
                        2 ->{
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        3 -> {
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        4 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        5 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        6 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2] + "" + tabletName2[5])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.5f
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                    tmp2.append(tabletName2)
                    layoutBoneTabletResult3.text = tmp2.toString()
                }
            }
        }
        else if(tabletType.contains("본관") && tabletType != "문구") {
            val layoutBoneTabletResult1 = binding.layoutBoneTabletResult1
            val layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
            val layoutBoneTabletResult21 = binding.layoutBoneTabletResult21
            val layoutBoneTabletResult22 = binding.layoutBoneTabletResult22
            val layoutBoneTabletResult23 = binding.layoutBoneTabletResult23
            val layoutBoneTabletResult24 = binding.layoutBoneTabletResult24
            val layoutBoneTabletResult25 = binding.layoutBoneTabletResult25
            val layoutBoneTabletResult26 = binding.layoutBoneTabletResult26
            val layoutBoneTabletResult27 = binding.layoutBoneTabletResult27
            val layoutBoneTabletResult28 = binding.layoutBoneTabletResult28
            val layoutBoneTabletResult29 = binding.layoutBoneTabletResult29
            val layoutBoneTabletResult3 = binding.layoutBoneTabletResult3
            layoutBoneTabletResult2.visibility = View.VISIBLE

            println("본관 진입")
            when (tabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 410
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (name3.length) {
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[6].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.4f)
                        }
                        8 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = name3[6].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[7].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6]).append("\n").append(name3[7])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.25f)
                        }
                        9 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = name3[6].toString()

                            layoutBoneTabletResult28.visibility = View.VISIBLE
                            layoutBoneTabletResult28.text = name3[7].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[8].toString()

//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.1f)
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutBoneTabletResult1.visibility = View.VISIBLE
                    layoutBoneTabletResult1.scaleY = 1.3f

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 280
                    layoutBoneTabletResult2.layoutParams = layoutParams
//                    layoutBoneTabletResult2.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())


                    layoutBoneTabletResult3.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutBoneTabletResult3.typeface = hyhaeso
                    layoutBoneTabletResult3.text = "召天"

                    when (name3.length) {
                        5 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[4].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.7f)
                        }
                        6 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[5].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.4f)
                        }
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[6].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.15f)
                        }
                    }

                    when (tabletName2.length) {
                        2 ->{
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])
                            layoutBoneTabletResult1.scaleY = 1.3f
                        }
                        3 -> {
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])
                            layoutBoneTabletResult1.scaleY = 1.3f
                        }
                        4 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])
                            layoutBoneTabletResult1.scaleY = 1.3f
                            layoutBoneTabletResult1.letterSpacing = -0.2f
                        }
                    }
                    tmp2.append(tabletName2)
                    layoutBoneTabletResult1.text = tmp2.toString()
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 350
                    layoutBoneTabletResult2.layoutParams = layoutParams
//                    layoutBoneTabletResult2.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())

                    when (name3.length) {
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[6].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.35f)
                        }
                        8 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = name3[6].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[7].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6]).append("\n").append(name3[7])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.2f)
                        }
                        9 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = name3[6].toString()

                            layoutBoneTabletResult28.visibility = View.VISIBLE
                            layoutBoneTabletResult28.text = name3[7].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[8].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.5f)
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 310
                    layoutBoneTabletResult2.layoutParams = layoutParams
//                    layoutBoneTabletResult2.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())

                    layoutBoneTabletResult3.visibility = View.VISIBLE

                    when (name3.length) {
                        5 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[4].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.9f)
                        }
                        6 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[5].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.55f)
                        }
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = name3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = name3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = name3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = name3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = name3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = name3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = name3[6].toString()
//                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
//                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
//                                .append("\n").append(name3[6])
//                            layoutBoneTabletResult2.setLineSpacing(0f, 1.3f)
                        }
                    }

                    when (tabletName2.length) {
                        2 ->{
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        3 -> {
//                            tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        4 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])
                            layoutBoneTabletResult3.scaleY = 1.3f
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                        }
                        5 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        6 -> {
//                            tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2] + "" + tabletName2[5])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.5f
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                    tmp2.append(tabletName2)
                    layoutBoneTabletResult3.text = tmp2.toString()
                }
            }
        }
        else if(tabletType == "문구"){

        }

        // 합골
        println("합골 진입")
        if(!boneTabletType.contains("본관") && boneTabletType != "문구") {
            val layoutBoneTabletResult1 = binding.layoutBoneTablet2Result1
            val layoutBoneTabletResult2 = binding.layoutBoneTablet2Result2
            val layoutBoneTabletResult21 = binding.layoutBoneTablet2Result21
            val layoutBoneTabletResult22 = binding.layoutBoneTablet2Result22
            val layoutBoneTabletResult23 = binding.layoutBoneTablet2Result23
            val layoutBoneTabletResult24 = binding.layoutBoneTablet2Result24
            val layoutBoneTabletResult25 = binding.layoutBoneTablet2Result25
            val layoutBoneTabletResult26 = binding.layoutBoneTablet2Result26
            val layoutBoneTabletResult27 = binding.layoutBoneTablet2Result27
            val layoutBoneTabletResult28 = binding.layoutBoneTablet2Result28
            val layoutBoneTabletResult29 = binding.layoutBoneTablet2Result29
            val layoutBoneTabletResult3 = binding.layoutBoneTablet2Result3
            layoutBoneTabletResult2.visibility = View.VISIBLE

            when (boneTabletType) {
                "일반", "불교" -> {
                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 340
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[1].toString()

                            tmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
                        }
                        3 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[2].toString()
                        }
                        4 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[3].toString()
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "기독교" -> {
                    layoutBoneTabletResult1.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 310
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[1].toString()

                            tmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
//                            layoutBoneTabletResult2.setLineSpacing(260f, 1f)
                        }
                        3 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[2].toString()
                        }
                        4 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[3].toString()
                        }
                    }

                    when (boneTabletName2.length) {
                        2 ->{
//                            tmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1])
                        }
                        3 -> {
//                            tmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1]).append("\n").append(boneTabletName2[2])
                        }
                        4 -> {
//                            tmp2.append(boneTabletName2[0] + "" + boneTabletName2[1]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[3])
                            layoutBoneTabletResult1.letterSpacing = -0.2f
                        }
                    }
                    tmp2.append(boneTabletName2)
                    layoutBoneTabletResult1.text = tmp2.toString()
                }
                "천주교" -> {
                    layoutBoneTabletResult3.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 310
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[1].toString()

                            tmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
//                            layoutBoneTabletResult2.setLineSpacing(260f, 1f)
                        }
                        3 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[2].toString()
                        }
                        4 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[3].toString()
                        }
                    }

                    when (boneTabletName2.length) {
                        2 ->{
//                            tmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1])
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        3 -> {
//                            tmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1]).append("\n").append(boneTabletName2[2])
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        4 -> {
//                            tmp2.append(boneTabletName2[0] + "" + boneTabletName2[1]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[3])
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        5 -> {
//                            tmp2.append(boneTabletName2[0] + "" + boneTabletName2[3]).append("\n").append(boneTabletName2[1] + "" + boneTabletName2[4]).append("\n").append(boneTabletName2[2])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        6 -> {
//                            tmp2.append(boneTabletName2[0] + "" + boneTabletName2[3]).append("\n").append(boneTabletName2[1] + "" + boneTabletName2[4]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[5])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.5f
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                    tmp2.append(boneTabletName2)
                    layoutBoneTabletResult3.text = tmp2.toString()
                }
            }
        }
        else if(boneTabletType.contains("본관") && boneTabletType != "문구") {
            val layoutBoneTabletResult1 = binding.layoutBoneTablet2Result1
            val layoutBoneTabletResult2 = binding.layoutBoneTablet2Result2
            val layoutBoneTabletResult21 = binding.layoutBoneTablet2Result21
            val layoutBoneTabletResult22 = binding.layoutBoneTablet2Result22
            val layoutBoneTabletResult23 = binding.layoutBoneTablet2Result23
            val layoutBoneTabletResult24 = binding.layoutBoneTablet2Result24
            val layoutBoneTabletResult25 = binding.layoutBoneTablet2Result25
            val layoutBoneTabletResult26 = binding.layoutBoneTablet2Result26
            val layoutBoneTabletResult27 = binding.layoutBoneTablet2Result27
            val layoutBoneTabletResult28 = binding.layoutBoneTablet2Result28
            val layoutBoneTabletResult29 = binding.layoutBoneTablet2Result29
            val layoutBoneTabletResult3 = binding.layoutBoneTablet2Result3
            layoutBoneTabletResult2.visibility = View.VISIBLE

            println("본관 진입")
            when (boneTabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 410
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[6].toString()
                        }
                        8 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = boneName3[6].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[7].toString()
                        }
                        9 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = boneName3[6].toString()

                            layoutBoneTabletResult28.visibility = View.VISIBLE
                            layoutBoneTabletResult28.text = boneName3[7].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[8].toString()
                        }
                    }
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutBoneTabletResult1.visibility = View.VISIBLE
                    layoutBoneTabletResult1.scaleY = 1.3f

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 280
                    layoutBoneTabletResult2.layoutParams = layoutParams


                    layoutBoneTabletResult3.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutBoneTabletResult3.typeface = hyhaeso
                    layoutBoneTabletResult3.text = "召天"

                    when (boneName3.length) {
                        5 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[4].toString()
                        }
                        6 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[5].toString()
                        }
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[6].toString()
                        }
                    }

                    when (boneTabletName2.length) {
                        2 ->{
                            layoutBoneTabletResult1.scaleY = 1.3f
                        }
                        3 -> {
                            layoutBoneTabletResult1.scaleY = 1.3f
                        }
                        4 -> {
                            layoutBoneTabletResult1.scaleY = 1.3f
                            layoutBoneTabletResult1.letterSpacing = -0.2f
                        }
                    }
                    tmp2.append(boneTabletName2)
                    layoutBoneTabletResult1.text = tmp2.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 350
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[6].toString()
                        }
                        8 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = boneName3[6].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[7].toString()
                        }
                        9 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult27.visibility = View.VISIBLE
                            layoutBoneTabletResult27.text = boneName3[6].toString()

                            layoutBoneTabletResult28.visibility = View.VISIBLE
                            layoutBoneTabletResult28.text = boneName3[7].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[8].toString()
                        }
                    }
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 65
                    layoutParams.height = 310
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    layoutBoneTabletResult3.visibility = View.VISIBLE

                    when (boneName3.length) {
                        5 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[4].toString()
                        }
                        6 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[5].toString()
                        }
                        7 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult22.visibility = View.VISIBLE
                            layoutBoneTabletResult22.text = boneName3[1].toString()

                            layoutBoneTabletResult23.visibility = View.VISIBLE
                            layoutBoneTabletResult23.text = boneName3[2].toString()

                            layoutBoneTabletResult24.visibility = View.VISIBLE
                            layoutBoneTabletResult24.text = boneName3[3].toString()

                            layoutBoneTabletResult25.visibility = View.VISIBLE
                            layoutBoneTabletResult25.text = boneName3[4].toString()

                            layoutBoneTabletResult26.visibility = View.VISIBLE
                            layoutBoneTabletResult26.text = boneName3[5].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[6].toString()
                        }
                    }

                    when (boneTabletName2.length) {
                        2 ->{
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        3 -> {
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        4 -> {
                            layoutBoneTabletResult3.scaleY = 1.3f
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                        }
                        5 -> {
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.3f
                        }
                        6 -> {
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult3.letterSpacing = -0.2f
                            layoutBoneTabletResult3.scaleY = 1.5f
                        }
                    }
                    tmp2.append(boneTabletName2)
                    layoutBoneTabletResult3.text = tmp2.toString()
                }
            }
        }
        else if(boneTabletType == "문구"){

        }

    }
}