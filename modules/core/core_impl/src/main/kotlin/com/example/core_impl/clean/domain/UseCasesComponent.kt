package com.example.core_impl.clean.domain

import com.example.core_api.contracts.UseCasesProvider
import com.example.core_api.clean.data.database.DatabaseProvider
import com.example.core_api.clean.data.encryption.SecurityProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [UseCasesModule::class],
    dependencies = [DatabaseProvider::class, SecurityProvider::class]
)
interface UseCasesComponent : UseCasesProvider