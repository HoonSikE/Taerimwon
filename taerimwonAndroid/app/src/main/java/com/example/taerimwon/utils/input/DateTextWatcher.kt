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

        // 2022-01-01 형태 입력
//        if (numericValue.length >= 6) {
//            val year = numericValue.substring(0, 4)
//            formattedDate += year
//            val month = numericValue.substring(4, 6).padStart(2, '0')
//
//            if (numericValue.length >= 8) {
//                val day = numericValue.substring(6, 8).padStart(2, '0')
//                formattedDate += "-$month-$day"
//            } else {
//                formattedDate += "-$month-${numericValue.substring(6)}"
//            }
//        } else {
//            formattedDate = date
//        }

        // 월 일 구분하는 입력
        // 월 구분
        if (numericValue.length >= 5) {
            val year = numericValue.substring(0, 4)
            formattedDate += year
            var month = ""

            if (numericValue.length == 5) {
                // 0, 1은 그냥 입력. 나머지는 0n으로 입력
                if(numericValue.substring(4, 5) == "0" || numericValue.substring(4, 5) == "1"){
                    month = numericValue.substring(4, 5)
                }else{
                    month = "0" + numericValue.substring(4, 5)
                }
                formattedDate += "-$month"
            }
            if (numericValue.length == 6) {
                if(numericValue.substring(4, 5) == "0"){
                    // 00빼고 다 됨
                    if(numericValue.substring(5, 6) == "0"){
                        month = numericValue.substring(4, 5)
                    }else{
                        month = numericValue.substring(4, 6)
                    }
                }else if(numericValue.substring(4, 5) == "1"){
                    // 10, 11, 12만 허용
                    if(numericValue.substring(5, 6) == "0" || numericValue.substring(5, 6) == "1" || numericValue.substring(5, 6) == "2"){
                        month = numericValue.substring(4, 6)
                    }else{
                        month = numericValue.substring(4, 5)
                    }
                }
                formattedDate += "-$month"
            }

            if (numericValue.length >= 7) {
                month = numericValue.substring(4, 6)
                var day = ""

                // 일 구분
                if (numericValue.length == 7) {
                    if(Integer.parseInt(month) in 1..12){
                        // 0, 1, 2, 3은 그냥 입력. 나머지는 0n으로 입력
                        if(month != "02"){
                            when(numericValue.substring(6, 7)){
                                "0", "1", "2", "3" -> {
                                    day = numericValue.substring(6, 7)
                                }
                                else -> {
                                    day = "0" + numericValue.substring(6, 7)
                                }
                            }
                        }
                        // 0, 1, 2은 그냥 입력. 나머지는 0n으로 입력
                        else{
                            if(numericValue.substring(6, 7) == "0" || numericValue.substring(6, 7) == "1"
                                || numericValue.substring(6, 7) == "2"){
                                day = numericValue.substring(6, 7)
                            }else{
                                day = "0" + numericValue.substring(6, 7)
                            }
                        }
                    }
                    formattedDate += "-$month-$day"
                }
                if (numericValue.length == 8) {
                    day = numericValue.substring(6,7)

                    when(month){
                        // 0, 1, 2 뒤는 그냥 입력, 나머지는 월에 따라 진행
                        "01", "03", "05", "07", "08", "10", "12" -> {
                            when(day){
                                "0", "1", "2" -> {
                                    day += numericValue.substring(7, 8)
                                }
                                "3" -> {
                                    if(numericValue.substring(7, 8) == "0" || numericValue.substring(7, 8) == "1")
                                        day += numericValue.substring(7, 8)
                                }
                            }
                        }
                        "04", "06", "09", "11"  -> {
                            when(day){
                                "0", "1", "2" -> {
                                    day += numericValue.substring(7, 8)
                                }
                                "3" -> {
                                    if(numericValue.substring(7, 8) == "0")
                                        day += numericValue.substring(7, 8)
                                }
                            }
                        }
                        // 0, 1 뒤는 그냥 입력, 나머지는 윤년에 따라 진행
                        // 윤년 29, 일반 28
                        "02" -> {
                            var leapYear = false
                            val yearTmp = Integer.parseInt(year)
                            if (yearTmp % 4 == 0 && (yearTmp % 100 != 0 || yearTmp % 400 == 0)) {
                                leapYear = true
                            }

                            when(day){
                                "0" -> {
                                    if(numericValue.substring(7, 8) != "0")
                                        day += numericValue.substring(7, 8)
                                }
                                "1" -> {
                                    day += numericValue.substring(7, 8)
                                }
                                "2" -> {
                                    when(numericValue.substring(7, 8)) {
                                        "0", "1", "2", "3", "4", "5", "6", "7", "8" -> {
                                            day += numericValue.substring(7, 8)
                                        }
                                        "9" -> {
                                            if(leapYear)
                                                day += numericValue.substring(7, 8)
                                        }
                                    }
                                }
                            }
                        }
                    }
                    formattedDate += "-$month-$day"
                }
            }
        } else {
            formattedDate = date
        }

//        if(numericValue.length == 5 && numericValue.substring(4, 5) == "0"){
//            formattedDate = formattedDate.substring(0, formattedDate.length - 1)
//        }
//
//        if(numericValue.length == 7 && numericValue.substring(6, 7) == "0"){
//            formattedDate = formattedDate.substring(0, formattedDate.length - 1)
//        }

        // 마지막 글자가 하이픈인 경우 제거
        if (numericValue.length > 0 && (formattedDate.endsWith('-') || numericValue.isEmpty())) {
            formattedDate = formattedDate.substring(0, formattedDate.length - 1)
        }

        return formattedDate
    }
}

