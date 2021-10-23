package com.akin.hepsiburada.util

import android.widget.ImageView
import com.akin.hepsiburada.R
import com.bumptech.glide.Glide

fun ImageView.load(imgUrl: String) {
    Glide.with(context)
        .load(imgUrl)
        .circleCrop()
        .placeholder(R.drawable.loading)
        .into(this)

}