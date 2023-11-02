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

        // 2022-01-01 09:00 형태 입력
//        if (numericValue.length >= 6) {
//            val year = numericValue.substring(0, 4)
//            formattedDateTime += year
//            val month = numericValue.substring(4, 6).padStart(2, '0')
//
//            if (numericValue.length >= 8) {
//                val day = numericValue.substring(6, 8).padStart(2, '0')
//                formattedDateTime += "-$month-$day"
//
//                if (numericValue.length >= 10) {
//                    val hour = numericValue.substring(8, 10).padStart(2, '0')
//                    formattedDateTime += " $hour"
//
//                    if (numericValue.length >= 12) {
//                        val minute = numericValue.substring(10, 12).padStart(2, '0')
//                        formattedDateTime += ":$minute"
//                    } else {
//                        formattedDateTime += ":${numericValue.substring(10)}"
//                    }
//                } else {
//                    formattedDateTime += " ${numericValue.substring(8)}"
//                }
//            } else {
//                formattedDateTime += "-$month-${numericValue.substring(6)}"
//            }
//        } else {
//            formattedDateTime = dateTime
//        }

        // 월 일 구분하는 입력
        // 월 입력 (01~12)
        if (numericValue.length >= 5) {
            val year = numericValue.substring(0, 4)
            formattedDateTime += year
            var month = ""

            if (numericValue.length == 5) {
                // 0, 1은 그냥 입력. 나머지는 0n으로 입력
                if(numericValue.substring(4, 5) == "0" || numericValue.substring(4, 5) == "1"){
                    month = numericValue.substring(4, 5)
                }else{
                    month = "0" + numericValue.substring(4, 5)
                }
                formattedDateTime += "-$month"
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
                formattedDateTime += "-$month"
            }

            // 일 입력 (01~28,29,30,31)
            if (numericValue.length >= 7) {
                month = numericValue.substring(4, 6)
                var day = ""

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
                    formattedDateTime += "-$month-$day"
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
                    formattedDateTime += "-$month-$day"
                }

                // 시간 입력 (00 ~ 23)
                if (numericValue.length >= 9) {
                    day = numericValue.substring(6, 8)
                    var hour = ""

                    if (numericValue.length == 9) {
                        // 0, 1, 2는 그냥 입력. 나머지는 0n으로 입력
                        when(numericValue.substring(8, 9)){
                            "0", "1", "2"-> {
                                hour = numericValue.substring(8, 9)
                            }
                            else -> {
                                hour = "0" + numericValue.substring(8, 9)
                            }
                        }
                        formattedDateTime += "-$month-$day $hour"
                    }
                    if (numericValue.length == 10) {
                        hour = numericValue.substring(8,9)

                        when(hour){
                            // 0, 1뒤는 그냥 입력, 2뒤는 3까지
                            "0", "1" -> {
                                hour += numericValue.substring(9, 10)
                            }
                            "2" -> {
                                when(numericValue.substring(9, 10)){
                                    "0", "1", "2", "3" -> {
                                        hour += numericValue.substring(9, 10)
                                    }
                                }
                            }
                        }
                        formattedDateTime += "-$month-$day $hour"
                    }

                    // 분 입력 (00 ~ 59)
                    if (numericValue.length >= 11) {
                        hour = numericValue.substring(8, 10)
                        var minute = ""

                        // 일 구분
                        if (numericValue.length == 11) {
                            // 0, 1, 2, 3, 4, 5는 그냥 입력. 나머지는 0n으로 입력
                            when(numericValue.substring(10, 11)){
                                "0", "1", "2", "3", "4", "5" -> {
                                    minute = numericValue.substring(10, 11)
                                }
                                else -> {
                                    minute = "0" + numericValue.substring(10, 11)
                                }
                            }
                            formattedDateTime += "-$month-$day $hour:$minute"
                        }
                        if (numericValue.length == 12) {
                            minute = numericValue.substring(10,11)

                            when(minute){
                                // 0, 1, 2, 3, 4, 5 뒤는 그냥 입력
                                "0", "1", "2", "3", "4", "5" -> {
                                    minute += numericValue.substring(11, 12)
                                }
                            }
                            formattedDateTime += "-$month-$day $hour:$minute"
                        }
                    }
                }
            }
        } else {
            formattedDateTime = dateTime
        }

//        if(numericValue.length == 5 && numericValue.substring(4, 5) == "0"){
//            formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length - 1)
//        }
//
//        if(numericValue.length == 7 && numericValue.substring(6, 7) == "0"){
//            formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length - 1)
//        }
//
//        if(numericValue.length == 9 && numericValue.substring(8, 9) == "0"){
//            formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length - 1)
//        }
//
//        if(numericValue.length == 11 && numericValue.substring(10, 11) == "0"){
//            formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length - 1)
//        }

        // 마지막 글자가 공백, 하이폰, 또는 콜론인 경우 제거
        if (numericValue.length > 0 && (formattedDateTime.endsWith(' ') || formattedDateTime.endsWith('-') || formattedDateTime.endsWith(':') || numericValue.isEmpty())) {
            formattedDateTime = formattedDateTime.substring(0, formattedDateTime.length - 1)
        }

        return formattedDateTime
    }
}
