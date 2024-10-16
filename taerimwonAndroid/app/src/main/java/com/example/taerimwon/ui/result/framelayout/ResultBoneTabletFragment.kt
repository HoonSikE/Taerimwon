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
            if(selectedTabletName!!.contains("미정")){
                binding.layoutResultContent.setBackgroundColor(resources.getColor(android.R.color.black))
                binding.layoutBoneTabletContent.setBackgroundColor(resources.getColor(android.R.color.white))
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
            // 정보1
            name3 = ApplicationClass.prefs.boneName3
            tabletReligion = ApplicationClass.prefs.boneTabletReligion
            tabletType = ApplicationClass.prefs.boneTabletType

            // 정보2
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
                if(selectedTabletName!!.contains("검정")) {
                    imageName = "img_mark2_2"
                }
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
                }else if(tabletReligion == "기독교") {
                    imageName = "img_mark2"
                    if(selectedTabletName!!.contains("검정")) {
                        imageName = "img_mark2_2"
                    }
                }else if(tabletReligion == "불교")
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
                }else if(boneTabletReligion == "기독교") {
                    imageName = "img_mark2"
                    if(selectedTabletName!!.contains("검정")) {
                        imageName = "img_mark2_2"
                    }
                }else if(boneTabletReligion == "불교")
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
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
        val pixel_size_21 = resources.getDimensionPixelSize(R.dimen.pixel_size_21)
        val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
        val pixel_size_27 = resources.getDimensionPixelSize(R.dimen.pixel_size_27)
        val pixel_size_36 = resources.getDimensionPixelSize(R.dimen.pixel_size_36)

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

        val tabletExample = ApplicationClass.prefs.tabletExample

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

            binding.layoutBoneTabletResultUnder.setTextColor(Color.parseColor("#FFD700"))
        }

        var flagUp = false;
        var flagDown = false;
        var minHeight = 630;
        var upHeight = 60

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
                    layoutParams.width = 111
                    layoutParams.height = 510
                    minHeight = Math.min(minHeight, 510)
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
                    flagUp = true

                    layoutBoneTabletResult1.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 465
                    minHeight = Math.min(minHeight, 465)
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
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                tmp2.append(tabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                tmp2.append(tabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                layoutBoneTabletResult1.scaleY = 1.4f
                                tmp2.append(tabletName2)
                            }
                        }
                    }

                    layoutBoneTabletResult1.text = tmp2.toString()
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "천주교" -> {
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        flagUp = true
                        layoutBoneTabletResult1.visibility = View.VISIBLE
                    }else{
                        flagDown = true
                        layoutBoneTabletResult3.visibility = View.VISIBLE
                    }

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 465
                    minHeight = Math.min(minHeight, 465)
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
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        5 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2]).append("　")

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        6 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2] + "" + tabletName2[5])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                    layoutBoneTabletResult1.scaleY = 1.9f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                    layoutBoneTabletResult3.scaleY = 1.9f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        layoutBoneTabletResult1.text = tmp2.toString()
                    }else{
                        layoutBoneTabletResult3.text = tmp2.toString()
                    }
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
                    layoutParams.width = 111
                    layoutParams.height = 615
                    minHeight = Math.min(minHeight, 615)
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
                    flagUp = true
                    flagDown = true

                    println("기독교 진입")

                    layoutBoneTabletResult1.visibility = View.VISIBLE
                    layoutBoneTabletResult1.scaleY = 1.3f

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 400
                    minHeight = Math.min(minHeight, 400)
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
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 360
                                minHeight = Math.min(minHeight, 360)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                tmp2.append(tabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 320
                                minHeight = Math.min(minHeight, 320)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                tmp2.append(tabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.width = 360
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                layoutBoneTabletResult1.scaleY = 1.4f
                                tmp2.append(tabletName2)
                            }
                        }
                    }
                    layoutBoneTabletResult1.text = tmp2.toString()
//                    layoutBoneTabletResult2.text = tmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 510
                    minHeight = Math.min(minHeight, 510)
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
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        flagUp = true
                        layoutBoneTabletResult1.visibility = View.VISIBLE
                    }else{
                        flagDown = true
                        layoutBoneTabletResult3.visibility = View.VISIBLE
                    }
                    println("천주교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 465
                    minHeight = Math.min(minHeight, 465)
                    layoutBoneTabletResult2.layoutParams = layoutParams
//                    layoutBoneTabletResult2.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())

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
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.width = 420
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0]).append("\n").append(tabletName2[1]).append("\n").append(tabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[1]).append("\n").append(tabletName2[2] + "" + tabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.width = 420
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        5 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2]).append("　")

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                        6 -> {
                            if(tabletExample!!.contains("세로")) {
                                tmp2.append(tabletName2[0] + "" + tabletName2[3]).append("\n").append(tabletName2[1] + "" + tabletName2[4]).append("\n").append(tabletName2[2] + "" + tabletName2[5])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                    layoutBoneTabletResult1.scaleY = 1.9f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                    layoutBoneTabletResult3.scaleY = 1.9f
                                }
                                tmp2.append(tabletName2)
                            }
                        }
                    }
