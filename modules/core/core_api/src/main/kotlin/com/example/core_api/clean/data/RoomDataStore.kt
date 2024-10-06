package com.example.core_api.clean.data

import com.example.core_api.clean.domain.entities.IdeaEntity
import kotlinx.coroutines.flow.Flow

interface RoomDataStore {
    fun ideas(): Flow<List<IdeaEntity>>
}