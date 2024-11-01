package com.example.core_api.clean.domain.boundaries.use_cases

interface HashPasswordInputPort {
    suspend fun execute(clearText: String): String
}