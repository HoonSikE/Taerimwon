package com.example.taerimwon.ui.order.urn

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
import com.example.taerimwon.data.dto.urn.UrnItem
import com.example.taerimwon.databinding.FragmentUrnContainerBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.order.OrderViewModel
import com.example.taerimwon.ui.order.urn.bone.BoneEngraveType2Adapter
import com.example.taerimwon.ui.order.urn.bone.BoneEngraveTypeAdapter
import com.example.taerimwon.utils.input.DateTextWatcher
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UrnContainerFragment : BaseFragment<FragmentUrnContainerBinding>(R.layout.fragment_urn_container) {
    private val orderViewModel: OrderViewModel by viewModels()

    // 자동완성 단어들을 담을 리스트
    private lateinit var searchList: MutableList<UrnItem>
    private lateinit var searchList2: MutableList<UrnItem>
//    private lateinit var searchList3: MutableList<UrnItem>

    private lateinit var engraveTypeAdapter: EngraveTypeAdapter
    private lateinit var engraveType2Adapter: EngraveType2Adapter
    private var engraveTypeList = ArrayList<String>()
    private var engraveType2List = ArrayList<String>()

    private lateinit var boneEngraveTypeAdapter: BoneEngraveTypeAdapter
    private lateinit var boneEngraveType2Adapter: BoneEngraveType2Adapter
    private var boneEngraveTypeList = ArrayList<String>()
    private var boneEngraveType2List = ArrayList<String>()
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

    /** 추가, 합골 **/
    private val boneEngraveTypeClickListener: (View, String) -> Unit = { _, boneEngraveType ->
        // 배열을 가져옵니다.
        val boneEngraveTypes = boneEngraveType.split(" ")
        ApplicationClass.prefs.boneEngraveType = boneEngraveTypes[1]
        ApplicationClass.prefs.boneReligion = boneEngraveTypes[1]
        println("boneEngraveType: " + ApplicationClass.prefs.boneEngraveType)

        // 문자열 이름을 문자열로 정의합니다.
        val arrName = "engrave_type" + boneEngraveTypes[0]
        // 문자열 배열을 가져옵니다.
        val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
        val boneEngraveTypes2Array = resources.getStringArray(stringArray)

        // 배열을 리스트로 변환합니다.
        boneEngraveType2List = mutableListOf(*boneEngraveTypes2Array) as ArrayList<String>
        boneEngraveType2Adapter.updateList(boneEngraveType2List)

        // engraveType2 초기값
        ApplicationClass.prefs.boneEngraveType2 = "기본"
        boneEngraveType2Adapter.setSelectedItem(0)
        setBoneName2(ApplicationClass.prefs.boneEngraveType.toString(), ApplicationClass.prefs.boneEngraveType2.toString())
    }
    private val boneEngraveType2ClickListener: (View, String) -> Unit = { _, boneEngraveType2 ->
        val boneEngraveTypes2 = boneEngraveType2.split(" ")
        ApplicationClass.prefs.boneEngraveType2 = boneEngraveTypes2[1]
        println("boneEngraveType2: " + ApplicationClass.prefs.boneEngraveType2)
        setBoneName2(ApplicationClass.prefs.boneEngraveType.toString(), ApplicationClass.prefs.boneEngraveType2.toString())
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

        /** 합골 **/
        boneEngraveTypeAdapter = BoneEngraveTypeAdapter(requireContext()).apply {
            onItemClickListener = boneEngraveTypeClickListener
        }
        boneEngraveType2Adapter = BoneEngraveType2Adapter(requireContext()).apply {
            onItemClickListener = boneEngraveType2ClickListener
        }

        binding.recyclerviewBoneEngraveType.apply {
            adapter = boneEngraveTypeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        binding.recyclerviewBoneEngraveSelectType.apply {
            adapter = boneEngraveType2Adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
        boneEngraveTypeAdapter.setSelectedItem(ApplicationClass.prefs.boneEngraveTypePosition)
        boneEngraveType2Adapter.setSelectedItem(ApplicationClass.prefs.boneEngraveType2Position)
    }
    private fun initData() {
        // 검색 리스트
        searchList = mutableListOf()
        searchList2 = mutableListOf()
//        searchList3 = mutableListOf()
        settingList()

        val selectedUrnType = ApplicationClass.prefs.selectedUrnType.toString()
        // "ArrayList" - 리스트 객체
//        var searchListTmp = searchList
        if(selectedUrnType.contains("유골함")) {
            binding.layoutBoneUrnTitle.visibility = View.VISIBLE
            binding.spinnerBoneUrnType.visibility = View.VISIBLE
            binding.imageBoneUrnType.visibility = View.VISIBLE
            binding.layoutBoneEngrave.visibility = View.VISIBLE

            binding.textBoneUrnTitle.text = "● 유골함[추가] 주문"

            settingList_2()
            binding.autoCompleteTextView2.setAdapter(UrnAutoCompleteAdapter(
                    requireContext(),
                    android.R.layout.simple_dropdown_item_1line,
                    searchList2
                )
            )
            binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedUrnName2)

            val urnTypeTypesArray = resources.getStringArray(R.array.selected_urn_types2)
            val urnTypeList = mutableListOf(*urnTypeTypesArray) as ArrayList<String>
            binding.spinnerBoneUrnType.setSelection(urnTypeList.indexOf(ApplicationClass.prefs.selectedUrnType2))
        }else{
            binding.textBoneUrnTitle.text = "● 유골함[합골] 주문"
            binding.layoutBoneUrnTitle.visibility = View.VISIBLE
            binding.spinnerBoneUrnType.visibility = View.GONE
            binding.imageBoneUrnType.visibility = View.GONE
            binding.layoutBoneEngrave.visibility = View.VISIBLE
            binding.layoutUrnAutoComplete2.visibility = View.GONE

            if(selectedUrnType == "합골함1")
                settingList2()
            else if(selectedUrnType == "합골함2")
                settingList3()
        }

        binding.autoCompleteTextView.setAdapter(UrnAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
            searchList
            )
        )
        binding.autoCompleteTextView.setText(ApplicationClass.prefs.selectedUrnName)


        binding.editTextName1.setText(ApplicationClass.prefs.name1)
        binding.editTextDate1.setText(ApplicationClass.prefs.date1)
        // 배열을 가져옵니다.
        val dateTypeTypesArray = resources.getStringArray(R.array.date_types)
        // 배열을 리스트로 변환합니다.
        val dateTypeList = mutableListOf(*dateTypeTypesArray) as ArrayList<String>
        binding.spinnerDate1Type.setSelection(dateTypeList.indexOf(ApplicationClass.prefs.date1Type))

        binding.editTextDate2.setText(ApplicationClass.prefs.date2)
        binding.spinnerDate2Type.setSelection(dateTypeList.indexOf(ApplicationClass.prefs.date2Type))

        binding.editTextName2.setText(ApplicationClass.prefs.name2)

        val urnTypeTypesArray = resources.getStringArray(R.array.selected_urn_types)
        val urnTypeList = mutableListOf(*urnTypeTypesArray) as ArrayList<String>
        binding.spinnerUrnType.setSelection(urnTypeList.indexOf(ApplicationClass.prefs.selectedUrnType))

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

        // 직분, 세례명, 법명
        val engraveType = ApplicationClass.prefs.engraveType
        val engraveType2 = ApplicationClass.prefs.engraveType2
        val name2 = ApplicationClass.prefs.name2
        if((engraveType == "기독교" || engraveType == "순복음") && (engraveType2 == "기본" || engraveType2 == "年月日")) {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "직분"
            binding.editTextName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
            binding.editTextName2.setText(name2)
        }else if(engraveType == "불교" && engraveType2 == "법명") {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "법명"
            binding.editTextName2.hint = "법명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
            binding.editTextName2.setText(name2)
        }else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2 == "年月日")) {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "세례명"
            binding.editTextName2.hint = "세례명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(6)
            binding.editTextName2.filters = arrayOf(inputFilter)
            binding.editTextName2.setText(name2)
        }else{
            binding.textName2.visibility = View.GONE
            binding.editTextName2.visibility = View.GONE
            binding.imageName2.visibility = View.GONE
            ApplicationClass.prefs.name2 = ""
        }

        /** 합골 **/
        val boneSexArray = resources.getStringArray(R.array.bone_sex)
        val boneSexList = mutableListOf(*boneSexArray) as ArrayList<String>
        binding.spinnerBoneSex.setSelection(boneSexList.indexOf(ApplicationClass.prefs.boneSex))
        binding.editTextBoneName1.setText(ApplicationClass.prefs.boneName1)
        binding.editTextBoneDate1.setText(ApplicationClass.prefs.boneDate1)
        // 배열을 가져옵니다.
        val boneDateTypeTypesArray = resources.getStringArray(R.array.date_types)
        // 배열을 리스트로 변환합니다.
        val boneDateTypeList = mutableListOf(*boneDateTypeTypesArray) as ArrayList<String>
        binding.spinnerBoneDate1Type.setSelection(boneDateTypeList.indexOf(ApplicationClass.prefs.boneDate1Type))
        binding.editTextBoneDate2.setText(ApplicationClass.prefs.boneDate2)
        binding.spinnerBoneDate2Type.setSelection(dateTypeList.indexOf(ApplicationClass.prefs.boneDate2Type))
        binding.editTextBoneName2.setText(ApplicationClass.prefs.boneName2)

        // 배열을 가져옵니다.
        val boneEngraveTypesArray = resources.getStringArray(R.array.engrave_types)
        // 배열을 리스트로 변환합니다.
        boneEngraveTypeList = mutableListOf(*boneEngraveTypesArray) as ArrayList<String>
        boneEngraveTypeAdapter.updateList(boneEngraveTypeList)
        binding.recyclerviewBoneEngraveType.scrollToPosition(ApplicationClass.prefs.boneEngraveTypePosition)

        // 직분, 세례명, 법명
        val boneEngraveType = ApplicationClass.prefs.boneEngraveType
        val boneEngraveType2 = ApplicationClass.prefs.boneEngraveType2
        val boneName2 = ApplicationClass.prefs.boneName2
        if((boneEngraveType == "기독교" || boneEngraveType == "순복음") && (boneEngraveType2 == "기본" || boneEngraveType2 == "年月日")) {
            binding.textBoneName2.visibility = View.VISIBLE
            binding.editTextBoneName2.visibility = View.VISIBLE
            binding.imageBoneName2.visibility = View.VISIBLE
            binding.textBoneName2.text = "직분"
            binding.editTextBoneName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextBoneName2.filters = arrayOf(inputFilter)
            binding.editTextBoneName2.setText(boneName2)
        }else if(boneEngraveType == "불교" && boneEngraveType2 == "법명") {
            binding.textBoneName2.visibility = View.VISIBLE
            binding.editTextBoneName2.visibility = View.VISIBLE
            binding.imageBoneName2.visibility = View.VISIBLE
            binding.textBoneName2.text = "법명"
            binding.editTextBoneName2.hint = "법명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextBoneName2.filters = arrayOf(inputFilter)
            binding.editTextBoneName2.setText(boneName2)
        }else if(boneEngraveType == "천주교" && (boneEngraveType2 == "기본" || boneEngraveType2 == "年月日")) {
            binding.textBoneName2.visibility = View.VISIBLE
            binding.editTextBoneName2.visibility = View.VISIBLE
            binding.imageBoneName2.visibility = View.VISIBLE
            binding.textBoneName2.text = "세례명"
            binding.editTextBoneName2.hint = "세례명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(6)
            binding.editTextBoneName2.filters = arrayOf(inputFilter)
            binding.editTextBoneName2.setText(boneName2)
        }else{
            binding.textBoneName2.visibility = View.GONE
            binding.editTextBoneName2.visibility = View.GONE
            binding.imageBoneName2.visibility = View.GONE
            ApplicationClass.prefs.boneName2 = ""
        }

        // 배열을 가져옵니다.
        // 문자열 이름을 문자열로 정의합니다.
        val arrName2 = "engrave_type" + (ApplicationClass.prefs.boneEngraveTypePosition + 1)
        // 문자열 배열을 가져옵니다.
        val stringArray2 = resources.getIdentifier(arrName2, "array", requireContext().packageName)
        val boneEngraveTypes2Array = resources.getStringArray(stringArray2)
        // 배열을 리스트로 변환합니다.
        boneEngraveType2List = mutableListOf(*boneEngraveTypes2Array) as ArrayList<String>
        boneEngraveType2Adapter.updateList(boneEngraveType2List)
        binding.recyclerviewBoneEngraveSelectType.scrollToPosition(ApplicationClass.prefs.boneEngraveType2Position)
    }
    private fun setOnClickListeners() {
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedUrnItem = adapterView.getItemAtPosition(position) as UrnItem
            binding.autoCompleteTextView.setText(selectedUrnItem.urnItem)
            ApplicationClass.prefs.selectedUrnName = selectedUrnItem.urnItem
        }

        binding.autoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
        binding.autoCompleteTextView2.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedUrnItem = adapterView.getItemAtPosition(position) as UrnItem
            binding.autoCompleteTextView2.setText(selectedUrnItem.urnItem)
            ApplicationClass.prefs.selectedUrnName2 = selectedUrnItem.urnItem
        }

        binding.autoCompleteTextView2.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
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

                if(ApplicationClass.prefs.selectedUrnType!!.contains("선택안함"))
                    binding.layoutUrn.visibility = View.GONE
                else
                    binding.layoutUrn.visibility = View.VISIBLE


                when (ApplicationClass.prefs.selectedUrnType) {
                    "유골함" -> {
                        binding.layoutBoneUrnTitle.visibility = View.VISIBLE
                        binding.spinnerBoneUrnType.visibility = View.VISIBLE
                        binding.imageBoneUrnType.visibility = View.VISIBLE
                        binding.layoutBoneEngrave.visibility = View.VISIBLE

                        binding.textBoneUrnTitle.text = "● 유골함[추가] 주문"

                        settingList_2()
                        binding.autoCompleteTextView2.setAdapter(UrnAutoCompleteAdapter(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            searchList2
                        )
                        )
                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedUrnName2)

                        val urnTypeTypesArray = resources.getStringArray(R.array.selected_urn_types2)
                        val urnTypeList = mutableListOf(*urnTypeTypesArray) as ArrayList<String>
                        binding.spinnerBoneUrnType.setSelection(urnTypeList.indexOf(ApplicationClass.prefs.selectedUrnType2))

                        settingList()

                        binding.autoCompleteTextView.setAdapter(
                            UrnAutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchList
                            )
                        )
                    }
                    "합골함1" -> {
                        binding.textBoneUrnTitle.text = "● 유골함[합골] 주문"
                        binding.layoutBoneUrnTitle.visibility = View.VISIBLE
                        binding.spinnerBoneUrnType.visibility = View.GONE
                        binding.imageBoneUrnType.visibility = View.GONE
                        binding.layoutBoneEngrave.visibility = View.VISIBLE
                        binding.layoutUrnAutoComplete2.visibility = View.GONE

                        ApplicationClass.prefs.selectedUrnType2 = "선택안함"

                        settingList2()

                        binding.autoCompleteTextView.setAdapter(
                            UrnAutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchList
                            )
                        )
                    }
                    "합골함2" -> {
                        binding.textBoneUrnTitle.text = "● 유골함[합골] 주문"
                        binding.layoutBoneUrnTitle.visibility = View.VISIBLE
                        binding.spinnerBoneUrnType.visibility = View.GONE
                        binding.imageBoneUrnType.visibility = View.GONE
                        binding.layoutBoneEngrave.visibility = View.VISIBLE
                        binding.layoutUrnAutoComplete2.visibility = View.GONE

                        ApplicationClass.prefs.selectedUrnType2 = "선택안함"

                        settingList3()

                        binding.autoCompleteTextView.setAdapter(
                            UrnAutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchList
                            )
                        )
                    }
                }

                if(ApplicationClass.prefs.selectedUrnType!!.contains("선택안함")) {
                    binding.layoutBoneUrnTitle.visibility = View.GONE
                    binding.layoutBoneEngrave.visibility = View.GONE
                }else{
                    binding.layoutBoneUrnTitle.visibility = View.VISIBLE
                    if(ApplicationClass.prefs.boneSex == "남성"){
                        binding.layoutBoneEngrave.setBackgroundResource(R.drawable.view_radius_man)
                    } else if(ApplicationClass.prefs.boneSex == "여성"){
                        binding.layoutBoneEngrave.setBackgroundResource(R.drawable.view_radius_woman)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        /** 추가 **/
        binding.spinnerBoneUrnType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedUrnType2 = parent?.getItemAtPosition(position).toString() ?: ""

                if(ApplicationClass.prefs.selectedUrnType2!!.contains("선택안함"))
                    binding.layoutBoneEngrave.visibility = View.GONE
                else
                    binding.layoutBoneEngrave.visibility = View.VISIBLE


                when (ApplicationClass.prefs.selectedUrnType2) {
                    "유골함" -> {
                        binding.layoutUrnAutoComplete2.visibility = View.VISIBLE
                        settingList_2()

                        binding.autoCompleteTextView2.setAdapter(
                            UrnAutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchList2
                            )
                        )
                    }
                }

                if(ApplicationClass.prefs.selectedUrnType2!!.contains("선택안함")) {
                    binding.layoutBoneEngrave.visibility = View.GONE
                }else{
                    binding.layoutBoneEngrave.visibility = View.VISIBLE
                    if(ApplicationClass.prefs.boneSex == "남성"){
                        binding.layoutBoneEngrave.setBackgroundResource(R.drawable.view_radius_man)
                    } else if(ApplicationClass.prefs.boneSex == "여성"){
                        binding.layoutBoneEngrave.setBackgroundResource(R.drawable.view_radius_woman)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        /** 합골 **/
        binding.spinnerBoneDate1Type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.boneDate1Type = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        binding.spinnerBoneDate2Type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.boneDate2Type = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        binding.spinnerBoneSex.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.boneSex = parent?.getItemAtPosition(position).toString() ?: ""
                if(ApplicationClass.prefs.boneSex == "남성"){
                    ApplicationClass.prefs.sex = "여성"
                    val drawableResource = R.drawable.view_radius_man
                    binding.layoutBoneEngrave.setBackgroundResource(drawableResource)
                } else if(ApplicationClass.prefs.boneSex == "여성"){
                    ApplicationClass.prefs.sex = "남성"
                    val drawableResource = R.drawable.view_radius_woman
                    binding.layoutBoneEngrave.setBackgroundResource(drawableResource)
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
                ApplicationClass.prefs.selectedUrnName = s?.toString() ?: ""

                val selectedUrnName = ApplicationClass.prefs.selectedUrnName.toString()

                if(selectedUrnName.contains("수목함")
                    || selectedUrnName.contains("밀봉외함")
                    || selectedUrnName.contains("표석")
                    || selectedUrnName.contains("피아노")){
                    binding.layoutEngrave.visibility = View.GONE
                    binding.textUrnGuide.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
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
        editTextDate1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.date1 = s?.toString() ?: ""
                println("dd" + s?.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        editTextDate1.addTextChangedListener(DateTextWatcher(editTextDate1))

        val editTextDate2 = binding.editTextDate2
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
        editTextDate2.addTextChangedListener(DateTextWatcher(editTextDate2))

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
        /** 추가 */
        binding.autoCompleteTextView2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedUrnName2 = s?.toString() ?: ""

                val selectedUrnName2 = ApplicationClass.prefs.selectedUrnName2.toString()

                if(selectedUrnName2.contains("수목함")
                    || selectedUrnName2.contains("밀봉외함")
                    || selectedUrnName2.contains("표석")
                    || selectedUrnName2.contains("피아노")){
                    binding.textUrnGuide.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        /** 합골 **/
        // 고인 정보
        binding.editTextBoneName1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.boneName1 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        val editTextBoneDate1 = binding.editTextBoneDate1
        editTextBoneDate1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.boneDate1 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        editTextBoneDate1.addTextChangedListener(DateTextWatcher(editTextBoneDate1))

        val editTextBoneDate2 = binding.editTextBoneDate2
        editTextBoneDate2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.boneDate2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        editTextBoneDate2.addTextChangedListener(DateTextWatcher(editTextBoneDate2))

        // 직분, 법명, 세례명
        binding.editTextBoneName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.boneName2 = s?.toString() ?: ""
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
        if((engraveType == "기독교" || engraveType == "순복음") && (engraveType2 == "기본" || engraveType2 == "年月日")) {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "직분"
            binding.editTextName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "불교" && engraveType2 == "법명") {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "법명"
            binding.editTextName2.hint = "법명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2 == "年月日")) {
            binding.textName2.visibility = View.VISIBLE
            binding.editTextName2.visibility = View.VISIBLE
            binding.imageName2.visibility = View.VISIBLE
            binding.textName2.text = "세례명"
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
    /** 합골 **/
    private fun setBoneName2(engraveType: String, engraveType2: String) {
        // 직분, 세례명, 법명
        if((engraveType == "기독교" || engraveType == "순복음") && (engraveType2 == "기본" || engraveType2 == "年月日")) {
            binding.textBoneName2.visibility = View.VISIBLE
            binding.editTextBoneName2.visibility = View.VISIBLE
            binding.imageBoneName2.visibility = View.VISIBLE
            binding.textBoneName2.text = "직분"
            binding.editTextBoneName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextBoneName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "불교" && engraveType2 == "법명") {
            binding.textBoneName2.visibility = View.VISIBLE
            binding.editTextBoneName2.visibility = View.VISIBLE
            binding.imageBoneName2.visibility = View.VISIBLE
            binding.textBoneName2.text = "법명"
            binding.editTextBoneName2.hint = "법명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextBoneName2.filters = arrayOf(inputFilter)
        }else if(engraveType == "천주교" && (engraveType2 == "기본" || engraveType2 == "年月日")) {
            binding.textBoneName2.visibility = View.VISIBLE
            binding.editTextBoneName2.visibility = View.VISIBLE
            binding.imageBoneName2.visibility = View.VISIBLE
            binding.textBoneName2.text = "세례명"
            binding.editTextBoneName2.hint = "세례명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(6)
            binding.editTextBoneName2.filters = arrayOf(inputFilter)
        }else{
            binding.textBoneName2.visibility = View.GONE
            binding.editTextBoneName2.visibility = View.GONE
            binding.imageBoneName2.visibility = View.GONE
            ApplicationClass.prefs.boneName2 = ""
        }
    }

    // 장동완성 단어 세팅
    private fun settingList() {
        searchList = mutableListOf()

        searchList.add(UrnItem("미정", 0))
//        searchList.add(UrnItem("기본", R.drawable.img_urn))
//        searchList.add(UrnItem("기본(검정)", R.drawable.img_urn0))

        // 1. 일반 밀봉진공함
        searchList.add(UrnItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList.add(UrnItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList.add(UrnItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList.add(UrnItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList.add(UrnItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList.add(UrnItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList.add(UrnItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList.add(UrnItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList.add(UrnItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList.add(UrnItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList.add(UrnItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList.add(UrnItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList.add(UrnItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList.add(UrnItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList.add(UrnItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList.add(UrnItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList.add(UrnItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList.add(UrnItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList.add(UrnItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList.add(UrnItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList.add(UrnItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList.add(UrnItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList.add(UrnItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList.add(UrnItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList.add(UrnItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList.add(UrnItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList.add(UrnItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList.add(UrnItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList.add(UrnItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList.add(UrnItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList.add(UrnItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList.add(UrnItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList.add(UrnItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList.add(UrnItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList.add(UrnItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList.add(UrnItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList.add(UrnItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList.add(UrnItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList.add(UrnItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList.add(UrnItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList.add(UrnItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList.add(UrnItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList.add(UrnItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList.add(UrnItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList.add(UrnItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList.add(UrnItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList.add(UrnItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList.add(UrnItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList.add(UrnItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList.add(UrnItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList.add(UrnItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList.add(UrnItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList.add(UrnItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList.add(UrnItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList.add(UrnItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList.add(UrnItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList.add(UrnItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList.add(UrnItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList.add(UrnItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList.add(UrnItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList.add(UrnItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList.add(UrnItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList.add(UrnItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList.add(UrnItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList.add(UrnItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList.add(UrnItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList.add(UrnItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList.add(UrnItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList.add(UrnItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList.add(UrnItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList.add(UrnItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList.add(UrnItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList.add(UrnItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList.add(UrnItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList.add(UrnItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList.add(UrnItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList.add(UrnItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList.add(UrnItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList.add(UrnItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList.add(UrnItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList.add(UrnItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList.add(UrnItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList.add(UrnItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList.add(UrnItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList.add(UrnItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList.add(UrnItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList.add(UrnItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList.add(UrnItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList.add(UrnItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList.add(UrnItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList.add(UrnItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList.add(UrnItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList.add(UrnItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList.add(UrnItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList.add(UrnItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList.add(UrnItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList.add(UrnItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList.add(UrnItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList.add(UrnItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList.add(UrnItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList.add(UrnItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList.add(UrnItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList.add(UrnItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList.add(UrnItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList.add(UrnItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList.add(UrnItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList.add(UrnItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList.add(UrnItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList.add(UrnItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList.add(UrnItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList.add(UrnItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList.add(UrnItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList.add(UrnItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))
    }
    private fun settingList2() {
        searchList = mutableListOf()

        searchList.add(UrnItem("미정", 0))
//        searchList2.add(UrnItem("기본", R.drawable.img_bone1))
//        searchList2.add(UrnItem("기본(검정)", R.drawable.img_bone1_0))
        searchList.add(UrnItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
    }
    private fun settingList3() {
        searchList = mutableListOf()

        searchList.add(UrnItem("미정", 0))
//        searchList3.add(UrnItem("기본", R.drawable.img_bone2))
//        searchList3.add(UrnItem("기본(검정)", R.drawable.img_bone2_0))
        searchList.add(UrnItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        searchList.add(UrnItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))
    }
    // 장동완성 단어 세팅
    private fun settingList_2() {
        searchList2 = mutableListOf()

        searchList2.add(UrnItem("미정", 0))
//        searchList2.add(UrnItem("기본", R.drawable.img_urn))
//        searchList2.add(UrnItem("기본(검정)", R.drawable.img_urn0))

        // 1. 일반 밀봉진공함
        searchList2.add(UrnItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList2.add(UrnItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList2.add(UrnItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList2.add(UrnItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList2.add(UrnItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList2.add(UrnItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList2.add(UrnItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList2.add(UrnItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList2.add(UrnItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList2.add(UrnItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList2.add(UrnItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList2.add(UrnItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList2.add(UrnItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList2.add(UrnItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList2.add(UrnItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList2.add(UrnItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList2.add(UrnItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList2.add(UrnItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList2.add(UrnItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList2.add(UrnItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList2.add(UrnItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList2.add(UrnItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList2.add(UrnItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList2.add(UrnItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList2.add(UrnItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList2.add(UrnItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList2.add(UrnItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList2.add(UrnItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList2.add(UrnItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList2.add(UrnItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList2.add(UrnItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList2.add(UrnItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList2.add(UrnItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList2.add(UrnItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList2.add(UrnItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList2.add(UrnItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList2.add(UrnItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList2.add(UrnItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList2.add(UrnItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList2.add(UrnItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList2.add(UrnItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList2.add(UrnItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList2.add(UrnItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList2.add(UrnItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList2.add(UrnItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList2.add(UrnItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList2.add(UrnItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList2.add(UrnItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList2.add(UrnItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList2.add(UrnItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList2.add(UrnItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList2.add(UrnItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList2.add(UrnItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList2.add(UrnItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList2.add(UrnItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList2.add(UrnItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList2.add(UrnItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList2.add(UrnItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList2.add(UrnItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList2.add(UrnItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList2.add(UrnItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList2.add(UrnItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList2.add(UrnItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList2.add(UrnItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList2.add(UrnItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList2.add(UrnItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList2.add(UrnItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList2.add(UrnItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList2.add(UrnItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList2.add(UrnItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList2.add(UrnItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList2.add(UrnItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList2.add(UrnItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList2.add(UrnItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList2.add(UrnItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList2.add(UrnItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList2.add(UrnItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList2.add(UrnItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList2.add(UrnItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList2.add(UrnItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList2.add(UrnItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList2.add(UrnItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList2.add(UrnItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList2.add(UrnItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList2.add(UrnItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList2.add(UrnItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList2.add(UrnItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList2.add(UrnItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList2.add(UrnItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList2.add(UrnItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList2.add(UrnItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList2.add(UrnItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList2.add(UrnItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList2.add(UrnItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList2.add(UrnItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList2.add(UrnItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList2.add(UrnItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList2.add(UrnItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList2.add(UrnItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList2.add(UrnItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList2.add(UrnItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList2.add(UrnItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList2.add(UrnItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList2.add(UrnItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList2.add(UrnItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList2.add(UrnItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList2.add(UrnItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList2.add(UrnItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList2.add(UrnItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList2.add(UrnItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList2.add(UrnItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList2.add(UrnItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList2.add(UrnItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))
    }
}