package com.example.taerimwon.ui.order.urn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.databinding.ItemEngraveTypeBinding
import com.example.taerimwon.di.ApplicationClass

class EngraveType2Adapter(private val context: Context): RecyclerView.Adapter<EngraveType2Adapter.EngraveType2ListViewHolder>() {
    private var engraveType2List = mutableListOf<String>()
    lateinit var onItemClickListener: (View, String) -> Unit
    // 클릭된 아이템의 위치를 저장하는 변수
    private var selectedItemPosition = 0

    fun setListDate(data: MutableList<String>){
        engraveType2List = data
    }

    fun updateList(data: MutableList<String>){
        engraveType2List = data
        notifyDataSetChanged()
    }

    // 클릭된 아이템 설정 메서드
    fun setSelectedItem(position: Int) {
        selectedItemPosition = position
        ApplicationClass.prefs.engraveType2Position = position
        notifyDataSetChanged() // 변경 사항을 적용하기 위해 어댑터에 알립니다.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngraveType2ListViewHolder {
        return EngraveType2ListViewHolder(
            ItemEngraveTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context // Context를 생성자를 통해 전달
        ).apply {
            bindOnItemClickListener(onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: EngraveType2ListViewHolder, position: Int) {
        holder.bind(engraveType2List[position])

        // 클릭된 아이템에 따라 테두리를 설정합니다.
        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.drawable.black_border) // 클릭된 아이템에 테두리 적용
        } else {
            holder.itemView.background = null // 클릭되지 않은 아이템에 테두리 제거
        }
    }


    override fun getItemCount(): Int {
        return engraveType2List.size
    }

    inner class EngraveType2ListViewHolder(private val binding: ItemEngraveTypeBinding, private val context: Context)
        : RecyclerView.ViewHolder(binding.root) {
        lateinit var engraveType2: String
        lateinit var layoutEngraveType: LinearLayout

        fun bind(data: String) {
            val datas = data.split(" ")
            binding.textEngraveType.text = "[" + datas[1] + "]"

            // 이미지 이름을 문자열로 정의합니다.
            val imageName = "img_urn_sample" + datas[0]
            // 이미지 리소스 ID를 가져옵니다.
            val imageResource = context.resources.getIdentifier(imageName, "drawable", context.packageName)

            binding.imageEngraveSample.setImageResource(imageResource)

            engraveType2 = data
            layoutEngraveType = binding.layoutEngraveType
        }

        fun bindOnItemClickListener(onItemClickListener: (View, String) -> Unit ) {
            binding.root.setOnClickListener {
                onItemClickListener(it, engraveType2)
                setSelectedItem(adapterPosition)
            }
        }
    }
}