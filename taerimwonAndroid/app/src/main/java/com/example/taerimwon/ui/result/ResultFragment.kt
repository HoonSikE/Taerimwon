package com.example.taerimwon.ui.result

import android.content.ContentValues
import android.net.Uri
import android.provider.Telephony
import android.telephony.SmsManager
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController


@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
    }
    private fun setOnClickListeners() {
        binding.buttonResultToOrderFragment.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonMMS.setOnClickListener{
            // MMS 보내기
            // 수신자 전화번호
            val recipientNumber = "01000000000"
            // MMS 제목
            val mmsSubject = "MMS 제목"
            // MMS 내용
            val mmsMessage = "MMS 내용"
            // 파일 URI, 예: content://media/external/images/media/1
            val imageUri = Uri.parse("content://media/external/images/media/1")
            sendMMS(recipientNumber, mmsSubject, mmsMessage, imageUri)
        }
    }
    private fun observer() {
    }

    // MMS 메시지 보내기 함수
    fun sendMMS(recipient: String, subject: String, message: String, imageUri: Uri) {
        // MMS 전송을 위한 데이터 준비
        val values = ContentValues()
        values.put(Telephony.Mms.ADDR, recipient) // 수신자 전화번호
        values.put(Telephony.Mms.SUBJECT, subject) // MMS 제목
        values.put(Telephony.Mms.TEXT_ONLY, message) // MMS 내용

        // MMS 메시지를 MMS 데이터베이스에 추가
        val contentResolver = applicationContext.contentResolver
        val mmsUri = contentResolver.insert(Telephony.Mms.Outbox.CONTENT_URI, values)

        // MMS 이미지 추가
        val imageValues = ContentValues()
        imageValues.put(Telephony.Mms.Part.MSG_ID, mmsUri?.lastPathSegment)
        imageValues.put(Telephony.Mms.Part.CONTENT_TYPE, "image/jpeg")
        imageValues.put(Telephony.Mms.Part.NAME, "image.jpg")
        imageValues.put(Telephony.Mms.Part.FILE_NAME, "image.jpg")
        imageValues.put(Telephony.Mms.Part.URI, imageUri.toString())

        contentResolver.insert(Telephony.Mms.Part.CONTENT_URI, imageValues)

        // MMS 메시지 전송
        val smsManager = SmsManager.getDefault()
        smsManager.sendMultimediaMessage(applicationContext, mmsUri)
    }
}