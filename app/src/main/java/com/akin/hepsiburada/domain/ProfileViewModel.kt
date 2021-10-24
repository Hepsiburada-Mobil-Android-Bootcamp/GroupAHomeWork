package com.akin.hepsiburada.domain

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.data.UsersModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList

class ProfileViewModel(var context: Context) : ViewModel() {

    private lateinit var auth: FirebaseAuth
    private val _profileList = MutableLiveData<List<UsersModel>>()
    val profileList: LiveData<List<UsersModel>> = _profileList


    fun addAndGetImageFromFirebase(
        name: String,
        number: String,
        address: String,
        uid: String?,
        uri: Uri
    ) {
        val db = Firebase.firestore
        val uuid = UUID.randomUUID()
        val imageName: String = "$uuid.jpg"
        val storageRef = FirebaseStorage.getInstance().getReference("images/$imageName")
        storageRef.putFile(uri).continueWithTask {


            storageRef.downloadUrl

        }.addOnFailureListener {

            println(it.toString())
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUrl = task.result
                println(downloadUrl)
                val newUser = UsersModel(name, number, address, uid.toString(),downloadUrl.toString())
                db.collection("Users").document().set(newUser).addOnCompleteListener {
                    println("SUPRAAAAAAAAAAA")
                }
            }


        }
    }

    fun registerUser(email: String, password: String) {
        val db = Firebase.firestore
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Başarılı", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}