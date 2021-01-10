package com.example.teacherassistant.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.databinding.ReportItemViewBinding

class ReportListAdapter :
    ListAdapter<ReportItem, ReportListAdapter.ReportItemHolder>(ReportDiffCallback()) {


    inner class ReportItemHolder(val binding: ReportItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ReportItem) {
            binding.report = item

        }
    }

    override fun onBindViewHolder(holder: ReportListAdapter.ReportItemHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportListAdapter.ReportItemHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReportItemViewBinding.inflate(layoutInflater, parent, false)
        return ReportItemHolder(binding)
    }


}

class ReportDiffCallback : DiffUtil.ItemCallback<ReportItem>() {

    override fun areItemsTheSame(oldItem: ReportItem, newItem: ReportItem): Boolean {
        return (oldItem.courseName == newItem.courseName && oldItem.gradeNote == newItem.gradeNote
                && oldItem.gradeValue == newItem.gradeValue && oldItem.studentName == newItem.studentName)
    }


    override fun areContentsTheSame(oldItem: ReportItem, newItem: ReportItem): Boolean {
        return (oldItem.courseName == newItem.courseName && oldItem.gradeNote == newItem.gradeNote
                && oldItem.gradeValue == newItem.gradeValue && oldItem.studentName == newItem.studentName)
    }


}