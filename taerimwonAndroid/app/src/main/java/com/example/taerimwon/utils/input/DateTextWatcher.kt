package com.example.taerimwon.utils.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class DateTextWatcher(private val editText: EditText) : TextWatcher {
    private var isFormatting = false
    private var deletedCharIndex = -1

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // 이전 텍스트 변경 이벤트
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // 텍스트 변경 이벤트
        if (isFormatting) {
            return
        }

        val originalText = s.toString()
        val formattedText = formatToDateString(originalText)

        if (s.toString() != formattedText) {
            isFormatting = true
            editText.setText(formattedText)
            editText.setSelection(formattedText.length)
            isFormatting = false
        }
    }

    override fun afterTextChanged(s: Editable?) {
        // 텍스트 변경 후 이벤트
    }

    private fun formatToDateString(inputText: String): String {
        val cleanText = inputText.replace(Regex("[^0-9]"), "")
        if (cleanText.length >= 8) {
            try {
                val inputFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val date = inputFormat.parse(cleanText)
                return outputFormat.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return inputText
    }
}

