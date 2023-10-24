package com.example.taerimwon.ui.result

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.taerimwon.R
import com.example.taerimwon.databinding.DlgResultBinding

class ResultDialogFragment : DialogFragment() {
    private var _binding: DlgResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener : PhoneAuthDialogOKClickedListener


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DlgResultBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = binding.root

//        binding.imageResult.setImageResource(R.drawable.layout)

        //ok 버튼 동작
        binding.buttonClose.setOnClickListener {
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface PhoneAuthDialogOKClickedListener {
        fun onOKClicked(content : String)
    }
}