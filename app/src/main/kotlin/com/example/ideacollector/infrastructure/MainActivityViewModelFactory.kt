package com.example.ideacollector.infrastructure

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import javax.inject.Inject

class MainActivityViewModelFactory @Inject constructor(
    private val isPasswordEnabled: IsPasswordEnabledInputPort,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when {
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) ->
                return MainActivityViewModel(isPasswordEnabled) as T

            else -> throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}