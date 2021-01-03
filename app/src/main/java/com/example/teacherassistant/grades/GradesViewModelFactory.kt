package com.example.teacherassistant.grades

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.database.dao.GradeDao
import com.example.teacherassistant.database.dao.StudentCourseDao

class GradesViewModelFactory(
    private val dataSource: GradeDao,
    private val dataSourceStudentAndCourse: StudentCourseDao,
    private val application: Application,
    private val course_id: Int?,
    private val student_id: Int?
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradesViewModel::class.java)) {
            return GradesViewModel(
                dataSource,
                dataSourceStudentAndCourse,
                application,
                course_id,
                student_id
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}