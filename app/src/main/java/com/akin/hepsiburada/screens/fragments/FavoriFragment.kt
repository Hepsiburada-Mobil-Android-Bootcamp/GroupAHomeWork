package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentFavoriBinding
import com.akin.hepsiburada.domain.DetailViewModel
import com.akin.hepsiburada.domain.FavoritsViewModel
import com.akin.hepsiburada.domain.factory.DetailViewModelFactory
import com.akin.hepsiburada.domain.factory.FavoritsViewModelFactory
import com.akin.hepsiburada.screens.activity.MainActivity
import com.akin.hepsiburada.screens.adapters.FavoriAdapter


class FavoriFragment : Fragment() {
    private var _binding: FragmentFavoriBinding? = null
    private val binding get() = _binding!!
    private var rcFavori : RecyclerView? = null

    private val viewModel : FavoritsViewModel by viewModels {
        FavoritsViewModelFactory("3JZBBgTFlWD6U6SDBj8A")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.GONE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.GONE
        _binding = FragmentFavoriBinding.inflate(inflater, container, false)
        rcFavori = binding.recyclerViewFav
        viewModel.getAllFav()

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("------------------------------")
        viewModel.favList.observe(viewLifecycleOwner, {
            println(it)

        })

    }



}