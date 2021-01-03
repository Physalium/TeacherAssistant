package com.example.teacherassistant.grades

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
import com.example.teacherassistant.databinding.FragmentGradeAddBinding
import com.google.android.material.snackbar.Snackbar


class GradeAddFragment : Fragment() {
    private lateinit var binding: FragmentGradeAddBinding
    val args: GradeAddFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_grade_add,
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
        binding.addGrade.setOnClickListener {
            gradesViewModel.addGrade(
                args.studentId, args.courseId,
                binding.grade.text.toString().toDouble(), binding.note.text.toString()
            )
            binding.grade.text.clear()
            binding.note.text.clear()
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            Snackbar.make(binding.root, "The grade has been added.", Snackbar.LENGTH_SHORT)
                .show()
        }
        return binding.root
    }

}