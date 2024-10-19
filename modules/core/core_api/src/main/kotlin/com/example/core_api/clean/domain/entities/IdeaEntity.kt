package com.example.core_api.clean.domain.entities

data class IdeaEntity(
    val id: Long,
    val ideaText: String,
    val date: Long,
    val priority: Priority
)
