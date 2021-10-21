package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentFavoriBinding
import com.akin.hepsiburada.screens.adapters.FavoriAdapter


class FavoriFragment : Fragment() {
    private var _binding: FragmentFavoriBinding? = null
    private val binding get() = _binding!!
    private var rcFavori : RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriBinding.inflate(inflater, container, false)
        rcFavori = binding.recyclerViewFav
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



}