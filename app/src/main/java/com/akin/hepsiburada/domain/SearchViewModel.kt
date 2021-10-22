package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchViewModel:ViewModel() {
    private val _foodList = MutableLiveData<List<FoodsModel>>()
    val searchFoodList: LiveData<List<FoodsModel>> = _foodList
      init {
          getAllFoods()
      }
    fun getSpesificFoods(alpha:String) {


        val db = Firebase.firestore


        db.collection("Foods").orderBy("name").startAt(alpha).endAt(alpha+ "\uf8ff").get()
            .addOnSuccessListener { result ->

                _foodList.value = result.toObjects(FoodsModel::class.java)


            }
            .addOnFailureListener { exception ->
                println(exception)

            }


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
}