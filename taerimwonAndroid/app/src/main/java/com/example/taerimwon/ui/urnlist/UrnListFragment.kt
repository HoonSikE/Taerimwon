package com.example.taerimwon.ui.urnlist

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.data.dto.urn.UrnItem
import com.example.taerimwon.databinding.FragmentUrnListBinding
import com.example.taerimwon.di.ApplicationClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrnListFragment : BaseFragment<FragmentUrnListBinding>(R.layout.fragment_urn_list) {
    private lateinit var urnListAdapter1: UrnListAdapter
    private lateinit var urnListAdapter2: UrnListAdapter
    private lateinit var urnListAdapter3: UrnListAdapter
    private lateinit var urnListAdapter4: UrnListAdapter
    private lateinit var urnListAdapter5: UrnListAdapter
    private lateinit var urnListAdapter6: UrnListAdapter
    private lateinit var urnListAdapter7: UrnListAdapter
    private lateinit var urnListAdapter8: UrnListAdapter
    private lateinit var urnListAdapter9: UrnListAdapter
    private lateinit var urnListAdapter10: UrnListAdapter
    private lateinit var urnListAdapter11: UrnListAdapter

    private lateinit var urnList1: MutableList<UrnItem>
    private lateinit var urnList2: MutableList<UrnItem>
    private lateinit var urnList3: MutableList<UrnItem>
    private lateinit var urnList4: MutableList<UrnItem>
    private lateinit var urnList5: MutableList<UrnItem>
    private lateinit var urnList6: MutableList<UrnItem>
    private lateinit var urnList7: MutableList<UrnItem>
    private lateinit var urnList8: MutableList<UrnItem>
    private lateinit var urnList9: MutableList<UrnItem>
    private lateinit var urnList10: MutableList<UrnItem>
    private lateinit var urnList11: MutableList<UrnItem>

    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
    }

    private val UrnListClickListener: (View, String, Int) -> Unit = { _, urnName, imageName ->
        println("imageName: " + imageName)
        val dialog = UrnListDialogFragment()
        // 화면 밖 터치시 종료되지 않게 하기
        dialog.isCancelable = false

        dialog.setUrnName(urnName)
        dialog.setImageResource(imageName)

        dialog.show(childFragmentManager, "show layout zoom")
    }

    // 유골함 리스트
    private fun initAdapter(){
        // 1
        urnListAdapter1 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager1 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList1.apply {
            adapter = urnListAdapter1
            layoutManager = listManager1
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList1 = mutableListOf()
        settingUrnList1()
        urnListAdapter1.updateList(urnList1)

        // 2
        urnListAdapter2 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager2 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList2.apply {
            adapter = urnListAdapter2
            layoutManager = listManager2
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList2 = mutableListOf()
        settingUrnList2()
        urnListAdapter2.updateList(urnList2)

        // 3
        urnListAdapter3 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager3 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList3.apply {
            adapter = urnListAdapter3
            layoutManager = listManager3
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList3 = mutableListOf()
        settingUrnList3()
        urnListAdapter3.updateList(urnList3)

        // 4
        urnListAdapter4 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager4 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList4.apply {
            adapter = urnListAdapter4
            layoutManager = listManager4
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList4 = mutableListOf()
        settingUrnList4()
        urnListAdapter4.updateList(urnList4)

        // 5
        urnListAdapter5 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager5 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList5.apply {
            adapter = urnListAdapter5
            layoutManager = listManager5
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList5 = mutableListOf()
        settingUrnList5()
        urnListAdapter5.updateList(urnList5)

        // 6
        urnListAdapter6 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager6 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList6.apply {
            adapter = urnListAdapter6
            layoutManager = listManager6
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList6 = mutableListOf()
        settingUrnList6()
        urnListAdapter6.updateList(urnList6)

        // 7
        urnListAdapter7 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager7 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList7.apply {
            adapter = urnListAdapter7
            layoutManager = listManager7
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList7 = mutableListOf()
        settingUrnList7()
        urnListAdapter7.updateList(urnList7)

        // 8
        urnListAdapter8 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager8 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList8.apply {
            adapter = urnListAdapter8
            layoutManager = listManager8
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList8 = mutableListOf()
        settingUrnList8()
        urnListAdapter8.updateList(urnList8)

        // 9
        urnListAdapter9 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager9 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList9.apply {
            adapter = urnListAdapter9
            layoutManager = listManager9
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList9 = mutableListOf()
        settingUrnList9()
        urnListAdapter9.updateList(urnList9)

        // 10
        urnListAdapter10 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager10 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList10.apply {
            adapter = urnListAdapter10
            layoutManager = listManager10
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList10 = mutableListOf()
        settingUrnList10()
        urnListAdapter10.updateList(urnList10)

        // 11
        urnListAdapter11 = UrnListAdapter(requireContext()).apply {
            onItemClickListener = UrnListClickListener
        }
        var listManager11 = GridLayoutManager(requireContext(), 2)
        binding.recyclerviewUrnList11.apply {
            adapter = urnListAdapter11
            layoutManager = listManager11
//            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        urnList11 = mutableListOf()
        settingUrnList11()
        urnListAdapter11.updateList(urnList11)
    }


    private fun initData() {

    }

    private fun setOnClickListeners() {
        binding.imageUrnlistToHomeFragment.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_urn_list_to_homeFragment)
        }
    }

    private fun settingUrnList1(){
        // 1. 일반 밀봉진공함
        urnList1.add(UrnItem("도원기독교 DW-3 4010", R.drawable.img_urn1_1))
        urnList1.add(UrnItem("도원불교 DW-4 4010", R.drawable.img_urn1_2))
        urnList1.add(UrnItem("도원천주교 DW-5 4010", R.drawable.img_urn1_3))
        urnList1.add(UrnItem("도원칼라난 DW-2 4010", R.drawable.img_urn1_4))
        urnList1.add(UrnItem("도원칼라송학 DW-1 4010", R.drawable.img_urn1_5))
        urnList1.add(UrnItem("도화청꽃 DH-4 4010", R.drawable.img_urn1_6))
        urnList1.add(UrnItem("도화홍꽃 DH-5 4010", R.drawable.img_urn1_7))
        urnList1.add(UrnItem("소담난 SDN-2 4008", R.drawable.img_urn1_8))
        urnList1.add(UrnItem("소담송학 SDS-1 4008", R.drawable.img_urn1_9))
    }
    private fun settingUrnList2(){
        // 2. 일반함
        urnList2.add(UrnItem("매화조각민트 MH-2 2906", R.drawable.img_urn2_1))
        urnList2.add(UrnItem("매화조각핑크 MH-1 2906", R.drawable.img_urn2_2))
        urnList2.add(UrnItem("매화조각화이트 MH-3 2906", R.drawable.img_urn2_3))
        urnList2.add(UrnItem("원통난 WT-1 3307", R.drawable.img_urn2_4))
        urnList2.add(UrnItem("원통송학 WT-2 3307", R.drawable.img_urn2_5))
        urnList2.add(UrnItem("황토기독교 DR-1 2906", R.drawable.img_urn2_6))
        urnList2.add(UrnItem("황토난 DR-5 2906", R.drawable.img_urn2_7))
        urnList2.add(UrnItem("황토무지 DR-8 2505", R.drawable.img_urn2_8))
        urnList2.add(UrnItem("황토불교 DR-2 2906", R.drawable.img_urn2_9))
        urnList2.add(UrnItem("황토송학 DR-4 2906", R.drawable.img_urn2_10))
        urnList2.add(UrnItem("황토천주교 DR-3 2906", R.drawable.img_urn2_11))
        urnList2.add(UrnItem("황토청꽃 DR-6 2906", R.drawable.img_urn2_12))
        urnList2.add(UrnItem("황토홍꽃 DR-7 2906", R.drawable.img_urn2_13))
    }
    private fun settingUrnList3(){
        // 3. 이중 밀봉진공함
        urnList3.add(UrnItem("이중기독교 EG-1 5114", R.drawable.img_urn3_1))
        urnList3.add(UrnItem("이중밀봉블랙자개 EMR-1 5020", R.drawable.img_urn3_2))
        urnList3.add(UrnItem("이중밀봉송학 EM-1 4411", R.drawable.img_urn3_3))
        urnList3.add(UrnItem("이중백자 EW-6 4010", R.drawable.img_urn3_4))
        urnList3.add(UrnItem("이중불교 EBB-2 5114", R.drawable.img_urn3_5))
        urnList3.add(UrnItem("이중진주운학 EW-6 5114", R.drawable.img_urn3_6))
        urnList3.add(UrnItem("이중천주교 EC-3 5114", R.drawable.img_urn3_7))
        urnList3.add(UrnItem("이중칼라난 EH-5 5114", R.drawable.img_urn3_8))
        urnList3.add(UrnItem("이중칼라송학 EH-4 5114", R.drawable.img_urn3_9))
    }
    private fun settingUrnList4(){
        // 4. 잠금형 삼중 명품자개함
        urnList4.add(UrnItem("아름골드자개 ARG-1 8025", R.drawable.img_urn4_1))
        urnList4.add(UrnItem("아름꽃자개 ARF-2 8025", R.drawable.img_urn4_2))
        urnList4.add(UrnItem("아름화이트자개 SGS-3 8025", R.drawable.img_urn4_3))
        urnList4.add(UrnItem("휴안골드자개 HUG-16 10228", R.drawable.img_urn4_4))
        urnList4.add(UrnItem("휴안백십장생자개 HUG-17 12535", R.drawable.img_urn4_5))
        urnList4.add(UrnItem("휴안블랙자개 HUG-15 10228", R.drawable.img_urn4_6))
        urnList4.add(UrnItem("휴안홍한지십장생자개 HUG-19 13139", R.drawable.img_urn4_7))
        urnList4.add(UrnItem("휴안화이트자개 HUG-14 10228", R.drawable.img_urn4_8))
        urnList4.add(UrnItem("휴안흑십장생자개 HUG-18 12535", R.drawable.img_urn4_9))
    }
    private fun settingUrnList5(){
        // 5. 잠금형 삼중실크 밀봉진공함
        urnList5.add(UrnItem("안향궁 AN-1 8525", R.drawable.img_urn5_1))
        urnList5.add(UrnItem("안향천향 AN-2 8619.png", R.drawable.img_urn5_2))
        urnList5.add(UrnItem("휴안궁 HU-1 8525", R.drawable.img_urn5_3))
        urnList5.add(UrnItem("휴안그린난 HGN-2 9020", R.drawable.img_urn5_4))
        urnList5.add(UrnItem("휴안그린송학 HGS-1 9020", R.drawable.img_urn5_5))
        urnList5.add(UrnItem("휴안기독교 HUG-1 8817", R.drawable.img_urn5_6))
        urnList5.add(UrnItem("휴안루엔골드 HLG-1 8723", R.drawable.img_urn5_7))
        urnList5.add(UrnItem("휴안루엔화이트 HLS-2 8723", R.drawable.img_urn5_8))
        urnList5.add(UrnItem("휴안명성 HU-3 8525", R.drawable.img_urn5_9))
        urnList5.add(UrnItem("휴안불교 HUB-2 8817", R.drawable.img_urn5_10))
        urnList5.add(UrnItem("휴안상아난 HSN-20 8718", R.drawable.img_urn5_11))
        urnList5.add(UrnItem("휴안상아학 HSH-9 8718", R.drawable.img_urn5_12))
        urnList5.add(UrnItem("휴안샤이니블루보석 HU-6 9520", R.drawable.img_urn5_13))
        urnList5.add(UrnItem("휴안실버당초보석 HU-5 9520", R.drawable.img_urn5_14))
        urnList5.add(UrnItem("휴안오로라블루 HUO-3 9822", R.drawable.img_urn5_15))
        urnList5.add(UrnItem("휴안오로라실버 HUO-1 9822", R.drawable.img_urn5_16))
        urnList5.add(UrnItem("휴안오로라핑크 HUO-2 9822", R.drawable.img_urn5_17))
        urnList5.add(UrnItem("휴안천궁기독교 CGG-2 8723", R.drawable.img_urn5_18))
        urnList5.add(UrnItem("휴안천궁불교 CGB-3 8723", R.drawable.img_urn5_19))
        urnList5.add(UrnItem("휴안천궁일반 CG-1 8723", R.drawable.img_urn5_20))
        urnList5.add(UrnItem("휴안천궁천주교 CGC-4 8723", R.drawable.img_urn5_21))
        urnList5.add(UrnItem("휴안천주교 HUC-3 8817", R.drawable.img_urn5_22))
        urnList5.add(UrnItem("휴안천향 HU-2 8525", R.drawable.img_urn5_23))
        urnList5.add(UrnItem("휴안핑크당초보석 HU-4 9520", R.drawable.img_urn5_24))
        urnList5.add(UrnItem("휴안화이트기독교 HUG-7 9421", R.drawable.img_urn5_25))
        urnList5.add(UrnItem("휴안화이트불교 HU-8 9421", R.drawable.img_urn5_26))
        urnList5.add(UrnItem("휴안화이트천주교 HU-9 9421", R.drawable.img_urn5_27))
    }
    private fun settingUrnList6(){
        // 6. 소함
        urnList6.add(UrnItem("소함난 EB-1 3713", R.drawable.img_urn6_1))
        urnList6.add(UrnItem("소함송학 EB-2 3713", R.drawable.img_urn6_2))
    }
    private fun settingUrnList7(){
        // 7. 수목함
        urnList7.add(UrnItem("운학수목함 WH-1 1505", R.drawable.img_urn7_1))
        urnList7.add(UrnItem("황토수목함 HT-1 1604", R.drawable.img_urn7_2))
    }
    private fun settingUrnList8(){
        // 8. 스크류(잠금형)고급 진공함
        urnList8.add(UrnItem("봉분궁 BOB-3 6621", R.drawable.img_urn8_1))
        urnList8.add(UrnItem("봉분기독교 BOB-6 6621", R.drawable.img_urn8_2))
        urnList8.add(UrnItem("봉분난 BOB-2 6621", R.drawable.img_urn8_3))
        urnList8.add(UrnItem("봉분명성 BOB-4 6621", R.drawable.img_urn8_4))
        urnList8.add(UrnItem("봉분불교 BOB-7 6621", R.drawable.img_urn8_5))
        urnList8.add(UrnItem("봉분송학 BOB-1 6621", R.drawable.img_urn8_6))
        urnList8.add(UrnItem("봉분천주교 BOB-8 6621", R.drawable.img_urn8_7))
        urnList8.add(UrnItem("봉분천향 BOB-5 6621", R.drawable.img_urn8_8))
        urnList8.add(UrnItem("아름궁 AR-3 6117", R.drawable.img_urn8_9))
        urnList8.add(UrnItem("아름기독교 AR-9-6117", R.drawable.img_urn8_10))
        urnList8.add(UrnItem("아름난 AR-2 6117", R.drawable.img_urn8_11))
        urnList8.add(UrnItem("아름명성 AR-4 6117", R.drawable.img_urn8_12))
        urnList8.add(UrnItem("아름불교-AR-10-6117", R.drawable.img_urn8_13))
        urnList8.add(UrnItem("아름선궁 AR-6 6117", R.drawable.img_urn8_14))
        urnList8.add(UrnItem("아름선명성 AR-7 6117", R.drawable.img_urn8_15))
        urnList8.add(UrnItem("아름선천향 AR-8 6117", R.drawable.img_urn8_16))
        urnList8.add(UrnItem("아름송학 AR-1 6117", R.drawable.img_urn8_17))
        urnList8.add(UrnItem("아름천주교-AR-11-6117", R.drawable.img_urn8_18))
        urnList8.add(UrnItem("아름천향 AR-5 6117", R.drawable.img_urn8_19))
        urnList8.add(UrnItem("태림조각기독교 TA-2 6520", R.drawable.img_urn8_20))
        urnList8.add(UrnItem("태림조각불교 TA-3 6520", R.drawable.img_urn8_21))
        urnList8.add(UrnItem("태림조각일반 TA-1 6520", R.drawable.img_urn8_22))
        urnList8.add(UrnItem("태림조각천주교 TA-4 6520", R.drawable.img_urn8_23))
    }
    private fun settingUrnList9(){
        // 9. 황금함
        urnList9.add(UrnItem("황금십장생 WGS-1 18040", R.drawable.img_urn9_1))
        urnList9.add(UrnItem("황실황금기독교 HSG-2 13535", R.drawable.img_urn9_2))
        urnList9.add(UrnItem("황실황금불교 HSB-3 13535", R.drawable.img_urn9_3))
        urnList9.add(UrnItem("황실황금송학 HSS-5 13535", R.drawable.img_urn9_4))
        urnList9.add(UrnItem("황실황금천주교 HSC-4 13535", R.drawable.img_urn9_5))
        urnList9.add(UrnItem("황제황금함 ZE-14 20180", R.drawable.img_urn9_6))
    }
    private fun settingUrnList10(){
        // 10. KS인증 ZEN한국도자기
        urnList10.add(UrnItem("국화 ZE-1 11832", R.drawable.img_urn10_1))
        urnList10.add(UrnItem("사군자 ZE-8 9030", R.drawable.img_urn10_2))
        urnList10.add(UrnItem("선궁 ZE-3 11832", R.drawable.img_urn10_3))
        urnList10.add(UrnItem("소망 ZE-6 11832", R.drawable.img_urn10_4))
        urnList10.add(UrnItem("수복 ZE-10 9030", R.drawable.img_urn10_5))
        urnList10.add(UrnItem("십장생 ZE-7 9030", R.drawable.img_urn10_6))
        urnList10.add(UrnItem("아일렛 ZE-4 11832", R.drawable.img_urn10_7))
        urnList10.add(UrnItem("옥자수 ZE-9 9030", R.drawable.img_urn10_8))
        urnList10.add(UrnItem("조각기독교 ZE-11 1035", R.drawable.img_urn10_9))
        urnList10.add(UrnItem("조각불교 ZE-12 1035", R.drawable.img_urn10_10))
        urnList10.add(UrnItem("조각천주교 ZE-13 1035", R.drawable.img_urn10_11))
        urnList10.add(UrnItem("청연 ZE-2 11832", R.drawable.img_urn10_12))
        urnList10.add(UrnItem("화접도 ZE-5 11832", R.drawable.img_urn10_13))
    }
    private fun settingUrnList11(){
        // 11. 합골
        urnList11.add(UrnItem("ZEN사각합골진공함-HG-3-8228", R.drawable.img_bone1_1))
        urnList11.add(UrnItem("합골금띠 HG-1 4612", R.drawable.img_bone2_1))
        urnList11.add(UrnItem("합골실버십장생 HG-2 4914", R.drawable.img_bone2_2))
    }
}