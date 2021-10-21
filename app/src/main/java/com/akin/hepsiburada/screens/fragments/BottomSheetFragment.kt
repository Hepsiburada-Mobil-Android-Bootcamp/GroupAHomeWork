package com.akin.hepsiburada.screens.fragments


import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.akin.hepsiburada.data.FoodsModel

import com.akin.hepsiburada.databinding.PersistentBottomSheetBinding
import com.akin.hepsiburada.domain.BottomSheetViewModel
import com.akin.hepsiburada.domain.HomeViewModel
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class BottomSheetFragment() : BottomSheetDialogFragment() {
    private var _binding: PersistentBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var addButton: Button? = null
    private val viewModel: BottomSheetViewModel by viewModels()
    private var nameText: EditText? = null
    private var priceText: EditText? = null
    private var categoriesText: EditText? = null
    private var ingredientsText: EditText? = null
    private var calorieText: EditText? = null
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

                   // val list = mutableListOf<String>()
                    val list = ingredientsText?.text?.split(",").toString()

                    println(list)
//                    viewModel.addNewFood(nameText.text,priceText.text,categoriesText.text,
//                    ing)
                }
            }

        }


    }
}