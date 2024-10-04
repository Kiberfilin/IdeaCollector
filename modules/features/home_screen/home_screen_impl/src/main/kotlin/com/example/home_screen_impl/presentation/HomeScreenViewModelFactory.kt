package com.example.home_screen_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HomeScreenViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when {
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) ->
                return HomeScreenViewModel() as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}