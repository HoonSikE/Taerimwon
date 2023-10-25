package com.example.taerimwon.ui.result

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.taerimwon.databinding.DlgResultBinding

class ResultDialogFragment : DialogFragment() {
    private var _binding: DlgResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var image: Bitmap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DlgResultBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = binding.root

        binding.imageResult.setImageBitmap(image)

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

    fun setImageBitmap(imageRes: Bitmap){
        image = imageRes
    }
}