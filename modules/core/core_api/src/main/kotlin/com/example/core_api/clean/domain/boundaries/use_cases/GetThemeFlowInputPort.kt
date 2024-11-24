package com.example.core_api.clean.domain.boundaries.use_cases

import kotlinx.coroutines.flow.Flow

interface GetThemeFlowInputPort {
    fun execute(): Flow<String?>
}