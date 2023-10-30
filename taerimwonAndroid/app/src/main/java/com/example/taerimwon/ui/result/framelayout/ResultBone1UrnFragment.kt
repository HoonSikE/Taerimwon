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

@AndroidEntryPoint
class ResultBone1UrnFragment : BaseFragment<FragmentResultBone1UrnBinding>(R.layout.fragment_result_bone1_urn) {
    private lateinit var selectedUrnType: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()

        if(selectedUrnType != "선택안함") {
            if(selectedUrnType.contains("합골함1")){
                setBone1Data()
            }else{
                binding.layoutResultContent.visibility = View.GONE
            }
        }
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

        // 내용이 없으면 빈 값
        if(ApplicationClass.prefs.boneName1 == "") {
            binding.layoutResultContent.visibility = View.INVISIBLE
            return
        } else {
            binding.layoutResultContent.visibility = View.VISIBLE
            setBone1Mark()
        }

        val pixel_size_25 = resources.getDimensionPixelSize(R.dimen.pixel_size_25)
        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)
        val pixel_size_50 = resources.getDimensionPixelSize(R.dimen.pixel_size_50)
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
}