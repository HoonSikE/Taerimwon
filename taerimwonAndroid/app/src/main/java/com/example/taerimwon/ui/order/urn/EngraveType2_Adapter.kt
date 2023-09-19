package com.example.taerimwon.ui.order.urn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R

class EngraveType2_Adapter: RecyclerView.Adapter<EngraveType2_Adapter.EngraveType2ListViewHolder>() {
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
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_engrave_type2, parent, false)
        return EngraveType2ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: EngraveType2ListViewHolder, position: Int) {
        holder.text_engrave_type2.text = "[" + engraveType2List[position] + "]"
    }


    override fun getItemCount(): Int {
        return engraveType2List.size
    }

    inner class EngraveType2ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text_engrave_type2: TextView = view.findViewById(R.id.text_engrave_type2)
    }
}