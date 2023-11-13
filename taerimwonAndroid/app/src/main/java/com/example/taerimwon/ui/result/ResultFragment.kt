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
import android.view.MotionEvent
import android.view.ScaleGestureDetector
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
import com.example.taerimwon.ui.result.container.*
import com.example.taerimwon.ui.result.framelayout.*
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    val authViewModel: AuthViewModel by viewModels()
    private val REQUEST_CODE_STORAGE_PERMISSION = 101 // 권한 요청 코드
    private lateinit var selectedUrnType: String
    private lateinit var selectedUrnType2: String
    private lateinit var selectedTabletType: String
    private lateinit var boneSelectedTabletType: String
    private lateinit var selectedPyeongjangType: String
    private lateinit var selectedPyeongjangType2: String

    private lateinit var msg: String
    private lateinit var msgBitmap: Bitmap

    private lateinit var urnBitmap: Bitmap
    private lateinit var urnContentBitmap: Bitmap
    private lateinit var urnBitmap2: Bitmap
    private lateinit var urnContentBitmap2: Bitmap

    private lateinit var boneBitmap: Bitmap
    private lateinit var boneContentBitmap: Bitmap

    private lateinit var tabletBitmap: Bitmap
    private lateinit var tabletContentBitmap: Bitmap
    private lateinit var tablet2Bitmap: Bitmap
    private lateinit var tablet2ContentBitmap: Bitmap

    private lateinit var boneTabletBitmap: Bitmap
    private lateinit var boneTabletContentBitmap: Bitmap

    private lateinit var pyeongjangBitmap: Bitmap
    private lateinit var pyeongjangBitmap2: Bitmap

    private var index = 1;
    private var scale = 1.0f

    private lateinit var searchList: MutableList<String>

    override fun init() {
        initData()
        setImage()
        setOnClickListeners()
        observer()
    }
    // 최초 크기를 저장해둘 변수
    private var initialWidth1 = 0
    private var initialHeight1 = 0
    private var initialWidth1_1 = 0
    private var initialHeight1_1 = 0
    private var initialWidth1_2 = 0
    private var initialHeight1_2 = 0
    private var initialWidth1_3 = 0
    private var initialHeight1_3 = 0
    private var initialWidth1_4 = 0
    private var initialHeight1_4 = 0
    private var initialWidth1_5 = 0
    private var initialHeight1_5 = 0
    private var initialWidth1_6 = 0
    private var initialHeight1_6 = 0
    private var initialWidth1_7 = 0
    private var initialHeight1_7 = 0
    private var initialWidth1_8 = 0
    private var initialHeight1_8 = 0
    private var initialWidth1_9 = 0
    private var initialHeight1_9 = 0

    private var scaleFactor = 1.0f
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // scaleFactor를 사용하여 현재 확대/축소 비율 업데이트
            scaleFactor *= detector.scaleFactor

            // 최소 0.1배, 최대 5배까지 확대/축소
            scaleFactor = scaleFactor.coerceIn(0.5f, 5.0f)

            // LayoutParams를 사용하여 뷰의 크기 조절
//            val params = binding.layoutResultImage1.layoutParams
//            params.width = (initialWidth1 * scaleFactor).toInt()
//            params.height = (initialHeight1 * scaleFactor).toInt()
//            binding.layoutResultImage1.layoutParams = params

            val params1_1 = binding.layoutTabletResultImage.layoutParams
            params1_1.width = (initialWidth1_1 * scaleFactor).toInt()
            params1_1.height = (initialHeight1_1 * scaleFactor).toInt()
            binding.layoutTabletResultImage.layoutParams = params1_1

            val params1_2 = binding.layoutTabletPhoto.layoutParams
            params1_2.width = (initialWidth1_2 * scaleFactor).toInt()
            params1_2.height = (initialHeight1_2 * scaleFactor).toInt()
            binding.layoutTabletPhoto.layoutParams = params1_2

            val params1_3 = binding.layoutUrnResultImage12.layoutParams
            params1_3.width = (initialWidth1_3 * scaleFactor).toInt()
            params1_3.height = (initialHeight1_3 * scaleFactor).toInt()
            binding.layoutUrnResultImage12.layoutParams = params1_3

            val params1_4 = binding.layoutUrnResultImage.layoutParams
            params1_4.width = (initialWidth1_4 * scaleFactor).toInt()
            params1_4.height = (initialHeight1_4 * scaleFactor).toInt()
            binding.layoutUrnResultImage.layoutParams = params1_4

            val params1_5 = binding.layoutUrnResultImage22.layoutParams
            params1_5.width = (initialWidth1_5 * scaleFactor).toInt()
            params1_5.height = (initialHeight1_5 * scaleFactor).toInt()
            binding.layoutUrnResultImage22.layoutParams = params1_5

            val params1_6 = binding.layoutUrnResultImage2.layoutParams
            params1_6.width = (initialWidth1_6 * scaleFactor).toInt()
            params1_6.height = (initialHeight1_6 * scaleFactor).toInt()
            binding.layoutUrnResultImage2.layoutParams = params1_6

            val params1_7 = binding.layoutBoneResultImage.layoutParams
            params1_7.width = (initialWidth1_7 * scaleFactor).toInt()
            params1_7.height = (initialHeight1_7 * scaleFactor).toInt()
            binding.layoutBoneResultImage.layoutParams = params1_7

            val params1_8 = binding.layoutBone2ResultImage.layoutParams
            params1_8.width = (initialWidth1_8 * scaleFactor).toInt()
            params1_8.height = (initialHeight1_8 * scaleFactor).toInt()
            binding.layoutBone2ResultImage.layoutParams = params1_8

            val params1_9 = binding.layoutTablet2ResultImage.layoutParams
            params1_9.width = (initialWidth1_9 * scaleFactor).toInt()
            params1_9.height = (initialHeight1_9 * scaleFactor).toInt()
            binding.layoutTablet2ResultImage.layoutParams = params1_9

            return true

            // ViewPropertyAnimator를 사용하여 뷰에 애니메이션 적용
//            binding.layoutResultImage1.animate()
//                .scaleX(scaleFactor)
//                .scaleY(scaleFactor)
//                .setDuration(0) // 애니메이션 지속 시간을 0으로 설정하여 즉시 적용
//                .start()
        }
    }
    // 최초 크기를 저장해둘 변수
    private var initialWidth2 = 0
    private var initialHeight2 = 0
    private var initialWidth2_1 = 0
    private var initialHeight2_1 = 0
    private var initialWidth2_2 = 0
    private var initialHeight2_2 = 0
    private var initialWidth2_3 = 0
    private var initialHeight2_3 = 0
    private var initialWidth2_4 = 0
    private var initialHeight2_4 = 0
    private var scaleFactor2 = 1.0f
    private lateinit var scaleGestureDetector2: ScaleGestureDetector
    private inner class ScaleListener2 : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // scaleFactor를 사용하여 현재 확대/축소 비율 업데이트
            scaleFactor2 *= detector.scaleFactor

            // 최소 0.1배, 최대 5배까지 확대/축소
            scaleFactor2 = scaleFactor2.coerceIn(0.5f, 5.0f)

            // LayoutParams를 사용하여 뷰의 크기 조절
