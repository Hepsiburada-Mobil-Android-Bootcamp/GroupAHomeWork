package com.akin.hepsiburada.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentDetailBinding
import com.akin.hepsiburada.domain.DetailViewModel
import com.akin.hepsiburada.domain.factory.DetailViewModelFactory
import com.akin.hepsiburada.screens.activity.MainActivity
import com.bumptech.glide.Glide

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private  val args:DetailFragmentArgs by navArgs()
    private val viewModel : DetailViewModel by viewModels {
        DetailViewModelFactory(args.foodTitle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val image = view?.findViewById<ImageButton>(R.id.favIcon)
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.GONE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.GONE


        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root

    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewModel.detailFoodList.observe(viewLifecycleOwner,{
           binding.apply {
              textViewFood.text = it[0].name
              textViewFoodPrice.text = it[0].price.toString()
              textViewFoodCalorie.text = it[0].calory + " " + "Calories"
              textViewCategory.text = it[0].category
               backArrowButton.setOnClickListener {
                   findNavController().popBackStack()
               }

              Glide.with(requireContext()).load(it[0].image).circleCrop().into(imageViewFood)
           }

     })
    }


}