package com.example.teacherassistant.grades

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.database.dao.GradeDao
import com.example.teacherassistant.database.dao.StudentCourseDao
import com.example.teacherassistant.database.entities.Grade
import com.example.teacherassistant.database.entities.StudentAndCourse
import kotlinx.coroutines.launch
import java.util.*

class GradesViewModel(
    dataSource: GradeDao,
    dataSourceStudentAndCourse: StudentCourseDao,
    application: Application,
    course_id: Int?,
    student_id: Int?
) : ViewModel() {
    val database = dataSource
    val databaseStudentAndCourse = dataSourceStudentAndCourse
    val grades = if (student_id != null && course_id != null) database.getForStudentAndCourse(
        student_id,
        course_id
    ) else database.getAll()


    fun addGrade(student_id: Int, course_id: Int, grade: Double, note: String) {
        lateinit var studentAndCourse: StudentAndCourse

        viewModelScope.launch {
            viewModelScope.launch {
                studentAndCourse =
                    databaseStudentAndCourse.getByStudentAndCourse(course_id, student_id)
            }.join()
            if (studentAndCourse != null) {
                var grade = Grade(0, studentAndCourse.id, grade, note, Date())
                database.insert(grade)
            }

        }
    }

    fun deleteGrade(grade_id: Int) {
        viewModelScope.launch {
            database.delete(grade_id)
        }
    }
}