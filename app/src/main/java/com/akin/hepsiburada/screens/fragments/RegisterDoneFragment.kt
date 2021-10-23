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


class RegisterDoneFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_register_done, container, false)

        val signToHomeButton=view.findViewById<Button>(R.id.signToHomeButton)

        signToHomeButton.setOnClickListener{
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }

        return view
    }


}