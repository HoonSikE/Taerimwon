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

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {
    val authViewModel: AuthViewModel by viewModels()
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

        binding.editTextNote.setText(ApplicationClass.prefs.note)
//        }

        // 화장장 셀렉트박스
        // Spinner에 표시할 데이터 목록
//        val items = listOf("서울/경기/인천", "Item 2", "Item 3", "Item 4")

        val spinner = binding.spinnerCremationArea
        val cremationAreas = resources.getStringArray(R.array.cremationAreas).toMutableList()

        // 비활성화할 아이템
        val itemToDisable = "서울/경기/인천"
        val indexOfItemToDisable = cremationAreas.indexOf(itemToDisable)
        if (indexOfItemToDisable != -1) {
            cremationAreas.removeAt(indexOfItemToDisable)
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cremationAreas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

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
}