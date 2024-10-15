package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.InsertIdeaInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import javax.inject.Inject

class InsertIdeaInteractor @Inject constructor(private val dataInputPort: IdeasRepositoryInputPort) :
    InsertIdeaInputPort {
    override suspend fun execute(ideaEntity: IdeaEntity) = dataInputPort.insertIdea(ideaEntity)
}