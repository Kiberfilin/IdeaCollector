package com.example.core_api.contracts

import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort

interface UseCasesProvider {
    fun provideGetAllIdeasInputPort(): GetAllIdeasInputPort
}