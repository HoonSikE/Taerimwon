package com.example.taerimwon.ui.result

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
    private lateinit var boneSelectedTabletType: String

    private lateinit var msg: String
    private lateinit var msgBitmap: Bitmap
    private lateinit var urnBitmap: Bitmap
    private lateinit var urnContentBitmap: Bitmap
    private lateinit var boneBitmap: Bitmap
    private lateinit var boneContentBitmap: Bitmap
    private lateinit var tabletBitmap: Bitmap
    private lateinit var tabletContentBitmap: Bitmap
    private lateinit var boneTabletBitmap: Bitmap
    private lateinit var boneTabletContentBitmap: Bitmap
    private var index = 1;
    private var scale = 1.0f

    override fun init() {
        initData()
        setImage()
        setOnClickListeners()
        observer()
    }
    private fun initData() {
        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        boneSelectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()

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
            if(selectedTabletType.contains("사진")){
                binding.layoutTabletContent.visibility = View.VISIBLE
                binding.layoutTabletResult2.visibility = View.GONE
                binding.layoutTabletResultImage.visibility = View.VISIBLE
                binding.layoutTablet.visibility = View.VISIBLE
                binding.layoutTabletPhoto.visibility = View.VISIBLE
                // 이미지 경로(URI)를 SharedPreferences에서 가져옴
                val tabletImageUri = ApplicationClass.prefs.tabletImageUri

                if (tabletImageUri != "") {
                    val imageUri = Uri.parse(tabletImageUri)
                    binding.imageAddPhoto.setImageURI(imageUri)
                }
            }else if(ApplicationClass.prefs.tabletType.toString().contains("문구")){
                if(ApplicationClass.prefs.sex == "여성"){
                    binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                    binding.layoutTablet2.visibility = View.VISIBLE
                }else{
                    binding.layoutTabletResultImage.visibility = View.VISIBLE
                    binding.layoutTablet.visibility = View.VISIBLE
                }
                binding.layoutTabletContent.visibility = View.VISIBLE
                binding.layoutTabletResult2.visibility = View.GONE
            }else {
                setTabletData()
                if(ApplicationClass.prefs.sex == "여성"){
                    binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                    binding.layoutTablet2.visibility = View.VISIBLE
                }else{
                    binding.layoutTabletResultImage.visibility = View.VISIBLE
                    binding.layoutTablet.visibility = View.VISIBLE
                }
                binding.layoutTabletContent.visibility = View.VISIBLE
            }
        }

        if(boneSelectedTabletType != "선택안함") {
            if(boneSelectedTabletType.contains("사진")){
                binding.layoutTablet2Content.visibility = View.VISIBLE
                binding.layoutTablet2Result2.visibility = View.GONE
                binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                binding.layoutTablet2.visibility = View.VISIBLE
                binding.layoutBoneTabletPhoto.visibility = View.VISIBLE
                // 이미지 경로(URI)를 SharedPreferences에서 가져옴
                val boneTabletImageUri = ApplicationClass.prefs.boneTabletImageUri

                if (boneTabletImageUri != "") {
                    val imageUri = Uri.parse(boneTabletImageUri)
                    binding.imageBoneAddPhoto.setImageURI(imageUri)
                }
            }else if(ApplicationClass.prefs.boneTabletType.toString().contains("문구")){
                binding.layoutTablet2Content.visibility = View.VISIBLE
                binding.layoutTablet2Result2.visibility = View.GONE
                binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                binding.layoutTablet2.visibility = View.VISIBLE
            }else {
                setBoneTabletData()
                binding.layoutTabletContent.visibility = View.VISIBLE
                binding.layoutTabletResultImage.visibility = View.VISIBLE
                binding.layoutTablet.visibility = View.VISIBLE

                binding.layoutTablet2Content.visibility = View.VISIBLE
                binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                binding.layoutTablet2.visibility = View.VISIBLE
            }
        }

        // Urn Fragment 추가
        val resultContainerFragment = ResultContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_result_container, resultContainerFragment)
            .commit()

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

        if(engraveTypePosition == 0){
            binding.layoutUrnResult21.visibility = View.VISIBLE
            binding.imageUrnResult21.visibility = View.GONE
            return
        }
        var imageName = "img_mark" + (engraveTypePosition + 1)

        if(engraveTypePosition == 4 || engraveTypePosition == 5){
            if(ApplicationClass.prefs.engraveType2Position == 0)
                imageName = "img_mark5"
            else if(ApplicationClass.prefs.engraveType2Position == 1 || ApplicationClass.prefs.engraveType2Position == 2)
                imageName = "img_mark5_2"
        }
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageUrnResult21.setImageResource(imageResource)
    }
    private fun setUrnData() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        if(selectedUrnType == "유골함"){
            val layoutUrnImage = binding.layoutUrnImage
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn)

            when (selectedUrnName) {
                "미정" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
                }
                "기본" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn)
                    binding.imageUrnImage0.visibility = View.GONE
                    binding.imageUrnImage1.visibility = View.VISIBLE
                }
                "기본(검정)" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn0)
                    binding.imageUrnImage0.visibility = View.GONE
                    binding.imageUrnImage1.visibility = View.VISIBLE
                }
                "도원기독교 DW-3 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1)
                }
                "도원불교 DW-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2)
                }
                "도원천주교 DW-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3)
                }
                "도원칼라난 DW-2 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4)
                }
                "도원칼라송학 DW-1 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5)
                }
                "도화청꽃 DH-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6)
                }
                "도화홍꽃 DH-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn7)
                }
                "소담난 SDN-2 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8)
                }
                "소담송학 SDS-1 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9)
                }
            }
            layoutUrnImage.background = newBackground
        }

        if(selectedUrnName!!.contains("검정")){
            binding.layoutUrnResult111.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult112.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult121.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult122.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult123.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult124.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult13.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult131.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult141.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult142.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult15.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult151.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult161.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult162.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult171.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult17.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutUrnResult21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult220.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult221.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult222.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutUrnResult311.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult312.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult321.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult322.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult323.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult324.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult33.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult331.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult341.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult342.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult35.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult351.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult361.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult362.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult371.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult37.setTextColor(Color.parseColor("#FFD700"))
        }

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

        if(engraveType2.toString().contains("年月日")){
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)

            binding.layoutUrnResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult111.setTypeface(hyhaeso, Typeface.BOLD)
            binding.layoutUrnResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult112.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutUrnResult13.visibility = View.GONE
            binding.layoutUrnResult131.visibility = View.VISIBLE
            binding.layoutUrnResult15.visibility = View.GONE
            binding.layoutUrnResult151.visibility = View.VISIBLE
            binding.layoutUrnResult171.visibility = View.VISIBLE

            binding.layoutUrnResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult17.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutUrnResult311.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult311.setTypeface(hyhaeso, Typeface.BOLD)
            binding.layoutUrnResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult312.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutUrnResult33.visibility = View.GONE
            binding.layoutUrnResult331.visibility = View.VISIBLE
            binding.layoutUrnResult35.visibility = View.GONE
            binding.layoutUrnResult351.visibility = View.VISIBLE
            binding.layoutUrnResult371.visibility = View.VISIBLE

            binding.layoutUrnResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult37.setTypeface(hyhaeso, Typeface.BOLD)
        }

        binding.layoutUrnResult22.visibility = View.VISIBLE
        // 이름
        val layoutUrnResult221 = binding.layoutUrnResult221

        if((engraveType == "일반" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日")))
            || (engraveType == "기독교" && (engraveType2 == "직분X"))
            || (engraveType == "불교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日")))
            || (engraveType == "천주교" && (engraveType2 == "세례명X"))
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
        }else if(engraveType2 == "형제" || engraveType2 == "자매"){
            binding.layoutUrnResult222.visibility = View.VISIBLE

            if(engraveType2 == "형제")
                binding.layoutUrnResult222.text = "형제"
            else if(engraveType2 == "자매")
                binding.layoutUrnResult222.text = "자매"

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
        }else if(engraveType == "기독교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日"))
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
        } else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日"))){
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
                    layoutUrnResult222.textScaleX = 0.65f
                }
                5 -> {
                    layoutUrnResult222.textScaleX = 0.54f
                }
                6 -> {
                    layoutUrnResult222.textScaleX = 0.44f
                }
            }
            layoutUrnResult222.text = name2
        }else if(engraveType == "SGI"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutUrnResult220.typeface = serifamedium
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult220.letterSpacing = 0f

            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult222.typeface = hyhaeso
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
//            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "妙法"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult220.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult220.typeface = hyhaeso
            layoutUrnResult220.letterSpacing = 0f

            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult222.typeface = hyhaeso

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
                if(engraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult112.text = "出\n生"
                else
                    binding.layoutUrnResult112.text = "出生"
            }
            "천주교" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE
                if(engraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult112.text = "出\n生"
                else
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
                if(engraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult312.text = "召\n天"
                else
                    binding.layoutUrnResult312.text = "召天"
            }
            "천주교" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE

                if(engraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult312.text = "善\n終"
                else
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

        if(boneEngraveTypePosition == 0){
            binding.layoutBoneResult21.visibility = View.VISIBLE
            binding.imageBoneResult21.visibility = View.GONE
            return
        }

        var imageName = "img_mark" + (boneEngraveTypePosition + 1)

        if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
            if(ApplicationClass.prefs.boneEngraveType2Position == 0)
                imageName = "img_mark5"
            else if(ApplicationClass.prefs.boneEngraveType2Position == 1 || ApplicationClass.prefs.boneEngraveType2Position == 2)
                imageName = "img_mark5_2"
        }
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageBoneResult21.setImageResource(imageResource)
    }
    private fun setBone1Data() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName

        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1)

        if(selectedUrnName == "미정"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
        }
        else if(selectedUrnName == "기본"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1)
        }
        else if(selectedUrnName == "기본(검정)"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1_0)
        }
        binding.layoutBone1Image.background = newBackground

        if(selectedUrnName!!.contains("검정")){
            binding.layoutBoneResult111.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult112.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult121.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult122.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult123.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult124.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult13.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult131.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult141.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult142.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult15.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult151.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult161.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult162.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult171.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult17.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutBoneResult21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult220.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult221.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult222.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutBoneResult311.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult312.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult321.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult322.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult323.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult324.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult33.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult331.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult341.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult342.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult35.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult351.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult361.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult362.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult371.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBoneResult37.setTextColor(Color.parseColor("#FFD700"))
        }
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

        if(boneEngraveType2.toString().contains("年月日")){
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)

            binding.layoutBoneResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutBoneResult111.setTypeface(hyhaeso, Typeface.BOLD)
            binding.layoutBoneResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutBoneResult112.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutBoneResult13.visibility = View.GONE
            binding.layoutBoneResult131.visibility = View.VISIBLE
            binding.layoutBoneResult15.visibility = View.GONE
            binding.layoutBoneResult151.visibility = View.VISIBLE
            binding.layoutBoneResult171.visibility = View.VISIBLE

            binding.layoutBoneResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutBoneResult17.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutBoneResult311.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutBoneResult311.setTypeface(hyhaeso, Typeface.BOLD)
            binding.layoutBoneResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutBoneResult312.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutBoneResult33.visibility = View.GONE
            binding.layoutBoneResult331.visibility = View.VISIBLE
            binding.layoutBoneResult35.visibility = View.GONE
            binding.layoutBoneResult351.visibility = View.VISIBLE
            binding.layoutBoneResult371.visibility = View.VISIBLE

            binding.layoutBoneResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutBoneResult37.setTypeface(hyhaeso, Typeface.BOLD)
        }

        binding.layoutBoneResult22.visibility = View.VISIBLE
        // 이름
        val layoutBoneResult221 = binding.layoutBoneResult221

        if((boneEngraveType == "일반" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日")))
            || (boneEngraveType == "기독교" && (boneEngraveType2 == "직분X"))
            || (boneEngraveType == "불교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日")))
            || (boneEngraveType == "천주교" && (boneEngraveType2 == "세례명X"))
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
        }else if(boneEngraveType2 == "형제" || boneEngraveType2 == "자매"){
            binding.layoutBoneResult222.visibility = View.VISIBLE

            if(boneEngraveType2 == "형제")
                binding.layoutBoneResult222.text = "형제"
            else if(boneEngraveType2 == "자매")
                binding.layoutBoneResult222.text = "자매"

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
        }else if(boneEngraveType == "기독교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))
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
//                    layoutBoneResult220.width = pixel_size_100
                    layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutBoneResult220.letterSpacing = -0.2f
                }
            }
            layoutBoneResult220.text = boneName2
        } else if(boneEngraveType == "천주교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))){
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
//            layoutBoneResult222.width = pixel_size_100
            when (boneName2.length) {
                2, 3 -> {
//                    layoutBoneResult222.width = pixel_size_90
                    layoutBoneResult222.letterSpacing = -0.15f
                }
                4 -> {
                    layoutBoneResult222.textScaleX = 0.65f
                }
                5 -> {
                    layoutBoneResult222.textScaleX = 0.54f
                }
                6 -> {
                    layoutBoneResult222.textScaleX = 0.44f
                }
            }
            layoutBoneResult222.text = boneName2
        }else if(boneEngraveType == "SGI"){
            val layoutBoneResult220 = binding.layoutBoneResult220
            layoutBoneResult220.visibility = View.VISIBLE
//            layoutBoneResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutBoneResult220.typeface = serifamedium
            layoutBoneResult220.text = "SGI"
            layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult220.letterSpacing = 0f

            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            val layoutBoneResult222 = binding.layoutBoneResult222
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutBoneResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutBoneResult222.typeface = hyhaeso
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
//            layoutBoneResult220.width = pixel_size_80
            layoutBoneResult220.text = "妙法"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutBoneResult220.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutBoneResult220.typeface = hyhaeso
            layoutBoneResult220.letterSpacing = 0f

            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            val layoutBoneResult222 = binding.layoutBoneResult222
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutBoneResult222.typeface = hyhaeso

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
                if(ApplicationClass.prefs.boneEngraveType2.toString().contains("年月日"))
                    binding.layoutBoneResult112.text = "出\n生"
                else
                    binding.layoutBoneResult112.text = "出生"
            }
            "천주교" -> {
                binding.layoutBoneResult111.visibility = View.GONE
                binding.layoutBoneResult112.visibility = View.VISIBLE
                if(ApplicationClass.prefs.boneEngraveType2.toString().contains("年月日"))
                    binding.layoutBoneResult112.text = "出\n生"
                else
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

                if(ApplicationClass.prefs.boneEngraveType2.toString().contains("年月日"))
                    binding.layoutBoneResult312.text = "召\n天"
                else
                    binding.layoutBoneResult312.text = "召天"
            }
            "천주교" -> {
                binding.layoutBoneResult311.visibility = View.GONE
                binding.layoutBoneResult312.visibility = View.VISIBLE

                if(ApplicationClass.prefs.boneEngraveType2.toString().contains("年月日"))
                    binding.layoutBoneResult312.text = "善\n終"
                else
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
        var layoutBone2Result211 = binding.layoutBone2Result211
        var imageBone2Result211 = binding.imageBone2Result211

        // 정보2
        var boneName1 = ApplicationClass.prefs.boneName1
        var boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
        var layoutBone2Result212 = binding.layoutBone2Result212
        var imageBone2Result212 = binding.imageBone2Result212

        // 추가 정보 / 기존 정보
//        if((engraveTypePosition == 4 && boneEngraveTypePosition == 4) || (engraveTypePosition == 5 && boneEngraveTypePosition == 5) ||(engraveTypePosition == 4 && boneEngraveTypePosition == 5) || (engraveTypePosition == 5 && boneEngraveTypePosition == 4)){
//            binding.imageBone2Result21.visibility = View.VISIBLE
//            binding.imageBone2Result211.visibility = View.GONE
//            binding.imageBone2Result212.visibility = View.GONE
//            val engraveType2Position = ApplicationClass.prefs.engraveType2Position
//            val boneEngraveType2Position = ApplicationClass.prefs.boneEngraveType2Position
//
//            var imageName = "img_mark5"
//
//            when(engraveType2Position){
//                0 -> {
//                    when(boneEngraveType2Position){
//                        0 -> {
//                            imageName = "img_mark5"
//                        }
//                    }
//                }
//                1, 2 -> {
//                    when(boneEngraveType2Position){
//                        1, 2 -> {
//                            imageName = "img_mark5_2"
//                        }
//                    }
//                }
//            }
//            val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
//            binding.imageBone2Result21.setImageResource(imageResource)
//            return
//        }
//        else
        if(engraveTypePosition == boneEngraveTypePosition && !(name1 == "" || boneName1 == "")) {
            binding.imageBone2Result21.visibility = View.VISIBLE
            binding.imageBone2Result211.visibility = View.GONE
            binding.imageBone2Result212.visibility = View.GONE

            if(engraveTypePosition == 0){
                binding.layoutBone2Result21.visibility = View.VISIBLE
                binding.imageBone2Result21.visibility = View.GONE
            }

            var imageName = "img_mark" + (engraveTypePosition + 1)

            if(engraveTypePosition == 4 || engraveTypePosition == 5){
                if(ApplicationClass.prefs.engraveType2Position == 0)
                    imageName = "img_mark5"
                else if(ApplicationClass.prefs.engraveType2Position == 1 || ApplicationClass.prefs.engraveType2Position == 2)
                    imageName = "img_mark5_2"
            }
            val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
            binding.imageBone2Result21.setImageResource(imageResource)
            return
        }else if(ApplicationClass.prefs.boneSex == "남성"){
            // 정보1
            name1 = ApplicationClass.prefs.boneName1
            engraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
            layoutBone2Result211 = binding.layoutBone2Result212
            imageBone2Result211 = binding.imageBone2Result212

            // 정보2
            boneName1 = ApplicationClass.prefs.name1
            boneEngraveTypePosition = ApplicationClass.prefs.engraveTypePosition
            layoutBone2Result212 = binding.layoutBone2Result211
            imageBone2Result212 = binding.imageBone2Result211
        }
        // 기존 정보 / 추가 정보
        else if(ApplicationClass.prefs.boneSex == "여성"){
        }

        if(name1 != ""){
            if(engraveTypePosition == 0){
                layoutBone2Result211.visibility = View.VISIBLE
                imageBone2Result211.visibility = View.GONE
            }
            // 이미지 이름을 문자열로 정의합니다.
            var imageName1 = "img_mark" + (engraveTypePosition + 1)

            if(engraveTypePosition == 4 || engraveTypePosition == 5){
                if(ApplicationClass.prefs.engraveType2Position == 0)
                    imageName1 = "img_mark5"
                else if(ApplicationClass.prefs.engraveType2Position == 1 || ApplicationClass.prefs.engraveType2Position == 2)
                    imageName1 = "img_mark5_2"
            }
            val imageResource1 = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            binding.imageBone2Result211.setImageResource(imageResource1)
        }

        if(boneName1 != ""){
            if(boneEngraveTypePosition == 0){
                layoutBone2Result212.visibility = View.VISIBLE
                imageBone2Result212.visibility = View.GONE
            }
            // 이미지 이름을 문자열로 정의합니다.
            var imageName2 = "img_mark" + (boneEngraveTypePosition + 1)

            if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
                if(ApplicationClass.prefs.boneEngraveType2Position == 0)
                    imageName2 = "img_mark5"
                else if(ApplicationClass.prefs.boneEngraveType2Position == 1 || ApplicationClass.prefs.boneEngraveType2Position == 2)
                    imageName2 = "img_mark5_2"
            }
            val imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            binding.imageBone2Result212.setImageResource(imageResource)
        }
    }
    private fun setBone2Data() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2)

        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        if(selectedUrnName == "미정"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
        }
        else if(selectedUrnName == "기본"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2)
        }else if(selectedUrnName == "기본(검정)"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2_0)
        }
        binding.layoutBone2Image.background = newBackground

        // 정보1
        var name1 = ApplicationClass.prefs.name1.toString()
        var name2 = ApplicationClass.prefs.name2.toString()
        val tmp1 = StringBuilder()
        val tmp1_2 = StringBuilder()

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
        val tmp2_2 = StringBuilder()

        var boneEngraveType = ApplicationClass.prefs.boneEngraveType
        var boneEngraveType2 = ApplicationClass.prefs.boneEngraveType2

        var boneDate1 = ApplicationClass.prefs.boneDate1.toString()
        var boneDate1Type = ApplicationClass.prefs.boneDate1Type
        var boneDate2 = ApplicationClass.prefs.boneDate1.toString()
        var boneDate2Type = ApplicationClass.prefs.boneDate2Type

        // 정보1
        // 마크
        var layoutUrnResult211 = binding.layoutBone2Result211
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
        // 마크
        var layoutBoneResult211 = binding.layoutBone2Result212
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

        // 년월일
        var layoutUrnResult13 = binding.layoutBone21Result13
        var layoutUrnResult131 = binding.layoutBone21Result131
        var layoutUrnResult15 = binding.layoutBone21Result15
        var layoutUrnResult151 = binding.layoutBone21Result151
        var layoutUrnResult171 = binding.layoutBone21Result171

        var layoutUrnResult33 = binding.layoutBone21Result33
        var layoutUrnResult331 = binding.layoutBone21Result331
        var layoutUrnResult35 = binding.layoutBone21Result35
        var layoutUrnResult351 = binding.layoutBone21Result351
        var layoutUrnResult371 = binding.layoutBone21Result371

        var layoutBoneResult13 = binding.layoutBone22Result13
        var layoutBoneResult131 = binding.layoutBone22Result131
        var layoutBoneResult15 = binding.layoutBone22Result15
        var layoutBoneResult151 = binding.layoutBone22Result151
        var layoutBoneResult171 = binding.layoutBone22Result171

        var layoutBoneResult33 = binding.layoutBone22Result33
        var layoutBoneResult331 = binding.layoutBone22Result331
        var layoutBoneResult35 = binding.layoutBone22Result35
        var layoutBoneResult351 = binding.layoutBone22Result351
        var layoutBoneResult371 = binding.layoutBone22Result371
        // 폰트
        val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)

        // 추가 정보 / 기존 정보
        if(ApplicationClass.prefs.boneSex == "남성"){
            // 정보1
//            name1 = ApplicationClass.prefs.boneName1.toString()
//            name2 = ApplicationClass.prefs.boneName2.toString()

//            engraveType = ApplicationClass.prefs.boneEngraveType
//            engraveType2 = ApplicationClass.prefs.boneEngraveType2

//            date1 = ApplicationClass.prefs.boneDate1.toString()
//            date1Type = ApplicationClass.prefs.boneDate1Type
//            date2 = ApplicationClass.prefs.boneDate1.toString()
//            date2Type = ApplicationClass.prefs.boneDate2Type

            // 정보2
//            boneName1 = ApplicationClass.prefs.name1.toString()
//            boneName2 = ApplicationClass.prefs.name2.toString()

//            boneEngraveType = ApplicationClass.prefs.engraveType
//            boneEngraveType2 = ApplicationClass.prefs.engraveType2

//            boneDate1 = ApplicationClass.prefs.date1.toString()
//            boneDate1Type = ApplicationClass.prefs.date1Type
//            boneDate2 = ApplicationClass.prefs.date1.toString()
//            boneDate2Type = ApplicationClass.prefs.date2Type

            // 정보1
            // 마크
            layoutUrnResult211 = binding.layoutBone2Result212
            // 이름
            layoutUrnResult221 = binding.layoutBone22Result221
            // 위 글자
            layoutUrnResult220 = binding.layoutBone22Result220
            // 아래 글자
            layoutUrnResult222 = binding.layoutBone22Result222
            // 좌
            layoutUrnResult111 = binding.layoutBone22Result111
            layoutUrnResult112 = binding.layoutBone22Result112
            layoutUrnResult121 = binding.layoutBone22Result121
            layoutUrnResult122 = binding.layoutBone22Result122
            layoutUrnResult123 = binding.layoutBone22Result123
            layoutUrnResult124 = binding.layoutBone22Result124
            layoutUrnResult141 = binding.layoutBone22Result141
            layoutUrnResult142 = binding.layoutBone22Result142
            layoutUrnResult161 = binding.layoutBone22Result161
            layoutUrnResult162 = binding.layoutBone22Result162
            layoutUrnResult17 = binding.layoutBone22Result17
            // 우
            layoutUrnResult311 = binding.layoutBone22Result311
            layoutUrnResult312 = binding.layoutBone22Result312
            layoutUrnResult321 = binding.layoutBone22Result321
            layoutUrnResult322 = binding.layoutBone22Result322
            layoutUrnResult323 = binding.layoutBone22Result323
            layoutUrnResult324 = binding.layoutBone22Result324
            layoutUrnResult341 = binding.layoutBone22Result341
            layoutUrnResult342 = binding.layoutBone22Result342
            layoutUrnResult361 = binding.layoutBone22Result361
            layoutUrnResult362 = binding.layoutBone22Result362
            layoutUrnResult37 = binding.layoutBone22Result37

            // 정보2
            // 마크
            layoutBoneResult211 = binding.layoutBone2Result211
            // 이름
            layoutBoneResult221 = binding.layoutBone21Result221
            // 위 글자
            layoutBoneResult220 = binding.layoutBone21Result220
            // 아래 글자
            layoutBoneResult222 = binding.layoutBone21Result222
            // 좌
            layoutBoneResult111 = binding.layoutBone21Result111
            layoutBoneResult112 = binding.layoutBone21Result112
            layoutBoneResult121 = binding.layoutBone21Result121
            layoutBoneResult122 = binding.layoutBone21Result122
            layoutBoneResult123 = binding.layoutBone21Result123
            layoutBoneResult124 = binding.layoutBone21Result124
            layoutBoneResult141 = binding.layoutBone21Result141
            layoutBoneResult142 = binding.layoutBone21Result142
            layoutBoneResult161 = binding.layoutBone21Result161
            layoutBoneResult162 = binding.layoutBone21Result162
            layoutBoneResult17 = binding.layoutBone21Result17
            // 우
            layoutBoneResult311 = binding.layoutBone21Result311
            layoutBoneResult312 = binding.layoutBone21Result312
            layoutBoneResult321 = binding.layoutBone21Result321
            layoutBoneResult322 = binding.layoutBone21Result322
            layoutBoneResult323 = binding.layoutBone21Result323
            layoutBoneResult324 = binding.layoutBone21Result324
            layoutBoneResult341 = binding.layoutBone21Result341
            layoutBoneResult342 = binding.layoutBone21Result342
            layoutBoneResult361 = binding.layoutBone21Result361
            layoutBoneResult362 = binding.layoutBone21Result362
            layoutBoneResult37 = binding.layoutBone21Result37

            // 년월일
            layoutUrnResult13 = binding.layoutBone22Result13
            layoutUrnResult131 = binding.layoutBone22Result131
            layoutUrnResult15 = binding.layoutBone22Result15
            layoutUrnResult151 = binding.layoutBone22Result151
            layoutUrnResult171 = binding.layoutBone22Result171

//            layoutUrnResult30 = binding.layoutBone22Result30
            layoutUrnResult33 = binding.layoutBone22Result33
            layoutUrnResult331 = binding.layoutBone22Result331
            layoutUrnResult35 = binding.layoutBone22Result35
            layoutUrnResult351 = binding.layoutBone22Result351
            layoutUrnResult371 = binding.layoutBone22Result371

//            layoutBoneResult10 = binding.layoutBone21Result10
            layoutBoneResult13 = binding.layoutBone21Result13
            layoutBoneResult131 = binding.layoutBone21Result131
            layoutBoneResult15 = binding.layoutBone21Result15
            layoutBoneResult151 = binding.layoutBone21Result151
            layoutBoneResult171 = binding.layoutBone21Result171

//            layoutBoneResult30 = binding.layoutBone21Result30
            layoutBoneResult33 = binding.layoutBone21Result33
            layoutBoneResult331 = binding.layoutBone21Result331
            layoutBoneResult35 = binding.layoutBone21Result35
            layoutBoneResult351 = binding.layoutBone21Result351
            layoutBoneResult371 = binding.layoutBone21Result371
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

        if(engraveType2.toString().contains("年月日")){
            val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult111.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult112.setTypeface(hyhaeso, Typeface.BOLD)

            layoutUrnResult13.visibility = View.GONE
            layoutUrnResult131.visibility = View.VISIBLE
            layoutUrnResult15.visibility = View.GONE
            layoutUrnResult151.visibility = View.VISIBLE
            layoutUrnResult171.visibility = View.VISIBLE

            layoutUrnResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult17.setTypeface(hyhaeso, Typeface.BOLD)

            layoutUrnResult311.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult311.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult312.setTypeface(hyhaeso, Typeface.BOLD)

            layoutUrnResult33.visibility = View.GONE
            layoutUrnResult331.visibility = View.VISIBLE
            layoutUrnResult35.visibility = View.GONE
            layoutUrnResult351.visibility = View.VISIBLE
            layoutUrnResult371.visibility = View.VISIBLE

            layoutUrnResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
        }

        if(boneEngraveType2.toString().contains("年月日")){
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
            layoutBoneResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult111.setTypeface(hyhaeso, Typeface.BOLD)
            layoutBoneResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult112.setTypeface(hyhaeso, Typeface.BOLD)

            layoutBoneResult13.visibility = View.GONE
            layoutBoneResult131.visibility = View.VISIBLE
            layoutBoneResult15.visibility = View.GONE
            layoutBoneResult151.visibility = View.VISIBLE
            layoutBoneResult171.visibility = View.VISIBLE

            layoutBoneResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult17.setTypeface(hyhaeso, Typeface.BOLD)

            layoutBoneResult311.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult311.setTypeface(hyhaeso, Typeface.BOLD)
            layoutBoneResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult312.setTypeface(hyhaeso, Typeface.BOLD)

            layoutBoneResult33.visibility = View.GONE
            layoutBoneResult331.visibility = View.VISIBLE
            layoutBoneResult35.visibility = View.GONE
            layoutBoneResult351.visibility = View.VISIBLE
            layoutBoneResult371.visibility = View.VISIBLE

            layoutBoneResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutBoneResult37.setTypeface(hyhaeso, Typeface.BOLD)
        }

        if(selectedUrnName!!.contains("검정")){
            binding.layoutBone2Result21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBone2Result211.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutBone2Result212.setTextColor(Color.parseColor("#FFD700"))

            layoutUrnResult111.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult112.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult121.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult122.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult123.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult124.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult13.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult131.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult141.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult142.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult15.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult151.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult161.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult162.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult171.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult17.setTextColor(Color.parseColor("#FFD700"))

            layoutUrnResult211.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult220.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult221.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult222.setTextColor(Color.parseColor("#FFD700"))

            layoutUrnResult311.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult312.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult321.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult322.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult323.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult324.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult33.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult331.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult341.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult342.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult35.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult351.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult361.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult362.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult371.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult37.setTextColor(Color.parseColor("#FFD700"))

            layoutBoneResult111.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult112.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult121.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult122.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult123.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult124.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult13.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult131.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult141.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult142.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult15.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult151.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult161.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult162.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult171.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult17.setTextColor(Color.parseColor("#FFD700"))

            layoutBoneResult211.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult220.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult221.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult222.setTextColor(Color.parseColor("#FFD700"))

            layoutBoneResult311.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult312.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult321.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult322.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult323.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult324.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult33.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult331.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult341.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult342.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult35.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult351.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult361.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult362.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult371.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult37.setTextColor(Color.parseColor("#FFD700"))
        }

        val pixel_size_0 = resources.getDimensionPixelSize(R.dimen.pixel_size_0)
        val pixel_size_4 = resources.getDimensionPixelSize(R.dimen.pixel_size_4)
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
        if((engraveType == "일반" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日")))
            || (engraveType == "기독교" && (engraveType2 == "직분X"))
            || (engraveType == "불교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日")))
            || (engraveType == "천주교" && (engraveType2 == "세례명X"))
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
                    layoutUrnResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }else if(engraveType2 == "형제" || engraveType2 == "자매"){
            layoutUrnResult222.visibility = View.VISIBLE
//            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult221.height = pixel_size_170

            if(engraveType2 == "형제")
                layoutUrnResult222.text = "형제"
            else if(engraveType2 == "자매")
                layoutUrnResult222.text = "자매"

            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }else if(engraveType == "기독교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日"))
            || engraveType == "불교" && engraveType2 == "법명"
            || engraveType == "순복음"){
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
            layoutUrnResult220.visibility = View.VISIBLE
            when (name2.length) {
                2 ->{
                    tmp1_2.append(name2[0]).append("\n").append(name2[1])
                }
                3 -> {
                    tmp1_2.append(name2[0]).append("\n").append(name2[1]).append("\n").append(name2[2])

                    layoutUrnResult220.setLineSpacing(0f, 1.0f)
                }
                4 -> {
                    tmp1_2.append(name2[0] + "" + name2[1]).append("\n").append(name2[2] + "" + name2[3])
//                    layoutUrnResult220.width = pixel_size_100
//                    layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
//                    layoutUrnResult220.letterSpacing = -0.2f
                }
            }
            layoutUrnResult220.text = tmp1_2
        } else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日"))){
            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    tmp1.append(name1[0]).append("\n").append("\n").append(name1[1])
                }
                3 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2])
                }
                4 -> {
                    tmp1.append(name1[0]).append("\n").append(name1[1]).append("\n").append(name1[2]).append("\n").append(name1[3])
                    layoutUrnResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
//            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult220.visibility = View.VISIBLE
//            layoutUrnResult222.width = pixel_size_100
//            layoutUrnResult220.width = pixel_size_100
            when (name2.length) {
                2 ->{
                    tmp1_2.append(name2[0]).append("\n").append(name2[1])
                }
                3 -> {
                    tmp1_2.append(name2[0]).append("\n").append(name2[1]).append("\n").append(name2[2])

                    layoutUrnResult220.setLineSpacing(0f, 1.0f)
//                    layoutUrnResult222.width = pixel_size_90
//                    layoutUrnResult222.letterSpacing = -0.15f
                }
                4 -> {
                    tmp1_2.append(name2[0] + "" + name2[1]).append("\n").append(name2[2] + "" + name2[3])

//                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
//                    layoutUrnResult222.letterSpacing = -0.2f
                }
                5 -> {
                    tmp1_2.append(name2[0] + "" + name2[3]).append("\n").append(name2[1] + "" + name2[4]).append("\n").append(name2[2])

                    layoutUrnResult220.setLineSpacing(0f, 1.0f)

//                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
//                    layoutUrnResult222.letterSpacing = -0.2f
                }
                6 -> {
                    tmp1_2.append(name2[0] + "" + name2[3]).append("\n").append(name2[1] + "" + name2[4]).append("\n").append(name2[2] + "" + name2[5])

                    layoutUrnResult220.setLineSpacing(0f, 1.0f)

//                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_16.toFloat())
//                    layoutUrnResult222.letterSpacing = -0.2f
                }
            }
//            layoutUrnResult222.text = tmp1_2
            layoutUrnResult220.text = tmp1_2
        }else if(engraveType == "SGI"){
            layoutUrnResult220.visibility = View.VISIBLE
//            layoutUrnResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutUrnResult220.typeface = serifamedium
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutUrnResult220.setPadding(0, 0, 0, pixel_size_4)
            layoutUrnResult220.letterSpacing = 0f

//            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult222.typeface = hyhaeso
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
                    layoutUrnResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutUrnResult221.text = tmp1.toString()
        }else if(engraveType == "묘법"){
            layoutUrnResult220.visibility = View.VISIBLE
//            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "妙法"
            layoutUrnResult220.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult220.typeface = hyhaeso
            layoutUrnResult220.letterSpacing = 0f

//            layoutUrnResult221.height = pixel_size_145
            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult222.typeface = hyhaeso
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
                    layoutUrnResult221.setLineSpacing(0f, 1.05f)
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
                layoutUrnResult112.text = "出\n生"
            }
            "천주교" -> {
                layoutUrnResult111.visibility = View.GONE
                layoutUrnResult112.visibility = View.VISIBLE
                layoutUrnResult112.text = "出\n生"
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
                layoutUrnResult312.text = "召\n天"
            }
            "천주교" -> {
                layoutUrnResult311.visibility = View.GONE
                layoutUrnResult312.visibility = View.VISIBLE
                layoutUrnResult312.text = "善\n終"
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
        if((boneEngraveType == "일반" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日")))
            || (boneEngraveType == "기독교" && (boneEngraveType2 == "직분X"))
            || (boneEngraveType == "불교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日")))
            || (boneEngraveType == "천주교" && (boneEngraveType2 == "세례명X"))
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
                    layoutBoneResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }else if(boneEngraveType2 == "형제" || boneEngraveType2 == "자매" ){
            layoutBoneResult222.visibility = View.VISIBLE
//            layoutBoneResult220.visibility = View.VISIBLE
            if(boneEngraveType2 == "형제")
                layoutBoneResult222.text = "형제"
//                layoutBoneResult220.text = "형\n제"
            if(boneEngraveType2 == "자매")
                layoutBoneResult222.text = "자매"
//                layoutBoneResult220.text = "자\n매"

//            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }else if(boneEngraveType == "기독교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))
            || boneEngraveType == "불교" && boneEngraveType2 == "법명"
            || boneEngraveType == "순복음"){
//            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
            layoutBoneResult220.visibility = View.VISIBLE
            when (boneName2.length) {
                2 -> {
                    tmp2_2.append(boneName2[0]).append("\n").append(boneName2[1])
                }
                3 -> {
                    tmp2_2.append(boneName2[0]).append("\n").append(boneName2[1]).append("\n").append(boneName2[2])

                    layoutBoneResult220.setLineSpacing(0f, 1.0f)

//                    layoutBoneResult222.width = pixel_size_90
//                    layoutBoneResult222.letterSpacing = -0.15f
                }
                4 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[1]).append("\n").append(boneName2[2] + "" + boneName2[3])
//                    layoutBoneResult220.width = pixel_size_100
//                    layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
//                    layoutBoneResult220.letterSpacing = -0.2f
                }
            }
            layoutBoneResult220.text = tmp2_2
        } else if(boneEngraveType == "천주교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))){
//            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp2.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp2.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutBoneResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
//            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult220.visibility = View.VISIBLE
//            layoutBoneResult222.width = pixel_size_100
//            layoutBoneResult220.width = pixel_size_100
            when (boneName2.length) {
                2 -> {
                    tmp2_2.append(boneName2[0]).append("\n").append(boneName2[1])
                }
                3 -> {
                    tmp2_2.append(boneName2[0]).append("\n").append(boneName2[1]).append("\n").append(boneName2[2])

                    layoutBoneResult220.setLineSpacing(0f, 1.0f)

//                    layoutBoneResult222.width = pixel_size_90
//                    layoutBoneResult222.letterSpacing = -0.15f
                }
                4 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[1]).append("\n").append(boneName2[2] + "" + boneName2[3])

//                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
//                    layoutBoneResult222.letterSpacing = -0.2f
                }
                5 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[3]).append("\n").append(boneName2[1] + "" + boneName2[4]).append("\n").append(boneName2[3])

                    layoutBoneResult220.setLineSpacing(0f, 1.0f)

