package com.example.core_api.clean.domain.boundaries.use_cases

import com.example.core_api.clean.domain.entities.IdeaEntity
import kotlinx.coroutines.flow.Flow

interface GetAllIdeasInputPort {
    fun execute(): Flow<List<IdeaEntity>>
}