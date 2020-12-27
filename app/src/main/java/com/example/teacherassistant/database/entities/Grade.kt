package com.example.teacherassistant.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.teacherassistant.database.DateConverter
import java.util.*

@Entity(
    tableName = "grade_table",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = StudentAndCourse::class,
            parentColumns = ["id"],
            childColumns = ["studentAndCourseId"],
            onDelete = ForeignKey.CASCADE
        )]
)
@TypeConverters(DateConverter::class)
data class Grade(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val studentAndCourseId: Int,
    val grade: Double,
    val note: String,
    val date: Date
)