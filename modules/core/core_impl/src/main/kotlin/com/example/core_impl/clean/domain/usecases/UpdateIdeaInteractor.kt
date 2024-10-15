package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.UpdateIdeaInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import javax.inject.Inject

class UpdateIdeaInteractor @Inject constructor(private val dataInputPort: IdeasRepositoryInputPort) :
    UpdateIdeaInputPort {
    override suspend fun execute(ideaEntity: IdeaEntity) = dataInputPort.updateIdea(ideaEntity)
}