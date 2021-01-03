package com.example.teacherassistant.studentcourse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.courses.CoursesDiffCallback
import com.example.teacherassistant.database.entities.Course
import com.example.teacherassistant.databinding.StudentCoursesItemViewBinding
import com.example.teacherassistant.students.StudentDetailsFragmentDirections

class StudentCoursesListAdapter(
    var deleteCallback: ((c: Course) -> Unit), var student_id: Int, var student_name: String
) : ListAdapter<Course, StudentCoursesListAdapter.CourseHolder>(CoursesDiffCallback()) {

    inner class CourseHolder(val binding: StudentCoursesItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Course) {
            binding.course = item
            binding.studentDetailsCourseDelete.setOnClickListener {
                deleteCallback(item)
            }

            binding.studentDetailsCourseGrades.setOnClickListener {
                val action =
                    StudentDetailsFragmentDirections.actionStudentToGrades(
                        student_id, item.id, item.name, student_name
                    )
                itemView.findNavController().navigate(action)
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

