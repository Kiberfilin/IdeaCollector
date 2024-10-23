package com.example.settings_impl.presentation

import androidx.lifecycle.Lifecycle
import com.example.infrastructure.mvvm_blueprints.preference_fragment.BasePreferenceFragmentView
import javax.inject.Inject

class SettingsScreenView @Inject constructor(lifecycle: Lifecycle) :
    BasePreferenceFragmentView<SettingsScreenViewModel>(lifecycle) {
}