package com.example.core_impl.clean.domain.usecases

import com.example.core_api.clean.data.RoomDataStore
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.clean.domain.usecases.GetAllIdeasInteractor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllIdeasInteractorImpl @Inject constructor(private val store: RoomDataStore) :
    GetAllIdeasInteractor {
    override fun execute(): Flow<List<IdeaEntity>> = store.ideas()
}