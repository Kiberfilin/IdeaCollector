package com.example.core_impl.clean.domain

import com.example.core_api.clean.domain.usecases.GetAllIdeasInteractor
import com.example.core_impl.clean.domain.usecases.GetAllIdeasInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface UseCasesModule {
    @Binds
    fun bindGetAllIdeasInteractor(interactor: GetAllIdeasInteractorImpl): GetAllIdeasInteractor
}