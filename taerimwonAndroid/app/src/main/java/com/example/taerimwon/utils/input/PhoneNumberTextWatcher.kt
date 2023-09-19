package com.example.taerimwon.utils.input

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PhoneNumberTextWatcher(private val editText: EditText) : TextWatcher {
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

        val phoneText = s.toString().replace("-", "")

        val formattedText = when {
            phoneText.length >= 11 -> {
                isFormatting = true
                val formatted = StringBuilder()
                for (i in 0 until 11) {
                    formatted.append(phoneText[i])
                    if (i == 2 || i == 6) {
                        formatted.append("-")
                    }
                }
                isFormatting = false
                formatted.toString()
            }
            else -> phoneText
        }

        if (s.toString() != formattedText) {
            editText.setText(formattedText)
            editText.setSelection(formattedText.length)
        }
    }

    override fun afterTextChanged(s: Editable?) {
        // 텍스트 변경 후 이벤트
    }
}
