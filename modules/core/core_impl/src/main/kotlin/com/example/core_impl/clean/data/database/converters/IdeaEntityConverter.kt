package com.example.core_impl.clean.data.database.converters

import com.example.core_api.clean.domain.entities.IdeaEntity
import com.example.core_api.database.IdeaRoomEntity

object IdeaEntityConverter {
    fun convertFromDbToDomain(roomEntity: IdeaRoomEntity): IdeaEntity =
        IdeaEntity(
            id = roomEntity.id,
            ideaText = roomEntity.ideaText,
            date = roomEntity.date,
            priority = roomEntity.priority
        )

    fun convertFromDomainToDb(domainEntity: IdeaEntity): IdeaRoomEntity =
        IdeaRoomEntity(
            id = domainEntity.id,
            ideaText = domainEntity.ideaText,
            date = domainEntity.date,
            priority = domainEntity.priority
        )
}