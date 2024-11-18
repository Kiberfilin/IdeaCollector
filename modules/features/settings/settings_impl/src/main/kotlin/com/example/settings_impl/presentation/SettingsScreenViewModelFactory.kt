package com.example.settings_impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetThemeInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.HashPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistPasswordInputPort
import javax.inject.Inject

class SettingsScreenViewModelFactory @Inject constructor(
    private val persistPasswordInputPort: PersistPasswordInputPort,
    private val getPersistedPasswordInputPort: GetPersistedPasswordInputPort,
    private val hashPasswordInputPort: HashPasswordInputPort,
    private val getThemeInputPort: GetThemeInputPort
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when {
            modelClass.isAssignableFrom(
                SettingsScreenViewModel::class.java
            )    -> return SettingsScreenViewModel(
                persistPasswordInputPort,
                getPersistedPasswordInputPort,
                hashPasswordInputPort,
                getThemeInputPort
            ) as T

            else -> throw IllegalArgumentException(
                "Unknown ViewModel class"
            )
        }
    }
}