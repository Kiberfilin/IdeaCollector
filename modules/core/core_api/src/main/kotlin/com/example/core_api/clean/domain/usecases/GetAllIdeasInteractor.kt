package com.example.core_api.clean.domain.usecases

import com.example.core_api.clean.domain.entities.IdeaEntity
import kotlinx.coroutines.flow.Flow

interface GetAllIdeasInteractor {
    fun execute(): Flow<List<IdeaEntity>>
}