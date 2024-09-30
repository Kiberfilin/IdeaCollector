package com.example.ideacollector.di

import android.content.Context
import com.example.core_api.contracts.AppProvider
import com.example.core_api.contracts.ProvidersFacade
import com.example.core_api.database.DatabaseProvider
import com.example.core_api.di.qualifier.Application
import com.example.core_factory.CoreProvidersFactory
import com.example.home_screen_impl.di.HomeScreenComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [AppProvider::class, DatabaseProvider::class],
)
interface FacadeComponent : ProvidersFacade {
    companion object {

        fun init(@Application context: Context): FacadeComponent {
            val appProvider = AppComponent.create(context)
            return DaggerFacadeComponent.builder()
                .appProvider(appProvider)
//                .databaseProvider(
//                    CoreProvidersFactory.createDatabaseBuilder(appProvider)
//                )
                .build()
        }
    }
}