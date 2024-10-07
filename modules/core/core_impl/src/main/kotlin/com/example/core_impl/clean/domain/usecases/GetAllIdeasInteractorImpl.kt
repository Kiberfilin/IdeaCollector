package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.data.IdeasRepository
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.clean.domain.usecases.GetAllIdeasInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllIdeasInteractorImpl @Inject constructor(private val repository: IdeasRepository) :
    GetAllIdeasInteractor {
    override fun execute(): Flow<List<IdeaEntity>> = repository.ideas()
}