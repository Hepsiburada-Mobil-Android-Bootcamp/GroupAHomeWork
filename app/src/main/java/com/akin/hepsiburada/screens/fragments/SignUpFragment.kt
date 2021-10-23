package com.akin.hepsiburada.screens.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.akin.hepsiburada.R
import com.akin.hepsiburada.screens.activity.MainActivity


class SignUpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_up_tab, container, false)

        val signup_button=view.findViewById<Button>(R.id.signup_button)

        signup_button.setOnClickListener{
            val action=AuthFragmentDirections.actionAuthFragmentToChooseFragment()
            it.findNavController().navigate(action)
        }
        return view
    }


}