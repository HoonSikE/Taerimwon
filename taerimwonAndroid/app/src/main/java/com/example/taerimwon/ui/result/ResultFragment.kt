package com.example.taerimwon.ui.result

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Process
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private val REQUEST_CODE_STORAGE_PERMISSION = 101 // 권한 요청 코드
    private lateinit var msg: String

    override fun init() {
        initData()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
        msg = "발주자명: " + ApplicationClass.prefs.leaderName +
                "\n발주자 전화번호: " + ApplicationClass.prefs.leaderTel +
                "\n소속: " + ApplicationClass.prefs.leaderDepartment +
                "\n\n상주명: " + ApplicationClass.prefs.clientName +
                "\n상주 전화번호: " + ApplicationClass.prefs.clientTel +
                "\n\n고인명: " + ApplicationClass.prefs.name1 +
                "\n생년월일: " + ApplicationClass.prefs.date1 +
                "\n사망월일: " + ApplicationClass.prefs.date2 +
                "\n\n특이사항: " + ApplicationClass.prefs.note
        binding.textResult.text = msg
        binding.textResult3.text = msg
    }
    private fun setOnClickListeners() {
        binding.buttonResultToOrderFragment.setOnClickListener{
            ApplicationClass.prefs.clientName = ""
            ApplicationClass.prefs.clientTel = ""
            ApplicationClass.prefs.name1 = ""
            ApplicationClass.prefs.date1 = ""
            ApplicationClass.prefs.date2 = ""
            ApplicationClass.prefs.note = ""

            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonResultToOrderFragment2.setOnClickListener{
            findNavController().navigate(R.id.action_resultFragment_to_orderFragment)
        }
        binding.buttonMMS.setOnClickListener{
            // 권한 확인
            val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
            val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

            val activityContext = activity as? Activity // 안전하게 캐스트
            if (activityContext != null) {
                val readPermissionGranted = ContextCompat.checkSelfPermission(activityContext, readPermission) == PackageManager.PERMISSION_GRANTED
                val writePermissionGranted = ContextCompat.checkSelfPermission(activityContext, writePermission) == PackageManager.PERMISSION_GRANTED

                // 권한이 모두 허용되어 있는 경우
                if (readPermissionGranted && writePermissionGranted) {
                    // 파일 액세스 권한이 허용된 상태
                    // MMS 보내기
                    // 1. XML 레이아웃
                    val layoutResult = binding.layoutResult

                    // 2. 레이아웃을 이미지로 변환
                    val bitmap = Bitmap.createBitmap(
                        layoutResult.width, layoutResult.height, Bitmap.Config.ARGB_8888
                    )
                    val canvas = Canvas(bitmap)
                    layoutResult.draw(canvas)

                    // 3. 변환된 이미지를 저장 (파일로)
                    val imageFile = File(requireContext().cacheDir, "layout_result_image.png")
                    val outputStream = FileOutputStream(imageFile)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.close()

                    // 4. 이미지 파일의 경로를 Uri로 만들어서 MMS 메시지에 첨부
                    Log.d(TAG, "imageFile: " + imageFile)
                    val imageUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", imageFile)
                    Log.d(TAG, "imageUri: " + imageUri)

                    val tel = "01012345678"
                    val subject = "MMS 제목"
//                    val sms_body = "MMS 내용"
                    val sms_body = msg

                    // 5. attachmentUri를 사용하여 MMS 메시지를 만들고 보냅니다.
                    sendMMS(tel, subject, sms_body, imageUri)
                } else {
                    // 하나 이상의 권한이 거부된 경우
                    // 권한을 요청합니다.
                    ActivityCompat.requestPermissions(
                        activityContext,
                        arrayOf(readPermission, writePermission),
                        REQUEST_CODE_STORAGE_PERMISSION
                    )
                }
            } else {
                // activityContext가 null인 경우에 대한 처리
            }
        }
    }
    private fun observer() {
    }

    // MMS 메시지 보내기 함수
    private fun sendMMS(tel: String, subject: String, sms_body: String, imageUri: Uri) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            putExtra("address", tel)
//            putExtra("subject", subject)
            putExtra("sms_body", sms_body)
        }

        Log.d(TAG, "subject: " + subject)
        Log.d(TAG, "sms_body: " + sms_body)
        Log.d(TAG, "imageUri: " + imageUri)

        try {
            if (activity?.let { intent.resolveActivity(it.packageManager) } != null) {
                startActivity(intent)
            } else {
                // SMS/MMS 앱이 설치되어 있지 않음 또는 처리할 수 없는 경우
                toast("기본 메시지 앱을 찾을 수 없습니다.")
            }
        } catch (e: ActivityNotFoundException) {
            // SMS/MMS 앱을 찾을 수 없는 경우
            toast("기본 메시지 앱을 찾을 수 없습니다.")
        }
    }
}