package com.example.teacherassistant.studentcourse

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.database.dao.StudentCourseDao
import com.example.teacherassistant.database.entities.StudentAndCourse
import kotlinx.coroutines.launch

class StudentAndCourseViewModel(
    dataSource: StudentCourseDao,
    course_id: Int,
    application: Application
) : ViewModel() {

    val database = dataSource
    val studentsInCourse = database.getByCourseId(course_id)
    val studentsNotInCourse = database.getNotInCourseById(course_id)

    fun addStudentToCourse(student_id: Int, course_id: Int) {
        viewModelScope.launch {
            database.insert(StudentAndCourse(0, student_id, course_id))
        }
    }

    fun deleteStudentFromCourse(student_id: Int, course_id: Int) {
        viewModelScope.launch {
            database.delete(course_id, student_id)
        }
    }
}