package com.example.teacherassistant.students

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
import com.example.teacherassistant.databinding.FragmentStudentDetailsBinding
import com.example.teacherassistant.studentcourse.StudentAndCourseViewModel
import com.example.teacherassistant.studentcourse.StudentAndCourseViewModelFactory
import com.example.teacherassistant.studentcourse.StudentCoursesListAdapter

class StudentDetailsFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailsBinding
    val args: StudentDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_details,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).studentCourseDao

        val studentAndCourseViewModelFactory =
            StudentAndCourseViewModelFactory(dataSource, application, null, args.studentId)


        val studentAndCourseViewModel =
            ViewModelProvider(
                this, studentAndCourseViewModelFactory
            ).get(StudentAndCourseViewModel::class.java)

        binding.studentAndCourseViewModel = studentAndCourseViewModel
        binding.studentDetailsStudentFirstName.text = args.studentFirstName
        binding.studentDetailsStudentLastName.text = args.studentLastName

        val adapter = StudentCoursesListAdapter { course ->
            studentAndCourseViewModel.deleteStudentFromCourse(args.studentId, course.id)
        }

        binding.studentDetailsCourseList.adapter = adapter

        studentAndCourseViewModel.studentCourses?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }


}