package com.example.taerimwon.ui.urnlist

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.taerimwon.databinding.DlgUrnlistBinding

class UrnListDialogFragment : DialogFragment() {
    private var _binding: DlgUrnlistBinding? = null
    private val binding get() = _binding!!

    private lateinit var urnName : String
    private var image = 0

    // 최초 크기를 저장해둘 변수
    private var initialWidth = 0
    private var initialHeight = 0
    private var scaleFactor = 1.0f
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            // scaleFactor를 사용하여 현재 확대/축소 비율 업데이트
            scaleFactor *= detector.scaleFactor

            // 최소 0.1배, 최대 5배까지 확대/축소
            scaleFactor = scaleFactor.coerceIn(0.5f, 5.0f)

            // LayoutParams를 사용하여 뷰의 크기 조절
            val params = binding.imageUrn.layoutParams
            params.width = (initialWidth * scaleFactor).toInt()
            params.height = (initialHeight * scaleFactor).toInt()
            binding.imageUrn.layoutParams = params

            return true
        }
    }
    private fun setOnTouchListener(){
        // ScaleGestureDetector 초기화
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DlgUrnlistBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = binding.root

        binding.textDlgResultTitle.text = urnName

        binding.imageUrn.setImageResource(image)
        setOnTouchListener()

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

    fun setUrnName(urn: String){
        urnName = urn
    }
    fun setImageResource(imageRes: Int){
        image = imageRes
    }
}