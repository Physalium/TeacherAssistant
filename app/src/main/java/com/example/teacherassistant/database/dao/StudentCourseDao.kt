package com.example.teacherassistant.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teacherassistant.database.entities.Course
import com.example.teacherassistant.database.entities.Student
import com.example.teacherassistant.database.entities.StudentAndCourse

@Dao
interface StudentCourseDao {
    @Query("SELECT * FROM student_course_table")
    fun getAll(): LiveData<List<StudentAndCourse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(studentCourse: StudentAndCourse)

    @Query("DELETE FROM student_course_table WHERE student_id = :student_id AND course_id = :course_id")
    suspend fun delete(course_id: Int, student_id: Int)

    @Query("SELECT * FROM student_course_table WHERE course_id ==:courseId AND student_id == :studentId LIMIT 1")
    suspend fun getByStudentAndCourse(courseId: Int, studentId: Int): StudentAndCourse

    @Query("SELECT * FROM student_table WHERE id IN (SELECT student_id FROM student_course_table WHERE course_id = :course_id)")
    fun getByCourseId(course_id: Int): LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id NOT IN (SELECT student_id FROM student_course_table WHERE course_id = :course_id)")
    fun getNotInCourseById(course_id: Int): LiveData<List<Student>>

    @Query("SELECT * FROM course_table WHERE id IN (SELECT course_id FROM student_course_table WHERE student_id = :student_id)")
    fun getCoursesForStudent(student_id: Int): LiveData<List<Course>>

    @Query("select lastName from student_table where id in (select student_id from student_course_table where id=:studentCourseId)")
    fun getStudentLastName(studentCourseId: Int): String

    @Query("select name from course_table where id in (select course_id from student_course_table where id=:studentCourseId)")
    fun getCourseName(studentCourseId: Int): String
}