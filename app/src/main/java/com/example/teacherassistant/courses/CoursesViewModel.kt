package com.example.teacherassistant.courses

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.database.dao.CourseDao
import com.example.teacherassistant.database.entities.Course
import kotlinx.coroutines.launch

class CoursesViewModel(
    dataSource: CourseDao,
    application: Application
) : ViewModel() {

    val database = dataSource
    val courses = database.getAll()


    fun addCourse(name: String) {
        viewModelScope.launch {
            val newCourse = Course(0, name)
            database.insert(newCourse)
        }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch {
            database.delete(course)
        }
    }

    fun updateCourse(course: Course, newName: String) {
        viewModelScope.launch {
            database.update(Course(course.id, newName))
        }
    }
}