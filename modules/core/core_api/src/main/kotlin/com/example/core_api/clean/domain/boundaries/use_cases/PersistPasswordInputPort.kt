package com.example.core_api.clean.domain.boundaries.use_cases

interface PersistPasswordInputPort {
    fun execute(key: String, value: String?)
}