//                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
//                    layoutBoneResult222.letterSpacing = -0.2f
                }
                6 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[3]).append("\n").append(boneName2[1] + "" + boneName2[4]).append("\n").append(boneName2[2] + "" + boneName2[5])

                    layoutBoneResult221.setLineSpacing(0f, 1.0f)

//                    layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_16.toFloat())
//                    layoutBoneResult222.letterSpacing = -0.2f
                }
            }
//            layoutBoneResult222.text = tmp2_2
            layoutBoneResult220.text = tmp2_2
        }else if(boneEngraveType == "SGI"){
            layoutBoneResult220.visibility = View.VISIBLE
//            layoutBoneResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutBoneResult220.typeface = serifamedium
            layoutBoneResult220.text = "SGI"
            layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutBoneResult220.setPadding(0, 0, 0, pixel_size_4)
            layoutBoneResult220.letterSpacing = 0f

//            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutBoneResult222.typeface = hyhaeso
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
                    layoutBoneResult221.setLineSpacing(0f, 1.05f)
                }
            }
            layoutBoneResult221.text = tmp2.toString()
        }else if(boneEngraveType == "묘법"){
            layoutBoneResult220.visibility = View.VISIBLE
//            layoutBoneResult220.width = pixel_size_80
            layoutBoneResult220.text = "妙法"
            layoutBoneResult220.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutBoneResult220.typeface = hyhaeso
            layoutBoneResult220.letterSpacing = 0f

//            layoutBoneResult221.height = pixel_size_145
            layoutBoneResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
            layoutBoneResult221.setLineSpacing(0f, 1.0f)

            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutBoneResult222.typeface = hyhaeso
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
                    layoutBoneResult221.setLineSpacing(0f, 1.05f)
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
                layoutBoneResult112.text = "出\n生"
            }
            "천주교" -> {
                layoutBoneResult111.visibility = View.GONE
                layoutBoneResult112.visibility = View.VISIBLE
                layoutBoneResult112.text = "出\n生"
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
                layoutBoneResult312.text = "召\n天"
            }
            "천주교" -> {
                layoutBoneResult311.visibility = View.GONE
                layoutBoneResult312.visibility = View.VISIBLE
                layoutBoneResult312.text = "善\n終"
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
        val tabletReligion = ApplicationClass.prefs.tabletReligion
        val tabletType = ApplicationClass.prefs.tabletType
        var imageName = "img_mark1"

        if(tabletReligion == "일반" && tabletType == "본관") {
            binding.layoutTabletResult10.visibility = View.GONE
            binding.layoutTabletResult12.visibility = View.GONE
        }else if(tabletReligion == "일반" && tabletType != "문구") {
//            imageName = "img_mark1"
            binding.layoutTabletResult10.visibility = View.VISIBLE
            binding.layoutTabletResult12.visibility = View.GONE
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
        binding.layoutTabletResult12.setImageResource(imageResource)
    }
    private fun setTabletData() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)

        val selectedTabletName = ApplicationClass.prefs.selectedTabletName

        when (selectedTabletName) {
            "미정" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
//            "기본" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)
//            }
//            "기본(검정)" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet0)
//            }
            "도원기독교 DW-3 4010" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
            }
            "도원불교 DW-4 4010" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet2)
            }
            "도원천주교 DW-5 4010" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet3)
            }
            "도원칼라난 DW-2 4010" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet4)
            }
        }
        binding.layoutTabletImage.background = newBackground

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

        val layoutTabletResult0 = binding.layoutTabletResult0
        val layoutTabletResult20 = binding.layoutTabletResult20
        val layoutTabletResult2 = binding.layoutTabletResult2
        val layoutTabletResult21 = binding.layoutTabletResult21
        val layoutTabletResult22 = binding.layoutTabletResult22
        val layoutTabletResult30 = binding.layoutTabletResult30
        val layoutTabletResult3 = binding.layoutTabletResult3
        val layoutTabletResult31 = binding.layoutTabletResult31
        val layoutTabletResult312 = binding.layoutTabletResult312
        val layoutTabletResult32 = binding.layoutTabletResult32

        if(selectedTabletName!!.contains("검정")){
            binding.layoutTabletResult0.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult10.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult20.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult2.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult22.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult30.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult3.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult31.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult312.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutTabletResult32.setTextColor(Color.parseColor("#FFD700"))
        }

        if(!tabletType.contains("본관") && tabletType != "문구") {
            when (tabletType) {
                "일반", "불교" -> {
                    layoutTabletResult2.visibility = View.VISIBLE

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
                            layoutTabletResult2.setLineSpacing(0f, 1.4f)
                        }
                    }
                    layoutTabletResult2.text = tmp.toString()
                }
                "기독교" -> {
                    layoutTabletResult21.visibility = View.VISIBLE

                    val layoutParams = layoutTabletResult21.layoutParams as ViewGroup.MarginLayoutParams
                    val marginTopInPixels = 0
                    layoutParams.topMargin = marginTopInPixels
                    layoutTabletResult21.setLineSpacing(0f, 1.7f)
                    layoutTabletResult21.layoutParams = layoutParams

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
                            layoutTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutTabletResult20.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutTabletResult20.letterSpacing = -0.15f
                        }
                    }
                    layoutTabletResult20.text = tmp2.toString()
                    layoutTabletResult21.text = tmp.toString()
                }
                "천주교" -> {
                    layoutTabletResult21.visibility = View.VISIBLE
                    layoutTabletResult21.setLineSpacing(0f, 1.7f)

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
                            layoutTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutTabletResult22.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutTabletResult22.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutTabletResult22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutTabletResult22.letterSpacing = -0.17f
                        }
                    }
                    layoutTabletResult22.text = tmp2.toString()
                    layoutTabletResult21.text = tmp.toString()
                }
            }
        }else if(tabletType.contains("본관") && tabletType != "문구") {
            println("본관 진입")
            when (tabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    binding.layoutTabletResult10.visibility = View.GONE
                    binding.layoutTabletResult12.visibility = View.GONE
                    binding.layoutTabletResult0.visibility = View.VISIBLE

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
                            layoutTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutTabletResult0.text = tmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutTabletResult3.visibility = View.VISIBLE

                    layoutTabletResult32.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutTabletResult32.typeface = hyhaeso
                    layoutTabletResult32.text = "召天"

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutTabletResult30.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutTabletResult30.letterSpacing = -0.15f
                        }
                    }
                    layoutTabletResult30.text = tmp2.toString()
                    layoutTabletResult3.text = tmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    layoutTabletResult31.visibility = View.VISIBLE

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
                            layoutTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutTabletResult31.text = tmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    layoutTabletResult312.visibility = View.VISIBLE

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                            layoutTabletResult312.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutTabletResult32.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutTabletResult32.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutTabletResult32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutTabletResult32.letterSpacing = -0.17f
                        }
                    }
                    layoutTabletResult32.text = tmp2.toString()
                    layoutTabletResult312.text = tmp.toString()
                }
            }
        }else if(tabletType == "문구"){

        }

    }
    private fun setBoneTableMark() {
        // 이미지 이름을 문자열로 정의합니다.
        val boneTabletReligion = ApplicationClass.prefs.boneTabletReligion
        val boneTabletType = ApplicationClass.prefs.boneTabletType
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

        // 직분, 세례명, 법명
        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.layoutTablet2Result12.setImageResource(imageResource)
    }
    private fun setBoneTabletData() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)

        val selectedTabletName2 = ApplicationClass.prefs.selectedTabletName2
        if(selectedTabletName2 == "미정"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
        }
        else if(selectedTabletName2 == "기본"){
            newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)
        }
        binding.layoutTablet2Image.background = newBackground

        setBoneTableMark()
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
        var tabletName2 = ApplicationClass.prefs.tabletName2.toString()
        var name3 = ApplicationClass.prefs.name3.toString()
        var tmp = StringBuilder()
        var tmp2 = StringBuilder()
        var tabletReligion = ApplicationClass.prefs.tabletReligion
        var tabletType = ApplicationClass.prefs.tabletType.toString()

        var layoutTabletResult10 = binding.layoutTabletResult10
        var layoutTabletResult12 = binding.layoutTabletResult12
        var layoutTabletResult0 = binding.layoutTabletResult0
        var layoutTabletResult20 = binding.layoutTabletResult20
        var layoutTabletResult2 = binding.layoutTabletResult2
        var layoutTabletResult21 = binding.layoutTabletResult21
        var layoutTabletResult22 = binding.layoutTabletResult22
        var layoutTabletResult30 = binding.layoutTabletResult30
        var layoutTabletResult3 = binding.layoutTabletResult3
        var layoutTabletResult31 = binding.layoutTabletResult31
        var layoutTabletResult312 = binding.layoutTabletResult312
        var layoutTabletResult32 = binding.layoutTabletResult32

        // 합골
        var boneTabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
        var boneName3 = ApplicationClass.prefs.boneName3.toString()
        var boneTmp = StringBuilder()
        var boneTmp2 = StringBuilder()
        var boneTabletReligion = ApplicationClass.prefs.boneTabletReligion
        var boneTabletType = ApplicationClass.prefs.boneTabletType.toString()

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

        // 추가 정보 / 기존 정보
        if(ApplicationClass.prefs.boneTabletSex == "남성"){
            // 이름
            tabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
            name3 = ApplicationClass.prefs.boneName3.toString()
            tmp = StringBuilder()
            tmp2 = StringBuilder()
            tabletReligion = ApplicationClass.prefs.boneTabletReligion
            tabletType = ApplicationClass.prefs.boneTabletType.toString()

            layoutTabletResult10 = binding.layoutTablet2Result10
            layoutTabletResult12 = binding.layoutTablet2Result12
            layoutTabletResult0 = binding.layoutTablet2Result0
            layoutTabletResult20 = binding.layoutTablet2Result20
            layoutTabletResult2 = binding.layoutTablet2Result2
            layoutTabletResult21 = binding.layoutTablet2Result21
            layoutTabletResult22 = binding.layoutTablet2Result22
            layoutTabletResult30 = binding.layoutTablet2Result30
            layoutTabletResult3 = binding.layoutTablet2Result3
            layoutTabletResult31 = binding.layoutTablet2Result31
            layoutTabletResult312 = binding.layoutTablet2Result312
            layoutTabletResult32 = binding.layoutTablet2Result32

            // 합골
            boneTabletName2 = ApplicationClass.prefs.tabletName2.toString()
            boneName3 = ApplicationClass.prefs.name3.toString()
            boneTmp = StringBuilder()
            boneTmp2 = StringBuilder()
            boneTabletReligion = ApplicationClass.prefs.tabletReligion
            boneTabletType = ApplicationClass.prefs.tabletType.toString()

            layoutTablet2Result10 = binding.layoutTabletResult10
            layoutTablet2Result12 = binding.layoutTabletResult12
            layoutTablet2Result0 = binding.layoutTabletResult0
            layoutTablet2Result20 = binding.layoutTabletResult20
            layoutTablet2Result2 = binding.layoutTabletResult2
            layoutTablet2Result21 = binding.layoutTabletResult21
            layoutTablet2Result22 = binding.layoutTabletResult22
            layoutTablet2Result30 = binding.layoutTabletResult30
            layoutTablet2Result3 = binding.layoutTabletResult3
            layoutTablet2Result31 = binding.layoutTabletResult31
            layoutTablet2Result312 = binding.layoutTabletResult312
            layoutTablet2Result32 = binding.layoutTabletResult32
        }

        if(!tabletType.contains("본관") && tabletType != "문구") {
            when (tabletType) {
                "일반", "불교" -> {
                    layoutTabletResult2.visibility = View.VISIBLE

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
                            layoutTabletResult2.setLineSpacing(0f, 1.4f)
                        }
                    }
                    layoutTabletResult2.text = tmp.toString()
                }
                "기독교" -> {
                    layoutTabletResult21.visibility = View.VISIBLE

                    val layoutParams = layoutTabletResult21.layoutParams as ViewGroup.MarginLayoutParams
                    val marginTopInPixels = 0
                    layoutParams.topMargin = marginTopInPixels
                    layoutTabletResult21.setLineSpacing(0f, 1.7f)
                    layoutTabletResult21.layoutParams = layoutParams

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
                            layoutTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutTabletResult20.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutTabletResult20.letterSpacing = -0.15f
                        }
                    }
                    layoutTabletResult20.text = tmp2.toString()
                    layoutTabletResult21.text = tmp.toString()
                }
                "천주교" -> {
                    layoutTabletResult21.visibility = View.VISIBLE
                    layoutTabletResult21.setLineSpacing(0f, 1.7f)

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
                            layoutTabletResult21.setLineSpacing(0f, 1.2f)
                        }
                    }

                    layoutTabletResult22.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutTabletResult22.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutTabletResult22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutTabletResult22.letterSpacing = -0.17f
                        }
                    }
                    layoutTabletResult22.text = tmp2.toString()
                    layoutTabletResult21.text = tmp.toString()
                }
            }
        }else if(tabletType.contains("본관") && tabletType != "문구") {
            println("본관 진입")
            when (tabletType) {
                "일반(본관)" -> {
                    println("일반 진입")

                    layoutTabletResult10.visibility = View.GONE
                    layoutTabletResult12.visibility = View.GONE
                    layoutTabletResult0.visibility = View.VISIBLE

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
                            layoutTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutTabletResult0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutTabletResult0.text = tmp.toString()
                }
                "기독교(본관)" -> {
                    println("기독교 진입")

                    layoutTabletResult3.visibility = View.VISIBLE

                    layoutTabletResult32.visibility = View.VISIBLE
                    val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                    layoutTabletResult32.typeface = hyhaeso
                    layoutTabletResult32.text = "召天"

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutTabletResult3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
                            layoutTabletResult3.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutTabletResult30.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4 -> {
                            layoutTabletResult30.letterSpacing = -0.15f
                        }
                    }
                    layoutTabletResult30.text = tmp2.toString()
                    layoutTabletResult3.text = tmp.toString()
                }
                "불교(본관)" -> {
                    println("불교 진입")

                    layoutTabletResult31.visibility = View.VISIBLE

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
                            layoutTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6]).append("\n").append(name3[7]).append("\n").append(name3[8])
                            layoutTabletResult31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
                        }
                    }
                    layoutTabletResult31.text = tmp.toString()
                }
                "천주교(본관)" -> {
                    println("천주교 진입")

                    layoutTabletResult312.visibility = View.VISIBLE

                    when (name3.length) {
                        5 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4])
                        }
                        6 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                            layoutTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        7 -> {
                            tmp.append(name3[0]).append("\n").append(name3[1]).append("\n").append(name3[2])
                                .append("\n").append(name3[3]).append("\n").append(name3[4]).append("\n").append(name3[5])
                                .append("\n").append(name3[6])
                            layoutTabletResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                            layoutTabletResult312.setLineSpacing(0f, 1.0f)
                        }
                    }

                    layoutTabletResult32.visibility = View.VISIBLE
                    tmp2.append(tabletName2)
                    when (tabletName2.length) {
                        2 -> {
                        }
                        3 -> {
                        }
                        4, 5 -> {
                            layoutTabletResult32.letterSpacing = -0.15f
                        }
                        6 -> {
                            layoutTabletResult32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
                            layoutTabletResult32.letterSpacing = -0.17f
                        }
                    }
                    layoutTabletResult32.text = tmp2.toString()
                    layoutTabletResult312.text = tmp.toString()
                }
            }
        }else if(tabletType == "문구"){

        }

        // 합골
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
                            layoutTablet2Result22.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
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
                            layoutTablet2Result0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7]).append("\n").append(boneName3[8])
                            layoutTablet2Result0.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
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
                            layoutTablet2Result3.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_15.toFloat())
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
                            layoutTablet2Result31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        9 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6]).append("\n").append(boneName3[7]).append("\n").append(boneName3[8])
                            layoutTablet2Result31.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_17_5.toFloat())
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
                            layoutTablet2Result312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
                        }
                        7 -> {
                            boneTmp.append(boneName3[0]).append("\n").append(boneName3[1]).append("\n").append(boneName3[2])
                                .append("\n").append(boneName3[3]).append("\n").append(boneName3[4]).append("\n").append(boneName3[5])
                                .append("\n").append(boneName3[6])
                            layoutTablet2Result312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_20.toFloat())
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
                            layoutTablet2Result32.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_13.toFloat())
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
    private fun setMsg() {
        msg = "[태림원 주문]" +
                "\n${index++}. 발주자 정보" +
                "\n - 발주자명: " + ApplicationClass.prefs.leaderName +
                "\n - 발주자 전화번호: " + ApplicationClass.prefs.leaderTel +
                "\n - 소속: " + ApplicationClass.prefs.leaderDepartment

        if(ApplicationClass.prefs.clientName != "" && ApplicationClass.prefs.clientName != ""){
            msg += "\n\n${index++}. 상주 정보" +
                    "\n - 이름: " + ApplicationClass.prefs.clientName +
                    "\n - 전화번호: " + ApplicationClass.prefs.clientTel
        }

        msg += "\n\n${index++}. 발주 장소"

        val selectedLocation = ApplicationClass.prefs.selectedLocation
        if(selectedLocation.equals("화장장")){
            msg += "\n - 화장장소: " + ApplicationClass.prefs.cremationArea2 +
                    "\n - 화장시간: " + ApplicationClass.prefs.cremationTime
        } else if(selectedLocation.equals("장례식장")){
            msg += "\n - 장례식장 명: " + ApplicationClass.prefs.funeralName +
                    "\n - 호실: " + ApplicationClass.prefs.funeralNumber +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.funeralTime
        } else if(selectedLocation.equals("장지")){
            msg += "\n - 장지명: " + ApplicationClass.prefs.burialName +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.burialTime
        }

        msg += "\n\n${index++}. 유골함" +
                "\n - 함 종류: " + ApplicationClass.prefs.selectedUrnType +
                "\n - 함 명칭: " + ApplicationClass.prefs.selectedUrnName

        if(selectedUrnType != "선택안함"){
            msg += "\n - 각인 종류: " + ApplicationClass.prefs.engraveType + "[" + ApplicationClass.prefs.engraveType2 + "]"

            msg += "\n - 고인명: " + ApplicationClass.prefs.name1 +
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
                msg += "\n========== "

                msg += "\n합골 추가 정보" +
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

        msg += "\n\n${index++}. 위패" +
                "\n - 위패 종류: " + ApplicationClass.prefs.selectedTabletType +
                "\n - 위패 명칭: " + ApplicationClass.prefs.selectedTabletName
        if(selectedTabletType != "선택안함") {
            msg += "\n - 위패 상세 종류: " + ApplicationClass.prefs.tabletType

            if(!selectedTabletType.contains("사진")){
                msg += "\n - 위패 내용: " + ApplicationClass.prefs.name3
                if(!selectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.tabletType.toString().contains("기독교"))
                        msg += "\n - 직분: " + ApplicationClass.prefs.tabletName2
                    else if(ApplicationClass.prefs.tabletType.toString().contains("천주교"))
                        msg += "\n - 세례명: " + ApplicationClass.prefs.tabletName2
                }
            }
        }
        if(boneSelectedTabletType != "선택안함") {
            msg += "\n========== "
            msg += "\n합골 추가 정보" +
                    "\n - 합골 위패 종류: " + ApplicationClass.prefs.boneSelectedTabletType +
                    "\n - 합골 위패 명칭: " + ApplicationClass.prefs.selectedTabletName2
            msg += "\n - 위패 상세 종류: " + ApplicationClass.prefs.boneTabletType

            if(!boneSelectedTabletType.contains("사진")){
                msg += "\n - 위패 내용: " + ApplicationClass.prefs.boneName3
                if(!boneSelectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.boneTabletType.toString().contains("기독교"))
                        msg += "\n - 직분: " + ApplicationClass.prefs.boneTabletName2
                    else if(ApplicationClass.prefs.boneTabletType.toString().contains("천주교"))
                        msg += "\n - 세례명: " + ApplicationClass.prefs.boneTabletName2
                }
            }
            msg += "\n========== "
        }
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
            val selectedUrnName = ApplicationClass.prefs.selectedUrnName
            if(selectedUrnType != "선택안함") {
                if(selectedUrnType.contains("합골함")){
                    if(selectedUrnType.contains("합골함1")){
                        // 유골 정보
                        var layoutUrnContent = binding.layoutUrnContent

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

            // 위패 합골
            if(boneSelectedTabletType != "선택안함") {
                // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                // 1. XML 레이아웃
                val layoutTabletContent = binding.layoutTabletContent
                // 2. 레이아웃을 이미지로 변환
                tabletContentBitmap = Bitmap.createBitmap(layoutTabletContent.width, layoutTabletContent.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(tabletContentBitmap)
                layoutTabletContent.draw(canvas)

                // 1. XML 레이아웃
                val layoutTablet2Content = binding.layoutTablet2Content
                // 2. 레이아웃을 이미지로 변환
                boneTabletContentBitmap = Bitmap.createBitmap(layoutTablet2Content.width,layoutTablet2Content.height,Bitmap.Config.ARGB_8888)
                val boneCanvas = Canvas(boneTabletContentBitmap)
                layoutTablet2Content.draw(boneCanvas)

                // 1. XML 레이아웃
                var imageTabletImage = binding.imageTabletImage
                var imageTablet2Image = binding.imageTablet2Image
                var layoutTabletImage = binding.layoutTabletImage
                var layoutTablet2Image = binding.layoutTablet2Image
                var layoutTabletResultImage = binding.layoutTabletResultImage
                var layoutTablet2ResultImage = binding.layoutTablet2ResultImage

                if(ApplicationClass.prefs.boneTabletSex == "여성"){
                    imageTabletImage = binding.imageTablet2Image
                    imageTablet2Image = binding.imageTabletImage
                    layoutTabletImage = binding.layoutTablet2Image
                    layoutTablet2Image = binding.layoutTabletImage
                    layoutTabletResultImage = binding.layoutTablet2ResultImage
                    layoutTablet2ResultImage = binding.layoutTabletResultImage
                }

                imageTabletImage.setImageBitmap(tabletContentBitmap)
                imageTablet2Image.setImageBitmap(boneTabletContentBitmap)

                // 2. 레이아웃을 이미지로 변환
                tabletBitmap = Bitmap.createBitmap(layoutTabletImage.width, layoutTabletImage.height, Bitmap.Config.ARGB_8888)
                val canvas2 = Canvas(tabletBitmap)
                layoutTabletImage.draw(canvas2)

                // 2. 레이아웃을 이미지로 변환
                boneTabletBitmap = Bitmap.createBitmap(layoutTablet2Image.width,layoutTablet2Image.height,Bitmap.Config.ARGB_8888)
                val boneCanvas2 = Canvas(boneTabletBitmap)
                layoutTablet2Image.draw(boneCanvas2)

                layoutTabletResultImage.setImageBitmap(tabletBitmap)
                layoutTabletImage.visibility = View.GONE

                layoutTablet2ResultImage.setImageBitmap(boneTabletBitmap)
                layoutTablet2Image.visibility = View.GONE
            } else if(selectedTabletType != "선택안함") {
                // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                // 1. XML 레이아웃
                val layoutTabletContent = binding.layoutTabletContent

                // 2. 레이아웃을 이미지로 변환
                tabletContentBitmap = Bitmap.createBitmap(layoutTabletContent.width, layoutTabletContent.height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(tabletContentBitmap)
                layoutTabletContent.draw(canvas)

                var imageTabletImage = binding.imageTabletImage
                var layoutTabletImage = binding.layoutTabletImage
                var layoutTabletResultImage = binding.layoutTabletResultImage

                if(ApplicationClass.prefs.sex == "여성"){
                    imageTabletImage = binding.imageTablet2Image
                    layoutTabletImage = binding.layoutTablet2Image
                    layoutTabletResultImage = binding.layoutTablet2ResultImage
                }

                imageTabletImage.setImageBitmap(tabletContentBitmap)

                // 2. 레이아웃을 이미지로 변환
                tabletBitmap = Bitmap.createBitmap(layoutTabletImage.width, layoutTabletImage.height, Bitmap.Config.ARGB_8888)
                val canvas2 = Canvas(tabletBitmap)
                layoutTabletImage.draw(canvas2)

                layoutTabletResultImage.setImageBitmap(tabletBitmap)
                layoutTabletImage.visibility = View.GONE
            }
            binding.layoutResult.visibility = View.GONE
            binding.layoutResultContent.visibility = View.GONE
        }, delayMillis.toLong())
    }
    private fun setOnClickListeners() {
        binding.layoutZoom.setOnClickListener {
            val dialog = ResultDialogFragment()
            // 화면 밖 터치시 종료되지 않게 하기
            dialog.isCancelable = false


            val layoutResultImage = binding.layoutResultImage
            val resultBitmap = Bitmap.createBitmap(layoutResultImage.width, layoutResultImage.height, Bitmap.Config.ARGB_8888)
            val canvasBitmap = Canvas(resultBitmap)
            layoutResultImage.draw(canvasBitmap)

            dialog.setImageBitmap(resultBitmap)

            dialog.show(childFragmentManager, "show layout zoom")
        }

        binding.buttonResultToOrderFragment.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonMMS.setOnClickListener{
            if(checkInput()) {
                var msg2 = msg
                if (ApplicationClass.prefs.note == "")
                    msg2 += "\n\n${index}. 특이사항" +
                            "\n - 없음"
                else
                    msg2 += "\n\n${index}. 특이사항" +
                            "\n - " + ApplicationClass.prefs.note

                // 권한 확인
                val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
                val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

                val activityContext = activity as? Activity // 안전하게 캐스트
                if (activityContext != null) {
                    val readPermissionGranted = ContextCompat.checkSelfPermission(
                        activityContext,
                        readPermission
                    ) == PackageManager.PERMISSION_GRANTED
                    val writePermissionGranted = ContextCompat.checkSelfPermission(
                        activityContext,
                        writePermission
                    ) == PackageManager.PERMISSION_GRANTED

                    // 권한이 모두 허용되어 있는 경우
//                if (readPermissionGranted && writePermissionGranted) {
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
                    val imageUri = FileProvider.getUriForFile(
                        requireContext(),
                        "${requireContext().packageName}.fileprovider",
                        msgImageFile
                    )
                    Log.d(TAG, "imageUri: " + imageUri)


                    val tel = ApplicationClass.prefs.leaderTel.toString().replace("-", "")
                    val subject = "MMS 제목"
//                    val sms_body = "MMS 내용"

                    val sms_body = msg2

                    // 5. attachmentUri를 사용하여 MMS 메시지를 만들고 보냅니다.
                    sendMMS(tel, subject, sms_body, imageUri)
//                } else {
//                    // 하나 이상의 권한이 거부된 경우
//                    // 권한을 요청합니다.
//                    ActivityCompat.requestPermissions(
//                        activityContext,
//                        arrayOf(readPermission, writePermission),
//                        REQUEST_CODE_STORAGE_PERMISSION
//                    )
//                }
                } else {
                    // activityContext가 null인 경우에 대한 처리
                }
            }
        }
    }
    private fun observer() {
    }
    private fun checkInput(): Boolean {
        // 특이 사항 (30자 이내)
        val note = ApplicationClass.prefs.note.toString()
        if (note.length > 30) {
            toast("특이 사항을 30글자 이내로 입력해주세요.")
            return false
        }
        return true
    }

    // MMS 메시지 보내기 함수
    private fun sendMMS(tel: String, subject: String, sms_body: String, imageUri: Uri) {
        var intent = Intent(Intent.ACTION_SEND)
        if((ApplicationClass.prefs.name1 == "" && ApplicationClass.prefs.selectedTabletType == "선택안함")
            || (ApplicationClass.prefs.name3 == "" && ApplicationClass.prefs.selectedUrnType == "선택안함")
        ){
            intent.apply {
                type = "image/*"
                putExtra("address", tel)
                putExtra("sms_body", sms_body)
            }
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