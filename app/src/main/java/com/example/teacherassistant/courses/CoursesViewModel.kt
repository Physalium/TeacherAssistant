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
    var currentCourse = Course(0, "")
    val courses = database.getAll()

    suspend fun insert(course: Course) {
        database.insert(course)
    }

    fun addCourse(name: String) {
        viewModelScope.launch {
            val newCourse = Course(0, name)
            insert(newCourse)
        }


    }
}