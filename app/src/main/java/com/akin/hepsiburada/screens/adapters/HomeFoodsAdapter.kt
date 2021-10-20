package com.akin.hepsiburada.screens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.animation.content.Content
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.bumptech.glide.Glide

class HomeFoodsAdapter(private val foodList: List<FoodsModel>):RecyclerView.Adapter<HomeFoodsAdapter.ViewHolder>() {

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val foodImage : ImageView = itemView.findViewById(R.id.foodImage)
        val foodTitle : TextView = itemView.findViewById(R.id.foodTitle)
        val foodDesc : TextView = itemView.findViewById(R.id.foodDesc)
        val foodPrice : TextView = itemView.findViewById(R.id.foodPrice)
        val foodCalorie : TextView = itemView.findViewById(R.id.foodCalory)
        val context  = itemView.context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFoodsAdapter.ViewHolder {
      val view = LayoutInflater.from(parent.context)
          .inflate(R.layout.home_item, parent, false)

        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeFoodsAdapter.ViewHolder, position: Int) {
        holder.foodTitle.text = foodList[position].name
        holder.foodDesc.text = foodList[position].ingredients[0]
        holder.foodPrice.text = foodList[position].price.toString()
        holder.foodCalorie.text = foodList[position].calory
        Glide.with(holder.context).load( foodList[position].image).circleCrop().into(holder.foodImage)
    }

    override fun getItemCount(): Int {
        return  foodList.size
    }
}