package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.domain.boundaries.repository.RepositoryInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.clean.domain.boundaries.use_cases.GetAllIdeasInputPort
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllIdeasInteractor @Inject constructor(private val dataInputPort: RepositoryInputPort) :
    GetAllIdeasInputPort {
    override fun execute(): Flow<List<IdeaEntity>> = dataInputPort.ideas()
}