package com.example.teacherassistant.courses

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.database.entities.Course
import com.example.teacherassistant.databinding.FragmentCourseEditBinding
import com.google.android.material.snackbar.Snackbar


class CourseEditFragment : Fragment() {
    private lateinit var binding: FragmentCourseEditBinding
    val args: CourseEditFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_course_edit,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).courseDao
        val viewModelFactory = CoursesViewModelFactory(dataSource, application)
        var course = Course(args.courseId, args.courseName)

        val coursesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(CoursesViewModel::class.java)

        binding.coursesViewModel = coursesViewModel
        binding.oldCourseName.text = course.name
        binding.save.setOnClickListener {

            coursesViewModel.updateCourse(course, binding.newCourseName.text.toString())
            binding.oldCourseName.text = binding.newCourseName.text.toString()
            course = Course(course.id, binding.newCourseName.text.toString())
            binding.newCourseName.text.clear()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            Snackbar.make(binding.root, "The course name has been changed.", Snackbar.LENGTH_SHORT)
                .show()
        }
        return binding.root
    }


}