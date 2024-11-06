package com.example.hoursappandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hoursappandroid.databinding.HistoryObjectItemBinding

class HistoryObjectAdapter (private val historyList : List<HistoryRecord>) :
    RecyclerView.Adapter<HistoryObjectAdapter.HistoryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryObjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val record = historyList[position]
        holder.bind(record)
    }

    override fun getItemCount() = historyList.size

    inner class HistoryViewHolder(private val binding: HistoryObjectItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(record: HistoryRecord) {
            binding.actionTextView.text = record.action
            binding.timeTextView.text = record.time
            binding.dateTextView.text = record.date
            binding.objectTextView.text = record.objectName
        }
    }
}