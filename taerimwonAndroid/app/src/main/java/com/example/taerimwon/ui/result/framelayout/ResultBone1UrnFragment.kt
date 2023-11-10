package com.example.taerimwon.ui.result.framelayout

import android.graphics.*
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBone1UrnBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class ResultBone1UrnFragment : BaseFragment<FragmentResultBone1UrnBinding>(R.layout.fragment_result_bone1_urn) {
    private lateinit var selectedUrnType: String
    private lateinit var selectedUrnType2: String
    private lateinit var selectedUrnName2 : String
    private lateinit var boneName1 : String
    private lateinit var boneName2 : String
    private lateinit var boneEngraveType : String
    private lateinit var boneEngraveType2 : String
    private var boneEngraveTypePosition by Delegates.notNull<Int>()
    private var boneEngraveType2Position by Delegates.notNull<Int>()
    private lateinit var boneDate1 : String
    private lateinit var boneDate1Type : String
    private lateinit var boneDate2 : String
    private lateinit var boneDate2Type : String
    override fun init() {
        initData()
    }
    private fun initData() {
        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        selectedUrnType2 = ApplicationClass.prefs.selectedUrnType2.toString()
        selectedUrnName2 = ApplicationClass.prefs.selectedUrnName2.toString()
        boneName1 = ApplicationClass.prefs.boneName1.toString()
        boneName2 = ApplicationClass.prefs.boneName2.toString()
        boneEngraveType = ApplicationClass.prefs.boneEngraveType.toString()
        boneEngraveType2 = ApplicationClass.prefs.boneEngraveType2.toString()
        boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
        boneEngraveType2Position = ApplicationClass.prefs.boneEngraveType2Position

        boneDate1 = ApplicationClass.prefs.boneDate1.toString()
        boneDate1Type = ApplicationClass.prefs.boneDate1Type.toString()
        boneDate2 = ApplicationClass.prefs.boneDate2.toString()
        boneDate2Type = ApplicationClass.prefs.boneDate2Type.toString()

        if(!selectedUrnType.contains("선택안함") && (ApplicationClass.prefs.selectedUrnName!!.contains("ZEN사각합골진공함") || !selectedUrnType2.contains("선택안함"))) {
            if(ApplicationClass.prefs.boneSex == "남성"){
                selectedUrnType = ApplicationClass.prefs.selectedUrnType2.toString()
                selectedUrnType2 = ApplicationClass.prefs.selectedUrnType.toString()
                selectedUrnName2 = ApplicationClass.prefs.selectedUrnName.toString()
                boneName1 = ApplicationClass.prefs.name1.toString()
                boneName2 = ApplicationClass.prefs.name2.toString()
                boneEngraveType = ApplicationClass.prefs.engraveType.toString()
                boneEngraveType2 = ApplicationClass.prefs.engraveType2.toString()
                boneEngraveTypePosition = ApplicationClass.prefs.engraveTypePosition
                boneEngraveType2Position = ApplicationClass.prefs.engraveTypePosition

                boneDate1 = ApplicationClass.prefs.date1.toString()
                boneDate1Type = ApplicationClass.prefs.date1Type.toString()
                boneDate2 = ApplicationClass.prefs.date2.toString()
                boneDate2Type = ApplicationClass.prefs.date2Type.toString()
            }
        }

        if(selectedUrnType2 != "선택안함" || ApplicationClass.prefs.selectedUrnName!!.contains("ZEN사각합골진공함")) {
            setUrnData()
        }else{
            binding.layoutResultContent.visibility = View.GONE
        }
    }
    private fun setUrnMark() {
        if(boneName1 == "")
            return

        if(boneEngraveTypePosition == 0){
            binding.layoutUrnResult21.visibility = View.VISIBLE
            binding.imageUrnResult21.visibility = View.GONE
            return
        }
        var imageName = "img_mark" + (boneEngraveTypePosition + 1)

        if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
            if(boneEngraveType2Position == 0)
                imageName = "img_mark5"
            else if(boneEngraveType2Position == 1 || boneEngraveType2Position == 2)
                imageName = "img_mark5_2"
        }

        if(selectedUrnName2!!.contains("블랙") || selectedUrnName2!!.contains("검정")
            || selectedUrnName2!!.contains("휴안홍") || selectedUrnName2!!.contains("휴안흑")) {
            if(boneEngraveTypePosition == 1){
                imageName = "img_mark2_2"
            }else if(boneEngraveTypePosition == 3){
                imageName = "img_mark4_2"
            }else if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5) {
                imageName = "img_mark5_2"
            }
        }

        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageUrnResult21.setImageResource(imageResource)
    }
    private fun setUrnData() {
        if(selectedUrnName2!!.contains("블랙") || selectedUrnName2!!.contains("검정")
            || selectedUrnName2!!.contains("휴안홍") || selectedUrnName2!!.contains("휴안흑")){
            binding.layoutUrnResult111.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult112.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult121.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult122.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult123.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult124.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult13.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult131.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult141.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult142.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult15.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult151.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult161.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult162.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult171.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult17.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutUrnResult21.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult220.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult2211.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult2212.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult2213.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult2214.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult222.setTextColor(Color.parseColor("#FFD700"))

            binding.layoutUrnResult311.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult312.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult321.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult322.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult323.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult324.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult33.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult331.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult341.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult342.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult35.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult351.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult361.setTextColor(Color.parseColor("#FFD700"))
//            binding.layoutUrnResult362.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult371.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult37.setTextColor(Color.parseColor("#FFD700"))
        }

        when(selectedUrnName2){
            // 7. 수목함
            "운학수목함 WH-1 1505", "황토수목함 HT-1 1604",
            -> {
                binding.layoutResultContent.visibility = View.INVISIBLE
                return
            }
        }
        // 내용이 없으면 빈 값
        if(boneName1 == "") {
            binding.layoutResultContent.visibility = View.INVISIBLE
            return
        } else {
            binding.layoutResultContent.visibility = View.VISIBLE
            setUrnMark()
        }

        val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)
        val pixel_size_50 = resources.getDimensionPixelSize(R.dimen.pixel_size_50)
        val pixel_size_80 = resources.getDimensionPixelSize(R.dimen.pixel_size_80)
        val pixel_size_90 = resources.getDimensionPixelSize(R.dimen.pixel_size_90)
        val pixel_size_100 = resources.getDimensionPixelSize(R.dimen.pixel_size_100)
        val pixel_size_145 = resources.getDimensionPixelSize(R.dimen.pixel_size_145)
        val pixel_size_170 = resources.getDimensionPixelSize(R.dimen.pixel_size_170)

        val tmp = StringBuilder()

        if(boneEngraveType2.toString().contains("年月日")){
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)

            binding.layoutUrnResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult111.setTypeface(hyhaeso, Typeface.BOLD)
            binding.layoutUrnResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            binding.layoutUrnResult112.setTypeface(hyhaeso, Typeface.BOLD)

            binding.layoutUrnResult110.visibility = View.GONE
            binding.layoutUrnResult120.visibility = View.GONE
            binding.layoutUrnResult170.visibility = View.GONE

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

            binding.layoutUrnResult310.visibility = View.GONE
            binding.layoutUrnResult320.visibility = View.GONE
            binding.layoutUrnResult370.visibility = View.GONE

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
        val layoutUrnResult2211 = binding.layoutUrnResult2211
        val layoutUrnResult2212 = binding.layoutUrnResult2212
        val layoutUrnResult2213 = binding.layoutUrnResult2213
        val layoutUrnResult2214 = binding.layoutUrnResult2214

        if((boneEngraveType == "일반" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日")))
            || (boneEngraveType == "기독교" && (boneEngraveType2 == "직분X"))
            || (boneEngraveType == "불교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日")))
            || (boneEngraveType == "천주교" && (boneEngraveType2 == "세례명X"))
            || (boneEngraveType == "원불교")){

            when (boneName1.length) {
                2 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2214.text = boneName1[1].toString()
                }
                3 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2214.text = boneName1[2].toString()
                }
                4 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = boneName1[2].toString()
                    layoutUrnResult2214.text = boneName1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                }
            }
        }else if(boneEngraveType2 == "형제" || boneEngraveType2 == "자매"){
            binding.layoutUrnResult222.visibility = View.VISIBLE

            if(boneEngraveType2 == "형제")
                binding.layoutUrnResult222.text = "형제"
            else if(boneEngraveType2 == "자매")
                binding.layoutUrnResult222.text = "자매"

            when (boneName1.length) {
                2 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2214.text = boneName1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2214.text = boneName1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = boneName1[2].toString()
                    layoutUrnResult2214.text = boneName1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                }
            }
        }else if(boneEngraveType == "기독교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))
            || boneEngraveType == "불교" && boneEngraveType2 == "법명"
            || boneEngraveType == "순복음"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            when (boneName1.length) {
                2 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2214.text = boneName1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2214.text = boneName1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = boneName1[2].toString()
                    layoutUrnResult2214.text = boneName1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                }
            }
            layoutUrnResult220.visibility = View.VISIBLE
            when (boneName2.length) {
                4 -> {
                    layoutUrnResult220.width = pixel_size_100
                    layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
                    layoutUrnResult220.letterSpacing = -0.2f
                }
            }
            layoutUrnResult220.text = boneName2
        } else if(boneEngraveType == "천주교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))){
            val layoutUrnResult222 = binding.layoutUrnResult222
            when (boneName1.length) {
                2 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2214.text = boneName1[1].toString()
                }
                3 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2214.text = boneName1[2].toString()
                }
                4 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = boneName1[2].toString()
                    layoutUrnResult2214.text = boneName1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                }
            }
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.width = pixel_size_100
            when (boneName2.length) {
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
            layoutUrnResult222.text = boneName2
        }else if(boneEngraveType == "SGI"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_80
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutUrnResult220.typeface = serifamedium
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_25.toFloat())
            layoutUrnResult220.letterSpacing = 0f

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult222.setLineSpacing(0f, 1.0f)

            when (boneName1.length) {
                2 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2214.text = boneName1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2214.text = boneName1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = boneName1[2].toString()
                    layoutUrnResult2214.text = boneName1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
        }else if(boneEngraveType == "묘법"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.text = "妙法"
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            layoutUrnResult220.setTypeface(hyhaeso, Typeface.BOLD)
            layoutUrnResult220.letterSpacing = 0f

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.text = "位"
            layoutUrnResult222.setTypeface(hyhaeso, Typeface.BOLD)

            when (boneName1.length) {
                2 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2214.text = boneName1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2214.text = boneName1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_45.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = boneName1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = boneName1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = boneName1[2].toString()
                    layoutUrnResult2214.text = boneName1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
        }

        // 출생일
        when (boneEngraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutUrnResult111.visibility = View.VISIBLE
                binding.layoutUrnResult112.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE
                if(boneEngraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult112.text = "出\n生"
                else
                    binding.layoutUrnResult112.text = "出生"
            }
            "천주교" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE
                if(boneEngraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult112.text = "出\n生"
                else
                    binding.layoutUrnResult112.text = "出生"
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

            if(selectedUrnName2!!.contains("블랙") || selectedUrnName2!!.contains("검정")
                || selectedUrnName2!!.contains("휴안홍") || selectedUrnName2!!.contains("휴안흑")){
                imageName1 += "_2"
                imageName2 += "_2"
                imageName3 += "_2"
                imageName4 += "_2"
                imageName5 += "_2"
                imageName6 += "_2"
                imageName7 += "_2"
                imageName8 += "_2"
            }
            var imageResource = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            binding.layoutUrnResult121.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            binding.layoutUrnResult122.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName3, "drawable", requireActivity().packageName)
            binding.layoutUrnResult123.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName4, "drawable", requireActivity().packageName)
            binding.layoutUrnResult124.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName5, "drawable", requireActivity().packageName)
            binding.layoutUrnResult141.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName6, "drawable", requireActivity().packageName)
            binding.layoutUrnResult142.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName7, "drawable", requireActivity().packageName)
            binding.layoutUrnResult161.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName8, "drawable", requireActivity().packageName)
            binding.layoutUrnResult162.setImageResource(imageResource)
        }
        if(boneDate1Type == "양력")
            binding.layoutUrnResult17.text = "陽"
        else if(boneDate1Type == "음력")
            binding.layoutUrnResult17.text = "陰"

        // 사망일
        // 출생일
        when (boneEngraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutUrnResult311.visibility = View.VISIBLE
                binding.layoutUrnResult312.visibility = View.GONE
            }
            "기독교", "순복음" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE
                if(boneEngraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult312.text = "召\n天"
                else
                    binding.layoutUrnResult312.text = "召天"
            }
            "천주교" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE

                val yujimai = ResourcesCompat.getFont(requireContext(), R.font.yujimai)
                binding.layoutUrnResult312.typeface = yujimai
                binding.layoutUrnResult312.letterSpacing = -0.1f
                binding.layoutUrnResult312.scaleX = 0.9f

                if(boneEngraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult312.text = "善\n終"
                else
                    binding.layoutUrnResult312.text = "善終"
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

            if(selectedUrnName2!!.contains("블랙") || selectedUrnName2!!.contains("검정")
                || selectedUrnName2!!.contains("휴안홍") || selectedUrnName2!!.contains("휴안흑")){
                imageName1 += "_2"
                imageName2 += "_2"
                imageName3 += "_2"
                imageName4 += "_2"
                imageName5 += "_2"
                imageName6 += "_2"
                imageName7 += "_2"
                imageName8 += "_2"
            }
            var imageResource = resources.getIdentifier(imageName1, "drawable", requireActivity().packageName)
            binding.layoutUrnResult321.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName2, "drawable", requireActivity().packageName)
            binding.layoutUrnResult322.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName3, "drawable", requireActivity().packageName)
            binding.layoutUrnResult323.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName4, "drawable", requireActivity().packageName)
            binding.layoutUrnResult324.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName5, "drawable", requireActivity().packageName)
            binding.layoutUrnResult341.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName6, "drawable", requireActivity().packageName)
            binding.layoutUrnResult342.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName7, "drawable", requireActivity().packageName)
            binding.layoutUrnResult361.setImageResource(imageResource)

            imageResource = resources.getIdentifier(imageName8, "drawable", requireActivity().packageName)
            binding.layoutUrnResult362.setImageResource(imageResource)
        }

        if(boneDate2Type == "양력")
            binding.layoutUrnResult37.text = "陽"
        else if(boneDate2Type == "음력")
            binding.layoutUrnResult37.text = "陰"
    }
}