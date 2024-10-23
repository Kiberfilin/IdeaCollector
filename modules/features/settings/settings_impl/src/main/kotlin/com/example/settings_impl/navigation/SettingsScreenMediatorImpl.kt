package com.example.settings_impl.navigation

import androidx.navigation.NavController
import com.example.settings_api.SettingsScreenMediator
import com.example.settings_impl.R
import javax.inject.Inject

class SettingsScreenMediatorImpl @Inject constructor() : SettingsScreenMediator {
    override fun openSettingsScreen(navController: NavController) {
        navController.navigate(R.id.settingsScreenFragment)
    }
}