package com.akin.hepsiburada.domain

import android.content.Context
import android.content.DialogInterface
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.akin.hepsiburada.data.FoodsModel
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BottomSheetViewModel(val context: Context) : ViewModel() {
    var x: String? = null
    fun addNewFood(
        name: String,
        price: Double,
        image: String,
        category: String,
        ingredients: ArrayList<String>,
        calory: String,
        id: String
    ) {
        val newFood = FoodsModel(name, price, image, category, ingredients, calory, id)
        val db = Firebase.firestore


        db.collection("Foods").document().set(newFood).addOnCompleteListener {
           alert()
        }
    }

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



    private  fun alert(){
        val alert: CFAlertDialog.Builder = CFAlertDialog.Builder(context)
            .setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET).setTitle("Alert")
            .setMessage("Do you want to see details").addButton("Yummy!", -1, -1,
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


