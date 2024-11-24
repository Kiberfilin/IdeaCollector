package com.example.core_api.clean.domain.boundaries.use_cases

interface PersistThemeInputPort {
    suspend fun execute(key: String, value: String)
}