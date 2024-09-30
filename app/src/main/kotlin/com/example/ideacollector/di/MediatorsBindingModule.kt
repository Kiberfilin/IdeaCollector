package com.example.ideacollector.di

import com.example.home_screen_api.HomeScreenMediator
import com.example.home_screen_impl.HomeScreenMediatorImpl
import com.example.settings_api.SettingsScreenMediator
import com.example.settings_impl.SettingsScreenMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
interface MediatorsBindingModule {
    @Binds
    @IntoMap
    @ClassKey(HomeScreenMediator::class)
    fun bindMediator(mediator: HomeScreenMediatorImpl): Any

    @Binds
    @IntoMap
    @ClassKey(SettingsScreenMediator::class)
    fun bindMediator(mediator: SettingsScreenMediatorImpl)
}