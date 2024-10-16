package com.example.taerimwon.ui.result.framelayout

import android.graphics.*
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultUrnBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class ResultUrnFragment : BaseFragment<FragmentResultUrnBinding>(R.layout.fragment_result_urn) {
    private lateinit var selectedUrnType: String
    private lateinit var selectedUrnName : String
    private lateinit var name1 : String
    private lateinit var name2 : String
    private lateinit var engraveType : String
    private lateinit var engraveType2 : String
    private lateinit var checkCatholic : String
    private var engraveTypePosition by Delegates.notNull<Int>()
    private var engraveType2Position by Delegates.notNull<Int>()
    private lateinit var date1 : String
    private lateinit var date1Type : String
    private lateinit var date2 : String
    private lateinit var date2Type : String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        selectedUrnName = ApplicationClass.prefs.selectedUrnName.toString()
        name1 = ApplicationClass.prefs.name1.toString()
        name2 = ApplicationClass.prefs.name2.toString()
        engraveType = ApplicationClass.prefs.engraveType.toString()
        engraveType2 = ApplicationClass.prefs.engraveType2.toString()
        checkCatholic = ApplicationClass.prefs.checkCatholic.toString()
        engraveTypePosition = ApplicationClass.prefs.engraveTypePosition
        engraveType2Position = ApplicationClass.prefs.engraveType2Position

        date1 = ApplicationClass.prefs.date1.toString()
        date1Type = ApplicationClass.prefs.date1Type.toString()
        date2 = ApplicationClass.prefs.date2.toString()
        date2Type = ApplicationClass.prefs.date2Type.toString()

        if(!selectedUrnType.contains("선택안함") && (ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입1)") || selectedUrnName.contains("ZEN사각합골진공함") || !ApplicationClass.prefs.selectedUrnType2.toString().contains("선택안함"))) {
            if(ApplicationClass.prefs.boneSex == "남성"){
                selectedUrnName = ApplicationClass.prefs.selectedUrnName2.toString()
                name1 = ApplicationClass.prefs.boneName1.toString()
                name2 = ApplicationClass.prefs.boneName2.toString()
                engraveType = ApplicationClass.prefs.boneEngraveType.toString()
                engraveType2 = ApplicationClass.prefs.boneEngraveType2.toString()
                checkCatholic = ApplicationClass.prefs.checkCatholic2.toString()
                engraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
                engraveType2Position = ApplicationClass.prefs.boneEngraveType2Position

                date1 = ApplicationClass.prefs.boneDate1.toString()
                date1Type = ApplicationClass.prefs.boneDate1Type.toString()
                date2 = ApplicationClass.prefs.boneDate2.toString()
                date2Type = ApplicationClass.prefs.boneDate2Type.toString()
            }
        }

        if(ApplicationClass.prefs.selectedUrnName!!.contains("미정") || ApplicationClass.prefs.selectedUrnName!!.contains("미정(타입1)")){
            binding.layoutResultContent.setBackgroundColor(resources.getColor(android.R.color.black))
            binding.layoutUrnContent.setBackgroundColor(resources.getColor(android.R.color.white))
        }

        if(selectedUrnType != "선택안함") {
            setUrnData()
        }else{
            binding.layoutResultContent.visibility = View.GONE
        }
    }
    private fun setUrnMark() {
        if(name1 == "")
            return

        if(selectedUrnName!!.contains("휴안화이트기독교") || selectedUrnName!!.contains("휴안화이트불교")
            || selectedUrnName!!.contains("휴안화이트천주교")) {
            return
        }

        if(engraveTypePosition == 0){
            binding.layoutUrnResult21.visibility = View.VISIBLE
            binding.imageUrnResult21.visibility = View.GONE
            return
        }
        var imageName = "img_mark" + (engraveTypePosition + 1)

        if(engraveTypePosition == 4 || engraveTypePosition == 5){
            if(engraveType2Position == 0 || engraveType2Position == 2)
                imageName = "img_mark5"
            else if(engraveType2Position == 1 || engraveType2Position == 3)
                imageName = "img_mark5_2"
        }

        if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
            || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")) {
            if(engraveTypePosition == 1){
                imageName = "img_mark2_2"
            }else if(engraveTypePosition == 3){
                imageName = "img_mark4_2"
            }else if(engraveTypePosition == 4 || engraveTypePosition == 5) {
                imageName = "img_mark5_2"
            }
        }

        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageUrnResult21.setImageResource(imageResource)
    }
    private fun setUrnData() {
        if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
            || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")){
            binding.layoutUrnResult111.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult112.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult13.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult131.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult15.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult151.setTextColor(Color.parseColor("#FFD700"))
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
            binding.layoutUrnResult33.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult331.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult35.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult351.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult371.setTextColor(Color.parseColor("#FFD700"))
            binding.layoutUrnResult37.setTextColor(Color.parseColor("#FFD700"))
        }

        when(selectedUrnName){
            // 7. 수목함
            "운학수목함 WH-1 1505", "황토수목함 HT-1 1604",
            -> {
                binding.layoutResultContent.visibility = View.INVISIBLE
                return
            }
        }
        // 내용이 없으면 빈 값
        if(name1 == "") {
            binding.layoutResultContent.visibility = View.INVISIBLE
            return
        } else {
            binding.layoutResultContent.visibility = View.VISIBLE
            setUrnMark()
        }

        val pixel_size_43 = resources.getDimensionPixelSize(R.dimen.pixel_size_43)
        val pixel_size_50 = resources.getDimensionPixelSize(R.dimen.pixel_size_50)
        val pixel_size_65 = resources.getDimensionPixelSize(R.dimen.pixel_size_65)
        val pixel_size_75 = resources.getDimensionPixelSize(R.dimen.pixel_size_75)
        val pixel_size_105 = resources.getDimensionPixelSize(R.dimen.pixel_size_105)
        val pixel_size_120 = resources.getDimensionPixelSize(R.dimen.pixel_size_120)
        val pixel_size_130 = resources.getDimensionPixelSize(R.dimen.pixel_size_130)
        val pixel_size_135 = resources.getDimensionPixelSize(R.dimen.pixel_size_135)
        val pixel_size_140 = resources.getDimensionPixelSize(R.dimen.pixel_size_140)
        val pixel_size_150 = resources.getDimensionPixelSize(R.dimen.pixel_size_150)
        val pixel_size_240 = resources.getDimensionPixelSize(R.dimen.pixel_size_240)
        val pixel_size_270 = resources.getDimensionPixelSize(R.dimen.pixel_size_270)
        val pixel_size_300 = resources.getDimensionPixelSize(R.dimen.pixel_size_300)


        val tmp = StringBuilder()

        if(engraveType2.contains("年月日")){
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)

            binding.layoutUrnResult111.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            binding.layoutUrnResult111.setTypeface(hyhaeso, Typeface.NORMAL)
            binding.layoutUrnResult112.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            binding.layoutUrnResult112.setTypeface(hyhaeso, Typeface.NORMAL)

            binding.layoutUrnResult110.visibility = View.GONE
            binding.layoutUrnResult120.visibility = View.GONE
            binding.layoutUrnResult170.visibility = View.GONE

            binding.layoutUrnResult13.visibility = View.GONE
            binding.layoutUrnResult131.visibility = View.VISIBLE
            binding.layoutUrnResult15.visibility = View.GONE
            binding.layoutUrnResult151.visibility = View.VISIBLE
            binding.layoutUrnResult171.visibility = View.VISIBLE

            binding.layoutUrnResult17.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            binding.layoutUrnResult17.setTypeface(hyhaeso, Typeface.NORMAL)

            binding.layoutUrnResult311.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            binding.layoutUrnResult311.setTypeface(hyhaeso, Typeface.NORMAL)
            binding.layoutUrnResult312.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            binding.layoutUrnResult312.setTypeface(hyhaeso, Typeface.NORMAL)

            binding.layoutUrnResult310.visibility = View.GONE
            binding.layoutUrnResult320.visibility = View.GONE
            binding.layoutUrnResult370.visibility = View.GONE

            binding.layoutUrnResult33.visibility = View.GONE
            binding.layoutUrnResult331.visibility = View.VISIBLE
            binding.layoutUrnResult35.visibility = View.GONE
            binding.layoutUrnResult351.visibility = View.VISIBLE
            binding.layoutUrnResult371.visibility = View.VISIBLE

            binding.layoutUrnResult37.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            binding.layoutUrnResult37.setTypeface(hyhaeso, Typeface.NORMAL)
        }

        binding.layoutUrnResult22.visibility = View.VISIBLE
        // 이름
        val layoutUrnResult2211 = binding.layoutUrnResult2211
        val layoutUrnResult2212 = binding.layoutUrnResult2212
        val layoutUrnResult2213 = binding.layoutUrnResult2213
        val layoutUrnResult2214 = binding.layoutUrnResult2214

        if((engraveType == "일반" && (engraveType2 == "기본" || engraveType2.contains("한글")|| engraveType2.contains("年月日")))
            || (engraveType == "기독교" && (engraveType2 == "직분X"))
            || (engraveType == "불교" && (engraveType2 == "기본" || engraveType2.contains("한글") || engraveType2.contains("年月日")))
            || (engraveType == "천주교" && (engraveType2 == "세례명X"))
            || (engraveType == "원불교" && (engraveType2 == "기본" || engraveType2.contains("한글")))){

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

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
            }
        }else if(engraveType2 == "형제" || engraveType2 == "자매"){
            binding.layoutUrnResult222.visibility = View.VISIBLE

            if(engraveType2 == "형제")
                binding.layoutUrnResult222.text = "형제"
            else if(engraveType2 == "자매")
                binding.layoutUrnResult222.text = "자매"

            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
            }
        }else if(engraveType == "기독교" && (engraveType2 == "기본" || engraveType2.contains("한글") || engraveType2.toString().contains("年月日"))
            || engraveType == "불교" && engraveType2 == "법명"
            || engraveType == "순복음" && (engraveType2 == "기본" || engraveType2.contains("한글"))){
            val layoutUrnResult220 = binding.layoutUrnResult220
            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
            }
            layoutUrnResult220.visibility = View.VISIBLE
            when (name2.length) {
                3 -> {
                    layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
                    layoutUrnResult220.letterSpacing = -0.1f
//                    layoutUrnResult220.textScaleY = 1.1f
                    layoutUrnResult220.scaleY = 1.1f
                }
                4 -> {
//                    layoutUrnResult220.width = pixel_size_300
                    layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_65.toFloat())
                    layoutUrnResult220.letterSpacing = -0.18f
//                    layoutUrnResult220.textScaleX = 1.2f
                    layoutUrnResult220.scaleY = 1.2f

                }
            }
            layoutUrnResult220.text = name2
        } else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2.contains("한글") || engraveType2.toString().contains("年月日"))){
            val layoutUrnResult222 = binding.layoutUrnResult222
//            layoutUrnResult221.height = pixel_size_170
            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()
                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()
                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_140.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
            }
            layoutUrnResult222.visibility = View.VISIBLE
            layoutUrnResult222.width = pixel_size_300
            when (name2.length) {
                3 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
                    layoutUrnResult222.letterSpacing = -0.1f
//                    layoutUrnResult220.textScaleY = 1.1f
                    layoutUrnResult222.scaleY = 1.1f
                }
                4 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_65.toFloat())
                    layoutUrnResult222.letterSpacing = -0.18f
