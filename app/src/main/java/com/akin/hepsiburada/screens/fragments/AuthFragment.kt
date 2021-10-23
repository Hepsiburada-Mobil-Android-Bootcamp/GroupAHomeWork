package com.akin.hepsiburada.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.akin.hepsiburada.R
import com.akin.hepsiburada.screens.adapters.ViewPagerAuthenticationAdapter
import com.google.android.material.tabs.TabLayout


class AuthFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view= inflater.inflate(R.layout.fragment_auth, container, false)
        val adapter= fragmentManager?.let { ViewPagerAuthenticationAdapter(it) }
        adapter?.addFragment(LoginFragment(),"Login")
        adapter?.addFragment(SignUpFragment(),"Register")
        val viewPager= view.findViewById<ViewPager>(R.id.view_pager)
        val tabs=view.findViewById<TabLayout>(R.id.tab_layout)
        viewPager.adapter=adapter
        tabs.setupWithViewPager(viewPager)
        return view
    }


}