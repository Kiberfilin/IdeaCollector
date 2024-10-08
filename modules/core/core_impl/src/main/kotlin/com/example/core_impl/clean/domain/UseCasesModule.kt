package com.example.core_impl.clean.domain

import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_impl.clean.domain.usecases.GetAllIdeasInteractor
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {
    @Binds
    fun bindGetAllIdeasInputPort(interactor: GetAllIdeasInteractor): GetAllIdeasInputPort
}