package com.akin.hepsiburada.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentFirstScreenBinding
import com.akin.hepsiburada.databinding.FragmentSecondScreenBinding


class SecondScreenFragment : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)
        val viewPager  = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.apply {

            next2.setOnClickListener {
                viewPager?.currentItem = 2
            }
        }

        return binding.root
    }

}