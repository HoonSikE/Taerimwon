package com.example.taerimwon.ui.order.add

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.data.dto.add.AddItem
import com.example.taerimwon.databinding.FragmentAddContainerBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.order.OrderViewModel


@AndroidEntryPoint
class AddContainerFragment : BaseFragment<FragmentAddContainerBinding>(R.layout.fragment_add_container) {
    // 자동완성 단어들을 담을 리스트
    private lateinit var searchList: MutableList<AddItem>
    private lateinit var searchList2: MutableList<AddItem>

    override fun init() {
        initData()
        setOnClickListeners()
        addTextChangedListener()
        observer()
    }
    private fun initData() {
    }
    private fun setOnClickListeners() {
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag1 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedPyeongjangItem = adapterView.getItemAtPosition(position) as AddItem
            binding.autoCompleteTextView.setText(selectedPyeongjangItem.addItem)
            ApplicationClass.prefs.selectedPyeongjangName = selectedPyeongjangItem.addItem
        }

        binding.autoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
    }
    private fun addTextChangedListener(){
        binding.autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
//                ApplicationClass.prefs.selectedAddName = s?.toString() ?: ""
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
        searchList.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
    }
    private fun settingList1_2() {
        searchList = mutableListOf()
        searchList.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))
    }
    private fun settingList2_1() {
        searchList2 = mutableListOf()
        searchList2.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList2.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList2.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList2.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
    }
    private fun settingList2_2() {
        searchList2 = mutableListOf()
        searchList2.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))
    }
}