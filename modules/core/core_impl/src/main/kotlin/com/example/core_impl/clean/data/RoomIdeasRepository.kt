package com.example.core_impl.clean.data

import com.example.core_api.clean.domain.boundaries.repository.IdeasRepositoryInputPort
import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.clean.data.database.IdeaDao
import com.example.core_api.clean.data.database.IdeaRoomEntity
import com.example.core_impl.clean.data.database.converters.IdeaEntityConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomIdeasRepository @Inject constructor(private val dao: IdeaDao) : IdeasRepositoryInputPort {
    override fun ideas(): Flow<List<IdeaEntity>> =
        dao.ideas().map { ideasFromRoom: List<IdeaRoomEntity> ->
            ideasFromRoom.map { ideaRoomEntity: IdeaRoomEntity ->
                IdeaEntityConverter.convertFromDbToDomain(ideaRoomEntity)
            }
        }
}