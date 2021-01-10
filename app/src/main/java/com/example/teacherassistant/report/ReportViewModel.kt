package com.example.teacherassistant.report

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.database.dao.GradeDao
import com.example.teacherassistant.database.dao.StudentCourseDao
import com.example.teacherassistant.database.entities.Grade
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReportViewModel(
    dataSource: GradeDao,
    dataSourceStudentAndCourse: StudentCourseDao,
    application: Application
) : ViewModel() {
    val database = dataSource
    val databaseStudentAndCourse = dataSourceStudentAndCourse


    var reportItems: MutableLiveData<List<ReportItem>> = MutableLiveData()


    init {

        var reportItemsList = mutableListOf<ReportItem>()
        lateinit var grades: List<Grade>
        viewModelScope.launch {
            viewModelScope.launch(Dispatchers.IO) {
                grades = database.getAllFromToday()


                grades.forEach {
                    val reportItem = ReportItem(
                        it.grade, it.note,
                        databaseStudentAndCourse.getStudentLastName(it.studentAndCourseId),
                        databaseStudentAndCourse.getCourseName(it.studentAndCourseId)
                    )
                    reportItemsList.add(reportItem)
                }
            }.join()


            reportItems.value = reportItemsList
        }

    }


}