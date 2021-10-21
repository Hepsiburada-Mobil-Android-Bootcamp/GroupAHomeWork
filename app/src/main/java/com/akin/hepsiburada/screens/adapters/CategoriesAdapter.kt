package com.akin.hepsiburada.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.CategoriesModel
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.screens.components.ICategoriesOnClick
import com.bumptech.glide.Glide

class CategoriesAdapter(private val categoriesList:List<CategoriesModel>):RecyclerView.Adapter<CategoriesAdapter.PostHolder>() {

   inner class PostHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.categories_item_title)
        val image: ImageView = itemView.findViewById(R.id.categories_item_image)
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
        val context = itemView.context



    }
    private var listener: ICategoriesOnClick? = null
    private var selectedItem = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_item, parent, false)


        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.nameText.text = categoriesList[position].name
        Glide.with(holder.context).load( categoriesList[position].image).into(holder.image)


            if (position == selectedItem) {
                holder.linearLayout.setBackgroundResource(R.drawable.categories_bg)
                holder.linearLayout.animate().scaleX(1.1f)
                holder.linearLayout.animate().scaleY(1.1f)


            } else {
                holder.linearLayout.setBackgroundResource(R.drawable.categories_notseleceted_bg)
                holder.linearLayout.animate().scaleX(1f)
                holder.linearLayout.animate().scaleY(1f)
            }
        holder.linearLayout.setOnClickListener {
            selectedItem = holder.adapterPosition
            listener?.let {
                listener?.onClick(position)
            }
            notifyDataSetChanged()
        }






    }

    override fun getItemCount(): Int {
        return  categoriesList.size
    }
    fun addListener(listener: ICategoriesOnClick) {
        this.listener = listener
    }

    fun removeListeners() {
        this.listener = null
    }


}