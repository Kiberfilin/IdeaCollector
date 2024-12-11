package com.example.home_screen_impl.di

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.core_api.contracts.ProvidersFacade
import com.example.core_api.di.qualifier.Authentication
import com.example.core_api.di.scope.FragmentScope
import com.example.home_screen_impl.HomeScreenFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.flow.MutableStateFlow

@FragmentScope
@Component(
    modules = [HomeScreenModule::class],
    dependencies = [ProvidersFacade::class]
)
interface HomeScreenComponent {

    companion object {
        fun makeHomeScreenComponent(
            providersFacade: ProvidersFacade,
            navController: NavController,
            lifecycle: Lifecycle,
            authStateFlow: MutableStateFlow<Boolean>
        ) = DaggerHomeScreenComponent.factory().create(
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
        ): HomeScreenComponent
    }

    fun inject(homeScreenFragment: HomeScreenFragment)
}
