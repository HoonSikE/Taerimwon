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
        engraveTypePosition = ApplicationClass.prefs.engraveTypePosition
        engraveType2Position = ApplicationClass.prefs.engraveType2Position

        date1 = ApplicationClass.prefs.date1.toString()
        date1Type = ApplicationClass.prefs.date1Type.toString()
        date2 = ApplicationClass.prefs.date2.toString()
        date2Type = ApplicationClass.prefs.date2Type.toString()

        if(!selectedUrnType.contains("선택안함") && (selectedUrnType.contains("합골함1") || !ApplicationClass.prefs.selectedUrnType2.toString().contains("선택안함"))) {
            if(ApplicationClass.prefs.boneSex == "남성"){
                selectedUrnName = ApplicationClass.prefs.selectedUrnName2.toString()
                name1 = ApplicationClass.prefs.boneName1.toString()
                name2 = ApplicationClass.prefs.boneName2.toString()
                engraveType = ApplicationClass.prefs.boneEngraveType.toString()
                engraveType2 = ApplicationClass.prefs.boneEngraveType2.toString()
                engraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition
                engraveType2Position = ApplicationClass.prefs.boneEngraveType2Position

                date1 = ApplicationClass.prefs.boneDate1.toString()
                date1Type = ApplicationClass.prefs.boneDate1Type.toString()
                date2 = ApplicationClass.prefs.boneDate2.toString()
                date2Type = ApplicationClass.prefs.boneDate2Type.toString()
            }
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

        if(engraveTypePosition == 0){
            binding.layoutUrnResult21.visibility = View.VISIBLE
            binding.imageUrnResult21.visibility = View.GONE
            return
        }
        var imageName = "img_mark" + (engraveTypePosition + 1)

        if(engraveTypePosition == 4 || engraveTypePosition == 5){
            if(engraveType2Position == 0)
                imageName = "img_mark5"
            else if(engraveType2Position == 1 || engraveType2Position == 2)
                imageName = "img_mark5_2"
        }

        if(selectedUrnName!!.contains("블랙") || selectedUrnName!!.contains("검정")
            || selectedUrnName!!.contains("휴안홍") || selectedUrnName!!.contains("휴안흑")) {
            if(engraveTypePosition == 3){
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

        if(engraveType2.contains("年月日")){
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

        if((engraveType == "일반" && (engraveType2 == "기본" || engraveType2.contains("年月日")))
            || (engraveType == "기독교" && (engraveType2 == "직분X"))
            || (engraveType == "불교" && (engraveType2 == "기본" || engraveType2.contains("年月日")))
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
                if(engraveType2.contains("年月日"))
                    binding.layoutUrnResult112.text = "出\n生"
                else
                    binding.layoutUrnResult112.text = "出生"
            }
        }

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

                val yujimai = ResourcesCompat.getFont(requireContext(), R.font.yujimai)
                binding.layoutUrnResult312.typeface = yujimai
                binding.layoutUrnResult312.letterSpacing = -0.1f
                binding.layoutUrnResult312.scaleX = 0.7f

                if(engraveType2.toString().contains("年月日"))
                    binding.layoutUrnResult312.text = "善\n終"
                else
                    binding.layoutUrnResult312.text = "善終"
            }
        }

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

        if(date2Type == "양력")
            binding.layoutUrnResult37.text = "陽"
        else if(date2Type == "음력")
            binding.layoutUrnResult37.text = "陰"
    }
}