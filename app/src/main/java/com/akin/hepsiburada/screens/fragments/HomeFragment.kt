package com.akin.hepsiburada.screens.fragments

import android.animation.Animator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.domain.FavoritsViewModel
import com.akin.hepsiburada.domain.HomeViewModel
import com.akin.hepsiburada.domain.factory.FavoritsViewModelFactory
import com.akin.hepsiburada.screens.activity.MainActivity
import com.akin.hepsiburada.screens.adapters.CategoriesAdapter
import com.akin.hepsiburada.screens.adapters.HomeFoodsAdapter


class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val favViewModel: FavoritsViewModel by viewModels{
        FavoritsViewModelFactory("3JZBBgTFlWD6U6SDBj8A")
    }
    private var rcCategory: RecyclerView? = null
    private var rcHomeFoods: RecyclerView? = null
    private var searchView: SearchView? = null
    private var lottieAnimationView: LottieAnimationView? = null
    private var progress_bar: ProgressBar? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {
            rcCategory = categoriesRc
            rcHomeFoods = foodsRc
            searchView = searchViewHome
            lottieAnimationView = likeAnim
            progress_bar = progressBar
        }
        progress_bar?.visibility = VISIBLE
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //hide or show necessary view(it will change after add base fragment)
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.VISIBLE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.VISIBLE
        x.findViewById<ImageView>(R.id.fab).visibility = View.VISIBLE


        //searchView logic to transition
        searchView?.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.searchViewHome to "transitionSearch")
            findNavController().navigate(
                R.id.action_homeFragment_to_searchFragment,
                null,
                null,
                extras
            )
        }

        //getAll Foods to adapter

        viewModel.foodList.observeForever {
            val adapter = HomeFoodsAdapter(it, this)
            rcHomeFoods?.adapter = adapter

            adapter.itemClickListener={ data->
                favViewModel.addFoodsToFav(data)
            }

        }

       //progressbar logic
        viewModel.isComplete.observe(viewLifecycleOwner,{
            if (it){

                progress_bar?.visibility = GONE

            }
        })

        //getCategories to adapter
        viewModel.categoriesList.observe(viewLifecycleOwner, { categories ->
            val adapter = CategoriesAdapter(categories)
            rcCategory?.adapter = adapter
            adapter.itemClickListener = {
                viewModel.getSpesificFoods(it)

            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //lottie anim logic
    fun playLikeAnim() {
        lottieAnimationView?.playAnimation()
        lottieAnimationView?.visibility = VISIBLE

        lottieAnimationView?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                println("like anim started")
            }

            override fun onAnimationEnd(animation: Animator) {

                println("like anim finished")
                lottieAnimationView?.visibility = GONE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
    }

}