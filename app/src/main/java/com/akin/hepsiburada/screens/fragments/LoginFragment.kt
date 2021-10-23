package com.akin.hepsiburada.screens.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.akin.hepsiburada.R
import com.akin.hepsiburada.screens.activity.MainActivity


class LoginFragment : Fragment() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_login_tab, container, false)

        val loginButton= view.findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener{
            startActivity(Intent(requireContext(),MainActivity::class.java))

        }

        return view
    }



}