package com.example.core_api.clean.domain.boundaries.use_cases

import kotlinx.coroutines.flow.Flow

interface GetThemeInputPort {
    fun execute(): Flow<String?>
}