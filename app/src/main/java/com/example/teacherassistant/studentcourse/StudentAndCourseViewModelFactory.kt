package com.example.teacherassistant.studentcourse

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.database.dao.StudentCourseDao

class StudentAndCourseViewModelFactory(
    private val dataSource: StudentCourseDao,
    private val application: Application,
    private val course_id: Int?,
    private val student_id: Int?
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentAndCourseViewModel::class.java)) {
            return StudentAndCourseViewModel(dataSource, course_id, student_id, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}