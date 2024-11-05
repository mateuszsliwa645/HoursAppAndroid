package com.example.hoursappandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hoursappandroid.databinding.HistoryItemBinding

class HistoryAdapter (private val historyList : List<HistoryRecord>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val record = historyList[position]
        holder.bind(record)
    }

    override fun getItemCount() = historyList.size

    inner class HistoryViewHolder(private val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(record: HistoryRecord) {
            binding.actionTextView.text = record.action
            binding.timeTextView.text = record.time
            binding.dateTextView.text = record.date
        }
    }
}