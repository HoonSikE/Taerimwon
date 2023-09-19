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

        val formattedDateTime = formatDateTime(s.toString())
        isFormatting = true
        editText.setText(formattedDateTime)
        editText.setSelection(formattedDateTime.length)
        isFormatting = false
    }

    override fun afterTextChanged(s: Editable?) {
        // 텍스트 변경 후 이벤트
    }

    private fun formatDateTime(dateTime: String): String {
        val numericValue = dateTime.replace(Regex("\\D"), "") // 숫자만 추출

        var formattedDateTime = ""

        if (numericValue.length >= 6) {
            val year = numericValue.substring(0, 4)
            formattedDateTime += year
            val month = numericValue.substring(4, 6).padStart(2, '0')

            if (numericValue.length >= 8) {
                val day = numericValue.substring(6, 8).padStart(2, '0')
                formattedDateTime += "-$month-$day"

                if (numericValue.length >= 10) {
                    val hour = numericValue.substring(8, 10).padStart(2, '0')
                    formattedDateTime += " $hour"

                    if (numericValue.length >= 12) {
                        val minute = numericValue.substring(10, 12).padStart(2, '0')
                        formattedDateTime += ":$minute"
                    } else {
                        formattedDateTime += ":${numericValue.substring(10)}"
                    }
                } else {
                    formattedDateTime += " ${numericValue.substring(8)}"
                }
            } else {
                formattedDateTime += "-$month-${numericValue.substring(6)}"
            }
        } else {
            formattedDateTime = dateTime
        }

        // 마지막 글자가 공백, 하이폰, 또는 콜론인 경우 제거
        if (numericValue.length > 0 && (formattedDateTime.endsWith(' ') || formattedDateTime.endsWith('-') || formattedDateTime.endsWith(':') || numericValue.isEmpty())) {
            formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length - 1)
        }

        return formattedDateTime
    }
}
