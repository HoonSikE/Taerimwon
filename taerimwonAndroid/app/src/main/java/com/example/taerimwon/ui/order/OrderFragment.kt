package com.example.taerimwon.ui.order

import com.example.taerimwon.R
import com.example.taerimwon.databinding.FragmentOrderBinding
import com.example.taerimwon.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.ui.result.TabletContainerFragment
import com.example.taerimwon.ui.result.UrnContainerFragment

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {
    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
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
    private fun setOnClickListeners() {
        binding.buttonResultFragment.setOnClickListener{
            findNavController().navigate(R.id.action_orderFragment_to_resultFragment)
        }
    }
    private fun observer() {
    }
}