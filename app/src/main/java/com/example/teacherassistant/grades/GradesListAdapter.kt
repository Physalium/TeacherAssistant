package com.example.teacherassistant.grades

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.database.entities.Grade
import com.example.teacherassistant.databinding.GradeItemViewBinding

class GradesListAdapter(
    var deleteCallback: ((c: Grade) -> Unit)
) : ListAdapter<Grade, GradesListAdapter.GradeHolder>(GradesDiffCallback()) {


    inner class GradeHolder(val binding: GradeItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Grade) {
            binding.grade = item
            binding.deleteGrade.setOnClickListener {
                deleteCallback(item)
            }
        }
    }

    override fun onBindViewHolder(holder: GradesListAdapter.GradeHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GradesListAdapter.GradeHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GradeItemViewBinding.inflate(layoutInflater, parent, false)
        return GradeHolder(binding)
    }


}

class GradesDiffCallback : DiffUtil.ItemCallback<Grade>() {

    override fun areItemsTheSame(oldItem: Grade, newItem: Grade): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Grade, newItem: Grade): Boolean {
        return oldItem.id == newItem.id
    }


}