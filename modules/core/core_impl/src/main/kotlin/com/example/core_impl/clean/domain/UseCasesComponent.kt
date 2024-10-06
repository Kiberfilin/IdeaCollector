package com.example.core_impl.clean.domain

import com.example.core_api.contracts.UseCasesProvider
import com.example.core_api.database.DatabaseProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UseCasesModule::class], dependencies = [DatabaseProvider::class])
interface UseCasesComponent : UseCasesProvider