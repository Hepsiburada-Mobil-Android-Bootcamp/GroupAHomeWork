package com.akin.hepsiburada.screens.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.akin.hepsiburada.R
import kotlinx.android.synthetic.main.fragment_first_screen.view.*
import kotlinx.android.synthetic.main.fragment_third_screen.view.*
import androidx.navigation.fragment.findNavController
import com.akin.hepsiburada.databinding.FragmentFirstScreenBinding
import com.akin.hepsiburada.databinding.FragmentSecondScreenBinding
import com.akin.hepsiburada.databinding.FragmentThirdScreenBinding
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

class ThirdScreenFragment : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        binding.apply {
            finish.next.setOnClickListener {
                val action = ThirdScreenFragmentDirections.actionThirdScreenFragmentToHomeFragment3()
                it.findNavController().navigate(action)
            }
        }

        return binding.root
    }

}