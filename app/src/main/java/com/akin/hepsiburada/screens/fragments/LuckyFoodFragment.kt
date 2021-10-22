package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.akin.hepsiburada.R
import com.akin.hepsiburada.screens.activity.MainActivity


class LuckyFoodFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val x = activity as MainActivity
        x.findViewById<ImageView>(R.id.drawerMenuIcon).visibility = View.VISIBLE
        x.findViewById<ImageView>(R.id.profilPic).visibility = View.VISIBLE
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lucky_food, container, false)
    }


}