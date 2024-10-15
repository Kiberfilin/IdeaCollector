package com.example.core_api.clean.domain.boundaries.use_cases

import com.example.core_api.clean.domain.entities.IdeaEntity

interface InsertIdeaInputPort {
    suspend fun execute(ideaEntity: IdeaEntity)
}