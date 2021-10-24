package com.akin.hepsiburada.screens.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.akin.hepsiburada.R
import com.akin.hepsiburada.databinding.FragmentHomeBinding
import com.akin.hepsiburada.databinding.FragmentLoginTabBinding
import com.akin.hepsiburada.databinding.FragmentProfileBinding
import com.akin.hepsiburada.screens.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginTabBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth;
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginTabBinding.inflate(inflater, container, false)

        val loginButton= binding.loginButton
        val emailText= binding.loginEmailEditText
        val passwordText= binding.loginPasswordEditTextText

        auth = Firebase.auth
        loginButton.setOnClickListener{
            val receivedemail = emailText.text.toString()
            val receivedpass = passwordText.text.toString()
            when {

                receivedemail.isEmpty() -> {
                    emailText.error = "E-mail can not be empty!"
                }
                receivedpass.isEmpty() -> {

                    passwordText.error = "Password can not be empty!"
                }
                else -> {
                    println(" $receivedemail $receivedpass")
                    auth.signInWithEmailAndPassword(receivedemail, receivedpass)
                        .addOnSuccessListener {
                            println("Giris Basarili")
                            startActivity(Intent(requireContext(),MainActivity::class.java))
                            activity?.finish()

                        }.addOnFailureListener {
                            showAlert("Warning!", it.message.toString())
                            println(it.toString())
                        }

                }
            }


        }

        return binding.root
    }private fun showAlert(title:String, message:String){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(title)
        builder.setMessage(message)


        builder.setPositiveButton("Okay") { dialog, which ->
                dialog.dismiss()
        }



        builder.show()

    }



}