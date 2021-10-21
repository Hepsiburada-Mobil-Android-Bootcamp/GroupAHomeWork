package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
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
    private var searchView: SearchView? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        rcCategory = binding.categoriesRc
        rcHomeFoods = binding.foodsRc
        searchView = binding.searchViewHome
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView?.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.searchViewHome to "transitionSearch")
            findNavController().navigate(
                R.id.action_homeFragment_to_searchFragment,
                null,
                null,
                extras
            )
        }
        viewModel.foodList.observe(viewLifecycleOwner, {
            val adapter = HomeFoodsAdapter(it)
            rcHomeFoods?.adapter = adapter


        })

        viewModel.categoriesList.observe(viewLifecycleOwner, { categories ->
            val adapter = CategoriesAdapter(categories)
            rcCategory?.adapter = adapter
            adapter.itemClickListener= {
                viewModel.getSpesificFoods(it)

            }

        })
        viewModelFav.currentFavList.observe(viewLifecycleOwner,{

            println(it)

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}