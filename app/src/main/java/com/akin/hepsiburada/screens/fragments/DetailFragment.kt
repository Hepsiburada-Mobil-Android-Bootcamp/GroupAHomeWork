package com.akin.hepsiburada.screens.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentDetailBinding
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.screens.activity.SplashActivity

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val imageViewFood by lazy {
        binding.imageViewFood
    }
    private val textViewFood by lazy {
        binding.textViewFood
    }
    private val textViewFoodCalorie by lazy {
        binding.textViewFoodCalorie
    }
    private val textViewFoodPrice by lazy {
        binding.textViewFoodPrice
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}