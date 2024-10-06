package com.example.core_api.contracts

import com.example.core_api.clean.domain.usecases.GetAllIdeasInteractor

interface UseCasesProvider {
    fun provideGetAllIdeasInteractor(): GetAllIdeasInteractor
}