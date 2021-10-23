package com.akin.hepsiburada.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akin.hepsiburada.domain.DetailViewModel
import com.akin.hepsiburada.domain.FavoritsViewModel

class FavoritsViewModelFactory (val id : String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritsViewModel(id) as T
    }

}