package com.example.teacherassistant.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.database.entities.Course

class CourseListAdapter(
    var courses: LiveData<List<Course>>
) : RecyclerView.Adapter<CourseListAdapter.CourseHolder>() {
    inner class CourseHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val course = courses.value?.get(position)
        holder.textView.text = course?.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item_view, parent, false) as TextView
        return CourseHolder(view)
    }

    override fun getItemCount(): Int = courses.value?.size ?: 0
}