//                    layoutUrnResult220.textScaleX = 1.2f
                    layoutUrnResult222.scaleY = 1.2f
                }
                5 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult222.letterSpacing = -0.1f
                    layoutUrnResult222.scaleY = 1.5f
                }
                6 -> {
                    layoutUrnResult222.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_43.toFloat())
                    layoutUrnResult222.letterSpacing = -0.1f
                    layoutUrnResult222.scaleY = 1.7f
                }
            }
            layoutUrnResult222.text = name2
        }else if(engraveType == "SGI"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            layoutUrnResult220.width = pixel_size_240
            val serifamedium = ResourcesCompat.getFont(requireContext(), R.font.serifamedium)
            layoutUrnResult220.typeface = serifamedium
            layoutUrnResult220.text = "SGI"
            layoutUrnResult220.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_75.toFloat())
            layoutUrnResult220.letterSpacing = 0f

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE
            if (engraveType2.contains("한글")){
                val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                layoutUrnResult222.setTypeface(hygungso, Typeface.NORMAL)
                layoutUrnResult222.text = "위"
            }else {
                val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
                layoutUrnResult222.setTypeface(hyhaeso, Typeface.NORMAL)
                layoutUrnResult222.text = "位"
            }

            layoutUrnResult222.setLineSpacing(0f, 1.0f)

            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                }
            }
        }else if(engraveType == "묘법"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult220.visibility = View.VISIBLE
            val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
            val hyhaeso = ResourcesCompat.getFont(requireContext(), R.font.hyhaeso)
            if (engraveType2.contains("한글")){
                layoutUrnResult220.setTypeface(hygungso, Typeface.NORMAL)
                layoutUrnResult220.text = "묘법"
            }else {
                layoutUrnResult220.setTypeface(hyhaeso, Typeface.NORMAL)
                layoutUrnResult220.text = "妙法"
            }

            layoutUrnResult220.letterSpacing = 0f

            val layoutUrnResult222 = binding.layoutUrnResult222
            layoutUrnResult222.visibility = View.VISIBLE

            if (engraveType2.contains("한글")){
                layoutUrnResult222.setTypeface(hygungso, Typeface.NORMAL)
                layoutUrnResult222.text = "위"
            }else {
                layoutUrnResult222.setTypeface(hyhaeso, Typeface.NORMAL)
                layoutUrnResult222.text = "位"
            }

            when (name1.length) {
                2 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2214.text = name1[1].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
                3 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2214.text = name1[2].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_130.toFloat())
                }
                4 -> {
                    layoutUrnResult2211.text = name1[0].toString()
                    layoutUrnResult2212.visibility = View.VISIBLE
                    layoutUrnResult2212.text = name1[1].toString()
                    layoutUrnResult2213.visibility = View.VISIBLE
                    layoutUrnResult2213.text = name1[2].toString()
                    layoutUrnResult2214.text = name1[3].toString()

                    layoutUrnResult2211.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                    layoutUrnResult2212.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                    layoutUrnResult2213.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                    layoutUrnResult2214.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_105.toFloat())
                }
            }
        }

        // 출생일
        when (engraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutUrnResult111.visibility = View.VISIBLE
                binding.layoutUrnResult112.visibility = View.GONE
                val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                if (engraveType2.contains("한글")){
                    binding.layoutUrnResult111.setTypeface(hygungso, Typeface.NORMAL)
                    binding.layoutUrnResult111.text = "생"
                }
            }
            "기독교", "순복음" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE
                if(engraveType2.toString().contains("年月日")) {
                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    if (engraveType2.contains("한글")){
                        binding.layoutUrnResult112.setTypeface(hygungso, Typeface.NORMAL)
                        binding.layoutUrnResult112.text = "출\n생"
                    }else {
                        binding.layoutUrnResult112.text = "出\n生"
                    }
                }else{
                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    if (engraveType2.contains("한글")){
                        binding.layoutUrnResult112.setTypeface(hygungso, Typeface.NORMAL)
                        binding.layoutUrnResult112.text = "출생"
                    }else {
                        binding.layoutUrnResult112.text = "出生"
                    }
                }
            }
            "천주교" -> {
                binding.layoutUrnResult111.visibility = View.GONE
                binding.layoutUrnResult112.visibility = View.VISIBLE

                if(engraveType2.contains("年月日")) {
                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    if (engraveType2.contains("한글")){
                        binding.layoutUrnResult112.setTypeface(hygungso, Typeface.NORMAL)
                        binding.layoutUrnResult112.text = "출\n생"
                    }else {
                        binding.layoutUrnResult112.text = "出\n生"
                    }
                }else {
                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    if (engraveType2.contains("한글")){
                        binding.layoutUrnResult112.setTypeface(hygungso, Typeface.NORMAL)
                        binding.layoutUrnResult112.text = "출생"
                    }else {
                        binding.layoutUrnResult112.text = "出生"
                    }
                }
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

            if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
                || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")){
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

        if(date1Type == "양력") {
            val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
            if (engraveType2.contains("한글")){
                binding.layoutUrnResult17.setTypeface(hygungso, Typeface.NORMAL)
                binding.layoutUrnResult17.text = "양"
            }else {
                binding.layoutUrnResult17.text = "陽"
            }
        }else if(date1Type == "음력") {
            val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
            if (engraveType2.contains("한글")){
                binding.layoutUrnResult17.setTypeface(hygungso, Typeface.NORMAL)
                binding.layoutUrnResult17.text = "음"
            }else {
                binding.layoutUrnResult17.text = "陰"
            }
        }
        // 사망일
        // 출생일
        when (engraveType) {
            "일반", "불교", "묘법", "SGI", "원불교" -> {
                binding.layoutUrnResult311.visibility = View.VISIBLE
                binding.layoutUrnResult312.visibility = View.GONE

                val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                if (engraveType2.contains("한글")){
                    binding.layoutUrnResult311.setTypeface(hygungso, Typeface.NORMAL)
                    binding.layoutUrnResult311.text = "졸"
                }
            }
            "기독교", "순복음" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE
                if(engraveType2.toString().contains("年月日")) {

                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    if (checkCatholic == "별세") {
                        val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                        if (engraveType2.contains("한글")){
                            binding.layoutUrnResult312.setTypeface(hygungso, Typeface.NORMAL)
                            binding.layoutUrnResult312.text = "별\n세"
                        }else {
                            binding.layoutUrnResult312.text = "別\n世"
                        }
                    }else{
                        if (engraveType2.contains("한글")){
                            binding.layoutUrnResult312.setTypeface(hygungso, Typeface.NORMAL)
                            binding.layoutUrnResult312.text = "소\n천"
                        }else {
                            binding.layoutUrnResult312.text = "召\n天"
                        }
                    }
                }else{
                    if (checkCatholic == "별세") {
                        val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                        if (engraveType2.contains("한글")){
                            binding.layoutUrnResult312.setTypeface(hygungso, Typeface.NORMAL)
                            binding.layoutUrnResult312.text = "별세"
                        }else {
                            binding.layoutUrnResult312.text = "別世"
                        }
                    }else {
                        val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                        if (engraveType2.contains("한글")) {
                            binding.layoutUrnResult312.setTypeface(hygungso, Typeface.NORMAL)
                            binding.layoutUrnResult312.text = "소천"
                        } else {
                            binding.layoutUrnResult312.text = "召天"
                        }
                    }
                }
            }
            "천주교" -> {
                binding.layoutUrnResult311.visibility = View.GONE
                binding.layoutUrnResult312.visibility = View.VISIBLE

                if(engraveType2.contains("年月日")) {
                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    val yujimai = ResourcesCompat.getFont(requireContext(), R.font.yujimai)

                    if (engraveType2.contains("한글")){
                        binding.layoutUrnResult312.setTypeface(hygungso, Typeface.NORMAL)
                        binding.layoutUrnResult312.text = "선\n종"
                    }else {
                        val width = binding.layoutUrnResult112.width
                        val height = binding.layoutUrnResult112.height
//                        binding.layoutUrnResult312.typeface = yujimai
//                        binding.layoutUrnResult312.setLineSpacing(0f, -15.0f)
//                        binding.layoutUrnResult312.text = "善\n終"
                        binding.layoutUrnResult312.visibility = View.GONE
                        binding.layoutUrnResult314.visibility = View.VISIBLE
                        if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
                            || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")){
                            binding.layoutUrnResult314.setBackgroundResource(R.drawable.img_text_catholic4)
                        }else{
                            binding.layoutUrnResult314.setBackgroundResource(R.drawable.img_text_catholic3)
                        }
                        val layoutParams = binding.layoutUrnResult312.layoutParams
                        layoutParams.width = width
                        layoutParams.height = height
                        binding.layoutUrnResult312.layoutParams = layoutParams
                    }
                }else {
                    val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
                    val yujimai = ResourcesCompat.getFont(requireContext(), R.font.yujimai)

                    if (engraveType2.contains("한글")){
                        binding.layoutUrnResult312.setTypeface(hygungso, Typeface.NORMAL)
                        binding.layoutUrnResult312.text = "선종"
                    }else {
                        binding.layoutUrnResult312.visibility = View.GONE
                        binding.layoutUrnResult313.visibility = View.VISIBLE
                        if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
                            || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")){
                            binding.layoutUrnResult313.setBackgroundResource(R.drawable.img_text_catholic2)
                        }else{
                            binding.layoutUrnResult313.setBackgroundResource(R.drawable.img_text_catholic)
                        }
                    }
                }
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

            if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
                || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")){
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

        if(date2Type == "양력") {
            val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
            if (engraveType2.contains("한글")){
                binding.layoutUrnResult37.setTypeface(hygungso, Typeface.NORMAL)
                binding.layoutUrnResult37.text = "양"
            }else {
                binding.layoutUrnResult37.text = "陽"
            }
        }else if(date2Type == "음력") {
            val hygungso = ResourcesCompat.getFont(requireContext(), R.font.hygungso)
            if (engraveType2.contains("한글")){
                binding.layoutUrnResult37.setTypeface(hygungso, Typeface.NORMAL)
                binding.layoutUrnResult37.text = "음"
            }else {
                binding.layoutUrnResult37.text = "陰"
            }
        }
    }
}