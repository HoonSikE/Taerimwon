package com.example.taerimwon.di

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.taerimwon.R
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("ResourceAsColor")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_STORAGE_PERMISSION = 101 // 권한 요청 코드
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 권한 확인 및 요청
        checkStoragePermission()

        // 캡쳐방지
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        ApplicationClass.prefs.resetPreferences()
        supportActionBar?.hide()
    }

    private fun checkStoragePermission() {
        // 권한 확인
        val readPermission = Manifest.permission.READ_EXTERNAL_STORAGE
        val writePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE

        val readPermissionGranted = ContextCompat.checkSelfPermission(this, readPermission) == PackageManager.PERMISSION_GRANTED
        val writePermissionGranted = ContextCompat.checkSelfPermission(this, writePermission) == PackageManager.PERMISSION_GRANTED

        // 권한이 모두 허용되어 있는 경우
        if (readPermissionGranted && writePermissionGranted) {
            // 파일 액세스 권한이 허용된 상태
            // 여기에 파일 첨부 및 MMS 전송 코드를 추가하세요
        } else {
            // 하나 이상의 권한이 거부된 경우
            // 권한을 요청합니다.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(readPermission, writePermission),
                REQUEST_CODE_STORAGE_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            // 권한 요청 결과 처리
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 파일 액세스 권한이 허용된 경우
                // 여기에 파일 첨부 및 MMS 전송 코드를 추가하세요
            } else {
                // 권한이 거부된 경우
                Log.d("Permission", "Storage permission denied.")
                // 사용자에게 알림 또는 다른 조치를 취할 수 있습니다.
            }
        }
    }
}