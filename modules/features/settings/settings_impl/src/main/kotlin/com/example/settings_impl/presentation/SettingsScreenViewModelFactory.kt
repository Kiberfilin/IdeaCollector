package com.example.settings_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SettingsScreenViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when {
            modelClass.isAssignableFrom(SettingsScreenViewModel::class.java) ->
                return SettingsScreenViewModel() as T

            else                                                             -> throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}