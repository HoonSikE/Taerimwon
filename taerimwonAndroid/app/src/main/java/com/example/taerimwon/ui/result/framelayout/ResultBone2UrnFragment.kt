package com.example.taerimwon.ui.result.framelayout

import android.graphics.*
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBone2UrnBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultBone2UrnFragment : BaseFragment<FragmentResultBone2UrnBinding>(R.layout.fragment_result_bone2_urn) {
    private lateinit var selectedUrnType: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()

        if(selectedUrnType != "선택안함") {
            if(ApplicationClass.prefs.selectedUrnName!!.contains("합골금띠")
                || ApplicationClass.prefs.selectedUrnName!!.contains("합골실버십장생")){
                setBone2Data()
            }else {
                binding.layoutResultContent.visibility = View.GONE
            }
        }
    }
    private fun setBone2Mark() {
        // 정보1
        var name1 = ApplicationClass.prefs.name1
        var engraveTypePosition = ApplicationClass.prefs.engraveTypePosition
        var engraveType2Position = ApplicationClass.prefs.engraveType2Position
        var layoutBone2Result211 = binding.layoutBone2Result211
        var imageBone2Result211 = binding.imageBone2Result211

        // 정보2
        var boneName1 = ApplicationClass.prefs.boneName1
        var boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
        var boneEngraveType2Position = ApplicationClass.prefs.boneEngraveType2Position
        var layoutBone2Result212 = binding.layoutBone2Result212
        var imageBone2Result212 = binding.imageBone2Result212

        // 추가 정보 / 기존 정보
        if((engraveTypePosition == 4 && boneEngraveTypePosition == 4) || (engraveTypePosition == 4 && boneEngraveTypePosition == 5)
                || (engraveTypePosition == 5 && boneEngraveTypePosition == 4) || (engraveTypePosition == 5 && boneEngraveTypePosition == 5)){
            // 검정
            if((engraveType2Position == 0 && boneEngraveType2Position == 0)){
                binding.imageBone2Result21.visibility = View.VISIBLE
                binding.imageBone2Result211.visibility = View.GONE
                binding.imageBone2Result212.visibility = View.GONE

                var imageName = "img_mark5"
                val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
                binding.imageBone2Result21.setImageResource(imageResource)
                return
            }
            // 금색
            else if((engraveType2Position == 1 && (boneEngraveType2Position == 1 || boneEngraveType2Position == 2))) {
                binding.imageBone2Result21.visibility = View.VISIBLE
                binding.imageBone2Result211.visibility = View.GONE
                binding.imageBone2Result212.visibility = View.GONE

                var imageName = "img_mark5_2"
                val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
                binding.imageBone2Result21.setImageResource(imageResource)
                return
            }
        }

        if(engraveTypePosition == boneEngraveTypePosition && !(name1 == "" || boneName1 == "")) {
            binding.imageBone2Result21.visibility = View.VISIBLE
            binding.imageBone2Result211.visibility = View.GONE
            binding.imageBone2Result212.visibility = View.GONE

            if(engraveTypePosition == 0){
                binding.layoutBone2Result21.visibility = View.VISIBLE
                binding.imageBone2Result21.visibility = View.GONE
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
            binding.imageBone2Result21.setImageResource(imageResource)
            return
        }else if(ApplicationClass.prefs.boneSex == "남성"){
            // 정보1
            name1 = ApplicationClass.prefs.boneName1
            engraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
            engraveType2Position = ApplicationClass.prefs.boneEngraveType2Position

            // 정보2
            boneName1 = ApplicationClass.prefs.name1
            boneEngraveTypePosition = ApplicationClass.prefs.engraveTypePosition
            boneEngraveType2Position = ApplicationClass.prefs.engraveType2Position
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
                if(engraveType2Position == 0)
                    imageName1 = "img_mark5"
                else if(engraveType2Position == 1 || engraveType2Position == 2)
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
                if(boneEngraveType2Position == 0)
                    imageName2 = "img_mark5"
                else if(boneEngraveType2Position == 1 || boneEngraveType2Position == 2)
                    imageName2 = "img_mark5_2"
            }
            val imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            binding.imageBone2Result212.setImageResource(imageResource)
        }
    }
    private fun setBone2Data() {
        val selectedUrnName = ApplicationClass.prefs.selectedUrnName

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
        var layoutUrnResult2211 = binding.layoutBone21Result2211
        var layoutUrnResult2212 = binding.layoutBone21Result2212
        var layoutUrnResult2213 = binding.layoutBone21Result2213
        var layoutUrnResult2214 = binding.layoutBone21Result2214
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
        var layoutBoneResult2211 = binding.layoutBone22Result2211
        var layoutBoneResult2212 = binding.layoutBone22Result2212
        var layoutBoneResult2213 = binding.layoutBone22Result2213
        var layoutBoneResult2214 = binding.layoutBone22Result2214
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
        var layoutUrnResult110 = binding.layoutBone21Result110
        var layoutUrnResult120 = binding.layoutBone21Result120
        var layoutUrnResult170 = binding.layoutBone21Result170

        var layoutUrnResult13 = binding.layoutBone21Result13
        var layoutUrnResult131 = binding.layoutBone21Result131
        var layoutUrnResult15 = binding.layoutBone21Result15
        var layoutUrnResult151 = binding.layoutBone21Result151
        var layoutUrnResult171 = binding.layoutBone21Result171

        var layoutUrnResult310 = binding.layoutBone21Result310
        var layoutUrnResult320 = binding.layoutBone21Result320
        var layoutUrnResult370 = binding.layoutBone21Result370

        var layoutUrnResult33 = binding.layoutBone21Result33
        var layoutUrnResult331 = binding.layoutBone21Result331
        var layoutUrnResult35 = binding.layoutBone21Result35
        var layoutUrnResult351 = binding.layoutBone21Result351
        var layoutUrnResult371 = binding.layoutBone21Result371

        var layoutBoneResult110 = binding.layoutBone22Result110
        var layoutBoneResult120 = binding.layoutBone22Result120
        var layoutBoneResult170 = binding.layoutBone22Result170

        var layoutBoneResult13 = binding.layoutBone22Result13
        var layoutBoneResult131 = binding.layoutBone22Result131
        var layoutBoneResult15 = binding.layoutBone22Result15
        var layoutBoneResult151 = binding.layoutBone22Result151
        var layoutBoneResult171 = binding.layoutBone22Result171

        var layoutBoneResult310 = binding.layoutBone22Result310
        var layoutBoneResult320 = binding.layoutBone22Result320
        var layoutBoneResult370 = binding.layoutBone22Result370

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
            binding.layoutBone21Result221.visibility = View.INVISIBLE
        }
        if(boneName1 == "") {
            binding.layoutBone22Result1.visibility = View.INVISIBLE
            binding.layoutBone22Result3.visibility = View.INVISIBLE
            binding.layoutBone22Result221.visibility = View.INVISIBLE
        }
        setBone2Mark()

        if(engraveType2.toString().contains("年月日")){
            val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult111.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult112.setTypeface(hyhaeso, Typeface.BOLD)

            layoutUrnResult110.visibility = View.GONE
            layoutUrnResult120.visibility = View.GONE
            layoutUrnResult170.visibility = View.GONE

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

            layoutUrnResult310.visibility = View.GONE
            layoutUrnResult320.visibility = View.GONE
            layoutUrnResult370.visibility = View.GONE

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

            layoutBoneResult110.visibility = View.GONE
            layoutBoneResult120.visibility = View.GONE
            layoutBoneResult170.visibility = View.GONE

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

            layoutBoneResult310.visibility = View.GONE
            layoutBoneResult320.visibility = View.GONE
            layoutBoneResult370.visibility = View.GONE

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
//            layoutUrnResult121.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult122.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult123.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult124.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult13.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult131.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult141.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult142.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult15.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult151.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult161.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult162.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult171.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult17.setTextColor(Color.parseColor("#FFD700"))

            layoutUrnResult211.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult220.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult2211.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult2212.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult2213.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult2214.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult222.setTextColor(Color.parseColor("#FFD700"))

            layoutUrnResult311.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult312.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult321.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult322.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult323.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult324.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult33.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult331.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult341.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult342.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult35.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult351.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult361.setTextColor(Color.parseColor("#FFD700"))
//            layoutUrnResult362.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult371.setTextColor(Color.parseColor("#FFD700"))
            layoutUrnResult37.setTextColor(Color.parseColor("#FFD700"))

            layoutBoneResult111.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult112.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult121.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult122.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult123.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult124.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult13.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult131.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult141.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult142.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult15.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult151.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult161.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult162.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult171.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult17.setTextColor(Color.parseColor("#FFD700"))

            layoutBoneResult211.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult220.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult2211.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult2212.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult2213.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult2214.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult222.setTextColor(Color.parseColor("#FFD700"))

            layoutBoneResult311.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult312.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult321.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult322.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult323.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult324.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult33.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult331.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult341.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult342.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult35.setTextColor(Color.parseColor("#FFD700"))
            layoutBoneResult351.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult361.setTextColor(Color.parseColor("#FFD700"))
//            layoutBoneResult362.setTextColor(Color.parseColor("#FFD700"))
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
        val pixel_size_210 = resources.getDimensionPixelSize(R.dimen.pixel_size_210)


        var flagUp = false;
        var flagDown = false;

        /**정보1*/
        if((engraveType == "일반" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日")))
            || (engraveType == "기독교" && (engraveType2 == "직분X"))
            || (engraveType == "불교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日")))
            || (engraveType == "천주교" && (engraveType2 == "세례명X"))
            || (engraveType == "원불교")){

            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()
                }
            }
        }
        else if(engraveType2 == "형제" || engraveType2 == "자매"){
            flagDown = true

            binding.layoutBone21Result23.visibility = View.VISIBLE
//            layoutUrnResult220.visibility = View.VISIBLE
//            layoutUrnResult221.height = pixel_size_170

            if(engraveType2 == "형제")
                layoutUrnResult222.text = "형제"
            else if(engraveType2 == "자매")
                layoutUrnResult222.text = "자매"

            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()
                }
            }
        }
        else if(engraveType == "기독교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日"))
            || engraveType == "불교" && engraveType2 == "법명"
            || engraveType == "순복음"){
            flagUp = true

//            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()
                }
            }
            binding.layoutBone21Result21.visibility = View.VISIBLE
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
                }
            }
            layoutUrnResult220.text = tmp1_2
        }
        else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2.toString().contains("年月日"))){
            flagUp = true

//            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()
                }
            }
            binding.layoutBone21Result21.visibility = View.VISIBLE
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
                }
                5 -> {
                    tmp1_2.append(name2[0] + "" + name2[3]).append("\n").append(name2[1] + "" + name2[4]).append("\n").append(name2[2])

                    layoutUrnResult220.setLineSpacing(0f, 1.0f)
                }
                6 -> {
                    tmp1_2.append(name2[0] + "" + name2[3]).append("\n").append(name2[1] + "" + name2[4]).append("\n").append(name2[2] + "" + name2[5])

                    layoutUrnResult220.setLineSpacing(0f, 1.0f)
                }
            }
