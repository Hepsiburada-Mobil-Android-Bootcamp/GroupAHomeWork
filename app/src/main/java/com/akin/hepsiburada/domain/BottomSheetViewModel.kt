package com.akin.hepsiburada.domain

import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.akin.hepsiburada.data.FoodsModel
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList

class BottomSheetViewModel(val context: Context) : ViewModel() {
    val isClickable = MutableLiveData<Boolean>(false)
    var x: String? = null


    fun updateFood(
        name: String,
        price: Double,
        image: String,
        category: String,
        ingredients: ArrayList<String>,
        calory: String,
        id: String,

        ) {

        val db = Firebase.firestore

        db.collection("Foods").whereEqualTo("id", id).get().addOnSuccessListener { result ->
            for (document in result) {
                x = document.id
                println(x)
            }

        }.addOnCompleteListener {
            db.collection("Foods").document(x.toString()).update(
                "name", name, "price", price, "image", image,
                "category", category, "ingredients", ingredients, "calory", calory, "id", id
            )
        }

    }

    fun deleteFood(
        id: String,
    ) {

        val db = Firebase.firestore

        db.collection("Foods").whereEqualTo("id", id).get().addOnSuccessListener { result ->
            for (document in result) {
                x = document.id
            }

        }.addOnCompleteListener {
            db.collection("Foods").document(x.toString()).delete()
        }

    }


    fun addAndGetImageFromFirebase(
        uri: Uri,
        name: String,
        price: Double,
        category: String,
        ingredients: ArrayList<String>,
        calory: String,
        id: String,
    ) {
        val db = Firebase.firestore
        val uuid = UUID.randomUUID()
        val imageName: String = "$uuid.jpg"
        val storageRef = FirebaseStorage.getInstance().getReference("images/$imageName")
        storageRef.putFile(uri).continueWithTask {


            storageRef.downloadUrl

        }.addOnFailureListener {
            isClickable.value = true
            println(it.toString())
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUrl = task.result
                println(downloadUrl)
                val newFood = FoodsModel(
                    name,
                    price,
                    downloadUrl.toString(),
                    category,
                    ingredients,
                    calory,
                    id
                )
                db.collection("Foods").document().set(newFood).addOnCompleteListener {
                    alert()
                    isClickable.value = true
                }
            }


        }
    }

    private fun alert() {
        val alert: CFAlertDialog.Builder = CFAlertDialog.Builder(context)
            .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET).setTitle("Alert")
            .setMessage("New Food Successfully Add").addButton("Okay!", -1, -1,
                CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END,
                object : DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
                    override fun onCancel(p0: DialogInterface?) {
                        TODO("Not yet implemented")
                    }

                    override fun onClick(p0: DialogInterface?, p1: Int) {


                        p0?.dismiss()
                    }
                })
        alert.show()
    }

}


