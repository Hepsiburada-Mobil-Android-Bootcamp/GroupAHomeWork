package com.akin.hepsiburada.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akin.hepsiburada.domain.DetailViewModel

class DetailViewModelFactory(val id : String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(id) as T
    }

}