package com.example.core_impl.clean.data

import com.example.core_api.clean.data.RoomDataStore
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.database.IdeaDao
import com.example.core_api.database.IdeaRoomEntity
import com.example.core_impl.clean.data.database.converters.IdeaEntityConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataStoreImpl @Inject constructor(private val dao: IdeaDao) : RoomDataStore {
    override fun ideas(): Flow<List<IdeaEntity>> =
        dao.ideas().map { ideasFromRoom: List<IdeaRoomEntity> ->
            ideasFromRoom.map { ideaRoomEntity: IdeaRoomEntity ->
                IdeaEntityConverter.convertFromDbToDomain(ideaRoomEntity)
            }
        }
}