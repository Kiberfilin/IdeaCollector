package com.example.core_api.clean.domain.boundaries.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepositoryInputPort {
    fun encryptedPersistString(key: String, value: String?)
    fun encryptedGetPersistedString(key: String, defValue: String?): String?
    fun getBoolean(key: String, defValue: Boolean): Boolean
    fun getString(key: String, defValue: String?): String?
    fun getThemePreferenceFlow(): Flow<String?>
    fun persistString(key: String, value: String?)
    suspend fun persistTheme(value: String)
}