package com.example.settings_impl.di

import androidx.navigation.NavController
import com.example.settings_impl.navigation.SettingsScreenRouter
import com.example.settings_impl.navigation.SettingsScreenRouterImpl
import dagger.Module
import dagger.Provides

@Module
interface SettingsScreenModule {
    companion object {
        @Provides
        fun provideSettingsScreenRouter(navController: NavController): SettingsScreenRouter =
            SettingsScreenRouterImpl(navController)
    }
}