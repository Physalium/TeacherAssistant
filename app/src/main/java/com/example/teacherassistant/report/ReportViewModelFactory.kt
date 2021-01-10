package com.example.teacherassistant.report

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.database.dao.GradeDao
import com.example.teacherassistant.database.dao.StudentCourseDao

class ReportViewModelFactory(
    private val dataSource: GradeDao,
    private val dataSourceStudentAndCourse: StudentCourseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            return ReportViewModel(
                dataSource,
                dataSourceStudentAndCourse,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}