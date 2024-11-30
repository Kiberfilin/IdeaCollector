package com.example.core_api.clean.domain.boundaries.use_cases

interface CheckIsPasswordCorrectInputPort {
    suspend fun execute(enteredPassword: String): Boolean
}