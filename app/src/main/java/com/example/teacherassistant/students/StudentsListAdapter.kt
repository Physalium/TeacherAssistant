package com.example.teacherassistant.students

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.database.entities.Student
import com.example.teacherassistant.databinding.StudentItemViewBinding

class StudentsListAdapter(
    var deleteCallback: ((c: Student) -> Unit)
) : ListAdapter<Student, StudentsListAdapter.StudentHolder>(StudentsDiffCallback()) {


    inner class StudentHolder(val binding: StudentItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Student) {
            binding.student = item
            binding.studentDelete.setOnClickListener {
                if (item != null) deleteCallback(item)
            }
            binding.studentDetails.setOnClickListener {
                val action =
                    StudentsListFragmentDirections.actionDetailsStudent(
                        item.id,
                        item.firstName,
                        item.lastName
                    )
                itemView.findNavController().navigate(action)
            }
            binding.studentEdit.setOnClickListener {
                val action =
                    StudentsListFragmentDirections.actionEditStudent(
                        item.id,
                        item.firstName,
                        item.lastName
                    )
                itemView.findNavController().navigate(action)
            }


            binding.executePendingBindings()
        }


    }

    override fun onBindViewHolder(holder: StudentsListAdapter.StudentHolder, position: Int) {

        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentsListAdapter.StudentHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = StudentItemViewBinding.inflate(layoutInflater, parent, false)
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