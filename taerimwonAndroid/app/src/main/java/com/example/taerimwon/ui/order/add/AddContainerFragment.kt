package com.example.taerimwon.ui.order.add

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.data.dto.add.AddItem
import com.example.taerimwon.data.dto.tablet.TabletItem
import com.example.taerimwon.databinding.FragmentAddContainerBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.order.OrderViewModel
import com.example.taerimwon.ui.order.pyeongjang.PyeongjangAutoCompleteAdapter


@AndroidEntryPoint
class AddContainerFragment : BaseFragment<FragmentAddContainerBinding>(R.layout.fragment_add_container) {
    // 자동완성 단어들을 담을 리스트
    private lateinit var searchList1: MutableList<AddItem>
    private lateinit var searchList2: MutableList<AddItem>
    private lateinit var searchList3: MutableList<AddItem>
    private lateinit var searchList4: MutableList<AddItem>
    private lateinit var searchList5: MutableList<AddItem>
    private var index = 1

    override fun init() {
        initData()
        setOnClickListeners()
        addTextChangedListener()
        observer()
    }
    private fun initData() {
        val selectedAddName1 = ApplicationClass.prefs.selectedAddName1
        val selectedAddName2 = ApplicationClass.prefs.selectedAddName2
        val selectedAddName3 = ApplicationClass.prefs.selectedAddName3
        val selectedAddName4 = ApplicationClass.prefs.selectedAddName4
        val selectedAddName5 = ApplicationClass.prefs.selectedAddName5

        settingList1_1()
        binding.autoCompleteTextView1.setAdapter(
            AddAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchList1
            )
        )
        binding.autoCompleteTextView1.setText(selectedAddName1)

