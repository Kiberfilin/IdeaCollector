package com.example.home_screen_impl.di

import com.example.settings_api.SettingsScreenMediator
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
interface HomeScreenModule {
    companion object {
        @Provides
        fun provideSettingsScreenMediator(
            map: Map<Class<*>, @JvmSuppressWildcards Provider<Any>>
        ): SettingsScreenMediator =
            map[SettingsScreenMediator::class.java]!!.get() as SettingsScreenMediator
    }
}