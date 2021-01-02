package com.example.teacherassistant.students

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.database.dao.StudentDao
import com.example.teacherassistant.database.entities.Student
import kotlinx.coroutines.launch

class StudentsViewModel(
    dataSource: StudentDao,
    application: Application
) : ViewModel() {
    val database = dataSource
    val students = database.getAll()

    fun addStudent(firstName: String, lastName: String) {

        viewModelScope.launch {
            val newStudent = Student(0, firstName, lastName)
            database.insert(newStudent)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            database.delete(student)
        }
    }

    fun updateStudent(student: Student, newName: String, newSurname: String) {
        viewModelScope.launch {
            database.update(Student(student.id, newName, newSurname))
        }
    }
}