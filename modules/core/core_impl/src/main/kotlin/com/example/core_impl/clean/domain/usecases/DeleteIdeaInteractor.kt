package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.boundaries.use_cases.DeleteIdeaInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import javax.inject.Inject

class DeleteIdeaInteractor @Inject constructor(private val dataInputPort: IdeasRepositoryInputPort) :
    DeleteIdeaInputPort {
    override suspend fun execute(ideaEntity: IdeaEntity) = dataInputPort.deleteIdea(ideaEntity)
}