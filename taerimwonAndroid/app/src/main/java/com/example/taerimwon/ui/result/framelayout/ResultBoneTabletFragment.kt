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
import com.example.taerimwon.ui.result.ResultContainerFragment
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultBoneTabletFragment : BaseFragment<FragmentResultBoneTabletBinding>(R.layout.fragment_result_bone_tablet) {
    private lateinit var selectedTabletType: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()

        if(selectedTabletType != "선택안함") {
            if(selectedTabletType.contains("사진")){
                binding.layoutResultContent.visibility = View.GONE
            }else if(ApplicationClass.prefs.tabletType.toString().contains("문구")){
                binding.layoutResultContent.visibility = View.GONE
            }else if(selectedTabletType.contains("합골")){
//                setTabletData()
                setTablet2Data()
                println("dd")
            }else {
                binding.layoutResultContent.visibility = View.GONE
            }
        }
    }
    private fun setTableMark() {
        // 이미지 이름을 문자열로 정의합니다.
        val tabletReligion = ApplicationClass.prefs.tabletReligion
        val tabletType = ApplicationClass.prefs.tabletType
        var imageName = "img_mark1"

        if(tabletReligion == "일반" && tabletType == "본관") {
            binding.layoutBoneTabletResult10.visibility = View.GONE
            binding.layoutBoneTabletResult12.visibility = View.GONE
        }else if(tabletReligion == "일반" && tabletType != "문구") {
//            imageName = "img_mark1"
            binding.layoutBoneTabletResult10.visibility = View.VISIBLE
            binding.layoutBoneTabletResult12.visibility = View.GONE
        }else if(tabletReligion == "기독교" && tabletType != "문구")
            imageName = "img_mark2"
        else if(tabletReligion == "불교" && tabletType != "문구")
            imageName = "img_mark3"
        else if(tabletReligion == "천주교" && tabletType != "문구")
            imageName = "img_mark4"
        else
            return

        // 직분, 세례명, 법명
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.layoutBoneTabletResult12.setImageResource(imageResource)
    }
    private fun setTabletData() {
        val selectedTabletName = ApplicationClass.prefs.selectedTabletName

        setTableMark()
        val pixel_size_13 = resources.getDimensionPixelSize(R.dimen.pixel_size_13)
        val pixel_size_15 = resources.getDimensionPixelSize(R.dimen.pixel_size_15)
        val pixel_size_17_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_17_5)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
        val pixel_size_22_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_22_5)
