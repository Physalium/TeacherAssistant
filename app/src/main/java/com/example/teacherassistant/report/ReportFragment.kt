package com.example.teacherassistant.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.database.TeacherAssistantDatabase
import com.example.teacherassistant.databinding.FragmentReportBinding

class ReportFragment : Fragment() {
    private lateinit var binding: FragmentReportBinding
    private lateinit var viewModel: ReportViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_report,
            container,
            false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = TeacherAssistantDatabase.getInstance(application).gradeDao
        val dataSourceStudentAndCourse =
            TeacherAssistantDatabase.getInstance(application).studentCourseDao
        val viewModelFactory =
            ReportViewModelFactory(dataSource, dataSourceStudentAndCourse, application)


        val reportViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(ReportViewModel::class.java)

        binding.reportViewModel = reportViewModel
        val adapter = ReportListAdapter()
        binding.reportList.adapter = adapter

        reportViewModel.reportItems.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

}