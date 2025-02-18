package com.example.teacherassistant.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.database.entities.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM course_table")
    fun getAll(): LiveData<List<Course>>

    @Query("SELECT * FROM course_table INNER JOIN student_course_table ON course_table.id=student_course_table.course_id WHERE student_course_table.student_id=:student_id")
    fun getAllCoursesForStudent(student_id: Int): LiveData<List<Course>>

    @Insert
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(course: Course)

    @Update
    suspend fun update(course: Course)
}