package com.example.taerimwon.ui.order.tablet

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
import com.example.taerimwon.data.dto.tablet.TabletItem
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.databinding.FragmentTabletContainerBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.order.OrderViewModel
import com.example.taerimwon.ui.order.tablet.tablet2.Tablet2AutoCompleteAdapter
import com.example.taerimwon.ui.order.tablet.tablet2.Tablet2TypeAdapter
import com.example.taerimwon.utils.input.saveImageToInternalStorage
import com.example.taerimwon.utils.view.toast
import java.lang.Math.abs


@AndroidEntryPoint
class TabletContainerFragment : BaseFragment<FragmentTabletContainerBinding>(R.layout.fragment_tablet_container) {
    private val orderViewModel: OrderViewModel by viewModels()

    // 자동완성 단어들을 담을 리스트
    private lateinit var searchList: MutableList<TabletItem>
    private lateinit var searchList2: MutableList<TabletItem>

    private lateinit var searchBoneList: MutableList<TabletItem>
    private lateinit var searchBoneList2: MutableList<TabletItem>

    private lateinit var tabletSelectTypeAdapter: TabletTypeAdapter
    var tabletSelectTypeList = ArrayList<String>()

    private lateinit var boneTabletSelectTypeAdapter: Tablet2TypeAdapter
    var boneTabletSelectTypeList = ArrayList<String>()
    // 이미지 선택 요청 코드를 나타내는 상수
    private val PICK_IMAGE_REQUEST_CODE = 123
    private val PICK_IMAGE_REQUEST_CODE2 = 124

    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
        setOnItemSelectedListener()
        addTextChangedListener()
        observer()
    }
    private val tabletTypeClickListener: (View, String) -> Unit = { _, tabletType ->
        // 배열을 가져옵니다.
        val tabletTypes = tabletType.split(" ")
        ApplicationClass.prefs.tabletType = tabletTypes[1]
        println("tabletType: " + ApplicationClass.prefs.tabletType)

        setTabletName2(ApplicationClass.prefs.tabletReligion.toString(), tabletTypes[1])
        setName3(tabletTypes[1])
    }
    private val boneTabletTypeClickListener: (View, String) -> Unit = { _, boneTabletType ->
        // 배열을 가져옵니다.
        val boneTabletTypes = boneTabletType.split(" ")
        ApplicationClass.prefs.boneTabletType = boneTabletTypes[1]
        println("boneTabletType: " + ApplicationClass.prefs.boneTabletType)

        setBoneTabletName2(ApplicationClass.prefs.boneTabletReligion.toString(), boneTabletTypes[1])
        setBoneName3(boneTabletTypes[1])
    }
    private fun initAdapter() {
        tabletSelectTypeAdapter = TabletTypeAdapter(requireContext()).apply {
            onItemClickListener = tabletTypeClickListener
        }
        tabletSelectTypeAdapter.setSelectedItem(ApplicationClass.prefs.tabletTypePosition)

        binding.recyclerviewTabletType.apply {
            adapter = tabletSelectTypeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }

        boneTabletSelectTypeAdapter = Tablet2TypeAdapter(requireContext()).apply {
            onItemClickListener = boneTabletTypeClickListener
        }
        boneTabletSelectTypeAdapter.setSelectedItem(ApplicationClass.prefs.boneTabletTypePosition)

        binding.recyclerviewBoneTabletType.apply {
            adapter = boneTabletSelectTypeAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }
    private fun initData() {
        searchList = mutableListOf()
        settingList()
        searchList2 = mutableListOf()
        settingList2()

        // "ArrayList" - 리스트 객체
        var searchListTmp = searchList
        if(ApplicationClass.prefs.selectedTabletType == "사진")
            searchListTmp = searchList2

        binding.autoCompleteTextView.setAdapter(
            TabletAutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchListTmp
            )
        )

        searchBoneList = mutableListOf()
        settingBoneList()
        searchBoneList2 = mutableListOf()
        settingBoneList2()

        // "ArrayList" - 리스트 객체
        var searchBoneListTmp = searchBoneList
        if(ApplicationClass.prefs.boneSelectedTabletType == "사진")
            searchBoneListTmp = searchBoneList2

        binding.autoCompleteTextView2.setAdapter(
            Tablet2AutoCompleteAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                searchBoneListTmp
            )
        )

        binding.autoCompleteTextView.setText(ApplicationClass.prefs.selectedTabletName)
        binding.autoCompleteTextView2.setText(ApplicationClass.prefs.selectedTabletName2)

        binding.editTextTabletName2.setText(ApplicationClass.prefs.tabletName2)
        binding.editTextName3.setText(ApplicationClass.prefs.name3)

        val selectedTabletTypesArray = resources.getStringArray(R.array.selected_tablet_types)
        val selectedTabletTypeList = mutableListOf(*selectedTabletTypesArray) as ArrayList<String>
        binding.spinnerSelectTabletType.setSelection(selectedTabletTypeList.indexOf(ApplicationClass.prefs.selectedTabletType))

        val religionArray = resources.getStringArray(R.array.religion)
        val religionArrayList = mutableListOf(*religionArray) as ArrayList<String>
        var tabletReligion = ApplicationClass.prefs.tabletReligion
        binding.spinnerReligion.setSelection(religionArrayList.indexOf(tabletReligion))

        // 배열을 가져옵니다.
        // 문자열 이름을 문자열로 정의합니다.
        var position = 0
        when (ApplicationClass.prefs.tabletReligion) {
            "일반" -> position = 0
            "기독교" -> position = 1
            "불교" -> position = 2
            "천주교" -> position = 3
        }

        var arrName = "tablet_type" + position
        // 문자열 배열을 가져옵니다.
        val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
        val tabletTypesArray = resources.getStringArray(stringArray)

        if(ApplicationClass.prefs.name3.toString().length > 4)
            ApplicationClass.prefs.name3 = ApplicationClass.prefs.name3.toString().substring(0, 4)

        setName3(ApplicationClass.prefs.tabletType.toString())

        // 배열을 리스트로 변환합니다.
        tabletSelectTypeList = mutableListOf(*tabletTypesArray) as ArrayList<String>
        tabletSelectTypeAdapter.updateList(tabletSelectTypeList)
        binding.recyclerviewTabletType.scrollToPosition(ApplicationClass.prefs.tabletTypePosition)

        val tabletImageUri = ApplicationClass.prefs.tabletImageUri

        if (tabletImageUri != "") {
            val imageUri = Uri.parse(tabletImageUri)
            binding.imageAddPhoto.setImageURI(imageUri)
        }

        /**합골, 위패 추가*/
        if(ApplicationClass.prefs.selectedTabletType!!.contains("선택안함")) {
            binding.layoutTabletAutoComplete.visibility = View.GONE
            binding.layoutTablet.visibility = View.GONE
            binding.layoutTabletPhoto.visibility = View.GONE
            binding.layoutBone.visibility = View.GONE
        }else {
            binding.textSelectBoneTabletType.text = "● 위패 추가 주문"
            binding.spinnerSelectBoneTabletType.visibility = View.VISIBLE
            binding.imageSelectBoneTabletType.visibility = View.VISIBLE

            binding.layoutTabletAutoComplete.visibility = View.VISIBLE
            binding.layoutTablet2AutoComplete.visibility = View.VISIBLE
            binding.layoutBone.visibility = View.VISIBLE
            if(ApplicationClass.prefs.selectedTabletType!!.contains("사진")){
                binding.layoutTablet.visibility = View.GONE
                binding.layoutTabletPhoto.visibility = View.VISIBLE
            }else {
                binding.layoutTablet.visibility = View.VISIBLE
                binding.layoutTabletPhoto.visibility = View.GONE

                if(ApplicationClass.prefs.selectedTabletType!!.contains("합골")) {
                    ApplicationClass.prefs.boneSelectedTabletType = "선택안함"

                    binding.textSelectBoneTabletType.text = "● 위패[합골] 추가 정보"
                    binding.spinnerSelectBoneTabletType.visibility = View.GONE
                    binding.imageSelectBoneTabletType.visibility = View.GONE

                    binding.layoutBoneTablet.visibility = View.VISIBLE
                    binding.layoutTablet2AutoComplete.visibility = View.GONE
                    binding.layoutBoneTablet2.visibility = View.VISIBLE
                }
            }
        }

        val boneSexArray = resources.getStringArray(R.array.bone_sex)
        val boneSexList = mutableListOf(*boneSexArray) as ArrayList<String>
        binding.spinnerBoneSex.setSelection(boneSexList.indexOf(ApplicationClass.prefs.boneTabletSex))

        binding.editTextBoneTabletName2.setText(ApplicationClass.prefs.boneTabletName2)
        binding.editTextBoneName3.setText(ApplicationClass.prefs.boneName3)

        val selectedBoneTabletTypesArray = resources.getStringArray(R.array.selected_tablet_types2)
        val selectedBoneTabletTypeList = mutableListOf(*selectedBoneTabletTypesArray) as ArrayList<String>
        binding.spinnerSelectBoneTabletType.setSelection(selectedBoneTabletTypeList.indexOf(ApplicationClass.prefs.boneSelectedTabletType))

        val boneReligionArray = resources.getStringArray(R.array.religion)
        val boneReligionArrayList = mutableListOf(*boneReligionArray) as ArrayList<String>
        var boneTabletReligion = ApplicationClass.prefs.boneTabletReligion
        binding.spinnerBoneReligion.setSelection(boneReligionArrayList.indexOf(boneTabletReligion))

        // 배열을 가져옵니다.
        // 문자열 이름을 문자열로 정의합니다.
        var position2 = 0
        when (ApplicationClass.prefs.boneTabletReligion) {
            "일반" -> position2 = 0
            "기독교" -> position2 = 1
            "불교" -> position2 = 2
            "천주교" -> position2 = 3
        }

        var arrName2 = "tablet_type" + position2
        // 문자열 배열을 가져옵니다.
        val stringArray2 = resources.getIdentifier(arrName2, "array", requireContext().packageName)
        val boneTabletTypesArray = resources.getStringArray(stringArray2)

        if(ApplicationClass.prefs.boneName3.toString().length > 4)
            ApplicationClass.prefs.boneName3 = ApplicationClass.prefs.boneName3.toString().substring(0, 4)
        setBoneName3(ApplicationClass.prefs.boneTabletType.toString())

        // 배열을 리스트로 변환합니다.
        boneTabletSelectTypeList = mutableListOf(*boneTabletTypesArray) as ArrayList<String>
        boneTabletSelectTypeAdapter.updateList(boneTabletSelectTypeList)
        binding.recyclerviewBoneTabletType.scrollToPosition(ApplicationClass.prefs.boneTabletTypePosition)

        val boneTabletImageUri = ApplicationClass.prefs.boneTabletImageUri

        if (boneTabletImageUri != "") {
            val boneImageUri = Uri.parse(boneTabletImageUri)
            binding.imageBoneAddPhoto.setImageURI(boneImageUri)
        }
    }
    private fun setOnClickListeners() {
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag1 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedTabletItem = adapterView.getItemAtPosition(position) as TabletItem
            binding.autoCompleteTextView.setText(selectedTabletItem.tabletItem)
            ApplicationClass.prefs.selectedTabletName = selectedTabletItem.tabletItem
        }

        binding.autoCompleteTextView.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
        binding.autoCompleteTextView2.setOnItemClickListener { adapterView, view, position, rowId ->
            println("tag2 "+ "position: $position, rowId:$rowId, string: ${adapterView.getItemAtPosition(position)}")
            val selectedTabletItem = adapterView.getItemAtPosition(position) as TabletItem
            binding.autoCompleteTextView2.setText(selectedTabletItem.tabletItem)
            ApplicationClass.prefs.selectedTabletName2 = selectedTabletItem.tabletItem
        }

        binding.autoCompleteTextView2.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                (view as AutoCompleteTextView).showDropDown()
            }
        }
        binding.imageHeart.setOnClickListener{
            val editTextName3 = binding.editTextName3
            val updateText = editTextName3.text.toString() + "❤️"
            editTextName3.setText(updateText)
            if(updateText.length < 30)
                editTextName3.setSelection(updateText.length)
        }
        // 사진 첨부
        binding.textAddPhoto.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }

        binding.imageBoneHeart.setOnClickListener{
            val editTextBoneName3 = binding.editTextBoneName3
            val updateText = editTextBoneName3.text.toString() + "❤️"
            editTextBoneName3.setText(updateText)
            if(updateText.length < 30)
                editTextBoneName3.setSelection(updateText.length)
        }
        // 사진 첨부
        binding.textBoneAddPhoto.setOnClickListener{
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE2)
        }
    }
    // 사진 첨부
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {

                // 이미지를 앱 내부 파일 시스템에 복사 또는 이동하고 그 경로를 얻음
                val imageFile = saveImageToInternalStorage(requireContext(), selectedImageUri)

                if (is3x4AspectRatio(selectedImageUri, 0.1f)) {
                    // 이미지를 미리보기로 표시
                    binding.imageAddPhoto.setImageURI(selectedImageUri)

                    // 3:4 비율의 이미지를 선택한 경우
                    // 경로(URI)를 SharedPreferences에 저장
                    ApplicationClass.prefs.tabletImageUri = imageFile?.toString()
                } else {
                    // 3:4 비율이 아닌 이미지를 선택한 경우, 사용자에게 메시지 표시 또는 처리 방법을 결정
                    // 예: Toast 메시지를 표시하거나 경고 메시지를 표시
                    toast("이미지는 3:4 비율이어야 합니다.")
                }
            }
        }
        else if (requestCode == PICK_IMAGE_REQUEST_CODE2 && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {

                // 이미지를 앱 내부 파일 시스템에 복사 또는 이동하고 그 경로를 얻음
                val imageFile = saveImageToInternalStorage(requireContext(), selectedImageUri)

                if (is3x4AspectRatio(selectedImageUri, 0.1f)) {
                    // 이미지를 미리보기로 표시
                    binding.imageBoneAddPhoto.setImageURI(selectedImageUri)

                    // 3:4 비율의 이미지를 선택한 경우
                    // 경로(URI)를 SharedPreferences에 저장
                    ApplicationClass.prefs.boneTabletImageUri = imageFile?.toString()
                } else {
                    // 3:4 비율이 아닌 이미지를 선택한 경우, 사용자에게 메시지 표시 또는 처리 방법을 결정
                    // 예: Toast 메시지를 표시하거나 경고 메시지를 표시
                    toast("이미지는 3:4 비율이어야 합니다.")
                }
            }
        }
    }
    private fun setOnItemSelectedListener(){
        binding.spinnerSelectTabletType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedTabletType = parent?.getItemAtPosition(position).toString()
                println(ApplicationClass.prefs.selectedTabletType)

                if(ApplicationClass.prefs.selectedTabletType!!.contains("선택안함")) {
                    binding.layoutTabletAutoComplete.visibility = View.GONE
                    binding.layoutTablet.visibility = View.GONE
                    binding.layoutTabletPhoto.visibility = View.GONE
                    binding.layoutBone.visibility = View.GONE
                }else {
                    binding.textSelectBoneTabletType.text = "● 위패 추가 주문"
                    binding.spinnerSelectBoneTabletType.visibility = View.VISIBLE
                    binding.imageSelectBoneTabletType.visibility = View.VISIBLE

                    binding.layoutTabletAutoComplete.visibility = View.VISIBLE
                    binding.layoutTablet2AutoComplete.visibility = View.VISIBLE
                    binding.layoutBone.visibility = View.VISIBLE
                    if(ApplicationClass.prefs.selectedTabletType!!.contains("사진")){
                        binding.layoutTablet.visibility = View.GONE
                        binding.layoutTabletPhoto.visibility = View.VISIBLE
                    }else {
                        binding.layoutTablet.visibility = View.VISIBLE
                        binding.layoutTabletPhoto.visibility = View.GONE

                        if(ApplicationClass.prefs.selectedTabletType!!.contains("합골")) {
                            ApplicationClass.prefs.boneSelectedTabletType = "선택안함"

                            binding.textSelectBoneTabletType.text = "● 위패[합골] 추가 정보"
                            binding.spinnerSelectBoneTabletType.visibility = View.GONE
                            binding.imageSelectBoneTabletType.visibility = View.GONE

                            binding.layoutBoneTablet.visibility = View.VISIBLE
                            binding.layoutTablet2AutoComplete.visibility = View.GONE
                            binding.layoutBoneTablet2.visibility = View.VISIBLE
                        }
                    }
                }

                when (ApplicationClass.prefs.selectedTabletType) {
                    "사진" -> {
                        binding.autoCompleteTextView.setAdapter(
                            TabletAutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchList2
                            )
                        )
                    }
                    else -> {
                        binding.autoCompleteTextView.setAdapter(
                            TabletAutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchList
                            )
                        )
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        binding.spinnerReligion.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.tabletReligion = parent?.getItemAtPosition(position).toString()
                println(ApplicationClass.prefs.tabletReligion)

                // 문자열 이름을 문자열로 정의합니다.
                var arrName = "tablet_type" + position

                // 문자열 배열을 가져옵니다.
                val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
                val tabletTypesArray = resources.getStringArray(stringArray)

                // 리사이클러뷰 초기화.
                tabletSelectTypeAdapter = TabletTypeAdapter(requireContext()).apply {
                    onItemClickListener = tabletTypeClickListener
                }
                binding.recyclerviewTabletType.apply {
                    adapter = tabletSelectTypeAdapter
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }
                tabletSelectTypeList = mutableListOf(*tabletTypesArray) as ArrayList<String>
                tabletSelectTypeAdapter.updateList(tabletSelectTypeList)
                binding.recyclerviewTabletType.scrollToPosition(0)

                ApplicationClass.prefs.tabletType = ApplicationClass.prefs.tabletReligion
                setTabletName2(ApplicationClass.prefs.tabletReligion.toString(), ApplicationClass.prefs.tabletType.toString())
                setName3(ApplicationClass.prefs.tabletType.toString())
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        /**합골*/
        binding.spinnerBoneSex.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.boneTabletSex = parent?.getItemAtPosition(position).toString() ?: ""
                if(ApplicationClass.prefs.boneTabletSex == "남성"){
                    ApplicationClass.prefs.tabletSex = "여성"
                    val drawableResource = R.drawable.view_radius_man
                    binding.layoutBoneTablet.setBackgroundResource(drawableResource)
                } else if(ApplicationClass.prefs.boneTabletSex == "여성"){
                    ApplicationClass.prefs.tabletSex = "남성"
                    val drawableResource = R.drawable.view_radius_woman
                    binding.layoutBoneTablet.setBackgroundResource(drawableResource)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        binding.spinnerSelectBoneTabletType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.boneSelectedTabletType = parent?.getItemAtPosition(position).toString()
                println(ApplicationClass.prefs.boneSelectedTabletType)

                if(ApplicationClass.prefs.boneSelectedTabletType!!.contains("선택안함")) {
//                    binding.layoutTabletAutoComplete.visibility = View.GONE
                    binding.layoutBoneTablet.visibility = View.GONE
                }else {
//                    binding.layoutTabletAutoComplete.visibility = View.VISIBLE
                    binding.layoutBoneTablet.visibility = View.VISIBLE
                    if(ApplicationClass.prefs.boneSelectedTabletType!!.contains("사진")){
                        binding.layoutBoneTablet2.visibility = View.GONE
                        binding.layoutBoneTabletPhoto.visibility = View.VISIBLE
                    }else{
                        binding.layoutBoneTablet2.visibility = View.VISIBLE
                        binding.layoutBoneTabletPhoto.visibility = View.GONE
                    }
                }

                when (ApplicationClass.prefs.boneSelectedTabletType) {
                    "사진" -> {
                        binding.autoCompleteTextView2.setAdapter(
                            Tablet2AutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchBoneList2
                            )
                        )
                    }
                    else -> {
                        binding.autoCompleteTextView2.setAdapter(
                            Tablet2AutoCompleteAdapter(
                                requireContext(),
                                android.R.layout.simple_dropdown_item_1line,
                                searchBoneList
                            )
                        )
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
        binding.spinnerBoneReligion.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.boneTabletReligion = parent?.getItemAtPosition(position).toString()
                println(ApplicationClass.prefs.boneTabletReligion)

                // 문자열 이름을 문자열로 정의합니다.
                var arrName = "tablet_type" + position

                // 문자열 배열을 가져옵니다.
                val stringArray = resources.getIdentifier(arrName, "array", requireContext().packageName)
                val boneTabletTypesArray = resources.getStringArray(stringArray)

                // 리사이클러뷰 초기화.
                boneTabletSelectTypeAdapter = Tablet2TypeAdapter(requireContext()).apply {
                    onItemClickListener = boneTabletTypeClickListener
                }
                binding.recyclerviewBoneTabletType.apply {
                    adapter = boneTabletSelectTypeAdapter
                    layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                }
                boneTabletSelectTypeList = mutableListOf(*boneTabletTypesArray) as ArrayList<String>
                boneTabletSelectTypeAdapter.updateList(boneTabletSelectTypeList)
                binding.recyclerviewBoneTabletType.scrollToPosition(0)

                ApplicationClass.prefs.boneTabletType = ApplicationClass.prefs.boneTabletReligion
                setBoneTabletName2(ApplicationClass.prefs.boneTabletReligion.toString(), ApplicationClass.prefs.boneTabletType.toString())
                setBoneName3(ApplicationClass.prefs.boneTabletType.toString())
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
                ApplicationClass.prefs.selectedTabletName = s?.toString() ?: ""
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
                ApplicationClass.prefs.selectedTabletName2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        // 직분, 세례명, 법명
        binding.editTextTabletName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.tabletName2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        // 본관, 문구
        binding.editTextName3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.name3 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        /**합골**/
        // 직분, 세례명, 법명
        binding.editTextBoneTabletName2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.boneTabletName2 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
        // 본관, 문구
        binding.editTextBoneName3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.boneName3 = s?.toString() ?: ""
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })
    }
    private fun observer() {
        orderViewModel.engraveTypePosition.observe(viewLifecycleOwner) { value ->
        }
    }
    private fun setTabletName2(tabletReligion: String, tabletType: String) {
        // 직분, 세례명, 법명
        if(tabletReligion == "기독교" && tabletType != "문구") {
            binding.textTabletName2.visibility = View.VISIBLE
            binding.editTextTabletName2.visibility = View.VISIBLE
            binding.imageTabletName2.visibility = View.VISIBLE
            binding.textTabletName2.text = "직분"
            binding.editTextTabletName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextTabletName2.filters = arrayOf(inputFilter)
        }
//        else if(tabletReligion == "불교" && tabletType != "문구") {
//            binding.textName2.visibility = View.VISIBLE
//            binding.editTextName2.visibility = View.VISIBLE
//            binding.imageName2.visibility = View.VISIBLE
//            binding.textName2.text = "법명"
//            binding.editTextName2.hint = "법명을 입력하세요."
//            val inputFilter = InputFilter.LengthFilter(4)
//            binding.editTextName2.filters = arrayOf(inputFilter)
//        }
        else if(tabletReligion == "천주교" && tabletType != "문구") {
            binding.textTabletName2.visibility = View.VISIBLE
            binding.editTextTabletName2.visibility = View.VISIBLE
            binding.imageTabletName2.visibility = View.VISIBLE
            binding.textTabletName2.text = "세례명"
            binding.editTextTabletName2.hint = "세례명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(6)
            binding.editTextTabletName2.filters = arrayOf(inputFilter)
        }else{
            binding.textTabletName2.visibility = View.GONE
            binding.editTextTabletName2.visibility = View.GONE
            binding.imageTabletName2.visibility = View.GONE
            ApplicationClass.prefs.tabletName2 = ""
        }
    }
    private fun setName3(tabletType: String) {
        if(tabletType == "일반" || tabletType == "기독교" || tabletType == "불교" || tabletType == "천주교") {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.imageHeart.visibility = View.GONE
            binding.textName3.text = "이름"
            binding.editTextName3.hint = "이름을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else if(tabletType.contains("본관")) {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.imageHeart.visibility = View.GONE
            binding.textName3.text = "본관"
            binding.editTextName3.hint = "본관을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(10)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else if(tabletType == "문구") {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.imageHeart.visibility = View.VISIBLE
            binding.textName3.text = "문구"
            binding.editTextName3.hint = "문구를 입력하세요. (최대 30자)"
            val inputFilter = InputFilter.LengthFilter(30)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else{
            binding.textName3.visibility = View.GONE
            binding.editTextName3.visibility = View.GONE
            binding.imageName3.visibility = View.GONE
            binding.imageHeart.visibility = View.GONE
            ApplicationClass.prefs.tabletName2 = ""
        }
    }
    /**합골*/
    private fun setBoneTabletName2(boneTabletReligion: String, boneTabletType: String) {
        // 직분, 세례명, 법명
        if(boneTabletReligion == "기독교" && boneTabletType != "문구") {
            binding.textBoneTabletName2.visibility = View.VISIBLE
            binding.editTextBoneTabletName2.visibility = View.VISIBLE
            binding.imageBoneTabletName2.visibility = View.VISIBLE
            binding.textBoneTabletName2.text = "직분"
            binding.editTextBoneTabletName2.hint = "직분을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextBoneTabletName2.filters = arrayOf(inputFilter)
        }
//        else if(boneTabletReligion == "불교" && boneTabletType != "문구") {
//            binding.textName2.visibility = View.VISIBLE
//            binding.editTextName2.visibility = View.VISIBLE
//            binding.imageName2.visibility = View.VISIBLE
//            binding.textName2.text = "법명"
//            binding.editTextName2.hint = "법명을 입력하세요."
//            val inputFilter = InputFilter.LengthFilter(4)
//            binding.editTextName2.filters = arrayOf(inputFilter)
//        }
        else if(boneTabletReligion == "천주교" && boneTabletType != "문구") {
            binding.textBoneTabletName2.visibility = View.VISIBLE
            binding.editTextBoneTabletName2.visibility = View.VISIBLE
            binding.imageBoneTabletName2.visibility = View.VISIBLE
            binding.textBoneTabletName2.text = "세례명"
            binding.editTextBoneTabletName2.hint = "세례명을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(6)
            binding.editTextBoneTabletName2.filters = arrayOf(inputFilter)
        }else{
            binding.textBoneTabletName2.visibility = View.GONE
            binding.editTextBoneTabletName2.visibility = View.GONE
            binding.imageBoneTabletName2.visibility = View.GONE
            ApplicationClass.prefs.boneTabletName2 = ""
        }
    }
    private fun setBoneName3(boneTabletType: String) {
        if(boneTabletType == "일반" || boneTabletType == "기독교" || boneTabletType == "불교" || boneTabletType == "천주교") {
            binding.textBoneName3.visibility = View.VISIBLE
            binding.editTextBoneName3.visibility = View.VISIBLE
            binding.imageBoneName3.visibility = View.VISIBLE
            binding.imageBoneHeart.visibility = View.GONE
            binding.textBoneName3.text = "이름"
            binding.editTextBoneName3.hint = "이름을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextBoneName3.filters = arrayOf(inputFilter)
        }else if(boneTabletType.contains("본관")) {
            binding.textBoneName3.visibility = View.VISIBLE
            binding.editTextBoneName3.visibility = View.VISIBLE
            binding.imageBoneName3.visibility = View.VISIBLE
            binding.imageBoneHeart.visibility = View.GONE
            binding.textBoneName3.text = "본관"
            binding.editTextBoneName3.hint = "본관을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(10)
            binding.editTextBoneName3.filters = arrayOf(inputFilter)
        }else if(boneTabletType == "문구") {
            binding.textBoneName3.visibility = View.VISIBLE
            binding.editTextBoneName3.visibility = View.VISIBLE
            binding.imageBoneName3.visibility = View.VISIBLE
            binding.imageBoneHeart.visibility = View.VISIBLE
            binding.textBoneName3.text = "문구"
            binding.editTextBoneName3.hint = "문구를 입력하세요. (최대 30자)"
            val inputFilter = InputFilter.LengthFilter(30)
            binding.editTextBoneName3.filters = arrayOf(inputFilter)
        }else{
            binding.textBoneName3.visibility = View.GONE
            binding.editTextBoneName3.visibility = View.GONE
            binding.imageBoneName3.visibility = View.GONE
            binding.imageBoneHeart.visibility = View.GONE
            ApplicationClass.prefs.boneTabletName2 = ""
        }
    }
    private fun settingList() {
        searchList.add(TabletItem("미정", 0))
        searchList.add(TabletItem("조각위패(황금향,조각종교)", R.drawable.img_tablet1))
        searchList.add(TabletItem("검정위패-TR-2-0802", R.drawable.img_tablet2))
//        searchList.add(TabletItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
//        searchList.add(TabletItem("추모패-TR-4-1307", R.drawable.img_tablet4))
//        searchList.add(TabletItem("기본", R.drawable.img_tablet))
//        searchList.add(TabletItem("기본(검정)", R.drawable.img_tablet))
    }
    private fun settingList2() {
        searchList2.add(TabletItem("미정", 0))
//        searchList2.add(TabletItem("조각위패(황금향,조각종교)", R.drawable.img_tablet1))
//        searchList2.add(TabletItem("검정위패-TR-2-0802", R.drawable.img_tablet2))
        searchList2.add(TabletItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchList2.add(TabletItem("추모패-TR-4-1307", R.drawable.img_tablet4))
//        searchList2.add(TabletItem("기본", R.drawable.img_tablet))
//        searchList2.add(TabletItem("기본(검정)", R.drawable.img_tablet0))
    }
    private fun settingBoneList() {
        searchBoneList.add(TabletItem("미정", 0))
        searchBoneList.add(TabletItem("조각위패(황금향,조각종교)", R.drawable.img_tablet1))
        searchBoneList.add(TabletItem("검정위패-TR-2-0802", R.drawable.img_tablet2))
//        searchList.add(TabletItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
//        searchList.add(TabletItem("추모패-TR-4-1307", R.drawable.img_tablet4))
//        searchList.add(TabletItem("기본", R.drawable.img_tablet))
//        searchList.add(TabletItem("기본(검정)", R.drawable.img_tablet))
    }
    private fun settingBoneList2() {
        searchBoneList2.add(TabletItem("미정", 0))
//        searchList2.add(TabletItem("조각위패(황금향,조각종교)", R.drawable.img_tablet1))
//        searchList2.add(TabletItem("검정위패-TR-2-0802", R.drawable.img_tablet2))
        searchBoneList2.add(TabletItem("사진위패 TR-3 1005", R.drawable.img_tablet3))
        searchBoneList2.add(TabletItem("추모패-TR-4-1307", R.drawable.img_tablet4))
//        searchList2.add(TabletItem("기본", R.drawable.img_tablet))
//        searchList2.add(TabletItem("기본(검정)", R.drawable.img_tablet0))
    }
    fun is3x4AspectRatio(uri: Uri, aspectRatioTolerance: Float): Boolean {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(requireContext().contentResolver.openInputStream(uri), null, options)
        val width = options.outWidth
        val height = options.outHeight

        // 원하는 비율 (4:3)과의 비율 차이를 계산
        val targetAspectRatio = 3f / 4f
        val imageAspectRatio = width.toFloat() / height.toFloat()
        val aspectRatioDifference = abs(targetAspectRatio - imageAspectRatio)

        // 비율 차이가 허용 범위 내에 있으면 true 반환
        return aspectRatioDifference <= aspectRatioTolerance

//        return width * 4 == height * 3
    }
}