//        val pixel_size_26 = resources.getDimensionPixelSize(R.dimen.pixel_size_26)
//        val pixel_size_30 = resources.getDimensionPixelSize(R.dimen.pixel_size_30)
//        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
//        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
//        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)

        // 이름
        val tabletName2 = ApplicationClass.prefs.tabletName2.toString()
        val name3 = ApplicationClass.prefs.name3.toString()
        val tmp = StringBuilder()
        val tmp2 = StringBuilder()
        val tabletReligion = ApplicationClass.prefs.tabletReligion
        val tabletType = ApplicationClass.prefs.tabletType.toString()

        val layoutBoneTabletResult0 = binding.layoutBoneTabletResult0
        val layoutBoneTabletResult20 = binding.layoutBoneTabletResult20
        val layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
        val layoutBoneTabletResult21 = binding.layoutBoneTabletResult21
        val layoutBoneTabletResult22 = binding.layoutBoneTabletResult22
        val layoutBoneTabletResult30 = binding.layoutBoneTabletResult30
        val layoutBoneTabletResult3 = binding.layoutBoneTabletResult3
        val layoutBoneTabletResult31 = binding.layoutBoneTabletResult31
        val layoutBoneTabletResult312 = binding.layoutBoneTabletResult312
        val layoutBoneTabletResult32 = binding.layoutBoneTabletResult32

        if(selectedTabletName!!.contains("검정")){
            binding.layoutBoneTabletResult0.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult10.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult20.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult2.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult22.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult30.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult3.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult31.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult312.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTabletResult32.setTextColor(Color.parseColor("#FFD700"))
        }

        if(!tabletType.contains("본관") && tabletType != "문구") {
            when (tabletType) {
                "일반", "불교" -> {
                    layoutBoneTabletResult2.visibility = View.VISIBLE

                    when (name3.length) {
                        2 -> {
                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
                        }
                        3 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2])
                        }
                        4 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2]).append("\n").append(name3[3])
                            layoutBoneTabletResult2.setLineSpacing(0f, 1.4f)
                        }
                    }
                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "기독교" -> {
                    layoutBoneTabletResult21.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult21.layoutParams as ViewGroup.MarginLayoutParams
                    val marginTopInPixels = 0
                    layoutParams.topMargin = marginTopInPixels
                    layoutBoneTabletResult21.setLineSpacing(0f, 1.7f)
                    layoutBoneTabletResult21.layoutParams = layoutParams

                    when (name3.length) {
                        2 -> {
                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
                        }
                        3 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2])
                        }
                        4 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2]).append("\n").append(name3[3])
                            layoutBoneTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutBoneTabletResult20.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutBoneTabletResult20.letterSpacing = -0.15f
                        }
                    }
                    layoutBoneTabletResult20.text = tmp2.toString()
                    layoutBoneTabletResult21.text = tmp.toString()
                }
                "천주교" -> {
                    layoutBoneTabletResult21.visibility = View.VISIBLE
                    layoutBoneTabletResult21.setLineSpacing(0f, 1.7f)

                    when (name3.length) {
                        2 -> {
                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
                        }
                        3 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2])
                        }
                        4 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2]).append("\n").append(name3[3])
                            layoutBoneTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutBoneTabletResult22.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutBoneTabletResult22.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutBoneTabletResult22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult22.letterSpacing = -0.17f
                        }
                    }
                    layoutBoneTabletResult22.text = tmp2.toString()
                    layoutBoneTabletResult21.text = tmp.toString()
                }
            }
        }else if(tabletType.contains("본관") && tabletType != "문구") {
            println("본관 진입")
            when (tabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    binding.layoutBoneTabletResult10.visibility = View.GONE
                    binding.layoutBoneTabletResult12.visibility = View.GONE
                    binding.layoutBoneTabletResult0.visibility = View.VISIBLE

                    when (name3.length) {
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                        }
                        8 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7])
                            layoutBoneTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutBoneTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutBoneTabletResult0.text = tmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutBoneTabletResult3.visibility = View.VISIBLE

                    layoutBoneTabletResult32.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutBoneTabletResult32.typeface = hyhaeso
                    layoutBoneTabletResult32.text = "召天"

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutBoneTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutBoneTabletResult30.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutBoneTabletResult30.letterSpacing = -0.15f
                        }
                    }
                    layoutBoneTabletResult30.text = tmp2.toString()
                    layoutBoneTabletResult3.text = tmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    layoutBoneTabletResult31.visibility = View.VISIBLE

                    when (name3.length) {
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                        }
                        8 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7])
                            layoutBoneTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutBoneTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutBoneTabletResult31.text = tmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    layoutBoneTabletResult312.visibility = View.VISIBLE

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutBoneTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutBoneTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                            layoutBoneTabletResult312.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutBoneTabletResult32.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutBoneTabletResult32.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutBoneTabletResult32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult32.letterSpacing = -0.17f
                        }
                    }
                    layoutBoneTabletResult32.text = tmp2.toString()
                    layoutBoneTabletResult312.text = tmp.toString()
                }
            }
        }else if(tabletType == "문구"){

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


        if(ApplicationClass.prefs.boneSex == "남성"){
            // 정보1
            name3 = ApplicationClass.prefs.boneName3
            tabletReligion = ApplicationClass.prefs.boneTabletReligion
            tabletType = ApplicationClass.prefs.boneTabletType
            layoutBoneTabletMark1 = binding.layoutBoneTabletMark2
            layoutBoneTabletResult10 = binding.layoutBoneTablet2Result10
            layoutBoneTabletResult12 = binding.layoutBoneTablet2Result12

            // 정보2
            boneName3 = ApplicationClass.prefs.name3
            boneTabletReligion = ApplicationClass.prefs.tabletReligion
            boneTabletType = ApplicationClass.prefs.tabletType
            layoutBoneTabletMark2 = binding.layoutBoneTabletMark1
            layoutBoneTablet2Result10 = binding.layoutBoneTabletResult10
            layoutBoneTablet2Result12 = binding.layoutBoneTabletResult12
        }
        else if(ApplicationClass.prefs.boneSex == "여성"){
        }

        // 추가 정보 / 기존 정보
        if(tabletReligion == boneTabletReligion && !(name3 == "" || boneName3 == "")) {
            var imageName = "img_mark1"

            layoutBoneTabletMark0.visibility = View.VISIBLE
            layoutBoneTabletMark1.visibility = View.GONE
            layoutBoneTabletMark2.visibility = View.GONE

            if(tabletReligion == "일반" && (tabletType != "문구" && boneTabletType != "문구")) {
                if(tabletType == "본관" && boneTabletType == "본관"){
                    layoutBoneTabletMark0.visibility = View.GONE
                }else if(tabletType != "본관" && boneTabletType != "본관"){
                    layoutBoneTabletResult00.visibility = View.VISIBLE
                }else {
                    layoutBoneTabletMark0.visibility = View.GONE

                    if(tabletType == "본관"){
                        layoutBoneTabletMark2.visibility = View.VISIBLE
                        layoutBoneTabletResult10.visibility = View.VISIBLE
                    }else if(boneTabletType == "본관"){
                        layoutBoneTabletMark1.visibility = View.VISIBLE
                        layoutBoneTablet2Result10.visibility = View.VISIBLE
                    }
                }
            }else if(tabletReligion == "기독교" && (tabletType != "문구" && boneTabletType != "문구"))
                imageName = "img_mark2"
            else if(tabletReligion == "불교" && (tabletType != "문구" && boneTabletType != "문구"))
                imageName = "img_mark3"
            else if(tabletReligion == "천주교" && (tabletType != "문구" && boneTabletType != "문구"))
                imageName = "img_mark4"

            val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
            layoutBoneTabletResult01.setImageResource(imageResource)
        }else {
            if(name3 != ""){
                // 이미지 이름을 문자열로 정의합니다.
                var imageName = "img_mark1"
                layoutBoneTabletMark1.visibility = View.VISIBLE
                layoutBoneTabletResult12.visibility = View.VISIBLE

                if(tabletReligion == "일반" && tabletType == "본관") {
                    layoutBoneTabletMark1.visibility = View.GONE
                }else if(tabletReligion == "일반" && tabletType != "문구") {
//            imageName = "img_mark1"
                    layoutBoneTabletResult10.visibility = View.VISIBLE
                    layoutBoneTabletResult12.visibility = View.GONE
                }else if(tabletReligion == "기독교" && tabletType != "문구")
                    imageName = "img_mark2"
                else if(tabletReligion == "불교" && tabletType != "문구")
                    imageName = "img_mark3"
                else if(tabletReligion == "천주교" && tabletType != "문구")
                    imageName = "img_mark4"
                else
                    return

                // 직분, 세례명, 법명
                val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
                layoutBoneTabletResult12.setImageResource(imageResource)
            }

            if(boneName3 != ""){
                // 이미지 이름을 문자열로 정의합니다.
                var imageName = "img_mark1"
                layoutBoneTabletMark2.visibility = View.VISIBLE
                layoutBoneTablet2Result12.visibility = View.VISIBLE

                if(boneTabletReligion == "일반" && boneTabletType == "본관") {
                    layoutBoneTabletMark2.visibility = View.GONE
                }else if(boneTabletReligion == "일반" && boneTabletType != "문구") {
//            imageName = "img_mark1"
                    layoutBoneTablet2Result10.visibility = View.VISIBLE
                    layoutBoneTablet2Result12.visibility = View.GONE
                }else if(boneTabletReligion == "기독교" && boneTabletType != "문구")
                    imageName = "img_mark2"
                else if(boneTabletReligion == "불교" && boneTabletType != "문구")
                    imageName = "img_mark3"
                else if(boneTabletReligion == "천주교" && boneTabletType != "문구")
                    imageName = "img_mark4"
                else
                    return

                // 직분, 세례명, 법명
                val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
                layoutBoneTablet2Result12.setImageResource(imageResource)
            }
        }
    }
    private fun setTablet2Data() {
        val selectedTabletName2 = ApplicationClass.prefs.selectedTabletName2

        setTable2Mark()
        val pixel_size_13 = resources.getDimensionPixelSize(R.dimen.pixel_size_13)
        val pixel_size_15 = resources.getDimensionPixelSize(R.dimen.pixel_size_15)
        val pixel_size_17_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_17_5)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)

        if(selectedTabletName2!!.contains("검정")){
            binding.layoutBoneTablet2Result0.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result10.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result20.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result2.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result22.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result30.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result3.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result31.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result312.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneTablet2Result32.setTextColor(Color.parseColor("#FFD700"))
        }

        // 이름
        var tabletName2 = ApplicationClass.prefs.tabletName2.toString()
        var name3 = ApplicationClass.prefs.name3.toString()
        var tmp = StringBuilder()
        var tmp2 = StringBuilder()
        var tabletReligion = ApplicationClass.prefs.tabletReligion
        var tabletType = ApplicationClass.prefs.tabletType.toString()

        var layoutBoneTabletResult10 = binding.layoutBoneTabletResult10
        var layoutBoneTabletResult12 = binding.layoutBoneTabletResult12
        var layoutBoneTabletResult0 = binding.layoutBoneTabletResult0
        var layoutBoneTabletResult20 = binding.layoutBoneTabletResult20
        var layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
        var layoutBoneTabletResult21 = binding.layoutBoneTabletResult21
        var layoutBoneTabletResult22 = binding.layoutBoneTabletResult22
        var layoutBoneTabletResult30 = binding.layoutBoneTabletResult30
        var layoutBoneTabletResult3 = binding.layoutBoneTabletResult3
        var layoutBoneTabletResult31 = binding.layoutBoneTabletResult31
        var layoutBoneTabletResult312 = binding.layoutBoneTabletResult312
        var layoutBoneTabletResult32 = binding.layoutBoneTabletResult32

        // 합골
        var boneTabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
        var boneName3 = ApplicationClass.prefs.boneName3.toString()
        var boneTmp = StringBuilder()
        var boneTmp2 = StringBuilder()
        var boneTabletReligion = ApplicationClass.prefs.boneTabletReligion
        var boneTabletType = ApplicationClass.prefs.boneTabletType.toString()

        var layoutBoneTablet2Result10 = binding.layoutBoneTablet2Result10
        var layoutBoneTablet2Result12 = binding.layoutBoneTablet2Result12
        var layoutBoneTablet2Result0 = binding.layoutBoneTablet2Result0
        var layoutBoneTablet2Result20 = binding.layoutBoneTablet2Result20
        var layoutBoneTablet2Result2 = binding.layoutBoneTablet2Result2
        var layoutBoneTablet2Result21 = binding.layoutBoneTablet2Result21
        var layoutBoneTablet2Result22 = binding.layoutBoneTablet2Result22
        var layoutBoneTablet2Result30 = binding.layoutBoneTablet2Result30
        var layoutBoneTablet2Result3 = binding.layoutBoneTablet2Result3
        var layoutBoneTablet2Result31 = binding.layoutBoneTablet2Result31
        var layoutBoneTablet2Result312 = binding.layoutBoneTablet2Result312
        var layoutBoneTablet2Result32 = binding.layoutBoneTablet2Result32

        // 추가 정보 / 기존 정보
        if(ApplicationClass.prefs.boneTabletSex == "남성"){
            // 이름
            tabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
            name3 = ApplicationClass.prefs.boneName3.toString()
            tmp = StringBuilder()
            tmp2 = StringBuilder()
            tabletReligion = ApplicationClass.prefs.boneTabletReligion
            tabletType = ApplicationClass.prefs.boneTabletType.toString()

            layoutBoneTabletResult10 = binding.layoutBoneTablet2Result10
            layoutBoneTabletResult12 = binding.layoutBoneTablet2Result12
            layoutBoneTabletResult0 = binding.layoutBoneTablet2Result0
            layoutBoneTabletResult20 = binding.layoutBoneTablet2Result20
            layoutBoneTabletResult2 = binding.layoutBoneTablet2Result2
            layoutBoneTabletResult21 = binding.layoutBoneTablet2Result21
            layoutBoneTabletResult22 = binding.layoutBoneTablet2Result22
            layoutBoneTabletResult30 = binding.layoutBoneTablet2Result30
            layoutBoneTabletResult3 = binding.layoutBoneTablet2Result3
            layoutBoneTabletResult31 = binding.layoutBoneTablet2Result31
            layoutBoneTabletResult312 = binding.layoutBoneTablet2Result312
            layoutBoneTabletResult32 = binding.layoutBoneTablet2Result32

            // 합골
            boneTabletName2 = ApplicationClass.prefs.tabletName2.toString()
            boneName3 = ApplicationClass.prefs.name3.toString()
            boneTmp = StringBuilder()
            boneTmp2 = StringBuilder()
            boneTabletReligion = ApplicationClass.prefs.tabletReligion
            boneTabletType = ApplicationClass.prefs.tabletType.toString()

            layoutBoneTablet2Result10 = binding.layoutBoneTabletResult10
            layoutBoneTablet2Result12 = binding.layoutBoneTabletResult12
            layoutBoneTablet2Result0 = binding.layoutBoneTabletResult0
            layoutBoneTablet2Result20 = binding.layoutBoneTabletResult20
            layoutBoneTablet2Result2 = binding.layoutBoneTabletResult2
            layoutBoneTablet2Result21 = binding.layoutBoneTabletResult21
            layoutBoneTablet2Result22 = binding.layoutBoneTabletResult22
            layoutBoneTablet2Result30 = binding.layoutBoneTabletResult30
            layoutBoneTablet2Result3 = binding.layoutBoneTabletResult3
            layoutBoneTablet2Result31 = binding.layoutBoneTabletResult31
            layoutBoneTablet2Result312 = binding.layoutBoneTabletResult312
            layoutBoneTablet2Result32 = binding.layoutBoneTabletResult32
        }

        if(!tabletType.contains("본관") && tabletType != "문구") {
            when (tabletType) {
                "일반", "불교" -> {
                    layoutBoneTabletResult2.visibility = View.VISIBLE

                    when (name3.length) {
                        2 -> {
                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
                        }
                        3 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2])
                        }
                        4 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2]).append("\n").append(name3[3])
                            layoutBoneTabletResult2.setLineSpacing(0f, 1.4f)
                        }
                    }
                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "기독교" -> {
                    layoutBoneTabletResult21.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult21.layoutParams as ViewGroup.MarginLayoutParams
                    val marginTopInPixels = 0
                    layoutParams.topMargin = marginTopInPixels
                    layoutBoneTabletResult21.setLineSpacing(0f, 1.7f)
                    layoutBoneTabletResult21.layoutParams = layoutParams

                    when (name3.length) {
                        2 -> {
                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
                        }
                        3 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2])
                        }
                        4 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2]).append("\n").append(name3[3])
                            layoutBoneTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutBoneTabletResult20.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutBoneTabletResult20.letterSpacing = -0.15f
                        }
                    }
                    layoutBoneTabletResult20.text = tmp2.toString()
                    layoutBoneTabletResult21.text = tmp.toString()
                }
                "천주교" -> {
                    layoutBoneTabletResult21.visibility = View.VISIBLE
                    layoutBoneTabletResult21.setLineSpacing(0f, 1.7f)

                    when (name3.length) {
                        2 -> {
                            tmp.append(name3[0]).append("\n").append("\n").append(name3[1])
                        }
                        3 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2])
                        }
                        4 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n")
                                .append(name3[2]).append("\n").append(name3[3])
                            layoutBoneTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutBoneTabletResult22.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutBoneTabletResult22.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutBoneTabletResult22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult22.letterSpacing = -0.17f
                        }
                    }
                    layoutBoneTabletResult22.text = tmp2.toString()
                    layoutBoneTabletResult21.text = tmp.toString()
                }
            }
        }
        else if(tabletType.contains("본관") && tabletType != "문구") {
            println("본관 진입")
            when (tabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    layoutBoneTabletResult10.visibility = View.GONE
                    layoutBoneTabletResult12.visibility = View.GONE
                    layoutBoneTabletResult0.visibility = View.VISIBLE

                    when (name3.length) {
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                        }
                        8 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7])
                            layoutBoneTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutBoneTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutBoneTabletResult0.text = tmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutBoneTabletResult3.visibility = View.VISIBLE

                    layoutBoneTabletResult32.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutBoneTabletResult32.typeface = hyhaeso
                    layoutBoneTabletResult32.text = "召天"

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutBoneTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutBoneTabletResult30.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutBoneTabletResult30.letterSpacing = -0.15f
                        }
                    }
                    layoutBoneTabletResult30.text = tmp2.toString()
                    layoutBoneTabletResult3.text = tmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    layoutBoneTabletResult31.visibility = View.VISIBLE

                    when (name3.length) {
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                        }
                        8 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7])
                            layoutBoneTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutBoneTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutBoneTabletResult31.text = tmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    layoutBoneTabletResult312.visibility = View.VISIBLE

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutBoneTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutBoneTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                            layoutBoneTabletResult312.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutBoneTabletResult32.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutBoneTabletResult32.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutBoneTabletResult32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTabletResult32.letterSpacing = -0.17f
                        }
                    }
                    layoutBoneTabletResult32.text = tmp2.toString()
                    layoutBoneTabletResult312.text = tmp.toString()
                }
            }
        }
        else if(tabletType == "문구"){

        }

        // 합골
        if(!boneTabletType.contains("본관") && boneTabletType != "문구") {
            when (boneTabletType) {
                "일반", "불교" -> {
                    layoutBoneTablet2Result2.visibility = View.VISIBLE

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
                            layoutBoneTablet2Result2.setLineSpacing(0f, 1.4f)
                        }
                    }
                    layoutBoneTablet2Result2.text = boneTmp.toString()
                }
                "기독교" -> {
                    layoutBoneTablet2Result21.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTablet2Result21.layoutParams as ViewGroup.MarginLayoutParams
                    val marginTopInPixels = 0
                    layoutParams.topMargin = marginTopInPixels
                    layoutBoneTablet2Result21.setLineSpacing(0f, 1.7f)
                    layoutBoneTablet2Result21.layoutParams = layoutParams

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
                            layoutBoneTablet2Result21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutBoneTablet2Result20.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutBoneTablet2Result20.letterSpacing = -0.15f
                        }
                    }
                    layoutBoneTablet2Result20.text = boneTmp2.toString()
                    layoutBoneTablet2Result21.text = boneTmp.toString()
                }
                "천주교" -> {
                    layoutBoneTablet2Result21.visibility = View.VISIBLE
                    layoutBoneTablet2Result21.setLineSpacing(0f, 1.7f)

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
                            layoutBoneTablet2Result21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutBoneTablet2Result22.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutBoneTablet2Result22.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutBoneTablet2Result22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTablet2Result22.letterSpacing = -0.17f
                        }
                    }
                    layoutBoneTablet2Result22.text = boneTmp2.toString()
                    layoutBoneTablet2Result21.text = boneTmp.toString()
                }
            }
        }
        else if(boneTabletType.contains("본관") && boneTabletType != "문구") {
            println("본관 진입")
            when (boneTabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    layoutBoneTablet2Result10.visibility = View.GONE
                    layoutBoneTablet2Result12.visibility = View.GONE
                    layoutBoneTablet2Result0.visibility = View.VISIBLE

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
                            layoutBoneTablet2Result0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7]).append("\n").append(boneName3[8])
                            layoutBoneTablet2Result0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutBoneTablet2Result0.text = boneTmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutBoneTablet2Result3.visibility = View.VISIBLE

                    layoutBoneTablet2Result32.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutBoneTablet2Result32.typeface = hyhaeso
                    layoutBoneTablet2Result32.text = "召天"

                    when (boneName3.length) {
                        5 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4])
                        }
                        6 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                            layoutBoneTablet2Result3.setLineSpacing(0f, 1.0f)
                        }
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                            layoutBoneTablet2Result3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutBoneTablet2Result3.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutBoneTablet2Result30.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutBoneTablet2Result30.letterSpacing = -0.15f
                        }
                    }
                    layoutBoneTablet2Result30.text = boneTmp2.toString()
                    layoutBoneTablet2Result3.text = boneTmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    layoutBoneTablet2Result31.visibility = View.VISIBLE

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
                            layoutBoneTablet2Result31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7]).append("\n").append(boneName3[8])
                            layoutBoneTablet2Result31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutBoneTablet2Result31.text = boneTmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    layoutBoneTablet2Result312.visibility = View.VISIBLE

                    when (boneName3.length) {
                        5 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4])
                        }
                        6 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                            layoutBoneTablet2Result312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                            layoutBoneTablet2Result312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                            layoutBoneTablet2Result312.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutBoneTablet2Result32.visibility = View.VISIBLE
                    boneTmp2.append(boneTabletName2)
                    when (boneTabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutBoneTablet2Result32.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutBoneTablet2Result32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutBoneTablet2Result32.letterSpacing = -0.17f
                        }
                    }
                    layoutBoneTablet2Result32.text = boneTmp2.toString()
                    layoutBoneTablet2Result312.text = boneTmp.toString()
                }
            }
        }
        else if(boneTabletType == "문구"){

        }

    }
}