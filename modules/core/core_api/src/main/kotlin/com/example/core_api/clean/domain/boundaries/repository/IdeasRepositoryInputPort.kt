package com.example.core_api.clean.domain.boundaries.repository

import com.example.core_api.clean.domain.entities.IdeaEntity
import kotlinx.coroutines.flow.Flow

interface IdeasRepositoryInputPort {
    fun ideas(): Flow<List<IdeaEntity>>
    suspend fun insertIdea(ideaEntity: IdeaEntity)
    suspend fun deleteIdea(ideaEntity: IdeaEntity)
    suspend fun updateIdea(ideaEntity: IdeaEntity)
}