//            layoutUrnResult222.text = tmp1_2
            layoutUrnResult220.text = tmp1_2
        }
        else if(engraveType == "SGI"){
            flagUp = true
            flagDown = true

            binding.layoutBone21Result21.visibility = View.VISIBLE
            layoutUrnResult220.visibility = View.VISIBLE
//            layoutUrnResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutUrnResult220.typeface = serifamedium
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutUrnResult220.setPadding(0, 0, 0, pixel_size_4)
            layoutUrnResult220.letterSpacing = 0f

//            layoutUrnResult221.height = pixel_size_145
//            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
//            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            binding.layoutBone21Result23.visibility = View.VISIBLE
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.scaleY = 1f
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

//            layoutUrnResult222.typeface = hyhaeso
            layoutUrnResult222.setLineSpacing(0f, 1.0f)

            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
            }
        }
        else if(engraveType == "묘법"){
            flagUp = true
            flagDown = true

            binding.layoutBone21Result21.visibility = View.VISIBLE
            layoutUrnResult220.visibility = View.VISIBLE
//            layoutUrnResult220.width = pixel_size_80
            layoutUrnResult220.text = "妙法"
            layoutUrnResult220.setTypeface(hyhaeso, Typeface.BOLD)
//            layoutUrnResult220.typeface = hyhaeso
            layoutUrnResult220.letterSpacing = 0f

//            layoutUrnResult221.height = pixel_size_145
//            layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
//            layoutUrnResult221.setLineSpacing(0f, 1.0f)

            binding.layoutBone21Result23.visibility = View.VISIBLE
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.scaleY = 1f
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

//            layoutUrnResult222.typeface = hyhaeso
            layoutUrnResult222.setLineSpacing(0f, 1.0f)


            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
            }
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

                layoutUrnResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
            "천주교" -> {
                layoutUrnResult111.visibility = View.GONE
                layoutUrnResult112.visibility = View.VISIBLE
                layoutUrnResult112.text = "出\n生"

                layoutUrnResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
        }

        if(date1.length == 10){
            var imageName1 = "img_num" + date1[0].toString()
            var imageName2 = "img_num" + date1[1].toString()
            var imageName3 = "img_num" + date1[2].toString()
            var imageName4 = "img_num" + date1[3].toString()
            var imageName5 = "img_num" + date1[5].toString()
            var imageName6 = "img_num" + date1[6].toString()
            var imageName7 = "img_num" + date1[8].toString()
            var imageName8 = "img_num" + date1[9].toString()

            var imageResource = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            layoutUrnResult121.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            layoutUrnResult122.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName3, "drawable", requireActivity().packageName)
            layoutUrnResult123.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName4, "drawable", requireActivity().packageName)
            layoutUrnResult124.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName5, "drawable", requireActivity().packageName)
            layoutUrnResult141.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName6, "drawable", requireActivity().packageName)
            layoutUrnResult142.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName7, "drawable", requireActivity().packageName)
            layoutUrnResult161.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName8, "drawable", requireActivity().packageName)
            layoutUrnResult162.setImageResource(imageResource)
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

                layoutUrnResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
            "천주교" -> {
                layoutUrnResult311.visibility = View.GONE
                layoutUrnResult312.visibility = View.VISIBLE
                layoutUrnResult312.text = "善\n終"

                layoutUrnResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
        }

        if(date2.length == 10){
            var imageName1 = "img_num" + date2[0].toString()
            var imageName2 = "img_num" + date2[1].toString()
            var imageName3 = "img_num" + date2[2].toString()
            var imageName4 = "img_num" + date2[3].toString()
            var imageName5 = "img_num" + date2[5].toString()
            var imageName6 = "img_num" + date2[6].toString()
            var imageName7 = "img_num" + date2[8].toString()
            var imageName8 = "img_num" + date2[9].toString()

            var imageResource = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            layoutUrnResult321.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            layoutUrnResult322.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName3, "drawable", requireActivity().packageName)
            layoutUrnResult323.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName4, "drawable", requireActivity().packageName)
            layoutUrnResult324.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName5, "drawable", requireActivity().packageName)
            layoutUrnResult341.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName6, "drawable", requireActivity().packageName)
            layoutUrnResult342.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName7, "drawable", requireActivity().packageName)
            layoutUrnResult361.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName8, "drawable", requireActivity().packageName)
            layoutUrnResult362.setImageResource(imageResource)
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
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2214.text = boneName1[1].toString()
                }
                3 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2214.text = boneName1[2].toString()
                }
                4 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2213.visibility = View.VISIBLE
                    layoutBoneResult2213.text = boneName1[2].toString()
                    layoutBoneResult2214.text = boneName1[3].toString()
                }
            }
        }
        else if(boneEngraveType2 == "형제" || boneEngraveType2 == "자매" ){
            flagDown = true

            binding.layoutBone21Result23.visibility = View.VISIBLE
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
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2214.text = boneName1[1].toString()
                }
                3 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2214.text = boneName1[2].toString()
                }
                4 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2213.visibility = View.VISIBLE
                    layoutBoneResult2213.text = boneName1[2].toString()
                    layoutBoneResult2214.text = boneName1[3].toString()
                }
            }
        }
        else if(boneEngraveType == "기독교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))
            || boneEngraveType == "불교" && boneEngraveType2 == "법명"
            || boneEngraveType == "순복음"){
            flagUp = true

//            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2214.text = boneName1[1].toString()
                }
                3 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2214.text = boneName1[2].toString()
                }
                4 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2213.visibility = View.VISIBLE
                    layoutBoneResult2213.text = boneName1[2].toString()
                    layoutBoneResult2214.text = boneName1[3].toString()
                }
            }
            binding.layoutBone21Result21.visibility = View.VISIBLE
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
        }
        else if(boneEngraveType == "천주교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))){
            flagUp = true

//            layoutBoneResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2214.text = boneName1[1].toString()
                }
                3 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2214.text = boneName1[2].toString()
                }
                4 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2213.visibility = View.VISIBLE
                    layoutBoneResult2213.text = boneName1[2].toString()
                    layoutBoneResult2214.text = boneName1[3].toString()
                }
            }
            binding.layoutBone21Result21.visibility = View.VISIBLE
            layoutBoneResult220.visibility = View.VISIBLE
            when (boneName2.length) {
                2 -> {
                    tmp2_2.append(boneName2[0]).append("\n").append(boneName2[1])
                }
                3 -> {
                    tmp2_2.append(boneName2[0]).append("\n").append(boneName2[1]).append("\n").append(boneName2[2])

                    layoutBoneResult220.setLineSpacing(0f, 1.0f)
                }
                4 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[1]).append("\n").append(boneName2[2] + "" + boneName2[3])
                }
                5 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[3]).append("\n").append(boneName2[1] + "" + boneName2[4]).append("\n").append(boneName2[3])

                    layoutBoneResult220.setLineSpacing(0f, 1.0f)
                }
                6 -> {
                    tmp2_2.append(boneName2[0] + "" + boneName2[3]).append("\n").append(boneName2[1] + "" + boneName2[4]).append("\n").append(boneName2[2] + "" + boneName2[5])

                    layoutBoneResult220.setLineSpacing(0f, 1.0f)
                }
            }
            layoutBoneResult220.text = tmp2_2
        }
        else if(boneEngraveType == "SGI"){
            flagUp = true
            flagDown = true

            binding.layoutBone21Result21.visibility = View.VISIBLE
            layoutBoneResult220.visibility = View.VISIBLE
//            layoutBoneResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutBoneResult220.typeface = serifamedium
            layoutBoneResult220.text = "SGI"
            layoutBoneResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_23.toFloat())
            layoutBoneResult220.setPadding(0, 0, 0, pixel_size_4)
            layoutBoneResult220.letterSpacing = 0f

            binding.layoutBone21Result23.visibility = View.VISIBLE
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.scaleY = 1f
            layoutBoneResult222.setTypeface(hyhaeso, Typeface.BOLD)
            layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

