package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.CategoriesModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeViewModel : ViewModel() {
    private val _foodList = MutableLiveData<List<FoodsModel>>()
    val foodList: LiveData<List<FoodsModel>> = _foodList

    private val _categoriesList = MutableLiveData<List<CategoriesModel>>()
    val categoriesList: LiveData<List<CategoriesModel>> = _categoriesList

    init {

        getAllFoods()
        getCategories()

    }

    private fun getAllFoods() {


        val db = Firebase.firestore


        db.collection("Foods").get()
            .addOnSuccessListener { result ->

                _foodList.value = result.toObjects(FoodsModel::class.java)


            }
            .addOnFailureListener { exception ->
                println(exception)

            }


    }

    private fun getCategories() {

        val db = Firebase.firestore


        db.collection("Categorys").orderBy("id").get()
            .addOnSuccessListener { result ->
                _categoriesList.value = result.toObjects(CategoriesModel::class.java)


            }
            .addOnFailureListener { exception ->
                println(exception)

            }

    }
}