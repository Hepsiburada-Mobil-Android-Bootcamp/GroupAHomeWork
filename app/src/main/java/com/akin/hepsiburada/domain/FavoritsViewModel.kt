package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akin.hepsiburada.data.FoodsModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class FavoritsViewModel(private val userId: String) : ViewModel() {
    private val _favList = MutableLiveData<List<String>>()
    val favList: LiveData<List<String>> = _favList
    private val _currentFavList = MutableLiveData<MutableList<FoodsModel>>()
    val currentFavList: LiveData<MutableList<FoodsModel>> = _currentFavList
    private val isComplete = MutableLiveData<Boolean>()
    private lateinit var auth: FirebaseAuth
    private var x: String? = null
    private val list = mutableListOf<String>()

    init {
        getAllFav()
    }

    private fun getAllFav() {

        val db = Firebase.firestore
        auth = Firebase.auth


        db.collection("Users").document(userId).collection("Favorits").get()
            .addOnSuccessListener { result ->

                for (i in result) {
                    i.toObject(FoodsModel::class.java).let {
                        list.add(it.id)
                    }

                }


            }
            .addOnFailureListener { exception ->
                println(exception)

            }.addOnCompleteListener {

                _favList.value = list
            }


    }

    fun addFoodsToFav(food: FoodsModel) {

        val db = Firebase.firestore
        db.collection("Foods").whereEqualTo("id", food.id).get().addOnSuccessListener { result ->
            for (document in result) {

                document.toObject(FoodsModel::class.java).let { it1 ->
                    db.collection("Users").document("3JZBBgTFlWD6U6SDBj8A").collection("Favorits")
                        .document().set(document)

                }
            }


        }.addOnCompleteListener {
            println("basarili")

        }.addOnFailureListener {
            println(it)
        }


    }



}