//            layoutBoneResult222.typeface = hyhaeso
            layoutBoneResult222.setLineSpacing(0f, 1.0f)

            when (boneName1.length) {
                2 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2214.text = boneName1[1].toString()

                    layoutBoneResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                3 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2214.text = boneName1[2].toString()

                    layoutBoneResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                4 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2213.visibility = View.VISIBLE
                    layoutBoneResult2213.text = boneName1[2].toString()
                    layoutBoneResult2214.text = boneName1[3].toString()

                    layoutBoneResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
            }
        }
        else if(boneEngraveType == "묘법"){
            flagUp = true
            flagDown = true

            binding.layoutBone21Result21.visibility = View.VISIBLE
            layoutBoneResult220.visibility = View.VISIBLE
            layoutBoneResult220.text = "妙法"
            layoutBoneResult220.setTypeface(hyhaeso, Typeface.BOLD)
            layoutBoneResult220.letterSpacing = 0f

            binding.layoutBone21Result23.visibility = View.VISIBLE
            layoutBoneResult222.visibility = View.VISIBLE
            layoutBoneResult222.text = "位"
            layoutBoneResult222.scaleY = 1f
            layoutBoneResult222.setTypeface(hyhaeso, Typeface.BOLD)
            layoutBoneResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

//            layoutBoneResult222.typeface = hyhaeso
            layoutBoneResult222.setLineSpacing(0f, 1.0f)

            when (boneName1.length) {
                2 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2214.text = boneName1[1].toString()

                    layoutBoneResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                3 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2214.text = boneName1[2].toString()

                    layoutBoneResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                4 -> {
                    layoutBoneResult2211.text = boneName1[0].toString()
                    layoutBoneResult2212.visibility = View.VISIBLE
                    layoutBoneResult2212.text = boneName1[1].toString()
                    layoutBoneResult2213.visibility = View.VISIBLE
                    layoutBoneResult2213.text = boneName1[2].toString()
                    layoutBoneResult2214.text = boneName1[3].toString()

                    layoutBoneResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutBoneResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
            }
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

                layoutBoneResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
            "천주교" -> {
                layoutBoneResult111.visibility = View.GONE
                layoutBoneResult112.visibility = View.VISIBLE
                layoutBoneResult112.text = "出\n生"

                layoutBoneResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
        }

        if(boneDate1.length == 10){
            var imageName1 = "img_num" + boneDate1[0].toString()
            var imageName2 = "img_num" + boneDate1[1].toString()
            var imageName3 = "img_num" + boneDate1[2].toString()
            var imageName4 = "img_num" + boneDate1[3].toString()
            var imageName5 = "img_num" + boneDate1[5].toString()
            var imageName6 = "img_num" + boneDate1[6].toString()
            var imageName7 = "img_num" + boneDate1[8].toString()
            var imageName8 = "img_num" + boneDate1[9].toString()

            var imageResource = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            layoutBoneResult121.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            layoutBoneResult122.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName3, "drawable", requireActivity().packageName)
            layoutBoneResult123.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName4, "drawable", requireActivity().packageName)
            layoutBoneResult124.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName5, "drawable", requireActivity().packageName)
            layoutBoneResult141.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName6, "drawable", requireActivity().packageName)
            layoutBoneResult142.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName7, "drawable", requireActivity().packageName)
            layoutBoneResult161.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName8, "drawable", requireActivity().packageName)
            layoutBoneResult162.setImageResource(imageResource)
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

                layoutBoneResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
            "천주교" -> {
                layoutBoneResult311.visibility = View.GONE
                layoutBoneResult312.visibility = View.VISIBLE
                layoutBoneResult312.text = "善\n終"

                layoutBoneResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())
            }
        }

        if(boneDate2.length == 10){
            var imageName1 = "img_num" + boneDate2[0].toString()
            var imageName2 = "img_num" + boneDate2[1].toString()
            var imageName3 = "img_num" + boneDate2[2].toString()
            var imageName4 = "img_num" + boneDate2[3].toString()
            var imageName5 = "img_num" + boneDate2[5].toString()
            var imageName6 = "img_num" + boneDate2[6].toString()
            var imageName7 = "img_num" + boneDate2[8].toString()
            var imageName8 = "img_num" + boneDate2[9].toString()

            var imageResource = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            layoutBoneResult321.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            layoutBoneResult322.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName3, "drawable", requireActivity().packageName)
            layoutBoneResult323.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName4, "drawable", requireActivity().packageName)
            layoutBoneResult324.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName5, "drawable", requireActivity().packageName)
            layoutBoneResult341.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName6, "drawable", requireActivity().packageName)
            layoutBoneResult342.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName7, "drawable", requireActivity().packageName)
            layoutBoneResult361.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName8, "drawable", requireActivity().packageName)
            layoutBoneResult362.setImageResource(imageResource)
        }

        if(boneDate2Type == "양력")
            layoutBoneResult37.text = "陽"
        else if(boneDate2Type == "음력")
            layoutBoneResult37.text = "陰"

        // 레이아웃 파일에서 뷰 가져오기
        val layoutBone21Result221 = binding.layoutBone21Result221
        // 현재 레이아웃 파라미터를 가져옵니다.
        val params = layoutBone21Result221.layoutParams
        // 뷰의 높이를 픽셀 단위로 설정
        params.height = 170
        layoutBone21Result221.layoutParams = params

        if(flagUp && flagUp){
            // 레이아웃 파일에서 뷰 가져오기
            val layoutBone21Result221 = binding.layoutBone21Result221
            // 현재 레이아웃 파라미터를 가져옵니다.
            val params = layoutBone21Result221.layoutParams
            // 뷰의 높이를 픽셀 단위로 설정
            params.height = 170
            layoutBone21Result221.layoutParams = params

            val layoutBone22Result221 = binding.layoutBone22Result221
            val params2 = layoutBone22Result221.layoutParams
            params2.height = 170
            layoutBone22Result221.layoutParams = params2
//            binding.layoutBone21Result221.height = pixel_size_170
//            binding.layoutBone22Result221.height = pixel_size_170
        }else{
            if(flagUp || flagDown){
                val layoutBone21Result221 = binding.layoutBone21Result221
                val params = layoutBone21Result221.layoutParams
                params.height = 210
                layoutBone21Result221.layoutParams = params

                val layoutBone22Result221 = binding.layoutBone22Result221
                val params2 = layoutBone22Result221.layoutParams
                params2.height = 210
                layoutBone22Result221.layoutParams = params2
//                binding.layoutBone21Result221.height = pixel_size_210
//                binding.layoutBone22Result221.height = pixel_size_210
            }
        }

        if((engraveType2 == "형제" && boneEngraveType2 == "형제")
            || (engraveType2 == "자매" && boneEngraveType2 == "자매")
            || (engraveType2 == "SGI" && boneEngraveType2 == "SGI")
            || (engraveType2 == "묘법" && boneEngraveType2 == "묘법")
            || (engraveType2 == "묘법" && boneEngraveType2 == "SGI")
            || (engraveType2 == "SGI" && boneEngraveType2 == "묘법")){
            binding.layoutBone21Result222.scaleY = 1f
            binding.layoutBone21Result222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

            binding.layoutBone22Result222.visibility = View.GONE
        }
        if(engraveType2 == "SGI" && boneEngraveType2 == "SGI") {
            binding.layoutBone21Result220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

            binding.layoutBone22Result220.visibility = View.GONE
        }else if(engraveType2 == "묘법" && boneEngraveType2 == "묘법") {
            binding.layoutBone21Result220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_30.toFloat())

            binding.layoutBone22Result220.visibility = View.GONE
        }
    }
}