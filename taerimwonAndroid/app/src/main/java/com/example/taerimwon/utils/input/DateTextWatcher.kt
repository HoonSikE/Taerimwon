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

        val formattedDate = formatDate(s.toString())
        isFormatting = true
        editText.setText(formattedDate)
        editText.setSelection(formattedDate.length)
        isFormatting = false
    }

    override fun afterTextChanged(s: Editable?) {
        // 텍스트 변경 후 이벤트
    }

    private fun formatDate(date: String): String {
        val numericValue = date.replace(Regex("\\D"), "") // 숫자만 추출

        var formattedDate = ""

        if (numericValue.length >= 6) {
            val year = numericValue.substring(0, 4)
            formattedDate += year
            val month = numericValue.substring(4, 6).padStart(2, '0')

            if (numericValue.length >= 8) {
                val day = numericValue.substring(6, 8).padStart(2, '0')
                formattedDate += "-$month-$day"
            } else {
                formattedDate += "-$month-${numericValue.substring(6)}"
            }
        } else {
            formattedDate = date
        }

        // 마지막 글자가 하이픈인 경우 제거
        if (numericValue.length > 0 && (formattedDate.endsWith('-') || numericValue.isEmpty())) {
            formattedDate = formattedDate.substring(0, formattedDate.length - 1)
        }

        return formattedDate
    }
}

