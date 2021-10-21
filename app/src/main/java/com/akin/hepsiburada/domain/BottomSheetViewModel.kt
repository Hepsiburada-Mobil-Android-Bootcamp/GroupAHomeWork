package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BottomSheetViewModel : ViewModel() {

    fun addNewFood( name: String, price:Double, image:String, category:String, ingredients:ArrayList<String>, calory:String){
        val newFood = FoodsModel(name,price,image,category,ingredients,calory)
        val db = Firebase.firestore
        db.collection("Foods").document().set(newFood)
    }
}