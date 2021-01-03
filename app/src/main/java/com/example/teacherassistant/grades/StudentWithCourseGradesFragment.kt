package com.example.teacherassistant.grades

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
import com.example.teacherassistant.databinding.FragmentStudentWithCourseGradesBinding

class StudentWithCourseGradesFragment : Fragment() {
    private lateinit var binding: FragmentStudentWithCourseGradesBinding
    val args: StudentWithCourseGradesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_with_course_grades,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).gradeDao
        val dataSourceStudentAndCourse =
            TeacherAssistantDatabase.getInstance(application).studentCourseDao
        val gradesViewModelFactory =
            GradesViewModelFactory(
                dataSource,
                dataSourceStudentAndCourse,
                application,
                args.courseId,
                args.studentId
            )


        val gradesViewModel =
            ViewModelProvider(
                this, gradesViewModelFactory
            ).get(GradesViewModel::class.java)

        binding.gradesViewModel = gradesViewModel
        binding.gradesCourseName.text = args.courseName
        binding.gradesStudentName.text = args.studentName

        val adapter = GradesListAdapter { grade ->
            gradesViewModel.deleteGrade(grade.id)
        }

        binding.gradesList.adapter = adapter

        gradesViewModel.grades.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.addGrade.setOnClickListener {
            val action =
                StudentWithCourseGradesFragmentDirections.actionAddGrade(
                    args.studentId,
                    args.courseId
                )
            it.findNavController().navigate(action)
        }
        return binding.root
    }

}