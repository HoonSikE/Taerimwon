package com.example.taerimwon.utils.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class DateTimeTextWatcher(private val editText: EditText) : TextWatcher {
    private var isFormatting = false
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // 이전 텍스트 변경 이벤트
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // 텍스트 변경 이벤트
        if (isFormatting) {
            return
        }

        val formattedText = formatDateTime(s.toString())
        isFormatting = true
        editText.setText(formattedText)
        editText.setSelection(formattedText.length)
        isFormatting = false
    }

    override fun afterTextChanged(s: Editable?) {
        // 텍스트 변경 후 이벤트
    }

    private fun formatDateTime(dateTime: String): String {
        try {
            val parsedDate = dateFormat.parse(dateTime)
            return dateFormat.format(parsedDate)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dateTime
    }
}
