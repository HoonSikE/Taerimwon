package com.example.taerimwon.ui.order

import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.databinding.FragmentOrderBinding
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import com.example.taerimwon.ui.order.tablet.TabletContainerFragment
import com.example.taerimwon.ui.order.urn.UrnContainerFragment
import com.example.taerimwon.utils.input.DateTimeTextWatcher
import com.example.taerimwon.utils.input.PhoneNumberTextWatcher
import com.example.taerimwon.utils.view.toast

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {
    val authViewModel: AuthViewModel by viewModels()
    // 한글, 전화번호, 날짜, 날짜시간 점검 패턴
    private val hanglePattern = """[가-힣]+""".toRegex()
    private val telPattern = """^[0-9]{3}-[0-9]{4}-[0-9]{4}$""".toRegex()
    private val dateTimePattern = """^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$""".toRegex()
    private val datePattern = """^\d{4}-\d{2}-\d{2}$""".toRegex()
    override fun init() {
        initData()
        setOnCheckedChangeListener()
        setOnClickListeners()
        setOnItemSelectedListener()
        addTextChangedListener()
        observer()
    }

    private fun initData() {
        authViewModel.getBlackList()
        if (!ApplicationClass.prefs.authenticated)
            findNavController().navigate(R.id.action_orderFragment_to_phoneAuthFragment)

        println("ApplicationClass.prefs.saveInfo :" + ApplicationClass.prefs.saveInfo)
        println("ApplicationClass.prefs.leaderName :" + ApplicationClass.prefs.leaderName)
        println("ApplicationClass.prefs.leaderTel :" + ApplicationClass.prefs.leaderTel)
        println("ApplicationClass.prefs.leaderDepartment :" + ApplicationClass.prefs.leaderDepartment)

        // 발주자 정보
//        if(ApplicationClass.prefs.saveInfo) {
        binding.checkboxLeader.isChecked = ApplicationClass.prefs.saveInfo
        binding.editTextLeaderName.setText(ApplicationClass.prefs.leaderName)
        binding.editTextLeaderTel.setText(ApplicationClass.prefs.leaderTel)
        binding.editTextLeaderDepartment.setText(ApplicationClass.prefs.leaderDepartment)

        binding.editTextClientName.setText(ApplicationClass.prefs.clientName)
        binding.editTextClientTel.setText(ApplicationClass.prefs.clientTel)

        val selectedLocation = ApplicationClass.prefs.selectedLocation
        val layoutRadioCremation = binding.layoutRadioCremation
        val layoutRadioFuneral = binding.layoutRadioFuneral
        val layoutRadioBurial = binding.layoutRadioBurial
        if(selectedLocation == "화장장"){
            binding.radioButton1.isChecked = true
            binding.radioButton2.isChecked = false
            binding.radioButton3.isChecked = false

            layoutRadioCremation.visibility = View.VISIBLE
            layoutRadioFuneral.visibility = View.GONE
            layoutRadioBurial.visibility = View.GONE

            // 화장장 셀렉트박스
            // Spinner에 표시할 데이터 목록
            val spinner = binding.spinnerCremationArea
            val cremationAreas = resources.getStringArray(R.array.cremationAreas).toMutableList()
            val dateTypeTypesArray = resources.getStringArray(R.array.cremationAreas)

            // 배열을 리스트로 변환합니다.
            val dateTypeList = mutableListOf(*dateTypeTypesArray) as ArrayList<String>
            binding.spinnerCremationArea.setSelection(dateTypeList.indexOf(ApplicationClass.prefs.date1Type))

            // 비활성화할 아이템
            val itemToDisable = "서울/경기/인천"
            val indexOfItemToDisable = cremationAreas.indexOf(itemToDisable)
            if (indexOfItemToDisable != -1) {
                cremationAreas.removeAt(indexOfItemToDisable)
            }

            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cremationAreas)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            binding.editTextCremationTime.setText(ApplicationClass.prefs.cremationTime)
        }else if(selectedLocation == "장례식장"){
            binding.radioButton1.isChecked = false
            binding.radioButton2.isChecked = true
            binding.radioButton3.isChecked = false

            layoutRadioCremation.visibility = View.GONE
            layoutRadioFuneral.visibility = View.VISIBLE
            layoutRadioBurial.visibility = View.GONE

            binding.editTextFuneralName.setText(ApplicationClass.prefs.funeralName)
            binding.editTextFuneralNumber.setText(ApplicationClass.prefs.funeralNumber)
            binding.editTextFuneralTime.setText(ApplicationClass.prefs.funeralTime)
        }else if(selectedLocation == "장지"){
            binding.radioButton1.isChecked = false
            binding.radioButton2.isChecked = false
            binding.radioButton3.isChecked = true

            layoutRadioCremation.visibility = View.GONE
            layoutRadioFuneral.visibility = View.GONE
            layoutRadioBurial.visibility = View.VISIBLE

            binding.editTextBurialName.setText(ApplicationClass.prefs.burialName)
            binding.editTextBurialTime.setText(ApplicationClass.prefs.burialTime)
        }

        binding.editTextNote.setText(ApplicationClass.prefs.note)

        // Urn Fragment 추가
        val urnFragment = UrnContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_urn_container, urnFragment)
            .commit()

        // Tablet Fragment 추가
        val tabletFragment = TabletContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_tablet_container, tabletFragment)
            .commit()
    }

    private fun setOnCheckedChangeListener() {
        val radioGroup = binding.radioGroup
        val layoutRadioCremation = binding.layoutRadioCremation
        val layoutRadioFuneral = binding.layoutRadioFuneral
        val layoutRadioBurial = binding.layoutRadioBurial

        // 라디오 버튼 선택 시 호출되는 리스너 설정
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // 선택된 라디오 버튼을 루트 뷰 내에서 찾습니다.
            val rootView = view // Fragment의 루트 뷰를 가져옵니다.
            val radioButton = rootView?.findViewById<RadioButton>(checkedId)

            // 선택된 라디오 버튼에 따라 레이아웃 변경
            when (radioButton?.id) {
                R.id.radioButton1 -> {
                    ApplicationClass.prefs.selectedLocation = "화장장"
                    layoutRadioCremation.visibility = View.VISIBLE
                    layoutRadioFuneral.visibility = View.GONE
                    layoutRadioBurial.visibility = View.GONE
                }
                R.id.radioButton2 -> {
                    ApplicationClass.prefs.selectedLocation = "장례식장"
                    layoutRadioCremation.visibility = View.GONE
                    layoutRadioFuneral.visibility = View.VISIBLE
                    layoutRadioBurial.visibility = View.GONE
                }
                R.id.radioButton3 -> {
                    ApplicationClass.prefs.selectedLocation = "장지"
                    layoutRadioCremation.visibility = View.GONE
                    layoutRadioFuneral.visibility = View.GONE
                    layoutRadioBurial.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setOnClickListeners() {
        binding.buttonResultFragment.setOnClickListener {
            if(checkInput())
                findNavController().navigate(R.id.action_orderFragment_to_resultFragment)
        }
    }

    private fun setOnItemSelectedListener() {
        val spinnerCremationArea = binding.spinnerCremationArea
        spinnerCremationArea.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.cremationArea = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
    }

    private fun addTextChangedListener() {
        // 발주자 정보
        binding.editTextLeaderName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 이전 텍스트 변경 이벤트
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // 텍스트 변경 이벤트
                ApplicationClass.prefs.leaderName =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
                println("ApplicationClass.prefs.leaderName :" + ApplicationClass.prefs.leaderName)
            }

            override fun afterTextChanged(s: Editable?) {
                // 텍스트 변경 후 이벤트
            }
        })

        val editTextLeaderTel = binding.editTextLeaderTel
        editTextLeaderTel.addTextChangedListener(PhoneNumberTextWatcher(editTextLeaderTel))
        editTextLeaderTel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.leaderTel =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
                println("ApplicationClass.prefs.leaderTel :" + ApplicationClass.prefs.leaderTel)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.editTextLeaderDepartment.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.leaderDepartment =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
                println("ApplicationClass.prefs.leaderDepartment :" + ApplicationClass.prefs.leaderDepartment)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.checkboxLeader.setOnCheckedChangeListener { buttonView, isChecked ->
            ApplicationClass.prefs.saveInfo = isChecked
            println("ApplicationClass.prefs.saveInfo :" + ApplicationClass.prefs.saveInfo)
            if (!ApplicationClass.prefs.saveInfo) {
                ApplicationClass.prefs.resetPreferencesLeader()
            }
        }

        // 상주 정보
        binding.editTextClientName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.clientName =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        val editTextClientTel = binding.editTextClientTel
        editTextClientTel.addTextChangedListener(PhoneNumberTextWatcher(editTextClientTel))
        editTextClientTel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.clientTel =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        // 발주 장소
//        ApplicationClass.prefs.selectedLocation = binding.editText.toString()
        // 화장장
//        ApplicationClass.prefs.cremationArea = binding.editText.toString()
        val editTextCremationTime = binding.editTextCremationTime
        editTextCremationTime.addTextChangedListener(DateTimeTextWatcher(editTextCremationTime))
        editTextCremationTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.cremationTime =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        // 장례식장
        binding.editTextFuneralName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.funeralName =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        binding.editTextFuneralNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.funeralNumber =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        val editTextFuneralTime = binding.editTextFuneralTime
        editTextFuneralTime.addTextChangedListener(DateTimeTextWatcher(editTextFuneralTime))
        editTextFuneralTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.funeralTime =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        // 장지
        binding.editTextBurialName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.burialName =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        val editTextBurialTime = binding.editTextBurialTime
        editTextBurialTime.addTextChangedListener(DateTimeTextWatcher(editTextBurialTime))
        editTextBurialTime.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.burialTime =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        // 메모
        binding.editTextNote.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                ApplicationClass.prefs.note =
                    s?.toString() ?: "" // editText의 텍스트를 가져오고 null이면 빈 문자열로 처리
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun observer() {

    }

    private fun checkInput(): Boolean {
        // 모든게 초기값인지 체크
        if(!checkOrderInput())
            return false;

        if(ApplicationClass.prefs.selectedUrnType == "선택안함"  && ApplicationClass.prefs.selectedTabletType == "선택안함"){
            toast("유골함, 위패 중 하나는 선택해주세요.")
            return false
        }

        // 유골함
        if(ApplicationClass.prefs.selectedUrnType != "선택안함"){
            if(!checkUrnInput()) {
                // 고인 정보
                val name1 = ApplicationClass.prefs.name1.toString()
                if (!(name1.length in 2..4 && name1.matches(hanglePattern))) {
                    toast("고인 명을 한글 2~4글자로 올바르게 입력해주세요.")
                    return false
                }
                val date1 = ApplicationClass.prefs.date1.toString()
                if (!date1.matches(datePattern)) {
                    toast("생년월일을 1900-01-01 형태로 올바르게 입력해주세요.")
                    return false
                }
                // date1Type 생략
                val date2 = ApplicationClass.prefs.date2.toString()
                if (!date2.matches(datePattern)) {
                    toast("사망월일일을 2023-09-01 형태로 올바르게 입력해주세요.")
                    return false
                }
                // date2Type 생략
                // 종교 생략
                // 직분, 세례명, 법명
                if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
                    val name2 = ApplicationClass.prefs.name2.toString()
                    if (!(name2.length in 2..4 && name2.matches(hanglePattern))) {
                        toast("직분을 한글 2~4글자로 올바르게 입력해주세요.")
                        return false
                    }
                }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
                    val name2 = ApplicationClass.prefs.name2.toString()
                    if (!(name2.length in 2..4 && name2.matches(hanglePattern))) {
                        toast("법명을 한글 2~4글자로 올바르게 입력해주세요.")
                        return false
                    }
                }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
                    val name2 = ApplicationClass.prefs.name2.toString()
                    if (!(name2.length in 2..6 && name2.matches(hanglePattern))) {
                        toast("세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                        return false
                    }
                }
                // 합골
                if(ApplicationClass.prefs.selectedUrnType!!.contains("합골")){
                    // 고인 정보
                    val boneName1 = ApplicationClass.prefs.boneName1.toString()
                    val boneDate1 = ApplicationClass.prefs.boneDate1.toString()
                    val boneDate2 = ApplicationClass.prefs.boneDate2.toString()
                    val boneName2 = ApplicationClass.prefs.boneName2.toString()

                    if(!(boneName1 == "" && boneDate1 == "" && boneDate2 == "" && boneName2 == "")){
                        if (!(boneName1.length in 2..4 && boneName1.matches(hanglePattern))) {
                            toast("(합골)고인 명을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                        if (!boneDate1.matches(datePattern)) {
                            toast("(합골)생년월일을 1900-01-01 형태로 올바르게 입력해주세요.")
                            return false
                        }
                        // date1Type 생략
                        if (!boneDate2.matches(datePattern)) {
                            toast("(합골)사망월일을 2023-09-01 형태로 올바르게 입력해주세요.")
                            return false
                        }
                        // date2Type 생략
                        // 종교 생략
                        // 직분, 세례명, 법명
                        if((ApplicationClass.prefs.boneEngraveType == "기독교" || ApplicationClass.prefs.boneEngraveType == "순복음") && (ApplicationClass.prefs.boneEngraveType2 == "기본")) {
                            if (!(boneName2.length in 2..4 && boneName2.matches(hanglePattern))) {
                                toast("(합골)직분을 한글 2~4글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }else if(ApplicationClass.prefs.boneEngraveType == "불교" && ApplicationClass.prefs.boneEngraveType2 == "법명") {
                            if (!(boneName2.length in 2..4 && boneName2.matches(hanglePattern))) {
                                toast("(합골)법명을 한글 2~4글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }else if(ApplicationClass.prefs.boneEngraveType == "천주교" && ApplicationClass.prefs.boneEngraveType2 == "기본") {
                            if (!(boneName2.length in 2..6 && boneName2.matches(hanglePattern))) {
                                toast("(합골)세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }
                    }
                }
            }
        }

        // 위패
        if(ApplicationClass.prefs.selectedTabletType != "선택안함"){
            if(!checkTabletInput()){
                val name2 = ApplicationClass.prefs.name2.toString()
                val name3 = ApplicationClass.prefs.name3.toString()
                val tabletType = ApplicationClass.prefs.tabletType.toString()

                if(tabletType.contains("문구")){
                    if (!(name3.length in 1..20 && name3.matches(hanglePattern))) {
                        toast("문구 내용을 한글 1~20글자로 올바르게 입력해주세요.")
                        return false
                    }
                }else if(!tabletType.contains("본관") && !tabletType.contains("사진")){
                    if (!(name3.length in 2..4 && name3.matches(hanglePattern))) {
                        toast("이름을 한글 2~4글자로 올바르게 입력해주세요.")
                        return false
                    }
                    // 직분, 세례명, 법명
                    if((ApplicationClass.prefs.religion == "기독교")) {
                        if (!(name2.length in 2..4 && name2.matches(hanglePattern))) {
                            toast("직분을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }
                    else if(ApplicationClass.prefs.religion == "천주교") {
                        if (!(name2.length in 2..6 && name2.matches(hanglePattern))) {
                            toast("세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }
                } else if(tabletType.contains("본관")){
                    if(ApplicationClass.prefs.religion == "일반" || ApplicationClass.prefs.religion == "법명") {
                        if (!(name3.length in 7..9 && name3.matches(hanglePattern))) {
                            toast("본관내용을 한글 7~9글자로 올바르게 입력해주세요.")
                            return false
                        }
                    } else if((ApplicationClass.prefs.religion == "기독교")) {
                        if (!(name2.length in 2..4 && name2.matches(hanglePattern))) {
                            toast("직분을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                        if (!(name3.length in 5..7 && name3.matches(hanglePattern))) {
                            toast("본관내용을 한글 5~7글자로 올바르게 입력해주세요.")
                            return false
                        }
                    } else if(ApplicationClass.prefs.religion == "천주교") {
                        if (!(name2.length in 2..6 && name2.matches(hanglePattern))) {
                            toast("세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                            return false
                        }
                        if (!(name3.length in 5..7 && name3.matches(hanglePattern))) {
                            toast("본관내용을 한글 5~7글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }
                }
            }
        }

        // 특이 사항 (30자 이내)
        val note = ApplicationClass.prefs.note.toString()
        if (note.length > 30) {
            toast("특이 사항을 30글자 이내로 입력해주세요.")
            return false
        }
        return true
    }
    // 모든게 초기값인지 체크
    private fun checkOrderInput(): Boolean {
        // 발주자 정보 (필수)
        val leaderName = ApplicationClass.prefs.leaderName.toString()
        if (!(leaderName.length in 2..4 && leaderName.matches(hanglePattern))) {
            toast("발주자 명을 한글 2~4글자로 올바르게 입력해주세요.")
            return false
        }
        val leaderTel = ApplicationClass.prefs.leaderTel.toString()
        if (!leaderTel.matches(telPattern)) {
            toast("발주자 전화번호를 010-1234-5678 형태로 올바르게 입력해주세요.")
            return false
        }
        val leaderDepartment = ApplicationClass.prefs.leaderDepartment.toString()
        if (!(leaderDepartment.length in 1 .. 10)) {
            toast("발주자 소속을 10글자 이내로 입력해주세요.")
            return false
        }
        // 상주 정보 (선택)
        val clientName = ApplicationClass.prefs.clientName.toString()
        val clientTel = ApplicationClass.prefs.clientTel.toString()
        if(clientName != "" || clientTel != "") {
            if (!(clientName.length in 2..4 && clientName.matches(hanglePattern))) {
                toast("상주 명을 한글 2~4글자로 올바르게 입력해주세요.")
                return false
            }
            if (!clientTel.matches(telPattern)) {
                toast("상주 전화번호를 010-1234-5678 형태로 올바르게 입력해주세요.")
                return false
            }
        }
/**        // 발주 장소별 (필수)
        val selectedLocation = ApplicationClass.prefs.selectedLocation.toString()
        if(selectedLocation == "화장장"){
//            val cremationArea = ApplicationClass.prefs.cremationArea.toString()
//            if (cremationArea.isNotEmpty()) {
//                return false
//            }
            val cremationTime = ApplicationClass.prefs.cremationTime.toString()
            if (!cremationTime.matches(dateTimePattern)) {
                toast("화장 시간을 2023-09-01 00:00 형태로 올바르게 입력해주세요.")
                return false
            }
        } else if(selectedLocation == "장례식장"){
            val funeralName = ApplicationClass.prefs.funeralName.toString()
            if (!(funeralName.length <= 10 && funeralName.matches(hanglePattern))) {
                toast("장례식장명을 한글 10글자 이내로 올바르게 입력해주세요.")
                return false
            }
            val funeralNumber = ApplicationClass.prefs.funeralNumber.toString()
            if (!(leaderDepartment.length in 1 .. 8)) {
                toast("호실을 8글자 이내로 입력해주세요.")
                return false
            }
            val funeralTime = ApplicationClass.prefs.funeralTime.toString()
            if (!funeralTime.matches(dateTimePattern)) {
                toast("함 도착 시간을 2023-09-01 00:00 형태로 올바르게 입력해주세요.")
                return false
            }
        } else if(selectedLocation == "장지"){
            val burialName = ApplicationClass.prefs.burialName.toString()
            if (!(leaderDepartment.length in 1 .. 8)) {
                toast("장지 명을 8글자 이내로 입력해주세요.")
                return false
            }
            val burialTime = ApplicationClass.prefs.burialTime.toString()
            if (!burialTime.matches(dateTimePattern)) {
                toast("함 도착 시간을 2023-09-01 00:00 형태로 올바르게 입력해주세요.")
                return false
            }
        }
*/
        // 특이 사항 생략(선택)
        return true
    }
    private fun checkUrnInput(): Boolean {
        // 유골함
        if(ApplicationClass.prefs.selectedUrnType != "선택안함") {
            // 고인 정보
            val name1 = ApplicationClass.prefs.name1.toString()
            if (name1 != "") {
                return false
            }
            val date1 = ApplicationClass.prefs.date1.toString()
            if (date1 != "") {
                return false
            }
            // date1Type 생략
            val date2 = ApplicationClass.prefs.date2.toString()
            if (date2 != "") {
                return false
            }
            // date2Type 생략
            // 종교 생략
            // 직분, 세례명, 법명
            if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
                val name2 = ApplicationClass.prefs.name2.toString()
                if (name2 != "") {
                    return false
                }
            }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
                val name2 = ApplicationClass.prefs.name2.toString()
                if (name2 != "") {
                    return false
                }
            }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
                val name2 = ApplicationClass.prefs.name2.toString()
                if (name2 != "") {
                    return false
                }
            }
            // 합골
            if(ApplicationClass.prefs.selectedUrnType!!.contains("합골")){
                // 고인 정보
                val boneName1 = ApplicationClass.prefs.boneName1.toString()
                if (boneName1 != "") {
                    return false
                }
                val boneDate1 = ApplicationClass.prefs.boneDate1.toString()
                if (boneDate1 != "") {
                    return false
                }
                // date1Type 생략
                val boneDate2 = ApplicationClass.prefs.boneDate2.toString()
                if (boneDate2 != "") {
                    return false
                }
                // date2Type 생략
                // 종교 생략
                // 직분, 세례명, 법명
                if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
                    val boneName2 = ApplicationClass.prefs.boneName2.toString()
                    if (boneName2 != "") {
                        return false
                    }
                }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
                    val boneName2 = ApplicationClass.prefs.boneName2.toString()
                    if (boneName2 != "") {
                        return false
                    }
                }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
                    val boneName2 = ApplicationClass.prefs.boneName2.toString()
                    if (boneName2 != "") {
                        return false
                    }
                }
            }
        }
        return true
    }
    private fun checkTabletInput(): Boolean {
        // 위패
        if(ApplicationClass.prefs.selectedTabletType != "선택안함") {
            val name2 = ApplicationClass.prefs.name2
            val name3 = ApplicationClass.prefs.name3
            if (!(name2 == "" && name3 == "")) {
                return false
            }
        }
        return true
    }
}