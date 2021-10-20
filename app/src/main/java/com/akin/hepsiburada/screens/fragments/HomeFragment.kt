package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.domain.FirebaseViewModel


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel : FirebaseViewModel by viewModels ()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.foodList.observe(viewLifecycleOwner, {
            println(it[0].name)

        })
        viewModel.categoriesList.observe(viewLifecycleOwner, {
            println(it.toString())

        })




    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}