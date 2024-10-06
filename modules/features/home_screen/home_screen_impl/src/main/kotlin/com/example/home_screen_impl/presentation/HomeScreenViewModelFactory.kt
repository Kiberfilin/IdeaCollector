package com.example.home_screen_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.clean.domain.usecases.GetAllIdeasInteractor
import javax.inject.Inject

class HomeScreenViewModelFactory @Inject constructor(
    private val getAllIdeasInteractor: GetAllIdeasInteractor
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when {
            modelClass.isAssignableFrom(HomeScreenViewModel::class.java) ->
                return HomeScreenViewModel(getAllIdeasInteractor) as T

            else                                                         -> throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}