package com.example.core_api.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ideas_table")
data class IdeaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val ideaText: String,
    val date: Long
)
