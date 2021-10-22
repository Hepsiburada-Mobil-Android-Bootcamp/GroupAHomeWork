package com.akin.hepsiburada.screens.fragments


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.akin.hepsiburada.data.FoodsModel

import com.akin.hepsiburada.databinding.PersistentBottomSheetBinding
import com.akin.hepsiburada.domain.BottomSheetFragmentFactory
import com.akin.hepsiburada.domain.BottomSheetViewModel
import com.akin.hepsiburada.domain.HomeViewModel
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class BottomSheetFragment() : BottomSheetDialogFragment() {
    private var _binding: PersistentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var addButton: Button? = null
    private val viewModel: BottomSheetViewModel by viewModels{
        BottomSheetFragmentFactory(requireContext())
    }
    private var nameText: EditText? = null
    private var priceText: EditText? = null
    private var categoriesText: EditText? = null
    private var ingredientsText: EditText? = null
    private var calorieText: EditText? = null
    private var addImage: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PersistentBottomSheetBinding.inflate(inflater, container, false)
        addButton = binding.addButton
        nameText = binding.nameEditText
        priceText = binding.priceEditText
        categoriesText = binding.categoriesEditText
        ingredientsText = binding.ingredientsEditText
        calorieText = binding.caloriesEditText
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientsText?.setOnClickListener {
            val alert : CFAlertDialog.Builder = CFAlertDialog.Builder(context).setDialogStyle(
                CFAlertDialog.CFAlertStyle.ALERT).
            setTitle("Important").setMessage("When you add ingredients, you have to put a comma between each item!").addButton("Okay", -1, -1,
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
        addButton?.setOnClickListener {

            when {
                ingredientsText?.text?.contains(",")==false -> {
                    ingredientsText?.error="Please put a comma between each item"
                }
                nameText?.text.isNullOrEmpty() -> {
                    nameText?.error = "Please write a food name"
                }
                priceText?.text.isNullOrEmpty() -> {
                    priceText?.error = "Please write a price"
                }
                categoriesText?.text.isNullOrEmpty() -> {
                    categoriesText?.error = "Please write a category"
                }
                calorieText?.text.isNullOrEmpty() -> {
                    calorieText?.error = "Please write a calorie"
                }
                else -> {

                    //val key = Firebase.auth.currentUser?.uid
                    val randomId = (1..100000).random()
                    //val newKey = key+randomId.toString()
                    println(randomId)
                    val list = (ingredientsText?.text?.split(","))

                    viewModel.addNewFood(nameText?.text.toString(),priceText?.text.toString().toDouble(),"https://i.pinimg.com/564x/df/80/d1/df80d1b9f2db2b5d915835b8eb9c8c5c.jpg",
                        categoriesText?.text.toString(),
                        list as ArrayList<String>,calorieText?.text.toString(),"5555"
                   )


                }
            }

        }


    }
}