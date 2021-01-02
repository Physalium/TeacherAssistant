package com.example.teacherassistant.studentcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.database.entities.Student
import com.example.teacherassistant.databinding.StudentInCourseAddItemViewBinding
import com.example.teacherassistant.students.StudentsDiffCallback

class StudentInCourseAddListAdapter(
    var addCallBack: ((c: Student) -> Unit)
) : ListAdapter<Student, StudentInCourseAddListAdapter.StudentHolder>(StudentsDiffCallback()) {

    inner class StudentHolder(val binding: StudentInCourseAddItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student) {
            binding.student = item
            binding.courseStudentAdd.setOnClickListener {
                addCallBack(item)
            }

            binding.executePendingBindings()
        }


    }

    override fun onBindViewHolder(
        holder: StudentInCourseAddListAdapter.StudentHolder,
        position: Int
    ) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentInCourseAddListAdapter.StudentHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StudentInCourseAddItemViewBinding.inflate(layoutInflater, parent, false)
        return StudentHolder(binding)
    }


}

