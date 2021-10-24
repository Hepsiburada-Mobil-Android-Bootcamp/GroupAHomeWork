package com.akin.hepsiburada.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView

import com.akin.hepsiburada.R
import com.akin.hepsiburada.data.FoodsModel
import com.akin.hepsiburada.domain.FavoritsViewModel
import com.akin.hepsiburada.domain.factory.BottomSheetFragmentFactory
import com.akin.hepsiburada.domain.factory.FavoritsViewModelFactory
import com.akin.hepsiburada.screens.fragments.HomeFragment
import com.akin.hepsiburada.screens.fragments.HomeFragmentDirections
import com.akin.hepsiburada.util.load
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.crowdfire.cfalertdialog.CFAlertDialog
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFoodsAdapter(
    private val foodList: List<FoodsModel>,
    private val homeFragment: HomeFragment,

    ) :
    RecyclerView.Adapter<HomeFoodsAdapter.ViewHolder>() {

    var itemClickListener: (food: FoodsModel) -> Unit = {}

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodImage: ImageView = itemView.findViewById(R.id.foodImage)
        val foodTitle: TextView = itemView.findViewById(R.id.foodTitle)
        val foodPrice: TextView = itemView.findViewById(R.id.foodPrice)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLy)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val favIcon: ImageView = itemView.findViewById(R.id.favIcon)
        val context = itemView.context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFoodsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_item, parent, false)



        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeFoodsAdapter.ViewHolder, position: Int) {

        position.apply {

            holder.foodTitle.text = foodList[this].name
            holder.foodPrice.text = foodList[this].price.toString()
            holder.foodTitle.text = foodList[this].name
            holder.foodPrice.text = foodList[this].price.toString()
            val key = foodList[this].id
            holder.foodImage.load(foodList[this].image)
            holder.favIcon.setOnClickListener {
                itemClickListener(foodList[this])
                homeFragment.playLikeAnim()

            }
            holder.cardView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(key)
                it.findNavController().navigate(action)

            }

        }

    }


    override fun getItemCount(): Int {
        return foodList.size
    }

}