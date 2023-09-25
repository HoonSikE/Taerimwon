package com.example.taerimwon.ui.order.urn

import android.text.Editable
import android.text.InputFilter
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
    private lateinit var engraveTypeAdapter: EngraveTypeAdapter
    private lateinit var engraveType2Adapter: EngraveType2Adapter
    private var engraveTypeList = ArrayList<String>()
    private var engraveType2List = ArrayList<String>()
    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
        setOnItemSelectedListener()
        addTextChangedListener()
        observer()
    }
    private val engraveTypeClickListener: (View, String) -> Unit = { _, engraveType ->
        // 배열을 가져옵니다.
        val engraveTypes = engraveType.split(" ")
        ApplicationClass.prefs.engraveType = engraveTypes[1]
        ApplicationClass.prefs.religion = engraveTypes[1]
        println("engraveType: " + ApplicationClass.prefs.engraveType)

        // 문자열 이름을 문자열로 정의합니다.
        val arrName = "engrave_type" + engraveTypes[0]
        // 문자열 배열을 가져옵니다.
        val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
        val engraveTypes2Array = resources.getStringArray(stringArray)

        // 배열을 리스트로 변환합니다.
        engraveType2List = mutableListOf(*engraveTypes2Array) as ArrayList<String>
        engraveType2Adapter.updateList(engraveType2List)

        // engraveType2 초기값
        ApplicationClass.prefs.engraveType2 = "기본"
        engraveType2Adapter.setSelectedItem(0)
        setName2(ApplicationClass.prefs.engraveType.toString(), ApplicationClass.prefs.engraveType2.toString())
    }
    private val engraveType2ClickListener: (View, String) -> Unit = { _, engraveType2 ->
        val engraveTypes2 = engraveType2.split(" ")
        ApplicationClass.prefs.engraveType2 = engraveTypes2[1]
        println("engraveType2: " + ApplicationClass.prefs.engraveType2)
        setName2(ApplicationClass.prefs.engraveType.toString(), ApplicationClass.prefs.engraveType2.toString())
    }
    private fun initAdapter() {
        engraveTypeAdapter = EngraveTypeAdapter(requireContext()).apply {
            onItemClickListener = engraveTypeClickListener
        }
        engraveType2Adapter = EngraveType2Adapter(requireContext()).apply {
            onItemClickListener = engraveType2ClickListener
        }

        binding.recyclerviewEngraveType.apply {
            adapter = engraveTypeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        binding.recyclerviewEngraveSelectType.apply {
            adapter = engraveType2Adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        engraveTypeAdapter.setSelectedItem(ApplicationClass.prefs.engraveTypePosition)
        engraveType2Adapter.setSelectedItem(ApplicationClass.prefs.engraveType2Position)
    }
    private fun initData() {
        binding.editTextName1.setText(ApplicationClass.prefs.name1)
        binding.editTextDate1.setText(ApplicationClass.prefs.date1)
        binding.editTextDate2.setText(ApplicationClass.prefs.date2)
        binding.editTextName2.setText(ApplicationClass.prefs.name2)

        // 배열을 가져옵니다.
        val engraveTypesArray = resources.getStringArray(R.array.engrave_types)
        // 배열을 리스트로 변환합니다.
        engraveTypeList = mutableListOf(*engraveTypesArray) as ArrayList<String>
        engraveTypeAdapter.updateList(engraveTypeList)
        binding.recyclerviewEngraveType.scrollToPosition(ApplicationClass.prefs.engraveTypePosition)

        // 배열을 가져옵니다.
        // 문자열 이름을 문자열로 정의합니다.
        val arrName = "engrave_type" + (ApplicationClass.prefs.engraveTypePosition + 1)
        // 문자열 배열을 가져옵니다.
        val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
        val engraveTypes2Array = resources.getStringArray(stringArray)

        // 배열을 리스트로 변환합니다.
        engraveType2List = mutableListOf(*engraveTypes2Array) as ArrayList<String>
        engraveType2Adapter.updateList(engraveType2List)
        binding.recyclerviewEngraveSelectType.scrollToPosition(ApplicationClass.prefs.engraveType2Position)
    }
    private fun setOnClickListeners() {
    }
    private fun setOnItemSelectedListener() {
        binding.spinnerDate1Type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.date1Type = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
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
    private fun addTextChangedListener(){
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
        // 직분, 법명, 세례명
        binding.editTextName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.name2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
    }
    private fun observer() {

    }
    private fun setName2(engraveType: String, engraveType2: String) {
        // 직분, 세례명, 법명
        if((engraveType == "기독교" || engraveType == "순복음") && (engraveType2 == "기본")) {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "* 직분"
            binding.editTextName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "불교" && engraveType2 == "법명") {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "* 법명"
            binding.editTextName2.hint = "법명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "천주교" && engraveType2 == "기본") {
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
}