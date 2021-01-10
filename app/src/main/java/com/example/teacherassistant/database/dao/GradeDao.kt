package com.example.teacherassistant.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.database.entities.Grade

@Dao
interface GradeDao {
    @Query("SELECT * FROM grade_table")
    fun getAll(): LiveData<List<Grade>>

    @Query("SELECT * FROM grade_table where studentAndCourseId in (select id from student_course_table WHERE course_id ==:courseId AND student_id == :studentId)")
    fun getForStudentAndCourse(studentId: Int, courseId: Int): LiveData<List<Grade>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(grade: Grade)

    @Query("SELECT * FROM grade_table WHERE DATE(date/1000,'unixepoch')>= DATE('now', '-1 days')  ")
    fun getAllFromToday(): List<Grade>

    @Query("DELETE FROM grade_table WHERE id = :grade_id")
    suspend fun delete(grade_id: Int)
}