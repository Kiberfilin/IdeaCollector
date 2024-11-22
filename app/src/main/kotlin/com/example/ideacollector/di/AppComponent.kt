package com.example.ideacollector.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core_api.contracts.AppProvider
import com.example.core_api.di.qualifier.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : AppProvider {
    companion object {
        private var appComponent: AppProvider? = null
        fun create(
            @Application appContext: Context
        ): AppProvider =
            appComponent ?: DaggerAppComponent.factory().create(appContext)
                .also { appComponent = it }
    }

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @Application context: Context): AppComponent
    }
}