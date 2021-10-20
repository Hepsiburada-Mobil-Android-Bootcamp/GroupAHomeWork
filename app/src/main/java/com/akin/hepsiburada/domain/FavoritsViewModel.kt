package com.akin.hepsiburada.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoritsViewModel : ViewModel() {
    private val _favList = MutableLiveData<List<String>>()
    val favList: LiveData<List<String>> = _favList
    private lateinit var auth: FirebaseAuth

    init {
       getAllFav()
    }

   private fun getAllFav() {

        val db = Firebase.firestore
        auth = Firebase.auth


        db.collection("Users").document("3JZBBgTFlWD6U6SDBj8A").collection("Favorits").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    _favList.value = document.get("foodId") as ArrayList<String>

                }

            }
            .addOnFailureListener { exception ->
                println(exception)

            }

    }
}