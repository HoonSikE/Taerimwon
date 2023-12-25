package com.example.taerimwon.ui.home

import androidx.navigation.fragment.findNavController
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun init() {
        initData()
        setOnClickListeners()
    }

    private fun initData() {

    }

    private fun setOnClickListeners() {
        binding.imageHomeToOrder.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_orderFragment)
        }
        binding.imageHomeToUrnlist.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_urnListFragment)
        }
    }
}