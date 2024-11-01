package com.example.core_factory

import com.example.core_api.contracts.AppProvider
import com.example.core_api.contracts.UseCasesProvider
import com.example.core_impl.clean.data.database.DaggerDatabaseComponent
import com.example.core_impl.clean.data.encryption.DaggerCryptoComponent
import com.example.core_impl.clean.domain.DaggerUseCasesComponent

object CoreProvidersFactory {
    fun createUsecasesProvider(appProvider: AppProvider): UseCasesProvider {
        return DaggerUseCasesComponent.builder().databaseProvider(
            DaggerDatabaseComponent.builder().appProvider(appProvider).build()
        ).securityProvider(
            DaggerCryptoComponent.builder().appProvider(appProvider).build()
        ).build()
    }
}