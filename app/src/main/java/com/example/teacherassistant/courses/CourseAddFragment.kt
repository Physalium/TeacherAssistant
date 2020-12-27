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
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.databinding.FragmentCourseAddBinding
import com.google.android.material.snackbar.Snackbar


class CourseAddFragment : Fragment() {
    private lateinit var binding: FragmentCourseAddBinding
    private lateinit var viewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_course_add,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).courseDao
        val viewModelFactory = CoursesViewModelFactory(dataSource, application)


        val coursesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(CoursesViewModel::class.java)


        binding.coursesViewModel = coursesViewModel
        binding.addButton.setOnClickListener {
            coursesViewModel.addCourse(binding.name.text.toString())
            binding.name.text.clear()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            Snackbar.make(binding.root, "The course has been added.", Snackbar.LENGTH_SHORT)
                .show()
        }
        return binding.root
    }


}