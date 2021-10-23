package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.akin.hepsiburada.R


class ChooseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_choose, container, false)

        val chooseNextButton=view.findViewById<Button>(R.id.chooseNextButton)

        chooseNextButton.setOnClickListener{
            val action=ChooseFragmentDirections.actionChooseFragmentToPhotoUploadFragment2()
            it.findNavController().navigate(action)
        }

        return view
    }


}