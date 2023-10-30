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
import com.example.taerimwon.ui.result.framelayout.*
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
    private lateinit var tablet2Bitmap: Bitmap
    private lateinit var tablet2ContentBitmap: Bitmap
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

        // layout_tablet2_content 추가
        val resultTablet2Fragment = ResultTablet2Fragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_tablet2_content, resultTablet2Fragment)
            .commit()

        // layout_urn_content 추가
        val resultUrnFragment = ResultUrnFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_urn_content, resultUrnFragment)
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

        // layout_tablet_content 추가
        val resultTabletFragment = ResultTabletFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_tablet_content, resultTabletFragment)
            .commit()

        // layout_bone_tablet_content 추가
        val resultBoneTabletFragment = ResultBoneTabletFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_bone_tablet_content, resultBoneTabletFragment)
            .commit()


        if(selectedUrnType != "선택안함") {
            setUrnData()
            if(selectedUrnType.contains("합골함")){
                if(selectedUrnType.contains("합골함1")){
                    setBone1Data()
                    binding.fragmentUrnContent.visibility = View.VISIBLE
                    binding.fragmentBoneContent.visibility = View.VISIBLE
                    binding.layoutBoneResultImage.visibility = View.VISIBLE
                    binding.layoutBone1.visibility = View.VISIBLE
                }else if(selectedUrnType.contains("합골함2")){
                    setBone2Data()
                    binding.fragmentBone2Content.visibility = View.VISIBLE
                    binding.layoutBone2ResultImage.visibility = View.VISIBLE
                    binding.layoutBone2.visibility = View.VISIBLE
                }
            }else{
                binding.fragmentUrnContent.visibility = View.VISIBLE
                binding.layoutUrnResultImage.visibility = View.VISIBLE
                binding.layoutUrn.visibility = View.VISIBLE
            }
        }

        if(selectedTabletType != "선택안함") {
            if(selectedTabletType.contains("합골")){
                setBoneTabletData()
                binding.fragmentBoneTabletContent.visibility = View.VISIBLE
                binding.layoutBoneTabletResultImage.visibility = View.VISIBLE
                binding.layoutBoneTablet.visibility = View.VISIBLE
                binding.fragmentBoneTabletContent.visibility = View.VISIBLE
            }
            else if(selectedTabletType.contains("사진")){
                setTabletData()
                binding.fragmentTabletContent.visibility = View.VISIBLE
//                binding.layoutTabletResult2.visibility = View.GONE
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
                binding.fragmentTabletContent.visibility = View.VISIBLE
//                binding.layoutTabletResult2.visibility = View.GONE
            }else {
                setTabletData()
                if(ApplicationClass.prefs.sex == "여성"){
                    binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                    binding.layoutTablet2.visibility = View.VISIBLE
                }else{
                    binding.layoutTabletResultImage.visibility = View.VISIBLE
                    binding.layoutTablet.visibility = View.VISIBLE
                }
                binding.fragmentTabletContent.visibility = View.VISIBLE
            }
        }

        if(boneSelectedTabletType != "선택안함") {
            if(boneSelectedTabletType.contains("사진")){
                setTablet2Data()
                binding.fragmentTablet2Content.visibility = View.VISIBLE
//                binding.layoutTablet2Result2.visibility = View.GONE
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
                binding.fragmentTablet2Content.visibility = View.VISIBLE
//                binding.layoutTablet2Result2.visibility = View.GONE
                binding.layoutTablet2ResultImage.visibility = View.VISIBLE
                binding.layoutTablet2.visibility = View.VISIBLE
            }else {
                setTablet2Data()
                binding.fragmentTabletContent.visibility = View.VISIBLE
                binding.layoutTabletResultImage.visibility = View.VISIBLE
                binding.layoutTablet.visibility = View.VISIBLE

                binding.fragmentTablet2Content.visibility = View.VISIBLE
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

    private fun setUrnData() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        if(selectedUrnType == "유골함"){
            val layoutUrnImage = binding.layoutUrnImage
            var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

            when (selectedUrnName) {
                "미정" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
                }
//                "기본" -> {
//                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn)
//                    binding.imageUrnImage0.visibility = View.GONE
//                    binding.imageUrnImage1.visibility = View.VISIBLE
//                }
//                "기본(검정)" -> {
//                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn0)
//                    binding.imageUrnImage0.visibility = View.GONE
//                    binding.imageUrnImage1.visibility = View.VISIBLE
//                }
                // 1. 일반 밀봉진공함
                "도원기독교 DW-3 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_1)
                }
                "도원불교 DW-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_2)
                }
                "도원천주교 DW-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_3)
                }
                "도원칼라난 DW-2 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_4)
                }
                "도원칼라송학 DW-1 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_5)
                }
                "도화청꽃 DH-4 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_6)
                }
                "도화홍꽃 DH-5 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_7)
                }
                "소담난 SDN-2 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_8)
                }
                "소담송학 SDS-1 4008" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn1_9)
                }
                // 2. 일반함
                "매화조각민트 MH-2 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_1)
                }
                "매화조각핑크 MH-1 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_2)
                }
                "매화조각화이트 MH-3 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_3)
                }
                "원통난 WT-1 3307" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_4)
                }
                "원통송학 WT-2 3307" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_5)
                }
                "황토기독교 DR-1 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_6)
                }
                "황토난 DR-5 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_7)
                }
                "황토무지 DR-8 2505" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_8)
                }
                "황토불교 DR-2 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_9)
                }
                "황토송학 DR-4 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_10)
                }
                "황토천주교 DR-3 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_11)
                }
                "황토청꽃 DR-6 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_12)
                }
                "황토홍꽃 DR-7 2906" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn2_13)
                }

                // 3. 이중 밀봉진공함
                "이중기독교 EG-1 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_1)
                }
                "이중밀봉블랙자개 EMR-1 5020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_2)
                }
                "이중밀봉송학 EM-1 4411" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_3)
                }
                "이중백자 EW-6 4010" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_4)
                }
                "이중불교 EBB-2 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_5)
                }
                "이중진주운학 EW-6 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_6)
                }
                "이중천주교 EC-3 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_7)
                }
                "이중칼라난 EH-5 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_8)
                }
                "이중칼라송학 EH-4 5114" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn3_9)
                }

                // 4. 잠금형 삼중 명품자개함
                "아름골드자개 ARG-1 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_1)
                }
                "아름꽃자개 ARF-2 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_2)
                }
                "아름화이트자개 SGS-3 8025" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_3)
                }
                "휴안골드자개 HUG-16 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_4)
                }
                "휴안백십장생자개 HUG-17 12535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_5)
                }
                "휴안블랙자개 HUG-15 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_6)
                }
                "휴안홍한지십장생자개 HUG-19 13139" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_7)
                }
                "휴안화이트자개 HUG-14 10228" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_8)
                }
                "휴안흑십장생자개 HUG-18 12535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn4_9)
                }

                // 5. 잠금형 삼중실크 밀봉진공함
                "안향궁 AN-1 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_1)
                }
                "안향천향 AN-2 8619.png" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_2)
                }
                "휴안궁 HU-1 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_3)
                }
                "휴안그린난 HGN-2 9020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_4)
                }
                "휴안그린송학 HGS-1 9020" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_5)
                }
                "휴안기독교 HUG-1 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_6)
                }
                "휴안루엔골드 HLG-1 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_7)
                }
                "휴안루엔화이트 HLS-2 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_8)
                }
                "휴안명성 HU-3 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_9)
                }
                "휴안불교 HUB-2 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_10)
                }
                "휴안상아난 HSN-20 8718" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_11)
                }
                "휴안상아학 HSH-9 8718" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_12)
                }
                "휴안샤이니블루보석 HU-6 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_13)
                }
                "휴안실버당초보석 HU-5 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_14)
                }
                "휴안오로라블루 HUO-3 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_15)
                }
                "휴안오로라실버 HUO-1 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_16)
                }
                "휴안오로라핑크 HUO-2 9822" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_17)
                }
                "휴안천궁기독교 CGG-2 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_18)
                }
                "휴안천궁불교 CGB-3 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_19)
                }
                "휴안천궁일반 CG-1 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_20)
                }
                "휴안천궁천주교 CGC-4 8723" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_21)
                }
                "휴안천주교 HUC-3 8817" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_22)
                }
                "휴안천향 HU-2 8525" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_23)
                }
                "휴안핑크당초보석 HU-4 9520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_24)
                }
                "휴안화이트기독교 HUG-7 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_25)
                }
                "휴안화이트불교 HU-8 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_26)
                }
                "휴안화이트천주교 HU-9 9421" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn5_27)
                }

                // 6. 소함
                "소함난 EB-1 3713" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6_1)
                }
                "소함송학 EB-2 3713" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn6_2)
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
                }
                "봉분기독교 BOB-6 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_2)
                }
                "봉분난 BOB-2 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_3)
                }
                "봉분명성 BOB-4 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_4)
                }
                "봉분불교 BOB-7 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_5)
                }
                "봉분송학 BOB-1 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_6)
                }
                "봉분천주교 BOB-8 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_7)
                }
                "봉분천향 BOB-5 6621" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_8)
                }
                "아름궁 AR-3 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_9)
                }
                "아름기독교 AR-9-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_10)
                }
                "아름난 AR-2 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_11)
                }
                "아름명성 AR-4 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_12)
                }
                "아름불교-AR-10-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_13)
                }
                "아름선궁 AR-6 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_14)
                }
                "아름선명성 AR-7 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_15)
                }
                "아름선천향 AR-8 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_16)
                }
                "아름송학 AR-1 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_17)
                }
                "아름천주교-AR-11-6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_18)
                }
                "아름천향 AR-5 6117" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_19)
                }
                "태림조각기독교 TA-2 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_20)
                }
                "태림조각불교 TA-3 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_21)
                }
                "태림조각일반 TA-1 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_22)
                }
                "태림조각천주교 TA-4 6520" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn8_23)
                }

                // 9. 황금함
                "황금십장생 WGS-1 18040" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_1)
                }
                "황실황금기독교 HSG-2 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_2)
                }
                "황실황금불교 HSB-3 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_3)
                }
                "황실황금송학 HSS-5 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_4)
                }
                "황실황금천주교 HSC-4 13535" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_5)
                }
                "황제황금함 ZE-14 20180" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn9_6)
                }

                // 10. KS인증 ZEN한국도자기
                "국화 ZE-1 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_1)
                }
                "사군자 ZE-8 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_2)
                }
                "선궁 ZE-3 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_3)
                }
                "소망 ZE-6 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_4)
                }
                "수복 ZE-10 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_5)
                }
                "십장생 ZE-7 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_6)
                }
                "아일렛 ZE-4 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_7)
                }
                "옥자수 ZE-9 9030" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_8)
                }
                "조각기독교 ZE-11 1035" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_9)
                }
                "조각불교 ZE-12 1035" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_10)
                }
                "조각천주교 ZE-13 1035" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_11)
                }
                "청연 ZE-2 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_12)
                }
                "화접도 ZE-5 11832" -> {
                    newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_urn10_13)
                }
            }
            layoutUrnImage.background = newBackground
        }
    }
    private fun setBone1Data() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName

        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        when (selectedUrnName) {
            "미정" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
//            "기본" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1)
//            }
//            "기본(검정)" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1_0)
//            }
            "ZEN사각합골진공함-HG-3-8228" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone1_1)
            }
        }
        binding.layoutBone1Image.background = newBackground
    }
    private fun setBone2Data() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        val selectedUrnName = ApplicationClass.prefs.selectedUrnName
        when (selectedUrnName) {
            "미정" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
//            "기본" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2)
//            }
//            "기본(검정)" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2_0)
//            }
            "합골금띠 HG-1 4612" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2_1)
            }
            "합골실버십장생 HG-2 4914" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_bone2_2)
                binding.imageBone2Image2.visibility = View.VISIBLE
            }
        }
        binding.layoutBone2Image.background = newBackground
    }
    private fun setTabletData() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

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
            "조각위패(황금향,조각종교)" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                binding.imageTabletImage2.visibility = View.VISIBLE
            }
            "검정위패-TR-2-0802" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet2)
            }
            "사진위패 TR-3 1005" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet3)
                binding.imageTabletImage.visibility = View.GONE
            }
            "추모패-TR-4-1307" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet4)
                binding.imageTabletImage.visibility = View.GONE
            }
        }
        binding.layoutTabletImage.background = newBackground
    }
    private fun setTablet2Data() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

        val selectedTabletName2 = ApplicationClass.prefs.selectedTabletName2

        when (selectedTabletName2) {
            "미정" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)
            }
