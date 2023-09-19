package com.example.taerimwon.utils.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PhoneNumberTextWatcher(private val editText: EditText) : TextWatcher {
    private var isFormatting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // 이전 텍스트 변경 이벤트
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // 텍스트 변경 이벤트
        if (isFormatting) {
            return
        }

        val formattedPhone = formatPhoneNumber(s.toString())
        isFormatting = true
        editText.setText(formattedPhone)
        editText.setSelection(formattedPhone.length)
        isFormatting = false
    }

    override fun afterTextChanged(s: Editable?) {
        // 텍스트 변경 후 이벤트
    }

    private fun formatPhoneNumber(phoneNumber: String): String {
        val numericValue = phoneNumber.replace(Regex("\\D"), "") // 숫자만 추출

        var formattedPhone = ""

        if (numericValue.length >= 4) {
            val firstPart = numericValue.substring(0, 3)
            formattedPhone += "$firstPart-"
            val secondPart = numericValue.substring(3, minOf(7, numericValue.length))

            if (numericValue.length >= 8) {
                val thirdPart = numericValue.substring(7, minOf(11, numericValue.length))
                formattedPhone += "$secondPart-$thirdPart"
            } else {
                formattedPhone += secondPart
            }
        } else {
            formattedPhone = phoneNumber
        }

        // 마지막 글자가 하이픈인 경우 제거
        if (numericValue.length > 0 && (formattedPhone.endsWith('-') || numericValue.isEmpty())) {
            formattedPhone = formattedPhone.substring(0, formattedPhone.length - 1)
        }

        return formattedPhone
    }
}