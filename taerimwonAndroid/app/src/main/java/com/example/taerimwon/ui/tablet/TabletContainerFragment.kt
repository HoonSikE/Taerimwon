package com.example.taerimwon.ui.result

import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import com.example.taerimwon.databinding.FragmentTabletContainerBinding


@AndroidEntryPoint
class TabletContainerFragment : BaseFragment<FragmentTabletContainerBinding>(R.layout.fragment_tablet_container) {
    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
    }
    private fun setOnClickListeners() {
    }
    private fun observer() {
    }
}