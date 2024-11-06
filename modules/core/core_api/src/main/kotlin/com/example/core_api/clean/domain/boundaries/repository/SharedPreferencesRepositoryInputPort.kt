package com.example.core_api.clean.domain.boundaries.repository

interface SharedPreferencesRepositoryInputPort {
    fun encryptedPersistString(key: String, value: String?)
    fun encryptedGetPersistedString(key: String, defValue: String?): String?
    fun getBoolean(key: String, defValue: Boolean): Boolean
}