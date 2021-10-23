package com.akin.hepsiburada.screens.fragments


import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels

import com.akin.hepsiburada.databinding.PersistentBottomSheetBinding
import com.akin.hepsiburada.domain.factory.BottomSheetFragmentFactory
import com.akin.hepsiburada.domain.BottomSheetViewModel
import com.bumptech.glide.Glide
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.storage.FirebaseStorage
import java.lang.Exception
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BottomSheetFragment() : BottomSheetDialogFragment() {
    private var _binding: PersistentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var addButton: Button? = null
    private lateinit var imageUri: Uri
    private lateinit var selectedImage: Bitmap
    private val viewModel: BottomSheetViewModel by viewModels {
        BottomSheetFragmentFactory(requireContext())
    }
    private var nameText: EditText? = null
    private var priceText: EditText? = null
    private var categoriesText: EditText? = null
    private var ingredientsText: EditText? = null
    private var calorieText: EditText? = null
    private var addImage: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersistentBottomSheetBinding.inflate(inflater, container, false)
        addButton = binding.addButton
        nameText = binding.nameEditText
        priceText = binding.priceEditText
        categoriesText = binding.categoriesEditText
        ingredientsText = binding.ingredientsEditText
        calorieText = binding.caloriesEditText
        addImage = binding.addImage
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        ingredientsText?.setOnClickListener {
            val alert: CFAlertDialog.Builder = CFAlertDialog.Builder(context).setDialogStyle(
                CFAlertDialog.CFAlertStyle.ALERT
            ).setTitle("Important")
                .setMessage("When you add ingredients, you have to put a comma between each item!")
                .addButton("Okay",
                    -1,
                    -1,
                    CFAlertDialog.CFAlertActionStyle.POSITIVE,
                    CFAlertDialog.CFAlertActionAlignment.END,
                    object : DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
                        override fun onCancel(p0: DialogInterface?) {
                            TODO("Not yet implemented")
                        }

                        override fun onClick(p0: DialogInterface?, p1: Int) {


                            p0?.dismiss()
                        }
                    })
            alert.show()
        }

        addButton?.setOnClickListener {

            checkItem()
            viewModel.isClickable.observe(viewLifecycleOwner,{
                if (it){
                    addButton!!.isEnabled = true
                    viewModel.isClickable.value = false
                    addButton!!.text = "Add Food"
                }
            })




        }
        addImage?.setOnClickListener {
            addImage()
        }


    }

    private fun checkItem() {
        when {
            ingredientsText?.text?.contains(",") == false -> {
                ingredientsText?.error = "Please put a comma between each item"
            }
            nameText?.text.isNullOrEmpty() -> {
                nameText?.error = "Please write a food name"
            }
            priceText?.text.isNullOrEmpty() -> {
                priceText?.error = "Please write a price"
            }
            categoriesText?.text.isNullOrEmpty() -> {
                categoriesText?.error = "Please write a category"
            }
            calorieText?.text.isNullOrEmpty() -> {
                calorieText?.error = "Please write a calorie"
            }

            else -> {

                addButton!!.isEnabled = false
                addButton!!.text = "Loading...."
                uploadImage()

            }
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

    private fun uploadImage() {
        val randomId = (1..100000).random()
        println(randomId)
        val list = (ingredientsText?.text?.split(","))
        val uuid = UUID.randomUUID()
        try {
            viewModel.addAndGetImageFromFirebase(
                imageUri,
                nameText?.text.toString(),
                priceText?.text.toString().toDouble(),
                categoriesText?.text.toString(),
                list as ArrayList<String>,
                calorieText?.text.toString(),
                uuid.toString()
            )
        }catch (ex:Exception){
            addButton!!.isEnabled = true
            addButton!!.text = "Add Food"
            Toast.makeText(context,"Please choose a image",Toast.LENGTH_LONG).show()
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            imageUri = data.data!!
            selectedImage =
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
            Glide.with(requireContext()).load(selectedImage).circleCrop().into(addImage!!)


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

}