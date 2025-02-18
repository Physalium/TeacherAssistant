package com.example.teacherassistant.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        binding.buttonCourses.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_homeFragment_to_coursesListFragment)
        }

        binding.buttonStudents.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_homeFragment_to_studentsListFragment)
        }

        binding.buttonReport.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_homeFragment_to_reportFragment)
        }
        return binding.root

    }


}