//                    layoutBoneTabletResult2.text = tmp.toString()
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        layoutBoneTabletResult1.text = tmp2.toString()
                    }else{
                        layoutBoneTabletResult3.text = tmp2.toString()
                    }
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
                    layoutParams.width = 111
                    layoutParams.height = 510
                    minHeight = Math.min(minHeight, 510)
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[1].toString()

                            boneTmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
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
//                    layoutBoneTabletResult2.text = boneTmp.toString()
                }
                "기독교" -> {
                    flagUp = true

                    layoutBoneTabletResult1.visibility = View.VISIBLE

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 465
                    minHeight = Math.min(minHeight, 465)
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[1].toString()

                            boneTmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
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
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1]).append("\n").append(boneTabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[1]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                layoutBoneTabletResult1.scaleY = 1.4f
                                layoutBoneTabletResult1.letterSpacing = -0.1f
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                    }
                    layoutBoneTabletResult1.text = boneTmp2.toString()
                }
                "천주교" -> {
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        flagUp = true
                        layoutBoneTabletResult1.visibility = View.VISIBLE
                    }else{
                        flagDown = true
                        layoutBoneTabletResult3.visibility = View.VISIBLE
                    }

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 465
                    minHeight = Math.min(minHeight, 465)
                    layoutBoneTabletResult2.layoutParams = layoutParams

                    when (boneName3.length) {
                        2 -> {
                            layoutBoneTabletResult21.visibility = View.VISIBLE
                            layoutBoneTabletResult21.text = boneName3[0].toString()

                            layoutBoneTabletResult29.visibility = View.VISIBLE
                            layoutBoneTabletResult29.text = boneName3[1].toString()

                            boneTmp.append(boneName3[0]).append("\n").append("\n").append(boneName3[1])
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
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1]).append("\n").append(boneTabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[1]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        5 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[3]).append("\n").append(boneTabletName2[1] + "" + boneTabletName2[4]).append("\n").append(boneTabletName2[2]).append("　")

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        6 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[3]).append("\n").append(boneTabletName2[1] + "" + boneTabletName2[4]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[5])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                    layoutBoneTabletResult1.scaleY = 1.9f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                    layoutBoneTabletResult3.scaleY = 1.9f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                    }
//                    layoutBoneTabletResult2.text = boneTmp.toString()
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        layoutBoneTabletResult1.text = boneTmp2.toString()
                    }else{
                        layoutBoneTabletResult3.text = boneTmp2.toString()
                    }
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
                    layoutParams.width = 111
                    layoutParams.height = 615
                    minHeight = Math.min(minHeight, 615)
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
                    flagUp = true
                    flagDown = true

                    println("기독교 진입")

                    layoutBoneTabletResult1.visibility = View.VISIBLE
                    layoutBoneTabletResult1.scaleY = 1.3f

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 400
                    minHeight = Math.min(minHeight, 400)
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
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 360
                                minHeight = Math.min(minHeight, 360)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1]).append("\n").append(boneTabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 320
                                minHeight = Math.min(minHeight, 320)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                layoutBoneTabletResult1.scaleY = 1.1f
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[1]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 360
                                minHeight = Math.min(minHeight, 360)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                layoutBoneTabletResult1.scaleY = 1.4f
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                    }
                    layoutBoneTabletResult1.text = boneTmp2.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 510
                    minHeight = Math.min(minHeight, 510)
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
                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        flagUp = true
                        layoutBoneTabletResult1.visibility = View.VISIBLE
                    }else{
                        flagDown = true
                        layoutBoneTabletResult3.visibility = View.VISIBLE
                    }
                    println("천주교 진입")

                    val layoutParams = layoutBoneTabletResult2.layoutParams
                    layoutParams.width = 111
                    layoutParams.height = 465
                    minHeight = Math.min(minHeight, 460)
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
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        3 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0]).append("\n").append(boneTabletName2[1]).append("\n").append(boneTabletName2[2])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.scaleY = 1.1f
                                }else{
                                    layoutBoneTabletResult3.scaleY = 1.1f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        4 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[1]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[3])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 420
                                minHeight = Math.min(minHeight, 420)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 80)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_27.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        5 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[3]).append("\n").append(boneTabletName2[1] + "" + boneTabletName2[4]).append("\n").append(boneTabletName2[2]).append("　")

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult1.scaleY = 1.4f
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                                    layoutBoneTabletResult3.scaleY = 1.4f
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                        6 -> {
                            if(tabletExample!!.contains("세로")) {
                                boneTmp2.append(boneTabletName2[0] + "" + boneTabletName2[3]).append("\n").append(boneTabletName2[1] + "" + boneTabletName2[4]).append("\n").append(boneTabletName2[2] + "" + boneTabletName2[5])

                                val layoutParams = layoutBoneTabletResult2.layoutParams
                                layoutParams.width = 111
                                layoutParams.height = 380
                                minHeight = Math.min(minHeight, 380)
                                layoutBoneTabletResult2.layoutParams = layoutParams

                                upHeight = Math.max(upHeight, 120)
                            }else{
                                if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                                    layoutBoneTabletResult1.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult1.letterSpacing = -0.1f
                                    layoutBoneTabletResult1.scaleY = 1.9f
                                }else{
                                    layoutBoneTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                                    layoutBoneTabletResult3.letterSpacing = -0.1f
                                    layoutBoneTabletResult3.scaleY = 1.9f
                                }
                                boneTmp2.append(boneTabletName2)
                            }
                        }
                    }
