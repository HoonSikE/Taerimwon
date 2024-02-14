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
import com.example.taerimwon.databinding.FragmentResultTablet2Binding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultTablet2Fragment : BaseFragment<FragmentResultTablet2Binding>(R.layout.fragment_result_tablet2) {
    private lateinit var selectedTabletType: String
    private lateinit var boneSelectedTabletType: String
    private lateinit var boneTabletType: String
    private lateinit var boneTabletReligion: String
    private lateinit var selectedTabletName2: String
    private lateinit var boneTabletName2: String
    private lateinit var boneName3: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        boneSelectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
        boneTabletType = ApplicationClass.prefs.boneTabletType.toString()
        boneTabletReligion = ApplicationClass.prefs.boneTabletReligion.toString()
        selectedTabletName2 = ApplicationClass.prefs.selectedTabletName2.toString()
        boneTabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
        boneName3 = ApplicationClass.prefs.boneName3.toString()

        if(!selectedTabletType.contains("선택안함") && !selectedTabletType.contains("합골") && !boneSelectedTabletType.contains("선택안함")) {
            if(ApplicationClass.prefs.boneTabletSex == "남성"){
                selectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
                boneSelectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
                boneTabletType = ApplicationClass.prefs.tabletType.toString()
                boneTabletReligion = ApplicationClass.prefs.tabletReligion.toString()
                selectedTabletName2 = ApplicationClass.prefs.selectedTabletName.toString()
                boneTabletName2 = ApplicationClass.prefs.tabletName2.toString()
                boneName3 = ApplicationClass.prefs.name3.toString()
            }
        }

        if(boneSelectedTabletType != "선택안함") {
            if(boneSelectedTabletType.contains("사진")){
            }else if(boneTabletType.toString().contains("문구")){
            }else if(boneTabletType.toString().contains("합골")) {
                binding.layoutResultContent.visibility = View.GONE
            }else {
                setTablet2Data()
            }
            if(selectedTabletName2!!.contains("미정")){
                binding.layoutResultContent.setBackgroundColor(resources.getColor(android.R.color.black))
                binding.layoutTablet2Content.setBackgroundColor(resources.getColor(android.R.color.white))
            }
        }
    }
    private fun setTable2Mark() {
        // 이미지 이름을 문자열로 정의합니다.
        var imageName = "img_mark1"

        if(boneTabletReligion == "일반" && boneTabletType == "본관") {
            binding.layoutTablet2Result10.visibility = View.GONE
            binding.layoutTablet2Result12.visibility = View.GONE
        }else if(boneTabletReligion == "일반" && boneTabletType != "문구") {
//            imageName = "img_mark1"
            binding.layoutTablet2Result10.visibility = View.VISIBLE
            binding.layoutTablet2Result12.visibility = View.GONE
        }else if(boneTabletReligion == "기독교" && boneTabletType != "문구")
            imageName = "img_mark2"
        else if(boneTabletReligion == "불교" && boneTabletType != "문구")
            imageName = "img_mark3"
        else if(boneTabletReligion == "천주교" && boneTabletType != "문구")
            imageName = "img_mark4"
        else
            return

        if(selectedTabletName2!!.contains("검정")) {
            if(boneTabletReligion == "기독교" && boneTabletType != "문구"){
                imageName = "img_mark2_2"
            }
            if(boneTabletReligion == "천주교" && boneTabletType != "문구"){
                imageName = "img_mark4_2"
            }
        }

        // 직분, 세례명, 법명
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.layoutTablet2Result12.setImageResource(imageResource)
    }
    private fun setTablet2Data() {
        setTable2Mark()
        val pixel_size_39 = resources.getDimensionPixelSize(R.dimen.pixel_size_39)
        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)
        val pixel_size_52_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_52_5)
        val pixel_size_60 = resources.getDimensionPixelSize(R.dimen.pixel_size_60)

        if(selectedTabletName2!!.contains("검정")){
            binding.layoutTablet2Result0.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result10.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result20.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result2.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result22.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result30.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result3.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result31.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result312.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTablet2Result32.setTextColor(Color.parseColor("#FFD700"))
        }

        // 이름
        var tmp = StringBuilder()
        var tmp2 = StringBuilder()

        // 합골
        var boneTmp = StringBuilder()
        var boneTmp2 = StringBuilder()

        var layoutTablet2Result10 = binding.layoutTablet2Result10
        var layoutTablet2Result12 = binding.layoutTablet2Result12
        var layoutTablet2Result0 = binding.layoutTablet2Result0
        var layoutTablet2Result20 = binding.layoutTablet2Result20
        var layoutTablet2Result2 = binding.layoutTablet2Result2
        var layoutTablet2Result21 = binding.layoutTablet2Result21
        var layoutTablet2Result22 = binding.layoutTablet2Result22
        var layoutTablet2Result30 = binding.layoutTablet2Result30
        var layoutTablet2Result3 = binding.layoutTablet2Result3
        var layoutTablet2Result31 = binding.layoutTablet2Result31
        var layoutTablet2Result312 = binding.layoutTablet2Result312
        var layoutTablet2Result32 = binding.layoutTablet2Result32

        val tabletExample = ApplicationClass.prefs.tabletExample

        // 추가
        if(!boneTabletType.contains("본관") && boneTabletType != "문구") {
            when (boneTabletType) {
                "일반", "불교" -> {
                    layoutTablet2Result2.visibility = View.VISIBLE

                    when (boneName3.length) {
                        2 -> {
                            boneTmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
                        }
                        3 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n")
                                .append(boneName3[2])
                        }
                        4 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n")
                                .append(boneName3[2]).append("\n").append(boneName3[3])
                            layoutTablet2Result2.setLineSpacing(0f, 1.4f)
                        }
                    }
                    layoutTablet2Result2.text = boneTmp.toString()
                }
                "기독교" -> {
                    layoutTablet2Result21.visibility = View.VISIBLE

                    val layoutParams = layoutTablet2Result21.layoutParams as ViewGroup.MarginLayoutParams
                    val marginTopInPixels = 0
                    layoutParams.topMargin = marginTopInPixels
                    layoutTablet2Result21.setLineSpacing(0f, 1.7f)
                    layoutTablet2Result21.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            boneTmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
                        }
                        3 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n")
                                .append(boneName3[2])
                        }
                        4 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n")
                                .append(boneName3[2]).append("\n").append(boneName3[3])
                            layoutTablet2Result21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutTablet2Result20.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutTablet2Result20.letterSpacing = -0.15f
                        }
                    }
                    layoutTablet2Result20.text = boneTmp2.toString()
                    layoutTablet2Result21.text = boneTmp.toString()
                }
                "천주교" -> {
                    layoutTablet2Result21.visibility = View.VISIBLE
                    layoutTablet2Result21.setLineSpacing(0f, 1.7f)

                    when (boneName3.length) {
                        2 -> {
                            boneTmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
                        }
                        3 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n")
                                .append(boneName3[2])
                        }
                        4 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n")
                                .append(boneName3[2]).append("\n").append(boneName3[3])
                            layoutTablet2Result21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutTablet2Result20.text = boneTmp2.toString()
                    layoutTablet2Result22.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutTablet2Result22.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutTablet2Result22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_39.toFloat())
                            layoutTablet2Result22.letterSpacing = -0.17f
                        }
                    }
                    layoutTablet2Result22.text = boneTmp2.toString()
                    layoutTablet2Result21.text = boneTmp.toString()
                }
            }
        }else if(boneTabletType.contains("본관") && boneTabletType != "문구") {
            println("본관 진입")
            when (boneTabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    layoutTablet2Result10.visibility = View.GONE
                    layoutTablet2Result12.visibility = View.GONE
                    layoutTablet2Result0.visibility = View.VISIBLE

                    when (boneName3.length) {
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                        }
                        8 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7])
                            layoutTablet2Result0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_60.toFloat())
                        }
                        9 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7]).append("\n").append(boneName3[8])
                            layoutTablet2Result0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_52_5.toFloat())
                        }
                    }
                    layoutTablet2Result0.text = boneTmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutTablet2Result3.visibility = View.VISIBLE

                    layoutTablet2Result32.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutTablet2Result32.typeface = hyhaeso
                    layoutTablet2Result32.text = "召天"

                    when (boneName3.length) {
                        5 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4])
                        }
                        6 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                            layoutTablet2Result3.setLineSpacing(0f, 1.0f)
                        }
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                            layoutTablet2Result3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                            layoutTablet2Result3.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutTablet2Result30.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutTablet2Result30.letterSpacing = -0.15f
                        }
                    }
                    layoutTablet2Result30.text = boneTmp2.toString()
                    layoutTablet2Result3.text = boneTmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    layoutTablet2Result31.visibility = View.VISIBLE

                    when (boneName3.length) {
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                        }
                        8 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7])
                            layoutTablet2Result31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_60.toFloat())
                        }
                        9 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7]).append("\n").append(boneName3[8])
                            layoutTablet2Result31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_52_5.toFloat())
                        }
                    }
                    layoutTablet2Result31.text = boneTmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    layoutTablet2Result312.visibility = View.VISIBLE

                    when (boneName3.length) {
                        5 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4])
                        }
                        6 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                            layoutTablet2Result312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_60.toFloat())
                        }
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                            layoutTablet2Result312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_60.toFloat())
                            layoutTablet2Result312.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutTablet2Result32.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutTablet2Result32.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutTablet2Result32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_39.toFloat())
                            layoutTablet2Result32.letterSpacing = -0.17f
                        }
                    }
                    layoutTablet2Result32.text = boneTmp2.toString()
                    layoutTablet2Result312.text = boneTmp.toString()
                }
            }
        }else if(boneTabletType == "문구"){

        }

    }
}