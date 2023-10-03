package com.example.taerimwon.utils.input

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

// 이미지를 내부 저장소에 저장하고 저장된 파일의 경로(URI)를 반환
fun saveImageToInternalStorage(context: Context, imageUri: Uri): Uri? {
    try {
        // 이미지를 내부 저장소에 저장할 파일 경로 설정
        val internalStorageDir = context.filesDir // 내부 저장소의 디렉터리 경로
        val imageFileName = "my_image.jpg" // 이미지 파일 이름
        val imageFile = File(internalStorageDir, imageFileName)

        // 이미지 데이터를 읽어와 파일에 복사
        val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
        val outputStream: OutputStream = FileOutputStream(imageFile)
        if (inputStream != null) {
            val buffer = ByteArray(4 * 1024) // 4KB 버퍼 크기
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            inputStream.close()
            outputStream.close()
        }

        // 저장된 이미지 파일의 경로(URI)를 반환
        return Uri.fromFile(imageFile)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}