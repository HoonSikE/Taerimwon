package com.example.taerimwon.ui.order.tablet

import android.content.Context
import android.content.SharedPreferences
import android.text.InputFilter
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.databinding.FragmentTabletContainerBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.utils.constant.ENGRAVETYPEPOSITION


@AndroidEntryPoint
class TabletContainerFragment : BaseFragment<FragmentTabletContainerBinding>(R.layout.fragment_tablet_container) {
    private lateinit var tabletSelectTypeAdapter: TabletTypeAdapter
    var tabletSelectTypeList = ArrayList<String>()

    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
        setOnItemSelectedListener()
        observer()
    }
    private val tabletTypeClickListener: (View, String) -> Unit = { _, tabletType ->
        // 배열을 가져옵니다.
        val tabletTypes = tabletType.split(" ")
        ApplicationClass.prefs.tabletType = tabletTypes[1]
        println("tabletType: " + ApplicationClass.prefs.tabletType)

        setName2(ApplicationClass.prefs.engraveType.toString(), ApplicationClass.prefs.engraveType2.toString(), tabletTypes[1])
        setName3(tabletTypes[1])
    }
    private fun initAdapter() {
        tabletSelectTypeAdapter = TabletTypeAdapter(requireContext()).apply {
            onItemClickListener = tabletTypeClickListener
        }

        binding.recyclerviewTabletType.apply {
            adapter = tabletSelectTypeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }
    private fun initData() {
        val selectedTabletTypesArray = resources.getStringArray(R.array.selected_tablet_types)
        val selectedTabletTypeList = mutableListOf(*selectedTabletTypesArray) as ArrayList<String>
        binding.spinnerSelectTabletType.setSelection(selectedTabletTypeList.indexOf(ApplicationClass.prefs.selectedTabletType))

        // 배열을 가져옵니다.
        // 문자열 이름을 문자열로 정의합니다.
        val arrName = "tablet_type" + (ApplicationClass.prefs.engraveTypePosition + 1)
        // 문자열 배열을 가져옵니다.
        val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
        val tabletTypesArray = resources.getStringArray(stringArray)

        // 배열을 리스트로 변환합니다.
        tabletSelectTypeList = mutableListOf(*tabletTypesArray) as ArrayList<String>
        tabletSelectTypeAdapter.updateList(tabletSelectTypeList)
        binding.recyclerviewTabletType.scrollToPosition(ApplicationClass.prefs.tabletTypePosition)
    }
    private fun setOnClickListeners() {
    }
    private fun setOnItemSelectedListener(){
        binding.spinnerSelectTabletType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedTabletType = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
    }
    private fun observer() {

    }
    private fun setName2(engraveType: String, engraveType2: String, tabletType: String) {
        // 직분, 세례명, 법명
        if((engraveType == "기독교" || engraveType == "순복음") && engraveType2 != "기본" && tabletType != "문구") {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "* 직분"
            binding.editTextName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "불교" && engraveType2 != "법명" && tabletType != "문구") {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "* 법명"
            binding.editTextName2.hint = "법명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "천주교" && engraveType2 != "기본" && tabletType != "문구") {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "* 세례명"
            binding.editTextName2.hint = "세례명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(6)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else{
            binding.textName2.visibility = View.GONE
            binding.editTextName2.visibility = View.GONE
            binding.imageName2.visibility = View.GONE
            ApplicationClass.prefs.name2 = ""
        }
    }
    private fun setName3(tabletType: String) {
        if(tabletType.contains("본관")) {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.textName3.text = "* 본관"
            binding.editTextName3.hint = "본관을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(10)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else if(tabletType == "문구") {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.textName3.text = "* 문구"
            binding.editTextName3.hint = "문구를 입력하세요. (최대 30자)"
            val inputFilter = InputFilter.LengthFilter(30)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else{
            binding.textName3.visibility = View.GONE
            binding.editTextName3.visibility = View.GONE
            binding.imageName3.visibility = View.GONE
            ApplicationClass.prefs.name2 = ""
        }
    }
}