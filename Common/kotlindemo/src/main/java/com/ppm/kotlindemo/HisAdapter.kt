package com.ppm.kotlindemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HisAdapter : RecyclerView.Adapter<HisAdapter.HisViewHolder>() {

    var mData: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HisViewHolder {
        return HisViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_his,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mData.size;
    }

    override fun onBindViewHolder(holder: HisViewHolder, position: Int) {
        holder.textView.text = mData[position]
    }

    class HisViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.test);
    }
}