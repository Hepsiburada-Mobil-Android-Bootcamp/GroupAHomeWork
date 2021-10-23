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
    private val viewModelFav: FavoritsViewModel by viewModels{
        FavoritsViewModelFactory("3JZBBgTFlWD6U6SDBj8A")
    }
    private var rcCategory: RecyclerView? = null
    private var rcHomeFoods: RecyclerView? = null
    private var searchView: SearchView? = null
    private var lottieAnimationView: LottieAnimationView? = null



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
        }
//        rcCategory = binding.categoriesRc
//        rcHomeFoods = binding.foodsRc
//        searchView = binding.searchViewHome
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.VISIBLE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.VISIBLE
        x.findViewById<ImageView>(R.id.fab).visibility = View.VISIBLE



        searchView?.setOnClickListener {
            val extras = FragmentNavigatorExtras(binding.searchViewHome to "transitionSearch")
            findNavController().navigate(
                R.id.action_homeFragment_to_searchFragment,
                null,
                null,
                extras
            )
        }

        viewModel.foodList.observeForever {
            val adapter = HomeFoodsAdapter(it,this)
            rcHomeFoods?.adapter = adapter
            println(it)


        }

        viewModel.categoriesList.observe(viewLifecycleOwner, { categories ->
            val adapter = CategoriesAdapter(categories)
            rcCategory?.adapter = adapter
            adapter.itemClickListener= {
                viewModel.getSpesificFoods(it)

            }

        })

//        viewModelFav.currentFavList.observe(viewLifecycleOwner,{
//
//            println(it)
//
//        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun playLikeAnim(){
        lottieAnimationView?.playAnimation()
        lottieAnimationView?.visibility = VISIBLE

        lottieAnimationView?.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                println("basladi")
            }

            override fun onAnimationEnd(animation: Animator) {

                //Navigate to onboarding fragment
                println("bitti")
                lottieAnimationView?.visibility = GONE
            }

            override fun onAnimationCancel(animation: Animator) {

            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
    }

}