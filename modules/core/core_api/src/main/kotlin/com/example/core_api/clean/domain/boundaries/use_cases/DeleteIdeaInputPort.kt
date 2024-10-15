package com.example.core_api.clean.domain.boundaries.use_cases

import com.example.core_api.clean.domain.entities.IdeaEntity

interface DeleteIdeaInputPort {
    suspend fun execute(ideaEntity: IdeaEntity)
}