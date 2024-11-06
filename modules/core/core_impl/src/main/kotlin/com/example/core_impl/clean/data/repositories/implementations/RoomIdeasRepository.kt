package com.example.core_impl.clean.data.repositories.implementations

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

    override suspend fun insertIdea(ideaEntity: IdeaEntity) =
        dao.insertIdea(IdeaEntityConverter.convertFromDomainToDb(ideaEntity))

    override suspend fun deleteIdea(ideaEntity: IdeaEntity) =
        dao.deleteIdea(IdeaEntityConverter.convertFromDomainToDb(ideaEntity))

    override suspend fun updateIdea(ideaEntity: IdeaEntity) =
        dao.updateIdea(IdeaEntityConverter.convertFromDomainToDb(ideaEntity))
}