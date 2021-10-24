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
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.akin.hepsiburada.R
import com.bumptech.glide.Glide
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class ChooseFragment : Fragment() {



    private lateinit var imageUri: Uri
    private lateinit var selectedImage: Bitmap
    private lateinit var chooseImage:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val args:ChooseFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_choose, container, false)

        val gallery_cardview=view.findViewById<CardView>(R.id.gallery_cardview)
        val chooseNextButton=view.findViewById<Button>(R.id.chooseNextButton)
        chooseImage=view.findViewById(R.id.chooseImage)
        gallery_cardview.setOnClickListener{
            addImage()
        }


        chooseNextButton.setOnClickListener{
            try {
                val action=ChooseFragmentDirections.actionChooseFragmentToPhotoUploadFragment2(imageUri,args.name,args.address,args.number)
                it.findNavController().navigate(action)
            }catch (e:Exception){
                Toast.makeText(requireContext(),"SelectedImage",Toast.LENGTH_LONG).show()
                println(e)
            }


        }

        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data!!
            println(imageUri)
            selectedImage =
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
            Glide.with(requireContext()).load(selectedImage).circleCrop().into(chooseImage!!)


        }
        super.onActivityResult(requestCode, resultCode, data)


    }

    private fun uploadImage() {
        val randomId = (1..100000).random()
        println(randomId)
        val uuid = UUID.randomUUID()
       /* try {

            )
        }catch (ex: Exception){

            Toast.makeText(context,"Please choose a image", Toast.LENGTH_LONG).show()
        }*/


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

}