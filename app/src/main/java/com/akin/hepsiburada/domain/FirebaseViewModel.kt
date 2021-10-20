package com.akin.hepsiburada.domain

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize


class FirebaseViewModel() : ViewModel() {

    private lateinit var auth : FirebaseAuth

    private val _foodList = MutableLiveData<List<FoodsModel>>()
    val foodList: LiveData<List<FoodsModel>> = _foodList

    private val _categoriesList = MutableLiveData<List<String>>()
    val categoriesList: LiveData<List<String>> = _categoriesList

    private val _favList = MutableLiveData<List<String>>()
    val favList: LiveData<List<String>> = _favList


     fun getAllFoods(){

       // Firebase.initialize(context)
        val db = Firebase.firestore


        db.collection("Foods").get()
            .addOnSuccessListener { result->

                val returnObject =   result.toObjects(FoodsModel::class.java)
                _foodList.value = returnObject

            }
            .addOnFailureListener { exception ->
                println(exception)

            }
    }
     fun getCategories(){

        // Firebase.initialize(context)
        val db = Firebase.firestore


        db.collection("Categorys").get()
            .addOnSuccessListener { result->

                for (document in result) {
                    val categories:ArrayList<String> = document.get("name") as ArrayList<String>
                    _categoriesList.value = categories


                }

            }
            .addOnFailureListener { exception ->
                println(exception)

            }
    }

     fun getAllFav(){

        // Firebase.initialize(context)
        val db = Firebase.firestore
        auth = Firebase.auth

        db.collection("Users").document(auth.currentUser!!.uid).collection("Favorits").get()
            .addOnSuccessListener { result->
                for (document in result) {
                    val favorits:ArrayList<String> = document.get("foodId") as ArrayList<String>
                    _favList.value = favorits
                }


            }
            .addOnFailureListener { exception ->
                println(exception)

            }
    }




}