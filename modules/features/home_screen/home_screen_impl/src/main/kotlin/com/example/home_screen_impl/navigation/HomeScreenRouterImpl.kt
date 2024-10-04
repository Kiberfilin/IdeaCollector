package com.example.home_screen_impl.navigation

import androidx.navigation.NavController
import com.example.settings_api.SettingsScreenMediator

internal class HomeScreenRouterImpl (
    private val navController: NavController,
    private val settingsScreenMediator: SettingsScreenMediator
) : HomeScreenRouter {
    override fun navigateToSettingsScreen() =
        settingsScreenMediator.openSettingsScreen(navController)
}