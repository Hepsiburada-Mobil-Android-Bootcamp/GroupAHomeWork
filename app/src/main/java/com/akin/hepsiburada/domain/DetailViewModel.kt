package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailViewModel(val id : String) : ViewModel(){
    private val _foodList = MutableLiveData<List<FoodsModel>>()
    val detailFoodList: LiveData<List<FoodsModel>> = _foodList
    init {
        getDetail()
    }

    private fun getDetail() {
        val db = Firebase.firestore
        db.collection("Foods").whereEqualTo("id",id).get()
            .addOnSuccessListener { result ->
                _foodList.value = result.toObjects(FoodsModel::class.java)
            }
            .addOnFailureListener { exception ->
                println(exception)

            }
    }


}