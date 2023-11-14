package com.example.taerimwon.ui.order

import android.annotation.SuppressLint
import android.os.Build
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.databinding.FragmentOrderBinding
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.data.dto.tablet.TabletItem
import com.example.taerimwon.data.dto.urn.UrnItem
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import com.example.taerimwon.ui.order.pyeongjang.PyeongjangContainerFragment
import com.example.taerimwon.ui.order.tablet.TabletContainerFragment
import com.example.taerimwon.ui.order.urn.EngraveTypeAdapter
import com.example.taerimwon.ui.order.urn.UrnContainerFragment
import com.example.taerimwon.ui.urnlist.UrnListAdapter
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

    private lateinit var searchList: MutableList<String>
    private lateinit var searchList2: MutableList<String>
    private lateinit var searchList3: MutableList<String>
    private lateinit var urnList: MutableList<UrnItem>

    private lateinit var urnListAdapter: UrnListAdapter


    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
        setOnItemSelectedListener()
        addTextChangedListener()
        observer()
    }

    private fun initAdapter(){
        urnListAdapter = UrnListAdapter(requireContext())

        var listManager = GridLayoutManager(requireContext(), 2)

        binding.recyclerviewUrnList.apply {
            adapter = urnListAdapter
            layoutManager = listManager
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList = mutableListOf()
        settingUrnList()
        urnListAdapter.updateList(urnList)
    }

    private fun initData() {
        if(ApplicationClass.prefs.selectedUrnType != "선택안함"
            || ApplicationClass.prefs.selectedTabletType != "선택안함"
            || ApplicationClass.prefs.selectedPyeongjangType != "선택안함"){
            val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
            val blackColor = ContextCompat.getColor(requireContext(), R.color.black)

            // style 변경
            binding.menuButton1.setTextColor(blackColor)
            // background 변경
            binding.menuButton1.setBackgroundResource(R.drawable.button_gray)

            binding.menuButton2.setTextColor(whiteColor)
            binding.menuButton2.setBackgroundResource(R.drawable.button_primary)

            binding.menuButton3.setTextColor(blackColor)
            binding.menuButton3.setBackgroundResource(R.drawable.button_gray)

            binding.layoutHome.visibility = View.GONE
            binding.layoutOrder.visibility = View.VISIBLE
            binding.layoutButtonOrder.visibility = View.VISIBLE
            binding.layoutUrnList.visibility = View.GONE
        }
//        authViewModel.getBlackList()
//        if (!ApplicationClass.prefs.authenticated)
//            findNavController().navigate(R.id.action_orderFragment_to_phoneAuthFragment)

        searchList = mutableListOf()
        searchList2 = mutableListOf()
        searchList3 = mutableListOf()
        settingList()
        settingList2()
        settingList3()

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

        val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)

        if(selectedLocation == "화장장"){
            // style 변경
            binding.button1.setTextColor(whiteColor)
            // background 변경
            binding.button1.setBackgroundResource(R.drawable.button_primary)

            binding.button2.setTextColor(blackColor)
            binding.button2.setBackgroundResource(R.drawable.button_gray)

            binding.button3.setTextColor(blackColor)
            binding.button3.setBackgroundResource(R.drawable.button_gray)

            layoutRadioCremation.visibility = View.VISIBLE
            layoutRadioFuneral.visibility = View.GONE
            layoutRadioBurial.visibility = View.GONE

            // 화장장 셀렉트박스
            // Spinner에 표시할 데이터 목록
            val cremationAreasArray = resources.getStringArray(R.array.cremationAreas)
            val cremationAreasList = mutableListOf(*cremationAreasArray) as ArrayList<String>
            binding.spinnerCremationArea.setSelection(cremationAreasList.indexOf(ApplicationClass.prefs.cremationArea))

            var cremationAreasArray2 = resources.getStringArray(R.array.cremationSeoul)

            when (ApplicationClass.prefs.cremationArea) {
                "서울/경기/인천" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationSeoul)
                    ApplicationClass.prefs.cremationArea2 = "서울시립승화원"
                }
                "충청도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationChungbuk)
                    ApplicationClass.prefs.cremationArea2 = "공주나래원"
                }
                "강원도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationGangwon)
                    ApplicationClass.prefs.cremationArea2 = "태백시화장장"
                }
                "대구광역시" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationDaegu)
                    ApplicationClass.prefs.cremationArea2 = "대구명복공원"
                }
                "광주광역시" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationGwangju)
                    ApplicationClass.prefs.cremationArea2 = "광주영락공원"
                }
                "부산광역시" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationBusan)
                    ApplicationClass.prefs.cremationArea2 = "부산영락공원"
                }
                "세종특별자치시" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationSejong)
                    ApplicationClass.prefs.cremationArea2 = "세종은하수공원"
                }
                "울산광역시" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationUlsan)
                    ApplicationClass.prefs.cremationArea2 = "울산하늘공원"
                }
                "전라남도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationJeonnam)
                    ApplicationClass.prefs.cremationArea2 = "국립소록도병원화장장"
                }
                "전라북도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationJeonbuk)
                    ApplicationClass.prefs.cremationArea2 = "군산시승화원"
                }
                "경상북도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationGyeongbuk)
                    ApplicationClass.prefs.cremationArea2 = "경주하늘마루"
                }
                "경상남도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationGyeongnam)
                    ApplicationClass.prefs.cremationArea2 = "고성군공설화장장"
                }
                "제주특별자치도" -> {
                    cremationAreasArray2 = resources.getStringArray(R.array.cremationJeju)
                    ApplicationClass.prefs.cremationArea2 = "제주양지공원"
                }
            }
            val cremationAreasList2 = mutableListOf(*cremationAreasArray2) as ArrayList<String>
            binding.spinnerCremationArea2.setSelection(cremationAreasList2.indexOf(ApplicationClass.prefs.cremationArea2))
            
            binding.editTextCremationTime.setText(ApplicationClass.prefs.cremationTime)
        }else if(selectedLocation == "장례식장"){
            // style 변경
            binding.button1.setTextColor(blackColor)
            // background 변경
            binding.button1.setBackgroundResource(R.drawable.button_gray)

            binding.button2.setTextColor(whiteColor)
            binding.button2.setBackgroundResource(R.drawable.button_primary)

            binding.button3.setTextColor(blackColor)
            binding.button3.setBackgroundResource(R.drawable.button_gray)

            layoutRadioCremation.visibility = View.GONE
            layoutRadioFuneral.visibility = View.VISIBLE
            layoutRadioBurial.visibility = View.GONE

            binding.editTextFuneralName.setText(ApplicationClass.prefs.funeralName)
            binding.editTextFuneralNumber.setText(ApplicationClass.prefs.funeralNumber)
            binding.editTextFuneralTime.setText(ApplicationClass.prefs.funeralTime)
        }else if(selectedLocation == "장지"){
            // style 변경
            binding.button1.setTextColor(blackColor)
            // background 변경
            binding.button1.setBackgroundResource(R.drawable.button_gray)

            binding.button2.setTextColor(blackColor)
            binding.button2.setBackgroundResource(R.drawable.button_gray)

            binding.button3.setTextColor(whiteColor)
            binding.button3.setBackgroundResource(R.drawable.button_primary)

            layoutRadioCremation.visibility = View.GONE
            layoutRadioFuneral.visibility = View.GONE
            layoutRadioBurial.visibility = View.VISIBLE

            binding.editTextBurialName.setText(ApplicationClass.prefs.burialName)
            binding.editTextBurialTime.setText(ApplicationClass.prefs.burialTime)
        }

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

        // Tablet Fragment 추가
        val pyeongjangFragment = PyeongjangContainerFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_pyeongjang_container, pyeongjangFragment)
            .commit()
    }

    private fun setOnClickListeners() {
        val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
        val blackColor = ContextCompat.getColor(requireContext(), R.color.black)

        binding.menuButton1.setOnClickListener {
            // style 변경
            binding.menuButton1.setTextColor(whiteColor)
            // background 변경
            binding.menuButton1.setBackgroundResource(R.drawable.button_primary)

            binding.menuButton2.setTextColor(blackColor)
            binding.menuButton2.setBackgroundResource(R.drawable.button_gray)

            binding.menuButton3.setTextColor(blackColor)
            binding.menuButton3.setBackgroundResource(R.drawable.button_gray)

            binding.layoutHome.visibility = View.VISIBLE
            binding.layoutOrder.visibility = View.GONE
            binding.layoutButtonOrder.visibility = View.GONE
            binding.layoutUrnList.visibility = View.GONE
        }
        binding.menuButton2.setOnClickListener {
            // style 변경
            binding.menuButton1.setTextColor(blackColor)
            // background 변경
            binding.menuButton1.setBackgroundResource(R.drawable.button_gray)

            binding.menuButton2.setTextColor(whiteColor)
            binding.menuButton2.setBackgroundResource(R.drawable.button_primary)

            binding.menuButton3.setTextColor(blackColor)
            binding.menuButton3.setBackgroundResource(R.drawable.button_gray)

            binding.layoutHome.visibility = View.GONE
            binding.layoutOrder.visibility = View.VISIBLE
            binding.layoutButtonOrder.visibility = View.VISIBLE
            binding.layoutUrnList.visibility = View.GONE
        }
        binding.menuButton3.setOnClickListener {
            // style 변경
            binding.menuButton1.setTextColor(blackColor)
            // background 변경
            binding.menuButton1.setBackgroundResource(R.drawable.button_gray)

            binding.menuButton2.setTextColor(blackColor)
            binding.menuButton2.setBackgroundResource(R.drawable.button_gray)

            binding.menuButton3.setTextColor(whiteColor)
            binding.menuButton3.setBackgroundResource(R.drawable.button_primary)

            binding.layoutHome.visibility = View.GONE
            binding.layoutOrder.visibility = View.GONE
            binding.layoutButtonOrder.visibility = View.GONE
            binding.layoutUrnList.visibility = View.VISIBLE
        }

        binding.buttonOrderToOrderFragment.setOnClickListener{
            ApplicationClass.prefs.resetPreferences()
            findNavController().navigate(R.id.action_orderFragment_to_orderFragment)
        }
        binding.buttonResultFragment.setOnClickListener {
            if(checkInput())
                findNavController().navigate(R.id.action_orderFragment_to_resultFragment)
        }
        val layoutRadioCremation = binding.layoutRadioCremation
        val layoutRadioFuneral = binding.layoutRadioFuneral
        val layoutRadioBurial = binding.layoutRadioBurial
        binding.button1.setOnClickListener {
            // style 변경
            binding.button1.setTextColor(whiteColor)
            // background 변경
            binding.button1.setBackgroundResource(R.drawable.button_primary)

            binding.button2.setTextColor(blackColor)
            binding.button2.setBackgroundResource(R.drawable.button_gray)

            binding.button3.setTextColor(blackColor)
            binding.button3.setBackgroundResource(R.drawable.button_gray)

            ApplicationClass.prefs.selectedLocation = "화장장"
            layoutRadioCremation.visibility = View.VISIBLE
            layoutRadioFuneral.visibility = View.GONE
            layoutRadioBurial.visibility = View.GONE
        }
        binding.button2.setOnClickListener {
            // style 변경
            binding.button1.setTextColor(blackColor)
            // background 변경
            binding.button1.setBackgroundResource(R.drawable.button_gray)

            binding.button2.setTextColor(whiteColor)
            binding.button2.setBackgroundResource(R.drawable.button_primary)

            binding.button3.setTextColor(blackColor)
            binding.button3.setBackgroundResource(R.drawable.button_gray)

            ApplicationClass.prefs.selectedLocation = "장례식장"
            layoutRadioCremation.visibility = View.GONE
            layoutRadioFuneral.visibility = View.VISIBLE
            layoutRadioBurial.visibility = View.GONE
        }
        binding.button3.setOnClickListener {
            // style 변경
            binding.button1.setTextColor(blackColor)
            // background 변경
            binding.button1.setBackgroundResource(R.drawable.button_gray)

            binding.button2.setTextColor(blackColor)
            binding.button2.setBackgroundResource(R.drawable.button_gray)

            binding.button3.setTextColor(whiteColor)
            binding.button3.setBackgroundResource(R.drawable.button_primary)

            ApplicationClass.prefs.selectedLocation = "장지"
            layoutRadioCremation.visibility = View.GONE
            layoutRadioFuneral.visibility = View.GONE
            layoutRadioBurial.visibility = View.VISIBLE
        }
    }

    private fun setOnItemSelectedListener() {
        val spinnerCremationArea = binding.spinnerCremationArea
        spinnerCremationArea.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.cremationArea = parent?.getItemAtPosition(position).toString() ?: ""

                var cremationAreasArray2 = resources.getStringArray(R.array.cremationSeoul)

                when (ApplicationClass.prefs.cremationArea) {
                    "서울/경기/인천" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationSeoul)
                        ApplicationClass.prefs.cremationArea2 = "서울시립승화원"
                    }
                    "충청도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationChungbuk)
                        ApplicationClass.prefs.cremationArea2 = "공주나래원"
                    }
                    "강원도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationGangwon)
                        ApplicationClass.prefs.cremationArea2 = "태백시화장장"
                    }
                    "대구광역시" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationDaegu)
                        ApplicationClass.prefs.cremationArea2 = "대구명복공원"
                    }
                    "광주광역시" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationGwangju)
                        ApplicationClass.prefs.cremationArea2 = "광주영락공원"
                    }
                    "부산광역시" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationBusan)
                        ApplicationClass.prefs.cremationArea2 = "부산영락공원"
                    }
                    "세종특별자치시" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationSejong)
                        ApplicationClass.prefs.cremationArea2 = "세종은하수공원"
                    }
                    "울산광역시" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationUlsan)
                        ApplicationClass.prefs.cremationArea2 = "울산하늘공원"
                    }
                    "전라남도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationJeonnam)
                        ApplicationClass.prefs.cremationArea2 = "국립소록도병원화장장"
                    }
                    "전라북도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationJeonbuk)
                        ApplicationClass.prefs.cremationArea2 = "군산시승화원"
                    }
                    "경상북도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationGyeongbuk)
                        ApplicationClass.prefs.cremationArea2 = "경주하늘마루"
                    }
                    "경상남도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationGyeongnam)
                        ApplicationClass.prefs.cremationArea2 = "고성군공설화장장"
                    }
                    "제주특별자치도" -> {
                        cremationAreasArray2 = resources.getStringArray(R.array.cremationJeju)
                        ApplicationClass.prefs.cremationArea2 = "제주양지공원"
                    }
                }
                val cremationAreasList2 = mutableListOf(*cremationAreasArray2) as ArrayList<String>
                val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, cremationAreasList2)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinnerCremationArea2.adapter = adapter

                binding.spinnerCremationArea2.setSelection(0)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })

        val spinnerCremationArea2 = binding.spinnerCremationArea2
        spinnerCremationArea2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.cremationArea2 = parent?.getItemAtPosition(position).toString() ?: ""
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
        editTextCremationTime.addTextChangedListener(DateTimeTextWatcher(editTextCremationTime))

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
        editTextFuneralTime.addTextChangedListener(DateTimeTextWatcher(editTextFuneralTime))

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
        editTextBurialTime.addTextChangedListener(DateTimeTextWatcher(editTextBurialTime))
    }

    private fun observer() {

    }

    private fun checkInput(): Boolean {
        // 모든게 초기값인지 체크
        if(!checkOrderInput())
            return false;

        if(ApplicationClass.prefs.selectedUrnType == "선택안함"  && ApplicationClass.prefs.selectedTabletType == "선택안함" && ApplicationClass.prefs.selectedPyeongjangType == "선택안함"){
            toast("유골함, 위패, 평장 중 하나는 선택해주세요.")
            return false
        }

        // 유골함
        if(ApplicationClass.prefs.selectedUrnType != "선택안함"){
            if(!checkUrnInput()) {
                val containsString = searchList.any { it == ApplicationClass.prefs.selectedUrnName }

                if (!containsString) {
                    toast("유골함 종류를 골라주세요.")
                    return false
                }
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
                if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본" || ApplicationClass.prefs.engraveType2 == "年月日")) {
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
                }else if(ApplicationClass.prefs.engraveType == "천주교" && (ApplicationClass.prefs.engraveType2 == "기본" || ApplicationClass.prefs.engraveType2 == "年月日")) {
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
                            toast("[합골]고인 명을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                        if (!boneDate1.matches(datePattern)) {
                            toast("[합골]생년월일을 1900-01-01 형태로 올바르게 입력해주세요.")
                            return false
                        }
                        // date1Type 생략
                        if (!boneDate2.matches(datePattern)) {
                            toast("[합골]사망월일을 2023-09-01 형태로 올바르게 입력해주세요.")
                            return false
                        }
                        // date2Type 생략
                        // 종교 생략
                        // 직분, 세례명, 법명
                        if((ApplicationClass.prefs.boneEngraveType == "기독교" || ApplicationClass.prefs.boneEngraveType == "순복음") && (ApplicationClass.prefs.boneEngraveType2 == "기본"  || ApplicationClass.prefs.boneEngraveType2 == "年月日")) {
                            if (!(boneName2.length in 2..4 && boneName2.matches(hanglePattern))) {
                                toast("[합골]직분을 한글 2~4글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }else if(ApplicationClass.prefs.boneEngraveType == "불교" && ApplicationClass.prefs.boneEngraveType2 == "법명") {
                            if (!(boneName2.length in 2..4 && boneName2.matches(hanglePattern))) {
                                toast("[합골]법명을 한글 2~4글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }else if(ApplicationClass.prefs.boneEngraveType == "천주교" && (ApplicationClass.prefs.boneEngraveType2 == "기본" || ApplicationClass.prefs.boneEngraveType2 == "年月日")) {
                            if (!(boneName2.length in 2..6 && boneName2.matches(hanglePattern))) {
                                toast("[합골]세례명을 한글 2~6글자로 올바르게 입력해주세요.")
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
                val containsString = searchList2.any { it == ApplicationClass.prefs.selectedTabletName }
                if (!containsString) {
                    toast("위패 종류를 골라주세요.")
                    return false
                }

                val tabletName2 = ApplicationClass.prefs.tabletName2.toString()
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
                        if (!(tabletName2.length in 2..4 && tabletName2.matches(hanglePattern))) {
                            toast("직분을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }
                    else if(ApplicationClass.prefs.religion == "천주교") {
                        if (!(tabletName2.length in 2..6 && tabletName2.matches(hanglePattern))) {
                            toast("세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }
                } else if(tabletType.contains("본관")){
                    if(ApplicationClass.prefs.religion == "일반" || ApplicationClass.prefs.religion == "불교") {
                        if (!(name3.length in 7..9 && name3.matches(hanglePattern))) {
                            toast("본관내용을 한글 7~9글자로 올바르게 입력해주세요.")
                            return false
                        }
                    } else if((ApplicationClass.prefs.religion == "기독교")) {
                        if (!(tabletName2.length in 2..4 && tabletName2.matches(hanglePattern))) {
                            toast("직분을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                        if (!(name3.length in 5..7 && name3.matches(hanglePattern))) {
                            toast("본관내용을 한글 5~7글자로 올바르게 입력해주세요.")
                            return false
                        }
                    } else if(ApplicationClass.prefs.religion == "천주교") {
                        if (!(tabletName2.length in 2..6 && tabletName2.matches(hanglePattern))) {
                            toast("세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                            return false
                        }
                        if (!(name3.length in 5..7 && name3.matches(hanglePattern))) {
                            toast("본관내용을 한글 5~7글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }
                }

                // 위패 합골
                if(ApplicationClass.prefs.boneSelectedTabletType != "선택안함"){
                    val containsString = searchList2.any { it == ApplicationClass.prefs.selectedTabletName2 }
                    if (!containsString) {
                        toast("[합골]위패 종류를 골라주세요.")
                        return false
                    }

                    val boneTabletName2 = ApplicationClass.prefs.boneTabletName2.toString()
                    val boneName3 = ApplicationClass.prefs.boneName3.toString()
                    val boneTabletType = ApplicationClass.prefs.boneTabletType.toString()

                    if(boneTabletType.contains("문구")){
                        if (!(boneName3.length in 1..20 && boneName3.matches(hanglePattern))) {
                            toast("[합골]문구 내용을 한글 1~20글자로 올바르게 입력해주세요.")
                            return false
                        }
                    }else if(!boneTabletType.contains("본관") && !boneTabletType.contains("사진")){
                        if (!(boneName3.length in 2..4 && boneName3.matches(hanglePattern))) {
                            toast("[합골]이름을 한글 2~4글자로 올바르게 입력해주세요.")
                            return false
                        }
                        // 직분, 세례명, 법명
                        if((ApplicationClass.prefs.boneReligion == "기독교")) {
                            if (!(boneTabletName2.length in 2..4 && boneTabletName2.matches(hanglePattern))) {
                                toast("[합골]직분을 한글 2~4글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }
                        else if(ApplicationClass.prefs.boneReligion == "천주교") {
                            if (!(boneTabletName2.length in 2..6 && boneTabletName2.matches(hanglePattern))) {
                                toast("[합골]세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }
                    } else if(boneTabletType.contains("본관")){
                        if(ApplicationClass.prefs.boneReligion == "일반" || ApplicationClass.prefs.boneReligion == "불교") {
                            if (!(boneName3.length in 7..9 && boneName3.matches(hanglePattern))) {
                                toast("[합골]본관내용을 한글 7~9글자로 올바르게 입력해주세요.")
                                return false
                            }
                        } else if((ApplicationClass.prefs.boneReligion == "기독교")) {
                            if (!(boneTabletName2.length in 2..4 && boneTabletName2.matches(hanglePattern))) {
                                toast("[합골]직분을 한글 2~4글자로 올바르게 입력해주세요.")
                                return false
                            }
                            if (!(boneName3.length in 5..7 && boneName3.matches(hanglePattern))) {
                                toast("[합골]본관내용을 한글 5~7글자로 올바르게 입력해주세요.")
                                return false
                            }
                        } else if(ApplicationClass.prefs.boneReligion == "천주교") {
                            if (!(boneTabletName2.length in 2..6 && boneTabletName2.matches(hanglePattern))) {
                                toast("[합골]세례명을 한글 2~6글자로 올바르게 입력해주세요.")
                                return false
                            }
                            if (!(boneName3.length in 5..7 && boneName3.matches(hanglePattern))) {
                                toast("[합골]본관내용을 한글 5~7글자로 올바르게 입력해주세요.")
                                return false
                            }
                        }
                    }
                }
            }
        }

        // 평장
        if(ApplicationClass.prefs.selectedPyeongjangType != "선택안함"){
            if(!checkPyeongjangInput()) {
                val containsString = searchList3.any { it == ApplicationClass.prefs.selectedPyeongjangName }
                if (!containsString) {
                    toast("평장 종류를 골라주세요.")
                    return false
                }
                // 추가
                if(ApplicationClass.prefs.selectedPyeongjangType2 != "선택안함"){
                    val containsString2 = searchList3.any { it == ApplicationClass.prefs.selectedPyeongjangName2 }
                    if (!containsString2) {
                        toast("평장[추가] 종류를 골라주세요.")
                        return false
                    }
                }
            }
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
        // 발주 장소별 (필수)
        val selectedLocation = ApplicationClass.prefs.selectedLocation.toString()
        if(selectedLocation == "화장장"){
//            val cremationArea = ApplicationClass.prefs.cremationArea.toString()
//            if (cremationArea.isNotEmpty()) {
//                return false
//            }
            val cremationTime = ApplicationClass.prefs.cremationTime.toString()
//            if (!cremationTime.matches(dateTimePattern)) {
//                toast("화장 시간을 2023-09-01 00:00 형태로 올바르게 입력해주세요.")
//                return false
//            }
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

        // 특이 사항 생략(선택)
        return true
    }
    private fun checkUrnInput(): Boolean {
        // 유골함
        if(ApplicationClass.prefs.selectedUrnType != "선택안함") {
            val containsString = searchList.any { it == ApplicationClass.prefs.selectedUrnName }
            if (!containsString) {
                return false
            }
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
            val containsString = searchList2.any { it == ApplicationClass.prefs.selectedTabletName }
            if (!containsString) {
                return false
            }
            val tabletName2 = ApplicationClass.prefs.tabletName2
            val name3 = ApplicationClass.prefs.name3
            if (!(tabletName2 == "" && name3 == "")) {
                return false
            }
        }
        // 합골
        if(ApplicationClass.prefs.boneSelectedTabletType != "선택안함") {
            val containsString = searchList2.any { it == ApplicationClass.prefs.selectedTabletName2 }
            if (!containsString) {
                return false
            }
            val boneTabletName2 = ApplicationClass.prefs.boneTabletName2
            val boneName3 = ApplicationClass.prefs.boneName3
            if (!(boneTabletName2 == "" && boneName3 == "")) {
                return false
            }
        }
        return true
    }
    private fun checkPyeongjangInput(): Boolean {
        // 평장
        if(ApplicationClass.prefs.selectedPyeongjangType != "선택안함") {
            val containsString = searchList3.any { it == ApplicationClass.prefs.selectedPyeongjangName }
            if (!containsString) {
                return false
            }
            // 추가
            if(ApplicationClass.prefs.selectedPyeongjangType2 != "선택안함"){
                val containsString2 = searchList3.any { it == ApplicationClass.prefs.selectedPyeongjangName2 }
                if (!containsString2) {
                    return false
                }
            }
        }
        return true
    }
    private fun settingList() {
        searchList.add("미정")
//        searchList.add("기본")
//        searchList.add("기본(검정)")
        
        // 1. 일반 밀봉진공함
        searchList.add("도원기독교 DW-3 4010")
        searchList.add("도원불교 DW-4 4010")
        searchList.add("도원천주교 DW-5 4010")
        searchList.add("도원칼라난 DW-2 4010")
        searchList.add("도원칼라송학 DW-1 4010")
        searchList.add("도화청꽃 DH-4 4010")
        searchList.add("도화홍꽃 DH-5 4010")
        searchList.add("소담난 SDN-2 4008")
        searchList.add("소담송학 SDS-1 4008")

        // 2. 일반함
        searchList.add("매화조각민트 MH-2 2906")
        searchList.add("매화조각핑크 MH-1 2906")
        searchList.add("매화조각화이트 MH-3 2906")
        searchList.add("원통난 WT-1 3307")
        searchList.add("원통송학 WT-2 3307")
        searchList.add("황토기독교 DR-1 2906")
        searchList.add("황토난 DR-5 2906")
        searchList.add("황토무지 DR-8 2505")
        searchList.add("황토불교 DR-2 2906")
        searchList.add("황토송학 DR-4 2906")
        searchList.add("황토천주교 DR-3 2906")
        searchList.add("황토청꽃 DR-6 2906")
        searchList.add("황토홍꽃 DR-7 2906")

        // 3. 이중 밀봉진공함
        searchList.add("이중기독교 EG-1 5114")
        searchList.add("이중밀봉블랙자개 EMR-1 5020")
        searchList.add("이중밀봉송학 EM-1 4411")
        searchList.add("이중백자 EW-6 4010")
        searchList.add("이중불교 EBB-2 5114")
        searchList.add("이중진주운학 EW-6 5114")
        searchList.add("이중천주교 EC-3 5114")
        searchList.add("이중칼라난 EH-5 5114")
        searchList.add("이중칼라송학 EH-4 5114")

        // 4. 잠금형 삼중 명품자개함
        searchList.add("아름골드자개 ARG-1 8025")
        searchList.add("아름꽃자개 ARF-2 8025")
        searchList.add("아름화이트자개 SGS-3 8025")
        searchList.add("휴안골드자개 HUG-16 10228")
        searchList.add("휴안백십장생자개 HUG-17 12535")
        searchList.add("휴안블랙자개 HUG-15 10228")
        searchList.add("휴안홍한지십장생자개 HUG-19 13139")
        searchList.add("휴안화이트자개 HUG-14 10228")
        searchList.add("휴안흑십장생자개 HUG-18 12535")

        // 5. 잠금형 삼중실크 밀봉진공함
        searchList.add("안향궁 AN-1 8525")
        searchList.add("안향천향 AN-2 8619.png")
        searchList.add("휴안궁 HU-1 8525")
        searchList.add("휴안그린난 HGN-2 9020")
        searchList.add("휴안그린송학 HGS-1 9020")
        searchList.add("휴안기독교 HUG-1 8817")
        searchList.add("휴안루엔골드 HLG-1 8723")
        searchList.add("휴안루엔화이트 HLS-2 8723")
        searchList.add("휴안명성 HU-3 8525")
        searchList.add("휴안불교 HUB-2 8817")
        searchList.add("휴안상아난 HSN-20 8718")
        searchList.add("휴안상아학 HSH-9 8718")
        searchList.add("휴안샤이니블루보석 HU-6 9520")
        searchList.add("휴안실버당초보석 HU-5 9520")
        searchList.add("휴안오로라블루 HUO-3 9822")
        searchList.add("휴안오로라실버 HUO-1 9822")
        searchList.add("휴안오로라핑크 HUO-2 9822")
        searchList.add("휴안천궁기독교 CGG-2 8723")
        searchList.add("휴안천궁불교 CGB-3 8723")
        searchList.add("휴안천궁일반 CG-1 8723")
        searchList.add("휴안천궁천주교 CGC-4 8723")
        searchList.add("휴안천주교 HUC-3 8817")
        searchList.add("휴안천향 HU-2 8525")
        searchList.add("휴안핑크당초보석 HU-4 9520")
        searchList.add("휴안화이트기독교 HUG-7 9421")
        searchList.add("휴안화이트불교 HU-8 9421")
        searchList.add("휴안화이트천주교 HU-9 9421")

        // 6. 소함
        searchList.add("소함난 EB-1 3713")
        searchList.add("소함송학 EB-2 3713")

        // 7. 수목함
        searchList.add("운학수목함 WH-1 1505")
        searchList.add("황토수목함 HT-1 1604")

        // 8. 스크류(잠금형)고급 진공함
        searchList.add("봉분궁 BOB-3 6621")
        searchList.add("봉분기독교 BOB-6 6621")
        searchList.add("봉분난 BOB-2 6621")
        searchList.add("봉분명성 BOB-4 6621")
        searchList.add("봉분불교 BOB-7 6621")
        searchList.add("봉분송학 BOB-1 6621")
        searchList.add("봉분천주교 BOB-8 6621")
        searchList.add("봉분천향 BOB-5 6621")
        searchList.add("아름궁 AR-3 6117")
        searchList.add("아름기독교 AR-9-6117")
        searchList.add("아름난 AR-2 6117")
        searchList.add("아름명성 AR-4 6117")
        searchList.add("아름불교-AR-10-6117")
        searchList.add("아름선궁 AR-6 6117")
        searchList.add("아름선명성 AR-7 6117")
        searchList.add("아름선천향 AR-8 6117")
        searchList.add("아름송학 AR-1 6117")
        searchList.add("아름천주교-AR-11-6117")
        searchList.add("아름천향 AR-5 6117")
        searchList.add("태림조각기독교 TA-2 6520")
        searchList.add("태림조각불교 TA-3 6520")
        searchList.add("태림조각일반 TA-1 6520")
        searchList.add("태림조각천주교 TA-4 6520")

        // 9. 황금함
        searchList.add("황금십장생 WGS-1 18040")
        searchList.add("황실황금기독교 HSG-2 13535")
        searchList.add("황실황금불교 HSB-3 13535")
        searchList.add("황실황금송학 HSS-5 13535")
        searchList.add("황실황금천주교 HSC-4 13535")
        searchList.add("황제황금함 ZE-14 20180")

        // 10. KS인증 ZEN한국도자기
        searchList.add("국화 ZE-1 11832")
        searchList.add("사군자 ZE-8 9030")
        searchList.add("선궁 ZE-3 11832")
        searchList.add("소망 ZE-6 11832")
        searchList.add("수복 ZE-10 9030")
        searchList.add("십장생 ZE-7 9030")
        searchList.add("아일렛 ZE-4 11832")
        searchList.add("옥자수 ZE-9 9030")
        searchList.add("조각기독교 ZE-11 1035")
        searchList.add("조각불교 ZE-12 1035")
        searchList.add("조각천주교 ZE-13 1035")
        searchList.add("청연 ZE-2 11832")
        searchList.add("화접도 ZE-5 11832")

        // 합골 1
        searchList.add("미정(타입2)")
        searchList.add("합골금띠 HG-1 4612")
        searchList.add("합골실버십장생 HG-2 4914")
        
        // 합골 2
        searchList.add("미정(타입1)")
        searchList.add("ZEN사각합골진공함-HG-3-8228")
    }
    private fun settingList2() {
        searchList2.add("미정")
        searchList2.add("흰색위패")
        searchList2.add("조각위패(황금향,조각종교)")
        searchList2.add("검정위패-TR-2-0802")
        searchList2.add("사진위패 TR-3 1005")
        searchList2.add("추모패-TR-4-1307")
//        searchList2.add("기본")
//        searchList2.add("기본(검정)")
    }
    private fun settingList3() {
        // 11. 평장
        searchList3.add("밀봉외함 MH-1 1015")
        searchList3.add("표석(大) PS-2 2317")
        searchList3.add("표석(小) PS-4 0911")
        searchList3.add("피아노(大) PS-1 3020")
        searchList3.add("피아노(小) PS-3 2015")
    }
    private fun settingUrnList(){
        // 1. 일반 밀봉진공함
        urnList.add(UrnItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        urnList.add(UrnItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        urnList.add(UrnItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        urnList.add(UrnItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        urnList.add(UrnItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        urnList.add(UrnItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        urnList.add(UrnItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        urnList.add(UrnItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        urnList.add(UrnItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))

        // 2. 일반함
        urnList.add(UrnItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        urnList.add(UrnItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        urnList.add(UrnItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        urnList.add(UrnItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        urnList.add(UrnItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        urnList.add(UrnItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        urnList.add(UrnItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        urnList.add(UrnItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        urnList.add(UrnItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        urnList.add(UrnItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        urnList.add(UrnItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        urnList.add(UrnItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        urnList.add(UrnItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))

        // 3. 이중 밀봉진공함
        urnList.add(UrnItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        urnList.add(UrnItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        urnList.add(UrnItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        urnList.add(UrnItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        urnList.add(UrnItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        urnList.add(UrnItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        urnList.add(UrnItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        urnList.add(UrnItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        urnList.add(UrnItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))

        // 4. 잠금형 삼중 명품자개함
        urnList.add(UrnItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        urnList.add(UrnItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        urnList.add(UrnItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        urnList.add(UrnItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        urnList.add(UrnItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        urnList.add(UrnItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        urnList.add(UrnItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        urnList.add(UrnItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        urnList.add(UrnItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))

        // 5. 잠금형 삼중실크 밀봉진공함
        urnList.add(UrnItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        urnList.add(UrnItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        urnList.add(UrnItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        urnList.add(UrnItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        urnList.add(UrnItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        urnList.add(UrnItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        urnList.add(UrnItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        urnList.add(UrnItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        urnList.add(UrnItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        urnList.add(UrnItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        urnList.add(UrnItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        urnList.add(UrnItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        urnList.add(UrnItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        urnList.add(UrnItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        urnList.add(UrnItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        urnList.add(UrnItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        urnList.add(UrnItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        urnList.add(UrnItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        urnList.add(UrnItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        urnList.add(UrnItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        urnList.add(UrnItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        urnList.add(UrnItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        urnList.add(UrnItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        urnList.add(UrnItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        urnList.add(UrnItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        urnList.add(UrnItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        urnList.add(UrnItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))

        // 6. 소함
        urnList.add(UrnItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        urnList.add(UrnItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))

        // 7. 수목함
        urnList.add(UrnItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        urnList.add(UrnItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))

        // 8. 스크류(잠금형)고급 진공함
        urnList.add(UrnItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        urnList.add(UrnItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        urnList.add(UrnItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        urnList.add(UrnItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        urnList.add(UrnItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        urnList.add(UrnItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        urnList.add(UrnItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        urnList.add(UrnItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        urnList.add(UrnItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        urnList.add(UrnItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        urnList.add(UrnItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        urnList.add(UrnItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        urnList.add(UrnItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        urnList.add(UrnItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        urnList.add(UrnItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        urnList.add(UrnItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        urnList.add(UrnItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        urnList.add(UrnItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        urnList.add(UrnItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        urnList.add(UrnItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        urnList.add(UrnItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        urnList.add(UrnItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        urnList.add(UrnItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))

        // 9. 황금함
        urnList.add(UrnItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        urnList.add(UrnItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        urnList.add(UrnItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        urnList.add(UrnItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        urnList.add(UrnItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        urnList.add(UrnItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))

        // 10. KS인증 ZEN한국도자기
        urnList.add(UrnItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        urnList.add(UrnItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        urnList.add(UrnItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        urnList.add(UrnItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        urnList.add(UrnItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        urnList.add(UrnItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        urnList.add(UrnItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        urnList.add(UrnItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        urnList.add(UrnItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        urnList.add(UrnItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        urnList.add(UrnItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        urnList.add(UrnItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        urnList.add(UrnItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))
    }
}