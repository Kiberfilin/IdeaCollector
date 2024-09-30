package com.example.home_screen_impl.di

import com.example.home_screen_api.HomeScreenMediator
import com.example.settings_api.SettingsScreenMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface HomeScreenModule {
    companion object {
        @Provides
        fun provideHomeScreenMediator(
            map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
        ): HomeScreenMediator =
            map[HomeScreenMediator::class.java]!!.get() as HomeScreenMediator
    }
}