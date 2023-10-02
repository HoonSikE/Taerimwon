package com.example.taerimwon.ui.result

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Typeface
import android.net.Uri
import android.os.Handler
import android.os.Process
import android.text.InputFilter
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    val authViewModel: AuthViewModel by viewModels()
    private val REQUEST_CODE_STORAGE_PERMISSION = 101 // 권한 요청 코드
    private lateinit var selectedUrnType: String
    private lateinit var selectedTabletType: String

    private lateinit var msg: String
    private lateinit var msgBitmap: Bitmap
    private lateinit var urnBitmap: Bitmap
    private lateinit var urnContentBitmap: Bitmap
    private lateinit var boneBitmap: Bitmap
    private lateinit var boneContentBitmap: Bitmap
    private lateinit var tabletBitmap: Bitmap
    private lateinit var tabletContentBitmap: Bitmap

    override fun init() {
        initData()
        setImage()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()

        if(selectedUrnType != "선택안함") {
            setUrnData()
            if(selectedUrnType.contains("합골함")){
                if(selectedUrnType.contains("합골함1")){
                    setBone1Data()
                    binding.layoutUrnContent.visibility = View.VISIBLE
                    binding.layoutBoneContent.visibility = View.VISIBLE
                    binding.layoutBoneResultImage.visibility = View.VISIBLE
                    binding.layoutBone1.visibility = View.VISIBLE
                }else if(selectedUrnType.contains("합골함2")){
                    setBone2Data()
                    binding.layoutBone2Content.visibility = View.VISIBLE
                    binding.layoutBone2ResultImage.visibility = View.VISIBLE
                    binding.layoutBone2.visibility = View.VISIBLE
                }
            }else{
                binding.layoutUrnContent.visibility = View.VISIBLE
                binding.layoutUrnResultImage.visibility = View.VISIBLE
                binding.layoutUrn.visibility = View.VISIBLE
            }
        }

        if(selectedTabletType != "선택안함") {
            setTabletData()
            binding.layoutTabletContent.visibility = View.VISIBLE
            binding.layoutTabletResultImage.visibility = View.VISIBLE
            binding.layoutTablet.visibility = View.VISIBLE
        }

        setMsg()
        authViewModel.getBlackList()
        if(!ApplicationClass.prefs.authenticated)
            findNavController().navigate(R.id.action_resultFragment_to_phoneAuthFragment)
    }
    private fun setUrnMark() {
        if(ApplicationClass.prefs.name1 == "")
            return
        // 이미지 이름을 문자열로 정의합니다.
        val engraveTypePosition = ApplicationClass.prefs.engraveTypePosition
        var imageName = "img_mark" + (engraveTypePosition + 1)

        if(engraveTypePosition == 4 || engraveTypePosition == 5){
            if(ApplicationClass.prefs.engraveType2Position == 0)
                imageName = "img_mark5"
            else if(ApplicationClass.prefs.engraveType2Position == 1)
                imageName = "img_mark5_2"
        }
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageUrnResult21.setImageResource(imageResource)
    }
    private fun setUrnData() {
        // 내용이 없으면 빈 값
        if(ApplicationClass.prefs.name1 == "") {
            binding.imageUrnImage.visibility = View.INVISIBLE
            return
        } else {
            binding.imageUrnImage.visibility = View.VISIBLE
            setUrnMark()
        }

        val pixel_size_16 = resources.getDimensionPixelSize(R.dimen.pixel_size_16)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
        val pixel_size_23 = resources.getDimensionPixelSize(R.dimen.pixel_size_23)
        val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
        val pixel_size_30 = resources.getDimensionPixelSize(R.dimen.pixel_size_30)
        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)
        val pixel_size_50 = resources.getDimensionPixelSize(R.dimen.pixel_size_50)
        val pixel_size_80 = resources.getDimensionPixelSize(R.dimen.pixel_size_80)
        val pixel_size_90 = resources.getDimensionPixelSize(R.dimen.pixel_size_90)
        val pixel_size_100 = resources.getDimensionPixelSize(R.dimen.pixel_size_100)
        val pixel_size_145 = resources.getDimensionPixelSize(R.dimen.pixel_size_145)
        val pixel_size_170 = resources.getDimensionPixelSize(R.dimen.pixel_size_170)

        // 이름
        val name1 = ApplicationClass.prefs.name1.toString()
        val name2 = ApplicationClass.prefs.name2.toString()
        val tmp = StringBuilder()

        val engraveType = ApplicationClass.prefs.engraveType
        val engraveType2 = ApplicationClass.prefs.engraveType2

        binding.layoutUrnResult22.visibility = View.VISIBLE
        // 이름
        val layoutUrnResult221 = binding.layoutUrnResult221

        if((engraveType == "일반" && engraveType2 == "기본")
            || (engraveType == "기독교" && engraveType2 == "직분X")
            || (engraveType == "불교" && engraveType2 == "기본")
            || (engraveType == "천주교" && engraveType2 == "세례명X")
            || (engraveType == "원불교")){

            when (name1.length) {
                2 -> {
                    tmp.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])

                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }else if(engraveType2 == "형제"){
            binding.layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp.append(name1[0]).append("\n").append("\n").append(name1[1])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }else if(engraveType == "기독교" && engraveType2 == "기본"
            || engraveType == "불교" && engraveType2 == "법명"
            || engraveType == "순복음"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp.append(name1[0]).append("\n").append("\n").append(name1[1])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
            layoutUrnResult220.visibility = View.VISIBLE
            when (name2.length) {
                4 -> {
                    layoutUrnResult220.width = pixel_size_100
                    layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutUrnResult220.letterSpacing = -0.2f
                }
            }
            layoutUrnResult220.text = name2
        } else if(engraveType == "천주교" && engraveType2 == "기본"){
            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp.append(name1[0]).append("\n").append("\n").append(name1[1])
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.width = pixel_size_100
            when (name2.length) {
                2, 3 -> {
                    layoutUrnResult222.width = pixel_size_90
                    layoutUrnResult222.letterSpacing = -0.15f
                }
                4 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutUrnResult222.letterSpacing = -0.2f
                }
                5 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                    layoutUrnResult222.letterSpacing = -0.2f
                }
                6 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_16.toFloat())
                    layoutUrnResult222.letterSpacing = -0.2f
                }
            }
            layoutUrnResult222.text = name2
        }else if(engraveType == "SGI"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutUrnResult220.letterSpacing = 0f

            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult222.typeface = hyhaeso
            layoutUrnResult222.setLineSpacing(0f, 1.0f)

            when (name1.length) {
                2 -> {
                    tmp.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }else if(engraveType == "묘법"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "妙法"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult220.typeface = hyhaeso
            layoutUrnResult220.letterSpacing = 0f

            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.typeface = hyhaeso

            when (name1.length) {
                2 -> {
                    tmp.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }

        // 출생일
        when (engraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutUrnResult111.visibility = View.VISIBLE
                binding.layoutUrnResult112.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE
                binding.layoutUrnResult112.text = "出生"
            }
            "천주교" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE
                binding.layoutUrnResult112.text = "出生"
            }
        }

        val date1 = ApplicationClass.prefs.date1.toString()

        if(date1.length == 10){
            binding.layoutUrnResult121.text = date1[0].toString()
            binding.layoutUrnResult122.text = date1[1].toString()
            binding.layoutUrnResult123.text = date1[2].toString()
            binding.layoutUrnResult124.text = date1[3].toString()
            binding.layoutUrnResult141.text = date1[5].toString()
            binding.layoutUrnResult142.text = date1[6].toString()
            binding.layoutUrnResult161.text = date1[8].toString()
            binding.layoutUrnResult162.text = date1[9].toString()
        }
        val date1Type = ApplicationClass.prefs.date1Type
        if(date1Type == "양력")
            binding.layoutUrnResult17.text = "陽"
        else if(date1Type == "음력")
            binding.layoutUrnResult17.text = "陰"

        // 사망일
        // 출생일
        when (engraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutUrnResult311.visibility = View.VISIBLE
                binding.layoutUrnResult312.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE
                binding.layoutUrnResult312.text = "召天"
            }
            "천주교" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE
                binding.layoutUrnResult312.text = "善終"
            }
        }

        val date2 = ApplicationClass.prefs.date2.toString()
        if(date2.length == 10){
            binding.layoutUrnResult321.text = date2[0].toString()
            binding.layoutUrnResult322.text = date2[1].toString()
            binding.layoutUrnResult323.text = date2[2].toString()
            binding.layoutUrnResult324.text = date2[3].toString()
            binding.layoutUrnResult341.text = date2[5].toString()
            binding.layoutUrnResult342.text = date2[6].toString()
            binding.layoutUrnResult361.text = date2[8].toString()
            binding.layoutUrnResult362.text = date2[9].toString()
        }

        val date2Type = ApplicationClass.prefs.date2Type
        if(date2Type == "양력")
            binding.layoutUrnResult37.text = "陽"
        else if(date2Type == "음력")
            binding.layoutUrnResult37.text = "陰"
    }
    private fun setBone1Mark() {
        if(ApplicationClass.prefs.boneName1 == "")
            return
        // 이미지 이름을 문자열로 정의합니다.
        val boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
        var imageName = "img_mark" + (boneEngraveTypePosition + 1)

        if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
            if(ApplicationClass.prefs.boneEngraveType2Position == 0)
                imageName = "img_mark5"
            else if(ApplicationClass.prefs.boneEngraveType2Position == 1)
                imageName = "img_mark5_2"
        }
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageBoneResult21.setImageResource(imageResource)
    }
    private fun setBone1Data() {
        // 내용이 없으면 빈 값
        // name1 / boneName1
        if(ApplicationClass.prefs.boneSex == "여성"){
            if(ApplicationClass.prefs.name1 == "")
                binding.imageBone1Image1.visibility = View.INVISIBLE

            if(ApplicationClass.prefs.boneName1 == "") {
                binding.imageBone1Image2.visibility = View.INVISIBLE
            }else {
                setBone1Mark()
            }
        }
        // boneName1 / name1
        else if(ApplicationClass.prefs.boneSex == "남성"){
            if(ApplicationClass.prefs.boneName1 == "") {
                binding.imageBone1Image1.visibility = View.INVISIBLE
            } else {
                setBone1Mark()
            }

            if(ApplicationClass.prefs.name1 == "")
                binding.imageBone1Image2.visibility = View.INVISIBLE
        }

        val pixel_size_16 = resources.getDimensionPixelSize(R.dimen.pixel_size_16)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
        val pixel_size_23 = resources.getDimensionPixelSize(R.dimen.pixel_size_23)
        val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
        val pixel_size_30 = resources.getDimensionPixelSize(R.dimen.pixel_size_30)
        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)
        val pixel_size_50 = resources.getDimensionPixelSize(R.dimen.pixel_size_50)
        val pixel_size_80 = resources.getDimensionPixelSize(R.dimen.pixel_size_80)
        val pixel_size_90 = resources.getDimensionPixelSize(R.dimen.pixel_size_90)
        val pixel_size_100 = resources.getDimensionPixelSize(R.dimen.pixel_size_100)
        val pixel_size_145 = resources.getDimensionPixelSize(R.dimen.pixel_size_145)
        val pixel_size_170 = resources.getDimensionPixelSize(R.dimen.pixel_size_170)

        // 이름
        val boneName1 = ApplicationClass.prefs.boneName1.toString()
        val boneName2 = ApplicationClass.prefs.boneName2.toString()
        val tmp = StringBuilder()

        val boneEngraveType = ApplicationClass.prefs.boneEngraveType
        val boneEngraveType2 = ApplicationClass.prefs.boneEngraveType2


        binding.layoutBoneResult22.visibility = View.VISIBLE
        // 이름
        val layoutBoneResult221 = binding.layoutBoneResult221

        if((boneEngraveType == "일반" && boneEngraveType2 == "기본")
            || (boneEngraveType == "기독교" && boneEngraveType2 == "직분X")
            || (boneEngraveType == "불교" && boneEngraveType2 == "기본")
            || (boneEngraveType == "천주교" && boneEngraveType2 == "세례명X")
            || (boneEngraveType == "원불교")){

            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])

                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp.toString()
        }else if(boneEngraveType2 == "형제"){
            binding.layoutUrnResult222.visibility = View.VISIBLE
            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp.toString()
        }else if(boneEngraveType == "기독교" && boneEngraveType2 == "기본"
            || boneEngraveType == "불교" && boneEngraveType2 == "법명"
            || boneEngraveType == "순복음"){
            val layoutBoneResult220 = binding.layoutBoneResult220
            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp.toString()
            layoutBoneResult220.visibility = View.VISIBLE
            when (boneName2.length) {
                4 -> {
                    layoutBoneResult220.width = pixel_size_100
                    layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutBoneResult220.letterSpacing = -0.2f
                }
            }
            layoutBoneResult220.text = boneName2
        } else if(boneEngraveType == "천주교" && boneEngraveType2 == "기본"){
            val layoutBoneResult222 = binding.layoutBoneResult222
            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp.toString()
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.width = pixel_size_100
            when (boneName2.length) {
                2, 3 -> {
                    layoutBoneResult222.width = pixel_size_90
                    layoutBoneResult222.letterSpacing = -0.15f
                }
                4 -> {
                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutBoneResult222.letterSpacing = -0.2f
                }
                5 -> {
                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                    layoutBoneResult222.letterSpacing = -0.2f
                }
                6 -> {
                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_16.toFloat())
                    layoutBoneResult222.letterSpacing = -0.2f
                }
            }
            layoutBoneResult222.text = boneName2
        }else if(boneEngraveType == "SGI"){
            val layoutBoneResult220 = binding.layoutBoneResult220
            layoutBoneResult220.visibility = View.VISIBLE
            layoutBoneResult220.width = pixel_size_80
            layoutBoneResult220.text = "SGI"
            layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutBoneResult220.letterSpacing = 0f

            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            val layoutBoneResult222 = binding.layoutBoneResult222
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutBoneResult222.typeface = hyhaeso
            layoutBoneResult222.setLineSpacing(0f, 1.0f)

            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutBoneResult221.text = tmp.toString()
        }else if(boneEngraveType == "묘법"){
            val layoutBoneResult220 = binding.layoutBoneResult220
            layoutBoneResult220.visibility = View.VISIBLE
            layoutBoneResult220.width = pixel_size_80
            layoutBoneResult220.text = "妙法"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutBoneResult220.typeface = hyhaeso
            layoutBoneResult220.letterSpacing = 0f

            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            val layoutBoneResult222 = binding.layoutBoneResult222
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.typeface = hyhaeso

            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutBoneResult221.text = tmp.toString()
        }

        // 출생일
        when (boneEngraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutBoneResult111.visibility = View.VISIBLE
                binding.layoutBoneResult112.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                binding.layoutBoneResult111.visibility = View.GONE
                binding.layoutBoneResult112.visibility = View.VISIBLE
                binding.layoutBoneResult112.text = "出生"
            }
            "천주교" -> {
                binding.layoutBoneResult111.visibility = View.GONE
                binding.layoutBoneResult112.visibility = View.VISIBLE
                binding.layoutBoneResult112.text = "出生"
            }
        }

        val boneDate1 = ApplicationClass.prefs.boneDate1.toString()

        if(boneDate1.length == 10){
            binding.layoutBoneResult121.text = boneDate1[0].toString()
            binding.layoutBoneResult122.text = boneDate1[1].toString()
            binding.layoutBoneResult123.text = boneDate1[2].toString()
            binding.layoutBoneResult124.text = boneDate1[3].toString()
            binding.layoutBoneResult141.text = boneDate1[5].toString()
            binding.layoutBoneResult142.text = boneDate1[6].toString()
            binding.layoutBoneResult161.text = boneDate1[8].toString()
            binding.layoutBoneResult162.text = boneDate1[9].toString()
        }
        val boneDate1Type = ApplicationClass.prefs.boneDate1Type
        if(boneDate1Type == "양력")
            binding.layoutBoneResult17.text = "陽"
        else if(boneDate1Type == "음력")
            binding.layoutBoneResult17.text = "陰"

        // 사망일
        // 출생일
        when (boneEngraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutBoneResult311.visibility = View.VISIBLE
                binding.layoutBoneResult312.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                binding.layoutBoneResult311.visibility = View.GONE
                binding.layoutBoneResult312.visibility = View.VISIBLE
                binding.layoutBoneResult312.text = "召天"
            }
            "천주교" -> {
                binding.layoutBoneResult311.visibility = View.GONE
                binding.layoutBoneResult312.visibility = View.VISIBLE
                binding.layoutBoneResult312.text = "善終"
            }
        }

        val boneDate2 = ApplicationClass.prefs.boneDate2.toString()
        if(boneDate2.length == 10){
            binding.layoutBoneResult321.text = boneDate2[0].toString()
            binding.layoutBoneResult322.text = boneDate2[1].toString()
            binding.layoutBoneResult323.text = boneDate2[2].toString()
            binding.layoutBoneResult324.text = boneDate2[3].toString()
            binding.layoutBoneResult341.text = boneDate2[5].toString()
            binding.layoutBoneResult342.text = boneDate2[6].toString()
            binding.layoutBoneResult361.text = boneDate2[8].toString()
            binding.layoutBoneResult362.text = boneDate2[9].toString()
        }

        val boneDate2Type = ApplicationClass.prefs.boneDate2Type
        if(boneDate2Type == "양력")
            binding.layoutBoneResult37.text = "陽"
        else if(boneDate2Type == "음력")
            binding.layoutBoneResult37.text = "陰"
    }
    private fun setBone2Mark() {
        // 정보1
        var name1 = ApplicationClass.prefs.name1
        var engraveTypePosition = ApplicationClass.prefs.engraveTypePosition

        // 정보2
        var boneName1 = ApplicationClass.prefs.boneName1
        var boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition

        // 추가 정보 / 기존 정보
        if(engraveTypePosition == boneEngraveTypePosition && !(name1 == "" || boneName1 == "")) {
            binding.imageBone2Result21.visibility = View.VISIBLE
            binding.imageBone2Result211.visibility = View.GONE
            binding.imageBone2Result212.visibility = View.GONE

            var imageName = "img_mark" + (engraveTypePosition + 1)

            if(engraveTypePosition == 4 || engraveTypePosition == 5){
                if(ApplicationClass.prefs.engraveType2Position == 0)
                    imageName = "img_mark5"
                else if(ApplicationClass.prefs.engraveType2Position == 1)
                    imageName = "img_mark5_2"
            }
            val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
            binding.imageBone2Result21.setImageResource(imageResource)
            return
        }else if(ApplicationClass.prefs.boneSex == "남성"){
            // 정보1
            name1 = ApplicationClass.prefs.boneName1
            engraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition

            // 정보2
            boneName1 = ApplicationClass.prefs.name1
            boneEngraveTypePosition = ApplicationClass.prefs.engraveTypePosition
        }
        // 기존 정보 / 추가 정보
        else if(ApplicationClass.prefs.boneSex == "여성"){
        }

        if(name1 != ""){
            // 이미지 이름을 문자열로 정의합니다.
            var imageName1 = "img_mark" + (engraveTypePosition + 1)

            if(engraveTypePosition == 4 || engraveTypePosition == 5){
                if(ApplicationClass.prefs.engraveType2Position == 0)
                    imageName1 = "img_mark5"
                else if(ApplicationClass.prefs.engraveType2Position == 1)
                    imageName1 = "img_mark5_2"
            }
            val imageResource1 = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            binding.imageBone2Result211.setImageResource(imageResource1)
        }

        if(boneName1 != ""){
            // 이미지 이름을 문자열로 정의합니다.
            var imageName2 = "img_mark" + (boneEngraveTypePosition + 1)

            if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
                if(ApplicationClass.prefs.boneEngraveType2Position == 0)
                    imageName2 = "img_mark5"
                else if(ApplicationClass.prefs.boneEngraveType2Position == 1)
                    imageName2 = "img_mark5_2"
            }
            val imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            binding.imageBone2Result212.setImageResource(imageResource)
        }
    }
    private fun setBone2Data() {
        // 정보1
        var name1 = ApplicationClass.prefs.name1.toString()
        var name2 = ApplicationClass.prefs.name2.toString()
        val tmp1 = StringBuilder()

        var engraveType = ApplicationClass.prefs.engraveType
        var engraveType2 = ApplicationClass.prefs.engraveType2

        var date1 = ApplicationClass.prefs.date1.toString()
        var date1Type = ApplicationClass.prefs.date1Type
        var date2 = ApplicationClass.prefs.date1.toString()
        var date2Type = ApplicationClass.prefs.date2Type

        // 정보2
        var boneName1 = ApplicationClass.prefs.boneName1.toString()
        var boneName2 = ApplicationClass.prefs.boneName2.toString()
        val tmp2 = StringBuilder()

        var boneEngraveType = ApplicationClass.prefs.boneEngraveType
        var boneEngraveType2 = ApplicationClass.prefs.boneEngraveType2

        var boneDate1 = ApplicationClass.prefs.boneDate1.toString()
        var boneDate1Type = ApplicationClass.prefs.boneDate1Type
        var boneDate2 = ApplicationClass.prefs.boneDate1.toString()
        var boneDate2Type = ApplicationClass.prefs.boneDate2Type

        // 정보1
        // 이름
        var layoutUrnResult221 = binding.layoutBone21Result221
        // 위 글자
        var layoutUrnResult220 = binding.layoutBone21Result220
        // 아래 글자
        var layoutUrnResult222 = binding.layoutBone21Result222
        // 좌
        var layoutUrnResult111 = binding.layoutBone21Result111
        var layoutUrnResult112 = binding.layoutBone21Result112
        var layoutUrnResult121 = binding.layoutBone21Result121
        var layoutUrnResult122 = binding.layoutBone21Result122
        var layoutUrnResult123 = binding.layoutBone21Result123
        var layoutUrnResult124 = binding.layoutBone21Result124
        var layoutUrnResult141 = binding.layoutBone21Result141
        var layoutUrnResult142 = binding.layoutBone21Result142
        var layoutUrnResult161 = binding.layoutBone21Result161
        var layoutUrnResult162 = binding.layoutBone21Result162
        var layoutUrnResult17 = binding.layoutBone21Result17
        // 우
        var layoutUrnResult311 = binding.layoutBone21Result311
        var layoutUrnResult312 = binding.layoutBone21Result312
        var layoutUrnResult321 = binding.layoutBone21Result321
        var layoutUrnResult322 = binding.layoutBone21Result322
        var layoutUrnResult323 = binding.layoutBone21Result323
        var layoutUrnResult324 = binding.layoutBone21Result324
        var layoutUrnResult341 = binding.layoutBone21Result341
        var layoutUrnResult342 = binding.layoutBone21Result342
        var layoutUrnResult361 = binding.layoutBone21Result361
        var layoutUrnResult362 = binding.layoutBone21Result362
        var layoutUrnResult37 = binding.layoutBone21Result37

        // 정보2
        // 이름
        var layoutBoneResult221 = binding.layoutBone22Result221
        // 위 글자
        var layoutBoneResult220 = binding.layoutBone22Result220
        // 아래 글자
        var layoutBoneResult222 = binding.layoutBone22Result222
        // 좌
        var layoutBoneResult111 = binding.layoutBone22Result111
        var layoutBoneResult112 = binding.layoutBone22Result112
        var layoutBoneResult121 = binding.layoutBone22Result121
        var layoutBoneResult122 = binding.layoutBone22Result122
        var layoutBoneResult123 = binding.layoutBone22Result123
        var layoutBoneResult124 = binding.layoutBone22Result124
        var layoutBoneResult141 = binding.layoutBone22Result141
        var layoutBoneResult142 = binding.layoutBone22Result142
        var layoutBoneResult161 = binding.layoutBone22Result161
        var layoutBoneResult162 = binding.layoutBone22Result162
        var layoutBoneResult17 = binding.layoutBone22Result17
        // 우
        var layoutBoneResult311 = binding.layoutBone22Result311
        var layoutBoneResult312 = binding.layoutBone22Result312
        var layoutBoneResult321 = binding.layoutBone22Result321
        var layoutBoneResult322 = binding.layoutBone22Result322
        var layoutBoneResult323 = binding.layoutBone22Result323
        var layoutBoneResult324 = binding.layoutBone22Result324
        var layoutBoneResult341 = binding.layoutBone22Result341
        var layoutBoneResult342 = binding.layoutBone22Result342
        var layoutBoneResult361 = binding.layoutBone22Result361
        var layoutBoneResult362 = binding.layoutBone22Result362
        var layoutBoneResult37 = binding.layoutBone22Result37
        // 폰트
        val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)

        // 추가 정보 / 기존 정보
        if(ApplicationClass.prefs.boneSex == "남성"){
            // 정보1
            name1 = ApplicationClass.prefs.boneName1.toString()
            name2 = ApplicationClass.prefs.boneName2.toString()

            engraveType = ApplicationClass.prefs.boneEngraveType
            engraveType2 = ApplicationClass.prefs.boneEngraveType2

            date1 = ApplicationClass.prefs.boneDate1.toString()
            date1Type = ApplicationClass.prefs.boneDate1Type
            date2 = ApplicationClass.prefs.boneDate1.toString()
            date2Type = ApplicationClass.prefs.boneDate2Type

            // 정보2
            boneName1 = ApplicationClass.prefs.name1.toString()
            boneName2 = ApplicationClass.prefs.name2.toString()

            boneEngraveType = ApplicationClass.prefs.engraveType
            boneEngraveType2 = ApplicationClass.prefs.engraveType2

            boneDate1 = ApplicationClass.prefs.date1.toString()
            boneDate1Type = ApplicationClass.prefs.date1Type
            boneDate2 = ApplicationClass.prefs.date1.toString()
            boneDate2Type = ApplicationClass.prefs.date2Type
        }
        // 기존 정보 / 추가 정보
        if(name1 == "") {
            binding.layoutBone21Result1.visibility = View.INVISIBLE
            binding.layoutBone21Result3.visibility = View.INVISIBLE
            binding.layoutBone21Result22.visibility = View.INVISIBLE
        }
        if(boneName1 == "") {
            binding.layoutBone22Result1.visibility = View.INVISIBLE
            binding.layoutBone22Result3.visibility = View.INVISIBLE
            binding.layoutBone22Result22.visibility = View.INVISIBLE
        }
        setBone2Mark()

        val pixel_size_16 = resources.getDimensionPixelSize(R.dimen.pixel_size_16)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
        val pixel_size_23 = resources.getDimensionPixelSize(R.dimen.pixel_size_23)
        val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
        val pixel_size_30 = resources.getDimensionPixelSize(R.dimen.pixel_size_30)
        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)
        val pixel_size_50 = resources.getDimensionPixelSize(R.dimen.pixel_size_50)
        val pixel_size_80 = resources.getDimensionPixelSize(R.dimen.pixel_size_80)
        val pixel_size_90 = resources.getDimensionPixelSize(R.dimen.pixel_size_90)
        val pixel_size_100 = resources.getDimensionPixelSize(R.dimen.pixel_size_100)
        val pixel_size_145 = resources.getDimensionPixelSize(R.dimen.pixel_size_145)
        val pixel_size_170 = resources.getDimensionPixelSize(R.dimen.pixel_size_170)

        /**정보1*/
        if((engraveType == "일반" && engraveType2 == "기본")
            || (engraveType == "기독교" && engraveType2 == "직분X")
            || (engraveType == "불교" && engraveType2 == "기본")
            || (engraveType == "천주교" && engraveType2 == "세례명X")
            || (engraveType == "원불교")){

            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])

                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }else if(engraveType2 == "형제"){
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }else if(engraveType == "기독교" && engraveType2 == "기본"
            || engraveType == "불교" && engraveType2 == "법명"
            || engraveType == "순복음"){
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
            layoutUrnResult220.visibility = View.VISIBLE
            when (name2.length) {
                4 -> {
                    layoutUrnResult220.width = pixel_size_100
                    layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutUrnResult220.letterSpacing = -0.2f
                }
            }
            layoutUrnResult220.text = name2
        } else if(engraveType == "천주교" && engraveType2 == "기본"){
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.width = pixel_size_100
            when (name2.length) {
                2, 3 -> {
                    layoutUrnResult222.width = pixel_size_90
                    layoutUrnResult222.letterSpacing = -0.15f
                }
                4 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutUrnResult222.letterSpacing = -0.2f
                }
                5 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                    layoutUrnResult222.letterSpacing = -0.2f
                }
                6 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_16.toFloat())
                    layoutUrnResult222.letterSpacing = -0.2f
                }
            }
            layoutUrnResult222.text = name2
        }else if(engraveType == "SGI"){
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutUrnResult220.letterSpacing = 0f

            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.typeface = hyhaeso
            layoutUrnResult222.setLineSpacing(0f, 1.0f)

            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }else if(engraveType == "묘법"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "妙法"
            layoutUrnResult220.typeface = hyhaeso
            layoutUrnResult220.letterSpacing = 0f

            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.typeface = hyhaeso

            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }

        // 출생일
        when (engraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                layoutUrnResult111.visibility = View.VISIBLE
                layoutUrnResult112.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                layoutUrnResult111.visibility = View.GONE
                layoutUrnResult112.visibility = View.VISIBLE
                layoutUrnResult112.text = "出生"
            }
            "천주교" -> {
                layoutUrnResult111.visibility = View.GONE
                layoutUrnResult112.visibility = View.VISIBLE
                layoutUrnResult112.text = "出生"
            }
        }

        if(date1.length == 10){
            layoutUrnResult121.text = date1[0].toString()
            layoutUrnResult122.text = date1[1].toString()
            layoutUrnResult123.text = date1[2].toString()
            layoutUrnResult124.text = date1[3].toString()
            layoutUrnResult141.text = date1[5].toString()
            layoutUrnResult142.text = date1[6].toString()
            layoutUrnResult161.text = date1[8].toString()
            layoutUrnResult162.text = date1[9].toString()
        }
        if(date1Type == "양력")
            layoutUrnResult17.text = "陽"
        else if(date1Type == "음력")
            layoutUrnResult17.text = "陰"

        // 사망일
        // 출생일
        when (engraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                layoutUrnResult311.visibility = View.VISIBLE
                layoutUrnResult312.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                layoutUrnResult311.visibility = View.GONE
                layoutUrnResult312.visibility = View.VISIBLE
                layoutUrnResult312.text = "召天"
            }
            "천주교" -> {
                layoutUrnResult311.visibility = View.GONE
                layoutUrnResult312.visibility = View.VISIBLE
                layoutUrnResult312.text = "善終"
            }
        }

        if(date2.length == 10){
            layoutUrnResult321.text = date2[0].toString()
            layoutUrnResult322.text = date2[1].toString()
            layoutUrnResult323.text = date2[2].toString()
            layoutUrnResult324.text = date2[3].toString()
            layoutUrnResult341.text = date2[5].toString()
            layoutUrnResult342.text = date2[6].toString()
            layoutUrnResult361.text = date2[8].toString()
            layoutUrnResult362.text = date2[9].toString()
        }

        if(date2Type == "양력")
            layoutUrnResult37.text = "陽"
        else if(date2Type == "음력")
            layoutUrnResult37.text = "陰"

        /**정보2*/
        if((boneEngraveType == "일반" && boneEngraveType2 == "기본")
            || (boneEngraveType == "기독교" && boneEngraveType2 == "직분X")
            || (boneEngraveType == "불교" && boneEngraveType2 == "기본")
            || (boneEngraveType == "천주교" && boneEngraveType2 == "세례명X")
            || (boneEngraveType == "원불교")){

            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])

                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }else if(boneEngraveType2 == "형제"){
            layoutUrnResult222.visibility = View.VISIBLE
            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }else if(boneEngraveType == "기독교" && boneEngraveType2 == "기본"
            || boneEngraveType == "불교" && boneEngraveType2 == "법명"
            || boneEngraveType == "순복음"){
            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
            layoutBoneResult220.visibility = View.VISIBLE
            when (boneName2.length) {
                4 -> {
                    layoutBoneResult220.width = pixel_size_100
                    layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutBoneResult220.letterSpacing = -0.2f
                }
            }
            layoutBoneResult220.text = boneName2
        } else if(boneEngraveType == "천주교" && boneEngraveType2 == "기본"){
            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutBoneResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutBoneResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.width = pixel_size_100
            when (boneName2.length) {
                2, 3 -> {
                    layoutBoneResult222.width = pixel_size_90
                    layoutBoneResult222.letterSpacing = -0.15f
                }
                4 -> {
                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutBoneResult222.letterSpacing = -0.2f
                }
                5 -> {
                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                    layoutBoneResult222.letterSpacing = -0.2f
                }
                6 -> {
                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_16.toFloat())
                    layoutBoneResult222.letterSpacing = -0.2f
                }
            }
            layoutBoneResult222.text = boneName2
        }else if(boneEngraveType == "SGI"){
            layoutBoneResult220.visibility = View.VISIBLE
            layoutBoneResult220.width = pixel_size_80
            layoutBoneResult220.text = "SGI"
            layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutBoneResult220.letterSpacing = 0f

            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.typeface = hyhaeso
            layoutBoneResult222.setLineSpacing(0f, 1.0f)

            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }else if(boneEngraveType == "묘법"){
            layoutBoneResult220.visibility = View.VISIBLE
            layoutBoneResult220.width = pixel_size_80
            layoutBoneResult220.text = "妙法"
            layoutBoneResult220.typeface = hyhaeso
            layoutBoneResult220.letterSpacing = 0f

            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.typeface = hyhaeso

            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }

        // 출생일
        when (boneEngraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                layoutBoneResult111.visibility = View.VISIBLE
                layoutBoneResult112.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                layoutBoneResult111.visibility = View.GONE
                layoutBoneResult112.visibility = View.VISIBLE
                layoutBoneResult112.text = "出生"
            }
            "천주교" -> {
                layoutBoneResult111.visibility = View.GONE
                layoutBoneResult112.visibility = View.VISIBLE
                layoutBoneResult112.text = "出生"
            }
        }

        if(boneDate1.length == 10){
            layoutBoneResult121.text = boneDate1[0].toString()
            layoutBoneResult122.text = boneDate1[1].toString()
            layoutBoneResult123.text = boneDate1[2].toString()
            layoutBoneResult124.text = boneDate1[3].toString()
            layoutBoneResult141.text = boneDate1[5].toString()
            layoutBoneResult142.text = boneDate1[6].toString()
            layoutBoneResult161.text = boneDate1[8].toString()
            layoutBoneResult162.text = boneDate1[9].toString()
        }
        if(boneDate1Type == "양력")
            layoutBoneResult17.text = "陽"
        else if(boneDate1Type == "음력")
            layoutBoneResult17.text = "陰"

        // 사망일
        // 출생일
        when (boneEngraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                layoutBoneResult311.visibility = View.VISIBLE
                layoutBoneResult312.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                layoutBoneResult311.visibility = View.GONE
                layoutBoneResult312.visibility = View.VISIBLE
                layoutBoneResult312.text = "召天"
            }
            "천주교" -> {
                layoutBoneResult311.visibility = View.GONE
                layoutBoneResult312.visibility = View.VISIBLE
                layoutBoneResult312.text = "善終"
            }
        }

        if(boneDate2.length == 10){
            layoutBoneResult321.text = boneDate2[0].toString()
            layoutBoneResult322.text = boneDate2[1].toString()
            layoutBoneResult323.text = boneDate2[2].toString()
            layoutBoneResult324.text = boneDate2[3].toString()
            layoutBoneResult341.text = boneDate2[5].toString()
            layoutBoneResult342.text = boneDate2[6].toString()
            layoutBoneResult361.text = boneDate2[8].toString()
            layoutBoneResult362.text = boneDate2[9].toString()
        }

        if(boneDate2Type == "양력")
            layoutBoneResult37.text = "陽"
        else if(boneDate2Type == "음력")
            layoutBoneResult37.text = "陰"
    }
    private fun setTableMark() {
        // 이미지 이름을 문자열로 정의합니다.
        val boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
        var imageName = "img_mark" + (boneEngraveTypePosition + 1)

        if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
            if(ApplicationClass.prefs.boneEngraveType2Position == 0)
                imageName = "img_mark5"
            else if(ApplicationClass.prefs.boneEngraveType2Position == 1)
                imageName = "img_mark5_2"
        }
        // 직분, 세례명, 법명
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageBoneResult21.setImageResource(imageResource)
    }
    private fun setTabletData() {
        if (ApplicationClass.prefs.name3 == "") {
            binding.layoutTabletImage.visibility = View.GONE
            return
        } else {
            binding.layoutTabletImage.visibility = View.VISIBLE
//            setTableMark()
        }

        // 이름
        val name1 = ApplicationClass.prefs.name1
    }
    private fun setMsg() {
        msg = "[태림원]" +
                "\n발주자 정보" +
                "\n - 발주자명: " + ApplicationClass.prefs.leaderName +
                "\n - 발주자 전화번호: " + ApplicationClass.prefs.leaderTel +
                "\n - 소속: " + ApplicationClass.prefs.leaderDepartment +
                "\n\n상주 정보" +
                "\n상주명: " + ApplicationClass.prefs.clientName +
                "\n상주 전화번호: " + ApplicationClass.prefs.clientTel +
                "\n\n발주 장소"

        val selectedLocation = ApplicationClass.prefs.selectedLocation
        if(selectedLocation.equals("화장장")){
            msg += "\n - 화장장소: " + ApplicationClass.prefs.cremationArea +
                    "\n - 화장시간: " + ApplicationClass.prefs.cremationTime
        } else if(selectedLocation.equals("장례식장")){
            msg += "\n - 장례식장 명: " + ApplicationClass.prefs.funeralName +
                    "\n - 호실: " + ApplicationClass.prefs.funeralNumber +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.funeralTime
        } else if(selectedLocation.equals("장지")){
            msg += "\n - 장지명: " + ApplicationClass.prefs.burialName +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.burialTime
        }

        msg += "\n\n유골함 종류: " + ApplicationClass.prefs.selectedUrnType

        if(selectedUrnType != "선택안함"){
            msg += "\n\n각인 종류: " + ApplicationClass.prefs.engraveType + "[" + ApplicationClass.prefs.engraveType2 + "]"

            msg += "\n\n고인 정보: " +
                    "\n - 고인명: " + ApplicationClass.prefs.name1 +
                    "\n - 생년월일: " + ApplicationClass.prefs.date1.toString().replace("-", ".") + " (${ApplicationClass.prefs.date1Type})" +
                    "\n - 사망월일: " + ApplicationClass.prefs.date2.toString().replace("-", ".") + " (${ApplicationClass.prefs.date2Type})"

            // 직분, 세례명, 법명
            if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
                msg += "\n - 직분: " + ApplicationClass.prefs.name2
            }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
                msg += "\n - 법명: " + ApplicationClass.prefs.name2
            }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
                msg += "\n - 세례명: " + ApplicationClass.prefs.name2
            }

            // 합골
            if(selectedUrnType.contains("합골")){
                msg += "\n\n========== "

                msg += "\n합골 추가 정보: " +
                        "\n - 각인 종류: " + ApplicationClass.prefs.boneEngraveType + "[" + ApplicationClass.prefs.boneEngraveType2 + "]" +
                        "\n - 고인명: " + ApplicationClass.prefs.boneName1 +
                        "\n - 생년월일: " + ApplicationClass.prefs.boneDate1.toString().replace("-", ".") + " (${ApplicationClass.prefs.boneDate1Type})" +
                        "\n - 사망월일: " + ApplicationClass.prefs.boneDate2.toString().replace("-", ".") + " (${ApplicationClass.prefs.boneDate2Type})"

                // 직분, 세례명, 법명
                if((ApplicationClass.prefs.boneEngraveType == "기독교" || ApplicationClass.prefs.boneEngraveType == "순복음") && (ApplicationClass.prefs.boneEngraveType2 == "기본")) {
                    msg += "\n - 직분: " + ApplicationClass.prefs.boneName2
                }else if(ApplicationClass.prefs.boneEngraveType == "불교" && ApplicationClass.prefs.boneEngraveType2 == "법명") {
                    msg += "\n - 법명: " + ApplicationClass.prefs.boneName2
                }else if(ApplicationClass.prefs.boneEngraveType == "천주교" && ApplicationClass.prefs.boneEngraveType2 == "기본") {
                    msg += "\n - 세례명: " + ApplicationClass.prefs.boneName2
                }
                msg += "\n========== "
            }
        }

        msg += "\n\n - 위패 종류: " + ApplicationClass.prefs.selectedTabletType
        if(selectedTabletType != "선택안함") {
            msg += "\n위패 상세 종류: " + ApplicationClass.prefs.tabletType +
                    "\n - 위패 내용: " + ApplicationClass.prefs.name3
        }

        msg += "\n\n특이사항: " + ApplicationClass.prefs.note

        println(msg)

        binding.textResult.text = msg
    }
    private fun setImage() {
        val handler = Handler()
        val delayMillis = 500 // 1초 (1000 밀리초)

        handler.postDelayed({
            // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
            // 1. XML 레이아웃
            val layoutResultContent = binding.layoutResultContent
            // 2. 레이아웃을 이미지로 변환
            msgBitmap = Bitmap.createBitmap(layoutResultContent.width, layoutResultContent.height, Bitmap.Config.ARGB_8888)
            val canvasMsgBitmap = Canvas(msgBitmap)
            layoutResultContent.draw(canvasMsgBitmap)
            // 유골
            if(selectedUrnType != "선택안함") {
                if(selectedUrnType.contains("합골함")){
                    if(selectedUrnType.contains("합골함1")){
                        // 유골 정보
                        val layoutUrnContent = binding.layoutUrnContent

                        // 2. 레이아웃을 이미지로 변환
                        urnContentBitmap = Bitmap.createBitmap(layoutUrnContent.width, layoutUrnContent.height, Bitmap.Config.ARGB_8888)
                        val canvasUrnContentBitmap = Canvas(urnContentBitmap)
                        layoutUrnContent.draw(canvasUrnContentBitmap)

                        // 합골 추가 정보
                        val layoutBone1Content = binding.layoutBoneContent

                        // 2. 레이아웃을 이미지로 변환
                        boneContentBitmap = Bitmap.createBitmap(layoutBone1Content.width, layoutBone1Content.height, Bitmap.Config.ARGB_8888)
                        val canvasBoneContentBitmap = Canvas(boneContentBitmap)
                        layoutBone1Content.draw(canvasBoneContentBitmap)

                        // name1 / boneName1
                        if(ApplicationClass.prefs.boneSex == "여성"){
                            val imageBone1Image1 = binding.imageBone1Image1
                            imageBone1Image1.setImageBitmap(urnContentBitmap)

                            val imageBone1Image2 = binding.imageBone1Image2
                            imageBone1Image2.setImageBitmap(boneContentBitmap)
                        }
                        // boneName1 / name1
                        else if(ApplicationClass.prefs.boneSex == "남성"){
                            val imageBone1Image1 = binding.imageBone1Image1
                            imageBone1Image1.setImageBitmap(boneContentBitmap)

                            val imageBone1Image2 = binding.imageBone1Image2
                            imageBone1Image2.setImageBitmap(urnContentBitmap)
                        }

                        // 합골 최종 결과
                        val layoutBone1 = binding.layoutBone1

                        boneBitmap = Bitmap.createBitmap(layoutBone1.width, layoutBone1.height, Bitmap.Config.ARGB_8888)
                        val canvasBoneBitmap = Canvas(boneBitmap)
                        layoutBone1.draw(canvasBoneBitmap)

                        val layoutBoneResultImage = binding.layoutBoneResultImage
                        layoutBoneResultImage.setImageBitmap(boneBitmap)
                    }else if(selectedUrnType.contains("합골함2")){
                        // 텍스트를 이미지화
                        // 1. XML 레이아웃
                        val layoutBone2Content = binding.layoutBone2Content

                        // 2. 레이아웃을 이미지로 변환
                        boneContentBitmap = Bitmap.createBitmap(layoutBone2Content.width, layoutBone2Content.height, Bitmap.Config.ARGB_8888)
                        val canvasBoneContentBitmap = Canvas(boneContentBitmap)
                        layoutBone2Content.draw(canvasBoneContentBitmap)

                        val imageBone2Image = binding.imageBone2Image
                        imageBone2Image.setImageBitmap(boneContentBitmap)

                        // 유골 최종 결과
                        val layoutBone2Image = binding.layoutBone2Image

                        boneBitmap = Bitmap.createBitmap(layoutBone2Image.width, layoutBone2Image.height, Bitmap.Config.ARGB_8888)
                        val canvasBone2Bitmap = Canvas(boneBitmap)
                        layoutBone2Image.draw(canvasBone2Bitmap)

                        val layoutBone2ResultImage = binding.layoutBone2ResultImage
                        layoutBone2ResultImage.setImageBitmap(boneBitmap)
                    }
                }else{
                    // 텍스트를 이미지화
                    // 1. XML 레이아웃
                    val layoutUrnContent = binding.layoutUrnContent

                    // 2. 레이아웃을 이미지로 변환
                    urnContentBitmap = Bitmap.createBitmap(layoutUrnContent.width, layoutUrnContent.height, Bitmap.Config.ARGB_8888)
                    val canvasUrnContentBitmap = Canvas(urnContentBitmap)
                    layoutUrnContent.draw(canvasUrnContentBitmap)

                    val imageUrnImage = binding.imageUrnImage
                    imageUrnImage.setImageBitmap(urnContentBitmap)

                    // 유골 최종 결과
                    val layoutUrnImage = binding.layoutUrnImage

                    urnBitmap = Bitmap.createBitmap(layoutUrnImage.width, layoutUrnImage.height, Bitmap.Config.ARGB_8888)
                    val canvasUrnBitmap = Canvas(urnBitmap)
                    layoutUrnImage.draw(canvasUrnBitmap)

                    val layoutUrnResultImage = binding.layoutUrnResultImage
                    layoutUrnResultImage.setImageBitmap(urnBitmap)
                }
            }

            if(selectedTabletType != "선택안함") {
                // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                // 1. XML 레이아웃
                val layoutTabletImage = binding.layoutTabletImage

                // 2. 레이아웃을 이미지로 변환
                tabletBitmap = Bitmap.createBitmap(layoutTabletImage.width, layoutTabletImage.height, Bitmap.Config.ARGB_8888)
                val canvas2 = Canvas(tabletBitmap)
                layoutTabletImage.draw(canvas2)

                val layoutTabletResultImage = binding.layoutTabletResultImage
                layoutTabletResultImage.setImageBitmap(tabletBitmap)
                layoutTabletImage.visibility = View.GONE
            }
            binding.layoutResult.visibility = View.GONE
            binding.layoutResultContent.visibility = View.GONE
        }, delayMillis.toLong())
    }
    private fun setOnClickListeners() {
        binding.buttonResultToOrderFragment.setOnClickListener{
            ApplicationClass.prefs.resetPreferences()

            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonResultToOrderFragment2.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonMMS.setOnClickListener{
            // 권한 확인
            val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
            val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

            val activityContext = activity as? Activity // 안전하게 캐스트
            if (activityContext != null) {
                val readPermissionGranted = ContextCompat.checkSelfPermission(activityContext, readPermission) == PackageManager.PERMISSION_GRANTED
                val writePermissionGranted = ContextCompat.checkSelfPermission(activityContext, writePermission) == PackageManager.PERMISSION_GRANTED

                // 권한이 모두 허용되어 있는 경우
                if (readPermissionGranted && writePermissionGranted) {
                    // 파일 액세스 권한이 허용된 상태
                    // MMS 보내기
                    // 유골
                    // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                    val msgImageFile = File(requireContext().cacheDir, "태림원_결과.png")
                    val outputStream = FileOutputStream(msgImageFile)
                    msgBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.close()

                    // 4. 이미지 파일의 경로를 Uri로 만들어서 MMS 메시지에 첨부
                    Log.d(TAG, "imageFile: " + msgImageFile)
                    val imageUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", msgImageFile)
                    Log.d(TAG, "imageUri: " + imageUri)


                    val tel = ApplicationClass.prefs.leaderTel.toString().replace("-", "")
                    val subject = "MMS 제목"
//                    val sms_body = "MMS 내용"
                    val sms_body = msg

                    // 5. attachmentUri를 사용하여 MMS 메시지를 만들고 보냅니다.
                    sendMMS(tel, subject, sms_body, imageUri)
                } else {
                    // 하나 이상의 권한이 거부된 경우
                    // 권한을 요청합니다.
                    ActivityCompat.requestPermissions(
                        activityContext,
                        arrayOf(readPermission, writePermission),
                        REQUEST_CODE_STORAGE_PERMISSION
                    )
                }
            } else {
                // activityContext가 null인 경우에 대한 처리
            }
        }
    }
    private fun observer() {
    }

    // MMS 메시지 보내기 함수
    private fun sendMMS(tel: String, subject: String, sms_body: String, imageUri: Uri) {
        var intent = Intent(Intent.ACTION_SEND)
        if(ApplicationClass.prefs.name1 == ""){
            intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:$tel") // 수신자 전화번호를 설정
            intent.putExtra("sms_body", sms_body) // 메시지 내용을 설정
        }else{
            intent.apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, imageUri)
                putExtra("address", tel)
//            putExtra("subject", subject)
                putExtra("sms_body", sms_body)
            }
        }

        Log.d(TAG, "subject: " + subject)
        Log.d(TAG, "sms_body: " + sms_body)
        Log.d(TAG, "imageUri: " + imageUri)

        try {
            if (activity?.let { intent.resolveActivity(it.packageManager) } != null) {
                startActivity(intent)
            } else {
                // SMS/MMS 앱이 설치되어 있지 않음 또는 처리할 수 없는 경우
                toast("기본 메시지 앱을 찾을 수 없습니다.")
            }
        } catch (e: ActivityNotFoundException) {
            // SMS/MMS 앱을 찾을 수 없는 경우
            toast("기본 메시지 앱을 찾을 수 없습니다.")
        }
    }
}