        settingList1_2()
        binding.autoCompleteTextView2.setAdapter(
            AddAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchList2
            )
        )

        settingList1_3()
        binding.autoCompleteTextView3.setAdapter(
            AddAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchList3
            )
        )

        settingList1_4()
        binding.autoCompleteTextView4.setAdapter(
            AddAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchList4
            )
        )

        settingList1_5()
        binding.autoCompleteTextView5.setAdapter(
            AddAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchList5
            )
        )

        if(selectedAddName1 != ""){
            index++

            binding.textView1.text = "삭제"
            binding.layoutAddAutoComplete2.visibility = View.VISIBLE

            binding.autoCompleteTextView2.setText(selectedAddName2)
        }
        if(selectedAddName2 != ""){
            index++

            binding.textView2.text = "삭제"
            binding.layoutAddAutoComplete3.visibility = View.VISIBLE

            binding.autoCompleteTextView3.setText(selectedAddName3)
        }
        if(selectedAddName3 != ""){
            index++

            binding.textView3.text = "삭제"
            binding.layoutAddAutoComplete4.visibility = View.VISIBLE

            binding.autoCompleteTextView4.setText(selectedAddName4)
        }
        if(selectedAddName4 != ""){
            index++

            binding.textView4.text = "삭제"
            binding.layoutAddAutoComplete5.visibility = View.VISIBLE

            binding.autoCompleteTextView5.setText(selectedAddName5)
        }
    }
    private fun setOnClickListeners() {
        binding.autoCompleteTextView1.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag1 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedAddItem = adapterView.getItemAtPosition(position) as AddItem
            binding.autoCompleteTextView1.setText(selectedAddItem.addItem)
            ApplicationClass.prefs.selectedAddName1 = selectedAddItem.addItem
        }
        binding.autoCompleteTextView1.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }

        binding.autoCompleteTextView2.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag2 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedAddItem = adapterView.getItemAtPosition(position) as AddItem
            binding.autoCompleteTextView2.setText(selectedAddItem.addItem)
            ApplicationClass.prefs.selectedAddName2 = selectedAddItem.addItem
        }
        binding.autoCompleteTextView2.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }

        binding.autoCompleteTextView3.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag3 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedAddItem = adapterView.getItemAtPosition(position) as AddItem
            binding.autoCompleteTextView3.setText(selectedAddItem.addItem)
            ApplicationClass.prefs.selectedAddName3 = selectedAddItem.addItem
        }
        binding.autoCompleteTextView3.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }

        binding.autoCompleteTextView4.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag4 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedAddItem = adapterView.getItemAtPosition(position) as AddItem
            binding.autoCompleteTextView4.setText(selectedAddItem.addItem)
            ApplicationClass.prefs.selectedAddName4 = selectedAddItem.addItem
        }
        binding.autoCompleteTextView4.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }

        binding.autoCompleteTextView5.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag5 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedAddItem = adapterView.getItemAtPosition(position) as AddItem
            binding.autoCompleteTextView5.setText(selectedAddItem.addItem)
            ApplicationClass.prefs.selectedAddName5 = selectedAddItem.addItem
        }
        binding.autoCompleteTextView5.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }

        binding.textView1.setOnClickListener{
//            binding.textView1.visibility = View.GONE
            if(binding.textView1.text == "추가"){
                binding.textView1.text = "삭제"
                binding.layoutAddAutoComplete2.visibility = View.VISIBLE
                index++
            }else if(binding.textView1.text == "삭제"){
                deleteOrder(1)
                index--
            }
        }
        binding.textView2.setOnClickListener{
            if(binding.textView2.text == "추가") {
                binding.textView2.text = "삭제"
                binding.layoutAddAutoComplete3.visibility = View.VISIBLE
                index++
            }else if(binding.textView2.text == "삭제"){
                deleteOrder(2)
                index--
            }
        }
        binding.textView3.setOnClickListener{
            if(binding.textView3.text == "추가") {
                binding.textView3.text = "삭제"
                binding.layoutAddAutoComplete4.visibility = View.VISIBLE
                index++
            }else if(binding.textView3.text == "삭제"){
                deleteOrder(3)
                index--
            }
        }
        binding.textView4.setOnClickListener{
            if(binding.textView4.text == "추가") {
                binding.textView4.text = "삭제"
                binding.layoutAddAutoComplete5.visibility = View.VISIBLE
                index++
            }else if(binding.textView4.text == "삭제"){
                deleteOrder(4)
                index--
            }
        }
    }
    // 값을 앞으로 땡기고, 마지막 항목 처리
    private fun deleteOrder(n : Int) {
        when (index) {
            1 -> {
            }
            2 -> {
                when(n){
                    1 -> {
                        binding.autoCompleteTextView1.setText(ApplicationClass.prefs.selectedAddName2)
                        ApplicationClass.prefs.selectedAddName1 = ApplicationClass.prefs.selectedAddName2
                    }
                }
                binding.autoCompleteTextView2.setText("")
                ApplicationClass.prefs.selectedAddName2 = ""
                binding.layoutAddAutoComplete2.visibility = View.GONE
                binding.textView1.text = "추가"
            }
            3 -> {
                when(n){
                    1 -> {
                        binding.autoCompleteTextView1.setText(ApplicationClass.prefs.selectedAddName2)
                        ApplicationClass.prefs.selectedAddName1 = ApplicationClass.prefs.selectedAddName2

                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedAddName3)
                        ApplicationClass.prefs.selectedAddName2 = ApplicationClass.prefs.selectedAddName3
                    }
                    2 -> {
                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedAddName3)
                        ApplicationClass.prefs.selectedAddName2 = ApplicationClass.prefs.selectedAddName3
                    }
                }
                binding.autoCompleteTextView3.setText("")
                ApplicationClass.prefs.selectedAddName3 = ""
                binding.layoutAddAutoComplete3.visibility = View.GONE
                binding.textView2.text = "추가"
            }
            4 -> {
                when(n){
                    1 -> {
                        binding.autoCompleteTextView1.setText(ApplicationClass.prefs.selectedAddName2)
                        ApplicationClass.prefs.selectedAddName1 = ApplicationClass.prefs.selectedAddName2

                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedAddName3)
                        ApplicationClass.prefs.selectedAddName2 = ApplicationClass.prefs.selectedAddName3

                        binding.autoCompleteTextView3.setText(ApplicationClass.prefs.selectedAddName4)
                        ApplicationClass.prefs.selectedAddName3 = ApplicationClass.prefs.selectedAddName4
                    }
                    2 -> {
                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedAddName3)
                        ApplicationClass.prefs.selectedAddName2 = ApplicationClass.prefs.selectedAddName3

                        binding.autoCompleteTextView3.setText(ApplicationClass.prefs.selectedAddName4)
                        ApplicationClass.prefs.selectedAddName3 = ApplicationClass.prefs.selectedAddName4
                    }
                    3 -> {
                        binding.autoCompleteTextView3.setText(ApplicationClass.prefs.selectedAddName4)
                        ApplicationClass.prefs.selectedAddName3 = ApplicationClass.prefs.selectedAddName4
                    }
                }
                binding.autoCompleteTextView4.setText("")
                ApplicationClass.prefs.selectedAddName4 = ""
                binding.layoutAddAutoComplete4.visibility = View.GONE
                binding.textView3.text = "추가"
            }
            5 -> {
                when(n){
                    1 -> {
                        binding.autoCompleteTextView1.setText(ApplicationClass.prefs.selectedAddName2)
                        ApplicationClass.prefs.selectedAddName1 = ApplicationClass.prefs.selectedAddName2

                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedAddName3)
                        ApplicationClass.prefs.selectedAddName2 = ApplicationClass.prefs.selectedAddName3

                        binding.autoCompleteTextView3.setText(ApplicationClass.prefs.selectedAddName4)
                        ApplicationClass.prefs.selectedAddName3 = ApplicationClass.prefs.selectedAddName4

                        binding.autoCompleteTextView4.setText(ApplicationClass.prefs.selectedAddName5)
                        ApplicationClass.prefs.selectedAddName4 = ApplicationClass.prefs.selectedAddName5
                    }
                    2 -> {
                        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedAddName3)
                        ApplicationClass.prefs.selectedAddName2 = ApplicationClass.prefs.selectedAddName3

                        binding.autoCompleteTextView3.setText(ApplicationClass.prefs.selectedAddName4)
                        ApplicationClass.prefs.selectedAddName3 = ApplicationClass.prefs.selectedAddName4

                        binding.autoCompleteTextView4.setText(ApplicationClass.prefs.selectedAddName5)
                        ApplicationClass.prefs.selectedAddName4 = ApplicationClass.prefs.selectedAddName5
                    }
                    3 -> {
                        binding.autoCompleteTextView3.setText(ApplicationClass.prefs.selectedAddName4)
                        ApplicationClass.prefs.selectedAddName3 = ApplicationClass.prefs.selectedAddName4

                        binding.autoCompleteTextView4.setText(ApplicationClass.prefs.selectedAddName5)
                        ApplicationClass.prefs.selectedAddName4 = ApplicationClass.prefs.selectedAddName5
                    }
                    4 -> {
                        binding.autoCompleteTextView4.setText(ApplicationClass.prefs.selectedAddName5)
                        ApplicationClass.prefs.selectedAddName4 = ApplicationClass.prefs.selectedAddName5
                    }
                }
                binding.autoCompleteTextView5.setText("")
                ApplicationClass.prefs.selectedAddName5 = ""
                binding.layoutAddAutoComplete5.visibility = View.GONE
                binding.textView4.text = "추가"
            }
        }
    }
    private fun addTextChangedListener(){
        binding.autoCompleteTextView1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedAddName1 = s?.toString() ?: ""
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
                ApplicationClass.prefs.selectedAddName2 = s?.toString() ?: ""
            }
            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })

        binding.autoCompleteTextView3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedAddName3 = s?.toString() ?: ""
            }
            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })

        binding.autoCompleteTextView4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedAddName4 = s?.toString() ?: ""
            }
            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })

        binding.autoCompleteTextView5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.selectedAddName5 = s?.toString() ?: ""
            }
            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
    }
    private fun observer() {

    }
    private fun settingList1_1() {
        searchList1 = mutableListOf()

        searchList1.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList1.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList1.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList1.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
        searchList1.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))

        /** 유골함 **/
        // 1. 일반 밀봉진공함
        searchList1.add(AddItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList1.add(AddItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList1.add(AddItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList1.add(AddItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList1.add(AddItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList1.add(AddItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList1.add(AddItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList1.add(AddItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList1.add(AddItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList1.add(AddItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList1.add(AddItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList1.add(AddItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList1.add(AddItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList1.add(AddItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList1.add(AddItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList1.add(AddItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList1.add(AddItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList1.add(AddItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList1.add(AddItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList1.add(AddItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList1.add(AddItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList1.add(AddItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList1.add(AddItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList1.add(AddItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList1.add(AddItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList1.add(AddItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList1.add(AddItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList1.add(AddItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList1.add(AddItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList1.add(AddItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList1.add(AddItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList1.add(AddItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList1.add(AddItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList1.add(AddItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList1.add(AddItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList1.add(AddItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList1.add(AddItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList1.add(AddItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList1.add(AddItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList1.add(AddItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList1.add(AddItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList1.add(AddItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList1.add(AddItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList1.add(AddItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList1.add(AddItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList1.add(AddItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList1.add(AddItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList1.add(AddItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList1.add(AddItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList1.add(AddItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList1.add(AddItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList1.add(AddItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList1.add(AddItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList1.add(AddItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList1.add(AddItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList1.add(AddItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList1.add(AddItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList1.add(AddItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList1.add(AddItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList1.add(AddItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList1.add(AddItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList1.add(AddItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList1.add(AddItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList1.add(AddItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList1.add(AddItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList1.add(AddItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList1.add(AddItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList1.add(AddItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList1.add(AddItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList1.add(AddItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList1.add(AddItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList1.add(AddItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList1.add(AddItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList1.add(AddItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList1.add(AddItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList1.add(AddItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList1.add(AddItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList1.add(AddItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList1.add(AddItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList1.add(AddItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList1.add(AddItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList1.add(AddItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList1.add(AddItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList1.add(AddItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList1.add(AddItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList1.add(AddItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList1.add(AddItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList1.add(AddItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList1.add(AddItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList1.add(AddItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList1.add(AddItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList1.add(AddItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList1.add(AddItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList1.add(AddItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList1.add(AddItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList1.add(AddItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList1.add(AddItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList1.add(AddItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList1.add(AddItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList1.add(AddItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList1.add(AddItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList1.add(AddItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList1.add(AddItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList1.add(AddItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList1.add(AddItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList1.add(AddItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList1.add(AddItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList1.add(AddItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList1.add(AddItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList1.add(AddItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList1.add(AddItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList1.add(AddItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList1.add(AddItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))

        /** 합골 **/
        searchList1.add(AddItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
        searchList1.add(AddItem("미정(타입2)", 0))
        searchList1.add(AddItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        searchList1.add(AddItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))

        /** 위패 **/
        searchList1.add(AddItem("흰색위패", R.drawable.img_tablet))
        searchList1.add(AddItem("검정위패-TR-2-0802", R.drawable.img_tablet2))

        searchList1.add(AddItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchList1.add(AddItem("추모패-TR-4-1307", R.drawable.img_tablet4))
    }
    private fun settingList1_2() {
        searchList2 = mutableListOf()

        searchList2.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList2.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList2.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList2.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
        searchList2.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))

        /** 유골함 **/
        // 1. 일반 밀봉진공함
        searchList2.add(AddItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList2.add(AddItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList2.add(AddItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList2.add(AddItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList2.add(AddItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList2.add(AddItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList2.add(AddItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList2.add(AddItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList2.add(AddItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList2.add(AddItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList2.add(AddItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList2.add(AddItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList2.add(AddItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList2.add(AddItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList2.add(AddItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList2.add(AddItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList2.add(AddItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList2.add(AddItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList2.add(AddItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList2.add(AddItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList2.add(AddItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList2.add(AddItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList2.add(AddItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList2.add(AddItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList2.add(AddItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList2.add(AddItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList2.add(AddItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList2.add(AddItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList2.add(AddItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList2.add(AddItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList2.add(AddItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList2.add(AddItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList2.add(AddItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList2.add(AddItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList2.add(AddItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList2.add(AddItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList2.add(AddItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList2.add(AddItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList2.add(AddItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList2.add(AddItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList2.add(AddItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList2.add(AddItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList2.add(AddItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList2.add(AddItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList2.add(AddItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList2.add(AddItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList2.add(AddItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList2.add(AddItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList2.add(AddItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList2.add(AddItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList2.add(AddItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList2.add(AddItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList2.add(AddItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList2.add(AddItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList2.add(AddItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList2.add(AddItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList2.add(AddItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList2.add(AddItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList2.add(AddItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList2.add(AddItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList2.add(AddItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList2.add(AddItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList2.add(AddItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList2.add(AddItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList2.add(AddItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList2.add(AddItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList2.add(AddItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList2.add(AddItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList2.add(AddItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList2.add(AddItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList2.add(AddItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList2.add(AddItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList2.add(AddItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList2.add(AddItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList2.add(AddItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList2.add(AddItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList2.add(AddItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList2.add(AddItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList2.add(AddItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList2.add(AddItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList2.add(AddItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList2.add(AddItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList2.add(AddItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList2.add(AddItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList2.add(AddItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList2.add(AddItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList2.add(AddItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList2.add(AddItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList2.add(AddItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList2.add(AddItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList2.add(AddItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList2.add(AddItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList2.add(AddItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList2.add(AddItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList2.add(AddItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList2.add(AddItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList2.add(AddItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList2.add(AddItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList2.add(AddItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList2.add(AddItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList2.add(AddItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList2.add(AddItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList2.add(AddItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList2.add(AddItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList2.add(AddItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList2.add(AddItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList2.add(AddItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList2.add(AddItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList2.add(AddItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList2.add(AddItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList2.add(AddItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList2.add(AddItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList2.add(AddItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))

        /** 합골 **/
        searchList2.add(AddItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
        searchList2.add(AddItem("미정(타입2)", 0))
        searchList2.add(AddItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        searchList2.add(AddItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))

        /** 위패 **/
        searchList2.add(AddItem("흰색위패", R.drawable.img_tablet))
        searchList2.add(AddItem("검정위패-TR-2-0802", R.drawable.img_tablet2))

        searchList2.add(AddItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchList2.add(AddItem("추모패-TR-4-1307", R.drawable.img_tablet4))
    }
    private fun settingList1_3() {
        searchList3 = mutableListOf()

        searchList3.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList3.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList3.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList3.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
        searchList3.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))

        /** 유골함 **/
        // 1. 일반 밀봉진공함
        searchList3.add(AddItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList3.add(AddItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList3.add(AddItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList3.add(AddItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList3.add(AddItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList3.add(AddItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList3.add(AddItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList3.add(AddItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList3.add(AddItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList3.add(AddItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList3.add(AddItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList3.add(AddItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList3.add(AddItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList3.add(AddItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList3.add(AddItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList3.add(AddItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList3.add(AddItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList3.add(AddItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList3.add(AddItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList3.add(AddItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList3.add(AddItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList3.add(AddItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList3.add(AddItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList3.add(AddItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList3.add(AddItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList3.add(AddItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList3.add(AddItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList3.add(AddItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList3.add(AddItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList3.add(AddItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList3.add(AddItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList3.add(AddItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList3.add(AddItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList3.add(AddItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList3.add(AddItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList3.add(AddItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList3.add(AddItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList3.add(AddItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList3.add(AddItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList3.add(AddItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList3.add(AddItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList3.add(AddItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList3.add(AddItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList3.add(AddItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList3.add(AddItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList3.add(AddItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList3.add(AddItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList3.add(AddItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList3.add(AddItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList3.add(AddItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList3.add(AddItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList3.add(AddItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList3.add(AddItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList3.add(AddItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList3.add(AddItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList3.add(AddItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList3.add(AddItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList3.add(AddItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList3.add(AddItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList3.add(AddItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList3.add(AddItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList3.add(AddItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList3.add(AddItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList3.add(AddItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList3.add(AddItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList3.add(AddItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList3.add(AddItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList3.add(AddItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList3.add(AddItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList3.add(AddItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList3.add(AddItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList3.add(AddItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList3.add(AddItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList3.add(AddItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList3.add(AddItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList3.add(AddItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList3.add(AddItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList3.add(AddItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList3.add(AddItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList3.add(AddItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList3.add(AddItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList3.add(AddItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList3.add(AddItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList3.add(AddItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList3.add(AddItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList3.add(AddItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList3.add(AddItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList3.add(AddItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList3.add(AddItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList3.add(AddItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList3.add(AddItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList3.add(AddItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList3.add(AddItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList3.add(AddItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList3.add(AddItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList3.add(AddItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList3.add(AddItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList3.add(AddItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList3.add(AddItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList3.add(AddItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList3.add(AddItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList3.add(AddItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList3.add(AddItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList3.add(AddItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList3.add(AddItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList3.add(AddItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList3.add(AddItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList3.add(AddItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList3.add(AddItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList3.add(AddItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList3.add(AddItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList3.add(AddItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList3.add(AddItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))

        /** 합골 **/
        searchList3.add(AddItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
        searchList3.add(AddItem("미정(타입2)", 0))
        searchList3.add(AddItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        searchList3.add(AddItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))

        /** 위패 **/
        searchList3.add(AddItem("흰색위패", R.drawable.img_tablet))
        searchList3.add(AddItem("검정위패-TR-2-0802", R.drawable.img_tablet2))

        searchList3.add(AddItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchList3.add(AddItem("추모패-TR-4-1307", R.drawable.img_tablet4))
    }
    private fun settingList1_4() {
        searchList4 = mutableListOf()

        searchList4.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList4.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList4.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList4.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
        searchList4.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))

        /** 유골함 **/
        // 1. 일반 밀봉진공함
        searchList4.add(AddItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList4.add(AddItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList4.add(AddItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList4.add(AddItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList4.add(AddItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList4.add(AddItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList4.add(AddItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList4.add(AddItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList4.add(AddItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList4.add(AddItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList4.add(AddItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList4.add(AddItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList4.add(AddItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList4.add(AddItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList4.add(AddItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList4.add(AddItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList4.add(AddItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList4.add(AddItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList4.add(AddItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList4.add(AddItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList4.add(AddItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList4.add(AddItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList4.add(AddItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList4.add(AddItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList4.add(AddItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList4.add(AddItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList4.add(AddItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList4.add(AddItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList4.add(AddItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList4.add(AddItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList4.add(AddItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList4.add(AddItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList4.add(AddItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList4.add(AddItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList4.add(AddItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList4.add(AddItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList4.add(AddItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList4.add(AddItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList4.add(AddItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList4.add(AddItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList4.add(AddItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList4.add(AddItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList4.add(AddItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList4.add(AddItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList4.add(AddItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList4.add(AddItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList4.add(AddItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList4.add(AddItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList4.add(AddItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList4.add(AddItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList4.add(AddItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList4.add(AddItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList4.add(AddItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList4.add(AddItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList4.add(AddItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList4.add(AddItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList4.add(AddItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList4.add(AddItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList4.add(AddItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList4.add(AddItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList4.add(AddItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList4.add(AddItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList4.add(AddItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList4.add(AddItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList4.add(AddItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList4.add(AddItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList4.add(AddItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList4.add(AddItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList4.add(AddItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList4.add(AddItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList4.add(AddItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList4.add(AddItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList4.add(AddItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList4.add(AddItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList4.add(AddItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList4.add(AddItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList4.add(AddItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList4.add(AddItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList4.add(AddItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList4.add(AddItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList4.add(AddItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList4.add(AddItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList4.add(AddItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList4.add(AddItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList4.add(AddItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList4.add(AddItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList4.add(AddItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList4.add(AddItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList4.add(AddItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList4.add(AddItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList4.add(AddItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList4.add(AddItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList4.add(AddItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList4.add(AddItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList4.add(AddItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList4.add(AddItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList4.add(AddItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList4.add(AddItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList4.add(AddItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList4.add(AddItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList4.add(AddItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList4.add(AddItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList4.add(AddItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList4.add(AddItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList4.add(AddItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList4.add(AddItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList4.add(AddItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList4.add(AddItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList4.add(AddItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList4.add(AddItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList4.add(AddItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList4.add(AddItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList4.add(AddItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))

        /** 합골 **/
        searchList4.add(AddItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
        searchList4.add(AddItem("미정(타입2)", 0))
        searchList4.add(AddItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        searchList4.add(AddItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))

        /** 위패 **/
        searchList4.add(AddItem("흰색위패", R.drawable.img_tablet))
        searchList4.add(AddItem("검정위패-TR-2-0802", R.drawable.img_tablet2))

        searchList4.add(AddItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchList4.add(AddItem("추모패-TR-4-1307", R.drawable.img_tablet4))
    }
    private fun settingList1_5() {
        searchList5 = mutableListOf()

        searchList5.add(AddItem("표석(大) PS-2 2317", R.drawable.img_urn11_2))
        searchList5.add(AddItem("표석(小) PS-4 0911", R.drawable.img_urn11_3))
        searchList5.add(AddItem("피아노(大) PS-1 3020", R.drawable.img_urn11_4))
        searchList5.add(AddItem("피아노(小) PS-3 2015", R.drawable.img_urn11_5))
        searchList5.add(AddItem("밀봉외함 MH-1 1015", R.drawable.img_urn11_1))

        /** 유골함 **/
        // 1. 일반 밀봉진공함
        searchList5.add(AddItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        searchList5.add(AddItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        searchList5.add(AddItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        searchList5.add(AddItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        searchList5.add(AddItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        searchList5.add(AddItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        searchList5.add(AddItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        searchList5.add(AddItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        searchList5.add(AddItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        searchList5.add(AddItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        searchList5.add(AddItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        searchList5.add(AddItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        searchList5.add(AddItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        searchList5.add(AddItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        searchList5.add(AddItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        searchList5.add(AddItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        searchList5.add(AddItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        searchList5.add(AddItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        searchList5.add(AddItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        searchList5.add(AddItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        searchList5.add(AddItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        searchList5.add(AddItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        searchList5.add(AddItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        searchList5.add(AddItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        searchList5.add(AddItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        searchList5.add(AddItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        searchList5.add(AddItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        searchList5.add(AddItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        searchList5.add(AddItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        searchList5.add(AddItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        searchList5.add(AddItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        searchList5.add(AddItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        searchList5.add(AddItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        searchList5.add(AddItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        searchList5.add(AddItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        searchList5.add(AddItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        searchList5.add(AddItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        searchList5.add(AddItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        searchList5.add(AddItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        searchList5.add(AddItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList5.add(AddItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        searchList5.add(AddItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        searchList5.add(AddItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        searchList5.add(AddItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        searchList5.add(AddItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        searchList5.add(AddItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        searchList5.add(AddItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        searchList5.add(AddItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        searchList5.add(AddItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        searchList5.add(AddItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        searchList5.add(AddItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        searchList5.add(AddItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        searchList5.add(AddItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        searchList5.add(AddItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        searchList5.add(AddItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        searchList5.add(AddItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        searchList5.add(AddItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        searchList5.add(AddItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        searchList5.add(AddItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        searchList5.add(AddItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        searchList5.add(AddItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        searchList5.add(AddItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        searchList5.add(AddItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        searchList5.add(AddItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        searchList5.add(AddItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        searchList5.add(AddItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        searchList5.add(AddItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        searchList5.add(AddItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        searchList5.add(AddItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        searchList5.add(AddItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        searchList5.add(AddItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        searchList5.add(AddItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        searchList5.add(AddItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        searchList5.add(AddItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        searchList5.add(AddItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        searchList5.add(AddItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        searchList5.add(AddItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        searchList5.add(AddItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        searchList5.add(AddItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        searchList5.add(AddItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        searchList5.add(AddItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        searchList5.add(AddItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        searchList5.add(AddItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        searchList5.add(AddItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        searchList5.add(AddItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        searchList5.add(AddItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        searchList5.add(AddItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        searchList5.add(AddItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        searchList5.add(AddItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        searchList5.add(AddItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        searchList5.add(AddItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        searchList5.add(AddItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        searchList5.add(AddItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        searchList5.add(AddItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        searchList5.add(AddItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        searchList5.add(AddItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        searchList5.add(AddItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        searchList5.add(AddItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        searchList5.add(AddItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        searchList5.add(AddItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        searchList5.add(AddItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        searchList5.add(AddItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        searchList5.add(AddItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        searchList5.add(AddItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        searchList5.add(AddItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        searchList5.add(AddItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        searchList5.add(AddItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        searchList5.add(AddItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        searchList5.add(AddItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        searchList5.add(AddItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        searchList5.add(AddItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        searchList5.add(AddItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        searchList5.add(AddItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))

        /** 합골 **/
        searchList5.add(AddItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
        searchList5.add(AddItem("미정(타입2)", 0))
        searchList5.add(AddItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        searchList5.add(AddItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))

        /** 위패 **/
        searchList5.add(AddItem("흰색위패", R.drawable.img_tablet))
        searchList5.add(AddItem("검정위패-TR-2-0802", R.drawable.img_tablet2))

        searchList5.add(AddItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchList5.add(AddItem("추모패-TR-4-1307", R.drawable.img_tablet4))
    }
}