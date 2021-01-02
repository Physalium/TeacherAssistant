package com.example.teacherassistant.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.database.dao.CourseDao
import com.example.teacherassistant.database.dao.StudentDao
import com.example.teacherassistant.database.entities.Course
import com.example.teacherassistant.database.entities.Grade
import com.example.teacherassistant.database.entities.Student
import com.example.teacherassistant.database.entities.StudentAndCourse


@Database(
    entities = [Student::class,
        Course::class,
        StudentAndCourse::class,
        Grade::class],
    version = 3,
    exportSchema = false
)
abstract class TeacherAssistantDatabase : RoomDatabase() {

    abstract val courseDao: CourseDao
    abstract val studentDao: StudentDao

    companion object {

        @Volatile
        private var INSTANCE: TeacherAssistantDatabase? = null

        fun getInstance(context: Context): TeacherAssistantDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TeacherAssistantDatabase::class.java,
                        "teacher_assistant_database"
                    )

                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}