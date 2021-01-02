package com.example.teacherassistant.students

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
import com.example.teacherassistant.databinding.FragmentStudentAddBinding
import com.google.android.material.snackbar.Snackbar


class StudentAddFragment : Fragment() {
    private lateinit var binding: FragmentStudentAddBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_add,
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
        binding.addButton.setOnClickListener {
            studentsViewModel.addStudent(
                binding.firstName.text.toString(),
                binding.lastName.text.toString()
            )
            binding.firstName.text.clear()
            binding.lastName.text.clear()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            Snackbar.make(binding.root, "The student has been added.", Snackbar.LENGTH_SHORT)
                .show()
        }
        return binding.root
    }


}