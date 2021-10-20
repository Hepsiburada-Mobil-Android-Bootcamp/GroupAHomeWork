package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.domain.FavoritsViewModel
import com.akin.hepsiburada.domain.HomeViewModel
import com.akin.hepsiburada.screens.adapters.CategoriesAdapter
import com.akin.hepsiburada.screens.adapters.HomeFoodsAdapter


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val viewModelFav: FavoritsViewModel by viewModels()
    private var rcCategory: RecyclerView? = null
    private var rcHomeFoods: RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        rcCategory = binding.categoriesRc
        rcHomeFoods = binding.foodsRc
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.foodList.observe(viewLifecycleOwner, {
            val adapter = HomeFoodsAdapter(it)
            rcHomeFoods?.adapter = adapter

        })

        viewModel.categoriesList.observe(viewLifecycleOwner, { categories ->
            val adapter = CategoriesAdapter(categories)
            rcCategory?.adapter = adapter

        })
        viewModelFav.favList.observe(viewLifecycleOwner,{
            println(it.toString())
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}