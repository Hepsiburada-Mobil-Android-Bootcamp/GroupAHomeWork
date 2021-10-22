package com.akin.hepsiburada.screens.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akin.hepsiburada.R
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.akin.hepsiburada.databinding.FragmentFirstScreenBinding
import kotlinx.android.synthetic.main.fragment_first_screen.view.*
import kotlinx.android.synthetic.main.fragment_view_pager.*

class FirstScreenFragment : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        binding.apply {
            next.next.setOnClickListener {
                viewPager?.currentItem = 1
            }
        }

        return binding.root
    }


}