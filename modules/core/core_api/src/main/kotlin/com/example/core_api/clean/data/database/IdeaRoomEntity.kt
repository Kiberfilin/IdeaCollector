package com.example.core_api.clean.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas_table")
data class IdeaRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val ideaText: String,
    val date: Long,
    val priority: Int
)
