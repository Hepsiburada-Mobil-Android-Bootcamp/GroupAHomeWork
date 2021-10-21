package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridLayout
import android.widget.ListAdapter
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.databinding.FragmentSearchBinding
import com.akin.hepsiburada.domain.HomeViewModel
import com.akin.hepsiburada.domain.SearchViewModel
import com.akin.hepsiburada.screens.adapters.HomeFoodsAdapter
import com.akin.hepsiburada.screens.adapters.SearchFoodsAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase


class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var rcSearch: RecyclerView? = null
    private var searchView: SearchView? = null
    private val viewModel: HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val names = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        rcSearch = binding.rcSearch
        rcSearch?.layoutManager = GridLayoutManager(context,2)
        searchView = binding.searchViewSearch

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.searchFoodList.observe(viewLifecycleOwner, {
            val adapter = SearchFoodsAdapter(it)
            rcSearch?.adapter = adapter

            searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextChange(p0: String?): Boolean {
                    searchViewModel.getSpesificFoods(p0.toString())
                    return false
                }

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    searchViewModel.getSpesificFoods(p0.toString())
                    return false
                }
            })


        })




    }






}