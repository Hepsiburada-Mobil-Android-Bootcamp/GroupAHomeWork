package com.akin.hepsiburada.domain.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akin.hepsiburada.domain.BottomSheetViewModel

class BottomSheetFragmentFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BottomSheetViewModel(context) as T
    }
    }