//                    boneTmp2.append(boneTabletName2)

                    if(tabletExample!!.contains("위") || tabletExample!!.contains("세로")) {
                        layoutBoneTabletResult1.text = boneTmp2.toString()
                    }else{
                        layoutBoneTabletResult3.text = boneTmp2.toString()
                    }
                }
            }
        }
        else if(boneTabletType == "문구"){

        }

        if(!tabletType.contains("본관") && !boneTabletType.contains("본관")){
            if(flagUp && flagDown){
                binding.layoutBoneTabletResult1.visibility = View.VISIBLE
                binding.layoutBoneTabletResult3.visibility = View.VISIBLE
                binding.layoutBoneTablet2Result1.visibility = View.VISIBLE
                binding.layoutBoneTablet2Result3.visibility = View.VISIBLE

                // 레이아웃 파일에서 뷰 가져오기
                val layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
                // 현재 레이아웃 파라미터를 가져옵니다.
                val params = layoutBoneTabletResult2.layoutParams
                // 뷰의 높이를 픽셀 단위로 설정
                params.height = 400
                layoutBoneTabletResult2.layoutParams = params

                val layoutBoneTablet2Result2 = binding.layoutBoneTablet2Result2
                val params2 = layoutBoneTablet2Result2.layoutParams
                params2.height = 400
                minHeight = Math.min(minHeight, 400)
                layoutBoneTablet2Result2.layoutParams = params2
            }else if(flagDown){
                binding.layoutBoneTabletResult3.visibility = View.VISIBLE
                binding.layoutBoneTablet2Result3.visibility = View.VISIBLE

                // 레이아웃 파일에서 뷰 가져오기
                val layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
                // 현재 레이아웃 파라미터를 가져옵니다.
                val params = layoutBoneTabletResult2.layoutParams
                // 뷰의 높이를 픽셀 단위로 설정
                params.height = 465
                layoutBoneTabletResult2.layoutParams = params

                val layoutBoneTablet2Result2 = binding.layoutBoneTablet2Result2
                val params2 = layoutBoneTablet2Result2.layoutParams
                params2.height = 465
                minHeight = Math.min(minHeight, 465)
                layoutBoneTablet2Result2.layoutParams = params2
            }else if(flagUp){
                binding.layoutBoneTabletResult1.visibility = View.VISIBLE
                binding.layoutBoneTablet2Result1.visibility = View.VISIBLE

                // 레이아웃 파일에서 뷰 가져오기
                val layoutBoneTabletResult2 = binding.layoutBoneTabletResult2
                // 현재 레이아웃 파라미터를 가져옵니다.
                val params = layoutBoneTabletResult2.layoutParams
                // 뷰의 높이를 픽셀 단위로 설정
                params.height = 465

                val layoutBoneTablet2Result2 = binding.layoutBoneTablet2Result2
                val params2 = layoutBoneTablet2Result2.layoutParams
                params2.height = 465
                minHeight = Math.min(minHeight, 465)

//                if(tabletExample!!.contains("세로")) {
//
//                }

                layoutBoneTabletResult2.layoutParams = params
                layoutBoneTablet2Result2.layoutParams = params2
            }
        }

        val params1 = binding.layoutBoneTabletResult2.layoutParams
        params1.height = minHeight
        val params2 = binding.layoutBoneTablet2Result2.layoutParams
        params2.height = minHeight
        binding.layoutBoneTabletResult2.layoutParams = params1
        binding.layoutBoneTablet2Result2.layoutParams = params2

        val upParams1 = binding.layoutBoneTabletResult1.layoutParams
        upParams1.height = upHeight
        val upParams2 = binding.layoutBoneTablet2Result1.layoutParams
        upParams2.height = upHeight
        binding.layoutBoneTabletResult1.layoutParams = upParams1
        binding.layoutBoneTablet2Result1.layoutParams = upParams2

        if(tabletType == "기독교(본관)" && boneTabletType == "기독교(본관)"){
            binding.layoutBoneTabletResult3.visibility = View.GONE
            binding.layoutBoneTablet2Result3.visibility = View.GONE
            binding.layoutBoneTabletResultUnder.visibility = View.VISIBLE

            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            binding.layoutBoneTabletResultUnder.typeface = hyhaeso
            binding.layoutBoneTabletResultUnder.text = "召天"
        }
    }
}