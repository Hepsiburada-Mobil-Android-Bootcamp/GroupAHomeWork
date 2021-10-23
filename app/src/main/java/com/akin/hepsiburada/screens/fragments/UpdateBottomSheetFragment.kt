package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentUpdateBottomSheetBinding
import com.akin.hepsiburada.databinding.PersistentBottomSheetBinding
import com.akin.hepsiburada.domain.BottomSheetFragmentFactory
import com.akin.hepsiburada.domain.BottomSheetViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match

class UpdateBottomSheetFragment : BottomSheetDialogFragment() {
    lateinit var myValue : String
    private var _binding: FragmentUpdateBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var addButton: Button? = null
    private val viewModel: BottomSheetViewModel by viewModels{
        BottomSheetFragmentFactory(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateBottomSheetBinding.inflate(inflater, container, false)
        myValue = this.requireArguments().getString("message").toString()
        binding.nameEditText.setText(this.requireArguments().getString("name").toString())
        binding.categoriesEditText.setText(this.requireArguments().getString("category").toString())
        binding.priceEditText.setText(this.requireArguments().getString("price").toString())
        binding.caloriesEditText.setText(this.requireArguments().getString("calory").toString())
        binding.ingredientsEditText.setText(this.requireArguments().getString("ing").toString())
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.updateButton.setOnClickListener {
            binding.apply {

            }



            when {

                binding.ingredientsEditText.text?.contains(",")==false -> {
                    binding.ingredientsEditText.error="Please put a comma between each item"
                }
                binding.nameEditText.text.isNullOrEmpty() -> {
                    binding.nameEditText.error = "Please write a food name"
                }
                binding.priceEditText.text.isNullOrEmpty() -> {
                    binding.priceEditText.error = "Please write a price"
                }
                binding.categoriesEditText.text.isNullOrEmpty() -> {
                    binding.categoriesEditText.error = "Please write a category"
                }
                binding.caloriesEditText.text.isNullOrEmpty() -> {
                    binding.caloriesEditText.error = "Please write a calorie"
                }
                else -> {

                    //val key = Firebase.auth.currentUser?.uid
                    val randomId = (1..100000).random()
                    //val newKey = key+randomId.toString()
                    println(randomId)
                    val list = (binding.ingredientsEditText.text?.split(","))

                    viewModel.updateFood(binding.nameEditText.text.toString(),binding.priceEditText.text.toString().toDouble(),"https://images-ext-2.discordapp.net/external/KYrZHaybuDQvS1TXNXgiqMvDhxlzRrzzBiJcuQ9F_aE/https/pbs.twimg.com/profile_images/1368589025234325507/L4EMPJbL_400x400.jpg",
                        binding.categoriesEditText.text.toString(),
                        list as ArrayList<String>, binding.caloriesEditText.text.toString(),myValue,

                    )
                }
            }

        }
    }

}