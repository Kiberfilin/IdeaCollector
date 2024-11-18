package com.example.settings_impl.presentation

import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.HashPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistPasswordInputPort
import com.example.infrastructure.mvvm_blueprints.BaseViewModel
import com.example.settings_impl.navigation.SettingsScreenRouter

class SettingsScreenViewModel(
    private val persistPassword: PersistPasswordInputPort,
    private val getPersistedPassword: GetPersistedPasswordInputPort,
    private val hashPassword: HashPasswordInputPort
) : BaseViewModel<SettingsScreenRouter>() {
    fun onPersistingPasswordString(key: String, value: String?) =
        persistPassword.execute(key, value)

    fun onExtractingPersistedPasswordString(key: String, defValue: String?): String =
        getPersistedPassword.execute(key, defValue).orEmpty()

    suspend fun onPasswordValuePersisting(passwordString: String): String =
        hashPassword.execute(passwordString)
}