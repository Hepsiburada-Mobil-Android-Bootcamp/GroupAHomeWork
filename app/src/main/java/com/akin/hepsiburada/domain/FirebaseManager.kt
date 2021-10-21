package com.akin.hepsiburada.domain

import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseManager {
    fun getAllFoods(): List<FoodsModel>? {

        // Firebase.initialize(context)
        val db = Firebase.firestore
        var alpha : List<FoodsModel> ?= null


        db.collection("Foods").get()
            .addOnSuccessListener { result->
                alpha =   result.toObjects(FoodsModel::class.java)

            }
            .addOnFailureListener { exception ->
                println(exception)

            }

        return alpha
    }
}