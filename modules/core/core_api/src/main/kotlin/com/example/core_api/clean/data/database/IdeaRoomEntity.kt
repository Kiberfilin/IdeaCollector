package com.example.core_api.clean.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.core_api.clean.data.database.converters.PriorityConverter
import com.example.core_api.clean.domain.entities.Priority

@Entity(tableName = "ideas_table")
@TypeConverters(value = [PriorityConverter::class])
data class IdeaRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ideaText: String,
    val date: Long,
    val priority: Priority
)
