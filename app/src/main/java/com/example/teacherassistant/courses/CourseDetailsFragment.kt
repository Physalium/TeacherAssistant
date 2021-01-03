package com.example.teacherassistant.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.databinding.FragmentCourseDetailsBinding
import com.example.teacherassistant.studentcourse.StudentAndCourseViewModel
import com.example.teacherassistant.studentcourse.StudentAndCourseViewModelFactory
import com.example.teacherassistant.studentcourse.StudentsInCourseListAdapter


class CourseDetailsFragment : Fragment() {
    private lateinit var binding: FragmentCourseDetailsBinding
    val args: CourseEditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_course_details,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).studentCourseDao

        val studentAndCourseViewModelFactory =
            StudentAndCourseViewModelFactory(dataSource, application, args.courseId, null)


        val studentAndCourseViewModel =
            ViewModelProvider(
                this, studentAndCourseViewModelFactory
            ).get(StudentAndCourseViewModel::class.java)

        binding.studentAndCourseViewModel = studentAndCourseViewModel
        binding.courseDetailsName.text = args.courseName

        val adapter = StudentsInCourseListAdapter(
            { student ->
                studentAndCourseViewModel.deleteStudentFromCourse(student.id, args.courseId)
            },
            args.courseId,
            args.courseName
        )

        binding.courseDetailsStudentsInCourseList.adapter = adapter

        studentAndCourseViewModel.studentsInCourse?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.courseDetailsAddStudent.setOnClickListener { view ->
            val action =
                CourseDetailsFragmentDirections.actionCourseAddStudent(
                    args.courseName,
                    args.courseId
                )
            view.findNavController().navigate(action)
        }
        return binding.root
    }


}