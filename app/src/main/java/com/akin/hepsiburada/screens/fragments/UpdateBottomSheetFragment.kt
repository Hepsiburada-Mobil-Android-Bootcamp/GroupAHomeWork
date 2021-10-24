package com.akin.hepsiburada.screens.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentUpdateBottomSheetBinding
import com.akin.hepsiburada.databinding.PersistentBottomSheetBinding
import com.akin.hepsiburada.domain.factory.BottomSheetFragmentFactory
import com.akin.hepsiburada.domain.BottomSheetViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match

class UpdateBottomSheetFragment : BottomSheetDialogFragment() {
    private var downloadUrl : String? = null
    lateinit var myValue : String
    private var _binding: FragmentUpdateBottomSheetBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageUri: Uri
    private lateinit var selectedImage: Bitmap
    private val viewModel: BottomSheetViewModel by viewModels{
        BottomSheetFragmentFactory(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUpdateBottomSheetBinding.inflate(inflater, container, false)
        myValue = this.requireArguments().getString("message").toString()
        binding.apply {
            nameEditText.setText(requireArguments().getString("name").toString())
            categoriesEditText.setText(requireArguments().getString("category").toString())
            priceEditText.setText(requireArguments().getString("price").toString())
            caloriesEditText.setText(requireArguments().getString("calory").toString())
            ingredientsEditText.setText(requireArguments().getString("ing").toString())
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.updateButton.setOnClickListener {
            viewModel.isClickable.observe(viewLifecycleOwner,{
                if (it){
                    binding.updateButton.isEnabled = true
                    viewModel.isClickable.value = false
                    binding.updateButton.text = "Update Food"
                }
            })
            isValid()
        }

        binding.addImage.setOnClickListener {
            addImage()
        }
    }
    private fun addImage() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            val intentToGalery =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentToGalery, 2)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data!!
            selectedImage =
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
            //  addImage?.load(selectedImage) check Glide func.---
            Glide.with(requireContext()).load(selectedImage).circleCrop().into(binding.addImage)


        }
        super.onActivityResult(requestCode, resultCode, data)


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intentToGalery =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intentToGalery, 2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

   private fun isValid() {
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

               viewModel.updateAndGetImageFromFirebase(imageUri,binding.nameEditText.text.toString(),binding.priceEditText.text.toString().toDouble(),downloadUrl.toString(),
                   binding.categoriesEditText.text.toString(),
                   list as ArrayList<String>, binding.caloriesEditText.text.toString(),myValue,)



           }
       }
   }


}