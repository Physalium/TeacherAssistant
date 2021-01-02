package com.example.teacherassistant.studentcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.courses.CoursesDiffCallback
import com.example.teacherassistant.database.entities.Course
import com.example.teacherassistant.databinding.StudentCoursesItemViewBinding

class StudentCoursesListAdapter(
    var deleteCallback: ((c: Course) -> Unit)
) : ListAdapter<Course, StudentCoursesListAdapter.CourseHolder>(CoursesDiffCallback()) {

    inner class CourseHolder(val binding: StudentCoursesItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Course) {
            binding.course = item
            binding.studentDetailsCourseDelete.setOnClickListener {
                deleteCallback(item)
            }

            binding.executePendingBindings()
        }


    }

    override fun onBindViewHolder(
        holder: StudentCoursesListAdapter.CourseHolder,
        position: Int
    ) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentCoursesListAdapter.CourseHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StudentCoursesItemViewBinding.inflate(layoutInflater, parent, false)
        return CourseHolder(binding)
    }


}

