package com.akin.hepsiburada.domain.factory

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akin.hepsiburada.domain.FavoritsViewModel
import com.akin.hepsiburada.domain.ProfileViewModel

class ProfileViewModelFactory(val context:Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(context) as T
    }
}