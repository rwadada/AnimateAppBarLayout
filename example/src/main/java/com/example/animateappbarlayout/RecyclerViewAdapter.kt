package com.example.animateappbarlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(dataset: List<String>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var dataset: List<String> = dataset

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val textView: TextView = v.findViewById(R.id.item_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text: String = dataset[position]
        holder.textView.text = text
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}