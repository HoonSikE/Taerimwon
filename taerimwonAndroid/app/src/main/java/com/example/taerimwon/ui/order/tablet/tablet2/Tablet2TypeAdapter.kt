package com.example.taerimwon.ui.order.tablet.tablet2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.databinding.ItemTabletTypeBinding
import com.example.taerimwon.di.ApplicationClass

class Tablet2TypeAdapter(private val context: Context): RecyclerView.Adapter<Tablet2TypeAdapter.TabletTypeListViewHolder>() {
    private var boneTabletTypeList = mutableListOf<String>()
    lateinit var onItemClickListener: (View, String) -> Unit
    // 클릭된 아이템의 위치를 저장하는 변수
    private var selectedItemPosition = 0

    fun setListDate(data: MutableList<String>){
        boneTabletTypeList = data
    }

    // 클릭된 아이템 설정 메서드
    fun setSelectedItem(position: Int) {
        selectedItemPosition = position
        ApplicationClass.prefs.boneTabletTypePosition = position
        notifyDataSetChanged() // 변경 사항을 적용하기 위해 어댑터에 알립니다.
    }

    fun updateList(data: MutableList<String>){
        boneTabletTypeList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabletTypeListViewHolder {
        return TabletTypeListViewHolder(
            ItemTabletTypeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context // Context를 생성자를 통해 전달
        ).apply {
            bindOnItemClickListener(onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: TabletTypeListViewHolder, position: Int) {
        holder.bind(boneTabletTypeList[position])

        // 클릭된 아이템에 따라 테두리를 설정합니다.
        if (position == selectedItemPosition) {
            holder.itemView.setBackgroundResource(R.drawable.border) // 클릭된 아이템에 테두리 적용
        } else {
            holder.itemView.background = null // 클릭되지 않은 아이템에 테두리 제거
        }
    }


    override fun getItemCount(): Int {
        return boneTabletTypeList.size
    }

//        val text_tablet_type: TextView = view.findViewById(R.id.text_tablet_type)

    inner class TabletTypeListViewHolder(private val binding: ItemTabletTypeBinding, private val context: Context)
        : RecyclerView.ViewHolder(binding.root) {
        lateinit var tableteType: String
        lateinit var layoutTabletType: LinearLayout

        fun bind(data: String) {
            val datas = data.split(" ")
            binding.textTabletType.text = "[" + datas[1] + "]"

            // 이미지 이름을 문자열로 정의합니다.
            val imageName = "img_tablet_sample" + datas[0]
            // 이미지 리소스 ID를 가져옵니다.
            val imageResource = context.resources.getIdentifier(imageName, "drawable", context.packageName)

            binding.imageTabletSample.setImageResource(imageResource)
            tableteType = data
            layoutTabletType = binding.layoutTabletType
        }

        fun bindOnItemClickListener(onItemClickListener: (View, String) -> Unit) {
            binding.root.setOnClickListener {
                onItemClickListener(it, tableteType)
                setSelectedItem(adapterPosition)
            }
        }
    }
}