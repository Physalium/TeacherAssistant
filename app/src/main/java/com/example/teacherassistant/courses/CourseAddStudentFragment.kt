package com.example.teacherassistant.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.databinding.FragmentCourseAddStudentBinding
import com.example.teacherassistant.studentcourse.StudentAndCourseViewModel
import com.example.teacherassistant.studentcourse.StudentAndCourseViewModelFactory
import com.example.teacherassistant.studentcourse.StudentInCourseAddListAdapter


class CourseAddStudentFragment : Fragment() {
    private lateinit var binding: FragmentCourseAddStudentBinding
    val args: CourseAddStudentFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_course_add_student,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).studentCourseDao

        val studentAndCourseViewModelFactory =
            StudentAndCourseViewModelFactory(dataSource, application, args.courseId)


        val studentAndCourseViewModel =
            ViewModelProvider(
                this, studentAndCourseViewModelFactory
            ).get(StudentAndCourseViewModel::class.java)

        binding.studentAndCourseViewModel = studentAndCourseViewModel
        binding.courseAddStudentCourseName.text = args.courseName

        val adapter = StudentInCourseAddListAdapter { student ->
            studentAndCourseViewModel.addStudentToCourse(student.id, args.courseId)
        }
        binding.courseAddStudentStudentsList.adapter = adapter

        studentAndCourseViewModel.studentsNotInCourse.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return binding.root
    }


}