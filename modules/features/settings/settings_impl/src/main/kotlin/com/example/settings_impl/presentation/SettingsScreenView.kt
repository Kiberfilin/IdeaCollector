package com.example.settings_impl.presentation

import androidx.lifecycle.Lifecycle
import com.example.infrastructure.mvvm_blueprints.preference_fragment.BasePreferenceFragmentView
import com.example.settings_impl.presentation.custom.CustomDatastore
import javax.inject.Inject

class SettingsScreenView @Inject constructor(lifecycle: Lifecycle) :
    BasePreferenceFragmentView<SettingsScreenViewModel>(lifecycle) {
    suspend fun onPasswordValuePersisting(passwordString: String): String =
        viewModel.onPasswordValuePersisting(passwordString)

    fun providePasswordDatastore(): CustomDatastore =
        CustomDatastore(
            viewModel::onPersistingPasswordString,
            viewModel::onExtractingPersistedPasswordString
        )
    fun provideThemeDataStore(): CustomDatastore =
        CustomDatastore(viewModel::onPersistingThemeString,
            viewModel::onExtractingPersistedThemeString)
}