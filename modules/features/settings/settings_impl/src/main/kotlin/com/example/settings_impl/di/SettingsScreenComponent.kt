package com.example.settings_impl.di

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.core_api.contracts.ProvidersFacade
import com.example.core_api.di.qualifier.Authentication
import com.example.core_api.di.scope.FragmentScope
import com.example.settings_impl.SettingsScreenFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.flow.MutableStateFlow

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
            lifecycle: Lifecycle,
            authStateFlow: MutableStateFlow<Boolean>
        ): SettingsScreenComponent = DaggerSettingsScreenComponent.factory().create(
            providersFacade,
            navController,
            lifecycle,
            authStateFlow
        )
    }

    @Component.Factory
    interface Factory {
        fun create(
            providersFacade: ProvidersFacade,
            @BindsInstance navController: NavController,
            @BindsInstance lifecycle: Lifecycle,
            @BindsInstance @Authentication authStateFlow: MutableStateFlow<Boolean>
        ): SettingsScreenComponent
    }

    fun inject(settingsScreenFragment: SettingsScreenFragment)
}