package com.example.taerimwon.ui.result

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.example.taerimwon.databinding.DlgResultBinding

class ResultDialogFragment : DialogFragment() {
    private var _binding: DlgResultBinding? = null
    private val binding get() = _binding!!

    private lateinit var image: Bitmap

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
            val params = binding.imageResult.layoutParams
            params.width = (initialWidth * scaleFactor).toInt()
            params.height = (initialHeight * scaleFactor).toInt()
            binding.imageResult.layoutParams = params

            return true
        }
    }
    private fun setOnTouchListener(){
        // 최초 크기 저장
        initialWidth = image.width
        initialHeight = image.height

        // ScaleGestureDetector 초기화
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

        // 뷰에 터치 이벤트 리스너 설정
        binding.imageResult.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }

        // 확대 버튼 클릭 이벤트
        binding.buttonZoomIn.setOnClickListener {
            scaleFactor *= 1.2f // 예시로 20%씩 확대
            updateImageSize()
        }

        // 축소 버튼 클릭 이벤트
        binding.buttonZoomOut.setOnClickListener {
            scaleFactor *= 0.8f // 예시로 20%씩 축소
            updateImageSize()
        }
    }

    private fun updateImageSize() {
        // LayoutParams를 사용하여 뷰의 크기 조절
        val params = binding.imageResult.layoutParams
        params.width = (initialWidth * scaleFactor).toInt()
        params.height = (initialHeight * scaleFactor).toInt()
        binding.imageResult.layoutParams = params
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DlgResultBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        val view = binding.root

        binding.imageResult.setImageBitmap(image)
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

    fun setImageBitmap(imageRes: Bitmap){
        image = imageRes
    }
}