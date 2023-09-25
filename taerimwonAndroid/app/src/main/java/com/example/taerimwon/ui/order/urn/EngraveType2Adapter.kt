package com.example.taerimwon.ui.order.urn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.databinding.ItemEngraveType2Binding

class EngraveType2Adapter: RecyclerView.Adapter<EngraveType2Adapter.EngraveType2ListViewHolder>() {
    private var engraveType2List = mutableListOf<String>()
    lateinit var onItemClickListener: (View, String) -> Unit

    fun setListDate(data: MutableList<String>){
        engraveType2List = data
    }

    fun updateList(data: MutableList<String>){
        engraveType2List = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngraveType2ListViewHolder {
        return EngraveType2ListViewHolder(
            ItemEngraveType2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            bindOnItemClickListener(onItemClickListener)
        }

//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_engrave_type2, parent, false)
//        return EngraveType2ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: EngraveType2ListViewHolder, position: Int) {
        holder.bind(engraveType2List[position])
    }


    override fun getItemCount(): Int {
        return engraveType2List.size
    }

    class EngraveType2ListViewHolder(private val binding: ItemEngraveType2Binding)
        : RecyclerView.ViewHolder(binding.root) {
        lateinit var engraveType2: String

        fun bind(data: String) {
            binding.textEngraveType.text = "[" + data + "]"
            engraveType2 = data
        }

        fun bindOnItemClickListener(onItemClickListener: (View, String) -> Unit ) {
            binding.root.setOnClickListener {
                onItemClickListener(it, engraveType2)
            }
        }
    }
}