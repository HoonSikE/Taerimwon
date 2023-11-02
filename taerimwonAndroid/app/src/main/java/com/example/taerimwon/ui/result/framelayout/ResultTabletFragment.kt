package com.example.taerimwon.ui.result.framelayout

import android.graphics.*
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultTabletBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultTabletFragment : BaseFragment<FragmentResultTabletBinding>(R.layout.fragment_result_tablet) {
    private lateinit var selectedTabletType: String
    private lateinit var boneSelectedTabletType: String
    private lateinit var tabletType: String
    private lateinit var tabletReligion: String
    private lateinit var selectedTabletName: String
    private lateinit var tabletName2: String
    private lateinit var name3: String

    override fun init() {
        initData()
    }
    private fun initData() {
        selectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
        boneSelectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
        tabletType = ApplicationClass.prefs.tabletType.toString()
        tabletReligion = ApplicationClass.prefs.tabletReligion.toString()
        selectedTabletName = ApplicationClass.prefs.selectedTabletName.toString()
        tabletName2 = ApplicationClass.prefs.tabletName2.toString()
        name3 = ApplicationClass.prefs.name3.toString()

        if(!selectedTabletType.contains("선택안함") && !selectedTabletType.contains("합골") && !boneSelectedTabletType.contains("선택안함")) {
            if(ApplicationClass.prefs.boneTabletSex == "남성"){
                selectedTabletType = ApplicationClass.prefs.boneSelectedTabletType.toString()
                boneSelectedTabletType = ApplicationClass.prefs.selectedTabletType.toString()
                tabletType = ApplicationClass.prefs.boneTabletType.toString()
                tabletReligion = ApplicationClass.prefs.boneTabletReligion.toString()
                selectedTabletName = ApplicationClass.prefs.selectedTabletName2.toString()
                tabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
                name3 = ApplicationClass.prefs.boneName3.toString()
            }
        }

        if(selectedTabletType != "선택안함") {
            if(selectedTabletType.contains("사진")){
                binding.layoutResultContent.visibility = View.GONE
            }else if(tabletType.toString().contains("문구")){
                binding.layoutResultContent.visibility = View.GONE
            }else if(tabletType.toString().contains("합골")) {
                binding.layoutResultContent.visibility = View.GONE
            }else {
                setTabletData()
            }
        }
    }
    private fun setTableMark() {
        var imageName = "img_mark1"

        if(tabletReligion == "일반" && tabletType == "본관") {
            binding.layoutTabletResult10.visibility = View.GONE
            binding.layoutTabletResult12.visibility = View.GONE
        }else if(tabletReligion == "일반" && tabletType != "문구") {
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

        setTableMark()
        val pixel_size_13 = resources.getDimensionPixelSize(R.dimen.pixel_size_13)
        val pixel_size_15 = resources.getDimensionPixelSize(R.dimen.pixel_size_15)
        val pixel_size_17_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_17_5)
        val pixel_size_20 = resources.getDimensionPixelSize(R.dimen.pixel_size_20)
//        val pixel_size_22_5 = resources.getDimensionPixelSize(R.dimen.pixel_size_22_5)
//        val pixel_size_26 = resources.getDimensionPixelSize(R.dimen.pixel_size_26)
//        val pixel_size_30 = resources.getDimensionPixelSize(R.dimen.pixel_size_30)
//        val pixel_size_35 = resources.getDimensionPixelSize(R.dimen.pixel_size_35)
//        val pixel_size_40 = resources.getDimensionPixelSize(R.dimen.pixel_size_40)
//        val pixel_size_45 = resources.getDimensionPixelSize(R.dimen.pixel_size_45)


        val tmp = StringBuilder()
        val tmp2 = StringBuilder()

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
}