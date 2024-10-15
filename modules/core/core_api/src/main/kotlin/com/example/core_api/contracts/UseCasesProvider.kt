package com.example.core_api.contracts

import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort

interface UseCasesProvider {
    fun provideGetAllIdeasInputPort(): GetAllIdeasInputPort
    fun provideInsertIdeaInputPort(): InsertIdeaInputPort
    fun provideDeleteIdeaInputPort(): DeleteIdeaInputPort
    fun provideUpdateIdeaInputPort(): UpdateIdeaInputPort
}