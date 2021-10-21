package com.akin.hepsiburada.screens.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.databinding.FragmentFavoriBinding
import com.bumptech.glide.Glide

class FavoriViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val favFoodTitle : TextView by lazy {
    view.findViewById(R.id.FavFoodTitle)
}
    private val favFoodCalory : TextView by lazy {
        view.findViewById(R.id.FavFoodCalory)
    }
    private val favFoodDesc : TextView by lazy {
        view.findViewById(R.id.FavFoodDesc)
    }
    private val favFoodPrice : TextView by lazy {
        view.findViewById(R.id.FavFoodPrice)
    }
    private val favFoodImage : ImageView by lazy {
        view.findViewById(R.id.FavFoodImage)
    }




    @SuppressLint("SetTextI18n")
    fun bind(food: FoodsModel) {
        favFoodTitle.text = food.name
        favFoodCalory.text = food.calory + "Calories"
        favFoodDesc.text = food.category
        favFoodPrice.text = food.price.toString()
        Glide.with(itemView.context).load(food.image).circleCrop().into(favFoodImage)
    }
}