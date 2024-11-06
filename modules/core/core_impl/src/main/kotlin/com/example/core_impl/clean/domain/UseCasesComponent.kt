package com.example.core_impl.clean.domain

import com.example.core_api.clean.data.DataProvider
import com.example.core_api.contracts.UseCasesProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UseCasesModule::class], dependencies = [DataProvider::class])
interface UseCasesComponent : UseCasesProvider