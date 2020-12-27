package com.example.teacherassistant.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.databinding.CoursesListFragmentBinding

class CoursesListFragment : Fragment() {
    private lateinit var binding: CoursesListFragmentBinding
    private lateinit var viewModel: CoursesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.courses_list_fragment,
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
        val adapter = CourseListAdapter(coursesViewModel.courses)
        coursesViewModel.courses.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })
        binding.coursesList.adapter = adapter

        binding.addCourse.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_coursesListFragment_to_courseAddFragment)
        }
        return binding.root
    }


}