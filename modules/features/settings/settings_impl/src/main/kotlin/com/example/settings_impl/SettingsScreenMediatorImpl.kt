package com.example.settings_impl

import androidx.navigation.NavController
import com.example.settings_api.SettingsScreenMediator
import javax.inject.Inject

class SettingsScreenMediatorImpl @Inject constructor() : SettingsScreenMediator {
    override fun openSettingsScreen(navController: NavController) {
        navController.navigate(R.id.settingsScreenFragment)
    }
}