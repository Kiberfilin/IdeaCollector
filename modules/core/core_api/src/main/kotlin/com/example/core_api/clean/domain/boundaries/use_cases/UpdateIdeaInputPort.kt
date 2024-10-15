package com.example.core_api.clean.domain.boundaries.use_cases

import com.example.core_api.clean.domain.entities.IdeaEntity

interface UpdateIdeaInputPort {
    suspend fun execute(ideaEntity: IdeaEntity)
}