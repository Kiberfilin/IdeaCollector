package com.example.settings_impl.di

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.core_api.contracts.ProvidersFacade
import com.example.core_api.di.scope.FragmentScope
import com.example.settings_impl.SettingsScreenFragment
import dagger.BindsInstance
import dagger.Component

@FragmentScope
@Component(
    modules = [SettingsScreenModule::class],
    dependencies = [ProvidersFacade::class]
)
interface SettingsScreenComponent {
    companion object {
        fun madeSettingsScreenComponent(
            providersFacade: ProvidersFacade,
            navController: NavController,
            lifecycle: Lifecycle
        ): SettingsScreenComponent = DaggerSettingsScreenComponent.factory().create(
            providersFacade,
            navController,
            lifecycle
        )
    }

    @Component.Factory
    interface Factory {
        fun create(
            providersFacade: ProvidersFacade,
            @BindsInstance navController: NavController,
            @BindsInstance lifecycle: Lifecycle
        ): SettingsScreenComponent
    }

    fun inject(settingsScreenFragment: SettingsScreenFragment)
}