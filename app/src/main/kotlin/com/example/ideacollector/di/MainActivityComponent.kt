package com.example.ideacollector.di

import com.example.core_api.contracts.ProvidersFacade
import com.example.core_api.di.scope.ActivityScope
import com.example.ideacollector.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ProvidersFacade::class]
)
interface MainActivityComponent {
    @Component.Factory
    interface Factory {
        fun create(providersFacade: ProvidersFacade): MainActivityComponent
    }

    fun inject(activity: MainActivity)

    companion object {
        fun makeMainActivityComponent(providersFacade: ProvidersFacade) =
            DaggerMainActivityComponent.factory().create(providersFacade)
    }
}