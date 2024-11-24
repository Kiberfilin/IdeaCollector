package com.example.core_api.contracts

import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetPersistedThemeInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.GetThemeFlowInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.HashPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.IsPasswordEnabledInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistPasswordInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.PersistThemeInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort

interface UseCasesProvider {
    fun provideGetAllIdeasInputPort(): GetAllIdeasInputPort
    fun provideInsertIdeaInputPort(): InsertIdeaInputPort
    fun provideDeleteIdeaInputPort(): DeleteIdeaInputPort
    fun provideUpdateIdeaInputPort(): UpdateIdeaInputPort
    fun providePersistPasswordInputPort(): PersistPasswordInputPort
    fun provideGetPersistedPasswordInputPort(): GetPersistedPasswordInputPort
    fun provideHashPasswordInputPort(): HashPasswordInputPort
    fun provideIsPasswordEnabledInputPort(): IsPasswordEnabledInputPort
    fun provideGetThemeFlowInputPort(): GetThemeFlowInputPort
    fun providePersistThemeInputPort(): PersistThemeInputPort
    fun provideGetPersistedThemeInputPort(): GetPersistedThemeInputPort
}