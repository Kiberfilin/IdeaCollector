package com.example.core_api.clean.domain.boundaries.use_cases

interface GetPersistedThemeInputPort {
    fun execute(key: String, defValue: String?): String?
}