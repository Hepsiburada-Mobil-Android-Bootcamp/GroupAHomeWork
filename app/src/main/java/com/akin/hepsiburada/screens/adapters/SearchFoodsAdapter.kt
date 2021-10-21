package com.akin.hepsiburada.screens.adapters

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.airbnb.lottie.animation.content.Content
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.screens.fragments.HomeFragmentDirections
import com.akin.hepsiburada.screens.fragments.SearchFragmentDirections
import com.bumptech.glide.Glide
import com.crowdfire.cfalertdialog.CFAlertDialog

class SearchFoodsAdapter(private val foodList: List<FoodsModel>) :
    RecyclerView.Adapter<SearchFoodsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodTitle: TextView = itemView.findViewById(R.id.foodTitle)
        val foodDesc: TextView = itemView.findViewById(R.id.foodDesc)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val foodCalorie: TextView = itemView.findViewById(R.id.foodCalory)
        val constraintLayout :ConstraintLayout = itemView.findViewById(R.id.constraintLy)
        val context = itemView.context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFoodsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFoodsAdapter.ViewHolder, position: Int) {
        holder.foodTitle.text = foodList[position].name
        holder.foodDesc.text = foodList[position].ingredients[0]
        holder.foodPrice.text = foodList[position].price.toString()
        holder.foodCalorie.text = foodList[position].calory
        //Glide tek sefer olusturulacak
        Glide.with(holder.context).load(foodList[position].image).circleCrop()
            .into(holder.foodImage)

        holder.constraintLayout.setOnClickListener {
            val alert : CFAlertDialog.Builder = CFAlertDialog.Builder(holder.context).setDialogStyle(CFAlertDialog.CFAlertStyle.BOTTOM_SHEET).
                    setTitle("Alert").setMessage("Do you want to see details").addButton("Yummy!", -1, -1,
                CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END,
                 object :DialogInterface.OnCancelListener, DialogInterface.OnClickListener {
                     override fun onCancel(p0: DialogInterface?) {
                         TODO("Not yet implemented")
                     }

                     override fun onClick(p0: DialogInterface?, p1: Int) {

                        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(holder.foodTitle.text.toString())
                         it.findNavController().navigate(action)
                        p0?.dismiss()
                     }
                 })
            alert.show()



        }


    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}