//            val params = binding.layoutResultImage3.layoutParams
//            params.width = (initialWidth2 * scaleFactor2).toInt()
//            params.height = (initialHeight2 * scaleFactor2).toInt()
//            binding.layoutResultImage3.layoutParams = params

            val params2_1 = binding.layoutPyeongjangResultImage.layoutParams
            params2_1.width = (initialWidth2_1 * scaleFactor2).toInt()
            params2_1.height = (initialHeight2_1 * scaleFactor2).toInt()
            binding.layoutPyeongjangResultImage.layoutParams = params2_1

            val params2_2 = binding.layoutPyeongjangResultImage2.layoutParams
            params2_2.width = (initialWidth2_2 * scaleFactor2).toInt()
            params2_2.height = (initialHeight2_2 * scaleFactor2).toInt()
            binding.layoutPyeongjangResultImage2.layoutParams = params2_2

            val params2_3 = binding.layoutPyeongjangResultImage3.layoutParams
            params2_3.width = (initialWidth2_3 * scaleFactor2).toInt()
            params2_3.height = (initialHeight2_3 * scaleFactor2).toInt()
            binding.layoutPyeongjangResultImage3.layoutParams = params2_3

            val params2_4 = binding.layoutPyeongjangResultImage4.layoutParams
            params2_4.width = (initialWidth2_4 * scaleFactor2).toInt()
            params2_4.height = (initialHeight2_4 * scaleFactor2).toInt()
            binding.layoutPyeongjangResultImage4.layoutParams = params2_4

            return true
        }
    }
    private fun setOnTouchListener(){
        // 최초 크기 저장
        initialWidth1 = binding.layoutResultImage1.width
        initialHeight1 = binding.layoutResultImage1.height

        initialWidth1_1 = binding.layoutTabletResultImage.width
        initialHeight1_1 = binding.layoutTabletResultImage.height

        initialWidth1_2 = binding.layoutTabletPhoto.width
        initialHeight1_2 = binding.layoutTabletPhoto.height

        initialWidth1_3 = binding.layoutUrnResultImage12.width
        initialHeight1_3 = binding.layoutUrnResultImage12.height

        initialWidth1_4 = binding.layoutUrnResultImage.width
        initialHeight1_4 = binding.layoutUrnResultImage.height

        initialWidth1_5 = binding.layoutUrnResultImage22.width
        initialHeight1_5 = binding.layoutUrnResultImage22.height

        initialWidth1_6 = binding.layoutUrnResultImage2.width
        initialHeight1_6 = binding.layoutUrnResultImage2.height

        initialWidth1_7 = binding.layoutBoneResultImage.width
        initialHeight1_7 = binding.layoutBoneResultImage.height

        initialWidth1_8 = binding.layoutBone2ResultImage.width
        initialHeight1_8 = binding.layoutBone2ResultImage.height

        initialWidth1_9 = binding.layoutTablet2ResultImage.width
        initialHeight1_9 = binding.layoutTablet2ResultImage.height

        initialWidth2 = binding.layoutResultImage3.width
        initialHeight2 = binding.layoutResultImage3.height

        initialWidth2_1 = binding.layoutPyeongjangResultImage.width
        initialHeight2_1 = binding.layoutPyeongjangResultImage.height

        initialWidth2_2 = binding.layoutPyeongjangResultImage2.width
        initialHeight2_2 = binding.layoutPyeongjangResultImage2.height

        initialWidth2_3 = binding.layoutPyeongjangResultImage3.width
        initialHeight2_3 = binding.layoutPyeongjangResultImage3.height

        initialWidth2_4 = binding.layoutPyeongjangResultImage4.width
        initialHeight2_4 = binding.layoutPyeongjangResultImage4.height

        // ScaleGestureDetector 초기화
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

        // 뷰에 터치 이벤트 리스너 설정
        binding.layoutResultImage1.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }

        // ScaleGestureDetector 초기화
        scaleGestureDetector2 = ScaleGestureDetector(requireContext(), ScaleListener2())

        // 뷰에 터치 이벤트 리스너 설정
        binding.layoutResultImage3.setOnTouchListener { _, event ->
            scaleGestureDetector2.onTouchEvent(event)
            true
        }


    }
    private fun initData() {
        searchList = mutableListOf()
        settingList()

        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        selectedUrnType2 = ApplicationClass.prefs.selectedUrnType2.toString()
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        boneSelectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
        selectedPyeongjangType = ApplicationClass.prefs.selectedPyeongjangType.toString()
        selectedPyeongjangType2 = ApplicationClass.prefs.selectedPyeongjangType2.toString()

        // result Fragment 추가
        val resultContainerFragment = ResultContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_result_container, resultContainerFragment)
            .commit()

        // layout_urn_content 추가
        val resultUrnFragment = ResultUrnFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_urn_content, resultUrnFragment)
            .commit()
        // layout_urn_content2 추가
        val resultUrn2Fragment = ResultUrn2Fragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_urn_content2, resultUrn2Fragment)
            .commit()

        // layout_bone_content 추가
        val resultBone1UrnFragment = ResultBone1UrnFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_bone_content, resultBone1UrnFragment)
            .commit()
        // layout_bone2_content 추가
        val resultBone2UrnFragment = ResultBone2UrnFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_bone2_content, resultBone2UrnFragment)
            .commit()
        // urn result Fragment 추가
        val urnResultContainerFragment = UrnResultContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_urn_result_container, urnResultContainerFragment)
            .commit()

        // layout_tablet_content 추가
        val resultTabletFragment = ResultTabletFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_tablet_content, resultTabletFragment)
            .commit()
        // layout_tablet2_content 추가
        val resultTablet2Fragment = ResultTablet2Fragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_tablet2_content, resultTablet2Fragment)
            .commit()

        // layout_bone_tablet_content 추가
        val resultBoneTabletFragment = ResultBoneTabletFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_bone_tablet_content, resultBoneTabletFragment)
            .commit()

        // tablet result Fragment 추가
        val tabletResultContainerFragment = TabletResultContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_tablet_result_container, tabletResultContainerFragment)
            .commit()

        // pyeongjang result Fragment 추가
        val pyeongjangResultContainerFragment = PyeongjangResultContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_pyeongjang_result_container, pyeongjangResultContainerFragment)
            .commit()

        // result Fragment 추가
        val resultContainerFragment2 = ResultContainerFragment2()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_result_container2, resultContainerFragment2)
            .commit()

        if(selectedUrnType != "선택안함") {
            binding.layoutResultTitleImage.visibility = View.VISIBLE
//            binding.layoutResultTitleImage1.visibility = View.VISIBLE
            binding.View2.visibility = View.VISIBLE
            binding.layoutUrnResultText.visibility = View.VISIBLE

            if(ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입2)")
                || ApplicationClass.prefs.selectedUrnName!!.contains("합골금띠")
                || ApplicationClass.prefs.selectedUrnName!!.contains("합골실버십장생")) {
                setBone2Data()
                binding.fragmentBone2Content.visibility = View.VISIBLE
                binding.layoutBone2.visibility = View.VISIBLE
                binding.layoutBone2ResultImage.visibility = View.VISIBLE
            }else {
                setUrnData()
                if (ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입1)") || ApplicationClass.prefs.selectedUrnName!!.contains("ZEN사각합골진공함")) {
                    setBone1Data()
                    binding.fragmentUrnContent.visibility = View.VISIBLE
                    binding.fragmentBoneContent.visibility = View.VISIBLE
                    binding.layoutBone1.visibility = View.VISIBLE
                    binding.layoutBoneResultImage.visibility = View.VISIBLE
                } else {
                    binding.layoutPre.visibility = View.VISIBLE
                    binding.layoutCurrent.visibility = View.VISIBLE
                    binding.layoutNext.visibility = View.VISIBLE

                    binding.fragmentUrnContent.visibility = View.VISIBLE
                    binding.layoutUrn.visibility = View.VISIBLE
                    binding.layoutUrnResultImage.visibility = View.VISIBLE
                }
            }
            if(selectedUrnType2 != "선택안함") {
                setUrnData2()
                binding.fragmentUrnContent2.visibility = View.VISIBLE
                binding.layoutUrn2.visibility = View.VISIBLE
                binding.layoutUrnResultImage2.visibility = View.VISIBLE
            }
        }else {
//            binding.layoutResultTitleImage1.visibility = View.GONE
            binding.View2.visibility = View.GONE
            binding.layoutUrnResultText.visibility = View.GONE
        }

        if(selectedTabletType != "선택안함") {
            binding.layoutResultTitleImage.visibility = View.VISIBLE
//            binding.layoutResultTitleImage2.visibility = View.VISIBLE
            binding.View3.visibility = View.VISIBLE
            binding.layoutTabletResultText.visibility = View.VISIBLE

            if(selectedTabletType.contains("합골")){
                setBoneTabletData()
                binding.fragmentBoneTabletContent.visibility = View.VISIBLE
                binding.layoutBoneTablet.visibility = View.VISIBLE
                binding.layoutBoneTabletResultImage.visibility = View.VISIBLE
            }
            else if(selectedTabletType.contains("사진")){
                setTabletData()
                binding.fragmentTabletContent.visibility = View.VISIBLE
                binding.layoutTablet.visibility = View.VISIBLE
                binding.layoutTabletPhoto.visibility = View.VISIBLE
                binding.layoutTabletResultImage.visibility = View.VISIBLE
                // 이미지 경로(URI)를 SharedPreferences에서 가져옴
                val tabletImageUri = ApplicationClass.prefs.tabletImageUri

                if (tabletImageUri != "") {
                    val imageUri = Uri.parse(tabletImageUri)
                    binding.imageAddPhoto.setImageURI(imageUri)
                }
            }else if(ApplicationClass.prefs.tabletType.toString().contains("문구")){
                binding.fragmentTabletContent.visibility = View.VISIBLE
                binding.layoutTablet.visibility = View.VISIBLE
                binding.layoutTabletResultImage.visibility = View.VISIBLE
            }else {
                setTabletData()
                binding.fragmentTabletContent.visibility = View.VISIBLE
                binding.layoutTablet.visibility = View.VISIBLE
                binding.layoutTabletResultImage.visibility = View.VISIBLE
            }

            if(boneSelectedTabletType != "선택안함") {
                if(boneSelectedTabletType.contains("사진")){
                    setTablet2Data()
                    binding.fragmentTablet2Content.visibility = View.VISIBLE
                    binding.layoutTablet2.visibility = View.VISIBLE
                    binding.layoutTablet2Photo.visibility = View.VISIBLE
                    binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                    // 이미지 경로(URI)를 SharedPreferences에서 가져옴
                    val boneTabletImageUri = ApplicationClass.prefs.boneTabletImageUri

                    if (boneTabletImageUri != "") {
                        val imageUri = Uri.parse(boneTabletImageUri)
                        binding.imageBoneAddPhoto.setImageURI(imageUri)
                    }
                }else if(ApplicationClass.prefs.boneTabletType.toString().contains("문구")){
                    binding.fragmentTablet2Content.visibility = View.VISIBLE
                    binding.layoutTablet2.visibility = View.VISIBLE
                    binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                }else {
                    setTablet2Data()
                    binding.fragmentTablet2Content.visibility = View.VISIBLE
                    binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                    binding.layoutTablet2.visibility = View.VISIBLE
                }
            }
        }else{
//            binding.layoutResultTitleImage2.visibility = View.GONE
            binding.View3.visibility = View.GONE
            binding.layoutTabletResultText.visibility = View.GONE
        }

        if(selectedPyeongjangType != "선택안함") {
            binding.layoutResultTitleImage3.visibility = View.VISIBLE
            binding.layoutPyeongjangResultText.visibility = View.VISIBLE

            setPyeongjangData()
            if(selectedPyeongjangType2 != "선택안함") {
                setPyeongjangData2()
            }
        }else{
            binding.layoutResultTitleImage3.visibility = View.GONE
            binding.layoutPyeongjangResultText.visibility = View.GONE
        }

        setMsg()
