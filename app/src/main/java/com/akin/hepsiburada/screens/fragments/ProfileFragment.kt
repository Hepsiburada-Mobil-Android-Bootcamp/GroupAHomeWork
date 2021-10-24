package com.akin.hepsiburada.screens.fragments

import android.content.Intent
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.databinding.FragmentProfileBinding
import com.akin.hepsiburada.domain.MainActivityViewModel
import com.akin.hepsiburada.screens.activity.AuthenticationActivity
import com.akin.hepsiburada.screens.activity.MainActivity
import com.akin.hepsiburada.util.load
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by viewModels()
    private var image : ImageView ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val animation = TransitionInflater.from(requireContext()).inflateTransition(
            android.R.transition.move
        )
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.GONE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.GONE
        // Inflate the layout for this fragment
        binding.apply {
          image = profileImage
            logOutBttn.setOnClickListener {
                Firebase.auth.signOut()
                startActivity(Intent(requireContext(), AuthenticationActivity::class.java))
                activity?.finish()
            }

        }
        viewModel.userList.observe(viewLifecycleOwner, {
            val userImage = it.imageUrl
            image?.load(userImage)
            binding.profileNameText.text = it.name
            binding.profileAddressText.text = it.address
            binding.profileNumberText.text = it.number

        })
        return binding.root
    }


}