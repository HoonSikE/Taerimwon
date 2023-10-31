package com.example.taerimwon.ui.order.pyeongjang

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.data.dto.pyeongjang.PyeongjangItem
import com.example.taerimwon.data.dto.urn.UrnItem
import com.example.taerimwon.databinding.FragmentPyeongjangContainerBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.order.OrderViewModel
import com.example.taerimwon.ui.order.urn.UrnAutoCompleteAdapter
import com.example.taerimwon.utils.input.saveImageToInternalStorage
import com.example.taerimwon.utils.view.toast
import java.lang.Math.abs


@AndroidEntryPoint
class PyeongjangContainerFragment : BaseFragment<FragmentPyeongjangContainerBinding>(R.layout.fragment_pyeongjang_container) {
    private val orderViewModel: OrderViewModel by viewModels()

    // 자동완성 단어들을 담을 리스트
    private lateinit var searchList: MutableList<PyeongjangItem>
    private lateinit var searchList2: MutableList<PyeongjangItem>

    override fun init() {
        initData()
        setOnClickListeners()
        setOnItemSelectedListener()
        addTextChangedListener()
        observer()
    }
    private fun initData() {
        val selectedPyeongjangTypesArray = resources.getStringArray(R.array.selected_pyeongjang_types)
        val selectedPyeongjangTypeList = mutableListOf(*selectedPyeongjangTypesArray) as ArrayList<String>
        binding.spinnerPyeongjangType.setSelection(selectedPyeongjangTypeList.indexOf(ApplicationClass.prefs.selectedPyeongjangType))

        if(ApplicationClass.prefs.selectedPyeongjangType == "선택안함"){
            binding.layoutPyeongjangOrder.visibility = View.GONE
            binding.layoutPyeongjangTitle2.visibility = View.GONE
            binding.textPyeongjangGuide.visibility = View.GONE
        }else{
            binding.layoutPyeongjangOrder.visibility = View.VISIBLE
            binding.layoutPyeongjangTitle2.visibility = View.VISIBLE
            binding.textPyeongjangGuide.visibility = View.VISIBLE

            searchList = mutableListOf()
            settingList1_1()

            if(ApplicationClass.prefs.selectedPyeongjangType == "표석")
                settingList1_1()
            else if(ApplicationClass.prefs.selectedPyeongjangType == "밀봉외함")
                settingList1_2()

            binding.autoCompleteTextView.setAdapter(
                PyeongjangAutoCompleteAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    searchList
                )
            )
            binding.autoCompleteTextView.setText(ApplicationClass.prefs.selectedPyeongjangName)

            /**추가*/
            val selectedBonePyeongjangTypesArray = resources.getStringArray(R.array.selected_pyeongjang_types)
            val selectedBonePyeongjangTypeList = mutableListOf(*selectedBonePyeongjangTypesArray) as ArrayList<String>
            binding.spinnerPyeongjangType2.setSelection(selectedBonePyeongjangTypeList.indexOf(ApplicationClass.prefs.selectedPyeongjangType2))

            if(ApplicationClass.prefs.selectedPyeongjangType2 == "선택안함"){
                binding.layoutPyeongjang2.visibility = View.GONE
            }else{
                binding.layoutPyeongjang2.visibility = View.VISIBLE

                searchList2 = mutableListOf()
                settingList2_1()

                if(ApplicationClass.prefs.selectedPyeongjangType2 == "표석")
                    settingList2_1()
                else if(ApplicationClass.prefs.selectedPyeongjangType2 == "밀봉외함")
                    settingList2_2()

                binding.autoCompleteTextView2.setAdapter(
                    PyeongjangAutoCompleteAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        searchList2
                    )
                )
                binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedPyeongjangName2)
            }
        }
    }
    private fun setOnClickListeners() {
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag1 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedPyeongjangItem = adapterView.getItemAtPosition(position) as PyeongjangItem
            binding.autoCompleteTextView.setText(selectedPyeongjangItem.pyeongjangItem)
            ApplicationClass.prefs.selectedPyeongjangName = selectedPyeongjangItem.pyeongjangItem
        }

        binding.autoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
        binding.autoCompleteTextView2.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag2 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedPyeongjangItem = adapterView.getItemAtPosition(position) as PyeongjangItem
            binding.autoCompleteTextView2.setText(selectedPyeongjangItem.pyeongjangItem)
            ApplicationClass.prefs.selectedPyeongjangName2 = selectedPyeongjangItem.pyeongjangItem
        }

        binding.autoCompleteTextView2.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
    }
    private fun setOnItemSelectedListener(){
        binding.spinnerPyeongjangType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedPyeongjangType = parent?.getItemAtPosition(position).toString()
                println(ApplicationClass.prefs.selectedPyeongjangType)

                if(ApplicationClass.prefs.selectedPyeongjangType!!.contains("선택안함")) {
                    binding.layoutPyeongjangOrder.visibility = View.GONE
                    binding.layoutPyeongjangTitle2.visibility = View.GONE
                    binding.textPyeongjangGuide.visibility = View.GONE
                }else {
                    binding.layoutPyeongjangOrder.visibility = View.VISIBLE
                    binding.layoutPyeongjangTitle2.visibility = View.VISIBLE
                    binding.textPyeongjangGuide.visibility = View.VISIBLE

                    when (ApplicationClass.prefs.selectedPyeongjangType) {
                        "표석" -> {
                            settingList1_1()
                            binding.autoCompleteTextView.setAdapter(
                                PyeongjangAutoCompleteAdapter(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    searchList
                                )
                            )
                        }
                        "밀봉외함" -> {
                            settingList1_2()
                            binding.autoCompleteTextView.setAdapter(
                                PyeongjangAutoCompleteAdapter(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    searchList
                                )
                            )
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        /**추가*/
        binding.spinnerPyeongjangType2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedPyeongjangType2 = parent?.getItemAtPosition(position).toString()
                println(ApplicationClass.prefs.selectedPyeongjangType2)

                if(ApplicationClass.prefs.selectedPyeongjangType2!!.contains("선택안함")) {
                    binding.layoutPyeongjang2.visibility = View.GONE
                }else {
                    binding.layoutPyeongjang2.visibility = View.VISIBLE

                    when (ApplicationClass.prefs.selectedPyeongjangType2) {
                        "표석" -> {
                            settingList2_1()

                            binding.autoCompleteTextView2.setAdapter(
                                PyeongjangAutoCompleteAdapter(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    searchList2
                                )
                            )
                        }
                        "밀봉외함" -> {
                            settingList2_2()

                            binding.autoCompleteTextView2.setAdapter(
                                PyeongjangAutoCompleteAdapter(
                                    requireContext(),
                                    android.R.layout.simple_dropdown_item_1line,
                                    searchList2
                                )
                            )
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
    }
    private fun addTextChangedListener(){
        binding.autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedPyeongjangName = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        binding.autoCompleteTextView2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedPyeongjangName2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
    }
    private fun observer() {

    }
    private fun settingList1_1() {
        searchList = mutableListOf()
        searchList.add(PyeongjangItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList.add(PyeongjangItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList.add(PyeongjangItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList.add(PyeongjangItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
    }
    private fun settingList1_2() {
        searchList = mutableListOf()
        searchList.add(PyeongjangItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))
    }
    private fun settingList2_1() {
        searchList2 = mutableListOf()
        searchList2.add(PyeongjangItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList2.add(PyeongjangItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList2.add(PyeongjangItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList2.add(PyeongjangItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
    }
    private fun settingList2_2() {
        searchList2 = mutableListOf()
        searchList2.add(PyeongjangItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))
    }
}