package com.akin.hepsiburada.screens.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.akin.hepsiburada.R
import com.akin.hepsiburada.domain.ProfileViewModel
import com.akin.hepsiburada.domain.factory.ProfileViewModelFactory
import com.akin.hepsiburada.util.load
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class PhotoUploadFragment : Fragment() {


    private lateinit var imageUri: Uri
    private lateinit var selectedImage: Bitmap
    private val args:PhotoUploadFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var profileImage:ImageView?=null
    private val profileViewModel:ProfileViewModel by viewModels {
        ProfileViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_photo_upload, container, false)

        profileImage=view.findViewById(R.id.profileImage)
        println(args.selectedImage)

        val uploadPhotoButton=view.findViewById<Button>(R.id.uploadPhotoButton)

        println(args.name)

        val uid=Firebase.auth.currentUser?.uid
        uploadPhotoButton.setOnClickListener{
            profileViewModel.addAndGetImageFromFirebase(args.name,args.number,args.address,uid,args.selectedImage)
            val action=PhotoUploadFragmentDirections.actionPhotoUploadFragment2ToRegisterDoneFragment()
            it.findNavController().navigate(action)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(args.selectedImage).circleCrop().into(profileImage!!)
    }




}