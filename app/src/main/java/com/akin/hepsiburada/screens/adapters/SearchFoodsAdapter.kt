package com.akin.hepsiburada.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.screens.fragments.HomeFragmentDirections
import com.akin.hepsiburada.screens.fragments.SearchFragmentDirections
import com.bumptech.glide.Glide

class SearchFoodsAdapter(private val foodList: List<FoodsModel>) :
    RecyclerView.Adapter<SearchFoodsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodTitle: TextView = itemView.findViewById(R.id.foodTitle)
        val foodDesc: TextView = itemView.findViewById(R.id.foodDesc)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val foodCalorie: TextView = itemView.findViewById(R.id.foodCalory)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLy)
        val context = itemView.context
        val cardView : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchFoodsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchFoodsAdapter.ViewHolder, position: Int) {
        holder.foodTitle.text = foodList[position].name
        holder.foodDesc.text = foodList[position].ingredients[0]
        holder.foodPrice.text = foodList[position].price.toString()
        holder.foodCalorie.text = foodList[position].calory

        val key = foodList[position].id
        //Glide tek sefer olusturulacak
        Glide.with(holder.context).load(foodList[position].image).circleCrop()
            .into(holder.foodImage)
        holder.cardView.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(key)
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}


