package com.example.settings_impl

import androidx.navigation.NavController
import com.example.settings_api.SettingsScreenMediator

class SettingsScreenMediatorImpl: SettingsScreenMediator {
    override fun openSettingsScreen(navController: NavController) {
        navController.navigate(R.id.settingsScreenFragment)
    }
}