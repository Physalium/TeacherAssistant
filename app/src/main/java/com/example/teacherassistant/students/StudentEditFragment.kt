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
import androidx.navigation.fragment.navArgs
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.database.entities.Student
import com.example.teacherassistant.databinding.FragmentStudentEditBinding
import com.google.android.material.snackbar.Snackbar


class StudentEditFragment : Fragment() {
    private lateinit var binding: FragmentStudentEditBinding
    val args: StudentEditFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_student_edit,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).studentDao
        val viewModelFactory = StudentsViewModelFactory(dataSource, application)
        var student = Student(args.studentId, args.studentFirstName, args.studentLastName)

        val studentsViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(StudentsViewModel::class.java)

        binding.studentsViewModel = studentsViewModel
        binding.oldStudentFirstName.text = student.firstName
        binding.oldStudentLastName.text = student.lastName
        binding.save.setOnClickListener {

            studentsViewModel.updateStudent(
                student,
                binding.newStudentFirstName.text.toString(),
                binding.newStudentLastName.text.toString()
            )
            binding.oldStudentFirstName.text = binding.newStudentFirstName.text.toString()
            binding.oldStudentLastName.text = binding.newStudentLastName.text.toString()
            student = Student(
                student.id,
                binding.newStudentFirstName.text.toString(),
                binding.newStudentLastName.text.toString()
            )
            binding.newStudentFirstName.text.clear()
            binding.newStudentLastName.text.clear()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            Snackbar.make(
                binding.root,
                "The student's name has been changed.",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
        return binding.root
    }


}