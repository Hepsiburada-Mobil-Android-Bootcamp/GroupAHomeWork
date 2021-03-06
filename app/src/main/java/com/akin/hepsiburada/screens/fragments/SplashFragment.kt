package com.akin.hepsiburada.screens.fragments

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.databinding.FragmentSplashBinding
import com.akin.hepsiburada.screens.activity.AuthenticationActivity
import com.akin.hepsiburada.screens.activity.MainActivity
import com.akin.hepsiburada.screens.onboarding.ViewPagerFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SplashFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val prefs2 =requireActivity()
            .getSharedPreferences("prefs4", Context.MODE_PRIVATE)
        val firstStart = prefs2.getBoolean("firstStartAnasayfa", true)
        binding.apply {
            animationView.playAnimation()
            animationView.visibility = View.VISIBLE

            animationView.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    println("basladi")

                }

                override fun onAnimationEnd(animation: Animator) {



                    if (firstStart){

                        val action = SplashFragmentDirections.actionSplashFragmentToViewPagerFragment()
                        findNavController().navigate(action)

                    }else{
                        if(Firebase.auth.currentUser==null){
                            startActivity(Intent(requireContext(),AuthenticationActivity::class.java))
                            activity?.finish()
                        }
                        else{
                            startActivity(Intent(requireContext(),MainActivity::class.java))
                            activity?.finish()
                        }


                    }



                    animationView.visibility = View.GONE

                }

                override fun onAnimationCancel(animation: Animator) {

                }

                override fun onAnimationRepeat(animation: Animator) {

                }
            })
        }


        return binding.root
    }


}