package com.example.taerimwon.ui.order.urn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import com.example.taerimwon.R
import com.example.taerimwon.data.dto.urn.UrnItem
import java.util.*

class UrnAutoCompleteAdapter(context: Context, resource: Int, private val items: List<UrnItem>) : ArrayAdapter<UrnItem>(context, resource, items) {
    private val originalItems = items.toMutableList() // 원본 아이템 유지

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tmpConvertView = convertView

        if (tmpConvertView == null) {
            tmpConvertView = LayoutInflater.from(context).inflate(
                R.layout.item_urn_list, parent, false
            )
        }

        val textView: TextView = tmpConvertView!!.findViewById(R.id.text_view)
        val imageView: ImageView = tmpConvertView.findViewById(R.id.image_view)

        //getItem(position) 코드로 자동완성 될 아이템을 가져온다
        val urnItem: UrnItem? = getItem(position)

        if (urnItem != null) {
            textView.setText(urnItem.urnItem)
            imageView.setImageResource(urnItem.flagImage)
        }
        return tmpConvertView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                val filteredItems = mutableListOf<UrnItem>()

                if (constraint.isNullOrBlank()) {
                    // 검색어가 비어있으면 모든 원본 아이템을 유지합니다.
                    filteredItems.addAll(originalItems)
                } else {
                    val filterPattern = constraint.toString().toLowerCase().replace(" ", "").trim()
                    val filterInitialSound = getInitialSounds(constraint.toString().toLowerCase().replace(" ", "").trim())

                    for (item in originalItems) {
                        val itemInitialSound = getInitialSounds(item.urnItem.toLowerCase().replace(" ", "").trim())

                        // 여기서 초성과 부분 검색을 모두 적용합니다.
                        if (item.urnItem.toLowerCase().replace(" ", "").trim().contains(filterPattern) || itemInitialSound.contains(filterInitialSound)) {
                            filteredItems.add(item)
                        }
                    }
                }

                filterResults.values = filteredItems
                filterResults.count = filteredItems.size
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                // 필터링된 결과를 AutoCompleteTextView에 게시하고 표시 업데이트를 처리합니다.
                clear()
                if (results != null && results.count > 0) {
                    addAll(results.values as List<UrnItem>)
                } else {
                    // 결과가 없는 경우 AutoCompleteTextView를 초기화합니다.
                    notifyDataSetInvalidated()
                }
            }
        }
    }

    private fun getInitialSounds(text: String): String {
        val jaum = StringBuilder()
        for (c in text) {
            if (c.isHangul()) {
                jaum.append(getInitialSound(c))
            }else{
                jaum.append(c)
            }
        }
        println(jaum)
        return jaum.toString()
    }

    // 가: 44032
    val HANGUL_BEGIN_UNICODE = '가'
    // 힣: 55203
    val HANGUL_LAST_UNICODE = '힣'
    // 각 자음마다 가지는 글자수: 588
    val HANGUL_BASE_UNIT = 588
    private fun getInitialSound(c: Char): Char {
        val INITIAL_SOUND = charArrayOf(
            'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
        )
        val index = (c - HANGUL_BEGIN_UNICODE) / HANGUL_BASE_UNIT
        return INITIAL_SOUND[index]
    }

    private fun Char.isHangul(): Boolean {
        return this in HANGUL_BEGIN_UNICODE..HANGUL_LAST_UNICODE
    }
}