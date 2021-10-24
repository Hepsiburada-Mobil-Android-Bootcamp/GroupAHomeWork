package com.akin.hepsiburada.screens.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.akin.hepsiburada.R
import com.akin.hepsiburada.domain.ProfileViewModel
import com.akin.hepsiburada.domain.factory.ProfileViewModelFactory
import com.akin.hepsiburada.screens.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern


class SignUpFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    private val profileViewModel:ProfileViewModel by viewModels {
        ProfileViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_up_tab, container, false)

        val signup_button=view.findViewById<Button>(R.id.signup_button)


        val email=view.findViewById<EditText>(R.id.signup_emailEditText)
        val password=view.findViewById<EditText>(R.id.signup_passwordEditText)
        val username=view.findViewById<EditText>(R.id.signup_userNameEditText)
        val db = Firebase.firestore
        val address=view.findViewById<EditText>(R.id.signup_adressEditText)
        val number=view.findViewById<EditText>(R.id.singup_numberEditText)



        signup_button.setOnClickListener{
            if(email.text.toString().isEmpty()){
                email.error="Email is required"
                email.requestFocus()
                return@setOnClickListener
            }
            if(email.text.toString().length>50 || email.text.toString().length<5){
                email.error="Email is invalid."
                email.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
                email.error="Plase enter valid email."
                email.requestFocus()
                return@setOnClickListener
            }
            if(password.text.toString().isEmpty()){
                password.error="Please Enter Password."
                password.requestFocus()
                return@setOnClickListener
            }
            if(password.text.toString().length<7){
                password.error="Password is too short."
                password.requestFocus()
                return@setOnClickListener
            }
            if(password.text.toString().length>40){
                password.error="Password is too long."
                password.requestFocus()
                return@setOnClickListener
            }
            if(!passwordDigitValidate(password.text.toString())||!passwordLowerCaseValidate(password.text.toString())||!passwordUpperCaseValidate(password.text.toString())||!passwordSpecialCharsValidate(password.text.toString())){
                password.error="Password must contain one digit,one uppercase letter,one lowercase letter and one special character."
                password.requestFocus()
                return@setOnClickListener
            }
            if(username.text.toString().isEmpty()){
                username.error="Username is required."
                username.requestFocus()
                return@setOnClickListener
            }
            if(username.text.toString().length>20){
                username.error="Username is too long."
                username.requestFocus()
                return@setOnClickListener
            }
            if(username.text.toString().length<2){
                username.error="Username is too short."
                username.requestFocus()
                return@setOnClickListener
            }
            if(username.text.toString().length<2){
                username.error="Username is too short."
                username.requestFocus()
                return@setOnClickListener
            }
            if(!usernameLowerCaseValidate(username.text.toString())||!usernameNumberValidate(username.text.toString())||usernameUpperCaseValidate(username.text.toString())){
                username.error="Username can only include a-z,0-9 and _ characters."
                username.requestFocus()
                return@setOnClickListener
            }

            profileViewModel.registerUser(email.text.toString(),password.text.toString())
            val action=AuthFragmentDirections.actionAuthFragmentToChooseFragment(username.text.toString(),address.text.toString(),number.text.toString())
            it.findNavController().navigate(action)
        }
        return view
    }

    private fun usernameLowerCaseValidate(username:String?):Boolean{

        var p= Pattern.compile(".*[a-z].*")
        var m=p.matcher(username)
        return m.matches()
    }
    private fun usernameUpperCaseValidate(username:String?):Boolean{

        var p= Pattern.compile(".*[A-Z].*")
        var m=p.matcher(username)
        return m.matches()
    }
    private fun usernameNumberValidate(username:String?):Boolean{

        var p= Pattern.compile(".*[0-9].*")
        var m=p.matcher(username)
        return m.matches()
    }


    private fun passwordDigitValidate(password:String?):Boolean{

        var p= Pattern.compile(".*\\d.*")
        var m=p.matcher(password)
        return m.matches()
    }

    private fun passwordLowerCaseValidate(password: String?):Boolean{
        var p= Pattern.compile(".*[a-z].*")
        var m=p.matcher(password)
        return m.matches()
    }

    private fun passwordUpperCaseValidate(password: String?):Boolean{
        var p= Pattern.compile(".*[A-Z].*")
        var m=p.matcher(password)
        return m.matches()
    }

    private fun passwordSpecialCharsValidate(password: String?):Boolean{
        var p= Pattern.compile(".*[!@#$%^&*+=?-].*")
        var m=p.matcher(password)
        return m.matches()
    }


}