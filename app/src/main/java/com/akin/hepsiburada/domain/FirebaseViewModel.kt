package com.akin.hepsiburada.domain

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize


class FirebaseViewModel() : ViewModel() {
    private val _foodList = MutableLiveData<List<FoodsModel>>()
    val foodList: LiveData<List<FoodsModel>> = _foodList

    private val _categoriesList = MutableLiveData<List<String>>()
    val categoriesList: LiveData<List<String>> = _categoriesList

    init{

        getAllFoods()
        getCategories()
    }

    private fun getAllFoods(){

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
    private fun getCategories(){

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

}