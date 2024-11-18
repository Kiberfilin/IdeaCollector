package com.example.core_impl.clean.data.repositories.implementations

import android.content.SharedPreferences
import com.example.core_api.clean.domain.boundaries.repository.SharedPreferencesRepositoryInputPort
import com.example.core_api.di.qualifier.Encrypted
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    @Encrypted private val encryptedSharedPreferences: SharedPreferences,
) : SharedPreferencesRepositoryInputPort {

    override fun encryptedPersistString(key: String, value: String?) {
        encryptedSharedPreferences.edit().putString(key, value).apply()
    }

    override fun encryptedGetPersistedString(key: String, defValue: String?): String? {
        return encryptedSharedPreferences.getString(key, defValue)
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }

    override fun getString(key: String, defValue: String?): String? {
        return sharedPreferences.getString(key, defValue)
    }
}