package com.example.teacherassistant.students

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.databinding.FragmentStudentsListBinding


class StudentsListFragment : Fragment() {
    private lateinit var binding: FragmentStudentsListBinding
    private lateinit var viewModel: StudentsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_students_list,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).studentDao
        val viewModelFactory = StudentsViewModelFactory(dataSource, application)

        val studentsViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(StudentsViewModel::class.java)

        binding.studentsViewModel = studentsViewModel

        val adapter = StudentsListAdapter { student ->
            studentsViewModel.deleteStudent(student)
        }
        binding.studentsList.adapter = adapter
        studentsViewModel.students.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.addStudent.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_add_student)
        }
        return binding.root
    }


}