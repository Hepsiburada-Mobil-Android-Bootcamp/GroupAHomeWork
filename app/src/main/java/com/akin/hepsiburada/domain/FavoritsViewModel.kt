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

class FavoritsViewModel(private val userId: String) : ViewModel() {
    private val _favList = MutableLiveData<List<String>>()
    val favList: LiveData<List<String>> = _favList
    private val _currentFavList = MutableLiveData<MutableList<FoodsModel>>()
    val currentFavList: LiveData<MutableList<FoodsModel>> = _currentFavList
    private val isComplete = MutableLiveData<Boolean>()
    private lateinit var auth: FirebaseAuth

    init {
        getAllFav()
    }

    private fun getAllFav() {
        val list = mutableListOf<FoodsModel>()
        val db = Firebase.firestore
        auth = Firebase.auth


        db.collection("Users").document(userId).collection("Favorits").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    _favList.value = document.get("foodId") as ArrayList<String>


                }

            }
            .addOnFailureListener { exception ->
                println(exception)

            }.addOnCompleteListener {


                for (document in favList.value!!) {

                    db.collection("Foods").document(document).get()
                        .addOnSuccessListener { result ->

                            result.toObject(FoodsModel::class.java)?.let { it1 ->
                                list.add(it1)

                            }

                        }
                        .addOnFailureListener { exception ->
                            println(exception)

                        }.addOnCompleteListener {
                            ///Courotines kullanilacak eger mumkunse

                            if (document == favList.value!![favList.value!!.lastIndex]) {
                                isComplete.value = true
                            }


                        }
                }
                println(list)

                isComplete.observeForever {

                    _currentFavList.value = list


                }

            }

    }

    fun addFoodsToFav() {
        println("asaaaaa")
        val db = Firebase.firestore
        db.collection("Users").document("3JZBBgTFlWD6U6SDBj8A").collection("Favorits").document().update("foodId", FieldValue.arrayUnion("1111111111"))



    }
}