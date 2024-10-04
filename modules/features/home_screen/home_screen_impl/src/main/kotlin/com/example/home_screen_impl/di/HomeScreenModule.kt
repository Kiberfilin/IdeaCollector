package com.example.home_screen_impl.di

import androidx.navigation.NavController
import com.example.home_screen_impl.navigation.HomeScreenRouter
import com.example.home_screen_impl.navigation.HomeScreenRouterImpl
import com.example.settings_api.SettingsScreenMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface HomeScreenModule {
    companion object {
        @Provides
        fun provideHomeScreenRouter(
            navController: NavController,
            settingsScreenMediator: SettingsScreenMediator
        ): HomeScreenRouter = HomeScreenRouterImpl(navController, settingsScreenMediator)

        @Provides
        fun provideSettingsScreenMediator(
            map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
        ): SettingsScreenMediator =
            map[SettingsScreenMediator::class.java]!!.get() as SettingsScreenMediator
    }
}