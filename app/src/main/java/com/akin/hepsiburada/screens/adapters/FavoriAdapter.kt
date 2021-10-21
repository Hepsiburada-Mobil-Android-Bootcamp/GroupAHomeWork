package com.akin.hepsiburada.screens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel


class FavoriAdapter(private val list : List<FoodsModel>) :
    RecyclerView.Adapter<FavoriViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriViewHolder {
        return FavoriViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.favori_item,parent,false))
    }

    override fun onBindViewHolder(holder: FavoriViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}

