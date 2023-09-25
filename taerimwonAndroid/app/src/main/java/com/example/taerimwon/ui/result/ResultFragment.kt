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
import android.os.Handler
import android.os.Process
import android.text.InputFilter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.taerimwon.R
import com.example.taerimwon.base.BaseFragment
import com.example.taerimwon.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import com.example.taerimwon.di.ApplicationClass
import com.example.taerimwon.ui.home.AuthViewModel
import com.example.taerimwon.utils.view.toast
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    val authViewModel: AuthViewModel by viewModels()
    private val REQUEST_CODE_STORAGE_PERMISSION = 101 // 권한 요청 코드
    private lateinit var msg: String
    private lateinit var bitmap: Bitmap

    override fun init() {
        initData()
        setImage()
        setOnClickListeners()
        observer()
    }

    private fun initData() {
        authViewModel.getBlackList()
        if(!ApplicationClass.prefs.authenticated)
            findNavController().navigate(R.id.action_resultFragment_to_phoneAuthFragment)

        msg = "[태림원]" +
                "\n발주자 정보" +
                "\n - 발주자명: " + ApplicationClass.prefs.leaderName +
                "\n - 발주자 전화번호: " + ApplicationClass.prefs.leaderTel +
                "\n - 소속: " + ApplicationClass.prefs.leaderDepartment +
                "\n\n상주 정보" +
                "\n상주명: " + ApplicationClass.prefs.clientName +
                "\n상주 전화번호: " + ApplicationClass.prefs.clientTel +
                "\n\n발주 장소"

        val selectedLocation = ApplicationClass.prefs.selectedLocation
        if(selectedLocation.equals("화장장")){
            msg += "\n - 화장장소: " + ApplicationClass.prefs.cremationArea +
                    "\n - 화장시간: " + ApplicationClass.prefs.cremationTime
        } else if(selectedLocation.equals("장례식장")){
            msg += "\n - 장례식장 명: " + ApplicationClass.prefs.cremationTime +
                    "\n - 호실: " + ApplicationClass.prefs.funeralNumber +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.funeralTime
        } else if(selectedLocation.equals("장지")){
            msg += "\n - 장지명: " + ApplicationClass.prefs.burialName +
                    "\n - 함 도착 시간: " + ApplicationClass.prefs.burialTime
        }

        msg += "\n\n각인 종류: " + ApplicationClass.prefs.engraveType + "[" + ApplicationClass.prefs.engraveType2 + "]"

        msg += "\n\n고인 정보: " +
                "\n - 고인명: " + ApplicationClass.prefs.name1 +
                "\n - 생년월일: " + ApplicationClass.prefs.date1.toString().replace("-", ".") + " (${ApplicationClass.prefs.date1Type})" +
                "\n - 사망월일: " + ApplicationClass.prefs.date2.toString().replace("-", ".") + " (${ApplicationClass.prefs.date2Type})"

        // 직분, 세례명, 법명
        if((ApplicationClass.prefs.engraveType == "기독교" || ApplicationClass.prefs.engraveType == "순복음") && (ApplicationClass.prefs.engraveType2 == "기본")) {
            msg += "\n - 직분: " + ApplicationClass.prefs.name2
        }else if(ApplicationClass.prefs.engraveType == "불교" && ApplicationClass.prefs.engraveType2 == "법명") {
            msg += "\n - 법명: " + ApplicationClass.prefs.name2
        }else if(ApplicationClass.prefs.engraveType == "천주교" && ApplicationClass.prefs.engraveType2 == "기본") {
            msg += "\n - 세례명: " + ApplicationClass.prefs.name2
        }

        msg += "\n\n유골함 종류: " + ApplicationClass.prefs.selectedUrnType

        msg += "\n\n위패 종류: " + ApplicationClass.prefs.tabletType +
                "\n - 위패 내용: " + ApplicationClass.prefs.name3 +
                "\n - 위패 상세 종류: " + ApplicationClass.prefs.selectedTabletType

        msg += "\n\n특이사항: " + ApplicationClass.prefs.note

        println(msg)

        binding.textResult.text = msg
    }
    private fun setImage() {
        val handler = Handler()
        val delayMillis = 500 // 1초 (1000 밀리초)

        handler.postDelayed({
            // 여기에 1초 후에 실행하고자 하는 코드를 작성합니다.
            // 1. XML 레이아웃
            val layoutUrnImage = binding.layoutUrnImage

            // 2. 레이아웃을 이미지로 변환
            bitmap = Bitmap.createBitmap(layoutUrnImage.width, layoutUrnImage.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            layoutUrnImage.draw(canvas)

            val layoutResultImage = binding.layoutResultImage
//            layoutResultImage.setImageBitmap(bitmap)
//            layoutUrnImage.visibility = View.GONE
        }, delayMillis.toLong())
    }
    private fun setOnClickListeners() {
        binding.buttonResultToOrderFragment.setOnClickListener{
            ApplicationClass.prefs.resetPreferences()

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

                    // 3. 변환된 이미지를 저장 (파일로)
                    val imageFile = File(requireContext().cacheDir, "layout_result_image.png")
                    val outputStream = FileOutputStream(imageFile)
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.close()

                    // 4. 이미지 파일의 경로를 Uri로 만들어서 MMS 메시지에 첨부
                    Log.d(TAG, "imageFile: " + imageFile)
                    val imageUri = FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.fileprovider", imageFile)
                    Log.d(TAG, "imageUri: " + imageUri)

                    val tel = ApplicationClass.prefs.leaderTel.toString().replace("-", "")
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