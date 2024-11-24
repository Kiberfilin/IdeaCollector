package com.example.settings_impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedThemeInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.HashPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistThemeInputPort
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
import com.example.settings_impl.navigation.SettingsScreenRouter
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val persistPassword: PersistPasswordInputPort,
    private val getPersistedPassword: GetPersistedPasswordInputPort,
    private val hashPassword: HashPasswordInputPort,
    private val getPersistedTheme: GetPersistedThemeInputPort,
    private val persistTheme: PersistThemeInputPort
) : BaseViewModel<SettingsScreenRouter>() {
    fun onPersistingPasswordString(key: String, value: String?) =
        persistPassword.execute(key, value)

    fun onExtractingPersistedPasswordString(key: String, defValue: String?): String =
        getPersistedPassword.execute(key, defValue).orEmpty()

    suspend fun onPasswordValuePersisting(passwordString: String): String =
        hashPassword.execute(passwordString)

    fun onPersistingThemeString(key: String, value: String?) {
        viewModelScope.launch {
            value?.let { persistTheme.execute(key, it) }
        }
    }

    fun onExtractingPersistedThemeString(key: String, defValue: String?): String =
        getPersistedTheme.execute(key, defValue).orEmpty()
}