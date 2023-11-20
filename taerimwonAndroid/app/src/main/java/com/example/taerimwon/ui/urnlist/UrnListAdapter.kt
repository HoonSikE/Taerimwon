package com.example.taerimwon.ui.urnlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R
import com.example.taerimwon.data.dto.urn.UrnItem
import com.example.taerimwon.databinding.ItemEngraveTypeBinding
import com.example.taerimwon.databinding.ItemHomeUrnListBinding
import com.example.taerimwon.di.ApplicationClass

class UrnListAdapter(private val context: Context): RecyclerView.Adapter<UrnListAdapter.UrnListViewHolder>() {
    private var urnList = mutableListOf<UrnItem>()
    lateinit var onItemClickListener: (View, String, Int) -> Unit
    // 클릭된 아이템의 위치를 저장하는 변수
    private var selectedItemPosition = 0

    fun setListDate(data: MutableList<UrnItem>){
        urnList = data
    }

    fun updateList(data: MutableList<UrnItem>){
        urnList = data
        notifyDataSetChanged()
    }

    // 클릭된 아이템 설정 메서드
    fun setSelectedItem(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged() // 변경 사항을 적용하기 위해 어댑터에 알립니다.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UrnListViewHolder {
        return UrnListViewHolder(
            ItemHomeUrnListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context // Context를 생성자를 통해 전달
        ).apply {
            bindOnItemClickListener(onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: UrnListViewHolder, position: Int) {
        holder.bind(urnList[position])
    }


    override fun getItemCount(): Int {
        return urnList.size
    }

    inner class UrnListViewHolder(private val binding: ItemHomeUrnListBinding, private val context: Context)
        : RecyclerView.ViewHolder(binding.root) {
        lateinit var urnName : String
        var imageName = 0

        fun bind(data: UrnItem) {
            urnName = data.urnItem
            binding.textView.text = urnName
            // 이미지 이름을 문자열로 정의합니다.
            imageName = data.flagImage
            binding.imageView.setImageResource(imageName)
        }

        fun bindOnItemClickListener(onItemClickListener: (View, String, Int) -> Unit) {
            binding.root.setOnClickListener {
                onItemClickListener(it, urnName, imageName)
                setSelectedItem(adapterPosition)
            }
        }
    }
}