package com.example.home_screen_impl.di

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.core_api.contracts.ProvidersFacade
import com.example.core_api.di.scope.FragmentScope
import com.example.home_screen_impl.HomeScreenFragment
import dagger.BindsInstance
import dagger.Component

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
            lifecycle: Lifecycle
        ) = DaggerHomeScreenComponent.factory().create(
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
        ): HomeScreenComponent
    }

    fun inject(homeScreenFragment: HomeScreenFragment)
}
