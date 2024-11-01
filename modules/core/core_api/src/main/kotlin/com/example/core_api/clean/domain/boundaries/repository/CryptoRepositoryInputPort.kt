package com.example.core_api.clean.domain.boundaries.repository

interface CryptoRepositoryInputPort {
    suspend fun hashPassword(clearText: String): String
    fun persistString(key: String, value: String?)
    fun getPersistedString(key: String, defValue: String?): String?
}