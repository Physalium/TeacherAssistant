package com.example.teacherassistant.studentcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.database.entities.Student
import com.example.teacherassistant.databinding.StudentInCourseItemViewBinding
import com.example.teacherassistant.students.StudentsDiffCallback

class StudentsInCourseListAdapter(
    var deleteCallback: ((c: Student) -> Unit)
) : ListAdapter<Student, StudentsInCourseListAdapter.StudentHolder>(StudentsDiffCallback()) {

    inner class StudentHolder(val binding: StudentInCourseItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student) {
            binding.student = item
            binding.courseStudentDelete.setOnClickListener {
                deleteCallback(item)
            }

            binding.executePendingBindings()
        }


    }

    override fun onBindViewHolder(
        holder: StudentsInCourseListAdapter.StudentHolder,
        position: Int
    ) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsInCourseListAdapter.StudentHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StudentInCourseItemViewBinding.inflate(layoutInflater, parent, false)
        return StudentHolder(binding)
    }


}

class StudentsDiffCallback : DiffUtil.ItemCallback<Student>() {

    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return (oldItem.lastName == newItem.lastName && oldItem.firstName == newItem.firstName)
    }


}