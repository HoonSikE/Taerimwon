package com.example.taerimwon.ui.order.tablet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.taerimwon.databinding.FragmentTabletContainerBinding
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.utils.constant.ENGRAVETYPEPOSITION
import com.example.taerimwon.utils.input.saveImageToInternalStorage


@AndroidEntryPoint
class TabletContainerFragment : BaseFragment<FragmentTabletContainerBinding>(R.layout.fragment_tablet_container) {
    private lateinit var tabletSelectTypeAdapter: TabletTypeAdapter
    var tabletSelectTypeList = ArrayList<String>()
    // 이미지 선택 요청 코드를 나타내는 상수
    private val PICK_IMAGE_REQUEST_CODE = 123

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

        val tabletImageUri = ApplicationClass.prefs.tabletImageUri

        if (tabletImageUri != "") {
            val imageUri = Uri.parse(tabletImageUri)
            binding.imageAddPhoto.setImageURI(imageUri)
        }
    }
    private fun setOnClickListeners() {
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
    }
    // 사진 첨부
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {
                // 이미지를 미리보기로 표시
                binding.imageAddPhoto.setImageURI(selectedImageUri)

                // 이미지를 앱 내부 파일 시스템에 복사 또는 이동하고 그 경로를 얻음
                val imageFile = saveImageToInternalStorage(requireContext(), selectedImageUri)

                // 경로(URI)를 SharedPreferences에 저장
                ApplicationClass.prefs.tabletImageUri = imageFile?.toString()

//                // 이미지 URI를 다음 프래그먼트로 전달
//                val bundle = Bundle()
//                bundle.putString("selectedImageUri", selectedImageUri.toString())

//                // 다음 프래그먼트로 이동
//                findNavController().navigate(
//                    R.id.action_orderFragment_to_phoneAuthFragment,
//                    bundle
//                )
            }
        }
    }
    private fun setOnItemSelectedListener(){
        binding.spinnerSelectTabletType.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedTabletType = parent?.getItemAtPosition(position).toString()

                if(ApplicationClass.prefs.selectedTabletType!!.contains("선택안함"))
                    binding.layoutTablet.visibility = View.GONE
                else {
                    if(ApplicationClass.prefs.selectedTabletType!!.contains("사진")){
                        binding.layoutTablet.visibility = View.GONE
                        binding.layoutTabletPhoto.visibility = View.VISIBLE
                    }else{
                        binding.layoutTablet.visibility = View.VISIBLE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
    }
    private fun addTextChangedListener(){
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
        if(tabletType == "일반" && ApplicationClass.prefs.selectedUrnType == "선택안함") {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.imageHeart.visibility = View.GONE
            binding.textName3.text = "* 이름"
            binding.editTextName3.hint = "이름을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(4)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else if(tabletType.contains("본관")) {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.imageHeart.visibility = View.GONE
            binding.textName3.text = "* 본관"
            binding.editTextName3.hint = "본관을 입력하세요."
            val inputFilter = InputFilter.LengthFilter(10)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else if(tabletType == "문구") {
            binding.textName3.visibility = View.VISIBLE
            binding.editTextName3.visibility = View.VISIBLE
            binding.imageName3.visibility = View.VISIBLE
            binding.imageHeart.visibility = View.VISIBLE
            binding.textName3.text = "* 문구"
            binding.editTextName3.hint = "문구를 입력하세요. (최대 30자)"
            val inputFilter = InputFilter.LengthFilter(30)
            binding.editTextName3.filters = arrayOf(inputFilter)
        }else{
            binding.textName3.visibility = View.GONE
            binding.editTextName3.visibility = View.GONE
            binding.imageName3.visibility = View.GONE
            binding.imageHeart.visibility = View.GONE
            ApplicationClass.prefs.name2 = ""
            ApplicationClass.prefs.name3 = ApplicationClass.prefs.name1
        }
    }
}