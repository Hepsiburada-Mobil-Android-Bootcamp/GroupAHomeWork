package com.akin.hepsiburada.screens.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.akin.hepsiburada.R

import androidx.navigation.fragment.findNavController
import com.akin.hepsiburada.databinding.FragmentFirstScreenBinding
import com.akin.hepsiburada.databinding.FragmentSecondScreenBinding
import com.akin.hepsiburada.databinding.FragmentThirdScreenBinding
import com.akin.hepsiburada.screens.activity.AuthenticationActivity
import com.akin.hepsiburada.screens.activity.MainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ThirdScreenFragment : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        binding.apply {
            finish.setOnClickListener {

                val prefs2: SharedPreferences =
                    requireActivity().getSharedPreferences("prefs4", Context.MODE_PRIVATE)
                val editor = prefs2.edit()
                editor.putBoolean("firstStartAnasayfa", false)
                editor.apply()
                if (Firebase.auth.currentUser==null){
                    startActivity(Intent(requireContext(),AuthenticationActivity::class.java))
                }else{
                    startActivity(Intent(requireContext(),MainActivity::class.java))
                }


            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}