package com.example.taerimwon.ui.order.urn

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.databinding.FragmentUrnContainerBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.utils.input.DateTextWatcher


@AndroidEntryPoint
class UrnContainerFragment : BaseFragment<FragmentUrnContainerBinding>(R.layout.fragment_urn_container) {
    private lateinit var engraveType2Adapter: EngraveType2_Adapter
    private lateinit var engraveSelectType2Adapter: EngraveType2_Adapter
    private var engraveType2List = ArrayList<String>()
    private var engraveSelectType2List = ArrayList<String>()
    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
        observer()
    }
    private fun initAdapter() {
        engraveType2Adapter = EngraveType2_Adapter()
        engraveSelectType2Adapter = EngraveType2_Adapter()

        binding.recyclerviewEngraveType2.apply {
            adapter = engraveType2Adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        binding.recyclerviewEngraveSelectType2.apply {
            adapter = engraveSelectType2Adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }
    private fun initData() {
        binding.editTextName1.setText(ApplicationClass.prefs.name1)
        binding.editTextDate1.setText(ApplicationClass.prefs.date1)
        binding.editTextDate2.setText(ApplicationClass.prefs.date2)

        engraveType2List = arrayListOf("일반", "기독교", "불교", "천주교", "SGI", "묘법", "순복음", "원불교")
        engraveType2Adapter.updateList(engraveType2List)
        binding.recyclerviewEngraveType2.scrollToPosition(0)

        engraveSelectType2List = arrayListOf("일반", "형제")
        engraveSelectType2Adapter.updateList(engraveSelectType2List)
        binding.recyclerviewEngraveSelectType2.scrollToPosition(0)
    }
    private fun setOnClickListeners() {
    }
    private fun observer() {
        // 고인 정보
        binding.editTextName1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.name1 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        val editTextDate1 = binding.editTextDate1
        editTextDate1.addTextChangedListener(DateTextWatcher(editTextDate1))
        editTextDate1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.date1 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        binding.spinnerDate1Type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.date1Type = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        val editTextDate2 = binding.editTextDate2
        editTextDate2.addTextChangedListener(DateTextWatcher(editTextDate2))
        editTextDate2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.date2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        binding.spinnerDate2Type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.date2Type = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        binding.spinnerUrnType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedUrnType = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
    }
}