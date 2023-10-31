package com.example.taerimwon.ui.result.framelayout

import android.graphics.*
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultUrn2Binding
import com.example.taerimwon.databinding.FragmentResultUrnBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultUrn2Fragment : BaseFragment<FragmentResultUrn2Binding>(R.layout.fragment_result_urn2) {
    private lateinit var selectedUrnType2: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedUrnType2 = ApplicationClass.prefs.selectedUrnType2.toString()

        if(selectedUrnType2 != "선택안함") {
            setUrnData()
        }else{
            binding.layoutResultContent.visibility = View.GONE
        }
    }
    private fun setUrnMark() {
        if(ApplicationClass.prefs.boneName1 == "")
            return
        // 이미지 이름을 문자열로 정의합니다.
        val boneEngraveTypePosition = ApplicationClass.prefs.boneEngraveTypePosition

        if(boneEngraveTypePosition == 0){
            binding.layoutUrnResult21.visibility = View.VISIBLE
            binding.imageUrnResult21.visibility = View.GONE
            return
        }
        var imageName = "img_mark" + (boneEngraveTypePosition + 1)

        if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5){
            if(ApplicationClass.prefs.boneEngraveType2Position == 0)
                imageName = "img_mark5"
            else if(ApplicationClass.prefs.boneEngraveType2Position == 1 || ApplicationClass.prefs.boneEngraveType2Position == 2)
                imageName = "img_mark5_2"
        }

        val selectedUrnName2 = ApplicationClass.prefs.selectedUrnName2
        if(selectedUrnName2!!.contains("블랙") || selectedUrnName2!!.contains("검정")) {
            if(boneEngraveTypePosition == 3){
                imageName = "img_mark4_2"
            }else if(boneEngraveTypePosition == 4 || boneEngraveTypePosition == 5) {
                imageName = "img_mark5_2"
            }
        }

        val imageResource = resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
        binding.imageUrnResult21.setImageResource(imageResource)
    }
    private fun setUrnData() {
        val selectedUrnName2 = ApplicationClass.prefs.selectedUrnName2

        if(selectedUrnName2!!.contains("블랙") || selectedUrnName2!!.contains("검정")){
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

        when(selectedUrnName2){
            // 7. 수목함
            "운학수목함 WH-1 1505", "황토수목함 HT-1 1604",
            // 4. 잠금형 삼중 명품자개함
            "아름골드자개 ARG-1 8025", "아름꽃자개 ARF-2 8025", "아름화이트자개 SGS-3 8025", "휴안골드자개 HUG-16 10228",
            "휴안백십장생자개 HUG-17 12535" , "휴안블랙자개 HUG-15 10228", "휴안홍한지십장생자개 HUG-19 13139", "휴안화이트자개 HUG-14 10228",
            "휴안흑십장생자개 HUG-18 12535" ,
            -> {
                binding.layoutResultContent.visibility = View.INVISIBLE
                return
            }
        }
        // 내용이 없으면 빈 값
        if(ApplicationClass.prefs.boneName1 == "") {
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

        // 이름
        val boneName1 = ApplicationClass.prefs.boneName1.toString()
        val boneName2 = ApplicationClass.prefs.boneName2.toString()
        val tmp = StringBuilder()

        val boneEngraveType = ApplicationClass.prefs.boneEngraveType
        val boneEngraveType2 = ApplicationClass.prefs.boneEngraveType2

        if(boneEngraveType2.toString().contains("年月日")){
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

                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }else if(boneEngraveType2 == "형제" || boneEngraveType2 == "자매"){
            binding.layoutUrnResult222.visibility = View.VISIBLE

            if(boneEngraveType2 == "형제")
                binding.layoutUrnResult222.text = "형제"
            else if(boneEngraveType2 == "자매")
                binding.layoutUrnResult222.text = "자매"

            layoutUrnResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }else if(boneEngraveType == "기독교" && (boneEngraveType2 == "기본" || boneEngraveType2.toString().contains("年月日"))
            || boneEngraveType == "불교" && boneEngraveType2 == "법명"
            || boneEngraveType == "순복음"){
            val layoutUrnResult220 = binding.layoutUrnResult220
            layoutUrnResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_50.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
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
            layoutUrnResult221.height = pixel_size_170
            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                    layoutUrnResult221.setLineSpacing(0f, 1.1f)
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_40.toFloat())
                    layoutUrnResult221.setLineSpacing(0f, 1.0f)
                }
            }
            layoutUrnResult221.text = tmp.toString()
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

            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutUrnResult221.text = tmp.toString()
        }else if(boneEngraveType == "묘법"){
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

            when (boneName1.length) {
                2 -> {
                    tmp.append(boneName1[0]).append("\n").append("\n").append(boneName1[1])
                }
                3 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2])
                }
                4 -> {
                    tmp.append(boneName1[0]).append("\n").append(boneName1[1]).append("\n").append(boneName1[2]).append("\n").append(boneName1[3])
                    layoutUrnResult221.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixel_size_35.toFloat())
                }
            }
            layoutUrnResult221.text = tmp.toString()
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
                binding.layoutUrnResult312.scaleX = 0.7f

                if(boneEngraveType2.toString().contains("年月日"))
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
}