//        authViewModel.getBlackList()
//        if(!ApplicationClass.prefs.authenticated)
//            findNavController().navigate(R.id.action_resultFragment_to_phoneAuthFragment)
    }
    private fun setUrnData() {
        var selectedUrnName = ApplicationClass.prefs.selectedUrnName
        var layoutUrnImage = binding.layoutUrnImage
        var layoutUrnResultImage12 = binding.layoutUrnResultImage12
        var layoutUrnResultImage = binding.layoutUrnResultImage

        var imageUrnImageBackgound = binding.imageUrnImageBackgound
        var layoutParamsUrnBackgound = imageUrnImageBackgound.layoutParams
        var imageUrnImage = binding.imageUrnImage
        var layoutParamsUrn = imageUrnImage.layoutParams
        var imageUrnImageUnder = binding.imageUrnImageUnder
        val layoutParamsUnder = imageUrnImageUnder.layoutParams

        var imageUrnImageLeft = binding.imageUrnImageLeft
        val layoutParamsLeft = imageUrnImageLeft.layoutParams

        var imageUrnImageRight = binding.imageUrnImageRight
        val layoutParamsRight = imageUrnImageRight.layoutParams


        var urnType = ApplicationClass.prefs.selectedUrnType

        if(!selectedUrnType.contains("선택안함") && (ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입1)") || ApplicationClass.prefs.selectedUrnName!!.contains("ZEN사각합골진공함") || !selectedUrnType2.contains("선택안함"))) {
            if(ApplicationClass.prefs.boneSex == "남성"){
                urnType = ApplicationClass.prefs.selectedUrnType2
                selectedUrnName = ApplicationClass.prefs.selectedUrnName2
            }
        }

        if(urnType == "유골함"){
//        if(selectedUrnType == "유골함"){
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

            when (selectedUrnName) {
                "미정" -> {
                    val layoutParams3 = binding.layoutUrnImage.layoutParams
                    layoutParams3.width = 360
                    layoutParams3.height = 580
                    binding.layoutUrnImage.layoutParams = layoutParams3

                    val layoutParams = imageUrnImage.layoutParams
                    layoutParams.width = 360
                    layoutParams.height = 580
                    imageUrnImage.layoutParams = layoutParams

                    val layoutParams2 = imageUrnImageUnder.layoutParams
                    layoutParams2.height = 10
                    imageUrnImageUnder.layoutParams = layoutParams2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
                }
                // 1. 일반 밀봉진공함
                "도원기독교 DW-3 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_1)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270
                    layoutParamsUnder.height = 90
                }
                "도원불교 DW-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_2)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 17
                }
                "도원천주교 DW-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_3)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90
                }
                "도원칼라난 DW-2 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_4)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 90
                }
                "도원칼라송학 DW-1 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_5)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 90
                }
                "도화청꽃 DH-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_6)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90
                }
                "도화홍꽃 DH-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_7)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90
                }
                "소담난 SDN-2 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_8)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "소담송학 SDS-1 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_9)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 130
                }
                // 2. 일반함
                "매화조각민트 MH-2 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_1)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 110
                }
                "매화조각핑크 MH-1 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_2)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 110
                }
                "매화조각화이트 MH-3 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_3)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 90
                }
                "원통난 WT-1 3307" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_4)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 90
                }
                "원통송학 WT-2 3307" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_5)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 80

                    layoutParamsLeft.width = 15
                }
                "황토기독교 DR-1 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_6)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 80

                    layoutParamsRight.width = 15
                }
                "황토난 DR-5 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_7)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 60

                    layoutParamsRight.width = 15
                }
                "황토무지 DR-8 2505" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_8)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 60
                }
                "황토불교 DR-2 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_9)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 70

                    layoutParamsRight.width = 15
                }
                "황토송학 DR-4 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_10)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 90
                }
                "황토천주교 DR-3 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_11)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 75
                }
                "황토청꽃 DR-6 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_12)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 75
                }
                "황토홍꽃 DR-7 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_13)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 75
                }

                // 3. 이중 밀봉진공함
                "이중기독교 EG-1 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_1)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "이중밀봉블랙자개 EMR-1 5020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_2)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 71
                }
                "이중밀봉송학 EM-1 4411" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_3)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 71
                }
                "이중백자 EW-6 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_4)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120
                }
                "이중불교 EBB-2 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_5)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 15
                }
                "이중진주운학 EW-6 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_6)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "이중천주교 EC-3 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_7)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "이중칼라난 EH-5 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_8)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "이중칼라송학 EH-4 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_9)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 140
                }

                // 4. 잠금형 삼중 명품자개함
                "아름골드자개 ARG-1 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_1)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "아름꽃자개 ARF-2 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_2)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "아름화이트자개 SGS-3 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_3)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안골드자개 HUG-16 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_4)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안백십장생자개 HUG-17 12535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_5)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn4_5))
                    layoutParamsUrnBackgound.width = 170
                    layoutParamsUrnBackgound.height = 240

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안블랙자개 HUG-15 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_6)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안홍한지십장생자개 HUG-19 13139" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_7)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn4_7))
                    layoutParamsUrnBackgound.width = 170
                    layoutParamsUrnBackgound.height = 240

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안화이트자개 HUG-14 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_8)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110

                    layoutParamsRight.width = 151
                }
                "휴안흑십장생자개 HUG-18 12535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_9)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn4_9))
                    layoutParamsUrnBackgound.width = 170
                    layoutParamsUrnBackgound.height = 240

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }

                // 5. 잠금형 삼중실크 밀봉진공함
                "안향궁 AN-1 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_1)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 40
                }
                "안향천향 AN-2 8619.png" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_2)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 70
                }
                "휴안궁 HU-1 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_3)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn5_3))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "휴안그린난 HGN-2 9020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_4)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
                "휴안그린송학 HGS-1 9020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_5)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "휴안기독교 HUG-1 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_6)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안루엔골드 HLG-1 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_7)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안루엔화이트 HLS-2 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_8)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안명성 HU-3 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_9)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn5_9))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안불교 HUB-2 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_10)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안상아난 HSN-20 8718" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_11)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "휴안상아학 HSH-9 8718" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_12)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "휴안샤이니블루보석 HU-6 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_13)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 110
                }
                "휴안실버당초보석 HU-5 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_14)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "휴안오로라블루 HUO-3 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_15)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 15
                }
                "휴안오로라실버 HUO-1 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_16)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 15
                }
                "휴안오로라핑크 HUO-2 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_17)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "휴안천궁기독교 CGG-2 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_18)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천궁불교 CGB-3 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_19)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천궁일반 CG-1 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_20)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천궁천주교 CGC-4 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_21)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천주교 HUC-3 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_22)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "휴안천향 HU-2 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_23)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn5_23))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "휴안핑크당초보석 HU-4 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_24)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 130
                }
                "휴안화이트기독교 HUG-7 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_25)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 135

                    layoutParamsLeft.width = 15
                }
                "휴안화이트불교 HU-8 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_26)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 140

                    layoutParamsLeft.width = 15
                }
                "휴안화이트천주교 HU-9 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_27)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 135

                    layoutParamsLeft.width = 15
                }

                // 6. 소함
                "소함난 EB-1 3713" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6_1)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "소함송학 EB-2 3713" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6_2)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }

                // 7. 수목함
                "운학수목함 WH-1 1505" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn7_1)
                }
                "황토수목함 HT-1 1604" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn7_2)
                }

                // 8. 스크류(잠금형)고급 진공함
                "봉분궁 BOB-3 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_1)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 90
                }
                "봉분기독교 BOB-6 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_2)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 100

                    layoutParamsRight.width = 15
                }
                "봉분난 BOB-2 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_3)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 135

                    layoutParamsLeft.width = 21
                }
                "봉분명성 BOB-4 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_4)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 120
                }
                "봉분불교 BOB-7 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_5)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 140
                }
                "봉분송학 BOB-1 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_6)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 160
                }
                "봉분천주교 BOB-8 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_7)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 140
                }
                "봉분천향 BOB-5 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_8)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn8_8))
                    layoutParamsUrnBackgound.width = 150
                    layoutParamsUrnBackgound.height = 210

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 120
                }
                "아름궁 AR-3 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_9)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "아름기독교 AR-9-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_10)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "아름난 AR-2 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_11)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 120
                }
                "아름명성 AR-4 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_12)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "아름불교-AR-10-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_13)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "아름선궁 AR-6 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_14)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 50
                }
                "아름선명성 AR-7 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_15)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 80
                }
                "아름선천향 AR-8 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_16)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 95
                }
                "아름송학 AR-1 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_17)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 120
                }
                "아름천주교-AR-11-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_18)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "아름천향 AR-5 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_19)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 100
                }
                "태림조각기독교 TA-2 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_20)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "태림조각불교 TA-3 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_21)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "태림조각일반 TA-1 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_22)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "태림조각천주교 TA-4 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_23)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }

                // 9. 황금함
                "황금십장생 WGS-1 18040" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_1)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 85
                }
                "황실황금기독교 HSG-2 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_2)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 235

                    layoutParamsUnder.height = 80
                }
                "황실황금불교 HSB-3 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_3)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 235

                    layoutParamsUnder.height = 85
                }
                "황실황금송학 HSS-5 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_4)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 235

                    layoutParamsUnder.height = 80
                }
                "황실황금천주교 HSC-4 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_5)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 8
                }
                "황제황금함 ZE-14 20180" -> {
                    // 밑 내용
                    val layoutParams = layoutUrnImage.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage12.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage12.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_6)
                    layoutUrnResultImage12.visibility = View.VISIBLE
                    layoutUrnResultImage12.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }

                // 10. KS인증 ZEN한국도자기
                "국화 ZE-1 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_1)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_1))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "사군자 ZE-8 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_2)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "선궁 ZE-3 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_3)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_3))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "소망 ZE-6 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_4)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_4))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "수복 ZE-10 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_5)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 160
                }
                "십장생 ZE-7 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_6)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 160
                }
                "아일렛 ZE-4 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_7)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "옥자수 ZE-9 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_8)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
                "조각기독교 ZE-11 1035" -> {
                    val layoutParams = layoutUrnImage.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage12.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage12.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_9)
                    layoutUrnResultImage12.visibility = View.VISIBLE
                    layoutUrnResultImage12.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }
                "조각불교 ZE-12 1035" -> {
                    val layoutParams = layoutUrnImage.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage12.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage12.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_10)
                    layoutUrnResultImage12.visibility = View.VISIBLE
                    layoutUrnResultImage12.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }
                "조각천주교 ZE-13 1035" -> {
                    val layoutParams = layoutUrnImage.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage12.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage12.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_11)
                    layoutUrnResultImage12.visibility = View.VISIBLE
                    layoutUrnResultImage12.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }
                "청연 ZE-2 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_12)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_12))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
                "화접도 ZE-5 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_13)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
            }
            layoutUrnImage.background = newBackground

            imageUrnImageBackgound.layoutParams = layoutParamsUrnBackgound
            imageUrnImage.layoutParams = layoutParamsUrn
            imageUrnImageUnder.layoutParams = layoutParamsUnder
            imageUrnImageLeft.layoutParams = layoutParamsLeft
            imageUrnImageRight.layoutParams = layoutParamsRight
        }
    }
    private fun setUrnData2() {
        var selectedUrnName2 = ApplicationClass.prefs.selectedUrnName2
        var layoutUrnImage2 = binding.layoutUrnImage2
        var layoutUrnResultImage22 = binding.layoutUrnResultImage22
        var layoutUrnResultImage2 = binding.layoutUrnResultImage2

        var imageUrnImageBackgound = binding.imageUrnImage2Backgound
        var layoutParamsUrnBackgound = imageUrnImageBackgound.layoutParams
        var imageUrnImage2 = binding.imageUrnImage2
        var layoutParamsUrn = imageUrnImage2.layoutParams
        var imageUrnImageUnder = binding.imageUrnImage2Under
        val layoutParamsUnder = imageUrnImageUnder.layoutParams

        var imageUrnImageLeft = binding.imageUrnImage2Left
        val layoutParamsLeft = imageUrnImageLeft.layoutParams

        var imageUrnImageRight = binding.imageUrnImage2Right
        val layoutParamsRight = imageUrnImageRight.layoutParams

        var urnType2 = ApplicationClass.prefs.selectedUrnType2

        if(!selectedUrnType.contains("선택안함") && (ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입1)") || ApplicationClass.prefs.selectedUrnName!!.contains("ZEN사각합골진공함") || !selectedUrnType2.contains("선택안함"))) {
            if(ApplicationClass.prefs.boneSex == "남성"){
                urnType2 = ApplicationClass.prefs.selectedUrnType
                selectedUrnName2 = ApplicationClass.prefs.selectedUrnName
            }
        }

        if(urnType2 == "유골함"){
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

            when (selectedUrnName2) {
                "미정" -> {
                    val layoutParams3 = binding.layoutUrnImage2.layoutParams
                    layoutParams3.width = 360
                    layoutParams3.height = 580
                    binding.layoutUrnImage2.layoutParams = layoutParams3

                    val layoutParams = imageUrnImage2.layoutParams
                    layoutParams.width = 360
                    layoutParams.height = 580
                    imageUrnImage2.layoutParams = layoutParams

                    val layoutParams2 = imageUrnImageUnder.layoutParams
                    layoutParams2.height = 10
                    imageUrnImageUnder.layoutParams = layoutParams2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
                }
                // 1. 일반 밀봉진공함
                "도원기독교 DW-3 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_1)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270
                    layoutParamsUnder.height = 90
                }
                "도원불교 DW-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_2)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 17
                }
                "도원천주교 DW-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_3)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90
                }
                "도원칼라난 DW-2 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_4)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 90
                }
                "도원칼라송학 DW-1 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_5)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 90
                }
                "도화청꽃 DH-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_6)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90
                }
                "도화홍꽃 DH-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_7)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 90
                }
                "소담난 SDN-2 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_8)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "소담송학 SDS-1 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_9)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 130
                }
                // 2. 일반함
                "매화조각민트 MH-2 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_1)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 110
                }
                "매화조각핑크 MH-1 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_2)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 110
                }
                "매화조각화이트 MH-3 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_3)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 90
                }
                "원통난 WT-1 3307" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_4)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 90
                }
                "원통송학 WT-2 3307" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_5)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 80

                    layoutParamsLeft.width = 15
                }
                "황토기독교 DR-1 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_6)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 80

                    layoutParamsRight.width = 15
                }
                "황토난 DR-5 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_7)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 60

                    layoutParamsRight.width = 15
                }
                "황토무지 DR-8 2505" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_8)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 60
                }
                "황토불교 DR-2 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_9)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 70

                    layoutParamsRight.width = 15
                }
                "황토송학 DR-4 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_10)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 90
                }
                "황토천주교 DR-3 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_11)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 75
                }
                "황토청꽃 DR-6 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_12)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 75
                }
                "황토홍꽃 DR-7 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_13)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 220

                    layoutParamsUnder.height = 75
                }

                // 3. 이중 밀봉진공함
                "이중기독교 EG-1 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_1)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "이중밀봉블랙자개 EMR-1 5020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_2)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 71
                }
                "이중밀봉송학 EM-1 4411" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_3)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 71
                }
                "이중백자 EW-6 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_4)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 120
                }
                "이중불교 EBB-2 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_5)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsRight.width = 15
                }
                "이중진주운학 EW-6 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_6)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "이중천주교 EC-3 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_7)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "이중칼라난 EH-5 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_8)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "이중칼라송학 EH-4 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_9)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 140
                }

                // 4. 잠금형 삼중 명품자개함
                "아름골드자개 ARG-1 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_1)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "아름꽃자개 ARF-2 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_2)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "아름화이트자개 SGS-3 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_3)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안골드자개 HUG-16 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_4)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안백십장생자개 HUG-17 12535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_5)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn4_5))
                    layoutParamsUrnBackgound.width = 170
                    layoutParamsUrnBackgound.height = 240

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안블랙자개 HUG-15 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_6)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안홍한지십장생자개 HUG-19 13139" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_7)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn4_7))
                    layoutParamsUrnBackgound.width = 170
                    layoutParamsUrnBackgound.height = 240

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "휴안화이트자개 HUG-14 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_8)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110

                    layoutParamsRight.width = 151
                }
                "휴안흑십장생자개 HUG-18 12535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_9)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn4_9))
                    layoutParamsUrnBackgound.width = 170
                    layoutParamsUrnBackgound.height = 240

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }

                // 5. 잠금형 삼중실크 밀봉진공함
                "안향궁 AN-1 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_1)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 40
                }
                "안향천향 AN-2 8619.png" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_2)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 70
                }
                "휴안궁 HU-1 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_3)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn5_3))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "휴안그린난 HGN-2 9020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_4)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
                "휴안그린송학 HGS-1 9020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_5)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "휴안기독교 HUG-1 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_6)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안루엔골드 HLG-1 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_7)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안루엔화이트 HLS-2 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_8)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안명성 HU-3 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_9)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn5_9))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안불교 HUB-2 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_10)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 70
                }
                "휴안상아난 HSN-20 8718" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_11)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 15
                }
                "휴안상아학 HSH-9 8718" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_12)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "휴안샤이니블루보석 HU-6 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_13)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 110
                }
                "휴안실버당초보석 HU-5 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_14)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "휴안오로라블루 HUO-3 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_15)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 15
                }
                "휴안오로라실버 HUO-1 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_16)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120

                    layoutParamsRight.width = 15
                }
                "휴안오로라핑크 HUO-2 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_17)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "휴안천궁기독교 CGG-2 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_18)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천궁불교 CGB-3 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_19)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천궁일반 CG-1 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_20)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천궁천주교 CGC-4 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_21)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 260

                    layoutParamsUnder.height = 100
                }
                "휴안천주교 HUC-3 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_22)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "휴안천향 HU-2 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_23)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn5_23))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "휴안핑크당초보석 HU-4 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_24)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 130
                }
                "휴안화이트기독교 HUG-7 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_25)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 135

                    layoutParamsLeft.width = 15
                }
                "휴안화이트불교 HU-8 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_26)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 140

                    layoutParamsLeft.width = 15
                }
                "휴안화이트천주교 HU-9 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_27)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 135

                    layoutParamsLeft.width = 15
                }

                // 6. 소함
                "소함난 EB-1 3713" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6_1)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 140

                    layoutParamsLeft.width = 15
                }
                "소함송학 EB-2 3713" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6_2)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 140

                    layoutParamsLeft.width = 15
                }

                // 7. 수목함
                "운학수목함 WH-1 1505" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn7_1)
                }
                "황토수목함 HT-1 1604" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn7_2)
                }

                // 8. 스크류(잠금형)고급 진공함
                "봉분궁 BOB-3 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_1)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 90
                }
                "봉분기독교 BOB-6 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_2)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 100

                    layoutParamsRight.width = 15
                }
                "봉분난 BOB-2 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_3)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 135

                    layoutParamsLeft.width = 21
                }
                "봉분명성 BOB-4 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_4)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 120
                }
                "봉분불교 BOB-7 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_5)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 140
                }
                "봉분송학 BOB-1 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_6)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 160
                }
                "봉분천주교 BOB-8 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_7)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 140
                }
                "봉분천향 BOB-5 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_8)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn8_8))
                    layoutParamsUrnBackgound.width = 150
                    layoutParamsUrnBackgound.height = 210

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 120
                }
                "아름궁 AR-3 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_9)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "아름기독교 AR-9-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_10)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "아름난 AR-2 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_11)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 120
                }
                "아름명성 AR-4 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_12)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "아름불교-AR-10-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_13)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "아름선궁 AR-6 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_14)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 50
                }
                "아름선명성 AR-7 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_15)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 80
                }
                "아름선천향 AR-8 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_16)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 95
                }
                "아름송학 AR-1 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_17)

                    layoutParamsUrn.width = 160
                    layoutParamsUrn.height = 230

                    layoutParamsUnder.height = 120
                }
                "아름천주교-AR-11-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_18)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 120
                }
                "아름천향 AR-5 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_19)

                    layoutParamsUrn.width = 180
                    layoutParamsUrn.height = 270

                    layoutParamsUnder.height = 100
                }
                "태림조각기독교 TA-2 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_20)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "태림조각불교 TA-3 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_21)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "태림조각일반 TA-1 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_22)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }
                "태림조각천주교 TA-4 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_23)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 110
                }

                // 9. 황금함
                "황금십장생 WGS-1 18040" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_1)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 210

                    layoutParamsUnder.height = 85
                }
                "황실황금기독교 HSG-2 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_2)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 235

                    layoutParamsUnder.height = 80
                }
                "황실황금불교 HSB-3 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_3)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 235

                    layoutParamsUnder.height = 85
                }
                "황실황금송학 HSS-5 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_4)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 235

                    layoutParamsUnder.height = 80
                }
                "황실황금천주교 HSC-4 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_5)

                    layoutParamsUrn.width = 150
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 90

                    layoutParamsLeft.width = 8
                }
                "황제황금함 ZE-14 20180" -> {
                    // 밑 내용
                    val layoutParams = layoutUrnImage2.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage2.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage2.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage2.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage22.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage22.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_6)
                    layoutUrnResultImage22.visibility = View.VISIBLE
                    layoutUrnResultImage22.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }

                // 10. KS인증 ZEN한국도자기
                "국화 ZE-1 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_1)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_1))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "사군자 ZE-8 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_2)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "선궁 ZE-3 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_3)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_3))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "소망 ZE-6 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_4)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_4))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 90
                }
                "수복 ZE-10 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_5)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 160
                }
                "십장생 ZE-7 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_6)

                    layoutParamsUrn.width = 170
                    layoutParamsUrn.height = 240

                    layoutParamsUnder.height = 160
                }
                "아일렛 ZE-4 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_7)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 80
                }
                "옥자수 ZE-9 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_8)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
                "조각기독교 ZE-11 1035" -> {
                    // 밑 내용
                    val layoutParams = layoutUrnImage2.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage2.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage2.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage2.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage22.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage22.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_9)
                    layoutUrnResultImage22.visibility = View.VISIBLE
                    layoutUrnResultImage22.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }
                "조각불교 ZE-12 1035" -> {
                    // 밑 내용
                    val layoutParams = layoutUrnImage2.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage2.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage2.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage2.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage22.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage22.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_10)
                    layoutUrnResultImage22.visibility = View.VISIBLE
                    layoutUrnResultImage22.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)                }
                "조각천주교 ZE-13 1035" -> {
                    // 밑 내용
                    val layoutParams = layoutUrnImage2.layoutParams
                    layoutParams.width = 200
                    layoutParams.height = 400
                    layoutUrnImage2.layoutParams = layoutParams

                    layoutParamsUrn.width = 125
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 50

                    // 위 내용
                    val dpWidth = 40 // 변경하려는 너비(dp)
                    val dpHeight = 80 // 변경하려는 높이(dp)

                    val scale2 = resources.displayMetrics.density

                    val pixelWidth = (dpWidth * scale2 + 0.5f).toInt()
                    val pixelHeight = (dpHeight * scale2 + 0.5f).toInt()

                    val layoutParams4 = layoutUrnResultImage2.layoutParams
                    layoutParams4.width = pixelWidth
                    layoutParams4.height = pixelHeight
                    layoutUrnResultImage2.layoutParams = layoutParams4

                    val dpWidth2 = 90 // 변경하려는 너비(dp)
                    val dpHeight2 = 100 // 변경하려는 높이(dp)

                    val scale3 = resources.displayMetrics.density

                    val pixelWidth2 = (dpWidth2 * scale3 + 0.5f).toInt()
                    val pixelHeight2 = (dpHeight2 * scale3 + 0.5f).toInt()

                    val layoutParams3 = layoutUrnResultImage22.layoutParams
                    layoutParams3.width = pixelWidth2
                    layoutParams3.height = pixelHeight2
                    layoutUrnResultImage22.layoutParams = layoutParams3

                    val newBackground2 = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_11)
                    layoutUrnResultImage22.visibility = View.VISIBLE
                    layoutUrnResultImage22.background = newBackground2

                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                }
                "청연 ZE-2 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_12)

                    imageUrnImageBackgound.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.img_urn10_12))
                    layoutParamsUrnBackgound.width = 200
                    layoutParamsUrnBackgound.height = 300

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
                "화접도 ZE-5 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_13)

                    layoutParamsUrn.width = 200
                    layoutParamsUrn.height = 300

                    layoutParamsUnder.height = 100
                }
            }
            imageUrnImageBackgound.layoutParams = layoutParamsUrnBackgound
            layoutUrnImage2.background = newBackground
            imageUrnImageUnder.layoutParams = layoutParamsUnder
            imageUrnImageLeft.layoutParams = layoutParamsLeft
            imageUrnImageRight.layoutParams = layoutParamsRight
        }

    }
    private fun setBone1Data() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        var layoutBone1Image = binding.layoutBone1Image

        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        when (selectedUrnName) {
            "미정(타입1)" -> {
                val layoutParams3 = binding.layoutBone1Image.layoutParams
                layoutParams3.width = 460
                layoutParams3.height = 400
                binding.layoutBone1Image.layoutParams = layoutParams3

                val layoutParams = binding.imageBone1Image1.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.marginStart = 5
                layoutParams.bottomMargin = 10
                binding.imageBone1Image1.layoutParams = layoutParams

                val layoutParams2 = binding.imageBone1Image2.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams2.marginEnd = 5
                layoutParams2.bottomMargin = 10
                binding.imageBone1Image2.layoutParams = layoutParams2

                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
            "ZEN사각합골진공함-HG-3-8228" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1_1)
            }
        }
        layoutBone1Image.background = newBackground
    }
    private fun setBone2Data() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
        var layoutBone2Image = binding.layoutBone2Image

        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        when (selectedUrnName) {
            "미정(타입2)" -> {
                val layoutParams3 = binding.layoutBone2Image.layoutParams
                layoutParams3.width = 420
                layoutParams3.height = 420
                binding.layoutBone2Image.layoutParams = layoutParams3

                val layoutParams = binding.imageBone2Image.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.width = 400
                layoutParams.height = 400
                layoutParams.bottomMargin = 0
                binding.imageBone2Image.layoutParams = layoutParams

                val layoutParams2 = binding.imageBone2Image2.layoutParams
                layoutParams2.height = 10
                binding.imageBone2Image2.layoutParams = layoutParams2

                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
            "합골금띠 HG-1 4612" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2_1)
                binding.imageBone2Image2.visibility = View.GONE
            }
            "합골실버십장생 HG-2 4914" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2_2)
                binding.imageBone2Image2.visibility = View.VISIBLE
            }
        }
        layoutBone2Image.background = newBackground
    }
    private fun setTabletData() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        var selectedTabletName = ApplicationClass.prefs.selectedTabletName
        var layoutTabletImage = binding.layoutTabletImage
        var imageTabletImage = binding.imageTabletImage


        if(!selectedTabletType.contains("선택안함") && !selectedTabletType.contains("합골") && !boneSelectedTabletType.contains("선택안함")) {
            if(ApplicationClass.prefs.boneTabletSex == "남성"){
                selectedTabletName = ApplicationClass.prefs.selectedTabletName2
//                layoutTabletImage = binding.layoutTablet2Image
//
//                imageTabletImage = binding.imageTablet2Image
//                imageTabletImage2 = binding.imageTablet2Image2
            }
        }

        when (selectedTabletName) {
            "미정" -> {
                val layoutParams3 = binding.layoutTabletImage.layoutParams
                layoutParams3.width = 140
                layoutParams3.height = 430
                binding.layoutTabletImage.layoutParams = layoutParams3

                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
            "흰색위패" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)
            }
            "검정위패-TR-2-0802" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet2)
            }
            "사진위패 TR-3 1005" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet3)
                imageTabletImage.visibility = View.GONE
            }
            "추모패-TR-4-1307" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet4)
                imageTabletImage.visibility = View.GONE
            }
        }
        layoutTabletImage.background = newBackground
    }
    private fun setTablet2Data() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        var selectedTabletName2 = ApplicationClass.prefs.selectedTabletName2
        var layoutTablet2Image = binding.layoutTablet2Image

        var imageTablet2Image = binding.imageTablet2Image

        if(!selectedTabletType.contains("선택안함") && !selectedTabletType.contains("합골") && !boneSelectedTabletType.contains("선택안함")) {
            if(ApplicationClass.prefs.boneTabletSex == "남성"){
                selectedTabletName2 = ApplicationClass.prefs.selectedTabletName
            }
        }

        when (selectedTabletName2) {
            "미정" -> {
                val layoutParams3 = binding.layoutTablet2Image.layoutParams
                layoutParams3.width = 140
                layoutParams3.height = 430
                binding.layoutTablet2Image.layoutParams = layoutParams3

                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
            "색위패" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)
            }
            "검정위패-TR-2-0802" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet2)
            }
            "사진위패 TR-3 1005" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet3)
                imageTablet2Image.visibility = View.GONE
            }
            "추모패-TR-4-1307" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet4)
                imageTablet2Image.visibility = View.GONE
            }
        }

        layoutTablet2Image.background = newBackground
    }
    private fun setBoneTabletData() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        val selectedTabletName = ApplicationClass.prefs.selectedTabletName
        var layoutBoneTabletImage = binding.layoutBoneTabletImage

        when (selectedTabletName) {
            "미정" -> {
                val layoutParams3 = binding.layoutBoneTabletImage.layoutParams
                layoutParams3.width = 140
                layoutParams3.height = 430
                binding.layoutBoneTabletImage.layoutParams = layoutParams3

//                val layoutParams = binding.imageBone2Image.layoutParams
//                layoutParams.width = 400
//                layoutParams.height = 400
//                binding.imageBone2Image.layoutParams = layoutParams

                val layoutParams2 = binding.imageBoneTabletImage2.layoutParams
                layoutParams2.height = 10
                binding.imageBoneTabletImage2.layoutParams = layoutParams2

                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
            "흰색위패" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)
            }
            "조각위패(황금향,조각종교)" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                binding.imageBoneTabletImage2.visibility = View.VISIBLE
            }
            "검정위패-TR-2-0802" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet2)
            }
            "사진위패 TR-3 1005" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet3)
                binding.imageBoneTabletImage.visibility = View.GONE
            }
            "추모패-TR-4-1307" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet4)
                binding.imageBoneTabletImage.visibility = View.GONE
            }
        }
        layoutBoneTabletImage.background = newBackground
    }
    // 평장
    private fun setPyeongjangData() {
        var selectedPyeongjangName = ApplicationClass.prefs.selectedPyeongjangName
        var layoutPyeongjang = binding.layoutPyeongjang
        var layoutPyeongjang2 = binding.layoutPyeongjang2
        var layoutPyeongjangImage = binding.layoutPyeongjangImage
        var layoutPyeongjangImage2 = binding.layoutPyeongjangImage2

        if(!selectedPyeongjangType.contains("선택안함") && !selectedPyeongjangType2.contains("선택안함")) {
            if(ApplicationClass.prefs.pyeongjangSex2 == "남성"){
                selectedPyeongjangName = ApplicationClass.prefs.selectedPyeongjangName2
                layoutPyeongjang = binding.layoutPyeongjang3
                layoutPyeongjang2 = binding.layoutPyeongjang4
                layoutPyeongjangImage = binding.layoutPyeongjangImage3
                layoutPyeongjangImage2 = binding.layoutPyeongjangImage4
            }
        }

        if(selectedPyeongjangType == "표석"){
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_2)

            when (selectedPyeongjangName) {
                "표석(大) PS-2 2317" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_2)
                }
                "표석(小) PS-4 0911" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_3)
                }
                "피아노(大) PS-1 3020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_4)
                }
                "피아노(小) PS-3 2015" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_5)
                }

            }
            layoutPyeongjang.visibility = View.VISIBLE
            layoutPyeongjangImage.background = newBackground
        }
        else if(selectedPyeongjangType == "밀봉외함"){
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_1)

            when (selectedPyeongjangName) {
                "밀봉외함 MH-1 1015" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_1)
                }
            }
            layoutPyeongjang2.visibility = View.VISIBLE
            layoutPyeongjangImage2.background = newBackground
        }
    }
    // 평장2
    private fun setPyeongjangData2() {
        var selectedPyeongjangName2 = ApplicationClass.prefs.selectedPyeongjangName2
        var layoutPyeongjang3 = binding.layoutPyeongjang3
        var layoutPyeongjang4 = binding.layoutPyeongjang4
        var layoutPyeongjangImage3 = binding.layoutPyeongjangImage3
        var layoutPyeongjangImage4 = binding.layoutPyeongjangImage4

        if(!selectedPyeongjangType.contains("선택안함") && !selectedPyeongjangType2.contains("선택안함")) {
            if(ApplicationClass.prefs.pyeongjangSex2 == "남성"){
                selectedPyeongjangName2 = ApplicationClass.prefs.selectedPyeongjangName
                layoutPyeongjang3 = binding.layoutPyeongjang
                layoutPyeongjang4 = binding.layoutPyeongjang2
                layoutPyeongjangImage3 = binding.layoutPyeongjangImage
                layoutPyeongjangImage4 = binding.layoutPyeongjangImage2
            }
        }
        if(selectedPyeongjangType2 == "표석"){
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_2)

            when (selectedPyeongjangName2) {
                "표석(大) PS-2 2317" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_2)
                }
                "표석(小) PS-4 0911" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_3)
                }
                "피아노(大) PS-1 3020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_4)
                }
                "피아노(小) PS-3 2015" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_5)
                }

            }
            layoutPyeongjang3.visibility = View.VISIBLE
            layoutPyeongjangImage3.background = newBackground
        }
        else if(selectedPyeongjangType2 == "밀봉외함"){
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_1)

            when (selectedPyeongjangName2) {
                "밀봉외함 MH-1 1015" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn11_1)
                }
            }
            layoutPyeongjang4.visibility = View.VISIBLE
            layoutPyeongjangImage4.background = newBackground
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

        if(selectedUrnType != "선택안함"){
            msg += "\n\n${index++}. 유골함"
            msg += "\n - 함 명칭: " + ApplicationClass.prefs.selectedUrnName +
                    "\n - 각인 종류: " + ApplicationClass.prefs.engraveType + "[" + ApplicationClass.prefs.engraveType2 + "]"

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

            // 추가
            if(selectedUrnType2 != "선택안함"){
                msg += "\n\n - [추가] 함 명칭: " + ApplicationClass.prefs.selectedUrnName2 +
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
            }

            // 합골
            if(selectedUrnType.contains("합골")){
                msg += "\n\n - [합골] 각인 종류: " + ApplicationClass.prefs.boneEngraveType + "[" + ApplicationClass.prefs.boneEngraveType2 + "]" +
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
            }
        }

        if(selectedTabletType != "선택안함") {
            msg += "\n\n${index++}. 위패"

            msg += "\n - 위패 명칭: " + ApplicationClass.prefs.selectedTabletName +
                "\n - 위패 상세 종류: " + ApplicationClass.prefs.tabletType

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
            msg += "\n\n - [추가] 위패 명칭: " + ApplicationClass.prefs.selectedTabletName2
            msg += "\n - 상세 종류: " + ApplicationClass.prefs.boneTabletType

            if(!boneSelectedTabletType.contains("사진")){
                msg += "\n - 위패 내용: " + ApplicationClass.prefs.boneName3
                if(!boneSelectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.boneTabletType.toString().contains("기독교"))
                        msg += "\n - 직분: " + ApplicationClass.prefs.boneTabletName2
                    else if(ApplicationClass.prefs.boneTabletType.toString().contains("천주교"))
                        msg += "\n - 세례명: " + ApplicationClass.prefs.boneTabletName2
                }
            }
        }
        if(selectedTabletType.contains("합골")) {
            msg += "\n\n - [합골] 위패 상세 종류: " + ApplicationClass.prefs.boneTabletType

            if(!boneSelectedTabletType.contains("사진")){
                msg += "\n - 위패 내용: " + ApplicationClass.prefs.boneName3
                if(!boneSelectedTabletType.contains("본관")){
                    if(ApplicationClass.prefs.boneTabletType.toString().contains("기독교"))
                        msg += "\n - 직분: " + ApplicationClass.prefs.boneTabletName2
                    else if(ApplicationClass.prefs.boneTabletType.toString().contains("천주교"))
                        msg += "\n - 세례명: " + ApplicationClass.prefs.boneTabletName2
                }
            }
        }

        if(selectedPyeongjangType != "선택안함") {
            msg += "\n\n${index++}. 평장" +
                    "\n - 평장 종류: " + ApplicationClass.prefs.selectedPyeongjangType
            msg += "\n - ${ApplicationClass.prefs.selectedPyeongjangType} 명칭: " + ApplicationClass.prefs.selectedPyeongjangName
        }
        if(selectedPyeongjangType2 != "선택안함") {
            msg += "\n\n - [추가] 평장 종류: " + ApplicationClass.prefs.selectedPyeongjangType2 +
                    "\n - ${ApplicationClass.prefs.selectedPyeongjangType} 명칭: " + ApplicationClass.prefs.selectedPyeongjangName2
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
            if(selectedUrnType != "선택안함") {
                if(selectedUrnType.contains("합골함")){
                    if(ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입1)") || ApplicationClass.prefs.selectedUrnName!!.contains("ZEN사각합골진공함")){
                        // 유골 정보
                        var layoutUrnContent = binding.fragmentUrnContent

                        // 2. 레이아웃을 이미지로 변환
                        urnContentBitmap = Bitmap.createBitmap(layoutUrnContent.width, layoutUrnContent.height, Bitmap.Config.ARGB_8888)
                        val canvasUrnContentBitmap = Canvas(urnContentBitmap)
                        layoutUrnContent.draw(canvasUrnContentBitmap)

                        // 합골 추가 정보
                        val layoutBone1Content = binding.fragmentBoneContent

                        // 2. 레이아웃을 이미지로 변환
                        boneContentBitmap = Bitmap.createBitmap(layoutBone1Content.width, layoutBone1Content.height, Bitmap.Config.ARGB_8888)
                        val canvasBoneContentBitmap = Canvas(boneContentBitmap)
                        layoutBone1Content.draw(canvasBoneContentBitmap)

                        // name1 / boneName1
                        val imageBone1Image1 = binding.imageBone1Image1
                        imageBone1Image1.setImageBitmap(urnContentBitmap)

                        val imageBone1Image2 = binding.imageBone1Image2
                        imageBone1Image2.setImageBitmap(boneContentBitmap)

                        // 합골 최종 결과
                        val layoutBone1 = binding.layoutBone1

                        boneBitmap = Bitmap.createBitmap(layoutBone1.width, layoutBone1.height, Bitmap.Config.ARGB_8888)
                        val canvasBoneBitmap = Canvas(boneBitmap)
                        layoutBone1.draw(canvasBoneBitmap)

                        val layoutBoneResultImage = binding.layoutBoneResultImage
                        layoutBoneResultImage.setImageBitmap(boneBitmap)
                    }else if(ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입2)")
                        || ApplicationClass.prefs.selectedUrnName!!.contains("합골금띠")
                        || ApplicationClass.prefs.selectedUrnName!!.contains("합골실버십장생")){
                        // 텍스트를 이미지화
                        // 1. XML 레이아웃
                        val layoutBone2Content = binding.fragmentBone2Content

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
                    val layoutUrnContent = binding.fragmentUrnContent

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

                // 유골2
                if(selectedUrnType2 != "선택안함") {
                    // 텍스트를 이미지화
                    // 1. XML 레이아웃
                    val fragmentUrnContent2 = binding.fragmentUrnContent2

                    // 2. 레이아웃을 이미지로 변환
                    urnContentBitmap2 = Bitmap.createBitmap(fragmentUrnContent2.width, fragmentUrnContent2.height, Bitmap.Config.ARGB_8888)
                    val canvasUrnContentBitmap = Canvas(urnContentBitmap2)
                    fragmentUrnContent2.draw(canvasUrnContentBitmap)

                    val imageUrnImage2 = binding.imageUrnImage2
                    imageUrnImage2.setImageBitmap(urnContentBitmap2)

                    // 유골 최종 결과
                    val layoutUrnImage2 = binding.layoutUrnImage2

                    urnBitmap2 = Bitmap.createBitmap(layoutUrnImage2.width, layoutUrnImage2.height, Bitmap.Config.ARGB_8888)
                    val canvasUrnBitmap = Canvas(urnBitmap2)
                    layoutUrnImage2.draw(canvasUrnBitmap)

                    val layoutUrnResultImage2 = binding.layoutUrnResultImage2
                    layoutUrnResultImage2.setImageBitmap(urnBitmap2)
                }
            }else {
                binding.View2.visibility = View.GONE
//                binding.layoutResultTitleImage1.visibility = View.GONE
            }

            // 위패
            if(selectedTabletType != "선택안함") {
                // 위패 합골
                if(selectedTabletType.contains("합골")) {
                    // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                    // 1. XML 레이아웃
                    val fragmentBoneTabletContent = binding.fragmentBoneTabletContent

                    // 2. 레이아웃을 이미지로 변환
                    boneTabletContentBitmap = Bitmap.createBitmap(fragmentBoneTabletContent.width, fragmentBoneTabletContent.height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(boneTabletContentBitmap)
                    fragmentBoneTabletContent.draw(canvas)

                    var imageBoneTabletImage = binding.imageBoneTabletImage
                    var layoutBoneTabletImage = binding.layoutBoneTabletImage
                    var layoutBoneTabletResultImage = binding.layoutBoneTabletResultImage

                    imageBoneTabletImage.setImageBitmap(boneTabletContentBitmap)

                    // 2. 레이아웃을 이미지로 변환
                    boneTabletBitmap = Bitmap.createBitmap(layoutBoneTabletImage.width, layoutBoneTabletImage.height, Bitmap.Config.ARGB_8888)
                    val canvas2 = Canvas(boneTabletBitmap)
                    layoutBoneTabletImage.draw(canvas2)

                    layoutBoneTabletResultImage.setImageBitmap(boneTabletBitmap)
                    layoutBoneTabletImage.visibility = View.GONE
                }else{
                    // 1. XML 레이아웃
                    val layoutTabletContent = binding.fragmentTabletContent

                    // 2. 레이아웃을 이미지로 변환
                    tabletContentBitmap = Bitmap.createBitmap(layoutTabletContent.width, layoutTabletContent.height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(tabletContentBitmap)
                    layoutTabletContent.draw(canvas)

                    var imageTabletImage = binding.imageTabletImage
                    var layoutTabletImage = binding.layoutTabletImage
                    var layoutTabletResultImage = binding.layoutTabletResultImage

                    imageTabletImage.setImageBitmap(tabletContentBitmap)

                    // 2. 레이아웃을 이미지로 변환
                    tabletBitmap = Bitmap.createBitmap(layoutTabletImage.width, layoutTabletImage.height, Bitmap.Config.ARGB_8888)
                    val canvas2 = Canvas(tabletBitmap)
                    layoutTabletImage.draw(canvas2)

                    layoutTabletResultImage.setImageBitmap(tabletBitmap)
                    layoutTabletImage.visibility = View.GONE
                }

                // 위패 추가
                if(boneSelectedTabletType != "선택안함") {
                    // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                    // 1. XML 레이아웃
                    val layoutTabletContent = binding.fragmentTabletContent
                    // 2. 레이아웃을 이미지로 변환
                    tabletContentBitmap = Bitmap.createBitmap(layoutTabletContent.width, layoutTabletContent.height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(tabletContentBitmap)
                    layoutTabletContent.draw(canvas)

                    // 1. XML 레이아웃
                    val layoutTablet2Content = binding.fragmentTablet2Content
                    // 2. 레이아웃을 이미지로 변환
                    tablet2ContentBitmap = Bitmap.createBitmap(layoutTablet2Content.width,layoutTablet2Content.height,Bitmap.Config.ARGB_8888)
                    val boneCanvas = Canvas(tablet2ContentBitmap)
                    layoutTablet2Content.draw(boneCanvas)

                    // 1. XML 레이아웃
                    var imageTablet2Image = binding.imageTablet2Image
                    var layoutTablet2Image = binding.layoutTablet2Image
                    var layoutTablet2ResultImage = binding.layoutTablet2ResultImage

                    imageTablet2Image.setImageBitmap(tablet2ContentBitmap)

                    // 2. 레이아웃을 이미지로 변환
                    tablet2Bitmap = Bitmap.createBitmap(layoutTablet2Image.width,layoutTablet2Image.height,Bitmap.Config.ARGB_8888)
                    val boneCanvas2 = Canvas(tablet2Bitmap)
                    layoutTablet2Image.draw(boneCanvas2)

                    layoutTablet2ResultImage.setImageBitmap(tablet2Bitmap)
                    layoutTablet2Image.visibility = View.GONE
                }
            }else {
                binding.View2.visibility = View.GONE
//                binding.layoutResultTitleImage2.visibility = View.GONE
            }

            // 평장
            if(selectedPyeongjangType != "선택안함") {
                if(selectedPyeongjangType == "표석"){
                    // 1. XML 레이아웃
                    val layoutPyeongjangImage = binding.layoutPyeongjangImage

                    // 2. 레이아웃을 이미지로 변환
                    pyeongjangBitmap = Bitmap.createBitmap(layoutPyeongjangImage.width, layoutPyeongjangImage.height, Bitmap.Config.ARGB_8888)
                    val canvasPyeongjangBitmap = Canvas(pyeongjangBitmap)
                    layoutPyeongjangImage.draw(canvasPyeongjangBitmap)

                    val layoutPyeongjangResultImage = binding.layoutPyeongjangResultImage
                    layoutPyeongjangResultImage.visibility = View.VISIBLE
                    layoutPyeongjangResultImage.setImageBitmap(pyeongjangBitmap)
                }
                else if(selectedPyeongjangType == "밀봉외함"){
                    // 1. XML 레이아웃
                    val layoutPyeongjangImage2 = binding.layoutPyeongjangImage2

                    // 2. 레이아웃을 이미지로 변환
                    pyeongjangBitmap = Bitmap.createBitmap(layoutPyeongjangImage2.width, layoutPyeongjangImage2.height, Bitmap.Config.ARGB_8888)
                    val canvasPyeongjangBitmap = Canvas(pyeongjangBitmap)
                    layoutPyeongjangImage2.draw(canvasPyeongjangBitmap)

                    val layoutPyeongjangResultImage2 = binding.layoutPyeongjangResultImage2
                    layoutPyeongjangResultImage2.visibility = View.VISIBLE
                    layoutPyeongjangResultImage2.setImageBitmap(pyeongjangBitmap)
                }

                if(selectedPyeongjangType2 != "선택안함") {
                    if(selectedPyeongjangType2 == "표석"){
                        // 1. XML 레이아웃
                        val layoutPyeongjangImage3 = binding.layoutPyeongjangImage3

                        // 2. 레이아웃을 이미지로 변환
                        pyeongjangBitmap2 = Bitmap.createBitmap(layoutPyeongjangImage3.width, layoutPyeongjangImage3.height, Bitmap.Config.ARGB_8888)
                        val canvasPyeongjangBitmap2 = Canvas(pyeongjangBitmap2)
                        layoutPyeongjangImage3.draw(canvasPyeongjangBitmap2)

                        val layoutPyeongjangResultImage3 = binding.layoutPyeongjangResultImage3
                        layoutPyeongjangResultImage3.visibility = View.VISIBLE
                        layoutPyeongjangResultImage3.setImageBitmap(pyeongjangBitmap2)
                    }
                    else if(selectedPyeongjangType2 == "밀봉외함"){
                        // 1. XML 레이아웃
                        val layoutPyeongjangImage4 = binding.layoutPyeongjangImage4

                        // 2. 레이아웃을 이미지로 변환
                        pyeongjangBitmap2 = Bitmap.createBitmap(layoutPyeongjangImage4.width, layoutPyeongjangImage4.height, Bitmap.Config.ARGB_8888)
                        val canvasPyeongjangBitmap2 = Canvas(pyeongjangBitmap2)
                        layoutPyeongjangImage4.draw(canvasPyeongjangBitmap2)

                        val layoutPyeongjangResultImage4 = binding.layoutPyeongjangResultImage4
                        layoutPyeongjangResultImage4.visibility = View.VISIBLE
                        layoutPyeongjangResultImage4.setImageBitmap(pyeongjangBitmap2)
                    }
                }
            }
            binding.layoutResult1.visibility = View.GONE
            binding.layoutResult2.visibility = View.GONE
            binding.layoutResult3.visibility = View.GONE
            binding.layoutResultContent.visibility = View.GONE
            setOnTouchListener()
        }, delayMillis.toLong())
    }
    private fun setOnClickListeners() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        val urnIndex = searchList.indexOf(selectedUrnName)
        binding.layoutCurrent.text = "${urnIndex+1} / ${searchList.size}"

        binding.layoutPre.setOnClickListener {
            if(urnIndex > 0){
                ApplicationClass.prefs.selectedUrnName = searchList.get(urnIndex-1)
                findNavController().navigate(R.id.action_resultFragment_to_resultFragment)
            }
        }
        binding.layoutNext.setOnClickListener {
            if(urnIndex < searchList.size - 1){
                ApplicationClass.prefs.selectedUrnName = searchList.get(urnIndex+1)
                findNavController().navigate(R.id.action_resultFragment_to_resultFragment)
            }
        }

//        binding.layoutZoom1.setOnClickListener {
//            val dialog = ResultDialogFragment()
//            // 화면 밖 터치시 종료되지 않게 하기
//            dialog.isCancelable = false
//
//            val layoutResultImage = binding.layoutResultImage1
//
//            val resultBitmap = Bitmap.createBitmap(layoutResultImage.width, layoutResultImage.height, Bitmap.Config.ARGB_8888)
//            val canvasBitmap = Canvas(resultBitmap)
//            layoutResultImage.draw(canvasBitmap)
//
//            dialog.setImageBitmap(resultBitmap)
//
//            dialog.show(childFragmentManager, "show layout zoom")
//        }
//
//        binding.layoutZoom3.setOnClickListener {
//            val dialog = ResultDialogFragment()
//            // 화면 밖 터치시 종료되지 않게 하기
//            dialog.isCancelable = false
//
//            val layoutResultImage = binding.layoutResultImage3
//            val resultBitmap = Bitmap.createBitmap(layoutResultImage.width, layoutResultImage.height, Bitmap.Config.ARGB_8888)
//            val canvasBitmap = Canvas(resultBitmap)
//            layoutResultImage.draw(canvasBitmap)
//
//            dialog.setImageBitmap(resultBitmap)
//
//            dialog.show(childFragmentManager, "show layout zoom")
//        }

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
    private fun settingList() {
        searchList.add("미정")
//        searchList.add("기본")
//        searchList.add("기본(검정)")

        // 1. 일반 밀봉진공함
        searchList.add("도원기독교 DW-3 4010")
        searchList.add("도원불교 DW-4 4010")
        searchList.add("도원천주교 DW-5 4010")
        searchList.add("도원칼라난 DW-2 4010")
        searchList.add("도원칼라송학 DW-1 4010")
        searchList.add("도화청꽃 DH-4 4010")
        searchList.add("도화홍꽃 DH-5 4010")
        searchList.add("소담난 SDN-2 4008")
        searchList.add("소담송학 SDS-1 4008")

        // 2. 일반함
        searchList.add("매화조각민트 MH-2 2906")
        searchList.add("매화조각핑크 MH-1 2906")
        searchList.add("매화조각화이트 MH-3 2906")
        searchList.add("원통난 WT-1 3307")
        searchList.add("원통송학 WT-2 3307")
        searchList.add("황토기독교 DR-1 2906")
        searchList.add("황토난 DR-5 2906")
        searchList.add("황토무지 DR-8 2505")
        searchList.add("황토불교 DR-2 2906")
        searchList.add("황토송학 DR-4 2906")
        searchList.add("황토천주교 DR-3 2906")
        searchList.add("황토청꽃 DR-6 2906")
        searchList.add("황토홍꽃 DR-7 2906")

        // 3. 이중 밀봉진공함
        searchList.add("이중기독교 EG-1 5114")
        searchList.add("이중밀봉블랙자개 EMR-1 5020")
        searchList.add("이중밀봉송학 EM-1 4411")
        searchList.add("이중백자 EW-6 4010")
        searchList.add("이중불교 EBB-2 5114")
        searchList.add("이중진주운학 EW-6 5114")
        searchList.add("이중천주교 EC-3 5114")
        searchList.add("이중칼라난 EH-5 5114")
        searchList.add("이중칼라송학 EH-4 5114")

        // 4. 잠금형 삼중 명품자개함
        searchList.add("아름골드자개 ARG-1 8025")
        searchList.add("아름꽃자개 ARF-2 8025")
        searchList.add("아름화이트자개 SGS-3 8025")
        searchList.add("휴안골드자개 HUG-16 10228")
        searchList.add("휴안백십장생자개 HUG-17 12535")
        searchList.add("휴안블랙자개 HUG-15 10228")
        searchList.add("휴안홍한지십장생자개 HUG-19 13139")
        searchList.add("휴안화이트자개 HUG-14 10228")
        searchList.add("휴안흑십장생자개 HUG-18 12535")

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList.add("안향궁 AN-1 8525")
        searchList.add("안향천향 AN-2 8619.png")
        searchList.add("휴안궁 HU-1 8525")
        searchList.add("휴안그린난 HGN-2 9020")
        searchList.add("휴안그린송학 HGS-1 9020")
        searchList.add("휴안기독교 HUG-1 8817")
        searchList.add("휴안루엔골드 HLG-1 8723")
        searchList.add("휴안루엔화이트 HLS-2 8723")
        searchList.add("휴안명성 HU-3 8525")
        searchList.add("휴안불교 HUB-2 8817")
        searchList.add("휴안상아난 HSN-20 8718")
        searchList.add("휴안상아학 HSH-9 8718")
        searchList.add("휴안샤이니블루보석 HU-6 9520")
        searchList.add("휴안실버당초보석 HU-5 9520")
        searchList.add("휴안오로라블루 HUO-3 9822")
        searchList.add("휴안오로라실버 HUO-1 9822")
        searchList.add("휴안오로라핑크 HUO-2 9822")
        searchList.add("휴안천궁기독교 CGG-2 8723")
        searchList.add("휴안천궁불교 CGB-3 8723")
        searchList.add("휴안천궁일반 CG-1 8723")
        searchList.add("휴안천궁천주교 CGC-4 8723")
        searchList.add("휴안천주교 HUC-3 8817")
        searchList.add("휴안천향 HU-2 8525")
        searchList.add("휴안핑크당초보석 HU-4 9520")
        searchList.add("휴안화이트기독교 HUG-7 9421")
        searchList.add("휴안화이트불교 HU-8 9421")
        searchList.add("휴안화이트천주교 HU-9 9421")

        // 6. 소함
        searchList.add("소함난 EB-1 3713")
        searchList.add("소함송학 EB-2 3713")

        // 7. 수목함
        searchList.add("운학수목함 WH-1 1505")
        searchList.add("황토수목함 HT-1 1604")

        // 8. 스크류(잠금형)고급 진공함
        searchList.add("봉분궁 BOB-3 6621")
        searchList.add("봉분기독교 BOB-6 6621")
        searchList.add("봉분난 BOB-2 6621")
        searchList.add("봉분명성 BOB-4 6621")
        searchList.add("봉분불교 BOB-7 6621")
        searchList.add("봉분송학 BOB-1 6621")
        searchList.add("봉분천주교 BOB-8 6621")
        searchList.add("봉분천향 BOB-5 6621")
        searchList.add("아름궁 AR-3 6117")
        searchList.add("아름기독교 AR-9-6117")
        searchList.add("아름난 AR-2 6117")
        searchList.add("아름명성 AR-4 6117")
        searchList.add("아름불교-AR-10-6117")
        searchList.add("아름선궁 AR-6 6117")
        searchList.add("아름선명성 AR-7 6117")
        searchList.add("아름선천향 AR-8 6117")
        searchList.add("아름송학 AR-1 6117")
        searchList.add("아름천주교-AR-11-6117")
        searchList.add("아름천향 AR-5 6117")
        searchList.add("태림조각기독교 TA-2 6520")
        searchList.add("태림조각불교 TA-3 6520")
        searchList.add("태림조각일반 TA-1 6520")
        searchList.add("태림조각천주교 TA-4 6520")

        // 9. 황금함
        searchList.add("황금십장생 WGS-1 18040")
        searchList.add("황실황금기독교 HSG-2 13535")
        searchList.add("황실황금불교 HSB-3 13535")
        searchList.add("황실황금송학 HSS-5 13535")
        searchList.add("황실황금천주교 HSC-4 13535")
        searchList.add("황제황금함 ZE-14 20180")

        // 10. KS인증 ZEN한국도자기
        searchList.add("국화 ZE-1 11832")
        searchList.add("사군자 ZE-8 9030")
        searchList.add("선궁 ZE-3 11832")
        searchList.add("소망 ZE-6 11832")
        searchList.add("수복 ZE-10 9030")
        searchList.add("십장생 ZE-7 9030")
        searchList.add("아일렛 ZE-4 11832")
        searchList.add("옥자수 ZE-9 9030")
        searchList.add("조각기독교 ZE-11 1035")
        searchList.add("조각불교 ZE-12 1035")
        searchList.add("조각천주교 ZE-13 1035")
        searchList.add("청연 ZE-2 11832")
        searchList.add("화접도 ZE-5 11832")
    }
}