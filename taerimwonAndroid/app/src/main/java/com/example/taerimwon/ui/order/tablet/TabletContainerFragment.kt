package com.example.taerimwon.ui.order.tablet

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.databinding.FragmentTabletContainerBinding
import com.example.taerimwon.di.ApplicationClass


@AndroidEntryPoint
class TabletContainerFragment : BaseFragment<FragmentTabletContainerBinding>(R.layout.fragment_tablet_container) {
    private lateinit var tabletSelectType2Adapter: TabletType2_Adapter
    private var tabletSelectType2List = ArrayList<String>()

    override fun init() {
        initAdapter()
        initData()
        setOnClickListeners()
        setOnItemSelectedListener()
        observer()
    }
    private fun initAdapter() {
        tabletSelectType2Adapter = TabletType2_Adapter()

        binding.recyclerviewTabletType2.apply {
            adapter = tabletSelectType2Adapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }
    private fun initData() {
        tabletSelectType2List = arrayListOf("일반", "일반(본관)", "문구")
        tabletSelectType2Adapter.updateList(tabletSelectType2List)
        binding.recyclerviewTabletType2.scrollToPosition(0)
    }
    private fun setOnClickListeners() {
    }
    private fun setOnItemSelectedListener(){
        binding.spinnerTabletType2.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApplicationClass.prefs.selectedTabletType = parent?.getItemAtPosition(position).toString() ?: ""
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nothing to do here
            }
        })
    }
    private fun observer() {

    }
}