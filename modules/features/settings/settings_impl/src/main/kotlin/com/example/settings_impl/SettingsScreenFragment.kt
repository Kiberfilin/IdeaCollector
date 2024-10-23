package com.example.settings_impl

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.example.core_api.contracts.AppWithFacade
import com.example.infrastructure.base_blueprints.preference_fragment.BasePreferenceFragment
import com.example.settings_impl.di.SettingsScreenComponent
import com.example.settings_impl.navigation.SettingsScreenRouter
import com.example.settings_impl.presentation.SettingsScreenView
import com.example.settings_impl.presentation.SettingsScreenViewModel
import com.example.settings_impl.presentation.SettingsScreenViewModelFactory
import javax.inject.Inject

class SettingsScreenFragment : BasePreferenceFragment<SettingsScreenRouter,
        SettingsScreenViewModel,
        SettingsScreenViewModelFactory,
        SettingsScreenView>() {
    override fun daggerInit() = SettingsScreenComponent
        .madeSettingsScreenComponent(
            (requireActivity().application as AppWithFacade).getFacade(),
            findNavController(),
            lifecycle
        ).inject(this)

    @Inject
    override lateinit var viewModelFactory: SettingsScreenViewModelFactory

    @Inject
    override lateinit var router: SettingsScreenRouter

    @Inject
    override lateinit var view: SettingsScreenView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAndSetViewModel(SettingsScreenViewModel::class.java)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        TODO("Not yet implemented")
    }

    companion object {
        fun newInstance() = SettingsScreenFragment()
    }
}