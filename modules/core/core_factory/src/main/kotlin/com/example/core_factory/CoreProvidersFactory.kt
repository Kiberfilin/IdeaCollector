package com.example.core_factory

import com.example.core_api.database.DatabaseProvider
import com.example.core_api.contracts.AppProvider
import com.example.core_impl.DaggerDatabaseComponent

object CoreProvidersFactory {
    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider {
        return DaggerDatabaseComponent.builder().appProvider(appProvider).build()
    }
}