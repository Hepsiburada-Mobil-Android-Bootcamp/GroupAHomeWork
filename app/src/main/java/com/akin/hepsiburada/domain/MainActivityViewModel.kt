package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.UsersModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivityViewModel : ViewModel() {
    private val _userList = MutableLiveData<UsersModel>()
    val userList: LiveData<UsersModel> = _userList

    init {
        getUserInfo()
    }

    fun getUserInfo() {


        val db = Firebase.firestore


        db.collection("Users").document("kWxqVCzb3FfQCfK5ld1t").get()
            .addOnSuccessListener { result ->

                _userList.value = result.toObject(UsersModel::class.java)


            }
            .addOnFailureListener { exception ->
                println(exception)

            }.addOnCompleteListener {

            }


    }
}