package com.akin.hepsiburada.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentDetailBinding
import com.akin.hepsiburada.domain.DetailViewModel
import com.akin.hepsiburada.domain.*
import com.akin.hepsiburada.domain.factory.BottomSheetFragmentFactory
import com.akin.hepsiburada.domain.factory.DetailViewModelFactory
import com.akin.hepsiburada.screens.activity.MainActivity
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(args.foodTitle)
    }
    private var number = 1
    private val viewModelBottomSheet: BottomSheetViewModel by viewModels {
        BottomSheetFragmentFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.GONE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.GONE
        x.findViewById<FloatingActionButton>(R.id.fab).visibility = View.GONE


        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root

    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val deciFormat = DecimalFormat()
        deciFormat.maximumFractionDigits = 2
        super.onViewCreated(view, savedInstanceState)
        binding.removeIcon.setOnClickListener {
            viewModelBottomSheet.alertDelete(args.foodTitle)
            findNavController().popBackStack()

        }
        viewModel.detailFoodList.observe(viewLifecycleOwner, {
            binding.apply {
                textViewFood.text = it[0].name
                textViewFoodPrice.text = it[0].price.toString()
                textViewFoodCalorie.text = it[0].calory + " " + "Calories"
                textViewCategory.text = it[0].category
                val defaultFoodPrice = textViewFoodPrice.text.toString().toDouble()
                var newFoodPrice: Double
                textViewIngList.text =
                    it[0].ingredients.toString().replace("[", "").replace("]", "")

                numberText.text = number.toString()

                backArrowButton.setOnClickListener {
                    findNavController().popBackStack()
                }
                detailFab.setOnClickListener {
                    detailFab()

                }
                plusButton.setOnClickListener {
                    if (number > 0) {
                        number++
                        newFoodPrice = (number * defaultFoodPrice)
                        textViewFoodPrice.text = deciFormat.format(newFoodPrice).toString()
                        numberText.text = number.toString()
                    }
                }
                minusButton.setOnClickListener {
                    textViewFoodPrice.text.toString().replace(",","").toDouble()
                    if (number != 1) {
                        number--
                        newFoodPrice = (number * defaultFoodPrice)
                        textViewFoodPrice.text = deciFormat.format(newFoodPrice).toString()
                        numberText.text = number.toString()
                    }
                }
                Glide.with(requireContext()).load(it[0].image).circleCrop().into(imageViewFood)
            }
        })


    }

    private fun detailFab() {
        val bottomFragment = UpdateBottomSheetFragment()
        bottomFragment.setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.ThemeOverlay_Demo_BottomSheetDialog
        )
        val bundle = Bundle()
        val foodId = args.foodTitle

        bundle.apply {
            putString("message", foodId)
            putString("name", binding.textViewFood.text.toString())
            putString("category", binding.textViewCategory.text.toString())
            putString("price", binding.textViewFoodPrice.text.toString())
            putString("calory", binding.textViewFoodCalorie.text.toString().replace("Calories", ""))
            putString("ing", binding.textViewIngList.text.toString())
        }

        bottomFragment.arguments = bundle
        fragmentManager?.let { it1 ->
            bottomFragment.show(it1, "TAG")
        }
    }

}