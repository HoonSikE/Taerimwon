package com.example.taerimwon.ui.order.tablet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taerimwon.R

class TabletTypeAdapter: RecyclerView.Adapter<TabletTypeAdapter.TabletType2ListViewHolder>() {
    private var tabletType2List = mutableListOf<String>()
    lateinit var onItemClickListener: (View, String) -> Unit

    fun setListDate(data: MutableList<String>){
        tabletType2List = data
    }

    fun updateList(data: MutableList<String>){
        tabletType2List = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabletType2ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tablet_type, parent, false)
        return TabletType2ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TabletType2ListViewHolder, position: Int) {
        holder.text_tablet_type2.text = "[" + tabletType2List[position] + "]"
    }


    override fun getItemCount(): Int {
        return tabletType2List.size
    }

    inner class TabletType2ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text_tablet_type2: TextView = view.findViewById(R.id.text_tablet_type2)
    }
}