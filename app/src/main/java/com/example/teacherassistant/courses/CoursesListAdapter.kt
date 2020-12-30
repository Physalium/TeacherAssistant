package com.example.teacherassistant.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.database.entities.Course
import com.example.teacherassistant.databinding.CourseItemViewBinding

class CourseListAdapter(
    var deleteCallback: ((c: Course) -> Unit)
) : ListAdapter<Course, CourseListAdapter.CourseHolder>(CoursesDiffCallback()) {


    inner class CourseHolder(val binding: CourseItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Course) {
            binding.course = item
            binding.courseDelete.setOnClickListener {
                if (item != null) deleteCallback(item)
            }
            binding.courseEdit.setOnClickListener {
                val action =
                    CoursesListFragmentDirections.actionEditCourse(item.id, item.name)
                itemView.findNavController().navigate(action)
            }
            binding.executePendingBindings()
        }


    }

    override fun onBindViewHolder(holder: CourseListAdapter.CourseHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseListAdapter.CourseHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CourseItemViewBinding.inflate(layoutInflater, parent, false)
        return CourseHolder(binding)
    }


}

class CoursesDiffCallback : DiffUtil.ItemCallback<Course>() {

    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem == newItem
    }


}