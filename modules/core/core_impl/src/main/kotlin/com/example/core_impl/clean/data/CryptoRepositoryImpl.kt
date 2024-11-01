package com.example.core_impl.clean.data

import android.content.SharedPreferences
import com.example.core_api.clean.data.encryption.Hasher
import com.example.core_api.clean.domain.boundaries.repository.CryptoRepositoryInputPort
import com.example.core_api.di.qualifier.Encrypted
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(
    private val hasher: Hasher,
    @Encrypted
    private val preferences: SharedPreferences
) : CryptoRepositoryInputPort {
    override suspend fun hashPassword(clearText: String): String =
        hasher.md5(clearText)

    override fun persistString(key: String, value: String?) {
        preferences.edit().putString(key, value).apply()
    }

    override fun getPersistedString(key: String, defValue: String?): String? {
        return preferences.getString(key, defValue)
    }
}