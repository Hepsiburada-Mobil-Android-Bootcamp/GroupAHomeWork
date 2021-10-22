package com.akin.hepsiburada.screens.adapters

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.airbnb.lottie.animation.content.Content
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.screens.fragments.HomeFragmentDirections
import com.bumptech.glide.Glide
import com.crowdfire.cfalertdialog.CFAlertDialog

class HomeFoodsAdapter(private val foodList: List<FoodsModel>) :
    RecyclerView.Adapter<HomeFoodsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodTitle: TextView = itemView.findViewById(R.id.foodTitle)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val constraintLayout :ConstraintLayout = itemView.findViewById(R.id.constraintLy)
        val cardView :CardView = itemView.findViewById(R.id.cardView)
        val favIcon :ImageView = itemView.findViewById(R.id.favIcon)
        val context = itemView.context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFoodsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeFoodsAdapter.ViewHolder, position: Int) {
        holder.foodTitle.text = foodList[position].name
        holder.foodPrice.text = foodList[position].price.toString()
        val key = foodList[position].id
        //Glide tek sefer olusturulacak
        Glide.with(holder.context).load(foodList[position].image).circleCrop()
            .into(holder.foodImage)
        holder.favIcon.setOnClickListener {
            println("asdas")
        }
        holder.cardView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(key)
            it.findNavController().navigate(action)

        }


    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}