package com.example.home_screen_impl.di

import com.example.core_api.contracts.ProvidersFacade
import com.example.home_screen_impl.HomeScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [HomeScreenModule::class],
    dependencies = [ProvidersFacade::class]
)
interface HomeScreenComponent {

    companion object{
        fun create(providersFacade: ProvidersFacade) =
            DaggerHomeScreenComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
    }
    fun inject(homeScreenFragment: HomeScreenFragment)
}