//            "기본" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet)
//            }
//            "기본(검정)" -> {
//                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet0)
//            }
            "조각위패(황금향,조각종교)" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet1)
                binding.imageTablet2Image2.visibility = View.VISIBLE
            }
            "검정위패-TR-2-0802" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet2)
            }
            "사진위패 TR-3 1005" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet3)
                binding.imageTablet2Image.visibility = View.GONE
            }
            "추모패-TR-4-1307" -> {
                newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_tablet4)
                binding.imageTablet2Image.visibility = View.GONE
            }
        }

        binding.layoutTablet2Image.background = newBackground
    }
    private fun setBoneTabletData() {
        var newBackground = ContextCompat.getDrawable(requireContext(), R.drawable.img_white)

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
        binding.layoutBoneTabletImage.background = newBackground
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
            msg += "\n위패 추가 정보" +
                    "\n - 추가 위패 종류: " + ApplicationClass.prefs.boneSelectedTabletType +
                    "\n - 추가 위패 명칭: " + ApplicationClass.prefs.selectedTabletName2
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
        if(selectedTabletType != "합골") {
            msg += "\n========== "
            msg += "\n합골 추가 정보"
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
            }

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
            }
            // 위패 추가
            else if(boneSelectedTabletType != "선택안함") {
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
                imageTablet2Image.setImageBitmap(tablet2ContentBitmap)

                // 2. 레이아웃을 이미지로 변환
                tabletBitmap = Bitmap.createBitmap(layoutTabletImage.width, layoutTabletImage.height, Bitmap.Config.ARGB_8888)
                val canvas2 = Canvas(tabletBitmap)
                layoutTabletImage.draw(canvas2)

                // 2. 레이아웃을 이미지로 변환
                tablet2Bitmap = Bitmap.createBitmap(layoutTablet2Image.width,layoutTablet2Image.height,Bitmap.Config.ARGB_8888)
                val boneCanvas2 = Canvas(tablet2Bitmap)
                layoutTablet2Image.draw(boneCanvas2)

                layoutTabletResultImage.setImageBitmap(tabletBitmap)
                layoutTabletImage.visibility = View.GONE

                layoutTablet2ResultImage.setImageBitmap(tablet2Bitmap)
                layoutTablet2Image.visibility = View.GONE
            } else if(selectedTabletType != "선택안함") {
                // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
                // 1. XML 레이아웃
                val layoutTabletContent = binding.fragmentTabletContent

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